package com.company.project.model;

import com.company.project.core.join.JoinField;

import javax.persistence.*;
import java.util.Date;

@Table(name = "lucky_bag")
public class LuckyBag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "agency_id")
    private Integer agencyId;

    @JoinField(sourcefield = "id")
    public LuckyBag luckyBag;


    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "room_id")
    private Integer roomId;

    private String name;

    private String pic;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "share_pic")
    private String sharePic;

    private String phone;

    @Column(name = "share_small_pic")
    private String shareSmallPic;

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
     * @return agency_id
     */
    public Integer getAgencyId() {
        return agencyId;
    }

    /**
     * @param agencyId
     */
    public void setAgencyId(Integer agencyId) {
        this.agencyId = agencyId;
    }

    /**
     * @return user_id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * @return room_id
     */
    public Integer getRoomId() {
        return roomId;
    }

    /**
     * @param roomId
     */
    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return pic
     */
    public String getPic() {
        return pic;
    }

    /**
     * @param pic
     */
    public void setPic(String pic) {
        this.pic = pic;
    }

    /**
     * @return created_date
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * @param createdDate
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * @return share_pic
     */
    public String getSharePic() {
        return sharePic;
    }

    /**
     * @param sharePic
     */
    public void setSharePic(String sharePic) {
        this.sharePic = sharePic;
    }

    /**
     * @return phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return share_small_pic
     */
    public String getShareSmallPic() {
        return shareSmallPic;
    }

    /**
     * @param shareSmallPic
     */
    public void setShareSmallPic(String shareSmallPic) {
        this.shareSmallPic = shareSmallPic;
    }
}