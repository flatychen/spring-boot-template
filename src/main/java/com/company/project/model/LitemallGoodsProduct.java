package com.company.project.model;

import com.company.project.core.join.JoinField;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "litemall_goods_product")
public class LitemallGoodsProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @JoinField(sourceField = "goodsId")
    @Transient
    private LitemallGoods litemallGoods;


    public LitemallGoods getLitemallGoods() {
        return litemallGoods;
    }

    public LitemallGoodsProduct setLitemallGoods(LitemallGoods litemallGoods) {
        this.litemallGoods = litemallGoods;
        return this;
    }

    /**
     * 商品表的商品ID
     */
    @Column(name = "goods_id")
    private Integer goodsId;

    /**
     * 商品规格值列表，采用JSON数组格式
     */
    private String specifications;

    /**
     * 商品货品价格
     */
    private BigDecimal price;

    /**
     * 商品货品数量
     */
    private Integer number;

    /**
     * 商品货品图片
     */
    private String url;

    /**
     * 创建时间
     */
    @Column(name = "add_time")
    private Date addTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 逻辑删除
     */
    private Boolean deleted;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取商品表的商品ID
     *
     * @return goods_id - 商品表的商品ID
     */
    public Integer getGoodsId() {
        return goodsId;
    }

    /**
     * 设置商品表的商品ID
     *
     * @param goodsId 商品表的商品ID
     */
    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    /**
     * 获取商品规格值列表，采用JSON数组格式
     *
     * @return specifications - 商品规格值列表，采用JSON数组格式
     */
    public String getSpecifications() {
        return specifications;
    }

    /**
     * 设置商品规格值列表，采用JSON数组格式
     *
     * @param specifications 商品规格值列表，采用JSON数组格式
     */
    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }

    /**
     * 获取商品货品价格
     *
     * @return price - 商品货品价格
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * 设置商品货品价格
     *
     * @param price 商品货品价格
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * 获取商品货品数量
     *
     * @return number - 商品货品数量
     */
    public Integer getNumber() {
        return number;
    }

    /**
     * 设置商品货品数量
     *
     * @param number 商品货品数量
     */
    public void setNumber(Integer number) {
        this.number = number;
    }

    /**
     * 获取商品货品图片
     *
     * @return url - 商品货品图片
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置商品货品图片
     *
     * @param url 商品货品图片
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取创建时间
     *
     * @return add_time - 创建时间
     */
    public Date getAddTime() {
        return addTime;
    }

    /**
     * 设置创建时间
     *
     * @param addTime 创建时间
     */
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取逻辑删除
     *
     * @return deleted - 逻辑删除
     */
    public Boolean getDeleted() {
        return deleted;
    }

    /**
     * 设置逻辑删除
     *
     * @param deleted 逻辑删除
     */
    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}