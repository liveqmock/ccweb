/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nms.ccweb.entity.vasgate;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entity class handle table of SUBSCRIBERORDER
 *
 * @author Nguyen Trong Cuong
 * @since 08/26/2014
 * @version 1.0
 */
@Entity
@Table(name = "SUBSCRIBERORDER")
@XmlRootElement
public class SubscriberOrder implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ORDERID")
    private Long orderId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ORDERDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;
    @Column(name = "USERID")
    private Long userId;
    @Size(max = 60)
    @Column(name = "USERNAME")
    private String userName;
    @Column(name = "CREATEDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @Column(name = "MODIFIEDDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;
    @Column(name = "MERCHANTID")
    private Long merchantId;
    @Size(max = 60)
    @Column(name = "CHANNEL")
    private String channel;
    @Size(max = 60)
    @Column(name = "ORDERTYPE")
    private String orderType;
    @Size(max = 300)
    @Column(name = "ORDERNO")
    private String orderNo;
    @Column(name = "CYCLEDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cycleDate;
    @Column(name = "SUBSCRIBERID")
    private BigInteger subscriberId;
    @Column(name = "SUBPRODUCTID")
    private Long subproductId;
    @Column(name = "SUBSCRIBERTYPE")
    private Long subscriberType;
    @Size(max = 300)
    @Column(name = "ISDN")
    private String isdn;
//    @Column(name = "PRODUCTID")
//    private Long productId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PRODUCTID", insertable = false, updatable = false)
    private ProductEntry productEntry;
    @Column(name = "CAMPAIGNID")
    private Long campaignId;
    // @Max(value=?)  @Min(value=?)
    /* if you know range of your decimal fields consider using 
     * these annotations to enforce field validation */
    @Column(name = "OFFERPRICE")
    private BigDecimal offerPrice;
    @Column(name = "PRICE")
    private BigDecimal price;
    @Column(name = "QUANTITY")
    private Long quantity;
    @Column(name = "DISCOUNT")
    private BigDecimal discount;
    @Column(name = "AMOUNT")
    private BigDecimal amount;
    @Column(name = "SCORE")
    private BigDecimal score;
    @Column(name = "REASONID")
    private Short reasonId;
    @Column(name = "STATUS")
    private Short status;
    @Size(max = 300)
    @Column(name = "CAUSE")
    private String cause;
    @Size(max = 4000)
    @Column(name = "DESCRIPTION")
    private String description;

    public SubscriberOrder() {
    }

    public SubscriberOrder(Long orderid) {
        this.orderId = orderid;
    }

    public SubscriberOrder(Long orderid, Date orderdate) {
        this.orderId = orderid;
        this.orderDate = orderdate;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Long getUserid() {
        return userId;
    }

    public void setUserid(Long userid) {
        this.userId = userid;
    }

    public String getUsername() {
        return userName;
    }

    public void setUsername(String username) {
        this.userName = username;
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

    public Long getMerchantid() {
        return merchantId;
    }

    public void setMerchantid(Long merchantid) {
        this.merchantId = merchantid;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getOrdertype() {
        return orderType;
    }

    public void setOrdertype(String ordertype) {
        this.orderType = ordertype;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Date getCycleDate() {
        return cycleDate;
    }

    public void setCycleDate(Date cycleDate) {
        this.cycleDate = cycleDate;
    }

    public BigInteger getSubscriberId() {
        return subscriberId;
    }

    public void setSubscriberId(BigInteger subscriberId) {
        this.subscriberId = subscriberId;
    }

    public Long getSubproductId() {
        return subproductId;
    }

    public void setSubproductId(Long subproductId) {
        this.subproductId = subproductId;
    }

    public Long getSubscriberType() {
        return subscriberType;
    }

    public void setSubscriberType(Long subscriberType) {
        this.subscriberType = subscriberType;
    }

    public String getIsdn() {
        return isdn;
    }

    public void setIsdn(String isdn) {
        this.isdn = isdn;
    }

    public ProductEntry getProductEntry() {
        return productEntry;
    }

    public void setProductid(ProductEntry productEntry) {
        this.productEntry = productEntry;
    }

    public Long getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(Long campaignId) {
        this.campaignId = campaignId;
    }

    public BigDecimal getOfferPrice() {
        return offerPrice;
    }

    public void setOfferPrice(BigDecimal offerPrice) {
        this.offerPrice = offerPrice;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public Short getReasonId() {
        return reasonId;
    }

    public void setReasonId(Short reasonId) {
        this.reasonId = reasonId;
    }

    public Short getStatus() {
        return status;
    }
    
    public String getCause() {
        return cause;
    }
    
    public void setCause(String cause) {
        this.cause = cause;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orderId != null ? orderId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof SubscriberOrder)) {
            return false;
        }
        SubscriberOrder other = (SubscriberOrder) object;
        if (this.orderId == null) {
            return false;
        }
        return this.orderId.equals(other.orderId);
    }

    @Override
    public String toString() {
        return "com.nms.ccweb.entity.vasgate.Subscriberorder[ orderid=" + orderId + " ]";
    }

}
