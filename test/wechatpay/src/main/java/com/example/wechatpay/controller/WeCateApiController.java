package com.example.wechatpay.controller;


import com.example.wechatpay.util.WechatPayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/wechat")
public class WeCateApiController {

    static Logger logger= LoggerFactory.getLogger(WeCateApiController.class);
//    /**
//     * @return java.lang.String
//     * @Author hfq
//     * @Description
//     * @Date 2020/1/3 15:49
//     * @Param [request, response]
//     **/
//    @PostMapping("/callback")
//    public String payNotify(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        InputStream inStream = request.getInputStream();
//        StringBuffer sffer = new StringBuffer();
//        BufferedReader buffInt = null;
//        HashMap<String, String> return_data = null;
//        try {
//            String str = new String();
//            return_data = new HashMap<String, String>();
//            buffInt = new BufferedReader(new InputStreamReader(inStream, "UTF-8"));
//            while ((str = buffInt.readLine()) != null) {
//                sffer.append(str);
//            }
//            Map<String, String> results = WechatPayUtils.doXMLParse(sffer.toString());
//            logger.info("微信支付回调参数【" + results + "】");
//
//
//            //微信支付单号
//            String transactionId = results.get("transaction_id");
//            //微信订单号
//            String outTradeNo = results.get("out_trade_no");
//            /**
//             *判断该笔订单是否已经处理（去三方流水表中查询当前订单的状态）
//             */
//
//
//            if (!"SUCCESS".equals(results.get("return_code").toString())) {
//                return_data.put("return_code", "FAIL");
//                return_data.put("return_msg", "return_code不正确");
//            }
//
//            if (!"SUCCESS".equals(results.get("result_code").toString())) {
//                return_data.put("return_code", "FAIL");
//                return_data.put("return_msg", "result_code不正确");
//            }
//
//            //判断金额是否正确
//            long total_amount = new BigDecimal(results.get("total_fee")).longValue();
//            if (total_amount != tripartitePay.getAmount().multiply(new BigDecimal(100)).setScale(0, BigDecimal.ROUND_HALF_UP).longValue()) {
//                return_data.put("return_code", "FAIL");
//                return_data.put("return_msg", "返回金额不正确");
//                // throw new BusinessException(BusinessCodeEnum.RETURN_ERROR.getCode(), "金额total_amount错误");
//            }
//
//            /**
//             * 第一步：首先通过resultCode判断付款成功还是失败
//             * 第二步：如果付款失败：
//             *          1、修改三方流水表
//             */
//            PayCallBackParam payCallBackParam = PayCallBackParam.builder()
//                    .outTradeNo(outTradeNo)
//                    .payNumber(transactionId).timeEnd(results.get("time_end")).totalFee(results.get("total_fee")).returnMsg(results.get("return_msg")).resultCode(results.get("result_code")).errCode(results.get("err_code")).errCodeDesc(results.get("err_code_des")).payRetundType(PayRefundTypeEnum.PAY_MODE.getCode().toString()).transactionId(tripartitePay.getTransactionId()).tripartitePayInfoId(tripartitePay.getId()).transactionCode(tripartitePay.getTransactionCode())
//                    .result(sffer.toString())
//                    .build();
//
//            //处理业务
//
//            return_data.put("return_code", "SUCCESS");
//            return_data.put("return_msg", "OK");
//            return WechatPayUtils.setXML(return_data);
//
//        } catch (BusinessException e) {
//            if (Objects.nonNull(return_data)) {
//                return_data.put("return_code", "FAIL");
//                return_data.put("return_msg", "运行时异常");
//            }
//            //throw new BusinessException(BusinessCodeEnum.SYSTEM_ERROR.getCode(),e.getMessage());
//        } finally {
//            if (Objects.nonNull(buffInt)) {
//                buffInt.close();
//            }
//            if (Objects.nonNull(inStream)) {
//                inStream.close();
//            }
//        }
//    }
//
}
