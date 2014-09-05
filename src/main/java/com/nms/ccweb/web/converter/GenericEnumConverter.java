/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights reserved.
 */
package com.nms.ccweb.web.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

/**
 * Enum Converter.
 *
 * @author Nguyen Trong Cuong
 * @since 08/27/2014
 * @version 1.0
 */
@FacesConverter("enumConverter")
public class GenericEnumConverter implements Converter{
    public static final String ATTRIBUTE_ENUM_TYPE = "GenericEnumConverter.enumType";

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Class<Enum> enumType = (Class<Enum>) component.getAttributes().get(ATTRIBUTE_ENUM_TYPE);
        
        try {
            return Enum.valueOf(enumType, value);
        } catch (IllegalArgumentException e) {
            throw new ConverterException(new FacesMessage("Value is not enum of type" + enumType));
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value instanceof Enum) {
            component.getAttributes().put(ATTRIBUTE_ENUM_TYPE, value.getClass());
            return ((Enum<?>) value).name();
        } else {
            throw new ConverterException(new FacesMessage("Value is not of enum" + value));
        }
    }
    
}
