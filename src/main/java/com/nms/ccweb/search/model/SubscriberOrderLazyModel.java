/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nms.ccweb.search.model;

import com.nms.ccweb.ejb.VGSubscriberOrderBean;
import com.nms.ccweb.entity.vasgate.SubscriberOrder;
import com.nms.ccweb.search.criteria.SubscriberOrderSearchCriteria;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 *
 * @author Nguyen Trong Cuong
 * @since 08/27/2014
 * @version 1.0
 */
public class SubscriberOrderLazyModel extends LazyDataModel<SubscriberOrder> {

    private static final long serialVersionUID = 9130634324826539082L;
    private static final Logger LOGGER = Logger.getLogger(SubscriberOrderLazyModel.class.getName());
    private final VGSubscriberOrderBean vGSubscriberOrderBean;
    private SubscriberOrderSearchCriteria criteria;

    public SubscriberOrderLazyModel(SubscriberOrderSearchCriteria criteria, 
            VGSubscriberOrderBean vGSubscriberOrderBean) {
        this.criteria = criteria;
        this.vGSubscriberOrderBean = vGSubscriberOrderBean;
    }

    @Override
    public SubscriberOrder getRowData(String rowKey) {
        try {
            Long orderId = Long.parseLong(rowKey);
            return vGSubscriberOrderBean.find(orderId);
        } catch (NumberFormatException e) {
            LOGGER.log(Level.WARNING, "[SubscriberOrderLazyModel] getRowData(String rowKey): Can't "
                    + "not get SubscriberOrder from rowKey ({0}), error message: {1}",
                    new Object[]{rowKey, e.getMessage()});
            return null;
        }
    }

    @Override
    public Object getRowKey(SubscriberOrder object) {
        return object.getOrderId();
    }

    @Override
    public List<SubscriberOrder> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        boolean isAsc = false;
        switch (sortOrder) {
            case ASCENDING:
                isAsc = true;
                break;
            case DESCENDING:
                break;
            case UNSORTED:
                sortField = null;
                break;
        }
        
        // setCount
        this.setRowCount(vGSubscriberOrderBean.countSubscriberOrders(criteria));
        
        return vGSubscriberOrderBean.searchSubscriberOrder(getCriteria(), first, pageSize, sortField, isAsc);
    }

    public SubscriberOrderSearchCriteria getCriteria() {
        return criteria;
    }

    public void setCriteria(SubscriberOrderSearchCriteria criteria) {
        this.criteria = criteria;
    }
}
