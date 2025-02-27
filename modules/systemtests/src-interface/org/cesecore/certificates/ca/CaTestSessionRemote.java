/*************************************************************************
 *                                                                       *
 *  CESeCore: CE Security Core                                           *
 *                                                                       *
 *  This software is free software; you can redistribute it and/or       *
 *  modify it under the terms of the GNU Lesser General Public           *
 *  License as published by the Free Software Foundation; either         *
 *  version 2.1 of the License, or any later version.                    *
 *                                                                       *
 *  See terms of license at gnu.org.                                     *
 *                                                                       *
 *************************************************************************/
package org.cesecore.certificates.ca;

import javax.ejb.Remote;

import org.cesecore.authentication.tokens.AuthenticationToken;
import org.cesecore.authorization.AuthorizationDeniedException;

/**
 * CRUD bean for creating, removing and retrieving CAs.
 * 
 * @version $Id: CaTestSessionRemote.java 31988 2019-03-27 10:04:22Z tarmo_r_helmes $
 */
@Remote
public interface CaTestSessionRemote {

    /** @see org.cesecore.certificates.ca.CaSessionLocal#getCA(AuthenticationToken, int) */
    public CACommon getCA(AuthenticationToken admin, int caid) throws AuthorizationDeniedException;
  
    /** @see org.cesecore.certificates.ca.CaSessionLocal#getCA(AuthenticationToken, String) */
    public CACommon getCA(AuthenticationToken admin, String name) throws AuthorizationDeniedException;
}
