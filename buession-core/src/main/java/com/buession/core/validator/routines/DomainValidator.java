/*
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license
 * agreements. See the NOTICE file distributed with this work for additional information regarding
 * copyright ownership. The ASF licenses this file to you under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with the License. You may obtain
 * a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 *
 * =================================================================================================
 *
 * This software consists of voluntary contributions made by many individuals on behalf of the
 * Apache Software Foundation. For more information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 * +------------------------------------------------------------------------------------------------+
 * | License: http://www.apache.org/licenses/LICENSE-2.0.txt 										|
 * | Author: Yong.Teng <webmaster@buession.com> 													|
 * | Copyright @ 2013-2019 Buession.com Inc.														|
 * +------------------------------------------------------------------------------------------------+
 */
package com.buession.core.validator.routines;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 域名验证器
 *
 * @author Yong.Teng
 */
public class DomainValidator {

    private final static String DOMAIN_LABEL_REGEX = "\\p{Alnum}(?>[\\p{Alnum}-]*\\p{Alnum})*";

    private final static String TOP_LABEL_REGEX = "\\p{Alpha}{2,}";

    private final static String DOMAIN_NAME_REGEX = "^(?:" + DOMAIN_LABEL_REGEX + "\\.)+" + "(" + TOP_LABEL_REGEX +
            ")$";

    private final static String[] INFRASTRUCTURE_TLDS = new String[]{
            // internet infrastructure
            "arpa",
            // diagnostic marker for non-truncated root zone
            "root"};

    private final static String[] GENERIC_TLDS = new String[]{
            // air transport industry
            "aero",
            //
            "art",
            // Pan-Asia/Asia Pacific
            "asia",
            //
            "beer",
            //
            "bid",
            // businesses
            "biz",
            //
            "band",
            // Catalan linguistic/cultural community
            "cat",
            //
            "cc",
            //
            "click",
            //
            "club",
            //
            "co",
            // commercial enterprises
            "com",
            // cooperative associations
            "coop",
            //
            "date",
            //
            "design",
            //
            "engineer",
            //
            "fun",
            //
            "game",
            //
            "gift",
            //
            "group",
            //
            "help",
            // informational sites
            "info",
            // Human Resource managers
            "jobs",
            //
            "lawyer",
            //
            "loan",
            //
            "link",
            //
            "live",
            //
            "lol",
            //
            "ltd",
            //
            "kim",
            //
            "market",
            //
            "me",
            // mobile products and services
            "mobi",
            //
            "mom",
            // museums, surprisingly enough
            "museum",
            // individuals' sites
            "name",
            //
            "news",
            // internet support infrastructure/business
            "net",
            //
            "online",
            // noncommercial organizations
            "org",
            //
            "party",
            //
            "photo",
            //
            "pics",
            //
            "press",
            // credentialed professionals and entities
            "pro",
            //
            "pub",
            //
            "red",
            //
            "ren",
            //
            "rocks",
            //
            "shop",
            //
            "science",
            //
            "site",
            //
            "so",
            //
            "social",
            //
            "software",
            //
            "space",
            //
            "store",
            //
            "studio",
            //
            "tech",
            // contact data for businesses and individuals
            "tel",
            //
            "top",
            //
            "trade",
            // entities in the travel industry
            "travel",
            //
            "tv",
            // United States Government
            "gov",
            // accredited postsecondary US education entities
            "edu",
            // United States Military
            "mil",
            //
            "ink",
            // organizations established by international treaty
            "int",
            //
            "video",
            //
            "vip",
            //
            "wang",
            //
            "website",
            //
            "wiki",
            //
            "win",
            //
            "work",
            //
            "xin",
            //
            "xyz"};

