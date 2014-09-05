/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nms.ccweb.entity.vasgate;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entity class handle table of PRODUCTENTRY
 *
 * @author Nguyen Trong Cuong
 * @since 08/26/2014
 * @version 1.0
 */
@Entity
@Table(name = "PRODUCTENTRY")
@XmlRootElement
@NamedQueries(
        @NamedQuery(name="ProductEntry.findAll",
                query = "SELECT p FROM ProductEntry p")
)
public class ProductEntry implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRODUCTID")
    private Long productId;
    @Size(max = 30)
    @Column(name = "USERNAME")
    private String userName;
    @Column(name = "CREATEDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @Column(name = "MODIFIEDDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;
    @Column(name = "CATEGORYID")
    private Long categoryId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "ALIAS_")
    private String alias;
    // @Max(value=?)  @Min(value=?)
    /* if you know range of your decimal fields consider 
     * using these annotations to enforce field validation */
    @Column(name = "VERSION")
    private BigDecimal version;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "TITLE")
    private String title;
    @Size(max = 20)
    @Column(name = "PRODUCTTYPE")
    private String productType;
    @Size(max = 75)
    @Column(name = "SKU")
    private String sku;
    @Column(name = "PRICE")
    private BigDecimal price;
    @Column(name = "DISPLAY")
    private Short display;
    @Column(name = "BUYABLE")
    private Short buyable;
    @Column(name = "FEATURE")
    private Short feature;
    @Size(max = 4000)
    @Column(name = "DESCRIPTION")
    private String description;
    @Size(max = 4000)
    @Column(name = "CONTENT")
    private String content;
    @Column(name = "DISPLAYDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date displayDate;
    @Column(name = "EXPIRATIONDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expirationDate;
    @Column(name = "REVIEWDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date reviewDate;
    @Column(name = "TERMOFUSE")
    private Short termOfUse;
    @Column(name = "TERMPERIOD")
    private Integer termPeriod;
    @Size(max = 20)
    @Column(name = "TERMUNIT")
    private String termUnit;
    @Column(name = "SUBSCRIPTION")
    private boolean subscription;
    @Size(max = 20)
    @Column(name = "SUBSCRIPTIONUNIT")
    private String subscriptionUnit;
    @Column(name = "SUBSCRIPTIONPERIOD")
    private Integer subscriptionPeriod;
    @Column(name = "GRACEPERIOD")
    private Integer gracePeriod;
    @Size(max = 20)
    @Column(name = "GRACEUNIT")
    private String graceUnit;
    @Column(name = "STATUS")
    private Short status;
    @Column(name = "STATUSDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date statusdate;
    @Column(name = "MERCHANTID")
    private Long merchantid;
    @Size(max = 4000)
    @Column(name = "PROPERTIES")
    private String properties;

    public ProductEntry() {
    }

    public ProductEntry(Long productid) {
        this.productId = productid;
    }

    public ProductEntry(Long productid, String alias, String title) {
        this.productId = productid;
        this.alias = alias;
        this.title = title;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public BigDecimal getVersion() {
        return version;
    }

    public void setVersion(BigDecimal version) {
        this.version = version;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Short getDisplay() {
        return display;
    }

    public void setDisplay(Short display) {
        this.display = display;
    }

    public Short getBuyable() {
        return buyable;
    }

    public void setBuyable(Short buyable) {
        this.buyable = buyable;
    }

    public Short getFeature() {
        return feature;
    }

    public void setFeature(Short feature) {
        this.feature = feature;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDisplayDate() {
        return displayDate;
    }

    public void setDisplayDate(Date displayDate) {
        this.displayDate = displayDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Date getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
    }

    public Short getTermOfUse() {
        return termOfUse;
    }

    public void setTermOfUse(Short termOfUse) {
        this.termOfUse = termOfUse;
    }

    public Integer getTermPeriod() {
        return termPeriod;
    }

    public void setTermPeriod(Integer termPeriod) {
        this.termPeriod = termPeriod;
    }

    public String getTermUnit() {
        return termUnit;
    }

    public void setTermUnit(String termUnit) {
        this.termUnit = termUnit;
    }

    public boolean isSubscription() {
        return subscription;
    }

    public void setSubscription(boolean subscription) {
        this.subscription = subscription;
    }

    public String getSubscriptionUnit() {
        return subscriptionUnit;
    }

    public void setSubscriptionUnit(String subscriptionUnit) {
        this.subscriptionUnit = subscriptionUnit;
    }

    public Integer getSubscriptionPeriod() {
        return subscriptionPeriod;
    }

    public void setSubscriptionPeriod(Integer subscriptionPeriod) {
        this.subscriptionPeriod = subscriptionPeriod;
    }

    public Integer getGracePeriod() {
        return gracePeriod;
    }

    public void setGracePeriod(Integer gracePeriod) {
        this.gracePeriod = gracePeriod;
    }

    public String getGraceUnit() {
        return graceUnit;
    }

    public void setGraceUnit(String graceUnit) {
        this.graceUnit = graceUnit;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public Date getStatusdate() {
        return statusdate;
    }

    public void setStatusdate(Date statusdate) {
        this.statusdate = statusdate;
    }

    public Long getMerchantid() {
        return merchantid;
    }

    public void setMerchantid(Long merchantid) {
        this.merchantid = merchantid;
    }

    public String getProperties() {
        return properties;
    }

    public void setProperties(String properties) {
        this.properties = properties;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productId != null ? productId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductEntry)) {
            return false;
        }
        ProductEntry other = (ProductEntry) object;
        if ((this.productId == null && other.productId != null) || (this.productId != null && !this.productId.equals(other.productId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nms.ccweb.entity.vasgate.Productentry[ productid=" + productId + " ]";
    }

}
