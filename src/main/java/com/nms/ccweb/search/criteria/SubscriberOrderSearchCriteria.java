/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nms.ccweb.search.criteria;

import com.nms.ccweb.entity.vasgate.ProductEntry;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 * Search criteria for Subscriber Order search.
 *
 * @author Nguyen Trong Cuong
 * @since 08/26/2014
 * @version 1.0
 */
public class SubscriberOrderSearchCriteria implements Serializable {

    private static final long serialVersionUID = 8533205233182643521L;

    private String isdn;
    private Date startOrderDate;
    private Date endOrderDate;
    private Short status;
    private Collection<ProductEntry> products;
    private Collection<String> orderTypes;
    private String orderType;
    private ProductEntry productEntry;

    public SubscriberOrderSearchCriteria() {
    }

    public String getIsdn() {
        return isdn;
    }

    public void setIsdn(String isdn) {
        this.isdn = isdn;
    }

    public Date getStartOrderDate() {
        return startOrderDate;
    }

    public void setStartOrderDate(Date startOrderDate) {
        this.startOrderDate = startOrderDate;
    }

    public Date getEndOrderDate() {
        return endOrderDate;
    }

    public void setEndOrderDate(Date endOrderDate) {
        this.endOrderDate = endOrderDate;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public Collection<ProductEntry> getProducts() {
        return products;
    }

    public void setProducts(Collection<ProductEntry> products) {
        this.products = products;
    }

    public Collection<String> getOrderTypes() {
        return orderTypes;
    }

    public void setOrderTypes(Collection<String> orderTypes) {
        this.orderTypes = orderTypes;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public ProductEntry getProductEntry() {
        return productEntry;
    }

    public void setProductEntry(ProductEntry productEntry) {
        this.productEntry = productEntry;
    }
}
