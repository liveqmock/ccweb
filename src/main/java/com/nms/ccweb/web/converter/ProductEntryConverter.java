/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nms.ccweb.web.converter;

import com.nms.ccweb.ejb.VGProductEntryBean;
import com.nms.ccweb.entity.vasgate.ProductEntry;
import com.nms.ccweb.web.util.JsfUtil;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * ProductEntry Converter.
 *
 * @author Nguyen Trong Cuong
 * @since 08/27/2014
 * @version 1.0
 */
@FacesConverter(forClass = ProductEntry.class, value = "productEntryConverter")
public class ProductEntryConverter implements Converter {

    @EJB
    private VGProductEntryBean vgProductEntryBean;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.trim().isEmpty()) {
            return null;
        }
        
        if (JsfUtil.isDummySelectItem(component, value)) {
            return null;
        }
        
        return vgProductEntryBean.find(getKey(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {

        if (value == null) {
            return null;
        }

        if (value instanceof ProductEntry) {
            ProductEntry product = (ProductEntry) value;
            return getStringKey(product.getProductId());
        } else {
            throw new IllegalArgumentException("object " + value + " is of type " + value.getClass()
                    .getName() + "; expected type: " + ProductEntry.class.getName());
        }
    }

    private Long getKey(String value) {
        return Long.parseLong(value);
    }

    private String getStringKey(Long value) {
        StringBuilder cb = new StringBuilder();
        cb.append(value);
        return cb.toString();
    }
}
