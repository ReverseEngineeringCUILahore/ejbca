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

package org.ejbca.core.protocol.acme;

import javax.ejb.Remote;

/**
 * Remote interface for AcmeOrderDataSession
 * 
 * @version $Id: AcmeOrderDataSessionRemote.java 29917 2018-09-28 05:07:46Z jekaterina_b_helmes $
 *
 */

@Remote
public interface AcmeOrderDataSessionRemote extends AcmeOrderDataSession {
   
}
