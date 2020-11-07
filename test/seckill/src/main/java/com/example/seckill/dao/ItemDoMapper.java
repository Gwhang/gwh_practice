package com.example.seckill.dao;

import com.example.seckill.dataObject.ItemDo;

import java.util.List;

public interface ItemDoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item
     *
     * @mbg.generated Sat Nov 07 21:34:40 CST 2020
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item
     *
     * @mbg.generated Sat Nov 07 21:34:40 CST 2020
     */
    int insert(ItemDo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item
     *
     * @mbg.generated Sat Nov 07 21:34:40 CST 2020
     */
    int insertSelective(ItemDo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item
     *
     * @mbg.generated Sat Nov 07 21:34:40 CST 2020
     */
    ItemDo selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item
     *
     * @mbg.generated Sat Nov 07 21:34:40 CST 2020
     */
    int updateByPrimaryKeySelective(ItemDo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item
     *
     * @mbg.generated Sat Nov 07 21:34:40 CST 2020
     */
    int updateByPrimaryKey(ItemDo record);

    List<ItemDo> selectItemList();
}