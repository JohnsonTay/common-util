/*
 * www.wmjia.cn Inc.
 * Copyright (c) 2016 All Rights Reserved
 */

/*
 * 修订记录:
 * zjzj_547@outlook.com 2016-04-28 21:04 创建
 *
 */
package com.cly.common.util;

import com.cly.common.collection.ArrayUtils;
import com.cly.common.lang.util.Pair;
import com.cly.common.lang.validator.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author zjzj_547@outlook.com
 */
public class StringUtils {
    /** 空的 {@link String} 数组 */
    public static final String[] EMPTY_STRINGS = {};
    /** 空字符串 */
    public static final String EMPTY_STRING = "";
    private static final char SEPARATOR_CHAR_ASTERISK = '*';

    /*
	 * ====================================
	 * 大小写转换
	 * ====================================
	 */

    /**
     * 将字符串转换成大写。
     * --如果字符串是<code>null</code>则返回<code>null</code>。
     *
     * <pre>
     * StringUtils.toUpperCase(null)  = null
     * StringUtils.toUpperCase("")    = ""
     * StringUtils.toUpperCase("aBc") = "ABC"
     * </pre>
     *
     * @param str 要转换的字符串
     * @return 大写字符串，如果原字符串为<code>null</code>，则返回<code>null</code>
     */
    public static String toUpperCase(String str) {
        if (str == null) {
            return null;
        }

        return str.toUpperCase();
    }

    /**
     * 将字符串转换成小写。
     * --如果字符串是<code>null</code>则返回<code>null</code>。
     *
     * <pre>
     * StringUtils.toLowerCase(null)  = null
     * StringUtils.toLowerCase("")    = ""
     * StringUtils.toLowerCase("aBc") = "abc"
     * </pre>
     *
     * @param str 要转换的字符串
     * @return 大写字符串，如果原字符串为<code>null</code>，则返回<code>null</code>
     */
    public static String toLowerCase(String str) {
        if (str == null) {
            return null;
        }

        return str.toLowerCase();
    }

    /**
     * 将字符串的首字符转成大写（<code>Character.toTitleCase</code>），其它字符不变。
     * --如果字符串是<code>null</code>则返回<code>null</code>。
     *
     * <pre>
     * StringUtils.capitalize(null)  = null
     * StringUtils.capitalize("")    = ""
     * StringUtils.capitalize("cat") = "Cat"
     * StringUtils.capitalize("cAt") = "CAt"
     * </pre>
     *
     * @param str 要转换的字符串
     * @return 首字符为大写的字符串，如果原字符串为<code>null</code>，则返回<code>null</code>
     */
    public static String capitalize(String str) {
        int strLen;

        if ((str == null) || ((strLen = str.length()) == 0)) {
            return str;
        }

        return new StringBuffer(strLen).append(Character.toTitleCase(str.charAt(0)))
                .append(str.substring(1)).toString();
    }

    /**
     * 将字符串的首字符转成小写，其它字符不变。
     * --如果字符串是<code>null</code>则返回<code>null</code>。
     *
     * <pre>
     * StringUtils.uncapitalize(null)  = null
     * StringUtils.uncapitalize("")    = ""
     * StringUtils.uncapitalize("Cat") = "cat"
     * StringUtils.uncapitalize("CAT") = "cAT"
     * </pre>
     *
     * @param str 要转换的字符串
     * @return 首字符为小写的字符串，如果原字符串为<code>null</code>，则返回<code>null</code>
     */
    public static String uncapitalize(String str) {
        int strLen;

        if ((str == null) || ((strLen = str.length()) == 0)) {
            return str;
        }

        return new StringBuffer(strLen).append(Character.toLowerCase(str.charAt(0)))
                .append(str.substring(1)).toString();
    }

    /**
     * 反转字符串的大小写。
     * --如果字符串是<code>null</code>则返回<code>null</code>。
     *
     * <pre>
     * StringUtils.swapCase(null)                 = null
     * StringUtils.swapCase("")                   = ""
     * StringUtils.swapCase("The dog has a BONE") = "tHE DOG HAS A bone"
     * </pre>
     *
     * @param str 要转换的字符串
     * @return 大小写被反转的字符串，如果原字符串为<code>null</code>，则返回<code>null</code>
     */
    public static String swapCase(String str) {
        int strLen;

        if ((str == null) || ((strLen = str.length()) == 0)) {
            return str;
        }

        StringBuffer buffer = new StringBuffer(strLen);

        char ch = 0;

        for (int i = 0; i < strLen; i++) {
            ch = str.charAt(i);

            if (Character.isUpperCase(ch)) {
                ch = Character.toLowerCase(ch);
            } else if (Character.isTitleCase(ch)) {
                ch = Character.toLowerCase(ch);
            } else if (Character.isLowerCase(ch)) {
                ch = Character.toUpperCase(ch);
            }

            buffer.append(ch);
        }

        return buffer.toString();
    }

    /**
     * 将一个字符串指定位置的字符变为大写。
     *
     * @param str String 字符串。
     * @param index int 指定位置,该数必须大于等于1,小于等于字符串长度。
     * @return String 改变后的字符串。
     * @throws IndexOutOfBoundsException 如果 index 大于 <code>str.length()</code>。
     */
    public static String toUpperCase(String str, int index) {
        StringBuilder sb = new StringBuilder(str);
        char s = Character.toUpperCase(sb.charAt(index - 1));
        sb.setCharAt(index - 1, s);
        return sb.toString();
    }

    /**
     * 将一个字符串指定位置的字符变为小写。
     *
     * @param str String 字符串。
     * @param index int 指定位置,该数必须大于等于1,小于等于字符串长度。
     * @return String 改变后的字符串。
     * @throws IndexOutOfBoundsException 如果 index 大于 <code>str.length()</code>。
     */
    public static String toLowerCase(String str, int index) {
        StringBuilder sb = new StringBuilder(str);
        char s = Character.toLowerCase(sb.charAt(index - 1));
        sb.setCharAt(index - 1, s);
        return sb.toString();
    }


    /*
	 * ============================
	 *  字符串分割函数
	 * ============================
	 */

    /**
     * 将字符串按空白字符分割。
     * --分隔符不会出现在目标数组中，连续的分隔符就被看作一个。如果字符串为<code>null</code>，则返回<code>null</code>。
     *
     * <pre>
     * StringUtils.split(null)       = null
     * StringUtils.split("")         = []
     * StringUtils.split("abc def")  = ["abc", "def"]
     * StringUtils.split("abc  def") = ["abc", "def"]
     * StringUtils.split(" abc ")    = ["abc"]
     * </pre>
     *
     * @param str 要分割的字符串
     * @return 分割后的字符串数组，如果原字符串为<code>null</code>，则返回<code>null</code>
     */
    public static String[] split(String str) {
        return split(str, null, -1);
    }

    /**
     * 将字符串按指定字符分割。
     * --分隔符不会出现在目标数组中，连续的分隔符就被看作一个。如果字符串为<code>null</code>，则返回<code>null</code>。
     *
     * <pre>
     * StringUtils.split(null, *)         = null
     * StringUtils.split("", *)           = []
     * StringUtils.split("a.b.c", '.')    = ["a", "b", "c"]
     * StringUtils.split("a..b.c", '.')   = ["a", "b", "c"]
     * StringUtils.split("a:b:c", '.')    = ["a:b:c"]
     * StringUtils.split("a b c", ' ')    = ["a", "b", "c"]
     * </pre>
     *
     * @param str 要分割的字符串
     * @param separatorChar 分隔符
     * @return 分割后的字符串数组，如果原字符串为<code>null</code>，则返回<code>null</code>
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static String[] split(String str, char separatorChar) {
        if (str == null) {
            return null;
        }

        int length = str.length();

        if (length == 0) {
            return StringUtils.EMPTY_STRINGS;
        }

        List list = new ArrayList();
        int i = 0;
        int start = 0;
        boolean match = false;

        while (i < length) {
            if (str.charAt(i) == separatorChar) {
                if (match) {
                    list.add(str.substring(start, i));
                    match = false;
                }

                start = ++i;
                continue;
            }

            match = true;
            i++;
        }

        if (match) {
            list.add(str.substring(start, i));
        }

        return (String[]) list.toArray(new String[list.size()]);
    }

    /**
     * 将字符串按指定字符分割。
     * --分隔符不会出现在目标数组中，连续的分隔符就被看作一个。如果字符串为<code>null</code>，则返回<code>null</code>。
     *
     * <pre>
     * StringUtils.split(null, *)                = null
     * StringUtils.split("", *)                  = []
     * StringUtils.split("abc def", null)        = ["abc", "def"]
     * StringUtils.split("abc def", " ")         = ["abc", "def"]
     * StringUtils.split("abc  def", " ")        = ["abc", "def"]
     * StringUtils.split(" ab:  cd::ef  ", ":")  = ["ab", "cd", "ef"]
     * StringUtils.split("abc.def", "")          = ["abc.def"]
     * </pre>
     *
     * @param str 要分割的字符串
     * @param separatorChars 分隔符
     * @return 分割后的字符串数组，如果原字符串为<code>null</code>，则返回<code>null</code>
     */
    public static String[] split(String str, String separatorChars) {
        return split(str, separatorChars, -1);
    }

    /**
     * 将字符串按指定字符分割。
     * --分隔符不会出现在目标数组中，连续的分隔符就被看作一个。如果字符串为<code>null</code>，则返回<code>null</code>。
     *
     * <pre>
     * StringUtils.split(null, *, *)                 = null
     * StringUtils.split("", *, *)                   = []
     * StringUtils.split("ab cd ef", null, 0)        = ["ab", "cd", "ef"]
     * StringUtils.split("  ab   cd ef  ", null, 0)  = ["ab", "cd", "ef"]
     * StringUtils.split("ab:cd::ef", ":", 0)        = ["ab", "cd", "ef"]
     * StringUtils.split("ab:cd:ef", ":", 2)         = ["ab", "cdef"]
     * StringUtils.split("abc.def", "", 2)           = ["abc.def"]
     * </pre>
     *
     * @param str 要分割的字符串
     * @param separatorChars 分隔符
     * @param max 返回的数组的最大个数，如果小于等于0，则表示无限制
     * @return 分割后的字符串数组，如果原字符串为<code>null</code>，则返回<code>null</code>
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static String[] split(String str, String separatorChars, int max) {
        if (str == null) {
            return null;
        }

        int length = str.length();

        if (length == 0) {
            return StringUtils.EMPTY_STRINGS;
        }

        List list = new ArrayList();
        int sizePlus1 = 1;
        int i = 0;
        int start = 0;
        boolean match = false;

        if (separatorChars == null) {
            // null表示使用空白作为分隔符
            while (i < length) {
                if (Character.isWhitespace(str.charAt(i))) {
                    if (match) {
                        if (sizePlus1++ == max) {
                            i = length;
                        }

                        list.add(str.substring(start, i));
                        match = false;
                    }

                    start = ++i;
                    continue;
                }

                match = true;
                i++;
            }
        } else if (separatorChars.length() == 1) {
            // 优化分隔符长度为1的情形
            char sep = separatorChars.charAt(0);

            while (i < length) {
                if (str.charAt(i) == sep) {
                    if (match) {
                        if (sizePlus1++ == max) {
                            i = length;
                        }

                        list.add(str.substring(start, i));
                        match = false;
                    }

                    start = ++i;
                    continue;
                }

                match = true;
                i++;
            }
        } else {
            // 一般情形
            while (i < length) {
                if (separatorChars.indexOf(str.charAt(i)) >= 0) {
                    if (match) {
                        if (sizePlus1++ == max) {
                            i = length;
                        }

                        list.add(str.substring(start, i));
                        match = false;
                    }

                    start = ++i;
                    continue;
                }

                match = true;
                i++;
            }
        }

        if (match) {
            list.add(str.substring(start, i));
        }

        return (String[]) list.toArray(new String[list.size()]);
    }

    /*
	 * ==============================
	 * 字符串连接函数
	 * ==============================
	 * ==
	 */

    /**
     * 将数组中的元素连接成一个字符串。
     *
     * <pre>
     * StringUtils.join(null)            = null
     * StringUtils.join([])              = ""
     * StringUtils.join([null])          = ""
     * StringUtils.join(["a", "b", "c"]) = "abc"
     * StringUtils.join([null, "", "a"]) = "a"
     * </pre>
     *
     * @param array 要连接的数组
     * @return 连接后的字符串，如果原数组为<code>null</code>，则返回<code>null</code>
     */
    public static String join(Object[] array) {
        return join(array, null);
    }

    /**
     * 将数组中的元素按指定分隔符连接成一个字符串。
     *
     * <pre>
     * StringUtils.join(null, *)               = null
     * StringUtils.join([], *)                 = ""
     * StringUtils.join([null], *)             = ""
     * StringUtils.join(["a", "b", "c"], ';')  = "a;b;c"
     * StringUtils.join(["a", "b", "c"], null) = "abc"
     * StringUtils.join([null, "", "a"], ';')  = ";;a"
     * </pre>
     *
     * @param array 要连接的数组
     * @param separator 分隔符
     * @return 连接后的字符串，如果原数组为<code>null</code>，则返回<code>null</code>
     */
    public static String join(Object[] array, char separator) {
        if (array == null) {
            return null;
        }

        int arraySize = array.length;
        int bufSize = (arraySize == 0) ? 0 : ((((array[0] == null) ? 16 : array[0].toString()
                .length()) + 1) * arraySize);
        StringBuffer buf = new StringBuffer(bufSize);

        for (int i = 0; i < arraySize; i++) {
            if (i > 0) {
                buf.append(separator);
            }

            if (array[i] != null) {
                buf.append(array[i]);
            }
        }

        return buf.toString();
    }

