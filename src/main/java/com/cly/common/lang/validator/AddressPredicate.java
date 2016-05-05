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

import com.google.common.base.Predicate;
import com.google.common.base.Strings;

import javax.annotation.concurrent.ThreadSafe;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 地址格式验证。
 * <ul>
 * <li>不支持全数字</li>
 * <li>不支持非字符串（~！@$%^<>?&*～＠￥％…＆×＾＊）字符</li>
 * <li>不支持相同字符(如"哈哈哈")</li>
 * </ul>
 *
 * @author linying@yiji.com
 */
@ThreadSafe
public class AddressPredicate implements Predicate<String> {

    //\uFF10-\uFF19 全角数字
    public static final Pattern VALID_ADDRESS_REGEX = Pattern
            .compile("(^[\\d\uFF10-\uFF19]+$)|([!\\$\\%\\^*<>\\?~`！＄％＾＊＜＞？～｀￥…《》×])");

    public static final AddressPredicate INSTANCE = new AddressPredicate();

    private AddressPredicate() {

    }

    public boolean apply(String input) {
        if (Strings.isNullOrEmpty(input)) {
            return false;
        }
        Matcher matcher = VALID_ADDRESS_REGEX.matcher(input);
        Matcher continuousMatcher = PersonRealNamePredicate.VALID_CHAR_CONTINUOUS_REGEX
                .matcher(input);
        boolean hasPreOrSuffBlank = BlankInStringPreOrSuffPredicate.INSTANCE.apply(input);

        return !(matcher.find() || continuousMatcher.find() || hasPreOrSuffBlank);
    }
}
