/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nms.ccweb.web.controller;

import com.nms.ccweb.web.util.MessageUtil;
import java.io.Serializable;
import java.util.Calendar;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 * Managed all main navigations of application
 *
 * @author Nguyen Trong Cuong
 * @since 08/27/2014
 * @version 1.0
 */
@Named("appCtl")
@SessionScoped
public class ApplicationController implements Serializable {

    private static final long serialVersionUID = -8232804451655130032L;
    
    private String currentYear;

    public ApplicationController() {
    }
    
    public String getCurrentYear() {
        if (currentYear == null) {
            currentYear =String.format("%s", Calendar.getInstance().get(Calendar.YEAR));
        }
        return currentYear;
    }

}
