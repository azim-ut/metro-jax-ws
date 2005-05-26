/**
 * $Id: SOAP12XMLEncoder.java,v 1.4 2005-05-26 18:48:19 vivekp Exp $
 */

/*
 * Copyright 2005 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.sun.xml.ws.server;

import com.sun.pept.ept.MessageInfo;
import com.sun.xml.ws.encoding.soap.message.SOAPFaultInfo;
import com.sun.xml.ws.encoding.soap.streaming.SOAP12NamespaceConstants;
import com.sun.xml.ws.encoding.soap.streaming.SOAPNamespaceConstants;
import com.sun.xml.ws.encoding.JAXWSAttachmentMarshaller;
import com.sun.xml.ws.streaming.XMLWriter;
import com.sun.xml.ws.util.MessageInfoUtil;
import com.sun.xml.ws.client.BindingProviderProperties;
import com.sun.xml.bind.api.BridgeContext;

public class SOAP12XMLEncoder extends SOAPXMLEncoder {
    /*
     * @see com.sun.xml.rpc.rt.encoding.soap.SOAPEncoder#startEnvelope(com.sun.xml.rpc.streaming.XMLWriter)
     */
    @Override
    protected void startEnvelope(XMLWriter writer) {
        writer.startElement(SOAPNamespaceConstants.TAG_ENVELOPE, SOAP12NamespaceConstants.ENVELOPE,
                SOAPNamespaceConstants.NSPREFIX_SOAP_ENVELOPE);
    }

    /*
     * @see com.sun.xml.rpc.rt.encoding.soap.SOAPEncoder#startBody(com.sun.xml.rpc.streaming.XMLWriter)
     */
    @Override
    protected void startBody(XMLWriter writer) {
        writer.startElement(SOAPNamespaceConstants.TAG_BODY, SOAP12NamespaceConstants.ENVELOPE,
                SOAPNamespaceConstants.NSPREFIX_SOAP_ENVELOPE);
    }

    /*
     * @see com.sun.xml.rpc.rt.encoding.soap.SOAPEncoder#startHeader(com.sun.xml.rpc.streaming.XMLWriter)
     */
    @Override
    protected void startHeader(XMLWriter writer) {
        writer.startElement(SOAPNamespaceConstants.TAG_HEADER,
                SOAP12NamespaceConstants.ENVELOPE,
                SOAPNamespaceConstants.NSPREFIX_SOAP_ENVELOPE); // <env:Header>
    }

    /* (non-Javadoc)
     * @see com.sun.xml.rpc.rt.server.SOAPXMLEncoder#writeFault(com.sun.xml.rpc.soap.message.SOAPFaultInfo, com.sun.pept.ept.MessageInfo, com.sun.xml.rpc.streaming.XMLWriter)
     */
    @Override
    protected void writeFault(SOAPFaultInfo instance, MessageInfo messageInfo, XMLWriter writer) {
        throw new UnsupportedOperationException();
    }

    /* (non-Javadoc)
     * @see com.sun.xml.rpc.rt.server.SOAPXMLEncoder#getContentType()
     */
    @Override
    protected String getContentType(MessageInfo messageInfo) {
        Object rtc = messageInfo.getMetaData(BindingProviderProperties.JAXWS_RUNTIME_CONTEXT);
        if(rtc != null){
            BridgeContext bc = ((RuntimeContext)rtc).getBridgeContext();
            if(bc != null){
                JAXWSAttachmentMarshaller am = (JAXWSAttachmentMarshaller)bc.getAttachmentMarshaller();
                if(am.isXopped())
                    return "application/xop+xml;type=\"application/soap+xml\"";
                }
        }
        return "application/soap+xml";                
    }


}
