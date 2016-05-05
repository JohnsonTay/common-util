/*
 * www.wmjia.com Inc.
 * Copyright (c) 2014 All Rights Reserved
 */

/*
 * 修订记录:
 * zjzj_547@oublook.com 2016-05-05 09:41 创建
 *
 */
package com.cly.common.lang.validator;

import com.google.common.base.Predicate;
import com.google.common.base.Strings;
import org.apache.commons.validator.routines.EmailValidator;

import javax.annotation.concurrent.ThreadSafe;

/**
 * @author zjzj_547@oublook.com
 */
@ThreadSafe
public class EmailPredicate implements Predicate<String> {

    public static final EmailPredicate INSTANCE = new EmailPredicate();

    private EmailPredicate() {
    }

    private EmailValidator emailValidator = EmailValidator.getInstance(false);

    public boolean apply(String input) {
        return !Strings.isNullOrEmpty(input) && emailValidator.isValid(input);
    }
}

