package com.company.project.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "litemall_category")
public class LitemallCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 类目名称
     */
    private String name;

    /**
     * 类目关键字，以JSON数组格式
     */
    private String keywords;

    /**
     * 类目广告语介绍
     */
    private String desc;

    /**
     * 父类目ID
     */
    private Integer pid;

    /**
     * 类目图标
     */
    @Column(name = "icon_url")
    private String iconUrl;

    /**
     * 类目图片
     */
    @Column(name = "pic_url")
    private String picUrl;

    private String level;

    /**
     * 排序
     */
    @Column(name = "sort_order")
    private Byte sortOrder;

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
     * 获取类目名称
     *
     * @return name - 类目名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置类目名称
     *
     * @param name 类目名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取类目关键字，以JSON数组格式
     *
     * @return keywords - 类目关键字，以JSON数组格式
     */
    public String getKeywords() {
        return keywords;
    }

    /**
     * 设置类目关键字，以JSON数组格式
     *
     * @param keywords 类目关键字，以JSON数组格式
     */
    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    /**
     * 获取类目广告语介绍
     *
     * @return desc - 类目广告语介绍
     */
    public String getDesc() {
        return desc;
    }

    /**
     * 设置类目广告语介绍
     *
     * @param desc 类目广告语介绍
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * 获取父类目ID
     *
     * @return pid - 父类目ID
     */
    public Integer getPid() {
        return pid;
    }

    /**
     * 设置父类目ID
     *
     * @param pid 父类目ID
     */
    public void setPid(Integer pid) {
        this.pid = pid;
    }

    /**
     * 获取类目图标
     *
     * @return icon_url - 类目图标
     */
    public String getIconUrl() {
        return iconUrl;
    }

    /**
     * 设置类目图标
     *
     * @param iconUrl 类目图标
     */
    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    /**
     * 获取类目图片
     *
     * @return pic_url - 类目图片
     */
    public String getPicUrl() {
        return picUrl;
    }

    /**
     * 设置类目图片
     *
     * @param picUrl 类目图片
     */
    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    /**
     * @return level
     */
    public String getLevel() {
        return level;
    }

    /**
     * @param level
     */
    public void setLevel(String level) {
        this.level = level;
    }

    /**
     * 获取排序
     *
     * @return sort_order - 排序
     */
    public Byte getSortOrder() {
        return sortOrder;
    }

    /**
     * 设置排序
     *
     * @param sortOrder 排序
     */
    public void setSortOrder(Byte sortOrder) {
        this.sortOrder = sortOrder;
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