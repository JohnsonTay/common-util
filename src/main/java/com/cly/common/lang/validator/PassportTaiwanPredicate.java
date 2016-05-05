/*
 * www.yiji.com Inc.
 * Copyright (c) 2014 All Rights Reserved
 */

/*
 * 修订记录:
 * linying@yiji.com 2016-05-05 09:46 创建
 *
 */
package com.cly.common.lang.validator;

import com.google.common.base.Predicate;
import com.google.common.base.Strings;

import javax.annotation.concurrent.ThreadSafe;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author linying@yiji.com
 */
@ThreadSafe
public class PassportTaiwanPredicate implements Predicate<String> {
    //[]中是全角空格
    public static final Pattern VALID_PASSPORT_TAIWAN_REGEX = Pattern
            .compile("^[^\\s　]{8,}$");

    public static final PassportTaiwanPredicate INSTANCE = new PassportTaiwanPredicate();

    private PassportTaiwanPredicate() {

    }

    public boolean apply(String input) {
        if (Strings.isNullOrEmpty(input)) {
            return false;
        }
        Matcher matcher = VALID_PASSPORT_TAIWAN_REGEX.matcher(input);

        return matcher.find();
    }
}