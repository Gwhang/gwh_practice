package com.example.demo.pattern.bridging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *  具体实现化角色：其是实现化角色的具体实现类，比如这里的PayFaceMode、PayFingerprintMode、PayCypher类
 * 指纹支付
 */
public class PayFingerprintMode implements IPayMode{

    protected Logger logger = LoggerFactory.getLogger(PayFingerprintMode.class);

    @Override
    public boolean security(String uId) {
        logger.info("指纹支付，风控校验指纹信息");
        return true;
    }
}
