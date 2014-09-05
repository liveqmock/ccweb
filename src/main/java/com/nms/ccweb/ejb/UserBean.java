/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nms.ccweb.ejb;

import com.nms.ccweb.entity.security.User;
import com.nms.ccweb.entity.security.User_;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.xml.bind.DatatypeConverter;

/**
 * EJB for User Entity.
 *
 * @author Nguyen Trong Cuong
 * @since 09/03/2014
 * @version 1.0
 */
@Stateless
public class UserBean implements Serializable {

    private static final long serialVersionUID = -3943780011160480024L;

    @PersistenceContext(unitName = "vasgatePU")
    private EntityManager em;

    public User find(String username) {
        return em.find(User.class, username);
    }

    public void create(User user) {
        if (find(user.getUserName()) != null) {
            throw new EJBException("username-existed");
        }
        user.setCreateDate(new Date());
        try {
            user.setPassword(encodeSHA256(user.getPassword()));
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE, "Error when encript user password", ex);
            throw new EJBException("Error when encript user password (SHA-256)", ex);
        }
        em.persist(user);
    }

    public void update(User user) {
        em.merge(user);
    }

    public void updatePassword(String userName, String newPassword) {
        try {
            User user = find(userName);
            user.setPassword(encodeSHA256(newPassword));
            em.merge(user);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE, "Error when encript user password", ex);
            throw new EJBException("Error when encript user password (SHA-256)", ex);
        }
    }

    public void remove(User user) {
        em.remove(em.merge(user));
    }

    @SuppressWarnings({"UseSpecificCatch", "BroadCatchBlock", "TooBroadCatch"})
    public User find(String userName, String password) {
        User user = null;
        if (userName != null && password != null) {
            try {
                password = encodeSHA256(password);

                CriteriaBuilder cb = em.getCriteriaBuilder();
                CriteriaQuery<User> cq = cb.createQuery(User.class);
                Root<User> root = cq.from(User.class);
                cq.select(root);

                cq.where(new Predicate[]{
                    cb.equal(root.get(User_.userName), userName),
                    cb.equal(root.get(User_.password), password)
                });

                TypedQuery<User> q = em.createQuery(cq);

                user = q.getSingleResult();
            } catch (Exception e) {
                Logger.getLogger(UserBean.class.getName()).log(Level.INFO,
                        "User not found with username = {0}, password = {1}, message = {3}",
                        new Object[]{userName, password, e.getMessage()});
            }

        }
        return user;
    }

    /**
     * Search User for primeraces datatables.
     *
     * @param start
     * @param range
     * @param sortField
     * @param sortOrder 0: not short, 1: asc, 2: desc
     * @param filters
     * @return
     */
    public List<User> load(int start, int range, String sortField, int sortOrder, Map<String, Object> filters) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> root = cq.from(User.class);
        cq.select(root);

        List<Predicate> predicates = new ArrayList<>();

        if (filters != null) {
            for (Map.Entry<String, Object> entry : filters.entrySet()) {
                String key = entry.getKey();
                if (entry.getValue() != null) {
                    switch (key) {
                        case "userName":
                            predicates.add(cb.like(cb.upper(root.get(User_.userName)), "%" + ((String) entry.getValue()).toUpperCase() + "%"));
                            break;
                        case "email":
                            predicates.add(cb.like(cb.upper(root.get(User_.email)), "%" + ((String) entry.getValue()).toUpperCase() + "%"));
                            break;
                        case "fullname":
                            predicates.add(cb.like(cb.upper(root.get(User_.fullname)), "%" + ((String) entry.getValue()).toUpperCase() + "%"));
                            break;
                        case "description":
                            predicates.add(cb.like(cb.upper(root.get(User_.description)), "%" + ((String) entry.getValue()).toUpperCase() + "%"));
                            break;
                        case "roles":
                            predicates.add(cb.equal(root.get(User_.roles), entry.getValue()));
                            break;
                    }
                }
            }
        }

        if (!predicates.isEmpty()) {
            cq.where(predicates.toArray(new Predicate[]{}));
        }

        // order
        if ((sortOrder == 1 || sortOrder == 2) && (sortField != null && !sortField.trim().isEmpty())) {
            Order order;
            if (sortOrder == 1) {
                order = cb.asc(root.get(sortField));
            } else {
                order = cb.desc(root.get(sortField));
            }

            cq.orderBy(order);
        }

        TypedQuery<User> q = em.createQuery(cq);

        q.setFirstResult(start);
        q.setMaxResults(range);

        return q.getResultList();
    }

    public int count(Map<String, Object> filters) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<User> root = cq.from(User.class);
        cq.select(cb.count(root));

        List<Predicate> predicates = new ArrayList<>();

        if (filters != null) {
            for (Map.Entry<String, Object> entry : filters.entrySet()) {
                String key = entry.getKey();
                if (entry.getValue() != null) {
                    switch (key) {
                        case "userName":
                            predicates.add(cb.like(cb.upper(root.get(User_.userName)), "%" + ((String) entry.getValue()).toUpperCase() + "%"));
                            break;
                        case "email":
                            predicates.add(cb.like(cb.upper(root.get(User_.email)), "%" + ((String) entry.getValue()).toUpperCase() + "%"));
                            break;
                        case "fullname":
                            predicates.add(cb.like(cb.upper(root.get(User_.fullname)), "%" + ((String) entry.getValue()).toUpperCase() + "%"));
                            break;
                        case "description":
                            predicates.add(cb.like(cb.upper(root.get(User_.description)), "%" + ((String) entry.getValue()).toUpperCase() + "%"));
                            break;
                        case "roles":
                            predicates.add(cb.equal(root.get(User_.roles), entry.getValue()));
                            break;
                    }
                }
            }
        }

        if (!predicates.isEmpty()) {
            cq.where(predicates.toArray(new Predicate[]{}));
        }

        TypedQuery<Long> q = em.createQuery(cq);

        return q.getSingleResult().intValue();
    }

    private String encodeSHA256(String input) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(input.getBytes("UTF-8"));
        byte[] digest = md.digest();
        return DatatypeConverter.printBase64Binary(digest);
    }
}
