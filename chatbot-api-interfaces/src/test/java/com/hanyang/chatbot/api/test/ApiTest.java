package com.hanyang.chatbot.api.test;

import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;
/**
 * 单元测试
 */
public class ApiTest {

    @Test
    public void query_unanswered_questions() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpGet get = new HttpGet("https://api.zsxq.com/v2/groups/88888824855112/topics?scope=unanswered_questions&count=20");

        get.addHeader("cookie", "zsxq_access_token=F34C4C88-B2E0-8196-44CE-0199F3FAB37B_BE2EA811B7E4F416; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%22185455211581422%22%2C%22first_id%22%3A%2218ca48e258c591-0fa76f99bd8f348-26001951-1327104-18ca48e258e13b4%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E7%9B%B4%E6%8E%A5%E6%B5%81%E9%87%8F%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC_%E7%9B%B4%E6%8E%A5%E6%89%93%E5%BC%80%22%2C%22%24latest_referrer%22%3A%22%22%7D%2C%22identities%22%3A%22eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMThjYTQ4ZTI1OGM1OTEtMGZhNzZmOTliZDhmMzQ4LTI2MDAxOTUxLTEzMjcxMDQtMThjYTQ4ZTI1OGUxM2I0IiwiJGlkZW50aXR5X2xvZ2luX2lkIjoiMTg1NDU1MjExNTgxNDIyIn0%3D%22%2C%22history_login_id%22%3A%7B%22name%22%3A%22%24identity_login_id%22%2C%22value%22%3A%22185455211581422%22%7D%2C%22%24device_id%22%3A%2218ca48e258c591-0fa76f99bd8f348-26001951-1327104-18ca48e258e13b4%22%7D; abtest_env=product; zsxqsessionid=7d88eedefe1f23397a7cccd9165cde2b");
        get.addHeader("Content-Type", "application/json;charset=utf8");

        CloseableHttpResponse response = httpClient.execute(get);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
        } else {
            System.out.println(response.getStatusLine().getStatusCode());
        }
    }

    @Test
    public void answer() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost post = new HttpPost("https://api.zsxq.com/v2/topics/8855841154251812/answer");
        post.addHeader("cookie", "zsxq_access_token=F34C4C88-B2E0-8196-44CE-0199F3FAB37B_BE2EA811B7E4F416; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%22185455211581422%22%2C%22first_id%22%3A%2218ca48e258c591-0fa76f99bd8f348-26001951-1327104-18ca48e258e13b4%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E7%9B%B4%E6%8E%A5%E6%B5%81%E9%87%8F%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC_%E7%9B%B4%E6%8E%A5%E6%89%93%E5%BC%80%22%2C%22%24latest_referrer%22%3A%22%22%7D%2C%22identities%22%3A%22eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMThjYTQ4ZTI1OGM1OTEtMGZhNzZmOTliZDhmMzQ4LTI2MDAxOTUxLTEzMjcxMDQtMThjYTQ4ZTI1OGUxM2I0IiwiJGlkZW50aXR5X2xvZ2luX2lkIjoiMTg1NDU1MjExNTgxNDIyIn0%3D%22%2C%22history_login_id%22%3A%7B%22name%22%3A%22%24identity_login_id%22%2C%22value%22%3A%22185455211581422%22%7D%2C%22%24device_id%22%3A%2218ca48e258c591-0fa76f99bd8f348-26001951-1327104-18ca48e258e13b4%22%7D; abtest_env=product; zsxqsessionid=7d88eedefe1f23397a7cccd9165cde2b");
        post.addHeader("Content-Type", "application/json;charset=utf8");

        String paramJson = "{\n" +
                "  \"req_data\": {\n" +
                "    \"text\": \"自己去百度！\\n\",\n" +
                "    \"image_ids\": [],\n" +
                "    \"silenced\": false\n" +
                "  }\n" +
                "}";

        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("text/json", "UTF-8"));
        post.setEntity(stringEntity);

        CloseableHttpResponse response = httpClient.execute(post);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
        } else {
            System.out.println(response.getStatusLine().getStatusCode());
        }
    }
}
