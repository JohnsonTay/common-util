/*
 * www.yiji.com Inc.
 * Copyright (c) 2014 All Rights Reserved
 */

/*
 * 修订记录:
 * linying@yiji.com 2016-05-05 09:50 创建
 *
 */
package com.cly.common.lang.validator;

import com.google.common.base.Predicate;
import com.google.common.base.Strings;

import javax.annotation.concurrent.ThreadSafe;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 组织机构代码格式验证。 组织机构代码格式：9位数字或字母组成，且最后一位必须是数字或字母
 * @author linying@yiji.com
 */
@ThreadSafe
public class OrganizationCodePredicate implements Predicate<String> {

    public static final Pattern VALID_ORGANIZATION_CODE_REGEX = Pattern
            .compile("^[A-Za-z\\d\\-]{8,}[A-Za-z\\d]$");

    public static final OrganizationCodePredicate INSTANCE = new OrganizationCodePredicate();

    private OrganizationCodePredicate() {

    }

    public boolean apply(String input) {
        if (Strings.isNullOrEmpty(input)) {
            return false;
        }
        Matcher matcher = VALID_ORGANIZATION_CODE_REGEX.matcher(input);
        return matcher.find();
    }

}
