package com.skyworth.okhttp.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import okhttp3.*;

import java.util.Iterator;
import java.util.Map;

/**
 * @ClassName OkHttpUtils
 * @Description OkHttpUtils
 * @Author sky
 * @Date 2023/5/12 8:55
 * @Version 1.0
 **/
@SuppressWarnings("all")
public class OkHttpUtils {

    private static final Logger log = LoggerFactory.getLogger(OkHttpUtils.class);

    /**
     * post 数据到url
     *
     * @param client     okhttpclient
     * @param url        请求路径
     * @param queries    query ,跟在 url后面 ？ 后的参数
     * @param jsonParams json 格式数据
     * @param headers    header数据
     * @return
     */
    public static String postJson(OkHttpClient client, String url, Map<String, String> queries, String jsonParams, Map<String, String> headers) {
        String responseBody = "";
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonParams);
        Request.Builder builder = new Request.Builder();
        builder = builder.url(url(url, queries));
        builder = builder.post(requestBody);
        if (headers != null) {
            for (String key : headers.keySet()) {
                builder = builder.addHeader(key, headers.get(key));
            }
        }
        Request request = builder.build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
            int status = response.code();
            if (response.isSuccessful()) {
                return response.body().string();
            }
        } catch (Exception e) {
            log.error("okhttp3 post error >> ex = {}", e.getStackTrace());
        } finally {
            if (response != null) {
                response.close();
            }
        }
        return responseBody;
    }

    private static String url(String url, Map<String, String> queries) {
        if (queries != null && queries.keySet().size() > 0) {
            StringBuilder sb = new StringBuilder(url);
            boolean firstFlag = true;
            Iterator iterator = queries.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry entry = (Map.Entry<String, String>) iterator.next();
                if (firstFlag) {
                    sb.append("?" + entry.getKey() + "=" + entry.getValue());
                    firstFlag = false;
                } else {
                    sb.append("&" + entry.getKey() + "=" + entry.getValue());
                }
            }
            return sb.toString();
        }
        return url;
    }

    /**
     * get
     *
     * @param url     请求的url
     * @param queries 请求的参数，在浏览器？后面的数据，没有可以传null
     * @return
     */
    public static String get(OkHttpClient okHttpClient, String url, Map<String, String> queries) {
        String responseBody = "";
        Request request = new Request.Builder()
                .url(url(url, queries))
                .build();
        Response response = null;
        try {
            response = okHttpClient.newCall(request).execute();
            int status = response.code();
            if (response.isSuccessful()) {
                return response.body().string();
            }
        } catch (Exception e) {
            log.error("okhttp3 put error >> ex = {}", e.getStackTrace());
        } finally {
            if (response != null) {
                response.close();
            }
        }
        return responseBody;
    }

    /**
     * post
     *
     * @param url    请求的url
     * @param params post form 提交的参数
     * @return
     */
    public static String postForm(String url, Map<String, String> params) {
        String responseBody = "";
        FormBody.Builder builder = new FormBody.Builder();
        //添加参数
        if (params != null && params.keySet().size() > 0) {
            for (String key : params.keySet()) {
                builder.add(key, params.get(key));
            }
        }
        Request request = new Request.Builder()
                .url(url)
                .post(builder.build())
                .build();
        Response response = null;
        try {
            OkHttpClient okHttpClient = new OkHttpClient();
            response = okHttpClient.newCall(request).execute();
            int status = response.code();
            if (response.isSuccessful()) {
                return response.body().string();
            }
        } catch (Exception e) {
            log.error("okhttp3 post error >> ex = {}", e.getStackTrace());
        } finally {
            if (response != null) {
                response.close();
            }
        }
        return responseBody;
    }


    /**
     * Post请求发送xml数据....
     * 参数一：请求Url
     * 参数二：请求的xmlString
     * 参数三：请求回调
     */
    public static String postXmlParams(String url, String xml) {
        String responseBody = "";
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/xml; charset=utf-8"), xml);
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        Response response = null;
        try {
            OkHttpClient okHttpClient = new OkHttpClient();
            response = okHttpClient.newCall(request).execute();
            int status = response.code();
            if (response.isSuccessful()) {
                return response.body().string();
            }
        } catch (Exception e) {
            log.error("okhttp3 post error >> ex = {}", e.getStackTrace());
        } finally {
            if (response != null) {
                response.close();
            }
        }
        return responseBody;
    }

}
