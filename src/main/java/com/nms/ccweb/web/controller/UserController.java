/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nms.ccweb.web.controller;

import com.nms.ccweb.ejb.UserBean;
import com.nms.ccweb.entity.security.User;
import com.nms.ccweb.search.model.UserLazyModel;
import com.nms.ccweb.web.util.MessageUtil;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author Nguyen Trong Cuong
 * @since 08/27/2014
 * @version 1.0
 */
@Named("userCtl")
@SessionScoped
public class UserController implements Serializable {

    private static final long serialVersionUID = 4580173998553155189L;
    @EJB
    private UserBean userBean;
    private User current;
    private LazyDataModel<User> model;

    // for change password purposes
    private String newPassword;
    private String newPassword1;

    // store role select items.
    private SelectItem[] roleSelectItems;

    public UserController() {
    }

    public void prepareCreate(ActionEvent e) {
        current = null;
    }

    public void createUser(ActionEvent e) {
        try {
            userBean.create(current);
            MessageUtil.addGlobalInfoMessage("User.Create.SucccessMessage");
            resetModel();
        } catch (Exception ex) {
            MessageUtil.addGlobalErrorMessage(ex);
            FacesContext.getCurrentInstance().validationFailed();
        }
    }

    public void edit(ActionEvent e) {
        try {
            userBean.update(current);
            MessageUtil.addGlobalInfoMessage("User.Update.SuccessMessage");
            resetModel();
        } catch (Exception ex) {
            MessageUtil.addGlobalErrorMessage(ex);
            FacesContext.getCurrentInstance().validationFailed();
        }
    }

    public void changePassword(ActionEvent e) {
        // validate retype password.
        if (newPassword.compareTo(newPassword1) != 0) {
            MessageUtil.addGlobalErrorMessage("User.password.notmatchMessage");
            FacesContext.getCurrentInstance().validationFailed();
            return;
        }
        // change password
        try {
            userBean.updatePassword(current.getUserName(), newPassword);
            MessageUtil.addGlobalInfoMessage("User.ChangePassword.SuccessMessage");
        } catch (Exception ex) {
            MessageUtil.addGlobalErrorMessage(ex);
        } finally {
            resetChangePassword();
        }
    }

    public void deleteUser(User user) {
        // verify current user.
        // TODO: check if user is current user login, can't delete this user.
        // remove user.
        try {
            userBean.remove(user);
            MessageUtil.addGlobalInfoMessage("User.Remove.SuccessMessage");
            resetModel();
        } catch (Exception ex) {
            MessageUtil.addGlobalErrorMessage(ex);
        }
    }

    /**
     * Reset data model.
     */
    private void resetModel() {
        model = null;
    }

    /**
     * Reset change password form.
     *
     * @return
     */
    private void resetChangePassword() {
        newPassword = null;
        newPassword1 = null;
    }

    /* Getters and Setters */
    public User getCurrent() {
        if (current == null) {
            current = new User();
        }
        return current;
    }

    public void setCurrent(User current) {
        this.current = current;
    }

    public LazyDataModel<User> getModel() {
        if (model == null) {
            model = new UserLazyModel(userBean);
        }
        return model;
    }

    public void setModel(LazyDataModel<User> model) {
        this.model = model;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNewPassword1() {
        return newPassword1;
    }

    public void setNewPassword1(String newPassword1) {
        this.newPassword1 = newPassword1;
    }

    public SelectItem[] getRoleSelectItems() {
        if (roleSelectItems == null) {
            roleSelectItems = new SelectItem[User.UserRole.values().length];
            for (int i = 0; i < User.UserRole.values().length; i++) {
                roleSelectItems[i] = new SelectItem(User.UserRole.values()[i], MessageUtil.getBundleMessage(User.UserRole.values()[i].name()));
            }
        }
        return roleSelectItems;
    }
}
