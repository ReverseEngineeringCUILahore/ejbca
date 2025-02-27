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

import java.io.Serializable;

import org.cesecore.authentication.tokens.AuthenticationToken;
import org.cesecore.certificates.certificatetransparency.CTAuditLogCallback;
import org.cesecore.certificates.certificatetransparency.CTSubmissionConfigParams;
import org.cesecore.certificates.certificatetransparency.SctDataCallback;
import org.cesecore.keys.validation.CertificateValidationDomainService;

/**
 * Contains parameters and callbacks which is needed during certificate
 * generation in X509CA, e.g. by the CT extension. This can be used to access
 * session beans from this class, for instance the global configuration
 * or audit logging.
 * 
 * @note Since instances of this class may reference session beans, you must ensure
 * that instances of this interface are only used temporarily, e.g. as
 * functions arguments, and never as e.g. instance variables of non-temporary
 * classes.
 * 
 * @note Since it might not be possible to obtain the parameters, all methods that
 * accept objects of this class should also accept a null value, or null values
 * inside the CertificateGenerationParams object.
 * 
 * @see CTAuditLogCallback
 * 
 * @version $Id: CertificateGenerationParams.java 32432 2019-05-28 07:36:30Z jekaterina_b_helmes $
 */
public final class CertificateGenerationParams implements Serializable {

    private static final long serialVersionUID = 1L;

    private CTSubmissionConfigParams ctSubmissionConfigParams;
    private CTAuditLogCallback ctAuditLogCallback;
    private SctDataCallback sctDataCallback;
    
    private AuthenticationToken authenticationToken;
    private CertificateValidationDomainService certificateValidationDomainService;
    
    /**
     * Sets CT parameters that are not specific to the certificate profile, for example list of available CT logs.
     */
    public void setCTSubmissionConfigParams(final CTSubmissionConfigParams ctSubmissionConfigParams) {
        this.ctSubmissionConfigParams = ctSubmissionConfigParams;
    }

    /**
     * Set the a callback to be called after CT log submission.
     * This method is called automatically from CertificateCreateSession when generating a certificate.
     */
    public void setCTAuditLogCallback(CTAuditLogCallback ctAuditLogCallback) {
        this.ctAuditLogCallback = ctAuditLogCallback;
    }

    /* Package internal methods are called from X509CA */

    CTSubmissionConfigParams getCTSubmissionConfigParams() {
        return ctSubmissionConfigParams;
    }

    CTAuditLogCallback getCTAuditLogCallback() {
        return ctAuditLogCallback;
    }

    public SctDataCallback getSctDataCallback() {
        return sctDataCallback;
    }

    public void setSctDataCallback(SctDataCallback sctDataCallback) {
        this.sctDataCallback = sctDataCallback;
    }

    /**
     * Gets the validation domain service reference.
     * @return the domain service reference.
     */
    public CertificateValidationDomainService getCertificateValidationDomainService() {
        return certificateValidationDomainService;
    }

    /**
     * Sets the validation domain service reference.
     * @param certificateValidationDomainService the domain service reference.
     */
    public void setCertificateValidationDomainService(CertificateValidationDomainService certificateValidationDomainService) {
        this.certificateValidationDomainService = certificateValidationDomainService;
    }

    /**
     * Gets the authentication token.
     * @return the token.
     */
    public AuthenticationToken getAuthenticationToken() {
        return authenticationToken;
    }

    /**
     * Sets the authentication token.
     * @param authenticationToken the token.
     */
    public void setAuthenticationToken(AuthenticationToken authenticationToken) {
        this.authenticationToken = authenticationToken;
    }
}
