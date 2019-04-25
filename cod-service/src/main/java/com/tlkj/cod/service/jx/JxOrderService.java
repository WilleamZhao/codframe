/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.service.jx;

import com.tlkj.cod.model.common.SystemResponse;

/**
 * Desc
 *
 * @author sourcod
 * @version 1.0
 * @className JxOrderService
 * @date 2019/3/25 10:08 PM
 */
public interface JxOrderService {


    SystemResponse save();

    SystemResponse list();

    SystemResponse get();

    SystemResponse delete();

}