    /**
     * 将数组中的元素按指定字符串连接成一个字符串。
     *
     * <pre>
     * StringUtils.join(null, *)                = null
     * StringUtils.join([], *)                  = ""
     * StringUtils.join([null], *)              = ""
     * StringUtils.join(["a", "b", "c"], "--")  = "a--b--c"
     * StringUtils.join(["a", "b", "c"], null)  = "abc"
     * StringUtils.join(["a", "b", "c"], "")    = "abc"
     * StringUtils.join([null, "", "a"], ',')   = ",,a"
     * </pre>
     *
     * @param array 要连接的数组
     * @param separator 分隔符
     * @return 连接后的字符串，如果原数组为<code>null</code>，则返回<code>null</code>
     */
    public static String join(Object[] array, String separator) {
        if (array == null) {
            return null;
        }

        if (separator == null) {
            separator = EMPTY_STRING;
        }

        int arraySize = array.length;

        // ArraySize == 0: Len = 0
        // ArraySize > 0: Len = NofStrings *(len(firstString) + len(separator))
        // (估计大约所有的字符串都一样长)
        int bufSize = (arraySize == 0) ? 0 : (arraySize * (((array[0] == null) ? 16 : array[0]
                .toString().length()) + ((separator != null) ? separator.length() : 0)));

        StringBuffer buf = new StringBuffer(bufSize);

        for (int i = 0; i < arraySize; i++) {
            if ((separator != null) && (i > 0)) {
                buf.append(separator);
            }

            if (array[i] != null) {
                buf.append(array[i]);
            }
        }

        return buf.toString();
    }

    /**
     * 将<code>Iterator</code>中的元素按指定字符连接成一个字符串。
     * <p/>
     *
     * <pre>
     * StringUtils.join(null, *)                = null
     * StringUtils.join([], *)                  = ""
     * StringUtils.join([null], *)              = ""
     * StringUtils.join(["a", "b", "c"], "--")  = "a--b--c"
     * StringUtils.join(["a", "b", "c"], null)  = "abc"
     * StringUtils.join(["a", "b", "c"], "")    = "abc"
     * StringUtils.join([null, "", "a"], ',')   = ",,a"
     * </pre>
     *
     * @param iterator 要连接的<code>Iterator</code>
     * @param separator 分隔符
     * @return 连接后的字符串，如果原数组为<code>null</code>，则返回<code>null</code>
     */
    @SuppressWarnings("rawtypes")
    public static String join(Iterator iterator, char separator) {
        if (iterator == null) {
            return null;
        }

        StringBuffer buf = new StringBuffer(256); // Java默认值是16, 可能偏小

        while (iterator.hasNext()) {
            Object obj = iterator.next();

            if (obj != null) {
                buf.append(obj);
            }

            if (iterator.hasNext()) {
                buf.append(separator);
            }
        }

        return buf.toString();
    }

    /**
     * 将<code>Iterator</code>中的元素按指定字符串连接成一个字符串。
     * <p/>
     *
     * <pre>
     * StringUtils.join(null, *)                = null
     * StringUtils.join([], *)                  = ""
     * StringUtils.join([null], *)              = ""
     * StringUtils.join(["a", "b", "c"], "--")  = "a--b--c"
     * StringUtils.join(["a", "b", "c"], null)  = "abc"
     * StringUtils.join(["a", "b", "c"], "")    = "abc"
     * StringUtils.join([null, "", "a"], ',')   = ",,a"
     * </pre>
     *
     * @param iterator 要连接的<code>Iterator</code>
     * @param separator 分隔符
     * @return 连接后的字符串，如果原数组为<code>null</code>，则返回<code>null</code>
     */
    public static String join(Iterator<?> iterator, String separator) {
        if (iterator == null) {
            return null;
        }

        StringBuffer buf = new StringBuffer(256); // Java默认值是16, 可能偏小

        while (iterator.hasNext()) {
            Object obj = iterator.next();

            if (obj != null) {
                buf.append(obj);
            }

            if ((separator != null) && iterator.hasNext()) {
                buf.append(separator);
            }
        }

        return buf.toString();
    }

    /*
	 * ================================
	 * 字符串查找函数 —— 字符或字符串
	 * ================================
	 * ==
	 */

    /**
     * 在字符串中查找指定字符，并返回第一个匹配的索引值。
     *      --如果字符串为<code>null</code>或未找到，则返回<code>-1</code>。
     *
     * <pre>
     * StringUtils.indexOf(null, *)         = -1
     * StringUtils.indexOf("", *)           = -1
     * StringUtils.indexOf("aabaabaa", 'a') = 0
     * StringUtils.indexOf("aabaabaa", 'b') = 2
     * </pre>
     *
     * @param str 要扫描的字符串
     * @param searchChar 要查找的字符
     * @return 第一个匹配的索引值。如果字符串为<code>null</code>或未找到，则返回<code>-1</code>
     */
    public static int indexOf(String str, char searchChar) {
        if ((str == null) || (str.length() == 0)) {
            return -1;
        }

        return str.indexOf(searchChar);
    }

    /**
     * 在字符串中从指定位置开始查找指定字符，并返回第一个匹配的索引值。
     *      --如果字符串为<code>null</code>或未找到，则返回<code>-1</code>。
     *
     * <pre>
     * StringUtils.indexOf(null, *, *)          = -1
     * StringUtils.indexOf("", *, *)            = -1
     * StringUtils.indexOf("aabaabaa", 'b', 0)  = 2
     * StringUtils.indexOf("aabaabaa", 'b', 3)  = 5
     * StringUtils.indexOf("aabaabaa", 'b', 9)  = -1
     * StringUtils.indexOf("aabaabaa", 'b', -1) = 2
     * </pre>
     *
     * @param str 要扫描的字符串
     * @param searchChar 要查找的字符
     * @param startPos 开始搜索的索引值，如果小于0，则看作0
     * @return 第一个匹配的索引值。如果字符串为<code>null</code>或未找到，则返回<code>-1</code>
     */
    public static int indexOf(String str, char searchChar, int startPos) {
        if ((str == null) || (str.length() == 0)) {
            return -1;
        }

        return str.indexOf(searchChar, startPos);
    }

    /**
     * 在字符串中查找指定字符串，并返回第一个匹配的索引值。
     *      --如果字符串为<code>null</code>或未找到，则返回<code>-1</code>。
     *
     * <pre>
     * StringUtils.indexOf(null, *)          = -1
     * StringUtils.indexOf(*, null)          = -1
     * StringUtils.indexOf("", "")           = 0
     * StringUtils.indexOf("aabaabaa", "a")  = 0
     * StringUtils.indexOf("aabaabaa", "b")  = 2
     * StringUtils.indexOf("aabaabaa", "ab") = 1
     * StringUtils.indexOf("aabaabaa", "")   = 0
     * </pre>
     *
     * @param str 要扫描的字符串
     * @param searchStr 要查找的字符串
     * @return 第一个匹配的索引值。如果字符串为<code>null</code>或未找到，则返回<code>-1</code>
     */
    public static int indexOf(String str, String searchStr) {
        if ((str == null) || (searchStr == null)) {
            return -1;
        }

        return str.indexOf(searchStr);
    }

    /**
     * 在字符串中查找指定字符集合中的字符，并返回第一个匹配的起始索引。
     *      --如果字符串为<code>null</code>，则返回<code>-1</code>。
     *      --如果字符集合为<code>null</code>或空，也返回<code>-1</code>。
     *
     * <pre>
     * StringUtils.indexOfAny(null, *)                = -1
     * StringUtils.indexOfAny("", *)                  = -1
     * StringUtils.indexOfAny(*, null)                = -1
     * StringUtils.indexOfAny(*, [])                  = -1
     * StringUtils.indexOfAny("zzabyycdxx",['z','a']) = 0
     * StringUtils.indexOfAny("zzabyycdxx",['b','y']) = 3
     * StringUtils.indexOfAny("aba", ['z'])           = -1
     * </pre>
     *
     * @param str 要扫描的字符串
     * @param searchChars 要搜索的字符集合
     * @return 第一个匹配的索引值。如果字符串为<code>null</code>或未找到，则返回<code>-1</code>
     */
    public static int indexOfAny(String str, char[] searchChars) {
        if ((str == null) || (str.length() == 0) || (searchChars == null)
                || (searchChars.length == 0)) {
            return -1;
        }

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);

