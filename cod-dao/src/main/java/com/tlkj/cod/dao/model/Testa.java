package com.tlkj.cod.dao.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Desc
 *
 * @author sourcod
 * @version 1.0
 * @className Testa
 * @date 2019/5/30 4:28 PM
 */
@Component
public class Testa {

    @Value("${asd:lll}")
    private String url;

    @Value("qwe:000")
    private String name;
}
