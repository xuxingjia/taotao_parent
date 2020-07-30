package com.study.demo.taotao_pojo.pojo;


import com.study.demo.taotao_pojo.params.AuthorityInfo;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class TbUser implements Serializable {

  private long id;
  @NotBlank(message = "用户名不能为空！！！")
  private String userName;
  @NotBlank(message = "密码不能为空！！！")
  private String password;
  private String phone;
  private String email;
//  @NotBlank(message = "创建时间不能为空！！！")
  private java.sql.Timestamp created;
//  @NotBlank(message = "更新时间不能为空！！！")
  private java.sql.Timestamp updated;
  private String sourceType;
  private String nickName;
  private String name;
  private String status;
  private String headPic;
  private String qq;
  private double accountBalance;
  private String isMobileCheck;
  private String isEmailCheck;
  private String sex;
  private long userLevel;
  private long points;
  private long experienceValue;
  private java.sql.Timestamp birthday;
  private java.sql.Timestamp lastLoginTime;
  @NotBlank(message = "验证码不能为空！！！")
  private String smsCode;

  private Set<AuthorityInfo> authorities = new HashSet<AuthorityInfo>();

  public Set<AuthorityInfo> getAuthorities() {
    return authorities;
  }

  public void setAuthorities(Set<AuthorityInfo> authorities) {
    this.authorities = authorities;
  }

  public String getSmsCode() {
    return smsCode;
  }

  public void setSmsCode(String smsCode) {
    this.smsCode = smsCode;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }


  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }


  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }


  public java.sql.Timestamp getCreated() {
    return created;
  }

  public void setCreated(java.sql.Timestamp created) {
    this.created = created;
  }


  public java.sql.Timestamp getUpdated() {
    return updated;
  }

  public void setUpdated(java.sql.Timestamp updated) {
    this.updated = updated;
  }


  public String getSourceType() {
    return sourceType;
  }

  public void setSourceType(String sourceType) {
    this.sourceType = sourceType;
  }


  public String getNickName() {
    return nickName;
  }

  public void setNickName(String nickName) {
    this.nickName = nickName;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }


  public String getHeadPic() {
    return headPic;
  }

  public void setHeadPic(String headPic) {
    this.headPic = headPic;
  }


  public String getQq() {
    return qq;
  }

  public void setQq(String qq) {
    this.qq = qq;
  }


  public double getAccountBalance() {
    return accountBalance;
  }

  public void setAccountBalance(double accountBalance) {
    this.accountBalance = accountBalance;
  }


  public String getIsMobileCheck() {
    return isMobileCheck;
  }

  public void setIsMobileCheck(String isMobileCheck) {
    this.isMobileCheck = isMobileCheck;
  }


  public String getIsEmailCheck() {
    return isEmailCheck;
  }

  public void setIsEmailCheck(String isEmailCheck) {
    this.isEmailCheck = isEmailCheck;
  }


  public String getSex() {
    return sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }


  public long getUserLevel() {
    return userLevel;
  }

  public void setUserLevel(long userLevel) {
    this.userLevel = userLevel;
  }


  public long getPoints() {
    return points;
  }

  public void setPoints(long points) {
    this.points = points;
  }


  public long getExperienceValue() {
    return experienceValue;
  }

  public void setExperienceValue(long experienceValue) {
    this.experienceValue = experienceValue;
  }


  public java.sql.Timestamp getBirthday() {
    return birthday;
  }

  public void setBirthday(java.sql.Timestamp birthday) {
    this.birthday = birthday;
  }


  public java.sql.Timestamp getLastLoginTime() {
    return lastLoginTime;
  }

  public void setLastLoginTime(java.sql.Timestamp lastLoginTime) {
    this.lastLoginTime = lastLoginTime;
  }

}
