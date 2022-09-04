package com.zj.system.service.impl;

import cn.hutool.core.map.MapUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.zj.common.core.domain.PageResult;
import com.zj.common.core.domain.Result;
import com.zj.common.core.utils.StringUtils;
import com.zj.system.common.domain.GoodsEntity;
import com.zj.system.common.domain.GoodsTypeEntity;
import com.zj.system.common.request.GoodsRequest;
import com.zj.system.common.vo.GoodsItemVo;
import com.zj.system.mapper.GoodsMapper;
import com.zj.system.service.IGoodsService;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author zj
 * @create 2022-08-28 20:46
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, GoodsEntity> implements IGoodsService {

    private static final String INDEX_NAME = "goods";

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private RestHighLevelClient client;

    @Override
    public Result<PageResult<GoodsItemVo>> goodsEsList(GoodsRequest goodsRequest){
        List<GoodsItemVo> goodsItemVoList = new ArrayList<>();
        Long total = 0L;
        try {
            SearchRequest searchRequest = new SearchRequest(INDEX_NAME);
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            if(StringUtils.isNotEmpty(goodsRequest.getGoodsName())){
                boolQueryBuilder.must(QueryBuilders.matchQuery("goodsName",goodsRequest.getGoodsName()));
            }
            if(goodsRequest.getGoodsType()!=null){
                boolQueryBuilder.must(QueryBuilders.matchQuery("goodsTypeId",goodsRequest.getGoodsType()));
            }
            // es 分页
            searchSourceBuilder.from((goodsRequest.getPageNum()-1) * goodsRequest.getPageSize());
            searchSourceBuilder.size(goodsRequest.getPageSize());

            // 高亮
            HighlightBuilder highlightBuilder = new HighlightBuilder();
            highlightBuilder.field("goodsName");
            highlightBuilder.preTags("<span style=\'color:red\'>");
            highlightBuilder.postTags("</span>");
            searchSourceBuilder.highlighter(highlightBuilder);
            searchSourceBuilder.query(boolQueryBuilder);
            searchRequest.source(searchSourceBuilder);
            SearchResponse search = client.search(searchRequest, RequestOptions.DEFAULT);
            SearchHits searchHits = search.getHits();
            total = searchHits.getTotalHits().value;
            SearchHit[] hits = searchHits.getHits();
            Arrays.stream(hits).forEach(item->{
                String source = item.getSourceAsString();
                GoodsItemVo goodsItemVo = JSONObject.parseObject(source, GoodsItemVo.class);
                goodsItemVo.setGoodsId(Integer.valueOf(item.getId()));
                Map<String, HighlightField> highlightFields = item.getHighlightFields();
                if(MapUtil.isNotEmpty(highlightFields)){
                    HighlightField goodsName = highlightFields.get("goodsName");
                    if(goodsName!=null){
                        String str = goodsName.getFragments()[0].toString();
                        goodsItemVo.setGoodsName(str);
                    }
                }
                goodsItemVoList.add(goodsItemVo);
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return PageResult.toResult(total,goodsItemVoList);
    }

    /**
     * MyBatis Plus 联合查询
     */
    public List<GoodsItemVo> goodsList(){
        List<GoodsItemVo> goodsItemVoList = this.getBaseMapper().selectJoinList(
                GoodsItemVo.class,
                new MPJLambdaWrapper<GoodsEntity>()
                        .selectAll(GoodsEntity.class)
                        .selectAll(GoodsTypeEntity.class)
                        .leftJoin(GoodsTypeEntity.class, GoodsTypeEntity::getGoodsTypeId, GoodsEntity::getGoodsType)
        );
        return goodsItemVoList;
    }

    @PostConstruct
    public void esSyncAll(){
        try {
            List<GoodsItemVo> goodsItemVoList = this.goodsList();
            BulkRequest bulkRequest = new BulkRequest();
            goodsItemVoList.stream().forEach(item->{
                bulkRequest.add(
                        new IndexRequest(INDEX_NAME)
                                .id(item.getGoodsId().toString())
                                .source(JSONObject.toJSONString(item), XContentType.JSON)
                );
            });
            client.bulk(bulkRequest,RequestOptions.DEFAULT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
