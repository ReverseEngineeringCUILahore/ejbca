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

package org.ejbca.core.model;

/**
 * Constants for users and certificates. Constants for Type of user: Type is constructed as a mask
 * since one user can be of several types. To test a user type:
 * <pre>
 * if (((type & USER_ENDUSER) == USER_ENDUSER) && ((type & USER_CAADMIN) == USER_ADMINISTOR) || ...
 *    ...
 * </pre>
 * Bit usage: bits 0-7   (1:st byte):  user types bits 8-15  (2:nd byte):  unused bits 16-23 (3:rd
 * byte):  unused bits 24-30 (4:th byte):  unused Constants for certificates are simple integer
 * types. Constants for Token Types Token type is constructed of integer constants since only one
 * token type can be generated.
 *
 * @version $Id: SecConst.java 32112 2019-04-11 10:36:33Z tarmo_r_helmes $
 */
public final class SecConst {

    // Token types.

    /** Indicates that a browser generated token should be used. */
    public static final int TOKEN_SOFT_BROWSERGEN = 1;

    /** Indicates that a p12 token should be generated. */
    public static final int TOKEN_SOFT_P12 = 2;

    /** Indicates that a jks token should be generated. */
    public static final int TOKEN_SOFT_JKS = 3;

    /** Indicates that a pem token should be generated. */
    public static final int TOKEN_SOFT_PEM = 4;

    /** All values equal or below this constant should be treated as a soft token. */
    public static final int TOKEN_SOFT = 100;

    public static final String[] TOKENTEXTS = {"TOKENSOFTUSERGENERATED","TOKENSOFTP12","TOKENSOFTJKS","TOKENSOFTPEM"};

    public static final int[]    TOKENIDS   = {SecConst.TOKEN_SOFT_BROWSERGEN,SecConst.TOKEN_SOFT_P12,SecConst.TOKEN_SOFT_JKS,SecConst.TOKEN_SOFT_PEM};
    
    // ECA-419 Check This!
    public static final int EMPTY_VALIDATOR = 1;

    /** Used in end entity profiles and service workers
     * This is duplicated in CAConstants */
    public static final int ALLCAS = 1;


    /**
     * Constants used in the SignSessionBean indicating the userdata defined CA should be used.
     */
    public static final int CAID_USEUSERDEFINED = 0;

    /**
     * Default key lengths. Users are allowed to choose from these key lengths when
     * lookup of available bit lengths fails.
     * @see org.ejbca.ui.web.pub.ApplyBean
     */
    public static final int[] DEFAULT_KEY_LENGTHS = new int[] {512, 1024, 2048};

    /**
     * Prevents creation of new SecConst
     */
    private SecConst() {
    }

	// Revocation reasons identifiers
    public static final String[] reasontexts = {
        "REV_UNSPECIFIED",			"REV_KEYCOMPROMISE",	"REV_CACOMPROMISE",
        "REV_AFFILIATIONCHANGED",	"REV_SUPERSEDED",		"REV_CESSATIONOFOPERATION",
        "REV_CERTIFICATEHOLD",		"REV_UNUSED",			"REV_REMOVEFROMCRL",
        "REV_PRIVILEGEWITHDRAWN",	"REV_AACOMPROMISE"
    };
    public static final int HIGN_REASON_BOUNDRARY = 11;


}
