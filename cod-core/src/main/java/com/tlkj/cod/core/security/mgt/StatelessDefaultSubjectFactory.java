/*
 * Copyright (c) 2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.core.security.mgt;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.SubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSubjectFactory;

/**
 * Desc
 *
 * @author sourcod
 * @version 1.0
 * @className StatelessDefaultSubjectFactory
 * @date 2018/10/26 上午11:51
 */
public class StatelessDefaultSubjectFactory extends DefaultWebSubjectFactory {

    public StatelessDefaultSubjectFactory() {
        super();
    }

    @Override
    public Subject createSubject(SubjectContext context) {
        // 不创建session
        context.setSessionCreationEnabled(false);
        return super.createSubject(context);
    }



}
