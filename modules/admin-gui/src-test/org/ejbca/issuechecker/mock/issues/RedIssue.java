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

package org.ejbca.issuechecker.mock.issues;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.log4j.Level;
import org.ejbca.issuechecker.ConfigurationIssue;
import org.ejbca.issuechecker.Ticket;
import org.ejbca.issuechecker.TicketDescription;

/**
 * A red configuration issue used as a mock in unit testing. Creates a list of dummy tickets 
 * <code>{Red Ticket1, Red Ticket2... Red TicketN}</code> where <code>N</code> is specified 
 * when the mock is instantiated.
 * 
 * @version $Id: RedIssue.java 31731 2019-03-07 16:24:59Z bastianf $
 */
public class RedIssue extends ConfigurationIssue {
    private final int ticketCount;
    private final Level level;

    /**
     * Create a new red configuration issue for unit testing.
     * 
     * @param ticketCount the number of tickets to return when {@link #getTickets()} is invoked.
     * @param level the level of this issue.
     */
    public RedIssue(final int ticketCount, final Level level) {
        this.ticketCount = ticketCount;
        this.level = level;
    }

    @Override
    public List<Ticket> getTickets() {
        return IntStream.rangeClosed(1, ticketCount)
            .boxed()
            .map(i -> Ticket.builder(this, TicketDescription.fromStringLiteral("Red Ticket " + i)).build())
            .collect(Collectors.toList());
    }

    @Override
    public Level getLevel() {
        return level;
    }

    @Override
    public String getDescriptionLanguageKey() {
        return "RED_ISSUE_DESCRIPTION";
    }

    @Override
    public String getDatabaseValue() {
        return "RedIssue";
    }
}
