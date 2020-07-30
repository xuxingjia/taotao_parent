package com.study.demo.taotao_pojo.pojo;


import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class TbSeller implements Serializable {

    //用户ID
    @NotBlank(message = "用户ID不能为空")
    private String sellerId;
    //公司名
    @NotBlank(message = "公司名称不能为空")
    private String name;
    //店铺名称
    @NotBlank(message = "店铺名称不能为空")
    private String nickName;
    //密码
    @NotBlank(message = "密码不能为空")
    private String password;
    //邮箱
    @NotBlank(message = "邮箱不能为空")
    private String email;
    //公司手机
    private String mobile;
    //公司电话
    @NotBlank(message = "公司手机不能为空")
    private String telephone;
    //状态 1 待审 2审核失败 0审核通过 3关闭商店
    private String status;
    //详细地址
    @NotBlank(message = "详细地址不能为空")
    private String addressDetail;
    // 联系人姓名
    @NotBlank(message = "联系人姓名不能为空")
    private String linkmanName;
    //联系人QQ
    @NotBlank(message = "联系人QQ不能为空")
    private String linkmanQq;
    //联系人电话
    @NotBlank(message = "联系人电话不能为空")
    private String linkmanMobile;
    //联系人邮箱
    @NotBlank(message = "联系人邮箱不能为空")
    private String linkmanEmail;
    //营业执照号
    @NotBlank(message = "营业执照号不能为空")
    private String licenseNumber;
    //税务登记证号
    @NotBlank(message = "税务登记证号不能为空")
    private String taxNumber;
    //组织机构代码
    @NotBlank(message = "组织机构代码不能为空")
    private String orgNumber;
    //公司地址
    @NotBlank(message = "公司地址不能为空")
    private String address;
    //公司LOGO图
    private String logoPic;
    //简介
    @NotBlank(message = "简介不能为空")
    private String brief;
    //创建日期
    private java.sql.Timestamp createTime;
    //法定代表人
    @NotBlank(message = "法定代表人不能为空")
    private String legalPerson;
    //法定代表人身份证
    @NotBlank(message = "法定代表人身份证不能为空")
    private String legalPersonCardId;
    //开户行账号名称
    @NotBlank(message = "开户行账号名称不能为空")
    private String bankUser;
    //开户行
    @NotBlank(message = "开户行不能为空")
    private String bankName;


    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }


    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }


    public String getLinkmanName() {
        return linkmanName;
    }

    public void setLinkmanName(String linkmanName) {
        this.linkmanName = linkmanName;
    }


    public String getLinkmanQq() {
        return linkmanQq;
    }

    public void setLinkmanQq(String linkmanQq) {
        this.linkmanQq = linkmanQq;
    }


    public String getLinkmanMobile() {
        return linkmanMobile;
    }

    public void setLinkmanMobile(String linkmanMobile) {
        this.linkmanMobile = linkmanMobile;
    }


    public String getLinkmanEmail() {
        return linkmanEmail;
    }

    public void setLinkmanEmail(String linkmanEmail) {
        this.linkmanEmail = linkmanEmail;
    }


    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }


    public String getTaxNumber() {
        return taxNumber;
    }

    public void setTaxNumber(String taxNumber) {
        this.taxNumber = taxNumber;
    }


    public String getOrgNumber() {
        return orgNumber;
    }

    public void setOrgNumber(String orgNumber) {
        this.orgNumber = orgNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLogoPic() {
        return logoPic;
    }

    public void setLogoPic(String logoPic) {
        this.logoPic = logoPic;
    }


    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }


    public java.sql.Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(java.sql.Timestamp createTime) {
        this.createTime = createTime;
    }


    public String getLegalPerson() {
        return legalPerson;
    }

    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson;
    }


    public String getLegalPersonCardId() {
        return legalPersonCardId;
    }

    public void setLegalPersonCardId(String legalPersonCardId) {
        this.legalPersonCardId = legalPersonCardId;
    }


    public String getBankUser() {
        return bankUser;
    }

    public void setBankUser(String bankUser) {
        this.bankUser = bankUser;
    }


    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

}
