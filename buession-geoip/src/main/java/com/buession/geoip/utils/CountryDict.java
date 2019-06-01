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
 * | Copyright @ 2013-2017 Buession.com Inc.														|
 * +------------------------------------------------------------------------------------------------+
 */
package com.buession.geoip.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Yong.Teng
 */
public class CountryDict {

    public final static String[] COUNTRY_CODE = {"--", "AP", "EU", "AD", "AE", "AF", "AG", "AI", "AL", "AM", "CW",
            "AO", "AQ", "AR", "AS", "AT", "AU", "AW", "AZ", "BA", "BB", "BD", "BE", "BF", "BG", "BH", "BI", "BJ",
            "BM", "BN", "BO", "BR", "BS", "BT", "BV", "BW", "BY", "BZ", "CA", "CC", "CD", "CF", "CG", "CH", "CI",
            "CK", "CL", "CM", "CN", "CO", "CR", "CU", "CV", "CX", "CY", "CZ", "DE", "DJ", "DK", "DM", "DO", "DZ",
            "EC", "EE", "EG", "EH", "ER", "ES", "ET", "FI", "FJ", "FK", "FM", "FO", "FR", "SX", "GA", "GB", "GD",
            "GE", "GF", "GH", "GI", "GL", "GM", "GN", "GP", "GQ", "GR", "GS", "GT", "GU", "GW", "GY", "HK", "HM",
            "HN", "HR", "HT", "HU", "ID", "IE", "IL", "IN", "IO", "IQ", "IR", "IS", "IT", "JM", "JO", "JP", "KE",
            "KG", "KH", "KI", "KM", "KN", "KP", "KR", "KW", "KY", "KZ", "LA", "LB", "LC", "LI", "LK", "LR", "LS",
            "LT", "LU", "LV", "LY", "MA", "MC", "MD", "MG", "MH", "MK", "ML", "MM", "MN", "MO", "MP", "MQ", "MR",
            "MS", "MT", "MU", "MV", "MW", "MX", "MY", "MZ", "NA", "NC", "NE", "NF", "NG", "NI", "NL", "NO", "NP",
            "NR", "NU", "NZ", "OM", "PA", "PE", "PF", "PG", "PH", "PK", "PL", "PM", "PN", "PR", "PS", "PT", "PW",
            "PY", "QA", "RE", "RO", "RU", "RW", "SA", "SB", "SC", "SD", "SE", "SG", "SH", "SI", "SJ", "SK", "SL",
            "SM", "SN", "SO", "SR", "ST", "SV", "SY", "SZ", "TC", "TD", "TF", "TG", "TH", "TJ", "TK", "TM", "TN",
            "TO", "TL", "TR", "TT", "TV", "TW", "TZ", "UA", "UG", "UM", "US", "UY", "UZ", "VA", "VC", "VE", "VG",
            "VI", "VN", "VU", "WF", "WS", "YE", "YT", "RS", "ZA", "ZM", "ME", "ZW", "A1", "A2", "O1", "AX", "GG",
            "IM", "JE", "BL", "MF", "BQ", "SS", "O1"};