            for (int j = 0; j < searchChars.length; j++) {
                if (searchChars[j] == ch) {
                    return i;
                }
            }
        }

        return -1;
    }

    /**
     * 在字符串中查找指定字符集合中的字符，并返回第一个匹配的起始索引。
     *      --如果字符串为<code>null</code>，则返回<code>-1</code>。
     *      --如果字符集合为<code>null</code>或空，也返回<code>-1</code>。
     *
     * <pre>
     * StringUtils.indexOfAny(null, *)            = -1
     * StringUtils.indexOfAny("", *)              = -1
     * StringUtils.indexOfAny(*, null)            = -1
     * StringUtils.indexOfAny(*, "")              = -1
     * StringUtils.indexOfAny("zzabyycdxx", "za") = 0
     * StringUtils.indexOfAny("zzabyycdxx", "by") = 3
     * StringUtils.indexOfAny("aba","z")          = -1
     * </pre>
     *
     * @param str 要扫描的字符串
     * @param searchChars 要搜索的字符集合
     * @return 第一个匹配的索引值。如果字符串为<code>null</code>或未找到，则返回<code>-1</code>
     */
    public static int indexOfAny(String str, String searchChars) {
        if ((str == null) || (str.length() == 0) || (searchChars == null)
                || (searchChars.length() == 0)) {
            return -1;
        }

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);

            for (int j = 0; j < searchChars.length(); j++) {
                if (searchChars.charAt(j) == ch) {
                    return i;
                }
            }
        }

        return -1;
    }

    /**
     * 在字符串中查找指定字符串集合中的字符串，并返回第一个匹配的起始索引。
     *      --如果字符串为<code>null</code>，则返回<code>-1</code>。
     *      --如果字符串集合为<code>null</code>或空，也返回<code>-1</code>。
     *      --如果字符串集合包括<code>""</code>，并且字符串不为<code>null</code>，则返回<code>str.length()</code>
     *
     * <pre>
     * StringUtils.indexOfAny(null, *)                     = -1
     * StringUtils.indexOfAny(*, null)                     = -1
     * StringUtils.indexOfAny(*, [])                       = -1
     * StringUtils.indexOfAny("zzabyycdxx", ["ab","cd"])   = 2
     * StringUtils.indexOfAny("zzabyycdxx", ["cd","ab"])   = 2
     * StringUtils.indexOfAny("zzabyycdxx", ["mn","op"])   = -1
     * StringUtils.indexOfAny("zzabyycdxx", ["zab","aby"]) = 1
     * StringUtils.indexOfAny("zzabyycdxx", [""])          = 0
     * StringUtils.indexOfAny("", [""])                    = 0
     * StringUtils.indexOfAny("", ["a"])                   = -1
     * </pre>
     *
     * @param str 要扫描的字符串
     * @param searchStrs 要搜索的字符串集合
     * @return 第一个匹配的索引值。如果字符串为<code>null</code>或未找到，则返回<code>-1</code>
     */
    public static int indexOfAny(String str, String[] searchStrs) {
        if ((str == null) || (searchStrs == null)) {
            return -1;
        }

        int sz = searchStrs.length;

        // String's can't have a MAX_VALUEth index.
        int ret = Integer.MAX_VALUE;

        int tmp = 0;

        for (int i = 0; i < sz; i++) {
            String search = searchStrs[i];

            if (search == null) {
                continue;
            }

            tmp = str.indexOf(search);

            if (tmp == -1) {
                continue;
            }

            if (tmp < ret) {
                ret = tmp;
            }
        }

        return (ret == Integer.MAX_VALUE) ? (-1) : ret;
    }

    /**
     * 在字符串中查找不在指定字符集合中的字符，并返回第一个匹配的起始索引。
     *      --如果字符串为<code>null</code>，则返回<code>-1</code>。
     *      --如果字符集合为<code>null</code>或空，也返回<code>-1</code>。
     *
     * <pre>
     * StringUtils.indexOfAnyBut(null, *)             = -1
     * StringUtils.indexOfAnyBut("", *)               = -1
     * StringUtils.indexOfAnyBut(*, null)             = -1
     * StringUtils.indexOfAnyBut(*, [])               = -1
     * StringUtils.indexOfAnyBut("zzabyycdxx",'za')   = 3
     * StringUtils.indexOfAnyBut("zzabyycdxx", 'by')  = 0
     * StringUtils.indexOfAnyBut("aba", 'ab')         = -1
     * </pre>
     *
     * @param str 要扫描的字符串
     * @param searchChars 要搜索的字符集合
     * @return 第一个匹配的索引值。如果字符串为<code>null</code>或未找到，则返回<code>-1</code>
     */
    public static int indexOfAnyBut(String str, char[] searchChars) {
        if ((str == null) || (str.length() == 0) || (searchChars == null)
                || (searchChars.length == 0)) {
            return -1;
        }

        outer: for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);

            for (int j = 0; j < searchChars.length; j++) {
                if (searchChars[j] == ch) {
                    continue outer;
                }
            }

            return i;
        }

        return -1;
    }

    /**
     * 在字符串中查找不在指定字符集合中的字符，并返回第一个匹配的起始索引。
     *      --如果字符串为<code>null</code>，则返回<code>-1</code>。
     *      --如果字符集合为<code>null</code>或空，也返回<code>-1</code>。
     *
     * <pre>
     * StringUtils.indexOfAnyBut(null, *)            = -1
     * StringUtils.indexOfAnyBut("", *)              = -1
     * StringUtils.indexOfAnyBut(*, null)            = -1
     * StringUtils.indexOfAnyBut(*, "")              = -1
     * StringUtils.indexOfAnyBut("zzabyycdxx", "za") = 3
     * StringUtils.indexOfAnyBut("zzabyycdxx", "by") = 0
     * StringUtils.indexOfAnyBut("aba","ab")         = -1
     * </pre>
     *
     * @param str 要扫描的字符串
     * @param searchChars 要搜索的字符集合
     * @return 第一个匹配的索引值。如果字符串为<code>null</code>或未找到，则返回<code>-1</code>
     */
    public static int indexOfAnyBut(String str, String searchChars) {
        if ((str == null) || (str.length() == 0) || (searchChars == null)
                || (searchChars.length() == 0)) {
            return -1;
        }

        for (int i = 0; i < str.length(); i++) {
            if (searchChars.indexOf(str.charAt(i)) < 0) {
                return i;
            }
        }

        return -1;
    }

    /**
     * 从字符串尾部开始查找指定字符，并返回第一个匹配的索引值。
     *      --如果字符串为<code>null</code>或未找到，则返回<code>-1</code>。
     *
     * <pre>
     * StringUtils.lastIndexOf(null, *)         = -1
     * StringUtils.lastIndexOf("", *)           = -1
     * StringUtils.lastIndexOf("aabaabaa", 'a') = 7
     * StringUtils.lastIndexOf("aabaabaa", 'b') = 5
     * </pre>
     *
     * @param str 要扫描的字符串
     * @param searchChar 要查找的字符
     * @return 第一个匹配的索引值。如果字符串为<code>null</code>或未找到，则返回<code>-1</code>
     */
    public static int lastIndexOf(String str, char searchChar) {
        if ((str == null) || (str.length() == 0)) {
            return -1;
        }

        return str.lastIndexOf(searchChar);
    }

    /**
     * 从字符串尾部开始查找指定字符，并返回第一个匹配的索引值。
     *      --如果字符串为<code>null</code>或未找到，则返回<code>-1</code>。
     *
     * <pre>
     * StringUtils.lastIndexOf(null, *, *)          = -1
     * StringUtils.lastIndexOf("", *,  *)           = -1
     * StringUtils.lastIndexOf("aabaabaa", 'b', 8)  = 5
     * StringUtils.lastIndexOf("aabaabaa", 'b', 4)  = 2
     * StringUtils.lastIndexOf("aabaabaa", 'b', 0)  = -1
     * StringUtils.lastIndexOf("aabaabaa", 'b', 9)  = 5
     * StringUtils.lastIndexOf("aabaabaa", 'b', -1) = -1
     * StringUtils.lastIndexOf("aabaabaa", 'a', 0)  = 0
     * </pre>
     *
     * @param str 要扫描的字符串
     * @param searchChar 要查找的字符
     * @param startPos 从指定索引开始向前搜索
     * @return 第一个匹配的索引值。如果字符串为<code>null</code>或未找到，则返回<code>-1</code>
     */
    public static int lastIndexOf(String str, char searchChar, int startPos) {
        if ((str == null) || (str.length() == 0)) {
            return -1;
        }

        return str.lastIndexOf(searchChar, startPos);
    }

    /**
     * 从字符串尾部开始查找指定字符串，并返回第一个匹配的索引值。
     *      --如果字符串为<code>null</code>或未找到，则返回<code>-1</code>。
     *
     * <pre>
     * StringUtils.lastIndexOf(null, *)         = -1
     * StringUtils.lastIndexOf("", *)           = -1
     * StringUtils.lastIndexOf("aabaabaa", 'a') = 7
     * StringUtils.lastIndexOf("aabaabaa", 'b') = 5
     * </pre>
     *
     * @param str 要扫描的字符串
     * @param searchStr 要查找的字符串
     * @return 第一个匹配的索引值。如果字符串为<code>null</code>或未找到，则返回<code>-1</code>
     */
    public static int lastIndexOf(String str, String searchStr) {
        if ((str == null) || (searchStr == null)) {
            return -1;
        }

        return str.lastIndexOf(searchStr);
    }

    /**
     * 从字符串尾部开始查找指定字符串集合中的字符串，并返回第一个匹配的起始索引。
     *      --如果字符串为<code>null</code>，则返回<code>-1</code>。
     *      --如果字符串集合为<code>null</code>或空，也返回<code>-1</code>。
     *      --如果字符串集合包括<code>""</code>，并且字符串不为<code>null</code>，则返回<code>str.length()</code>
     *
     * <pre>
     * StringUtils.lastIndexOfAny(null, *)                   = -1
     * StringUtils.lastIndexOfAny(*, null)                   = -1
     * StringUtils.lastIndexOfAny(*, [])                     = -1
     * StringUtils.lastIndexOfAny(*, [null])                 = -1
     * StringUtils.lastIndexOfAny("zzabyycdxx", ["ab","cd"]) = 6
     * StringUtils.lastIndexOfAny("zzabyycdxx", ["cd","ab"]) = 6
     * StringUtils.lastIndexOfAny("zzabyycdxx", ["mn","op"]) = -1
     * StringUtils.lastIndexOfAny("zzabyycdxx", ["mn","op"]) = -1
     * StringUtils.lastIndexOfAny("zzabyycdxx", ["mn",""])   = 10
     * </pre>
     *
     * @param str 要扫描的字符串
     * @param searchStrs 要搜索的字符串集合
     * @return 第一个匹配的索引值。如果字符串为<code>null</code>或未找到，则返回<code>-1</code>
     */
    public static int lastIndexOfAny(String str, String[] searchStrs) {
        if ((str == null) || (searchStrs == null)) {
            return -1;
        }

        int searchStrsLength = searchStrs.length;
        int index = -1;
        int tmp = 0;

        for (int i = 0; i < searchStrsLength; i++) {
            String search = searchStrs[i];

            if (search == null) {
                continue;
            }

            tmp = str.lastIndexOf(search);

            if (tmp > index) {
                index = tmp;
            }
        }

        return index;
    }

    /**
     * 检查字符串中是否包含指定的字符。
     *      --如果字符串为<code>null</code>，将返回<code>false</code>。
     *
     * <pre>
     * StringUtils.contains(null, *)    = false
     * StringUtils.contains("", *)      = false
     * StringUtils.contains("abc", 'a') = true
     * StringUtils.contains("abc", 'z') = false
     * </pre>
     *
     * @param str 要扫描的字符串
     * @param searchChar 要查找的字符
     * @return 如果找到，则返回<code>true</code>
     */
    public static boolean contains(String str, char searchChar) {
        if ((str == null) || (str.length() == 0)) {
            return false;
        }

        return str.indexOf(searchChar) >= 0;
    }

    /**
     * 检查字符串中是否包含指定的字符串。
     *      --如果字符串为<code>null</code>，将返回<code>false</code>。
     *
     * <pre>
     * StringUtils.contains(null, *)     = false
     * StringUtils.contains(*, null)     = false
     * StringUtils.contains("", "")      = true
     * StringUtils.contains("abc", "")   = true
     * StringUtils.contains("abc", "a")  = true
     * StringUtils.contains("abc", "z")  = false
     * </pre>
     *
     * @param str 要扫描的字符串
     * @param searchStr 要查找的字符串
     * @return 如果找到，则返回<code>true</code>
     */
    public static boolean contains(String str, String searchStr) {
        if ((str == null) || (searchStr == null)) {
            return false;
        }

        return str.indexOf(searchStr) >= 0;
    }

    /**
     * 检查字符串是是否只包含指定字符集合中的字符。
     *      --如果字符串为<code>null</code>，则返回<code>false</code>。
     *      --如果字符集合为<code>null</code>，则返回<code>false</code>。 但是空字符串永远返回<code>true</code>.
     *
     * <pre>
     * StringUtils.containsOnly(null, *)       = false
     * StringUtils.containsOnly(*, null)       = false
     * StringUtils.containsOnly("", *)         = true
     * StringUtils.containsOnly("ab", '')      = false
     * StringUtils.containsOnly("abab", 'abc') = true
     * StringUtils.containsOnly("ab1", 'abc')  = false
     * StringUtils.containsOnly("abz", 'abc')  = false
     * </pre>
     *
     * @param str 要扫描的字符串
     * @param valid 要查找的字符串
     * @return 如果找到，则返回<code>true</code>
     */
    public static boolean containsOnly(String str, char[] valid) {
        if ((valid == null) || (str == null)) {
            return false;
        }

        if (str.length() == 0) {
            return true;
        }

        if (valid.length == 0) {
            return false;
        }

        return indexOfAnyBut(str, valid) == -1;
    }

    /**
     * 检查字符串是是否只包含指定字符集合中的字符。
     *      --如果字符串为<code>null</code>，则返回<code>false</code>。
     *      --如果字符集合为<code>null</code>，则返回<code>false</code>。 但是空字符串永远返回<code>true</code>.
     *
     * <pre>
     * StringUtils.containsOnly(null, *)       = false
     * StringUtils.containsOnly(*, null)       = false
     * StringUtils.containsOnly("", *)         = true
     * StringUtils.containsOnly("ab", "")      = false
     * StringUtils.containsOnly("abab", "abc") = true
     * StringUtils.containsOnly("ab1", "abc")  = false
     * StringUtils.containsOnly("abz", "abc")  = false
     * </pre>
     *
     * @param str 要扫描的字符串
     * @param valid 要查找的字符串
     * @return 如果找到，则返回<code>true</code>
     */
    public static boolean containsOnly(String str, String valid) {
        if ((str == null) || (valid == null)) {
            return false;
        }

        return containsOnly(str, valid.toCharArray());
    }

    /**
     * 检查字符串是是否不包含指定字符集合中的字符。
     *      --如果字符串为<code>null</code>，则返回<code>false</code>。
     *      --如果字符集合为<code>null</code>，则返回<code>true</code>。
     *      --但是空字符串永远返回<code>true</code>.
     *
     * <pre>
     * StringUtils.containsNone(null, *)       = true
     * StringUtils.containsNone(*, null)       = true
     * StringUtils.containsNone("", *)         = true
     * StringUtils.containsNone("ab", '')      = true
     * StringUtils.containsNone("abab", 'xyz') = true
     * StringUtils.containsNone("ab1", 'xyz')  = true
     * StringUtils.containsNone("abz", 'xyz')  = false
     * </pre>
     *
     * @param str 要扫描的字符串
     * @param invalid 要查找的字符串
     * @return 如果找到，则返回<code>true</code>
     */
    public static boolean containsNone(String str, char[] invalid) {
        if ((str == null) || (invalid == null)) {
            return true;
        }

        int strSize = str.length();
        int validSize = invalid.length;

        for (int i = 0; i < strSize; i++) {
            char ch = str.charAt(i);

            for (int j = 0; j < validSize; j++) {
                if (invalid[j] == ch) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * 检查字符串是是否不包含指定字符集合中的字符。
     *      --如果字符串为<code>null</code>，则返回<code>false</code>。
     *      --如果字符集合为<code>null</code>，则返回<code>true</code>。
     *      --空字符串永远返回<code>true</code>.
     *
     * <pre>
     * StringUtils.containsNone(null, *)       = true
     * StringUtils.containsNone(*, null)       = true
     * StringUtils.containsNone("", *)         = true
     * StringUtils.containsNone("ab", "")      = true
     * StringUtils.containsNone("abab", "xyz") = true
     * StringUtils.containsNone("ab1", "xyz")  = true
     * StringUtils.containsNone("abz", "xyz")  = false
     * </pre>
     *
     * @param str 要扫描的字符串
     * @param invalidChars 要查找的字符串
     * @return 如果找到，则返回<code>true</code>
     */
    public static boolean containsNone(String str, String invalidChars) {
        if ((str == null) || (invalidChars == null)) {
            return true;
        }

        return containsNone(str, invalidChars.toCharArray());
    }

    /**
     * 取得指定子串在字符串中出现的次数。
     *      --如果字符串为<code>null</code>或空，则返回<code>0</code>。
     *
     * <pre>
     * StringUtils.countMatches(null, *)       = 0
     * StringUtils.countMatches("", *)         = 0
     * StringUtils.countMatches("abba", null)  = 0
     * StringUtils.countMatches("abba", "")    = 0
     * StringUtils.countMatches("abba", "a")   = 2
     * StringUtils.countMatches("abba", "ab")  = 1
     * StringUtils.countMatches("abba", "xxx") = 0
     * </pre>
     *
     * @param str 要扫描的字符串
     * @param subStr 子字符串
     * @return 子串在字符串中出现的次数，如果字符串为<code>null</code>或空，则返回<code>0</code>
     */
    public static int countMatches(String str, String subStr) {
        if ((str == null) || (str.length() == 0) || (subStr == null) || (subStr.length() == 0)) {
            return 0;
        }

        int count = 0;
        int index = 0;

        while ((index = str.indexOf(subStr, index)) != -1) {
            count++;
            index += subStr.length();
        }

        return count;
    }

    /*
	 * ============================
	 * 取子串函数
	 * ============================
	 * ==
	 */

    /**
     * 压缩字符串。
     *      --如果开始位置大于字符串长度，则返回空字符串。 负的索引代表从尾部开始计算。
     *      --如果字符串为<code>null</code>，则返回<code>null</code>。
     *
     * <pre>
     * StringUtils.compressString(null, *)   = null
     * StringUtils.compressString("", *)     = ""
     * StringUtils.compressString("abc", 0)  = "abc"
     * StringUtils.compressString("abc", 2)  = "c"
     * StringUtils.compressString("abc", 4)  = ""
     * StringUtils.compressString("abc", -2) = "bc"
     * StringUtils.compressString("abc", -4) = "abc"
     * </pre>
     *
     * @param str 字符串
     * @param start 起始索引，如果为负数，表示从尾部查找
     * @return 压缩后的字符串，如果原始串为<code>null</code>，则返回<code>null</code>
     */
    public static String compressString(String str, int start) {
        if (str == null) {
            return null;
        }

        if (start < 0) {
            start = str.length() + start;
        }

        if (start < 0) {
            start = 0;
        }

        if (start > str.length()) {
            return EMPTY_STRING;
        }

        return str.substring(start);
    }

    /**
     * 压缩字符串。
     *      --如果开始位置大于字符串长度，则返回空字符串，
     *      --如果结束位置大于字符串长度，则取开始位置到原字符串的末尾。负的索引代表从尾部开始计算。
     *      --如果字符串为<code>null</code>，则返回<code>null</code>。
     *
     * <pre>
     * StringUtils.compressString(null, *, *)    = null
     * StringUtils.compressString("", * ,  *)    = "";
     * StringUtils.compressString("abc", 0, 2)   = "ab"
     * StringUtils.compressString("abc", 2, 0)   = ""
     * StringUtils.compressString("abc", 4, 7)   = ""
     * StringUtils.compressString("abc", 2, 4)   = "c"
     * StringUtils.compressString("abc", 4, 6)   = ""
     * StringUtils.compressString("abc", 2, 2)   = ""
     * StringUtils.compressString("abc", -2, -1) = "b"
     * StringUtils.compressString("abc", -4, 2)  = "ab"
     * </pre>
     *
     * @param str 字符串
     * @param start 起始索引，如果为负数，表示从尾部计算
     * @param end 结束索引（不含），如果为负数，表示从尾部计算
     * @return 压缩后的字符串，如果原始串为<code>null</code>，则返回<code>null</code>
     */
    public static String compressString(String str, int start, int end) {
        if (str == null) {
            return null;
        }

        if (end < 0) {
            end = str.length() + end;
        }

        if (start < 0) {
            start = str.length() + start;
        }

        if (end > str.length()) {
            end = str.length();
        }

        if (start > end) {
            return EMPTY_STRING;
        }

        if (start < 0) {
            start = 0;
        }

        if (end < 0) {
            end = 0;
        }

        return str.substring(start, end);
    }

    /**
     * 取得长度为指定字符数的最左边的子串。
     *
     * <pre>
     * StringUtils.left(null, *)    = null
     * StringUtils.left(*, -ve)     = ""
     * StringUtils.left("", *)      = ""
     * StringUtils.left("abc", 0)   = ""
     * StringUtils.left("abc", 2)   = "ab"
     * StringUtils.left("abc", 4)   = "abc"
     * </pre>
     *
     * @param str 字符串
     * @param len 最左子串的长度
     * @return 子串，如果原始字串为<code>null</code>，则返回<code>null</code>
     */
    public static String left(String str, int len) {
        if (str == null) {
            return null;
        }

        if (len < 0) {
            return EMPTY_STRING;
        }

        if (str.length() <= len) {
            return str;
        } else {
            return str.substring(0, len);
        }
    }

    /**
     * 取得长度为指定字符数的最右边的子串。
     *
     * <pre>
     * StringUtils.right(null, *)    = null
     * StringUtils.right(*, -ve)     = ""
     * StringUtils.right("", *)      = ""
     * StringUtils.right("abc", 0)   = ""
     * StringUtils.right("abc", 2)   = "bc"
     * StringUtils.right("abc", 4)   = "abc"
     * </pre>
     *
     * @param str 字符串
     * @param len 最右子串的长度
     * @return 子串，如果原始字串为<code>null</code>，则返回<code>null</code>
     */
    public static String right(String str, int len) {
        if (str == null) {
            return null;
        }

        if (len < 0) {
            return EMPTY_STRING;
        }

        if (str.length() <= len) {
            return str;
        } else {
            return str.substring(str.length() - len);
        }
    }

    /**
     * 取得从指定索引开始计算的、长度为指定字符数的子串。
     *
     * <pre>
     * StringUtils.mid(null, *, *)    = null
     * StringUtils.mid(*, *, -ve)     = ""
     * StringUtils.mid("", 0, *)      = ""
     * StringUtils.mid("abc", 0, 2)   = "ab"
     * StringUtils.mid("abc", 0, 4)   = "abc"
     * StringUtils.mid("abc", 2, 4)   = "c"
     * StringUtils.mid("abc", 4, 2)   = ""
     * StringUtils.mid("abc", -2, 2)  = "ab"
     * </pre>
     *
     * @param str 字符串
     * @param pos 起始索引，如果为负数，则看作<code>0</code>
     * @param len 子串的长度，如果为负数，则看作长度为<code>0</code>
     * @return 子串，如果原始字串为<code>null</code>，则返回<code>null</code>
     */
    public static String mid(String str, int pos, int len) {
        if (str == null) {
            return null;
        }

        if ((len < 0) || (pos > str.length())) {
            return EMPTY_STRING;
        }

        if (pos < 0) {
            pos = 0;
        }

        if (str.length() <= (pos + len)) {
            return str.substring(pos);
        } else {
            return str.substring(pos, pos + len);
        }
    }

    /**
     * 掩码中间几位为*,mask位数自动计算
     *
     * <pre>
     * StringUtils.mask(null)    = null
     * StringUtils.mask("")      = ""
     * StringUtils.mask("1")     = "*"
     * StringUtils.mask("11")   = "*1"
     * StringUtils.mask("111")   = "1*1"
     * StringUtils.mask("1111")   = "1**1"
     * StringUtils.mask("11111")   = "1**11"
     * StringUtils.mask("111111")  = "1***11"
     * StringUtils.mask("11111111111")  = "111*****111"
     * </pre>
     *
     * @param str 需要掩码的字符串
     * @return  掩码后的字符串
     */
    public static String mask(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }
        if (str.length() == 1) {
            return String.valueOf(SEPARATOR_CHAR_ASTERISK);
        }
        Pair<Integer, Integer> pair = getMaskLength(str.length());
        //复制整个str
        char[] chars = str.toCharArray();
        char[] mask = repeatAsterisk(pair.getS());
        //复制mask
        System.arraycopy(mask, 0, chars, (pair.getF().intValue()), pair.getS());
        //复制输出
        return new String(chars);
    }

    /**
     * *掩码中间几位为*
     *
     * @param str String 需要掩码的字符串
     * @param len mask位数
     * @return String  掩码后的字符串
     */
    public static String mask(String str, int len) {
        if (str == null || str.length() == 0) {
            return str;
        }
        if (len >= str.length()) {
            char[] mask = repeatAsterisk(str.length());
            return new String(mask);
        }
        int startIndex = (str.length() - len) / 2;
        char[] mask = repeatAsterisk(len);
        //复制整个str
        char[] chars = str.toCharArray();
        //复制mask
        System.arraycopy(mask, 0, chars, startIndex, mask.length);
        //复制输出
        return new String(chars);
    }

    /**
     * 掩码指定的位数为*
     * 注意:index从0开始
     *
     * @param str 原字符串
     * @param beginIndex 开始index,从0开始
     * @param endIndex 结束index,掩码不包括此位
     * @return 返回掩码后的字符串
     */
    public static String mask(String str, int beginIndex, int endIndex) {
        if (str == null || str.length() == 0) {
            return str;
        }
        if (beginIndex < 0) {
            beginIndex = 0;
        }
        if (endIndex > str.length()) {
            endIndex = str.length();
        }
        int subLen = endIndex - beginIndex;
        if (subLen < 0) {
            throw new StringIndexOutOfBoundsException(subLen);
        }
        if (str.length() == 1) {
            return String.valueOf(SEPARATOR_CHAR_ASTERISK);
        }
        //复制整个str
        char[] chars = str.toCharArray();
        char[] mask = repeatAsterisk(subLen);
        //复制mask
        System.arraycopy(mask, 0, chars, beginIndex, subLen);
        //复制输出
        return new String(chars);
    }

    private static char[] repeatAsterisk(int len) {
        char[] chars = new char[len];
        for (int i = 0; i < len; i++) {
            chars[i] = SEPARATOR_CHAR_ASTERISK;
        }
        return chars;
    }

    /**
     * 第一个保存开始index，第二个保存长度
     *
     * @param len int
     * @return Pair<Integer, Integer>
     */
    private static Pair<Integer, Integer> getMaskLength(int len) {
        int maskLen = Math.max((len)/ 2, 1);
        int begin = (len - maskLen) / 2 ;
        return Pair.build(begin, maskLen);
    }

    /**
	 * =============================
	 * ==
	* 搜索并取子串函数
	 * =============================
	 * ==
	 */

    /**
     * 取得第一个出现的分隔子串之前的子串。
     *      --如果字符串为<code>null</code>，则返回<code>null</code>。
     *      --如果分隔子串为<code>null</code>，或未找到该子串，则返回原字符串。
     *
     * <pre>
     * StringUtils.substringBefore(null, *)      = null
     * StringUtils.substringBefore("", *)        = ""
     * StringUtils.substringBefore("abc", "a")   = ""
     * StringUtils.substringBefore("abcba", "b") = "a"
     * StringUtils.substringBefore("abc", "c")   = "ab"
     * StringUtils.substringBefore("abc", "d")   = "abc"
     * StringUtils.substringBefore("abc", "")    = ""
     * StringUtils.substringBefore("abc", null)  = "abc"
     * </pre>
     *
     * @param str 字符串
     * @param separator 要搜索的分隔子串
     * @return 子串，如果原始串为<code>null</code>，则返回<code>null</code>
     */
    public static String substringBefore(String str, String separator) {
        if ((str == null) || (separator == null) || (str.length() == 0)) {
            return str;
        }

        if (separator.length() == 0) {
            return EMPTY_STRING;
        }

        int pos = str.indexOf(separator);

        if (pos == -1) {
            return str;
        }

        return str.substring(0, pos);
    }

    /**
     * 取得第一个出现的分隔子串之后的子串。
     *      --如果字符串为<code>null</code>，则返回<code>null</code>。
     *      --如果分隔子串为<code>null</code>，或未找到该子串，则返回原字符串。
     *
     * <pre>
     * StringUtils.substringAfter(null, *)      = null
     * StringUtils.substringAfter("", *)        = ""
     * StringUtils.substringAfter(*, null)      = ""
     * StringUtils.substringAfter("abc", "a")   = "bc"
     * StringUtils.substringAfter("abcba", "b") = "cba"
     * StringUtils.substringAfter("abc", "c")   = ""
     * StringUtils.substringAfter("abc", "d")   = ""
     * StringUtils.substringAfter("abc", "")    = "abc"
     * </pre>
     *
     * @param str 字符串
     * @param separator 要搜索的分隔子串
     * @return 子串，如果原始串为<code>null</code>，则返回<code>null</code>
     */
    public static String substringAfter(String str, String separator) {
        if ((str == null) || (str.length() == 0)) {
            return str;
        }

        if (separator == null) {
            return EMPTY_STRING;
        }

        int pos = str.indexOf(separator);

        if (pos == -1) {
            return EMPTY_STRING;
        }

        return str.substring(pos + separator.length());
    }

    /**
     * 取得最后一个的分隔子串之前的子串。
     *      --如果字符串为<code>null</code>，则返回<code>null</code>。
     *      --如果分隔子串为<code>null</code>，或未找到该子串，则返回原字符串。
     *
     * <pre>
     * StringUtils.substringBeforeLast(null, *)      = null
     * StringUtils.substringBeforeLast("", *)        = ""
     * StringUtils.substringBeforeLast("abcba", "b") = "abc"
     * StringUtils.substringBeforeLast("abc", "c")   = "ab"
     * StringUtils.substringBeforeLast("a", "a")     = ""
     * StringUtils.substringBeforeLast("a", "z")     = "a"
     * StringUtils.substringBeforeLast("a", null)    = "a"
     * StringUtils.substringBeforeLast("a", "")      = "a"
     * </pre>
     *
     * @param str 字符串
     * @param separator 要搜索的分隔子串
     * @return 子串，如果原始串为<code>null</code>，则返回<code>null</code>
     */
    public static String substringBeforeLast(String str, String separator) {
        if ((str == null) || (separator == null) || (str.length() == 0)
                || (separator.length() == 0)) {
            return str;
        }

        int pos = str.lastIndexOf(separator);

        if (pos == -1) {
            return str;
        }

        return str.substring(0, pos);
    }

    /**
     * 取得最后一个的分隔子串之后的子串。
     *      --如果字符串为<code>null</code>，则返回<code>null</code>。
     *      --如果分隔子串为<code>null</code>，或未找到该子串，则返回原字符串。
     *
     * <pre>
     * StringUtils.substringAfterLast(null, *)      = null
     * StringUtils.substringAfterLast("", *)        = ""
     * StringUtils.substringAfterLast(*, "")        = ""
     * StringUtils.substringAfterLast(*, null)      = ""
     * StringUtils.substringAfterLast("abc", "a")   = "bc"
     * StringUtils.substringAfterLast("abcba", "b") = "a"
     * StringUtils.substringAfterLast("abc", "c")   = ""
     * StringUtils.substringAfterLast("a", "a")     = ""
     * StringUtils.substringAfterLast("a", "z")     = ""
     * </pre>
     *
     * @param str 字符串
     * @param separator 要搜索的分隔子串
     * @return 子串，如果原始串为<code>null</code>，则返回<code>null</code>
     */
    public static String substringAfterLast(String str, String separator) {
        if ((str == null) || (str.length() == 0)) {
            return str;
        }

        if ((separator == null) || (separator.length() == 0)) {
            return EMPTY_STRING;
        }

        int pos = str.lastIndexOf(separator);

        if ((pos == -1) || (pos == (str.length() - separator.length()))) {
            return EMPTY_STRING;
        }

        return str.substring(pos + separator.length());
    }

    /**
     * 取得指定分隔符的前两次出现之间的子串。
     *      --如果字符串为<code>null</code>，则返回<code>null</code>。
     *      --果分隔子串为<code>null</code>，则返回<code>null</code>。
     *
     * <pre>
     * StringUtils.substringBetween(null, *)            = null
     * StringUtils.substringBetween("", "")             = ""
     * StringUtils.substringBetween("", "tag")          = null
     * StringUtils.substringBetween("tagabctag", null)  = null
     * StringUtils.substringBetween("tagabctag", "")    = ""
     * StringUtils.substringBetween("tagabctag", "tag") = "abc"
     * </pre>
     *
     * @param str 字符串
     * @param tag 要搜索的分隔子串
     * @return 子串，如果原始串为<code>null</code>或未找到分隔子串，则返回<code>null</code>
     */
    public static String substringBetween(String str, String tag) {
        return substringBetween(str, tag, tag, 0);
    }

    /**
     * 取得两个分隔符之间的子串。
     *      --如果字符串为<code>null</code>，则返回<code>null</code>。
     *      --如果分隔子串为<code>null</code>，则返回<code>null</code>。
     *
     * <pre>
     * StringUtils.substringBetween(null, *, *)          = null
     * StringUtils.substringBetween("", "", "")          = ""
     * StringUtils.substringBetween("", "", "tag")       = null
     * StringUtils.substringBetween("", "tag", "tag")    = null
     * StringUtils.substringBetween("yabcz", null, null) = null
     * StringUtils.substringBetween("yabcz", "", "")     = ""
     * StringUtils.substringBetween("yabcz", "y", "z")   = "abc"
     * StringUtils.substringBetween("yabczyabcz", "y", "z")   = "abc"
     * </pre>
     *
     * @param str 字符串
     * @param open 要搜索的分隔子串1
     * @param close 要搜索的分隔子串2
     * @return 子串，如果原始串为<code>null</code>或未找到分隔子串，则返回<code>null</code>
     */
    public static String substringBetween(String str, String open, String close) {
        return substringBetween(str, open, close, 0);
    }

    /**
     * 取得两个分隔符之间的子串。
     *      --如果字符串为<code>null</code>，则返回<code>null</code>。
     *      --如果分隔子串为<code>null</code>，则返回<code>null</code>。
     *
     * <pre>
     * StringUtils.substringBetween(null, *, *)          = null
     * StringUtils.substringBetween("", "", "")          = ""
     * StringUtils.substringBetween("", "", "tag")       = null
     * StringUtils.substringBetween("", "tag", "tag")    = null
     * StringUtils.substringBetween("yabcz", null, null) = null
     * StringUtils.substringBetween("yabcz", "", "")     = ""
     * StringUtils.substringBetween("yabcz", "y", "z")   = "abc"
     * StringUtils.substringBetween("yabczyabcz", "y", "z")   = "abc"
     * </pre>
     *
     * @param str 字符串
     * @param open 要搜索的分隔子串1
     * @param close 要搜索的分隔子串2
     * @param fromIndex 从指定index处搜索
     * @return 子串，如果原始串为<code>null</code>或未找到分隔子串，则返回<code>null</code>
     */
    public static String substringBetween(String str, String open, String close, int fromIndex) {
        if ((str == null) || (open == null) || (close == null)) {
            return null;
        }

        int start = str.indexOf(open, fromIndex);

        if (start != -1) {
            int end = str.indexOf(close, start + open.length());

            if (end != -1) {
                return str.substring(start + open.length(), end);
            }
        }

        return null;
    }

    /*
	 * ============================
	 * 删除字符
	 * ============================
	 */

    /**
     * 删除所有在<code>Character.isWhitespace(char)</code>中所定义的空白。
     *
     * <pre>
     * StringUtils.deleteWhitespace(null)         = null
     * StringUtils.deleteWhitespace("")           = ""
     * StringUtils.deleteWhitespace("abc")        = "abc"
     * StringUtils.deleteWhitespace("   ab  c  ") = "abc"
     * </pre>
     *
     * @param str 要处理的字符串
     * @return 去空白后的字符串，如果原始字符串为<code>null</code>，则返回<code>null</code>
     */
    public static String deleteWhitespace(String str) {
        if (str == null) {
            return null;
        }

        int sz = str.length();
        StringBuffer buffer = new StringBuffer(sz);

        for (int i = 0; i < sz; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                buffer.append(str.charAt(i));
            }
        }

        return buffer.toString();
    }

    /*
	 * ============================
	 * 替换子串
	 * ============================
	 */

    /**
     * 替换指定的子串，只替换第一个出现的子串。
     *      --如果字符串为<code>null</code>则返回<code>null</code>，
     *      --如果指定子串为<code>null</code>，则返回原字符串。
     *
     * <pre>
     * StringUtils.replaceOnce(null, *, *)        = null
     * StringUtils.replaceOnce("", *, *)          = ""
     * StringUtils.replaceOnce("aba", null, null) = "aba"
     * StringUtils.replaceOnce("aba", null, null) = "aba"
     * StringUtils.replaceOnce("aba", "a", null)  = "aba"
     * StringUtils.replaceOnce("aba", "a", "")    = "ba"
     * StringUtils.replaceOnce("aba", "a", "z")   = "zba"
     * </pre>
     *
     * @param text 要扫描的字符串
     * @param repl 要搜索的子串
     * @param with 替换字符串
     * @return 被替换后的字符串，如果原始字符串为<code>null</code>，则返回<code>null</code>
     */
    public static String replaceOnce(String text, String repl, String with) {
        return replace(text, repl, with, 1);
    }

    /**
     * 替换指定的子串，替换所有出现的子串。
     *      --如果字符串为<code>null</code>则返回<code>null</code>，
     *      --如果指定子串为<code>null</code>，则返回原字符串。
     *
     * <pre>
     * StringUtils.replace(null, *, *)        = null
     * StringUtils.replace("", *, *)          = ""
     * StringUtils.replace("aba", null, null) = "aba"
     * StringUtils.replace("aba", null, null) = "aba"
     * StringUtils.replace("aba", "a", null)  = "aba"
     * StringUtils.replace("aba", "a", "")    = "b"
     * StringUtils.replace("aba", "a", "z")   = "zbz"
     * </pre>
     *
     * @param text 要扫描的字符串
     * @param repl 要搜索的子串
     * @param with 替换字符串
     * @return 被替换后的字符串，如果原始字符串为<code>null</code>，则返回<code>null</code>
     */
    public static String replace(String text, String repl, String with) {
        return replace(text, repl, with, -1);
    }

    /**
     * 替换指定的子串，替换指定的次数。
     *      --如果字符串为<code>null</code>则返回<code>null</code>，
     *      --如果指定子串为<code>null</code>，则返回原字符串。
     *
     * <pre>
     * StringUtils.replace(null, *, *, *)         = null
     * StringUtils.replace("", *, *, *)           = ""
     * StringUtils.replace("abaa", null, null, 1) = "abaa"
     * StringUtils.replace("abaa", null, null, 1) = "abaa"
     * StringUtils.replace("abaa", "a", null, 1)  = "abaa"
     * StringUtils.replace("abaa", "a", "", 1)    = "baa"
     * StringUtils.replace("abaa", "a", "z", 0)   = "abaa"
     * StringUtils.replace("abaa", "a", "z", 1)   = "zbaa"
     * StringUtils.replace("abaa", "a", "z", 2)   = "zbza"
     * StringUtils.replace("abaa", "a", "z", -1)  = "zbzz"
     * </pre>
     *
     * @param text 要扫描的字符串
     * @param repl 要搜索的子串
     * @param with 替换字符串
     * @param max maximum number of values to replace, or <code>-1</code> if no
     * maximum
     * @return 被替换后的字符串，如果原始字符串为<code>null</code>，则返回<code>null</code>
     */
    public static String replace(String text, String repl, String with, int max) {
        if ((text == null) || (repl == null) || (with == null) || (repl.length() == 0)
                || (max == 0)) {
            return text;
        }

        StringBuffer buf = new StringBuffer(text.length());
        int start = 0;
        int end = 0;

        while ((end = text.indexOf(repl, start)) != -1) {
            buf.append(text.substring(start, end)).append(with);
            start = end + repl.length();

            if (--max == 0) {
                break;
            }
        }

        buf.append(text.substring(start));
        return buf.toString();
    }

    /**
     * 将字符串中所有指定的字符，替换成另一个。
     *      --如果字符串为<code>null</code>则返回<code>null</code>。
     *
     * <pre>
     * StringUtils.replaceChars(null, *, *)        = null
     * StringUtils.replaceChars("", *, *)          = ""
     * StringUtils.replaceChars("abcba", 'b', 'y') = "aycya"
     * StringUtils.replaceChars("abcba", 'z', 'y') = "abcba"
     * </pre>
     *
     * @param str 要扫描的字符串
     * @param searchChar 要搜索的字符
     * @param replaceChar 替换字符
     * @return 被替换后的字符串，如果原始字符串为<code>null</code>，则返回<code>null</code>
     */
    public static String replaceChars(String str, char searchChar, char replaceChar) {
        if (str == null) {
            return null;
        }

        return str.replace(searchChar, replaceChar);
    }

    /**
     * 将字符串中所有指定的字符，替换成另一个。
     *      --如果字符串为<code>null</code>则返回<code>null</code>。
     *      --如果搜索字符串为<code>null</code>。或空，则返回原字符串。
     * 例如：
     * <code>replaceChars(&quot;hello&quot;, &quot;ho&quot;, &quot;jy&quot;) = jelly</code>
     * 。
     * 通常搜索字符串和替换字符串是等长的，如果搜索字符串比替换字符串长，则多余的字符将被删除。 如果搜索字符串比替换字符串短，则缺少的字符将被忽略。
     *
     * <pre>
     * StringUtils.replaceChars(null, *, *)           = null
     * StringUtils.replaceChars("", *, *)             = ""
     * StringUtils.replaceChars("abc", null, *)       = "abc"
     * StringUtils.replaceChars("abc", "", *)         = "abc"
     * StringUtils.replaceChars("abc", "b", null)     = "ac"
     * StringUtils.replaceChars("abc", "b", "")       = "ac"
     * StringUtils.replaceChars("abcba", "bc", "yz")  = "ayzya"
     * StringUtils.replaceChars("abcba", "bc", "y")   = "ayya"
     * StringUtils.replaceChars("abcba", "bc", "yzx") = "ayzya"
     * </pre>
     *
     * @param str 要扫描的字符串
     * @param searchChars 要搜索的字符串
     * @param replaceChars 替换字符串
     * @return 被替换后的字符串，如果原始字符串为<code>null</code>，则返回<code>null</code>
     */
    public static String replaceChars(String str, String searchChars, String replaceChars) {
        if ((str == null) || (str.length() == 0) || (searchChars == null)
                || (searchChars.length() == 0)) {
            return str;
        }

        char[] chars = str.toCharArray();
        int len = chars.length;
        boolean modified = false;

        for (int i = 0, isize = searchChars.length(); i < isize; i++) {
            char searchChar = searchChars.charAt(i);

            if ((replaceChars == null) || (i >= replaceChars.length())) {
                // 删除
                int pos = 0;

                for (int j = 0; j < len; j++) {
                    if (chars[j] != searchChar) {
                        chars[pos++] = chars[j];
                    } else {
                        modified = true;
                    }
                }

                len = pos;
            } else {
                // 替换
                for (int j = 0; j < len; j++) {
                    if (chars[j] == searchChar) {
                        chars[j] = replaceChars.charAt(i);
                        modified = true;
                    }
                }
            }
        }

        if (!modified) {
            return str;
        }

        return new String(chars, 0, len);
    }

    /**
     * 将指定的子串用另一指定子串覆盖。
     *      --如果字符串为<code>null</code>，则返回<code>null</code>。 负的索引值将被看作<code>0</code>
     * ，越界的索引值将被设置成字符串的长度相同的值。
     *
     * <pre>
     * StringUtils.overlay(null, *, *, *)            = null
     * StringUtils.overlay("", "abc", 0, 0)          = "abc"
     * StringUtils.overlay("abcdef", null, 2, 4)     = "abef"
     * StringUtils.overlay("abcdef", "", 2, 4)       = "abef"
     * StringUtils.overlay("abcdef", "", 4, 2)       = "abef"
     * StringUtils.overlay("abcdef", "zzzz", 2, 4)   = "abzzzzef"
     * StringUtils.overlay("abcdef", "zzzz", 4, 2)   = "abzzzzef"
     * StringUtils.overlay("abcdef", "zzzz", -1, 4)  = "zzzzef"
     * StringUtils.overlay("abcdef", "zzzz", 2, 8)   = "abzzzz"
     * StringUtils.overlay("abcdef", "zzzz", -2, -3) = "zzzzabcdef"
     * StringUtils.overlay("abcdef", "zzzz", 8, 10)  = "abcdefzzzz"
     * </pre>
     *
     * @param str 要扫描的字符串
     * @param overlay 用来覆盖的字符串
     * @param start 起始索引
     * @param end 结束索引
     * @return 被覆盖后的字符串，如果原始字符串为<code>null</code>，则返回<code>null</code>
     */
    public static String overlay(String str, String overlay, int start, int end) {
        if (str == null) {
            return null;
        }

        if (overlay == null) {
            overlay = EMPTY_STRING;
        }

        int len = str.length();

        if (start < 0) {
            start = 0;
        }

        if (start > len) {
            start = len;
        }

        if (end < 0) {
            end = 0;
        }

        if (end > len) {
            end = len;
        }

        if (start > end) {
            int temp = start;

            start = end;
            end = temp;
        }

        return new StringBuffer((len + start) - end + overlay.length() + 1)
                .append(str.substring(0, start)).append(overlay).append(str.substring(end)).toString();
    }


	/*
	 * ===============================
	 * 重复/对齐字符串
	 * ===============================
	 */

    /**
     * 将指定字符串重复n遍。
     *
     * <pre>
     * StringUtils.repeat(null, 2)   = null
     * StringUtils.repeat("", 0)     = ""
     * StringUtils.repeat("", 2)     = ""
     * StringUtils.repeat("a", 3)    = "aaa"
     * StringUtils.repeat("ab", 2)   = "abab"
     * StringUtils.repeat("abcd", 2) = "abcdabcd"
     * StringUtils.repeat("a", -2)   = ""
     * </pre>
     *
     * @param str 要重复的字符串
     * @param repeat 重复次数，如果小于<code>0</code>，则看作<code>0</code>
     * @return 重复n次的字符串，如果原始字符串为<code>null</code>，则返回<code>null</code>
     */
    public static String repeat(String str, int repeat) {
        if (str == null) {
            return null;
        }

        if (repeat <= 0) {
            return EMPTY_STRING;
        }

        int inputLength = str.length();

        if ((repeat == 1) || (inputLength == 0)) {
            return str;
        }

        int outputLength = inputLength * repeat;

        switch (inputLength) {
            case 1:

                char ch = str.charAt(0);
                char[] output1 = new char[outputLength];

                for (int i = repeat - 1; i >= 0; i--) {
                    output1[i] = ch;
                }

                return new String(output1);

            case 2:

                char ch0 = str.charAt(0);
                char ch1 = str.charAt(1);
                char[] output2 = new char[outputLength];

                for (int i = (repeat * 2) - 2; i >= 0; i--, i--) {
                    output2[i] = ch0;
                    output2[i + 1] = ch1;
                }

                return new String(output2);

            default:

                StringBuffer buf = new StringBuffer(outputLength);

                for (int i = 0; i < repeat; i++) {
                    buf.append(str);
                }

                return buf.toString();
        }
    }

    /**
     * 扩展并左对齐字符串，用空格<code>' '</code>填充右边。
     *
     * <pre>
     * StringUtils.alignLeft(null, *)   = null
     * StringUtils.alignLeft("", 3)     = "   "
     * StringUtils.alignLeft("bat", 3)  = "bat"
     * StringUtils.alignLeft("bat", 5)  = "bat  "
     * StringUtils.alignLeft("bat", 1)  = "bat"
     * StringUtils.alignLeft("bat", -1) = "bat"
     * </pre>
     *
     * @param str 要对齐的字符串
     * @param size 扩展字符串到指定宽度
     * @return 扩展后的字符串，如果字符串为<code>null</code>，则返回<code>null</code>
     */

    public static String alignLeft(String str, int size) {
        return alignLeft(str, size, ' ');
    }

    /**
     * 扩展并左对齐字符串，用指定字符填充右边。
     *
     * <pre>
     * StringUtils.alignLeft(null, *, *)     = null
     * StringUtils.alignLeft("", 3, 'z')     = "zzz"
     * StringUtils.alignLeft("bat", 3, 'z')  = "bat"
     * StringUtils.alignLeft("bat", 5, 'z')  = "batzz"
     * StringUtils.alignLeft("bat", 1, 'z')  = "bat"
     * StringUtils.alignLeft("bat", -1, 'z') = "bat"
     * </pre>
     *
     * @param str 要对齐的字符串
     * @param size 扩展字符串到指定宽度
     * @param padChar 填充字符
     * @return 扩展后的字符串，如果字符串为<code>null</code>，则返回<code>null</code>
     */
    public static String alignLeft(String str, int size, char padChar) {
        if (str == null) {
            return null;
        }

        int pads = size - str.length();

        if (pads <= 0) {
            return str;
        }

        return alignLeft(str, size, String.valueOf(padChar));
    }

    /**
     * 扩展并左对齐字符串，用指定字符串填充右边。
     *
     * <pre>
     * StringUtils.alignLeft(null, *, *)      = null
     * StringUtils.alignLeft("", 3, "z")      = "zzz"
     * StringUtils.alignLeft("bat", 3, "yz")  = "bat"
     * StringUtils.alignLeft("bat", 5, "yz")  = "batyz"
     * StringUtils.alignLeft("bat", 8, "yz")  = "batyzyzy"
     * StringUtils.alignLeft("bat", 1, "yz")  = "bat"
     * StringUtils.alignLeft("bat", -1, "yz") = "bat"
     * StringUtils.alignLeft("bat", 5, null)  = "bat  "
     * StringUtils.alignLeft("bat", 5, "")    = "bat  "
     * </pre>
     *
     * @param str 要对齐的字符串
     * @param size 扩展字符串到指定宽度
     * @param padStr 填充字符串
     * @return 扩展后的字符串，如果字符串为<code>null</code>，则返回<code>null</code>
     */
    public static String alignLeft(String str, int size, String padStr) {
        if (str == null) {
            return null;
        }

        if ((padStr == null) || (padStr.length() == 0)) {
            padStr = " ";
        }

        int padLen = padStr.length();
        int strLen = str.length();
        int pads = size - strLen;

        if (pads <= 0) {
            return str;
        }

        if (pads == padLen) {
            return str.concat(padStr);
        } else if (pads < padLen) {
            return str.concat(padStr.substring(0, pads));
        } else {
            char[] padding = new char[pads];
            char[] padChars = padStr.toCharArray();

            for (int i = 0; i < pads; i++) {
                padding[i] = padChars[i % padLen];
            }

            return str.concat(new String(padding));
        }
    }

    /**
     * 扩展并右对齐字符串，用空格<code>' '</code>填充左边。
     *
     * <pre>
     * StringUtils.alignRight(null, *)   = null
     * StringUtils.alignRight("", 3)     = "   "
     * StringUtils.alignRight("bat", 3)  = "bat"
     * StringUtils.alignRight("bat", 5)  = "  bat"
     * StringUtils.alignRight("bat", 1)  = "bat"
     * StringUtils.alignRight("bat", -1) = "bat"
     * </pre>
     *
     * @param str 要对齐的字符串
     * @param size 扩展字符串到指定宽度
     * @return 扩展后的字符串，如果字符串为<code>null</code>，则返回<code>null</code>
     */
    public static String alignRight(String str, int size) {
        return alignRight(str, size, ' ');
    }

    /**
     * 扩展并右对齐字符串，用指定字符填充左边。
     *
     * <pre>
     * StringUtils.alignRight(null, *, *)     = null
     * StringUtils.alignRight("", 3, 'z')     = "zzz"
     * StringUtils.alignRight("bat", 3, 'z')  = "bat"
     * StringUtils.alignRight("bat", 5, 'z')  = "zzbat"
     * StringUtils.alignRight("bat", 1, 'z')  = "bat"
     * StringUtils.alignRight("bat", -1, 'z') = "bat"
     * </pre>
     *
     * @param str 要对齐的字符串
     * @param size 扩展字符串到指定宽度
     * @param padChar 填充字符
     * @return 扩展后的字符串，如果字符串为<code>null</code>，则返回<code>null</code>
     */
    public static String alignRight(String str, int size, char padChar) {
        if (str == null) {
            return null;
        }

        int pads = size - str.length();

        if (pads <= 0) {
            return str;
        }

        return alignRight(str, size, String.valueOf(padChar));
    }

    /**
     * 扩展并右对齐字符串，用指定字符串填充左边。
     *
     * <pre>
     * StringUtils.alignRight(null, *, *)      = null
     * StringUtils.alignRight("", 3, "z")      = "zzz"
     * StringUtils.alignRight("bat", 3, "yz")  = "bat"
     * StringUtils.alignRight("bat", 5, "yz")  = "yzbat"
     * StringUtils.alignRight("bat", 8, "yz")  = "yzyzybat"
     * StringUtils.alignRight("bat", 1, "yz")  = "bat"
     * StringUtils.alignRight("bat", -1, "yz") = "bat"
     * StringUtils.alignRight("bat", 5, null)  = "  bat"
     * StringUtils.alignRight("bat", 5, "")    = "  bat"
     * </pre>
     *
     * @param str 要对齐的字符串
     * @param size 扩展字符串到指定宽度
     * @param padStr 填充字符串
     * @return 扩展后的字符串，如果字符串为<code>null</code>，则返回<code>null</code>
     */
    public static String alignRight(String str, int size, String padStr) {
        if (str == null) {
            return null;
        }

        if ((padStr == null) || (padStr.length() == 0)) {
            padStr = " ";
        }

        int padLen = padStr.length();
        int strLen = str.length();
        int pads = size - strLen;

        if (pads <= 0) {
            return str;
        }

        if (pads == padLen) {
            return padStr.concat(str);
        } else if (pads < padLen) {
            return padStr.substring(0, pads).concat(str);
        } else {
            char[] padding = new char[pads];
            char[] padChars = padStr.toCharArray();

            for (int i = 0; i < pads; i++) {
                padding[i] = padChars[i % padLen];
            }

            return new String(padding).concat(str);
        }
    }

    /**
     * 扩展并居中字符串，用空格<code>' '</code>填充两边。
     *
     * <pre>
     * StringUtils.center(null, *)   = null
     * StringUtils.center("", 4)     = "    "
     * StringUtils.center("ab", -1)  = "ab"
     * StringUtils.center("ab", 4)   = " ab "
     * StringUtils.center("abcd", 2) = "abcd"
     * StringUtils.center("a", 4)    = " a  "
     * </pre>
     *
     * @param str 要对齐的字符串
     * @param size 扩展字符串到指定宽度
     * @return 扩展后的字符串，如果字符串为<code>null</code>，则返回<code>null</code>
     */
    public static String center(String str, int size) {
        return center(str, size, ' ');
    }

    /**
     * 扩展并居中字符串，用指定字符填充两边。
     *
     * <pre>
     * StringUtils.center(null, *, *)     = null
     * StringUtils.center("", 4, ' ')     = "    "
     * StringUtils.center("ab", -1, ' ')  = "ab"
     * StringUtils.center("ab", 4, ' ')   = " ab "
     * StringUtils.center("abcd", 2, ' ') = "abcd"
     * StringUtils.center("a", 4, ' ')    = " a  "
     * StringUtils.center("a", 4, 'y')    = "yayy"
     * </pre>
     *
     * @param str 要对齐的字符串
     * @param size 扩展字符串到指定宽度
     * @param padChar 填充字符
     * @return 扩展后的字符串，如果字符串为<code>null</code>，则返回<code>null</code>
     */
    public static String center(String str, int size, char padChar) {
        if ((str == null) || (size <= 0)) {
            return str;
        }

        int strLen = str.length();
        int pads = size - strLen;

        if (pads <= 0) {
            return str;
        }

        str = alignRight(str, strLen + (pads / 2), padChar);
        str = alignLeft(str, size, padChar);
        return str;
    }

    /**
     * 扩展并居中字符串，用指定字符串填充两边。
     *
     * <pre>
     * StringUtils.center(null, *, *)     = null
     * StringUtils.center("", 4, " ")     = "    "
     * StringUtils.center("ab", -1, " ")  = "ab"
     * StringUtils.center("ab", 4, " ")   = " ab "
     * StringUtils.center("abcd", 2, " ") = "abcd"
     * StringUtils.center("a", 4, " ")    = " a  "
     * StringUtils.center("a", 4, "yz")   = "yayz"
     * StringUtils.center("abc", 7, null) = "  abc  "
     * StringUtils.center("abc", 7, "")   = "  abc  "
     * </pre>
     *
     * @param str 要对齐的字符串
     * @param size 扩展字符串到指定宽度
     * @param padStr 填充字符串
     * @return 扩展后的字符串，如果字符串为<code>null</code>，则返回<code>null</code>
     */
    public static String center(String str, int size, String padStr) {
        if ((str == null) || (size <= 0)) {
            return str;
        }

        if ((padStr == null) || (padStr.length() == 0)) {
            padStr = " ";
        }

        int strLen = str.length();
        int pads = size - strLen;

        if (pads <= 0) {
            return str;
        }

        str = alignRight(str, strLen + (pads / 2), padStr);
        str = alignLeft(str, size, padStr);
        return str;
    }

    /**
	 * ===========================
	 * 反转字符串
	 * ===========================
	 */

    /**
     * 反转字符串中的字符顺序。
     * 如果字符串为<code>null</code>，则返回<code>null</code>。
     *
     * <pre>
     * StringUtils.reverse(null)  = null
     * StringUtils.reverse("")    = ""
     * StringUtils.reverse("bat") = "tab"
     * </pre>
     *
     * @param str 要反转的字符串
     * @return 反转后的字符串，如果原字符串为<code>null</code>，则返回<code>null</code>
     */
    public static String reverse(String str) {
        if ((str == null) || (str.length() == 0)) {
            return str;
        }

        return new StringBuffer(str).reverse().toString();
    }

    /**
     * 反转指定分隔符分隔的各子串的顺序。
     * 如果字符串为<code>null</code>，则返回<code>null</code>。
     *
     * <pre>
     * StringUtils.reverseDelimited(null, *)      = null
     * StringUtils.reverseDelimited("", *)        = ""
     * StringUtils.reverseDelimited("a.b.c", 'x') = "a.b.c"
     * StringUtils.reverseDelimited("a.b.c", '.') = "c.b.a"
     * </pre>
     *
     * @param str 要反转的字符串
     * @param separatorChar 分隔符
     * @return 反转后的字符串，如果原字符串为<code>null</code>，则返回<code>null</code>
     */
    public static String reverseDelimited(String str, char separatorChar) {
        if (str == null) {
            return null;
        }

        String[] strs = split(str, separatorChar);

        ArrayUtils.reverse(strs);

        return join(strs, separatorChar);
    }

    /**
     * 反转指定分隔符分隔的各子串的顺序。
     *      --如果字符串为<code>null</code>，则返回<code>null</code>。
     *
     * <pre>
     * StringUtils.reverseDelimited(null, *, *)          = null
     * StringUtils.reverseDelimited("", *, *)            = ""
     * StringUtils.reverseDelimited("a.b.c", null, null) = "a.b.c"
     * StringUtils.reverseDelimited("a.b.c", "", null)   = "a.b.c"
     * StringUtils.reverseDelimited("a.b.c", ".", ",")   = "c,b,a"
     * StringUtils.reverseDelimited("a.b.c", ".", null)  = "c b a"
     * </pre>
     *
     * @param str 要反转的字符串
     * @param separatorChars 分隔符，如果为<code>null</code>，则默认使用空白字符
     * @param separator 用来连接子串的分隔符，如果为<code>null</code>，默认使用空格
     * @return 反转后的字符串，如果原字符串为<code>null</code>，则返回<code>null</code>
     */
    public static String reverseDelimited(String str, String separatorChars, String separator) {
        if (str == null) {
            return null;
        }

        String[] strs = split(str, separatorChars);

        ArrayUtils.reverse(strs);

        if (separator == null) {
            return join(strs, ' ');
        }

        return join(strs, separator);
    }

    /*
	 * ==========================================================================
	 * 比较两个字符串的异同
	 *      --查找字符串之间的差异，比较字符串的相似度
	 * ==========================================================================
	 */

    /**
     * 比较两个字符串，取得第二个字符串中，和第一个字符串不同的部分。
     *
     * <pre>
     * StringUtils.difference("i am a machine", "i am a robot")  = "robot"
     * StringUtils.difference(null, null)                        = null
     * StringUtils.difference("", "")                            = ""
     * StringUtils.difference("", null)                          = ""
     * StringUtils.difference("", "abc")                         = "abc"
     * StringUtils.difference("abc", "")                         = ""
     * StringUtils.difference("abc", "abc")                      = ""
     * StringUtils.difference("ab", "abxyz")                     = "xyz"
     * StringUtils.difference("abcde", "abxyz")                  = "xyz"
     * StringUtils.difference("abcde", "xyz")                    = "xyz"
     * </pre>
     *
     * @param str1 字符串1
     * @param str2 字符串2
     * @return 第二个字符串中，和第一个字符串不同的部分。如果两个字符串相同，则返回空字符串<code>""</code>
     */
    public static String difference(String str1, String str2) {
        if (str1 == null) {
            return str2;
        }

        if (str2 == null) {
            return str1;
        }

        int index = indexOfDifference(str1, str2);

        if (index == -1) {
            return EMPTY_STRING;
        }

        return str2.substring(index);
    }

    /**
     * 比较两个字符串，取得两字符串开始不同的索引值。
     *
     * <pre>
     * StringUtils.indexOfDifference("i am a machine", "i am a robot")   = 7
     * StringUtils.indexOfDifference(null, null)                         = -1
     * StringUtils.indexOfDifference("", null)                           = -1
     * StringUtils.indexOfDifference("", "")                             = -1
     * StringUtils.indexOfDifference("", "abc")                          = 0
     * StringUtils.indexOfDifference("abc", "")                          = 0
     * StringUtils.indexOfDifference("abc", "abc")                       = -1
     * StringUtils.indexOfDifference("ab", "abxyz")                      = 2
     * StringUtils.indexOfDifference("abcde", "abxyz")                   = 2
     * StringUtils.indexOfDifference("abcde", "xyz")                     = 0
     * </pre>
     *
     * @param str1 字符串1
     * @param str2 字符串2
     * @return 两字符串开始产生差异的索引值，如果两字符串相同，则返回<code>-1</code>
     */
    public static int indexOfDifference(String str1, String str2) {
        if ((str1 == null) || (str2 == null) || (str1.equals(str2))) {
            return -1;
        }

        int i;

        for (i = 0; (i < str1.length()) && (i < str2.length()); ++i) {
            if (str1.charAt(i) != str2.charAt(i)) {
                break;
            }
        }

        if ((i < str2.length()) || (i < str1.length())) {
            return i;
        }

        return -1;
    }

    /**
     * 取得两个字符串的相似度，<code>0</code>代表字符串相等，数字越大表示字符串越不像。
     * 这个算法取自<a href="http://www.merriampark.com/ld.htm">http://www.merriampark.com/ld.htm</a>。
     * 它计算的是从字符串1转变到字符串2所需要的删除、插入和替换的步骤数。
     *
     * <pre>
     * StringUtils.getLevenshteinDistance(null, *)             = IllegalArgumentException
     * StringUtils.getLevenshteinDistance(*, null)             = IllegalArgumentException
     * StringUtils.getLevenshteinDistance("","")               = 0
     * StringUtils.getLevenshteinDistance("","a")              = 1
     * StringUtils.getLevenshteinDistance("aaapppp", "")       = 7
     * StringUtils.getLevenshteinDistance("frog", "fog")       = 1
     * StringUtils.getLevenshteinDistance("fly", "ant")        = 3
     * StringUtils.getLevenshteinDistance("elephant", "hippo") = 7
     * StringUtils.getLevenshteinDistance("hippo", "elephant") = 7
     * StringUtils.getLevenshteinDistance("hippo", "zzzzzzzz") = 8
     * StringUtils.getLevenshteinDistance("hello", "hallo")    = 1
     * </pre>
     *
     * @param s 第一个字符串，如果是<code>null</code>，则看作空字符串
     * @param t 第二个字符串，如果是<code>null</code>，则看作空字符串
     * @return 相似度值
     */
    public static int getLevenshteinDistance(String s, String t) {
        s = defaultIfNull(s);
        t = defaultIfNull(t);

        int[][] d; // matrix
        int n; // length of s
        int m; // length of t
        int i; // iterates through s
        int j; // iterates through t
        char s_i; // ith character of s
        char t_j; // jth character of t
        int cost; // cost

        // Step 1
        n = s.length();
        m = t.length();

        if (n == 0) {
            return m;
        }

        if (m == 0) {
            return n;
        }

        d = new int[n + 1][m + 1];

        // Step 2
        for (i = 0; i <= n; i++) {
            d[i][0] = i;
        }

        for (j = 0; j <= m; j++) {
            d[0][j] = j;
        }

        // Step 3
        for (i = 1; i <= n; i++) {
            s_i = s.charAt(i - 1);

            // Step 4
            for (j = 1; j <= m; j++) {
                t_j = t.charAt(j - 1);

                // Step 5
                if (s_i == t_j) {
                    cost = 0;
                } else {
                    cost = 1;
                }

                // Step 6
                d[i][j] = min(d[i - 1][j] + 1, d[i][j - 1] + 1, d[i - 1][j - 1] + cost);
            }
        }

        // Step 7
        return d[n][m];
    }

    /**
     * 取得最小数。
     *
     * @param a 整数1
     * @param b 整数2
     * @param c 整数3
     * @return 三个数中的最小值
     */
    private static int min(int a, int b, int c) {
        if (b < a) {
            a = b;
        }

        if (c < a) {
            a = c;
        }

        return a;
    }

    /**
     * 比较两个字符串（大小写敏感）。
     * <p/>
     *
     * <pre>
     * StringUtils.equals(null, null)   = true
     * StringUtils.equals(null, "abc")  = false
     * StringUtils.equals("abc", null)  = false
     * StringUtils.equals("abc", "abc") = true
     * StringUtils.equals("abc", "ABC") = false
     * </pre>
     *
     * @param str1 要比较的字符串1
     * @param str2 要比较的字符串2
     * @return 如果两个字符串相同，或者都是<code>null</code>，则返回<code>true</code>
     */
    public static boolean equals(String str1, String str2) {
        if (str1 == null) {
            return str2 == null;
        }

        return str1.equals(str2);
    }

    /**
     * 比较两个字符串（大小写敏感）。
     *
     * <pre>
     * StringUtils.equals(null, null)   = false
     * StringUtils.equals(null, "abc")  = true
     * StringUtils.equals("abc", null)  = true
     * StringUtils.equals("abc", "abc") = false
     * StringUtils.equals("abc", "ABC") = true
     * </pre>
     *
     * @param str1 要比较的字符串1
     * @param str2 要比较的字符串2
     * @return 如果两个字符串不相同，返回<code>true</code>
     */
    public static boolean notEquals(String str1, String str2) {
        if (str1 == null) {
            return str2 != null;
        }

        return !str1.equals(str2);
    }

    /**
     * 统计字符串出现个数
     *
     * @param origin 原始字符串
     * @param ch 比较的字符
     * @return 如果统计的字符为空(即"", 不是指空格)返回-1；
     */

    public static int getNum(String origin, String ch) {
        int chLength = ch.length();
        if (origin.length() == 0)
            return 0;
        if (chLength == 0)
            return -1; //如果统计的字符为空(即"",不是指空格)返回-1；
        int index = 0;
        int count = 0;
        while (origin.indexOf(ch, index) != -1) {
            ++count;
            index = origin.indexOf(ch, index) + chLength;
        }
        return count;
    }

    /**
	 * ==================================
	 * 判空函数。
	 *
	 * 以下方法用来判定一个字符串是否为：
	 * 1. null
	 * 2. empty - ""
	 * 3. blank - "全部是空白" - 空白由Character.isWhitespace所定义
	 *
	 * ===================================
	 *
	 */

    /**
     * 检查字符串是否为<code>null</code>或空字符串<code>""</code>。
     *
     * <pre>
     * StringUtils.isEmpty(null)      = true
     * StringUtils.isEmpty("")        = true
     * StringUtils.isEmpty(" ")       = false
     * StringUtils.isEmpty("bob")     = false
     * StringUtils.isEmpty("  bob  ") = false
     * </pre>
     *
     * @param str 要检查的字符串
     * @return 如果为空, 则返回<code>true</code>
     */
    public static boolean isEmpty(String str) {
        return ((str == null) || (str.length() == 0));
    }

    /**
     * 检查字符串是否是手机号码
     *
     * @param mobile
     * @return
     */
    public static boolean isMobile(String mobile) {
        return MobileNoPredicate.INSTANCE.apply(mobile);
    }

    /**
     * 检查字符串是否是邮件地址
     *
     * @param address
     * @return
     */
    public static boolean isEmail(String address) {
        if (StringUtils.isBlank(address)) {
            return false;
        }
        return EmailPredicate.INSTANCE.apply(address);
    }

    /**
     * 台胞证格式校验。
     * <ul>
     * <li>台胞证由8位以上字符组成</li>
     * </ul>
     *
     * @param taiwanPassport
     * @return 符合条件, 返回<code>true</code>
     */
    public static boolean isTaiwanPassport(String taiwanPassport) {
        if (StringUtils.isBlank(taiwanPassport)) {
            return false;
        }
        return PassportTaiwanPredicate.INSTANCE.apply(taiwanPassport);
    }

    /**
     * 回乡证格式校验。
     * <ul>
     * <li>回乡证支持首字母为M或H开头，后面字符均为数字</li>
     * </ul>
     *
     * @param homeReturnPassport
     * @return 符合条件, 返回<code>true</code>
     */
    public static boolean isHomeReturnPassport(String homeReturnPassport) {
        if (StringUtils.isBlank(homeReturnPassport)) {
            return false;
        }
        return PassportHomeReturnPredicate.INSTANCE.apply(homeReturnPassport);
    }

    /**
     * 护照格式校验。
     * <ul>
     * <li>护照支持字母和数字</li>
     * </ul>
     *
     * @param passport
     * @return 符合条件, 返回<code>true</code>
     */
    public static boolean isPassport(String passport) {
        if (StringUtils.isBlank(passport)) {
            return false;
        }
        return PassportPredicate.INSTANCE.apply(passport);
    }

    /**
     * 身份证格式校验。 支持15位和18位。
     *
     * @param certNo
     * @return 符合条件, 返回<code>true</code>
     */
    public static boolean isCertNo(String certNo) {
        if (StringUtils.isBlank(certNo)) {
            return false;
        }
        return CertNoPredicate.INSTANCE.apply(certNo);
    }

    /**
     * 营业执照号格式验证。  营业执照格式:前后不能有空格，满足数字、字母、中文、长度不限
     *
     * @param businessNo
     * @return 符合条件, 返回<code>true</code>
     */
    public static boolean isBusinessNo(String businessNo) {
        if (StringUtils.isBlank(businessNo)) {
            return false;
        }
        return BusinessNoPredicate.INSTANCE.apply(businessNo);
    }

    /**
     * 组织结构代码格式校验。 组织机构代码格式：9位数字或字母组成，且最后一位必须是数字或字母
     *
     * @param organizationCode
     * @return 符合条件, 返回<code>true</code>
     */
    public static boolean isOrganizationCode(String organizationCode) {
        if (StringUtils.isBlank(organizationCode)) {
            return false;
        }
        return OrganizationCodePredicate.INSTANCE.apply(organizationCode);
    }

    /**
     * 经营范围格式校验。
     * <ul>
     * <li>不支持全数字</li>
     * <li>不支持全字母</li>
     * <li>不支持全符号</li>
     * <li>不能包含"无"字样</li>
     * </ul>
     *
     * @param businessScope
     * @return 符合条件, 返回<code>true</code>
     */
    public static boolean isBusinessScope(String businessScope) {
        if (StringUtils.isBlank(businessScope)) {
            return false;
        }
        return BusinessScopePredicate.INSTANCE.apply(businessScope);
    }

    /**
     * 地址格式校验。
     * <ul>
     * <li>不支持全数字</li>
     * <li>不支持非字符串（•~！@$%^<>?&*）字符</li>
     * <li>不支持相同字符(如"哈哈哈")</li>
     * </ul>
     *
     * @param address
     * @return 符合条件, 返回<code>true</code>
     */
    public static boolean isAddress(String address) {
        if (StringUtils.isBlank(address)) {
            return false;
        }
        return AddressPredicate.INSTANCE.apply(address);
    }

    /**
     * 企业名字格式校验。
     * <ul>
     * <li>不支持全数字</li>
     * <li>不支持邮箱格式的名称</li>
     * <li>不支持名称里面包含"测试"、"反共产党"、"验证"</li>
     * <li>不支持非字符串（•~！@$%^<>?&*）字符</li>
     * <li>不支持相同字符(如"哈哈哈哈")</li>
     * </ul>
     *
     * @param enterpriseRealName
     * @return 符合条件, 返回<code>true</code>
     */
    public static boolean isEnterpriseRealName(String enterpriseRealName) {
        if (StringUtils.isBlank(enterpriseRealName)) {
            return false;
        }
        return EnterpriseRealNamePredicate.INSTANCE.apply(enterpriseRealName);
    }

    /**
     * 个人名字格式校验。
     * <ul>
     * <li>不支持全数字</li>
     * <li>不支持邮箱格式的名称</li>
     * <li>不支持名称里面包含"测试"、"验证"</li>
     * <li>不支持非字符串（•~！@$%^<>?&*）字符</li>
     * <li>不支持相同字符(如"哈哈哈哈")</li>
     * <li>当姓名为纯中文时，校验前、中、后空格，反之则校验前、后空格</li>
     * <li>不能包含"公司"字样</li>
     * </ul>
     *
     * @param personRealName
     * @return 符合条件, 返回<code>true</code>
     */
    public static boolean isPersonRealName(String personRealName) {
        if (StringUtils.isBlank(personRealName)) {
            return false;
        }
        return PersonRealNamePredicate.INSTANCE.apply(personRealName);
    }

    /**
     * 检查字符串是否不是<code>null</code>和空字符串<code>""</code>。
     * <p/>
     *
     * <pre>
     * StringUtils.isNotEmpty(null)      = false
     * StringUtils.isNotEmpty("")        = false
     * StringUtils.isNotEmpty(" ")       = true
     * StringUtils.isNotEmpty("bob")     = true
     * StringUtils.isNotEmpty("  bob  ") = true
     * </pre>
     *
     * @param str 要检查的字符串
     * @return 如果不为空, 则返回<code>true</code>
     */
    public static boolean isNotEmpty(String str) {
        return ((str != null) && (str.length() > 0));
    }

    /**
     * 检查字符串是否是空白：<code>null</code>、空字符串<code>""</code>或只有空白字符。
     * <p/>
     *
     * <pre>
     * StringUtils.isBlank(null)      = true
     * StringUtils.isBlank("")        = true
     * StringUtils.isBlank(" ")       = true
     * StringUtils.isBlank("bob")     = false
     * StringUtils.isBlank("  bob  ") = false
     * </pre>
     *
     * @param str 要检查的字符串
     * @return 如果为空白, 则返回<code>true</code>
     */
    public static boolean isBlank(String str) {
        int length;

        if ((str == null) || ((length = str.length()) == 0)) {
            return true;
        }

        for (int i = 0; i < length; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    /**
     * 检查字符串是否不是空白：<code>null</code>、空字符串<code>""</code>或只有空白字符。
     * <p/>
     *
     * <pre>
     * StringUtils.isNotBlank(null)      = false
     * StringUtils.isNotBlank("")        = false
     * StringUtils.isNotBlank(" ")       = false
     * StringUtils.isNotBlank("bob")     = true
     * StringUtils.isNotBlank("  bob  ") = true
     * </pre>
     *
     * @param str 要检查的字符串
     * @return 如果为空白, 则返回<code>true</code>
     */
    public static boolean isNotBlank(String str) {
        int length;

        if ((str == null) || ((length = str.length()) == 0)) {
            return false;
        }

        for (int i = 0; i < length; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return true;
            }
        }

        return false;
    }

    /**
     * 参数中有一个参数为null或者空白，返回true
     *
     * @param str
     * @return
     */
    public static boolean hasBlank(String... str) {
        if (str == null) {
            return true;
        }
        for (int i = 0; i < str.length; i++) {
            if (isBlank(str[i])) {
                return true;
            }
        }
        return false;
    }

    /**
     * 校验字符串中是否存在空格字符。 入参为空或者格式匹配或者是空串，返回true
     *
     * @param str
     * @return
     */
    public static boolean hasBlankInString(String str) {

        return BlankInStringPredicate.INSTANCE.apply(str);
    }

    /**
     * 校验字符串中是否存在前后空格字符。 入参为空或者格式匹配或者是空串，返回true
     *
     * @param str
     * @return
     */
    public static boolean hasPreOrSuffBlankInString(String str) {

        return BlankInStringPreOrSuffPredicate.INSTANCE.apply(str);
    }

    /**
	 * ==========================================================================
	 * 默认值函数。
	 * 当字符串为null、empty或blank时，将字符串转换成指定的默认字符串
	 *
	 * ==========================================================================
	 */

    /**
     * 如果字符串是<code>null</code>，则返回空字符串<code>""</code>，否则返回字符串本身。
     *
     * <pre>
     * StringUtils.defaultIfNull(null)  = ""
     * StringUtils.defaultIfNull("")    = ""
     * StringUtils.defaultIfNull("  ")  = "  "
     * StringUtils.defaultIfNull("bat") = "bat"
     * </pre>
     *
     * @param str 要转换的字符串
     * @return 字符串本身或空字符串<code>""</code>
     */
    public static String defaultIfNull(String str) {
        return (str == null) ? EMPTY_STRING : str;
    }

    /**
     * 如果字符串是<code>null</code>，则返回指定默认字符串，否则返回字符串本身。
     *
     * <pre>
     * StringUtils.defaultIfNull(null, "default")  = "default"
     * StringUtils.defaultIfNull("", "default")    = ""
     * StringUtils.defaultIfNull("  ", "default")  = "  "
     * StringUtils.defaultIfNull("bat", "default") = "bat"
     * </pre>
     *
     * @param str 要转换的字符串
     * @param defaultStr 默认字符串
     * @return 字符串本身或指定的默认字符串
     */
    public static String defaultIfNull(String str, String defaultStr) {
        return (str == null) ? defaultStr : str;
    }

    /**
     * 如果字符串是<code>null</code>或空字符串<code>""</code>，则返回空字符串<code>""</code>
     * ，否则返回字符串本身。
     * 此方法实际上和<code>defaultIfNull(String)</code>等效。
     *
     * <pre>
     * StringUtils.defaultIfEmpty(null)  = ""
     * StringUtils.defaultIfEmpty("")    = ""
     * StringUtils.defaultIfEmpty("  ")  = "  "
     * StringUtils.defaultIfEmpty("bat") = "bat"
     * </pre>
     *
     * @param str 要转换的字符串
     * @return 字符串本身或空字符串<code>""</code>
     */
    public static String defaultIfEmpty(String str) {
        return (str == null) ? EMPTY_STRING : str;
    }

    /**
     * 如果字符串是<code>null</code>或空字符串<code>""</code>，则返回指定默认字符串，否则返回字符串本身。
     *
     * <pre>
     * StringUtils.defaultIfEmpty(null, "default")  = "default"
     * StringUtils.defaultIfEmpty("", "default")    = "default"
     * StringUtils.defaultIfEmpty("  ", "default")  = "  "
     * StringUtils.defaultIfEmpty("bat", "default") = "bat"
     * </pre>
     *
     * @param str 要转换的字符串
     * @param defaultStr 默认字符串
     * @return 字符串本身或指定的默认字符串
     */
    public static String defaultIfEmpty(String str, String defaultStr) {
        return ((str == null) || (str.length() == 0)) ? defaultStr : str;
    }

    /**
     * 如果字符串是空白：<code>null</code>、空字符串<code>""</code>或只有空白字符，则返回空字符串
     * <code>""</code>，否则返回字符串本身。
     *
     * <pre>
     * StringUtils.defaultIfBlank(null)  = ""
     * StringUtils.defaultIfBlank("")    = ""
     * StringUtils.defaultIfBlank("  ")  = ""
     * StringUtils.defaultIfBlank("bat") = "bat"
     * </pre>
     *
     * @param str 要转换的字符串
     * @return 字符串本身或空字符串<code>""</code>
     */
    public static String defaultIfBlank(String str) {
        return isBlank(str) ? EMPTY_STRING : str;
    }

    /**
     * 如果字符串是<code>null</code>或空字符串<code>""</code>，则返回指定默认字符串，否则返回字符串本身。
     *
     * <pre>
     * StringUtils.defaultIfBlank(null, "default")  = "default"
     * StringUtils.defaultIfBlank("", "default")    = "default"
     * StringUtils.defaultIfBlank("  ", "default")  = "default"
     * StringUtils.defaultIfBlank("bat", "default") = "bat"
     * </pre>
     *
     * @param str 要转换的字符串
     * @param defaultStr 默认字符串
     * @return 字符串本身或指定的默认字符串
     */
    public static String defaultIfBlank(String str, String defaultStr) {
        return isBlank(str) ? defaultStr : str;
    }

}
