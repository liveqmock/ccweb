
package com.crm.webservice;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.crm.webservice package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Exception_QNAME = new QName("http://webservice.crm.com/", "Exception");
    private final static QName _SendOrderResponse_QNAME = new QName("http://webservice.crm.com/", "sendOrderResponse");
    private final static QName _SendOrder_QNAME = new QName("http://webservice.crm.com/", "sendOrder");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.crm.webservice
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Exception }
     * 
     */
    public Exception createException() {
        return new Exception();
    }

    /**
     * Create an instance of {@link SendOrderResponse }
     * 
     */
    public SendOrderResponse createSendOrderResponse() {
        return new SendOrderResponse();
    }

    /**
     * Create an instance of {@link SendOrder }
     * 
     */
    public SendOrder createSendOrder() {
        return new SendOrder();
    }

    /**
     * Create an instance of {@link VgRequest }
     * 
     */
    public VgRequest createVgRequest() {
        return new VgRequest();
    }

    /**
     * Create an instance of {@link VgResponse }
     * 
     */
    public VgResponse createVgResponse() {
        return new VgResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Exception }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.crm.com/", name = "Exception")
    public JAXBElement<Exception> createException(Exception value) {
        return new JAXBElement<Exception>(_Exception_QNAME, Exception.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendOrderResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.crm.com/", name = "sendOrderResponse")
    public JAXBElement<SendOrderResponse> createSendOrderResponse(SendOrderResponse value) {
        return new JAXBElement<SendOrderResponse>(_SendOrderResponse_QNAME, SendOrderResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendOrder }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.crm.com/", name = "sendOrder")
    public JAXBElement<SendOrder> createSendOrder(SendOrder value) {
        return new JAXBElement<SendOrder>(_SendOrder_QNAME, SendOrder.class, null, value);
    }

}