    private final static String[] COUNTRY_CODE_TLDS = new String[]{
            // Ascension Island
            "ac",
            // Andorra
            "ad",
            // United Arab Emirates
            "ae",
            // Afghanistan
            "af",
            // Antigua and Barbuda
            "ag",
            // Anguilla
            "ai",
            // Albania
            "al",
            // Armenia
            "am",
            // Netherlands Antilles
            "an",
            // Angola
            "ao",
            // Antarctica
            "aq",
            // Argentina
            "ar",
            // American Samoa
            "as",
            // Austria
            "at",
            // Australia (includes Ashmore and Cartier Islands and Coral Sea Islands)
            "au",
            // Aruba
            "aw",
            // Åland
            "ax",
            // Azerbaijan
            "az",
            // Bosnia and Herzegovina
            "ba",
            // Barbados
            "bb",
            // Bangladesh
            "bd",
            // Belgium
            "be",
            // Burkina Faso
            "bf",
            // Bulgaria
            "bg",
            // Bahrain
            "bh",
            // Burundi
            "bi",
            // Benin
            "bj",
            // Bermuda
            "bm",
            // Brunei Darussalam
            "bn",
            // Bolivia
            "bo",
            // Brazil
            "br",
            // Bahamas
            "bs",
            // Bhutan
            "bt",
            // Bouvet Island
            "bv",
            // Botswana
            "bw",
            // Belarus
            "by",
            // Belize
            "bz",
            // Canada
            "ca",
            // Cocos (Keeling) Islands
            "cc",
            // Democratic Republic of the Congo (formerly Zaire)
            "cd",
            // Central African Republic
            "cf",
            // Republic of the Congo
            "cg",
            // Switzerland
            "ch",
            // Côte d'Ivoire
            "ci",
            // Cook Islands
            "ck",
            // Chile
            "cl",
            // Cameroon
            "cm",
            // China, mainland
            "cn",
            // Colombia
            "co",
            // Costa Rica
            "cr",
            // Cuba
            "cu",
            // Cape Verde
            "cv",
            // Christmas Island
            "cx",
            // Cyprus
            "cy",
            // Czech Republic
            "cz",
            // Germany
            "de",
            // Djibouti
            "dj",
            // Denmark
            "dk",
            // Dominica
            "dm",
            // Dominican Republic
            "do",
            // Algeria
            "dz",
            // Ecuador
            "ec",
            // Estonia
            "ee",
            // Egypt
            "eg",
            // Eritrea
            "er",
            // Spain
            "es",
            // Ethiopia
            "et",
            // European Union
            "eu",
            // Finland
            "fi",
            // Fiji
            "fj",
            // Falkland Islands
            "fk",
            // Federated States of Micronesia
            "fm",
            // Faroe Islands
            "fo",
            // France
            "fr",
            // Gabon
            "ga",
            // Great Britain (United Kingdom)
            "gb",
            // Grenada
            "gd",
            // Georgia
            "ge",
            // French Guiana
            "gf",
            // Guernsey
            "gg",
            // Ghana
            "gh",
            // Gibraltar
            "gi",
            // Greenland
            "gl",
            // The Gambia
            "gm",
            // Guinea
            "gn",
            // Guadeloupe
            "gp",
            // Equatorial Guinea
            "gq",
            // Greece
            "gr",
            // South Georgia and the South Sandwich Islands
            "gs",
            // Guatemala
            "gt",
            // Guam
            "gu",
            // Guinea-Bissau
            "gw",
            // Guyana
            "gy",
            // Hong Kong
            "hk",
            // Heard Island and McDonald Islands
            "hm",
            // Honduras
            "hn",
            // Croatia (Hrvatska)
            "hr",
            // Haiti
            "ht",
            // Hungary
            "hu",
            // Indonesia
            "id",
            // Ireland (Éire)
            "ie",
            // Israel
            "il",
            // Isle of Man
            "im",
            // India
            "in",
            // British Indian Ocean Territory
            "io",
            // Iraq
            "iq",
            // Iran
            "ir",
            // Iceland
            "is",
            // Italy
            "it",
            // Jersey
            "je",
            // Jamaica
            "jm",
            // Jordan
            "jo",
            // Japan
            "jp",
            // Kenya
            "ke",
            // Kyrgyzstan
            "kg",
            // Cambodia (Khmer)
            "kh",
            // Kiribati
            "ki",
            // Comoros
            "km",
            // Saint Kitts and Nevis
            "kn",
            // North Korea
            "kp",
            // South Korea
            "kr",
            // Kuwait
            "kw",
            // Cayman Islands
            "ky",
            // Kazakhstan
            "kz",
            // Laos (currently being marketed as the official domain for Los Angeles)
            "la",
            // Lebanon
            "lb",
            // Saint Lucia
            "lc",
            // Liechtenstein
            "li",
            // Sri Lanka
            "lk",
            // Liberia
            "lr",
            // Lesotho
            "ls",
            // Lithuania
            "lt",
            // Luxembourg
            "lu",
            // Latvia
            "lv",
            // Libya
            "ly",
            // Morocco
            "ma",
            // Monaco
            "mc",
            // Moldova
            "md",
            // Montenegro
            "me",
            // Madagascar
            "mg",
            // Marshall Islands
            "mh",
            // Republic of Macedonia
            "mk",
            // Mali
            "ml",
            // Myanmar
            "mm",
            // Mongolia
            "mn",
            // Macau
            "mo",
            // Northern Mariana Islands
            "mp",
            // Martinique
            "mq",
            // Mauritania
            "mr",
            // Montserrat
            "ms",
            // Malta
            "mt",
            // Mauritius
            "mu",
            // Maldives
            "mv",
            // Malawi
            "mw",
            // Mexico
            "mx",
            // Malaysia
            "my",
            // Mozambique
            "mz",
            // Namibia
            "na",
            // New Caledonia
            "nc",
            // Niger
            "ne",
            // Norfolk Island
            "nf",
            // Nigeria
            "ng",
            // Nicaragua
            "ni",
            // Netherlands
            "nl",
            // Norway
            "no",
            // Nepal
            "np",
            // Nauru
            "nr",
            // Niue
            "nu",
            // New Zealand
            "nz",
            // Oman
            "om",
            // Panama
            "pa",
            // Peru
            "pe",
            // French Polynesia With Clipperton Island
            "pf",
            // Papua New Guinea
            "pg",
            // Philippines
            "ph",
            // Pakistan
            "pk",
            // Poland
            "pl",
            // Saint-Pierre and Miquelon
            "pm",
            // Pitcairn Islands
            "pn",
            // Puerto Rico
            "pr",
            // Palestinian territories (PA-controlled West Bank and Gaza Strip)
            "ps",
            // Portugal
            "pt",
            // Palau
            "pw",
            // Paraguay
            "py",
            // Qatar
            "qa",
            // Réunion
            "re",
            // Romania
            "ro",
            // Serbia
            "rs",
            // Russia
            "ru",
            // Rwanda
            "rw",
            // Saudi Arabia
            "sa",
            // Solomon Islands
            "sb",
            // Seychelles
            "sc",
            // Sudan
            "sd",
            // Sweden
            "se",
            // Singapore
            "sg",
            // Saint Helena
            "sh",
            // Slovenia
            "si",
            // Svalbard and Jan Mayen Islands Not in use (Norwegian dependencies; see .no)
            "sj",
            // Slovakia
            "sk",
            // Sierra Leone
            "sl",
            // San Marino
            "sm",
            // Senegal
            "sn",
            // Somalia
            "so",
            // Suriname
            "sr",
            // São Tomé and Príncipe
            "st",
            // Soviet Union (deprecated)
            "su",
            // El Salvador
            "sv",
            // Syria
            "sy",
            // Swaziland
            "sz",
            // Turks and Caicos Islands
            "tc",
            // Chad
            "td",
            // French Southern and Antarctic Lands
            "tf",
            // Togo
            "tg",
            // Thailand
            "th",
            // Tajikistan
            "tj",
            // Tokelau
            "tk",
            // East Timor (deprecated old code)
            "tl",
            // Turkmenistan
            "tm",
            // Tunisia
            "tn",
            // Tonga
            "to",
            // East Timor
            "tp",
            // Turkey
            "tr",
            // Trinidad and Tobago
            "tt",
            // Tuvalu
            "tv",
            // Taiwan, Republic of China
            "tw",
            // Tanzania
            "tz",
            // Ukraine
            "ua",
            // Uganda
            "ug",
            // United Kingdom
            "uk",
            // United States Minor Outlying Islands
            "um",
            // United States of America
            "us",
            // Uruguay
            "uy",
            // Uzbekistan
            "uz",
            // Vatican City State
            "va",
            // Saint Vincent and the Grenadines
            "vc",
            // Venezuela
            "ve",
            // British Virgin Islands
            "vg",
            // U.S. Virgin Islands
            "vi",
            // Vietnam
            "vn",
            // Vanuatu
            "vu",
            // Wallis and Futuna
            "wf",
            // Samoa (formerly Western Samoa)
            "ws",
            // Yemen
            "ye",
            // Mayotte
            "yt",
            // Serbia and Montenegro (originally Yugoslavia)
            "yu",
            // South Africa
            "za",
            // Zambia
            "zm",
            // Zimbabwe
            "zw",
            //
            "中国",
            //
            "中國",
            //
            "香港",
            //
            "台湾",
            //
            "台灣",
            //
            "公司",
            //
            "网络"};

