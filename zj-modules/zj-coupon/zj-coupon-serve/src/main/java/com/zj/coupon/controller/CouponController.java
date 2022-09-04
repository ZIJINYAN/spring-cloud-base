package com.zj.coupon.controller;

import com.zj.common.core.domain.Result;
import com.zj.coupon.common.domain.UserCouponEntity;
import com.zj.coupon.service.ICouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zj
 * @create 2022-08-28 20:57
 */
@RestController
@RequestMapping("/coupon")
public class CouponController {

    @Autowired
    private ICouponService couponService;

    @PostMapping("/randomCoupon")
    public Result<UserCouponEntity> randomCoupon(){
        return Result.success(couponService.randomCoupon(), "成功");
    }

//    @GetMapping("/export")
//    public void couponList(HttpServletResponse response){
//        List<CouponEntity> list = couponService.list();
//        ExcelUtil<CouponEntity> couponExcel = new ExcelUtil<>(CouponEntity.class);
//        couponExcel.exportExcel(response, list, "用户优惠劵列表");
//
//    }
//
//    @PostMapping("/importData")
//    public Result<?> importData(MultipartFile file) throws Exception {
//        ExcelUtil<CouponEntity> couponExcel = new ExcelUtil<>(CouponEntity.class);
//        List<CouponEntity> couponEntityList = couponExcel.importExcel(file.getInputStream());
//        couponService.importCoupon(couponEntityList);
//        return Result.success();
//    }

//    @GetMapping("/hutool/export")
//    public void couponList(HttpServletResponse response) throws IOException {
//        List<CouponEntity> list = this.couponService.list();
//        List<List<String>> list1 = list.stream().map(item -> {
//            List<String> row = CollUtil.newArrayList();
//            String s = item.toString();
//            String str = s.split("\\(")[1];
//            String str1 = str.substring(0, str.length() - 1);
//            String[] split = str1.split(",");
//            Arrays.stream(split).forEach(i -> {
//                String str2 = i.split("=")[1];
//                row.add(str2);
//            });
//            return row;
//        }).collect(Collectors.toList());
//        System.out.println(list1);
//        // 通过工具类创建writer，默认创建xls格式
//        ExcelWriter writer = cn.hutool.poi.excel.ExcelUtil.getWriter();
//        // 一次性写出内容，使用默认样式，强制输出标题
//        writer.write(list1, true);
//        //out为OutputStream，需要写出到的目标流
//
//        //response为HttpServletResponse对象
//        response.setContentType("application/vnd.ms-excel;charset=utf-8");
//        //test.xls是弹出下载对话框的文件名，不能为中文，中文请自行编码
//        response.setHeader("Content-Disposition","attachment;filename=test.xls");
//        ServletOutputStream out=response.getOutputStream();
//
//        writer.flush(out, true);
//        // 关闭writer，释放内存
//        writer.close();
//        //此处记得关闭输出Servlet流
//        IoUtil.close(out);
//    }
//
//    @PostMapping("/importData")
//    public Result<?> importData(MultipartFile file) throws Exception {
//        ExcelReader reader = ExcelUtil.getReader(file.getInputStream());
//        List<CouponEntity> list = reader.read().stream().map(item -> {
//            CouponEntity coupon = new CouponEntity();
//            coupon.setCouponId(Integer.valueOf(item.get(0).toString()));
//            coupon.setCouponName((String) item.get(1));
//            coupon.setCouponFull(new BigDecimal(item.get(2).toString()));
//            coupon.setCouponReduce(new BigDecimal(item.get(3).toString()));
//            coupon.setCouponNum(Integer.valueOf(item.get(4).toString()));
//            return coupon;
//        }).collect(Collectors.toList());
//        System.out.println(list);
//
//        return Result.success();
//    }

}
