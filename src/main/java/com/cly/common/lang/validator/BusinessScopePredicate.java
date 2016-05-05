/*
 * www.yiji.com Inc.
 * Copyright (c) 2014 All Rights Reserved
 */

/*
 * 修订记录:
 * linying@yiji.com 2016-05-05 09:56 创建
 *
 */
package com.cly.common.lang.validator;

import com.google.common.base.Predicate;
import com.google.common.base.Strings;

import javax.annotation.concurrent.ThreadSafe;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 经营范围格式验证。
 * <ul>
 * <li>不支持全数字</li>
 * <li>不支持全字母</li>
 * <li>不支持全符号</li>
 * <li>不能包含"无"字样</li>
 * </ul>
 *
 * @author linying@yiji.com
 */
@ThreadSafe
public class BusinessScopePredicate implements Predicate<String> {

    // \uFF10-\uFF19 全角 0-9    \uFF21-\uFF3A全角A-Z   \uFF41-\uFF5A全角a-z   \u65e0无
    public static final Pattern VALID_BUSINESS_SCOPE_REGEX = Pattern
            .compile("(^[\\d\uFF10-\uFF19]+$)|(^[A-Za-z\uFF21-\uFF3A\uFF41-\uFF5A]+$)|([!\\$\\%\\^*<>\\?\\~`！＄％＾＊＜＞？～｀￥…×])|(^[\u65e0]+$)");

    public static final BusinessScopePredicate INSTANCE = new BusinessScopePredicate();

    private BusinessScopePredicate() {

    }

    public boolean apply(String input) {
        if (Strings.isNullOrEmpty(input)) {
            return false;
        }
        Matcher matcher = VALID_BUSINESS_SCOPE_REGEX.matcher(input);
        Matcher continuousMatcher = PersonRealNamePredicate.VALID_CHAR_CONTINUOUS_REGEX
                .matcher(input);
        boolean hasPreOrSuffBlank = BlankInStringPreOrSuffPredicate.INSTANCE.apply(input);
        return !(matcher.find() || continuousMatcher.find() || hasPreOrSuffBlank);
    }

}
