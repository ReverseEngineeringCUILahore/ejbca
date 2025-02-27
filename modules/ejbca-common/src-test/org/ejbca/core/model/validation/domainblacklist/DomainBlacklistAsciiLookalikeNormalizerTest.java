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

package org.ejbca.core.model.validation.domainblacklist;


import static org.junit.Assert.assertEquals;

import java.net.IDN;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Tests DomainBlacklistAsciiLookalikeNormalizer functions.
 *
 * @version $Id: DomainBlacklistAsciiLookalikeNormalizerTest.java 31728 2019-03-07 13:45:14Z bastianf $
 */
public class DomainBlacklistAsciiLookalikeNormalizerTest  {
    private static DomainBlacklistAsciiLookalikeNormalizer normalizer;

    @BeforeClass
    public static void before(){
        normalizer = new DomainBlacklistAsciiLookalikeNormalizer();
    }

    @Test
    public void testNormalize() throws Exception {
        String result = normalizer.normalize("go091e.com");
        assertEquals("Expected normalized string", "google.com", result);

    }

    @Test
    public void testNormalizeLetterCombination() throws Exception {
        String result = normalizer.normalize("clcirnfivv5");
        assertEquals("Expected normalized string", "damaws", result);

    }

    @Test
    public void testNormalizePunicode() {
        String punycode = IDN.toASCII("котикg009levv");
        String punycodeNormalized = normalizer.normalize(punycode);

        String normilizedUnicode = IDN.toUnicode(punycodeNormalized);
        assertEquals("Expexted normalized punicode", "xn--googlevv-28gtb0c0c", punycodeNormalized);
        assertEquals("Expexted normalized unicode", "котикgooglevv", normilizedUnicode);
    }

    @Test
    public void testNormalizePunicodeUnicodeCharsInMiddle() {
        String punycode = IDN.toASCII("ama20nкотикg009levv");
        String punycodeNormalized = normalizer.normalize(punycode);

        String normilizedUnicode = IDN.toUnicode(punycodeNormalized);
        assertEquals("Expexted normalized punicode", "xn--amazongooglevv-gxl5ab4ezd", punycodeNormalized);
        assertEquals("Expexted normalized unicode", "amazonкотикgooglevv", normilizedUnicode);
    }

    @Test
    public void testNormalizePunicodeAsciCharsInMiddle() {
        String punycode = IDN.toASCII("котикg009levvчеширик");
        String punycodeNormalized = normalizer.normalize(punycode);

        String normilizedUnicode = IDN.toUnicode(punycodeNormalized);
        assertEquals("Expexted normalized punicode", "xn--googlevv-j8gvjaobl4c6bxa9hva", punycodeNormalized);
        assertEquals("Expexted normalized unicode", "котикgooglevvчеширик", normilizedUnicode);
    }

    @Test
    public void testNormalizePunicodeDots() {
        String punycode = IDN.toASCII("чеширик.g009leмяуvv.vv0rid");
        String punycodeNormalized = normalizer.normalize(punycode);

        String normilizedUnicode = IDN.toUnicode(punycodeNormalized);
        assertEquals("Expexted normalized punicode", "xn--e1agai2a3bi.xn--googlevv-99g4exg.world", punycodeNormalized);
        assertEquals("Expexted normalized unicode", "чеширик.googleмяуvv.world", normilizedUnicode);
    }


}