package com.example.other.utils;

import okhttp3.*;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author huangqi
 * @Package com.example.other
 * @Description: http请求工具
 * @date 2019-07-08 16:12
 */
public class HttpClientUtils {

    /**
     * 客户端配置
     */
    private static final OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build();


    /**
     * get请求
     *
     * @param url
     * @param param
     * @return
     */
    public static String get(String url, Map<String, String> param) {
        return get(url, param, null);
    }

    /**
     * get请求
     *
     * @param url
     * @param param
     * @param headers
     * @return
     */
    public static String get(String url, Map<String, String> param, Map<String, String> headers) {
        Request.Builder builder = new Request.Builder().get();
        HttpUrl.Builder urlBuilder = HttpUrl.parse(url)
                .newBuilder();
        //设置参数
        if (param != null) {
            Set<Map.Entry<String, String>> paramEntries = param.entrySet();
            paramEntries.forEach(p -> urlBuilder.addQueryParameter(p.getKey(), p.getValue()));

        }
        builder.url(urlBuilder.build());
        //设置请求头
        if (headers != null) {
            builder.headers(Headers.of(headers));
        }
        //发起请求
        Request request = builder.build();
        return getResp(request);
    }


    /**
     * post 提交String
     *
     * @param url
     * @param param
     * @return
     */
    public static String postString(String url, String param) {
        return postJson(url, param, null);
    }


    /**
     * post 提交String
     *
     * @param url
     * @param param
     * @param headers
     * @return
     */
    public static String postString(String url, String param, Map<String, String> headers) {
        MediaType mediaType = MediaType.get("text/x-markdown; charset=utf-8");
        RequestBody body = RequestBody.create(param, mediaType);
        Request.Builder requestBuilder = new Request.Builder().url(url).post(body);
        if (headers != null) {
            requestBuilder.headers(Headers.of(headers));
        }
        return getResp(requestBuilder.build());

    }


    /**
     * post json提交
     *
     * @param url
     * @param paramJson
     * @return
     */
    public static String postJson(String url, String paramJson) {
        return postJson(url, paramJson, null);
    }


    /**
     * post json提交
     *
     * @param url
     * @param paramJson
     * @param headers
     * @return
     */
    public static String postJson(String url, String paramJson, Map<String, String> headers) {
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(paramJson, mediaType);
        Request.Builder requestBuilder = new Request.Builder().url(url).post(body);
        if (headers != null) {
            requestBuilder.headers(Headers.of(headers));
        }
        return getResp(requestBuilder.build());

    }


    /**
     * post 表单提交
     *
     * @param url
     * @param params
     * @return
     */
    public static String postForm(String url, Map<String, String> params) {
        return postForm(url, params, null);
    }


    /**
     * post 表单提交
     *
     * @param url
     * @param params
     * @param headers
     * @return
     */
    public static String postForm(String url, Map<String, String> params, Map<String, String> headers) {
        FormBody.Builder formBody = new FormBody.Builder();
        if (params != null) {
            Set<Map.Entry<String, String>> paramEntries = params.entrySet();
            paramEntries.forEach(p -> formBody.add(p.getKey(), p.getValue()));
        }
        Request.Builder requestBuilder = new Request.Builder().url(url).post(formBody.build());
        if (headers != null) {
            requestBuilder.headers(Headers.of(headers));
        }
        return getResp(requestBuilder.build());

    }

    /**
     * post Multipart请求
     *
     * @param url           请求地址
     * @param fileParamName 文件参数名
     * @param fileName      文件全名
     * @param data          文件二进制数据
     * @param otherParams   附带其他的参数
     * @return
     */
    public static String postMultipart(String url, String fileParamName, String fileName, byte[] data,
                                       Map<String, String> otherParams) {
        return postMultipart(url, fileParamName, fileName, data, otherParams, null);
    }

    /**
     * post Multipart请求
     *
     * @param url           请求地址
     * @param fileParamName 文件参数名
     * @param fileName      文件全名
     * @param data          文件二进制数据
     * @param otherParams   附带其他的参数
     * @param headers       请求头
     * @return
     */
    public static String postMultipart(String url, String fileParamName, String fileName, byte[] data,
                                       Map<String, String> otherParams, Map<String, String> headers) {
        MultipartBody.Builder bodyBuilder = new MultipartBody.Builder();
        bodyBuilder.setType(MultipartBody.FORM);
        if (otherParams != null) {
            Set<Map.Entry<String, String>> paramEntries = otherParams.entrySet();
            paramEntries.forEach(p -> bodyBuilder.addFormDataPart(p.getKey(), p.getValue()));
        }
        bodyBuilder.addFormDataPart(fileParamName, fileName, RequestBody.create(data));
        Request.Builder requestBuilder = new Request.Builder().url(url).post(bodyBuilder.build());
        if (headers != null) {
            requestBuilder.headers(Headers.of(headers));
        }
        return getResp(requestBuilder.build());

    }

    /**
     * 读取响应
     *
     * @param request
     * @return
     * @throws IOException
     */
    private static String getResp(Request request) {
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws Exception {
	/*	String respGet = get("https://api.weibo.com/2/users/show.json", null);
		System.out.println(respGet);

		String jsonParam = "{" +
				"  \"productCode\":\"00000004\"," +
				"  \"customerNo\": \"C106619062000000001844818\"," +
				"  \"changeCredit\": 100.1," +
				"  \"changeRemark\": \"12321\"," +
				"  \"loanSerialNo\": \"213\"," +
				"  \"tradeSerialNo\": \"123\"" +
				"}";
		String respPostJson = postJson("http://192.168.8.69:31125/customer/subAcc/credit/repay", jsonParam);
		System.out.println(respPostJson);*/
        RandomAccessFile file = new RandomAccessFile("/Users/huangqi/Desktop/out.txt", "r");
        byte[] data = new byte[(int) file.length()];
        file.read(data);
        String respMul = postMultipart("http://127.0.0.1:8088/test/fileAccept", "file",
                "out.txt", data, null);
    }
}