    private final static String[] LOCAL_TLDS = new String[]{
            // RFC2606 defined
            "localhost",
            // Also widely used as localhost.localdomain
            "localdomain"};

    private final static List INFRASTRUCTURE_TLD_LIST = Arrays.asList(INFRASTRUCTURE_TLDS);

    private final static List GENERIC_TLD_LIST = Arrays.asList(GENERIC_TLDS);

    private final static List COUNTRY_CODE_TLD_LIST = Arrays.asList(COUNTRY_CODE_TLDS);

    private final static List LOCAL_TLD_LIST = Arrays.asList(LOCAL_TLDS);

    private DomainValidator(){

    }

    public final static boolean isValid(final CharSequence charSequence){
        Matcher matcher = Pattern.compile(DOMAIN_NAME_REGEX).matcher(charSequence);

        if(matcher.matches() == true){
            int count = matcher.groupCount();
            String[] groups = new String[count];

            for(int j = 0; j < count; j++){
                groups[j] = matcher.group(j + 1);
            }

            return isValidTld(groups[0]);
        }

        return false;
    }

    private final static boolean isValidTld(String tld){
        return isValidInfrastructureTld(tld) || isValidGenericTld(tld) || isValidCountryCodeTld(tld);
    }

    private final static boolean isValidInfrastructureTld(String iTld){
        return INFRASTRUCTURE_TLD_LIST.contains(chompLeadingDot(iTld.toLowerCase()));
    }

    private final static boolean isValidGenericTld(String gTld){
        return GENERIC_TLD_LIST.contains(chompLeadingDot(gTld.toLowerCase()));
    }

    private final static boolean isValidCountryCodeTld(String ccTld){
        return COUNTRY_CODE_TLD_LIST.contains(chompLeadingDot(ccTld.toLowerCase()));
    }

    private final static String chompLeadingDot(String str){
        return str.startsWith(".") ? str.substring(1) : str;
    }

}