    public final static String[] COUNTRY_NAME = {"N/A", "Asia/Pacific Region", "Europe", "Andorra", "United Arab " +
            "Emirates", "Afghanistan", "Antigua and Barbuda", "Anguilla", "Albania", "Armenia", "Curacao", "Angola",
            "Antarctica", "Argentina", "American Samoa", "Austria", "Australia", "Aruba", "Azerbaijan", "Bosnia and "
            + "Herzegovina", "Barbados", "Bangladesh", "Belgium", "Burkina Faso", "Bulgaria", "Bahrain", "Burundi",
            "Benin", "Bermuda", "Brunei Darussalam", "Bolivia", "Brazil", "Bahamas", "Bhutan", "Bouvet Island",
            "Botswana", "Belarus", "Belize", "Canada", "Cocos (Keeling) Islands", "Congo, The Democratic Republic of " +
            "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "the", "Central African Republic",
            "Congo", "Switzerland", "Cote " + "" + "D'Ivoire", "Cook " + "Islands", "Chile", "Cameroon", "China",
            "Colombia", "Costa Rica", "Cuba", "Cape " + "Verde", "Christmas " + "Island", "Cyprus", "Czech" + " " +
            "Republic", "Germany", "Djibouti", "Denmark", "Dominica", "Dominican " + "Republic", "Algeria",
            "Ecuador", "Estonia", "Egypt", "Western " + "Sahara", "Eritrea", "Spain", "Ethiopia", "Finland", "Fiji",
            "Falkland " + "Islands" + " " + "(Malvinas)", "Micronesia, " + "Federated " + "States" + " of", "Faroe "
            + "Islands", "France", "Sint " + "Maarten " + "(Dutch " + "part)", "Gabon", "United" + " Kingdom",
            "Grenada", "Georgia", "French" + " Guiana", "Ghana", "Gibraltar", "Greenland", "Gambia", "Guinea",
            "Guadeloupe", "Equatorial Guinea", "Greece", "South " + "Georgia" + " and the " + "South " + "Sandwich "
            + "Islands", "Guatemala", "Guam", "Guinea-Bissau", "Guyana", "Hong Kong", "Heard Island and " + "McDonald" +
            " " + "Islands", "Honduras", "Croatia", "Haiti", "Hungary", "Indonesia", "Ireland", "Israel", "India",
            "British" + " Indian " + "Ocean " + "Territory", "Iraq", "Iran, " + "Islamic " + "Republic of",
            "Iceland", "Italy", "Jamaica", "Jordan", "Japan", "Kenya", "Kyrgyzstan", "Cambodia", "Kiribati",
            "Comoros", "Saint Kitts and " + "" + "Nevis", "Korea, " + "Democratic " + "People's " + "Republic of",
            "Korea, " + "Republic of", "Kuwait", "Cayman " + "Islands", "Kazakhstan", "Lao " + "People's Democratic " +
            "Republic", "Lebanon", "Saint" + " Lucia", "Liechtenstein", "Sri " + "Lanka", "Liberia", "Lesotho",
            "Lithuania", "Luxembourg", "Latvia", "Libya", "Morocco", "Monaco", "Moldova, " + "Republic of",
            "Madagascar", "Marshall Islands", "Macedonia", "Mali", "Myanmar", "Mongolia", "Macau", "Northern Mariana " +
            "" + "" + "" + "" + "Islands", "Martinique", "Mauritania", "Montserrat", "Malta", "Mauritius",
            "Maldives", "Malawi", "Mexico", "Malaysia", "Mozambique", "Namibia", "New Caledonia", "Niger", "Norfolk " +
            "Island", "Nigeria", "Nicaragua", "Netherlands", "Norway", "Nepal", "Nauru", "Niue", "New Zealand",
            "Oman", "Panama", "Peru", "French " + "Polynesia", "Papua New Guinea", "Philippines", "Pakistan",
            "Poland", "Saint " + "Pierre and Miquelon", "Pitcairn Islands", "Puerto Rico", "Palestinian " +
            "Territory", "Portugal", "Palau", "Paraguay", "Qatar", "Reunion", "Romania", "Russian " + "Federation",
            "Rwanda", "Saudi Arabia", "Solomon " + "Islands", "Seychelles", "Sudan", "Sweden", "Singapore", "Saint "
            + "Helena", "Slovenia", "Svalbard and " + "Jan " + "Mayen", "Slovakia", "Sierra" + " " + "Leone", "San "
            + "Marino", "Senegal", "Somalia", "Suriname", "Sao Tome and " + "Principe", "El " + "Salvador", "Syrian"
            + " " + "Arab " + "Republic", "Swaziland", "Turks and Caicos Islands", "Chad", "French " + "Southern " +
            "Territories", "Togo", "Thailand", "Tajikistan", "Tokelau", "Turkmenistan", "Tunisia", "Tonga",
            "Timor-Leste", "Turkey", "Trinidad and " + "Tobago", "Tuvalu", "Taiwan", "Tanzania, " + "United Republic " +
            "" + "" + "of", "Ukraine", "Uganda", "United " + "States " + "Minor Outlying Islands", "United " +
            "States", "Uruguay", "Uzbekistan", "Holy " + "See " + "(Vatican City State)" + "", "Saint " + "Vincent " +
            "and the " + "Grenadines", "Venezuela", "Virgin Islands," + " " + "" + "British", "Virgin " + "Islands, U" +
            ".S.", "Vietnam", "Vanuatu", "Wallis and Futuna", "Samoa", "Yemen", "Mayotte", "Serbia", "South" + " " +
            "Africa", "Zambia", "Montenegro", "Zimbabwe", "Anonymous Proxy", "Satellite " + "Provider", "Other",
            "Aland " + "Islands", "Guernsey", "Isle of Man", "Jersey", "Saint Barthelemy", "Saint " + "Martin",
            "Bonaire, " + "Saint Eustatius " + "and " + "Saba", "South Sudan", "Other"};

