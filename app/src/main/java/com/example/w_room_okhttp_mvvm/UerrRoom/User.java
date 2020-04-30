package com.example.w_room_okhttp_mvvm.UerrRoom;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "pdd_user_tableeee")
public class User {

    @PrimaryKey(autoGenerate = true)
    public int id;
    @SerializedName("code")
    public String code;
    @SerializedName("access_id")
    public String accessId;
    @SerializedName("user_name")
    public String userName;
    @SerializedName("relationId")
    public String relationId;
    @SerializedName("mch_status")
    public String mchStatus;
    @SerializedName("headimgurl")
    public String headImgurl;
    @SerializedName("y_password")
    public String yPassword;
    @SerializedName("wx_status")
    public String wxStatus;
    @SerializedName("message")
    public String message;

    public User() {
    }

    public User(int id, String code, String accessId, String userName, String relationId, String mchStatus, String headImgurl, String yPassword, String wxStatus, String message) {
        this.id = id;
        this.code = code;
        this.accessId = accessId;
        this.userName = userName;
        this.relationId = relationId;
        this.mchStatus = mchStatus;
        this.headImgurl = headImgurl;
        this.yPassword = yPassword;
        this.wxStatus = wxStatus;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAccessId() {
        return accessId;
    }

    public void setAccessId(String accessId) {
        this.accessId = accessId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRelationId() {
        return relationId;
    }

    public void setRelationId(String relationId) {
        this.relationId = relationId;
    }

    public String getMchStatus() {
        return mchStatus;
    }

    public void setMchStatus(String mchStatus) {
        this.mchStatus = mchStatus;
    }

    public String getHeadImgurl() {
        return headImgurl;
    }

    public void setHeadImgurl(String headImgurl) {
        this.headImgurl = headImgurl;
    }

    public String getyPassword() {
        return yPassword;
    }

    public void setyPassword(String yPassword) {
        this.yPassword = yPassword;
    }

    public String getWxStatus() {
        return wxStatus;
    }

    public void setWxStatus(String wxStatus) {
        this.wxStatus = wxStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
