/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nms.ccweb.entity.vasgate;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author cuongnt
 */
@Entity
@Table(name = "SUBSCRIBERPRODUCT")
@XmlRootElement
public class SubscriberProduct implements Serializable {

    private static final long serialVersionUID = 8198520677175514393L;

    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "SUBPRODUCTID")
    private Long subproductid;

    @Column(name = "USERID")
    private Long userid;

    @Size(max = 30)
    @Column(name = "USERNAME")
    private String username;

    @Column(name = "CREATEDATE")
    @Temporal(TemporalType.DATE)
    private Date createdate;

    @Column(name = "MODIFIEDDATE")
    @Temporal(TemporalType.DATE)
    private Date modifieddate;

    @Column(name = "MERCHANTID")
    private Long merchantid;

    @Column(name = "SUBSCRIBERID")
    private Long subscriberid;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PRODUCTID", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private ProductEntry productEntry;

    @Size(max = 75)
    @Column(name = "ISDN")
    private String isdn;

    @Column(name = "SUBSCRIBERTYPE")
    private Short subscribertype;

    @Column(name = "BARRINGSTATUS")
    private Integer barringstatus;

    @Column(name = "SUPPLIERSTATUS")
    private Integer supplierstatus;

    @Column(name = "TERMDATE")
    @Temporal(TemporalType.DATE)
    private Date termdate;

    @Column(name = "REGISTERDATE")
    @Temporal(TemporalType.DATE)
    private Date registerdate;

    @Column(name = "UNREGISTERDATE")
    @Temporal(TemporalType.DATE)
    private Date unregisterdate;

    @Column(name = "EXPIRATIONDATE")
    @Temporal(TemporalType.DATE)
    private Date expirationdate;

    @Column(name = "GRACEDATE")
    @Temporal(TemporalType.DATE)
    private Date gracedate;

    @Size(max = 75)
    @Column(name = "LANGUAGEID")
    private String languageid;

    @Size(max = 4000)
    @Column(name = "PROPERTIES")
    private String properties;

    @Size(max = 4000)
    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "LASTRUNDATE")
    @Temporal(TemporalType.DATE)
    private Date lastrundate;

    @Column(name = "CAMPAIGNID")
    private BigInteger campaignid;

    @Column(name = "SUBSCRIPTIONSTATUS")
    private Long subscriptionstatus;

    @Column(name = "SCORE")
    private Integer score;

    @Size(max = 20)
    @Column(name = "LASTQUESTIONID")
    private String lastquestionid;

    @Size(max = 4000)
    @Column(name = "QUESTIONLIST")
    private String questionlist;

    @Column(name = "NUMOFQUESTION")
    private Short numofquestion;

    @Column(name = "ISFIRSTTIME")
    private Short isfirsttime;

    @Column(name = "SENDSMSDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date sendsmsdate;

    @Size(max = 20)
    @Column(name = "BIRTHDAY")
    private String birthday;

    @Transient
    private Long telcoid;

    public SubscriberProduct() {
    }

    public SubscriberProduct(Long subproductid) {
        this.subproductid = subproductid;
    }

    public Long getSubproductid() {
        return subproductid;
    }

    public void setSubproductid(Long subproductid) {
        this.subproductid = subproductid;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public Date getModifieddate() {
        return modifieddate;
    }

    public void setModifieddate(Date modifieddate) {
        this.modifieddate = modifieddate;
    }

    public Long getMerchantid() {
        return merchantid;
    }

    public void setMerchantid(Long merchantid) {
        this.merchantid = merchantid;
    }

    public Long getSubscriberid() {
        return subscriberid;
    }

    public void setSubscriberid(Long subscriberid) {
        this.subscriberid = subscriberid;
    }

    public ProductEntry getProductEntry() {
        return productEntry;
    }

    public void setProductEntry(ProductEntry productEntry) {
        this.productEntry = productEntry;
    }

    public String getIsdn() {
        return isdn;
    }

    public void setIsdn(String isdn) {
        this.isdn = isdn;
    }

    public Short getSubscribertype() {
        return subscribertype;
    }

    public void setSubscribertype(Short subscribertype) {
        this.subscribertype = subscribertype;
    }

    public Integer getBarringstatus() {
        return barringstatus;
    }

    public void setBarringstatus(Integer barringstatus) {
        this.barringstatus = barringstatus;
    }

    public Integer getSupplierstatus() {
        return supplierstatus;
    }

    public void setSupplierstatus(Integer supplierstatus) {
        this.supplierstatus = supplierstatus;
    }

    public Date getTermdate() {
        return termdate;
    }

    public void setTermdate(Date termdate) {
        this.termdate = termdate;
    }

    public Date getRegisterdate() {
        return registerdate;
    }

    public void setRegisterdate(Date registerdate) {
        this.registerdate = registerdate;
    }

    public Date getUnregisterdate() {
        return unregisterdate;
    }

    public void setUnregisterdate(Date unregisterdate) {
        this.unregisterdate = unregisterdate;
    }

    public Date getExpirationdate() {
        return expirationdate;
    }

    public void setExpirationdate(Date expirationdate) {
        this.expirationdate = expirationdate;
    }

    public Date getGracedate() {
        return gracedate;
    }

    public void setGracedate(Date gracedate) {
        this.gracedate = gracedate;
    }

    public String getLanguageid() {
        return languageid;
    }

    public void setLanguageid(String languageid) {
        this.languageid = languageid;
    }

    public String getProperties() {
        return properties;
    }

    public void setProperties(String properties) {
        this.properties = properties;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getLastrundate() {
        return lastrundate;
    }

    public void setLastrundate(Date lastrundate) {
        this.lastrundate = lastrundate;
    }

    public BigInteger getCampaignid() {
        return campaignid;
    }

    public void setCampaignid(BigInteger campaignid) {
        this.campaignid = campaignid;
    }

    public Long getSubscriptionstatus() {
        return subscriptionstatus;
    }

    public void setSubscriptionstatus(Long subscriptionstatus) {
        this.subscriptionstatus = subscriptionstatus;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getLastquestionid() {
        return lastquestionid;
    }

    public void setLastquestionid(String lastquestionid) {
        this.lastquestionid = lastquestionid;
    }

    public String getQuestionlist() {
        return questionlist;
    }

    public void setQuestionlist(String questionlist) {
        this.questionlist = questionlist;
    }

    public Short getNumofquestion() {
        return numofquestion;
    }

    public void setNumofquestion(Short numofquestion) {
        this.numofquestion = numofquestion;
    }

    public Short getIsfirsttime() {
        return isfirsttime;
    }

    public void setIsfirsttime(Short isfirsttime) {
        this.isfirsttime = isfirsttime;
    }

    public Date getSendsmsdate() {
        return sendsmsdate;
    }

    public void setSendsmsdate(Date sendsmsdate) {
        this.sendsmsdate = sendsmsdate;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Long getTelcoid() {
        return telcoid;
    }

    public void setTelcoid(Long telcoid) {
        this.telcoid = telcoid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (subproductid != null ? subproductid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SubscriberProduct)) {
            return false;
        }
        SubscriberProduct other = (SubscriberProduct) object;
        return !((this.subproductid == null && other.subproductid != null) || (this.subproductid != null && !this.subproductid.equals(other.subproductid)));
    }

    @Override
    public String toString() {
        return "com.nms.ccweb.entity.vasgate.SubscriberProduct[ subproductid=" + subproductid + " ]";
    }

}
