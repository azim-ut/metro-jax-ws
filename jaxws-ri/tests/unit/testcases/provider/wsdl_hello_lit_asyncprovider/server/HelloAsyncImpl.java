/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2004-2017 Oracle and/or its affiliates. All rights reserved.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License.  You can
 * obtain a copy of the License at
 * https://oss.oracle.com/licenses/CDDL+GPL-1.1
 * or LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 *
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at LICENSE.txt.
 *
 * GPL Classpath Exception:
 * Oracle designates this particular file as subject to the "Classpath"
 * exception as provided by Oracle in the GPL Version 2 section of the License
 * file that accompanied this code.
 *
 * Modifications:
 * If applicable, add the following below the License Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyright [year] [name of copyright owner]"
 *
 * Contributor(s):
 * If you wish your version of this file to be governed by only the CDDL or
 * only the GPL Version 2, indicate your decision by adding "[Contributor]
 * elects to include this software in this distribution under the [CDDL or GPL
 * Version 2] license."  If you don't indicate a single choice of license, a
 * recipient has the option to distribute your version of this file under
 * either the CDDL, the GPL Version 2 or to extend the choice of license to
 * its licensees as provided above.  However, if you add GPL Version 2 code
 * and therefore, elected the GPL Version 2 license, then the option applies
 * only if the new code is made subject to such option by the copyright
 * holder.
 */

package provider.wsdl_hello_lit_asyncprovider.server;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.ws.WebServiceException;
import com.sun.xml.ws.api.server.AsyncProvider;
import com.sun.xml.ws.api.server.AsyncProviderCallback;
import javax.xml.ws.Service;
import javax.xml.ws.Service.Mode;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.ws.WebServiceProvider;
import javax.xml.ws.WebServiceContext;

/**
 * @author Jitendra Kotamraju
 */

@WebServiceProvider(
    wsdlLocation="WEB-INF/wsdl/hello_literal.wsdl",
    targetNamespace="urn:test",
    serviceName="Hello",
    portName="HelloAsyncPort")

public class HelloAsyncImpl extends AbstractImpl implements AsyncProvider<Source> {

    public void invoke(Source source, AsyncProviderCallback cbak, WebServiceContext ctxt) {
        try {
            Hello_Type hello = recvBean(source);
            if (!hello.getExtra().startsWith("extra")) {
                cbak.send(new WebServiceException("Expected=extra*,got="+hello.getExtra()));
                return;
            }
            if (hello.getArgument().equals("fault")) {
                cbak.send(sendFaultSource());
                return;
            }
            cbak.send((++combo%2 == 0) ? sendSource(hello) : sendBean(hello));
        } catch(Exception e) {
            throw new WebServiceException("Endpoint failed", e);
        }
    }

}
