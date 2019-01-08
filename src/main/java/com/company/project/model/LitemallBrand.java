package com.company.project.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "litemall_brand")
public class LitemallBrand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 品牌商名称
     */
    private String name;

    /**
     * 品牌商简介
     */
    private String desc;

    /**
     * 品牌商页的品牌商图片
     */
    @Column(name = "pic_url")
    private String picUrl;

    @Column(name = "sort_order")
    private Byte sortOrder;

    /**
     * 品牌商的商品低价，仅用于页面展示
     */
    @Column(name = "floor_price")
    private BigDecimal floorPrice;

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
     * 获取品牌商名称
     *
     * @return name - 品牌商名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置品牌商名称
     *
     * @param name 品牌商名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取品牌商简介
     *
     * @return desc - 品牌商简介
     */
    public String getDesc() {
        return desc;
    }

    /**
     * 设置品牌商简介
     *
     * @param desc 品牌商简介
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * 获取品牌商页的品牌商图片
     *
     * @return pic_url - 品牌商页的品牌商图片
     */
    public String getPicUrl() {
        return picUrl;
    }

    /**
     * 设置品牌商页的品牌商图片
     *
     * @param picUrl 品牌商页的品牌商图片
     */
    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    /**
     * @return sort_order
     */
    public Byte getSortOrder() {
        return sortOrder;
    }

    /**
     * @param sortOrder
     */
    public void setSortOrder(Byte sortOrder) {
        this.sortOrder = sortOrder;
    }

    /**
     * 获取品牌商的商品低价，仅用于页面展示
     *
     * @return floor_price - 品牌商的商品低价，仅用于页面展示
     */
    public BigDecimal getFloorPrice() {
        return floorPrice;
    }

    /**
     * 设置品牌商的商品低价，仅用于页面展示
     *
     * @param floorPrice 品牌商的商品低价，仅用于页面展示
     */
    public void setFloorPrice(BigDecimal floorPrice) {
        this.floorPrice = floorPrice;
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