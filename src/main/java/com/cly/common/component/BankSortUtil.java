/*
 * www.yiji.com Inc.
 * Copyright (c) 2014 All Rights Reserved
 */

/*
 * 修订记录:
 * linying@yiji.com 2016-08-12 14:40 创建
 *
 */
package com.cly.common.component;


import com.cly.common.util.StringUtils;

import java.io.Serializable;
import java.util.*;

/**
 * @author linying@yiji.com
 */
public class BankSortUtil {

    public static List<Map<String, List<NetBankInfo>>> sort(List<String> keys, List<NetBankInfo> bankInfos){
        List<Map<String, List<NetBankInfo>>> mapList = new ArrayList<Map<String, List<NetBankInfo>>>();

        String[] bankCodeArrays = {"ABC", "ICBC", "BOC", "CCB", "COMM", "PSBC", "CMB", "CIB", "CEB", "CMBC", "CITIC", "PINGANBK"};
        List<String> commonBankCodes = Arrays.asList(bankCodeArrays);
        for(String key: keys){
            Map<String, List<NetBankInfo>> map = new HashMap<String, List<NetBankInfo>>();
            List<NetBankInfo> list = new ArrayList<NetBankInfo>();
            for(NetBankInfo bankInfo: bankInfos){
                if(StringUtils.equals(key, "热门") && commonBankCodes.contains(bankInfo.getBankCode())){
                    NetBankInfo info = new NetBankInfo();
                    Copier.copy(bankInfo, info);
                    info.setBankCode(bankInfo.getBankCode());
                    list.add(info);
                    continue;
                }

                String[] chars = key.split(",");
                String pyStr = getPYIndexStr(bankInfo.getBankName(), true, true);
                for(String a : chars){
                    if(StringUtils.equals(a, pyStr)){
                        NetBankInfo info = new NetBankInfo();
                        Copier.copy(bankInfo, info);
                        info.setBankCode(bankInfo.getBankCode());
                        list.add(info);
                    }
                }
            }

            if(list.size() > 0){
                map.put(StringUtils.replace(key, ",", ""), list);
                mapList.add(map);
            }
        }

        return mapList;
    }

    public static Map sort(List<String> sources){
        Map map=new HashMap();
        String[] chars =
                {
                        "A", "B", "C", "D", "E", "F", "G", "H", "I",
                        "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
                };
        for(String a: chars){
            List<String> list = new ArrayList<>();
            for(int i=0; i< sources.size();i++){//为了排序都返回大写字母
                if(a.equals(getPYIndexStr(sources.get(i), true, true))){
                    list.add(sources.get(i));
                }
            }
            if(list.size() > 0){
                map.put(a, list);
            }
        }
        return map;
    }


    /**
     * 返回字符串每个字符或第一个字符的首字母
     * @param str 目标字符串
     * @param bUpCase 是否返回大写字母,true-是；false-否
     * @param bFirstChar 是否只 返回第一个字符的首字母,true-是；false-否
     * @return String
     */
    public static String getPYIndexStr(String str, boolean bUpCase, boolean bFirstChar){
        try{
            StringBuffer buffer = new StringBuffer();
            String sourceStr;
            if(bFirstChar){
                sourceStr = str.substring(0, 1);
            }else{
                sourceStr = str;
            }

            byte b[] = sourceStr.getBytes("GBK");//把中文转化成byte数组
            for(int i = 0; i < b.length; i++){
                if((b[i] & 255) > 128){
                    int char1 = b[i++] & 255;
                    char1 <<= 8;//左移运算符用“<<”表示，是将运算符左边的对象，向左移动运算符右边指定的位数，并且在低位补零。其实，向左移n位，就相当于乘上2的n次方
                    int chart = char1 + (b[i] & 255);
                    buffer.append(getPYIndexChar((char)chart, bUpCase));
                    continue;
                }

                char c = (char)b[i];
                if(!Character.isJavaIdentifierPart(c)){//确定指定字符是否可以是 Java 标识符中首字符以外的部分。
                    c = 'A';
                }
                buffer.append(c);
            }

            return buffer.toString();
        }catch(Exception e){
            System.out.println((new StringBuilder()).append("\u53D6\u4E2D\u6587\u62FC\u97F3\u6709\u9519").append(e.getMessage()).toString());
        }

        return null;
    }

