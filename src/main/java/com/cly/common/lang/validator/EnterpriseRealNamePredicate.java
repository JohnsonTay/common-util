/*
 * www.yiji.com Inc.
 * Copyright (c) 2014 All Rights Reserved
 */

/*
 * 修订记录:
 * linying@yiji.com 2016-05-05 09:59 创建
 *
 */
package com.cly.common.lang.validator;

import com.cly.common.util.StringUtils;
import com.google.common.base.Predicate;
import com.google.common.base.Strings;

import javax.annotation.concurrent.ThreadSafe;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 企业名字格式验证。
 * <ul>
 * <li>不支持全数字</li>
 * <li>不支持邮箱格式的名称</li>
 * <li>不支持名称里面包含"测试"、"反共产党"</li>
 * <li>不支持非字符串（~！@$%^<>?&*～＠￥％…＆×＾＊）字符</li>
 * <li>不支持相同字符(如"哈哈哈哈")</li>
 * </ul>
 *
 * @author linying@yiji.com
 */
@ThreadSafe
public class EnterpriseRealNamePredicate implements Predicate<String> {

    // \uFF10-\uFF19全角数字      \u6d4b\u8bd5测试     \u53cd\u5171\u4ea7\u515a反共产党
    public static final Pattern VALID_BUSINESS_SCOPE_REGEX = Pattern
            .compile("(^[\\d\uFF10-\uFF19]+$)|(^.*\u6d4b\u8bd5.*$)|(^.*\u53cd\u5171\u4ea7\u515a.*$)|([!\\$\\%\\^\\*~`！＄％＾＊～｀￥…×])");

    public static final EnterpriseRealNamePredicate INSTANCE = new EnterpriseRealNamePredicate();

    private EnterpriseRealNamePredicate() {

    }

    public boolean apply(String input) {
        if (Strings.isNullOrEmpty(input) || input.length() < 4) {
            return false;
        }

        boolean isEmail = false;
        Matcher matcher = VALID_BUSINESS_SCOPE_REGEX.matcher(input);
        Matcher continuousMatcher = PersonRealNamePredicate.VALID_CHAR_CONTINUOUS_REGEX
                .matcher(input);
        boolean hasPreOrSuffBlank = BlankInStringPreOrSuffPredicate.INSTANCE.apply(input);

        isEmail = StringUtils.isEmail(input);

        return !(matcher.find() || continuousMatcher.find() || isEmail || hasPreOrSuffBlank);
    }

}
