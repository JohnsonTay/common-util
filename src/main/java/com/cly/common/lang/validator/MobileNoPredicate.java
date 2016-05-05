/*
 * www.wmjia.com Inc.
 * Copyright (c) 2014 All Rights Reserved
 */

/*
 * 修订记录:
 * zjzj_547@outlook.com 2016-05-05 09:16 创建
 *
 */
package com.cly.common.lang.validator;

import com.google.common.base.Predicate;
import com.google.common.base.Strings;

import javax.annotation.concurrent.ThreadSafe;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author zjzj_547@oublook.com
 */
@ThreadSafe
public class MobileNoPredicate implements Predicate<String> {

    public static final Pattern VALID_MOBILE_NUMBER_REGEX = Pattern.compile(
            "^(13[0-9]|15[0-9]|17[01678]|18[0-9]|14[57])[0-9]{8}$", Pattern.CASE_INSENSITIVE);

    public static final MobileNoPredicate INSTANCE = new MobileNoPredicate();

    private MobileNoPredicate() {

    }

    public boolean apply(String input) {
        if (Strings.isNullOrEmpty(input)) {
            return false;
        }
        Matcher matcher = VALID_MOBILE_NUMBER_REGEX.matcher(input);
        return matcher.find();
    }
}
