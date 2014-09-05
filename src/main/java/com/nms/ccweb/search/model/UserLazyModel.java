/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.nms.ccweb.search.model;

import com.nms.ccweb.ejb.UserBean;
import com.nms.ccweb.entity.security.User;
import java.util.List;
import java.util.Map;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;

/**
 *
 * @author Cuong
 */
public class UserLazyModel extends LazyDataModel<User> {

    private static final long serialVersionUID = -3167049915837590757L;
    private final UserBean userBean;

    public UserLazyModel(UserBean userBean) {
        this.userBean = userBean;
    }

    @Override
    public User getRowData(String rowKey) {
        return userBean.find(rowKey);
    }

    @Override
    public Object getRowKey(User object) {
        return object.getUserName();
    }

    @Override
    public List<User> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        int sort = 0;
        switch (sortOrder) {
            case ASCENDING: 
                sort = 1;
                break;
            case DESCENDING: 
                sort = 2;
                break;
            case UNSORTED:
                sort = 0;
                break;
        }
        
        // set count
        this.setRowCount(userBean.count(filters));
        
        return userBean.load(first, pageSize, sortField, sort, filters);
    }
}