    /**
     * 得到首字母
     */
    private static char getPYIndexChar(char strChinese, boolean bUpCase){
        int charGBK = strChinese;
        char result;
        if(charGBK >= 45217 && charGBK <= 45252)
            result = 'A';
        else
        if(charGBK >= 45253 && charGBK <= 45760)
            result = 'B';
        else
        if(charGBK >= 45761 && charGBK <= 46317
                || charGBK == 55000)
            result = 'C';
        else
        if(charGBK >= 46318 && charGBK <= 46825)
            result = 'D';
        else
        if(charGBK >= 46826 && charGBK <= 47009)
            result = 'E';
        else
        if(charGBK >= 47010 && charGBK <= 47296)
            result = 'F';
        else
        if(charGBK >= 47297 && charGBK <= 47613)
            result = 'G';
        else
        if(charGBK >= 47614 && charGBK <= 48118)
            result = 'H';
        else
        if(charGBK >= 48119 && charGBK <= 49061)
            result = 'J';
        else
        if(charGBK >= 49062 && charGBK <= 49323)
            result = 'K';
        else
        if(charGBK >= 49324 && charGBK <= 49895)
            result = 'L';
        else
        if(charGBK >= 49896 && charGBK <= 50370)
            result = 'M';
        else
        if(charGBK >= 50371 && charGBK <= 50613)
            result = 'N';
        else
        if(charGBK >= 50614 && charGBK <= 50621)
            result = 'O';
        else
        if(charGBK >= 50622 && charGBK <= 50905)
            result = 'P';
        else
        if(charGBK >= 50906 && charGBK <= 51386)
            result = 'Q';
        else
        if(charGBK >= 51387 && charGBK <= 51445)
            result = 'R';
        else
        if(charGBK >= 51446 && charGBK <= 52217)
            result = 'S';
        else
        if(charGBK >= 52218 && charGBK <= 52697)
            result = 'T';
        else
        if(charGBK >= 52698 && charGBK <= 52979)
            result = 'W';
        else
        if(charGBK >= 52980 && charGBK <= 53688)
            result = 'X';
        else
        if(charGBK >= 53689 && charGBK <= 54480)
            result = 'Y';
        else
        if(charGBK >= 54481 && charGBK <= 55289)
            result = 'Z';
        else
            result = (char)(65 + (new Random()).nextInt(25));
        if(!bUpCase)
            result = Character.toLowerCase(result);
        return result;
    }

    public static class NetBankInfo implements Serializable, Comparable<NetBankInfo> {
        private String bankCode;
        private String bankName;
        private String logoUrl;
        private String website;

        public String getBankCode() {
            return bankCode;
        }

        public void setBankCode(String bankCode) {
            this.bankCode = bankCode;
        }

        public String getBankName() {
            return bankName;
        }

        public void setBankName(String bankName) {
            this.bankName = bankName;
        }

        public String getLogoUrl() {
            return logoUrl;
        }

        public void setLogoUrl(String logoUrl) {
            this.logoUrl = logoUrl;
        }

        public String getWebsite() {
            return website;
        }

        public void setWebsite(String website) {
            this.website = website;
        }

        public int compareTo(NetBankInfo o) {
            if (null == o)
                return 1;
            if (this.equals(o))
                return 0;
            if (null == o.getBankCode())
                return 1;
            if (null == this.getBankCode())
                return -1;

            return o.getBankCode().compareTo(this.getBankCode());

        }
    }



    public static void main(String[] args) {
        List<String> keys = new ArrayList<String>();
        keys.add("热门");
        keys.add("A,B,C,D,E,F");
        keys.add("G,H");
        keys.add("J,K,L,M,N");
        keys.add("P,Q,R,S");
        keys.add("T,W,X,Y");
        keys.add("Z");

        Map map = BankSortUtil.sort(keys, );

        System.out.println(map);

//        String str = "重庆";
//        System.out.println("中文首字母："+getPYIndexStr(str, true, true));
    }

}
