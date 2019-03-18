package com.tlkj.cod.action;

import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Desc
 *
 * @author sourcod
 * @version 1.0
 * @className loginTest
 * @date 2018/10/26 下午12:30
 */
public class loginTest {
    private static final String WEB_URL = "http://127.0.0.1:8080/action/action/b";

    @Test
    public void testServiceHelloSuccess() throws IOException, URISyntaxException {
        /*String username = "admin";
        String param11 = "param11";
        String param12 = "param12";
        String param2 = "param2";
        String key = "dadadswdewq2ewdwqdwadsadasd";
        JSONObject params = new JSONObject();
        params.put("username", username);
        params.put("param1", param11);
        params.put("param1", param12);
        params.put("param2", param2);
        params.put("digest", Jwt.createJavaWebToken(params));
        String url = UriComponentsBuilder.fromHttpUrl(WEB_URL).build().toUriString();
        // String result = RestTemplateUtils.get(WEB_URL, params);
        HttpClientUtil.HttpGet(WEB_URL + "?a=" + Jwt.createJavaWebToken(params), null);
        System.out.println(url);*/
    }

    /*@Test
    public void testServiceHelloFail() {
        String username = "admin";
        String param11 = "param11";
        String param12 = "param12";
        String param2 = "param2";
        String key = "dadadswdewq2ewdwqdwadsadasd";
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("username", username);
        params.add("param1", param11);
        params.add("param1", param12);
        params.add("param2", param2);
        params.add("digest", Jwt.createJavaWebToken(params));
        params.set("param2", param2 + "1");

        String url = UriComponentsBuilder
                .fromHttpUrl("http://localhost:8080/hello")
                .queryParams(params).build().toUriString();
    }*/

}
