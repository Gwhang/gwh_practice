package com.example.wechat.service;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserInfoServiceTest {

    private UserInfoService userInfoService=new UserInfoService();

    @Test
    public void getUserInfo() {
        userInfoService.getUserInfo("ojpF7jsGGt07TjaU8d-JqESEudfI");
    }
}