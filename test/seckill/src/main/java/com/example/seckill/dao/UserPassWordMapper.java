package com.example.seckill.dao;

import com.example.seckill.dataObject.UserPassWord;

public interface UserPassWordMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_password
     *
     * @mbg.generated Thu Oct 15 23:37:50 CST 2020
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_password
     *
     * @mbg.generated Thu Oct 15 23:37:50 CST 2020
     */
    int insert(UserPassWord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_password
     *
     * @mbg.generated Thu Oct 15 23:37:50 CST 2020
     */
    int insertSelective(UserPassWord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_password
     *
     * @mbg.generated Thu Oct 15 23:37:50 CST 2020
     */
    UserPassWord selectByPrimaryKey(Integer id);
    //根据用户ID查询
    UserPassWord selectUserById(Integer userId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_password
     *
     * @mbg.generated Thu Oct 15 23:37:50 CST 2020
     */
    int updateByPrimaryKeySelective(UserPassWord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_password
     *
     * @mbg.generated Thu Oct 15 23:37:50 CST 2020
     */
    int updateByPrimaryKey(UserPassWord record);
}