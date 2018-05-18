package com.ws.framework.core.domain;
/**
 * Created by Administrator on 2017/8/14.
 */


import java.io.Serializable;
import java.util.Date;


/**
 * @author wangsi
 * @create 2017-08-14 17:09
 **/
public class Contact implements Serializable{

    private static final long serialVersionUID = -8434775887032963987L;
    /**
     * 客户Id
     */
    private Integer customerId;

    /**
     * 身份证号
     */
    private String idCard;

    /**
     * 证件类型
     */
    private String idType;

    /**
     * 姓名
     */
    private String custName;

    /**
     * 性别
     */
    private String sex;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 目前所在省份
     * @return
     */
    private String localState;

    /**
     * 目前所在城市
     * @return
     */
    private String localCity;

    /**
     * 目前所在区县
     * @return
     */
    private String localCityZone;

    /**
     * 目前所在街道
     * @return
     */
    private String localStreet;

    /**
     * 住宅地址
     * @return
     */
    private String homeAddress;

    /**
     * 出生日期
     */
    private Date birthDate ;

    /**
     * 来源渠道
     */
    private String channelCode ;

    /**
     * 户口所在省份
     */
    private String natureState ;

    /**
     * 户口所在城市
     */
    private String natureCity ;

    /**
     * 户口所在区县
     */
    private String natureCityzone ;

    /**
     * 户口所在街道
     */
    private String natureStreet ;

    /**
     * 法大大Id
     */
    private String fddId ;

    /**
     * 固定电话
     */
    private String homePhone ;

    /**
     * 民族
     */
    private String nation ;

    /**
     * 身份证有效期
     */
    private String idCardValidityTerm ;

    /**
     * 创建时间
     */
    private Date createDate ;
    
    /**
     * 更新时间
     */
    private Date lastUpdateTime ;

    /**
     * 户口所在地(身份证扫描地址)
     */
    private String natureFullAddress ;
    
    /**
     * 数据来源
     */
    private String updateSystem ;
    
    //婚姻状况 2:已婚, 3:	未婚,4:离异,5:丧偶,6:其他
    private String married;
    
    //子女数量
	private Integer childNum;
    
    //子女情况:1.参加工作,2.读书（高中及以上),3.读书（中专，初中及以下）,4.其他
	private String childrenInfo;

    //教育程度
	private String education;

    //住宅类型
	private String houseType;

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLocalState() {
        return localState;
    }

    public void setLocalState(String localState) {
        this.localState = localState;
    }

    public String getLocalCity() {
        return localCity;
    }

    public void setLocalCity(String localCity) {
        this.localCity = localCity;
    }

    public String getLocalCityZone() {
        return localCityZone;
    }

    public void setLocalCityZone(String localCityZone) {
        this.localCityZone = localCityZone;
    }

    public String getLocalStreet() {
        return localStreet;
    }

    public void setLocalStreet(String localStreet) {
        this.localStreet = localStreet;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    public String getNatureState() {
        return natureState;
    }

    public void setNatureState(String natureState) {
        this.natureState = natureState;
    }

    public String getNatureCity() {
        return natureCity;
    }

    public void setNatureCity(String natureCity) {
        this.natureCity = natureCity;
    }

    public String getNatureCityzone() {
        return natureCityzone;
    }

    public void setNatureCityzone(String natureCityzone) {
        this.natureCityzone = natureCityzone;
    }

    public String getNatureStreet() {
        return natureStreet;
    }

    public void setNatureStreet(String natureStreet) {
        this.natureStreet = natureStreet;
    }

    public String getFddId() {
        return fddId;
    }

    public void setFddId(String fddId) {
        this.fddId = fddId;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getIdCardValidityTerm() {
        return idCardValidityTerm;
    }

    public void setIdCardValidityTerm(String idCardValidityTerm) {
        this.idCardValidityTerm = idCardValidityTerm;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getNatureFullAddress() {
        return natureFullAddress;
    }

    public void setNatureFullAddress(String natureFullAddress) {
        this.natureFullAddress = natureFullAddress;
    }

    public String getUpdateSystem() {
        return updateSystem;
    }

    public void setUpdateSystem(String updateSystem) {
        this.updateSystem = updateSystem;
    }

    public String getMarried() {
        return married;
    }

    public void setMarried(String married) {
        this.married = married;
    }

    public Integer getChildNum() {
        return childNum;
    }

    public void setChildNum(Integer childNum) {
        this.childNum = childNum;
    }

    public String getChildrenInfo() {
        return childrenInfo;
    }

    public void setChildrenInfo(String childrenInfo) {
        this.childrenInfo = childrenInfo;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getHouseType() {
        return houseType;
    }

    public void setHouseType(String houseType) {
        this.houseType = houseType;
    }
}
