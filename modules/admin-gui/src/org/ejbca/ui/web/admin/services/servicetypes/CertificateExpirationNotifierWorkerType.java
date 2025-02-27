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
package org.ejbca.ui.web.admin.services.servicetypes;

/**
 * Class managing the view of the Certificate Expiration Notifier Worker
 *
 * @version $Id: CertificateExpirationNotifierWorkerType.java 30542 2018-11-19 13:35:45Z aminkh $
 */
public class CertificateExpirationNotifierWorkerType extends BaseEmailNotifyingWorkerType {

	private static final long serialVersionUID = -3680823921964522760L;
    public static final String NAME = "CERTNOTIFICATIONWORKER";
		
	public CertificateExpirationNotifierWorkerType(){
		super(NAME, ServiceTypeUtil.CERTNOTIFICATIONWORKER_SUB_PAGE, org.ejbca.core.model.services.workers.CertificateExpirationNotifierWorker.class.getName());
		
	}
}
