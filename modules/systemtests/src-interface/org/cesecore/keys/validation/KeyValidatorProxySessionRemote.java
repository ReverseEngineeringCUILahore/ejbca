/*************************************************************************
 *                                                                       *
 *  CESeCore: CE Security Core                                           *
 *                                                                       *
 *  This software is free software; you can redistribute it and/or       *
 *  modify it under the terms of the GNU Lesser General                  *
 *  License as published by the Free Software Foundation; either         *
 *  version 2.1 of the License, or any later version.                    *
 *                                                                       *
 *  See terms of license at gnu.org.                                     *
 *                                                                       *
 *************************************************************************/

package org.cesecore.keys.validation;

import javax.ejb.Remote;

import org.cesecore.authorization.AuthorizationDeniedException;

/**
 * @version $Id: KeyValidatorProxySessionRemote.java 28140 2018-01-30 12:40:30Z andresjakobs $
 *
 */
@Remote
public interface KeyValidatorProxySessionRemote extends KeyValidatorSessionLocal {

    /** Change a Validator without affecting the cache */
    void internalChangeValidatorNoFlushCache(Validator validator)
            throws AuthorizationDeniedException, KeyValidatorDoesntExistsException;
}
