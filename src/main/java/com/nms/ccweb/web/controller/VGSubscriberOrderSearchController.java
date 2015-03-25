/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nms.ccweb.web.controller;

import com.nms.ccweb.ejb.VGProductEntryBean;
import com.nms.ccweb.ejb.VGSubscriberOrderBean;
import com.nms.ccweb.entity.vasgate.AppDomain;
import com.nms.ccweb.entity.vasgate.ProductEntry;
import com.nms.ccweb.entity.vasgate.SubscriberOrder;
import com.nms.ccweb.entity.vasgate.SubscriberProduct;
import com.nms.ccweb.search.criteria.SubscriberOrderSearchCriteria;
import com.nms.ccweb.search.model.SubscriberOrderLazyModel;
import com.nms.ccweb.web.util.MessageUtil;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import org.primefaces.model.LazyDataModel;

/**
 * Managed bean for Subscriber Order Search
 *
 * @author Nguyen Trong Cuong
 * @since 08/26/2014
 * @version 1.0
 */
@Named("vgSubOrderCtl")
@SessionScoped
public class VGSubscriberOrderSearchController implements Serializable {
    
    private static final long serialVersionUID = -9173554196441575400L;
    private static final Logger LOGGER = Logger.getLogger(VGSubscriberOrderSearchController.class.getName());
    private static final String ORDER_TYPE_NAME = "ACTION_TYPE";
    
    @EJB
    private VGSubscriberOrderBean vGSubscriberOrderBean;
    @EJB
    private VGProductEntryBean vGProductEntryBean;
    
    private SubscriberOrderSearchCriteria criteria;
    private LazyDataModel<SubscriberOrder> model;
    /* store select items for orderType select */
    private SelectItem[] orderTypeSelectItems;
    /* store select items for product select */
    private SelectItem[] productSelectItems;

    /* Default non-args contructor */
    public VGSubscriberOrderSearchController() {
    }

    /* Business actions  */
    public void search(ActionEvent e) {
        // validate
        Date startDate = criteria.getStartOrderDate();
        Date endDate = criteria.getEndOrderDate();
        
        if (!endDate.after(startDate)) {
            MessageUtil.addGlobalErrorMessage("end-date-must-after-start-date");
            return;
        }

        // calculate days between
        long daysBetween = (endDate.getTime() - startDate.getTime()) / (1000 * 60 * 60 * 24);
        if (daysBetween > 5) {
            MessageUtil.addGlobalErrorMessage("end-date-dont-greeter-than-start-date-5-days");
            return;
        }
        
        resetModel();
    }
    
    private void resetModel() {        
        model = null;
    }

    /* Getters and Setters */
    public SubscriberOrderSearchCriteria getCriteria() {
        if (criteria == null) {
            criteria = new SubscriberOrderSearchCriteria();
        }
        return criteria;
    }
    
    public void setCriteria(SubscriberOrderSearchCriteria criteria) {
        this.criteria = criteria;
    }

    /* Get or Prepare select item for SubscriberOrder's orderType */
    public SelectItem[] getOrderTypeSelectItems() {
        if (orderTypeSelectItems == null) {
            List<AppDomain> orderTypes = vGSubscriberOrderBean.getDomains(ORDER_TYPE_NAME);
            if (orderTypes != null && orderTypes.size() > 0) {
                orderTypeSelectItems = new SelectItem[orderTypes.size() + 1];
                /* Add empty select */
                orderTypeSelectItems[0] = new SelectItem(null, MessageUtil.getBundleMessage("empty-select-label"));
                /* Add others */
                int i = 1;
                for (AppDomain orderType : orderTypes) {
                    orderTypeSelectItems[i++] = new SelectItem(orderType.getAlias(), orderType.getTitle());
                }
            }
        }
        return orderTypeSelectItems;
    }

    /* Get or prepare select items for product select control. */
    public SelectItem[] getProductSelectItems() {
        if (productSelectItems == null) {
            List<ProductEntry> products = vGProductEntryBean.getAllProduct();
            int size = products.size() + 1; // addition 1 for empty select item.
            productSelectItems = new SelectItem[size];
            /* Add empty select item. */
            productSelectItems[0] = new SelectItem(null, MessageUtil.getBundleMessage("empty-select-label"));
            /* Add others */
            int i = 1;
            for (ProductEntry product : products) {
                productSelectItems[i++] = new SelectItem(product, product.getTitle());
            }
        }
        return productSelectItems;
    }
    
    public LazyDataModel<SubscriberOrder> getModel() {
        if (model == null) {
            model = new SubscriberOrderLazyModel(getCriteria(), vGSubscriberOrderBean);
        }
        return model;
    }
    
    public void setModel(LazyDataModel<SubscriberOrder> model) {
        this.model = model;
    }
    
    public SubscriberProduct getSubProduct(String isdn, ProductEntry productEntry) {
        SubscriberProduct subProduct = new SubscriberProduct();
        try {
            subProduct = vGSubscriberOrderBean.getSubProductByIsdnAndProduct(isdn, productEntry);
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "SubscriberProduct not found with isdn = {0}, productId = {}", 
                    new Object[]{isdn, productEntry.getProductId()});
        }
        return subProduct;
    }
}
