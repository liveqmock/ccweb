/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nms.ccweb.ejb;

import com.nms.ccweb.entity.vasgate.SubscriberOrder;
import com.nms.ccweb.entity.vasgate.SubscriberOrder_;
import com.nms.ccweb.search.criteria.SubscriberOrderSearchCriteria;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * EJB for SubscriberOrder Entity.
 *
 * @author Nguyen Trong Cuong
 * @since 09/03/2014
 * @version 1.0
 */
@Stateless
public class VGSubscriberOrderBean implements Serializable {

    private static final long serialVersionUID = 2338076935307329551L;

    @PersistenceContext(unitName = "vasgatePU")
    private EntityManager em;

    public SubscriberOrder find(Long orderId) {
        return em.find(SubscriberOrder.class, orderId);
    }

    /**
     * Search SubscriberOrder by criteria
     *
     * @param criteria criria get form search form
     * @param start index of start row
     * @param range max number of row returned
     * @param sortField name of field to sorting.
     * @param isAsc is Ascending
     * @return list of SubscriberOrder
     */
    public List<SubscriberOrder> searchSubscriberOrder(SubscriberOrderSearchCriteria criteria,
            int start, int range, String sortField, boolean isAsc) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<SubscriberOrder> cq = cb.createQuery(SubscriberOrder.class);
        Root<SubscriberOrder> root = cq.from(SubscriberOrder.class);
        cq.select(root);

        List<Predicate> predicates = buildPredicates(criteria, cb, root);

        if (predicates != null && !predicates.isEmpty()) {
            cq.where(predicates.toArray(new Predicate[]{}));
        }

        if (sortField != null && !sortField.trim().isEmpty()) {
            Order order = isAsc ? cb.asc(root.get(sortField)) : cb.desc(root.get(sortField));
            cq.orderBy(order);
        } else {
            cq.orderBy(cb.desc(root.get(SubscriberOrder_.orderDate)));
        }

        TypedQuery<SubscriberOrder> q = em.createQuery(cq);

        if (start >= 0 && range >= 0) {
            q.setFirstResult(start);
            q.setMaxResults(range);
        }

        return q.getResultList();
    }
    
    /**
     * Count SubscriberOrder by criteria
     * @param criteria criria get from search form
     * @return 
     */
    public int countSubscriberOrders(SubscriberOrderSearchCriteria criteria) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<SubscriberOrder> root = cq.from(SubscriberOrder.class);
        cq.select(cb.count(root));
        
        List<Predicate> predicates = buildPredicates(criteria, cb, root);

        if (predicates != null && !predicates.isEmpty()) {
            cq.where(predicates.toArray(new Predicate[]{}));
        }
        
        TypedQuery<Long> q = em.createQuery(cq);
        return q.getSingleResult().intValue();
    }

    /**
     * Get all available OrderType column value form SubscriberOrder table.
     *
     * @return list of distinct orderType values
     */
    public List<String> getAllAvailOrderTypes() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<String> cq = cb.createQuery(String.class);
        Root<SubscriberOrder> root = cq.from(SubscriberOrder.class);
        cq.select(root.get(SubscriberOrder_.orderType)).distinct(true);
        TypedQuery<String> q = em.createQuery(cq);
        return q.getResultList();
    }

    private List<Predicate> buildPredicates(SubscriberOrderSearchCriteria criteria,
            CriteriaBuilder cb, Root<SubscriberOrder> root) {
        List<Predicate> predicates = new ArrayList<>();

        if (criteria.getIsdn() != null && !criteria.getIsdn().trim().isEmpty()) {
            predicates.add(cb.like(cb.upper(root.get(SubscriberOrder_.isdn)), "%" + criteria.getIsdn().trim() + "%"));
        }

        if (criteria.getStartOrderDate() != null) {
            predicates.add(cb.greaterThanOrEqualTo(root.get(SubscriberOrder_.orderDate), criteria.getStartOrderDate()));
        }

        if (criteria.getEndOrderDate() != null) {
            predicates.add(cb.lessThanOrEqualTo(root.get(SubscriberOrder_.orderDate), criteria.getEndOrderDate()));
        }

        if (criteria.getProducts() != null && !criteria.getProducts().isEmpty()) {
            predicates.add(root.get(SubscriberOrder_.productEntry).in(criteria.getProducts()));
        }

        if (criteria.getStatus() != null) {
            predicates.add(cb.equal(root.get(SubscriberOrder_.amount), criteria.getStatus()));
        }

        if (criteria.getOrderTypes() != null && !criteria.getOrderTypes().isEmpty()) {
            predicates.add(root.get(SubscriberOrder_.orderType).in(criteria.getOrderTypes()));
        }

        if (criteria.getProductEntry() != null) {
            predicates.add(cb.equal(root.get(SubscriberOrder_.productEntry), criteria.getProductEntry()));
        }

        if (criteria.getOrderType() != null && !criteria.getOrderType().trim().isEmpty()) {
            predicates.add(cb.equal(root.get(SubscriberOrder_.orderType), criteria.getOrderType().trim()));
        }

        return predicates;
    }
}
