/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nms.ccweb.web.controller;

import com.crm.webservice.VgRequest;
import com.crm.webservice.VgResponse;
import com.crm.webservice.impl.Hiboy;
import com.crm.webservice.impl.VGServiceImplService;
import com.nms.ccweb.ejb.VGProductEntryBean;
import com.nms.ccweb.entity.vasgate.ProductEntry;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.primefaces.context.RequestContext;

/**
 *
 * @author cuongnt
 */
@Named("vGUnregisterProductController")
@ViewScoped
public class VGUnregisterProductController implements Serializable {

    private static final long serialVersionUID = 6842015493093963448L;
    private static final Logger LOGGER = Logger.getLogger(VGUnregisterProductController.class.getName());
    private static final ResourceBundle RB = ResourceBundle.getBundle("Message");
    private static final ResourceBundle CONFIG = ResourceBundle.getBundle("config");
    private static final String SERVICE_ADDRESS_PROP_NAME = "webservice.vasgate.serviceAddress.unregister";
    private static final String SERVICE_WSDL_LOCATION_PROP_NAME = "webservice.vasgate.wsdl";

    @EJB
    private VGProductEntryBean productService;

    private Hiboy vgService;

    @NotNull
    @Pattern(regexp = "84[1-9]{1}\\d{8,9}$", message = "{validation.phonenumber}")
    private String isdn;

    @NotNull
    private String product;
    private SelectItem[] productNames;

    public VGUnregisterProductController() {
    }

    @PostConstruct
    public void init() {
        URL wsdlUrl = null;
        VGServiceImplService service;
        try {
            wsdlUrl = new URL(CONFIG.getString(SERVICE_WSDL_LOCATION_PROP_NAME));
        } catch (MalformedURLException e) {
            LOGGER.log(Level.SEVERE, "webservice.vasgate.wsdl not correct in config file.", e);
        }

        if (wsdlUrl == null) {
            service = new VGServiceImplService();
        } else {
            service = new VGServiceImplService(wsdlUrl);
        }

        vgService = service.getVGServiceImplPort();
    }

    public void unregister() {
        FacesMessage message;
        FacesContext facesContext = FacesContext.getCurrentInstance();
        String serviceAddress = CONFIG.getString(SERVICE_ADDRESS_PROP_NAME);
        if (serviceAddress == null || serviceAddress.trim().isEmpty()) {
            serviceAddress = "123";
        }

        VgRequest request = new VgRequest();
        request.setIsdn(isdn);
        request.setContent(product);
        request.setServiceAddress(serviceAddress);

        try {
           
            VgResponse respose = vgService.sendOrder(request);
            
             LOGGER.log(Level.INFO, "Call VasgateWS, inputParam: isdn={0}, content={1}, "
                    + "serviceAddress={2}, username={3}, password={4}; response: reponseCode={5},"
                    + "cause={6}, parameter={7}, responseDesc={8} ", new Object[]{
                request.getIsdn(),
                request.getContent(),
                request.getServiceAddress(),
                request.getUsername(),
                request.getPassword(),
                respose.getResponseCode(),
                respose.getCause(),
                respose.getParameter(),
                respose.getResponseDesc()
            });

            int responseCode = respose.getResponseCode();

            if (responseCode == 0) {
                String successMessage = RB.getString("unregistry-success-message");
                message = new FacesMessage(FacesMessage.SEVERITY_INFO, successMessage, successMessage);
                // Reset form
                isdn = null;
                productNames = null;
            } else {
                String failMessage = RB.getString("unregistry-failt-message");
                LOGGER.log(Level.SEVERE, "VGWebservice ERROR CAUSE: {0}", respose.getCause());
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, failMessage, null);
            }

        } catch (com.crm.webservice.impl.Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, RB.getString("webservice-exception"),
                    RB.getString("webservice-error-solution"));
        }

        facesContext.addMessage(null, message);
    }

    public void processIsdnChange(ValueChangeEvent e) {
        String newValue = e.getNewValue().toString();

        try {
            List<ProductEntry> products = productService.getActiveProductByIsdn(newValue);

            if (products != null && !products.isEmpty()) {
                productNames = new SelectItem[products.size()];

                for (int i = 0; i < products.size(); i++) {
                    String productTitle = products.get(i).getTitle();
                    String productId = String.valueOf(products.get(i).getProductId());
                    productNames[i] = new SelectItem(productId, productTitle);
                }
            } else {
                productNames = new SelectItem[]{};
                String message = RB.getString("subscriber-have-not-active-product");
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, message, message);
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }

            RequestContext.getCurrentInstance().update("form");

        } catch (Exception ex) {
            Logger.getLogger(VGUnregisterProductController.class.getName()).log(Level.SEVERE, "Error when get product by isdn", ex);
        }

    }

    public String getIsdn() {
        return isdn;
    }

    public void setIsdn(String isdn) {
        this.isdn = isdn;
    }

    public SelectItem[] getProductNames() {
        return productNames;
    }

    public void setProductNames(SelectItem[] productNames) {
        this.productNames = productNames;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

}
