package com.example.demo.pattern.bridging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 具体实现化角色：其是实现化角色的具体实现类，比如这里的PayFaceMode、PayFingerprintMode、PayCypher类
 * 刷脸支付模拟
 */
public class PayFaceMode implements IPayMode{

    protected Logger logger = LoggerFactory.getLogger(PayFaceMode.class);

    @Override
    public boolean security(String uId) {
        logger.info("人脸支付，风控校验脸部识别");
        return true;
    }
}
