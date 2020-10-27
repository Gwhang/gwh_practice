package com.example.wechat.service;

import org.junit.Test;

import static org.junit.Assert.*;

public class QrCodeServiceTest {

    private QrCodeService qrCodeService=new QrCodeService();

    @Test
    public void qrCode() {
        qrCodeService.qrCode("崽崽芮是小傻子");

    }
}