    public final static Map<String, String> COUNTRY_FULLNAME = new HashMap<String, String>();

    static{
        COUNTRY_FULLNAME.put("A1", "匿名代理");
        COUNTRY_FULLNAME.put("A2", "卫星供应商");
        COUNTRY_FULLNAME.put("AD", "安道尔公国");
        COUNTRY_FULLNAME.put("AE", "阿拉伯联合酋长国");
        COUNTRY_FULLNAME.put("AF", "阿富汗伊斯兰共和国");
        COUNTRY_FULLNAME.put("AG", "安提瓜和巴布达");
        COUNTRY_FULLNAME.put("AI", "安圭拉");
        COUNTRY_FULLNAME.put("AL", "阿尔巴尼亚共和国");
        COUNTRY_FULLNAME.put("AM", "亚美尼亚共和国");
        COUNTRY_FULLNAME.put("AO", "安哥拉共和国");
        COUNTRY_FULLNAME.put("AP", "亚洲/太平洋地区");
        COUNTRY_FULLNAME.put("AQ", "南极洲");
        COUNTRY_FULLNAME.put("AR", "阿根廷共和国");
        COUNTRY_FULLNAME.put("AS", "美属萨摩亚群");
        COUNTRY_FULLNAME.put("AT", "奥地利共和国");
        COUNTRY_FULLNAME.put("AU", "澳大利亚联邦");
        COUNTRY_FULLNAME.put("AW", "阿鲁巴");
        COUNTRY_FULLNAME.put("AX", "奥兰群岛");
        COUNTRY_FULLNAME.put("AZ", "阿塞拜疆共和国");

        COUNTRY_FULLNAME.put("BA", "波斯尼亚和黑塞哥维那");
        COUNTRY_FULLNAME.put("BB", "巴巴多斯");
        COUNTRY_FULLNAME.put("BD", "孟加拉人民共和国");
        COUNTRY_FULLNAME.put("BE", "比利时王国");
        COUNTRY_FULLNAME.put("BF", "布基纳法索");
        COUNTRY_FULLNAME.put("BG", "保加利亚共和国");
        COUNTRY_FULLNAME.put("BH", "巴林王国");
        COUNTRY_FULLNAME.put("BI", "布隆迪共和国");
        COUNTRY_FULLNAME.put("BJ", "贝宁共和国");
        COUNTRY_FULLNAME.put("BL", "圣巴特岛");
        COUNTRY_FULLNAME.put("BM", "百慕大群岛");
        COUNTRY_FULLNAME.put("BN", "文莱达鲁萨兰国");
        COUNTRY_FULLNAME.put("BO", "多民族玻利维亚国");
        COUNTRY_FULLNAME.put("BQ", "荷兰加勒比区");
        COUNTRY_FULLNAME.put("BR", "巴西联邦共和国");
        COUNTRY_FULLNAME.put("BS", "巴哈马国");
        COUNTRY_FULLNAME.put("BT", "不丹王国");
        COUNTRY_FULLNAME.put("BV", "布韦岛");
        COUNTRY_FULLNAME.put("BW", "博茨瓦纳共和国");
        COUNTRY_FULLNAME.put("BY", "白俄罗斯共和国");
        COUNTRY_FULLNAME.put("BZ", "伯利兹");

        COUNTRY_FULLNAME.put("CA", "加拿大自治领");
        COUNTRY_FULLNAME.put("CC", "科科斯（基林）群岛");
        COUNTRY_FULLNAME.put("CD", "刚果民主共和国");
        COUNTRY_FULLNAME.put("CF", "中非共和国");
        COUNTRY_FULLNAME.put("CG", "刚果共和国");
        COUNTRY_FULLNAME.put("CH", "瑞士联邦");
        COUNTRY_FULLNAME.put("CI", "科特迪瓦共和国");
        COUNTRY_FULLNAME.put("CK", "库克群岛");
        COUNTRY_FULLNAME.put("CL", "智利共和国");
        COUNTRY_FULLNAME.put("CM", "喀麦隆共和国");
        COUNTRY_FULLNAME.put("CN", "中华人民共和国");
        COUNTRY_FULLNAME.put("CO", "哥伦比亚共和国");
        COUNTRY_FULLNAME.put("CR", "哥斯达黎加共和国");
        COUNTRY_FULLNAME.put("CU", "古巴共和国");
        COUNTRY_FULLNAME.put("CV", "佛得角共和国");
        COUNTRY_FULLNAME.put("CW", "库拉索岛");
        COUNTRY_FULLNAME.put("CX", "澳大利亚圣诞岛");
        COUNTRY_FULLNAME.put("CY", "塞浦路斯共和国");
        COUNTRY_FULLNAME.put("CZ", "捷克共和国");

        COUNTRY_FULLNAME.put("DE", "德意志联邦共和国");
        COUNTRY_FULLNAME.put("DJ", "吉布提共和国");
        COUNTRY_FULLNAME.put("DK", "丹麦王国");
        COUNTRY_FULLNAME.put("DM", "多米尼克国");

        COUNTRY_FULLNAME.put("DO", "多米尼加共和国");
        COUNTRY_FULLNAME.put("DZ", "阿尔及利亚民主人民共和国");

        COUNTRY_FULLNAME.put("EC", "厄瓜多尔共和国");
        COUNTRY_FULLNAME.put("EE", "爱沙尼亚共和国");
        COUNTRY_FULLNAME.put("EG", "阿拉伯埃及共和国");
        COUNTRY_FULLNAME.put("EH", "阿拉伯撒哈拉民主共和国");
        COUNTRY_FULLNAME.put("ER", "厄立特里亚国");
        COUNTRY_FULLNAME.put("ES", "西班牙王国");
        COUNTRY_FULLNAME.put("ET", "埃塞俄比亚联邦民主共和国");
        COUNTRY_FULLNAME.put("EU", "欧洲");

        COUNTRY_FULLNAME.put("FI", "芬兰共和国");
        COUNTRY_FULLNAME.put("FJ", "斐济共和国");
        COUNTRY_FULLNAME.put("FK", "马尔维纳斯群岛");
        COUNTRY_FULLNAME.put("FM", "密克罗尼西亚联邦");
        COUNTRY_FULLNAME.put("FO", "法罗群岛");
        COUNTRY_FULLNAME.put("FR", "法兰西共和国");

        COUNTRY_FULLNAME.put("GA", "加蓬共和国");
        COUNTRY_FULLNAME.put("GB", "大不列颠及北爱尔兰联合王国");
        COUNTRY_FULLNAME.put("GD", "格林纳达");
        COUNTRY_FULLNAME.put("GE", "格鲁吉亚");
        COUNTRY_FULLNAME.put("GF", "法属圭亚那");
        COUNTRY_FULLNAME.put("GG", "根西岛");
        COUNTRY_FULLNAME.put("GH", "加纳共和国");
        COUNTRY_FULLNAME.put("GI", "直布罗陀");
        COUNTRY_FULLNAME.put("GL", "格陵兰岛");
        COUNTRY_FULLNAME.put("GM", "冈比亚共和国");
        COUNTRY_FULLNAME.put("GN", "几内亚共和国");
        COUNTRY_FULLNAME.put("GP", "瓜德罗普省");
        COUNTRY_FULLNAME.put("GQ", "赤道几内亚");
        COUNTRY_FULLNAME.put("GR", "希腊共和国");
        COUNTRY_FULLNAME.put("GS", "南乔治亚岛和南桑威奇群岛");
        COUNTRY_FULLNAME.put("GT", "危地马拉共和国");
        COUNTRY_FULLNAME.put("GU", "关岛");
        COUNTRY_FULLNAME.put("GW", "几内亚比绍共和国");
        COUNTRY_FULLNAME.put("GY", "圭亚那合作共和国");

        COUNTRY_FULLNAME.put("HK", "中华人民共和国香港特别行政区");
        COUNTRY_FULLNAME.put("HM", "赫德岛和麦克唐纳群岛");
        COUNTRY_FULLNAME.put("HN", "洪都拉斯共和国");
        COUNTRY_FULLNAME.put("HR", "克罗地亚共和国");
        COUNTRY_FULLNAME.put("HT", "海地共和国");
        COUNTRY_FULLNAME.put("HU", "匈牙利");

        COUNTRY_FULLNAME.put("ID", "印度尼西亚共和国");
        COUNTRY_FULLNAME.put("IE", "爱尔兰共和国");
        COUNTRY_FULLNAME.put("IL", "以色列国");
        COUNTRY_FULLNAME.put("IM", "马恩岛");
        COUNTRY_FULLNAME.put("IN", "印度共和国");
        COUNTRY_FULLNAME.put("IO", "英属印度洋领地");
        COUNTRY_FULLNAME.put("IQ", "伊拉克共和国");
        COUNTRY_FULLNAME.put("IR", "伊朗伊斯兰共和国");
        COUNTRY_FULLNAME.put("IS", "冰岛共和国");
        COUNTRY_FULLNAME.put("IT", "意大利共和国");
        COUNTRY_FULLNAME.put("JE", "泽西岛");
        COUNTRY_FULLNAME.put("JM", "牙买加");
        COUNTRY_FULLNAME.put("JO", "约旦哈希姆王国");
        COUNTRY_FULLNAME.put("JP", "日本国");

        COUNTRY_FULLNAME.put("KE", "肯尼亚共和国");
        COUNTRY_FULLNAME.put("KG", "吉尔吉斯共和国");
        COUNTRY_FULLNAME.put("KH", "柬埔寨王国");
        COUNTRY_FULLNAME.put("KI", "基里巴斯共和国");
        COUNTRY_FULLNAME.put("KM", "科摩罗伊斯兰联邦共和国");
        COUNTRY_FULLNAME.put("KN", "圣基茨和尼维斯联邦");
        COUNTRY_FULLNAME.put("KP", "朝鲜民主主义人民共和国");
        COUNTRY_FULLNAME.put("KR", "大韩民国");
        COUNTRY_FULLNAME.put("KW", "科威特国");
        COUNTRY_FULLNAME.put("KY", "英属开曼群岛");
        COUNTRY_FULLNAME.put("KZ", "哈萨克斯坦共和国");

        COUNTRY_FULLNAME.put("LA", "老挝人民民主共和国");
        COUNTRY_FULLNAME.put("LB", "黎巴嫩");
        COUNTRY_FULLNAME.put("LC", "圣卢西亚");
        COUNTRY_FULLNAME.put("LI", "列支敦士登公国");
        COUNTRY_FULLNAME.put("LK", "斯里兰卡民主社会主义共和国");
        COUNTRY_FULLNAME.put("LR", "利比里亚共和国");
        COUNTRY_FULLNAME.put("LS", "莱索托王国");
        COUNTRY_FULLNAME.put("LT", "立陶宛共和国");
        COUNTRY_FULLNAME.put("LU", "卢森堡大公国");
        COUNTRY_FULLNAME.put("LV", "拉脱维亚共和国");
        COUNTRY_FULLNAME.put("LY", "利比亚国");

        COUNTRY_FULLNAME.put("MA", "摩洛哥王国");
        COUNTRY_FULLNAME.put("MC", "摩纳哥公国");
        COUNTRY_FULLNAME.put("MD", "摩尔多瓦共和国");
        COUNTRY_FULLNAME.put("ME", "黑山共和国");
        COUNTRY_FULLNAME.put("MF", "圣马丁岛");
        COUNTRY_FULLNAME.put("MG", "马达加斯加共和国");
        COUNTRY_FULLNAME.put("MH", "马绍尔群岛");
        COUNTRY_FULLNAME.put("MK", "马其顿共和国");
        COUNTRY_FULLNAME.put("ML", "马里共和国");
        COUNTRY_FULLNAME.put("MM", "缅甸联邦共和国");
        COUNTRY_FULLNAME.put("MN", "蒙古人民共和国");
        COUNTRY_FULLNAME.put("MO", "中华人民共和国澳门特别行政区");
        COUNTRY_FULLNAME.put("MP", "北马里亚纳群岛自由邦");
        COUNTRY_FULLNAME.put("MQ", "马提尼克岛");
        COUNTRY_FULLNAME.put("MR", "毛里塔尼亚伊斯兰共和国");
        COUNTRY_FULLNAME.put("MS", "蒙特塞拉特岛");
        COUNTRY_FULLNAME.put("MT", "马耳他共和国");
        COUNTRY_FULLNAME.put("MU", "毛里求斯共和国");
        COUNTRY_FULLNAME.put("MV", "马尔代夫共和国");
        COUNTRY_FULLNAME.put("MW", "马拉维共和国");
        COUNTRY_FULLNAME.put("MX", "墨西哥合众国");
        COUNTRY_FULLNAME.put("MY", "马来西亚");
        COUNTRY_FULLNAME.put("MZ", "莫桑比克共和国");

        COUNTRY_FULLNAME.put("NA", "纳米比亚共和国");
        COUNTRY_FULLNAME.put("NC", "新喀里多尼亚");
        COUNTRY_FULLNAME.put("NE", "尼日尔共和国");
        COUNTRY_FULLNAME.put("NF", "诺福克岛");
        COUNTRY_FULLNAME.put("NG", "尼日利亚联邦共和国");
        COUNTRY_FULLNAME.put("NI", "尼加拉瓜共和国");
        COUNTRY_FULLNAME.put("NL", "尼德兰王国");
        COUNTRY_FULLNAME.put("NO", "挪威王国");
        COUNTRY_FULLNAME.put("NP", "尼泊尔联邦民主共和国");
        COUNTRY_FULLNAME.put("NR", "瑙鲁共和国");
        COUNTRY_FULLNAME.put("NU", "纽埃岛");
        COUNTRY_FULLNAME.put("NZ", "新西兰");

        COUNTRY_FULLNAME.put("O1", "其它");
        COUNTRY_FULLNAME.put("OM", "阿曼苏丹国");

        COUNTRY_FULLNAME.put("PA", "巴拿马共和国");
        COUNTRY_FULLNAME.put("PE", "秘鲁共和国");
        COUNTRY_FULLNAME.put("PF", "法属波利尼西亚");
        COUNTRY_FULLNAME.put("PG", "巴布亚新几内亚独立国");
        COUNTRY_FULLNAME.put("PH", "菲律宾共和国");
        COUNTRY_FULLNAME.put("PK", "巴基斯坦伊斯兰共和国");
        COUNTRY_FULLNAME.put("PL", "波兰共和国");
        COUNTRY_FULLNAME.put("PM", "圣皮埃尔和密克隆");
        COUNTRY_FULLNAME.put("PN", "皮特凯恩群岛");
        COUNTRY_FULLNAME.put("PR", "波多黎各");
        COUNTRY_FULLNAME.put("PS", "巴勒斯坦国");
        COUNTRY_FULLNAME.put("PT", "葡萄牙共和国");
        COUNTRY_FULLNAME.put("PW", "帕劳共和国");
        COUNTRY_FULLNAME.put("PY", "巴拉圭共和国");

        COUNTRY_FULLNAME.put("QA", "卡塔尔国");

        COUNTRY_FULLNAME.put("RE", "留尼汪岛");
        COUNTRY_FULLNAME.put("RO", "罗马尼亚");
        COUNTRY_FULLNAME.put("RS", "塞尔维亚共和国");
        COUNTRY_FULLNAME.put("RU", "俄罗斯联邦");
        COUNTRY_FULLNAME.put("RW", "卢旺达共和国");

        COUNTRY_FULLNAME.put("SA", "沙特阿拉伯王国");
        COUNTRY_FULLNAME.put("SB", "所罗门群岛");
        COUNTRY_FULLNAME.put("SC", "塞舌尔共和国");
        COUNTRY_FULLNAME.put("SD", "苏丹共和国");
        COUNTRY_FULLNAME.put("SE", "瑞典王国");
        COUNTRY_FULLNAME.put("SG", "新加坡共和国");
        COUNTRY_FULLNAME.put("SH", "圣赫勒拿岛");
        COUNTRY_FULLNAME.put("SI", "斯洛文尼亚共和国");
        COUNTRY_FULLNAME.put("SJ", "斯瓦尔巴群岛和扬马延岛");
        COUNTRY_FULLNAME.put("SK", "斯洛伐克共和国");
        COUNTRY_FULLNAME.put("SL", "塞拉利昂共和国");
        COUNTRY_FULLNAME.put("SM", "圣马力诺共和国");
        COUNTRY_FULLNAME.put("SN", "塞内加尔共和国");
        COUNTRY_FULLNAME.put("SO", "索马里联邦共和国");
        COUNTRY_FULLNAME.put("SR", "苏里南共和国");
        COUNTRY_FULLNAME.put("SS", "南苏丹共和国");
        COUNTRY_FULLNAME.put("ST", "圣多美和普林西比民主共和国");
        COUNTRY_FULLNAME.put("SV", "萨尔瓦多共和国");
        COUNTRY_FULLNAME.put("SX", "圣马丁岛");
        COUNTRY_FULLNAME.put("SY", "阿拉伯叙利亚共和国");
        COUNTRY_FULLNAME.put("SZ", "斯威士兰王国");

        COUNTRY_FULLNAME.put("TC", "特克斯和凯科斯群岛");
        COUNTRY_FULLNAME.put("TD", "乍得共和国");
        COUNTRY_FULLNAME.put("TF", "法属南部领地");
        COUNTRY_FULLNAME.put("TG", "多哥共和国");
        COUNTRY_FULLNAME.put("TH", "泰王国");
        COUNTRY_FULLNAME.put("TJ", "塔吉克斯坦共和国");
        COUNTRY_FULLNAME.put("TK", "托克劳群岛");
        COUNTRY_FULLNAME.put("TL", "东帝汶民主共和国");
        COUNTRY_FULLNAME.put("TM", "土库曼斯坦");
        COUNTRY_FULLNAME.put("TN", "突尼斯共和国");
        COUNTRY_FULLNAME.put("TO", "汤加王国");
        COUNTRY_FULLNAME.put("TR", "土耳其共和国");
        COUNTRY_FULLNAME.put("TT", "特立尼达和多巴哥共和国");
        COUNTRY_FULLNAME.put("TV", "图瓦卢");
        COUNTRY_FULLNAME.put("TW", "中华人民共和国台湾省");
        COUNTRY_FULLNAME.put("TZ", "坦桑尼亚联合共和国");

        COUNTRY_FULLNAME.put("UA", "乌克兰");
        COUNTRY_FULLNAME.put("UG", "乌干达共和国");
        COUNTRY_FULLNAME.put("UM", "美国本土外小岛屿");
        COUNTRY_FULLNAME.put("US", "美利坚合众国");
        COUNTRY_FULLNAME.put("UY", "乌拉圭东岸共和国");
        COUNTRY_FULLNAME.put("UZ", "乌兹别克斯坦共和国");

        COUNTRY_FULLNAME.put("VA", "梵蒂冈城国");
        COUNTRY_FULLNAME.put("VC", "圣文森特和格林纳丁斯");
        COUNTRY_FULLNAME.put("VE", "委内瑞拉玻利瓦尔共和国");
        COUNTRY_FULLNAME.put("VG", "英属维尔京群岛");
        COUNTRY_FULLNAME.put("VI", "美属维京群岛");
        COUNTRY_FULLNAME.put("VN", "越南社会主义共和国");
        COUNTRY_FULLNAME.put("VU", "瓦努阿图共和国");

        COUNTRY_FULLNAME.put("WF", "瓦利斯群岛和富图纳群岛");
        COUNTRY_FULLNAME.put("WS", "萨摩亚独立国");

        COUNTRY_FULLNAME.put("YE", "也门共和国");
        COUNTRY_FULLNAME.put("YT", "马约特岛");

        COUNTRY_FULLNAME.put("ZA", "南非共和国");
        COUNTRY_FULLNAME.put("ZM", "赞比亚共和国");
        COUNTRY_FULLNAME.put("ZW", "津巴布韦共和国");
    }

}
