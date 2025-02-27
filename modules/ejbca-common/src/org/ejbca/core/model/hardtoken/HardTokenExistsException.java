/*************************************************************************
 *                                                                       *
 *  EJBCA Community: The OpenSource Certificate Authority                *
 *                                                                       *
 *  This software is free software; you can redistribute it and/or       *
 *  modify it under the terms of the GNU Lesser General Public           *
 *  License as published by the Free Software Foundation; either         *
 *  version 2.1 of the License, or any later version.                    *
 *                                                                       *
 *  See terms of license at gnu.org.                                     *
 *                                                                       *
 *************************************************************************/
 
package org.ejbca.core.model.hardtoken;

import javax.xml.ws.WebFault;

/**
 * Deprecated class, kept for web service compatibility. It was used for Hard Tokens, which is a removed feature.
 * @deprecated Since EJBCA 7.1.0
 * @version $Id: HardTokenExistsException.java 32555 2019-06-18 14:36:39Z samuellb $
 */
@Deprecated
@WebFault
public class HardTokenExistsException extends Exception {
    
    private static final long serialVersionUID = -4147417555483748945L;

    /**
     * Deprecated exception. Do not use.
     * @deprecated Since EJBCA 7.1.0
     */
    @Deprecated
    public HardTokenExistsException() {
        super();
    }

    /**
     * Deprecated exception. Do not use.
     * @deprecated Since EJBCA 7.1.0
     */
    @Deprecated
    public HardTokenExistsException(final String msg) {
        super(msg);
    }
}
