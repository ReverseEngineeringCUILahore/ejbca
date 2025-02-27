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
package org.cesecore.certificates.certificatetransparency;


import java.util.List;

/**
 * SctData session
 *
 * @version $Id: SctDataSession.java 32462 2019-05-29 12:40:44Z jekaterina_b_helmes $
 */
public interface SctDataSession {

    List<SctData> findSctData(String fingerprint);
}
