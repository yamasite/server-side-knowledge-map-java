package com.javadevmap.elasticsearch.rest;


import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.nio.entity.NStringEntity;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.Map;

/**
 * Elasticserach RestClient示例
 */
public class RestClientTest {
	private static RestClient restClient;

    /**
     * 以用户名和密码方式获取 restclient
     */
	public static RestClient getRestClientByUserNameAndPassword(){

        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY,
                new UsernamePasswordCredentials("elastic", "password"));

        return RestClient.builder(new HttpHost("39.106.208.144",
                9200,"http"))
                .setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
                    @Override
                    public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
                        return httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
                    }
                }).setMaxRetryTimeoutMillis(60000).build();

	}

	public static RestClient  getRestClient(){
        return  RestClient.builder(
                new HttpHost("39.106.208.144", 9200, "http")
        ).setRequestConfigCallback(
                new RestClientBuilder.RequestConfigCallback() {
                    @Override
                    public RequestConfig.Builder customizeRequestConfig(
                            RequestConfig.Builder requestConfigBuilder) {
                        return requestConfigBuilder
                                .setConnectTimeout(5000)
                                .setSocketTimeout(60000);
                    }
                }).setMaxRetryTimeoutMillis(60000).build();
    }

	@Before
	public void getRest(){
	    restClient=getRestClient();
	}

    /**
     * 获取当前ES 的信息
     * @throws Exception
     */
    @Test
    public void testEsInfo()throws  Exception{

        String endpoint = "/";
        Map<String, String> params = Collections.singletonMap("pretty", "true");
        Response response = restClient.performRequest("GET", endpoint,params);
        System.out.println(EntityUtils.toString(response.getEntity()));
    }

    /**
     * 删除索引
     * @throws Exception
     */
    @Test
    public void testDeleteIndex() throws Exception{
        String method = "DELETE";
        String endpoint = "/java-dev-map-rest";
        Response response = restClient.performRequest(method,endpoint);
        System.out.println(EntityUtils.toString(response.getEntity()));
    }

	/**
	 * 创建索引
	 * @throws Exception
	 */
	@Test
	public void testCreateIndex() throws Exception{
        String method = "PUT";
        String endpoint = "/java-dev-map-rest";

        HttpEntity entity = new NStringEntity(
                "{\n" +
                        "    \"settings\" : {\n" +
                        "        \"analysis\" : {\n" +
                        "            \"analyzer\" : {\n" +
                        "                \"ik\" : {\n" +
                        "                    \"tokenizer\" : \"ik_smart\"\n" +
                        "                }\n" +
                        "            }\n" +
                        "        }\n" +
                        "    },\n" +
                        "    \"mappings\" : {\n" +
                        "        \"news\" : {\n" +
                        "            \"dynamic\" : true,\n" +
                        "            \"properties\" : {\n" +
                        "                \"message\" : {\n" +
                        "                    \"type\" : \"string\",\n" +
                        "                    \"analyzer\" : \"ik_smart\"\n" +
                        "                }\n" +
                        "            }\n" +
                        "        }\n" +
                        "    }\n" +
                        "}", ContentType.APPLICATION_JSON);

        Response response = restClient.performRequest(method,endpoint,Collections.<String, String>emptyMap(),entity);
        System.out.println(EntityUtils.toString(response.getEntity()));
	}

	/**
	 * 创建文档 doc
	 * @throws Exception
	 */
	@Test
	public void testCreateDocument()throws Exception{

        Map<String, String> params = Collections.singletonMap("pretty", "true");

        String method = "PUT";
        String endpoint = "/java-dev-map-rest/news/1";
        HttpEntity entity = new NStringEntity(
                "{\"message\" : \"航拍西班牙田野艳丽美景 色彩柔美如缎带\" }", ContentType.APPLICATION_JSON);
        Response response = restClient.performRequest(method,endpoint,params,entity);
        System.out.println(EntityUtils.toString(response.getEntity()));
        endpoint = "/java-dev-map-rest/news/2";
        entity = new NStringEntity(
                "{\"message\" : \"无人机航拍：“天空之眼”\" }", ContentType.APPLICATION_JSON);
        response = restClient.performRequest(method,endpoint, params,entity);
        System.out.println(EntityUtils.toString(response.getEntity()));

	}

	/**
	 * 获取文档 doc
	 * @throws Exception
	 */
	@Test
	public void testDeleteDocument()throws Exception{

        // 1、先添加一条测试删除数据
        Map<String, String> params = Collections.singletonMap("pretty", "true");
        String method = "PUT";
        String endpoint = "/java-dev-map-rest/news/33";
        HttpEntity entity = new NStringEntity(
                "{\"message\" : \"3333航拍西班牙田野艳丽美景 色彩柔美如缎带\" }", ContentType.APPLICATION_JSON);
        Response response = restClient.performRequest(method,endpoint,params,entity);
        System.out.println("put result is "+EntityUtils.toString(response.getEntity()));
        //2、对数据进行删除操作
        method = "DELETE";
        endpoint = "/java-dev-map-rest/news/33";
        response = restClient.performRequest(method,endpoint,params);
        System.out.println("delete result is "+EntityUtils.toString(response.getEntity()));
	}
	/**
	 * 获取文档 doc
	 * @throws Exception
	 */
	@Test
	public void testGetDocument()throws Exception{
        String method = "GET";
        String endpoint = "/java-dev-map-rest/news/1";
        Map<String, String> params = Collections.singletonMap("pretty", "true");
        Response response = restClient.performRequest(method,endpoint,params);
        System.out.println(EntityUtils.toString(response.getEntity()));
	}

	/**
	 * 查询所有文件数据
	 * @throws Exception
	 */
    @Test
    public void testGetAllDocs() throws Exception {
        String method = "POST";
        String endpoint = "/java-dev-map-rest/news/_search";
        HttpEntity entity = new NStringEntity("{\n" +
                "  \"query\": {\n" +
                "    \"match_all\": {}\n" +
                "  }\n" +
                "}", ContentType.APPLICATION_JSON);
        Map<String, String> params = Collections.singletonMap("pretty", "true");
        Response response = restClient.performRequest(method,endpoint,params,entity);
        System.out.println(EntityUtils.toString(response.getEntity()));
    }

    /**
     * 根据ID获取
     * @throws Exception
     */
    @Test
    public void testGetDocByField() throws Exception {
        String method = "POST";
        String endpoint = "/java-dev-map-rest/news/_search";
        HttpEntity entity = new NStringEntity("{\n" +
                "  \"query\": {\n" +
                "    \"match\": {\n" +
                "      \"message\": \"无人机\"\n" +
                "    }\n" +
                "  }\n" +
                "}", ContentType.APPLICATION_JSON);
        Map<String, String> params = Collections.singletonMap("pretty", "true");
        Response response = restClient.performRequest(method,endpoint,params,entity);
        System.out.println(EntityUtils.toString(response.getEntity()));
    }

    /**
     * 更新数据
     * @throws Exception
     * doc 只对部分文档进行修改。使用doc可以实现简单的递归合并、内部合并、替换KV以及数组
     */
    @Test
    public void testUpdateDoc() throws Exception {
        String method = "POST";
        String endpoint = "/java-dev-map-rest/news/1/_update";
        HttpEntity entity = new NStringEntity("{\n" +
                "    \"doc\":{\n" +
                "        \"message\":\"航拍西班牙田野艳丽美景 色彩柔美如缎带 update\" \n" +
                "        \n" +
                "    }\n" +
                "}\n", ContentType.APPLICATION_JSON);

        Map<String, String> params = Collections.singletonMap("pretty", "true");
        Response response = restClient.performRequest(method,endpoint,params,entity);
        System.out.println(EntityUtils.toString(response.getEntity()));
    }

    /**
     * 查询 指定参数，并高亮
     * @throws Exception
     */
    @Test
    public void testGetDocsByParams() throws Exception {
        String method = "POST";
        String endpoint = "/java-dev-map-rest/news/_search?pretty";
        HttpEntity entity = new NStringEntity("{\n" +
                "    \"query\" : { \"match\" : { \"message\" : \"无人机\" }},\n" +
                "    \"highlight\" : {\n" +
                "        \"pre_tags\" : [\"<font color='red'>\"],\n" +
                "        \"post_tags\" : [\"</font>\"],\n" +
                "        \"fields\" : {\n" +
                "            \"message\" : {}\n" +
                "        }\n" +
                "    }\n" +
                "}", ContentType.APPLICATION_JSON);
        Response response = restClient.performRequest(method,endpoint,Collections.<String, String>emptyMap(),entity);
        System.out.println(EntityUtils.toString(response.getEntity()));
    }

}
