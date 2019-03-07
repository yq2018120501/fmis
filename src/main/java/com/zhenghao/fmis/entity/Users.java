package com.zhenghao.fmis.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Users {

    private Integer userid;

    private String username;

    private String password;

    private Date regeditDate;

    private Date disabledDate;

    private Integer isDisabled;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegeditDate() {
        return regeditDate;
    }

    public void setRegeditDate(Date regeditDate) {
        this.regeditDate = regeditDate;
    }

    public Date getDisabledDate() {
        return disabledDate;
    }

    public void setDisabledDate(Date disabledDate) {
        this.disabledDate = disabledDate;
    }

    public Integer getIsDisabled() {
        return isDisabled;
    }

    public void setIsDisabled(Integer isDisabled) {
        this.isDisabled = isDisabled;
    }
}
