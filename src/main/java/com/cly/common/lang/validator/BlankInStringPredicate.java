/*
 * www.yiji.com Inc.
 * Copyright (c) 2014 All Rights Reserved
 */

/*
 * 修订记录:
 * linying@yiji.com 2016-05-05 10:00 创建
 *
 */
package com.cly.common.lang.validator;

import com.google.common.base.Predicate;
import com.google.common.base.Strings;

import javax.annotation.concurrent.ThreadSafe;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 校验字符串中是否存在空格字符。 入参为空或者格式匹配，返回true
 *
 * @author linying@yiji.com
 */
@ThreadSafe
public class BlankInStringPredicate implements Predicate<String> {
    //[] 中是全角空格
    public static final Pattern VALID_BLANKINSTRING_REGEX = Pattern.compile("[\\s　]");

    public static final BlankInStringPredicate INSTANCE = new BlankInStringPredicate();

    private BlankInStringPredicate() {

    }

    public boolean apply(String input) {
        if (Strings.isNullOrEmpty(input)) {
            return true;
        }
        Matcher matcher = VALID_BLANKINSTRING_REGEX.matcher(input);
        return matcher.find();
    }
}
