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
package org.ejbca.configdump;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.ejbca.configdump.ConfigDumpSetting.ItemKey;

/**
 * Holds information about the status of a Configdump import operation.
 * @version $Id: ConfigdumpImportResult.java 33274 2019-09-10 10:00:41Z samuellb $
 */
public final class ConfigdumpImportResult extends ConfigdumpResult {
    
    private static final long serialVersionUID = 1L;
    
    private final List<ItemKey> alreadyExistingItems;
    
    public ConfigdumpImportResult(final List<String> reportedErrors, final List<String> reportedWarnings, final Collection<ItemKey> alreadyExistingItems) {
        super(reportedErrors, reportedWarnings);
        this.alreadyExistingItems = Collections.unmodifiableList(new ArrayList<>(alreadyExistingItems));
    }
    
    public List<ItemKey> getAlreadyExistingItems() {
        return alreadyExistingItems;
    }
    
}
