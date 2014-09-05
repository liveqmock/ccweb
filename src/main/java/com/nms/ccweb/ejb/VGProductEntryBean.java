/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nms.ccweb.ejb;

import com.nms.ccweb.entity.vasgate.ProductEntry;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * EJB for ProductEntry Entity.
 *
 * @author Nguyen Trong Cuong
 * @since 09/03/2014
 * @version 1.0
 */
@Stateless
public class VGProductEntryBean implements Serializable {

    private static final long serialVersionUID = -6763564419189041338L;
    @PersistenceContext(unitName = "vasgatePU")
    private EntityManager em;
    
    public List<ProductEntry> getAllProduct() {
        TypedQuery<ProductEntry> q = em.createNamedQuery("ProductEntry.findAll", ProductEntry.class);
        return q.getResultList();
    }
    
    public ProductEntry find(Long id) {
        return em.find(ProductEntry.class, id);
    }
}
