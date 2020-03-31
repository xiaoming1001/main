package com.study.shiwu.Test;    /**
 * @author: wxs
 * @date: 2020/3/31
 */

import com.study.shiwu.util.MailUtils;

/**
 * @author zm
 * @date 2020/3/31 17:27
 */
public class MailTest {
    public static void main(String[] args) throws Exception {
        String str="xiaoming969064361@163.com";
        String vode="我有一致小毛驴，我从来也不骑";
        MailUtils.sendMail(str,vode);
    }
}
