/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nms.ccweb.ejb;

import com.nms.ccweb.entity.vasgate.AppDomain;
import com.nms.ccweb.entity.vasgate.AppDomain_;
import com.nms.ccweb.entity.vasgate.ProductEntry;
import com.nms.ccweb.entity.vasgate.SubscriberOrder;
import com.nms.ccweb.entity.vasgate.SubscriberOrder_;
import com.nms.ccweb.entity.vasgate.SubscriberProduct;
import com.nms.ccweb.search.criteria.SubscriberOrderSearchCriteria;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.sql.DataSource;

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
    private static final Logger _LOGGER = Logger.getLogger(VGSubscriberOrderBean.class.getName());

    @Resource(lookup = "jdbc/vasgate")
    private DataSource dataSource;

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
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        List<SubscriberOrder> resutl = new ArrayList<>();
        Format format = new SimpleDateFormat("dd/MM/yyyy");

        try {
            conn = dataSource.getConnection();
            StringBuilder sql = new StringBuilder("SELECT * FROM (SELECT a.*, ROWNUM rnum  FROM (SELECT ORDERID, AMOUNT, CAMPAIGNID, CAUSE, CHANNEL, CREATEDATE, CYCLEDATE, DESCRIPTION, DISCOUNT, ISDN, MERCHANTID, MODIFIEDDATE, OFFERPRICE, ORDERDATE, ORDERNO, ORDERTYPE, PRICE, QUANTITY, REASONID, SCORE, STATUS, SUBSCRIBERID, SUBSCRIBERTYPE, USERID, USERNAME, PRODUCTID, SUBPRODUCTID FROM SUBSCRIBERORDER WHERE ");

            if (criteria.getStartOrderDate() != null) {
                sql.append(" ORDERDATE >= TO_DATE('").append(format.format(criteria.getStartOrderDate())).append("', 'dd/MM/yyyy') AND");
            }

            if (criteria.getEndOrderDate() != null) {
                sql.append(" ORDERDATE <= TO_DATE('").append(format.format(criteria.getEndOrderDate())).append("', 'dd/MM/yyyy') AND");
            }

            if (criteria.getIsdn() != null && !criteria.getIsdn().trim().isEmpty()) {
                sql.append(" ISDN LIKE '").append(criteria.getIsdn()).append("%' AND");
            }

            if (criteria.getOrderType() != null && !criteria.getOrderType().trim().isEmpty()) {
                sql.append(" ORDERTYPE = '").append(criteria.getOrderType()).append("' AND");
            }

            if (criteria.getProductEntry() != null) {
                sql.append(" PRODUCTID = ").append(criteria.getProductEntry().getProductId()).append(" AND");
            }

            String query = sql.toString();

            if (query.endsWith(" AND")) {
                query = query.substring(0, query.length() - 4);
            }

            query += ") a WHERE ROWNUM <= " + (start + range) + ") WHERE rnum > " + start;

            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                SubscriberOrder subOrder = new SubscriberOrder();
                subOrder.setOrderId(rs.getLong("ORDERID"));
                subOrder.setAmount(rs.getBigDecimal("AMOUNT"));
                subOrder.setCampaignId(rs.getLong("CAMPAIGNID"));
                subOrder.setIsdn(rs.getString("ISDN"));
                subOrder.setOrderDate(rs.getDate("ORDERDATE"));
                subOrder.setStatus(rs.getShort("STATUS"));
                subOrder.setOrdertype(rs.getString("ORDERTYPE"));
                subOrder.setProductid(em.find(ProductEntry.class, rs.getLong("PRODUCTID")));

                resutl.add(subOrder);
            }
        } catch (Exception e) {
            _LOGGER.log(Level.SEVERE, "Error ", e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(VGSubscriberOrderBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(VGSubscriberOrderBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(VGSubscriberOrderBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return resutl;
    }

    /**
     * Count SubscriberOrder by criteria
     *
     * @param criteria criria get from search form
     * @return
     */
    public int countSubscriberOrders(SubscriberOrderSearchCriteria criteria) {

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        int resutl = 0;
        Format format = new SimpleDateFormat("dd/MM/yyyy");

        try {
            conn = dataSource.getConnection();
            StringBuilder sql = new StringBuilder("SELECT COUNT(ORDERID) COUNT FROM SUBSCRIBERORDER WHERE ");

            if (criteria.getStartOrderDate() != null) {
                sql.append(" ORDERDATE >= TO_DATE('").append(format.format(criteria.getStartOrderDate())).append("', 'dd/MM/yyyy') AND");
            }

            if (criteria.getEndOrderDate() != null) {
                sql.append(" ORDERDATE <= TO_DATE('").append(format.format(criteria.getEndOrderDate())).append("', 'dd/MM/yyyy') AND");
            }

            if (criteria.getIsdn() != null && !criteria.getIsdn().trim().isEmpty()) {
                sql.append(" ISDN LIKE '").append(criteria.getIsdn()).append("%' AND");
            }

            if (criteria.getOrderType() != null && !criteria.getOrderType().trim().isEmpty()) {
                sql.append(" ORDERTYPE = '").append(criteria.getOrderType()).append("' AND");
            }

            if (criteria.getProductEntry() != null) {
                sql.append(" PRODUCTID = ").append(criteria.getProductEntry().getProductId()).append(" AND");
            }

            String query = sql.toString();

            if (query.endsWith(" AND")) {
                query = query.substring(0, query.length() - 4);
            }
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            if (rs.next()) {
                resutl = rs.getInt("COUNT");
            }
        } catch (Exception e) {
            _LOGGER.log(Level.SEVERE, "Error ", e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(VGSubscriberOrderBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(VGSubscriberOrderBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(VGSubscriberOrderBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return resutl;

//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
//        Root<SubscriberOrder> root = cq.from(SubscriberOrder.class);
//        cq.select(cb.count(root));
//        
//        List<Predicate> predicates = buildPredicates(criteria, cb, root);
//
//        if (predicates != null && !predicates.isEmpty()) {
//            cq.where(predicates.toArray(new Predicate[]{}));
//        }
//        try {
//            TypedQuery<Long> q = em.createQuery(cq);
//            int value = q.getSingleResult().intValue();
//            return value;
//        } catch (Exception e) {
//            _LOGGER.log(Level.SEVERE, "Error when count", e);
//            return 0;
//        }    
    }

    /**
     * Get all available OrderType column value form SubscriberOrder table.
     *
     * @param type
     * @return list of distinct orderType values
     */
    public List<AppDomain> getDomains(String type) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<AppDomain> cq = cb.createQuery(AppDomain.class);
        Root<AppDomain> root = cq.from(AppDomain.class);
        cq.select(root);
        cq.where(cb.equal(root.get(AppDomain_.type), type));
        TypedQuery<AppDomain> q = em.createQuery(cq);
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

    public SubscriberProduct getSubProductByIsdnAndProduct(String isdn, ProductEntry product) {
        TypedQuery<SubscriberProduct> query = em.createQuery("SELECT sb FROM SubscriberProduct sb WHERE sb.isdn = :isdn AND sb.productEntry = :productEntry", SubscriberProduct.class);
        query.setParameter("isdn", isdn);
        query.setParameter("productEntry", product);
        query.setMaxResults(1);
        List<SubscriberProduct> subProducts = query.getResultList();
        if (subProducts != null && subProducts.size() > 0) {
            return subProducts.get(0);
        } else {
            return null;
        }
    }
}
