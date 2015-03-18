/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nms.ccweb.entity.security;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entity class handle table of CCS_USER
 *
 * @author Nguyen Trong Cuong
 * @since 09/03/2014
 * @version 1.0
 */
@Entity
@Table(name = "CCS_USER")
@XmlRootElement
public class User implements Serializable {

    private static final long serialVersionUID = 7197087364711440351L;

    public enum UserRole {

        Administrator;
    }

    @Id
    @Column(name = "USERNAME")
    private String userName;

    @NotNull
    @Column(name = "PASSWORD")
    private String password;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATEDATE")
    private Date createDate;

    @Size(max = 100, message = "user.fullname.maxlength")
    @Column(name = "FULLNAME")
    private String fullname;

    @Size(max = 150, message = "user.email.maxlength")
    @Column(name = "EMAIL")
    private String email;

    @Size(max = 250, message = "user.description.maxlength")
    @Column(name = "DESCRIPTION")
    private String description;

    @ElementCollection(targetClass = UserRole.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "CCS_USER_GROUP", joinColumns = {
        @JoinColumn(name = "USERNAME", referencedColumnName = "USERNAME")})
    @Column(name = "GROUPNAME")
    private List<UserRole> roles;

    public User() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(List<UserRole> roles) {
        this.roles = roles;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
