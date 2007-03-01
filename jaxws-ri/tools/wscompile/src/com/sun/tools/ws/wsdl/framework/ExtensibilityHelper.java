/*
 * The contents of this file are subject to the terms
 * of the Common Development and Distribution License
 * (the License).  You may not use this file except in
 * compliance with the License.
 * 
 * You can obtain a copy of the license at
 * https://glassfish.dev.java.net/public/CDDLv1.0.html.
 * See the License for the specific language governing
 * permissions and limitations under the License.
 * 
 * When distributing Covered Code, include this CDDL
 * Header Notice in each file and include the License file
 * at https://glassfish.dev.java.net/public/CDDLv1.0.html.
 * If applicable, add the following below the CDDL Header,
 * with the fields enclosed by brackets [] replaced by
 * you own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 * 
 * Copyright 2006 Sun Microsystems Inc. All Rights Reserved
 */

package com.sun.tools.ws.wsdl.framework;

import com.sun.tools.ws.api.wsdl.TWSDLExtension;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * A helper class for extensible entities.
 *
 * @author WS Development Team
 */
public class ExtensibilityHelper {

    public ExtensibilityHelper() {
    }

    public void addExtension(TWSDLExtension e) {
        if (_extensions == null) {
            _extensions = new ArrayList();
        }
        _extensions.add(e);
    }

    public Iterable<TWSDLExtension> extensions() {
        if (_extensions == null) {
            return new ArrayList<TWSDLExtension>();
        } else {
            return _extensions;
        }
    }

    public void withAllSubEntitiesDo(EntityAction action) {
//        if (_extensions != null) {
//            for (Iterator iter = _extensions.iterator(); iter.hasNext();) {
//                action.perform((Entity) iter.next());
//            }
//        }
    }

    public void accept(ExtensionVisitor visitor) throws Exception {
        if (_extensions != null) {
            for (Iterator iter = _extensions.iterator(); iter.hasNext();) {
                ((ExtensionImpl) iter.next()).accept(visitor);
            }
        }
    }

    private List<TWSDLExtension> _extensions;
}
