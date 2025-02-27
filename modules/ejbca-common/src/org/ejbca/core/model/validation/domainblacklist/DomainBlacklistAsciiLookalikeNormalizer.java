/*************************************************************************
 *                                                                       *
 *  EJBCA: The OpenSource Certificate Authority                          *
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

import org.apache.commons.lang.StringUtils;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Replaces look alike ascii characters.
 *
 * @version $Id: DomainBlacklistAsciiLookalikeNormalizer.java 31668 2019-03-01 22:27:39Z samuellb $
 */
public class DomainBlacklistAsciiLookalikeNormalizer implements DomainBlacklistNormalizer {
    private static Pattern patternMixedUnicodeAndAsci = Pattern.compile("(xn--)(.*?)(-.*)");
    private static Pattern patternOnlyUnicode = Pattern.compile("(xn--)([^-]*)");

    private Map<String, Character> replacementMap;

    private Map<String, Character> getReplacementMap() {
        if (replacementMap == null) {
            replacementMap = new LinkedHashMap<>();
            replacementMap.put("ci", 'a');
            replacementMap.put("fi", 'a'); //fi->A->a
            replacementMap.put("cl", 'd');
            replacementMap.put("rn", 'm');
            replacementMap.put("vv", 'w');
            replacementMap.put("0", 'o');
            replacementMap.put("6", 'b');
            replacementMap.put("q", 'g');
            replacementMap.put("9", 'g');
            replacementMap.put("1", 'l');
            replacementMap.put("i", 'l');
            replacementMap.put("5", 's');
            replacementMap.put("2", 'z');
            replacementMap.put("u", 'v');
        }
        return replacementMap;
    }

    @Override
    public String getNameKey() {
        return "DOMAINBLACKLISTVALIDATOR_NORMALIZATION_ASCIILOOKALIKE";
    }

    @Override
    public void initialize(final Map<Object, Object> configData) {
    }

    @Override
    public String normalize(final String domain) {
        if (StringUtils.isNotEmpty(domain)) {
            String normalizeString = domain.toLowerCase();
            StringBuilder sb = new StringBuilder(domain.length());
            String[] domainParts = normalizeString.split("\\.");
            for (int i = 0; i < domainParts.length; i++) {
                normalizeString = domainParts[i];
                Matcher matcher = patternOnlyUnicode.matcher(normalizeString);
                if (matcher.matches()) {
                    sb.append(normalizeString);
                    if (i < domainParts.length - 1) {
                        sb.append(".");
                    }
                    continue;
                }
                matcher = patternMixedUnicodeAndAsci.matcher(normalizeString);
                boolean isPuny = false;
                if (matcher.matches()) {
                    sb.append(matcher.group(1));
                    isPuny = true;
                    normalizeString = matcher.group(2);
                }

                for (Map.Entry<String, Character> entry : getReplacementMap().entrySet()) {
                    if (!isPuny || entry.getKey().length() == 1) {
                        normalizeString = normalizeString.replace(entry.getKey(), String.valueOf(entry.getValue()));
                    }
                }

                sb.append(normalizeString);
                if (isPuny) {
                    sb.append(matcher.group(3));
                }
                if (i < domainParts.length - 1) {
                    sb.append(".");
                }
            }
            return sb.toString();
        }
        return domain;
    }

}
