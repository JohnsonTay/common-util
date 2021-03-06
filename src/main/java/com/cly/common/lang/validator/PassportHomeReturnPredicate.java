/*
 * www.yiji.com Inc.
 * Copyright (c) 2014 All Rights Reserved
 */

/*
 * 修订记录:
 * linying@yiji.com 2016-05-05 09:47 创建
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
public class PassportHomeReturnPredicate implements Predicate<String> {

    public static final Pattern VALID_PASSPORT_HOME_RETURN_REGEX = Pattern.compile("^[MH][\\d]+$");

    public static final PassportHomeReturnPredicate INSTANCE = new PassportHomeReturnPredicate();

    private PassportHomeReturnPredicate() {

    }

    public boolean apply(String input) {
        if (Strings.isNullOrEmpty(input)) {
            return false;
        }
        Matcher matcher = VALID_PASSPORT_HOME_RETURN_REGEX.matcher(input);
        return matcher.find();
    }
}
