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

package org.ejbca.issuechecker.mock.issueset;

import java.util.Set;

import org.ejbca.issuechecker.ConfigurationIssue;
import org.ejbca.issuechecker.ConfigurationIssueSet;
import org.ejbca.issuechecker.mock.issues.GreenIssue;

import com.google.common.collect.ImmutableSet;

/**
 * A configuration issue set used as a mock in unit testing.
 * 
 * <p>Contains a single {@link GreenIssue}.
 * 
 * @version $Id: GreenIssueSet.java 31731 2019-03-07 16:24:59Z bastianf $
 */
public class GreenIssueSet extends ConfigurationIssueSet {

    @Override
    public Set<Class<? extends ConfigurationIssue>> getConfigurationIssues() {
        return ImmutableSet.of(GreenIssue.class);
    }

    @Override
    public String getTitleLanguageString() {
        return "GREEN_ISSUE_SET_TITLE";
    }

    @Override
    public String getDescriptionLanguageString() {
        return "GREEN_ISSUE_SET_DESCRIPTION";
    }

    @Override
    public String getDatabaseValue() {
        return "GreenIssueSet";
    }
}
