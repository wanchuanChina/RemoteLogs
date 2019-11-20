package com.hhjt.wanchuan.myapplication.romateLog;

/**
 * Created by Administrator
 * on 2019/11/1 11:26.
 * desc:
 */

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * OkHttp类
 * Created by: Android Studio.
 * PackageName: com.ww7h.ww.common.apis.http.okhttp
 * User: ww
 * DateTime: 2019/3/22 20:49
 */
public class HttpOkHttp {

    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private OkHttpClient client;

    private HttpOkHttp() {
        client = new OkHttpClient();
    }

    private static class HttpOkHttpInstance {
        private final static HttpOkHttp INSTANCE = new HttpOkHttp();
    }

    public static HttpOkHttp getInstance() {
        return HttpOkHttpInstance.INSTANCE;
    }

    /**
     * GET请求
     *
     * @param url            请求地址
     * @param okHttpCallBack 请求回调
     * @param clazz          返回结果的Class
     * @param <T>            返回结果类型
     */
    public <T> void requestGet(String url, final OkHttpCallBack<T> okHttpCallBack,
                               final Class<T> clazz) {
        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                okHttpCallBack.requestFailure(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    requestResult(response.body().string(), okHttpCallBack, clazz);
                } else {
                    okHttpCallBack.requestFailure(response.message());
                }
            }
        });
    }

    /**
     * POST请求
     *
     * @param url            请求地址
     * @param json           请求参数 json 格式
     * @param okHttpCallBack 请求回调
     * @param clazz          返回结果的class
     * @param <T>            请求返回的类型
     */
    public <T> void requestPost(String url, String json, final OkHttpCallBack<T> okHttpCallBack,
                                final Class<T> clazz) {

        FormBody.Builder formBody = new FormBody.Builder();//创建表单请求体
        formBody.add("log",json);//传递键值对参数
        Request request = new Request.Builder()//创建Request 对象。
                .url(url)
                .post(formBody.build())//传递请求体
                .build();

//        RequestBody body = RequestBody.create(JSON, json);
//        Request request = new Request.Builder()
//                .url(url)
//                .post(body)
//                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                okHttpCallBack.requestFailure(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    requestResult(response.body().string(), okHttpCallBack, clazz);
                } else {
                    okHttpCallBack.requestFailure(response.message());
                }
            }
        });
    }

    private <T> void requestResult(String result, OkHttpCallBack<T> callBack, Class<T> clazz) {

        if ("java.lang.String".equals(clazz.getName())) {
            callBack.requestSuccess((String) result);

        } else {
            callBack.requestSuccess("");
        }
    }


    public interface OkHttpCallBack<T> {
        /**
         * 请求成功回调
         *
         * @param t 回调返回成功结果输出
         */
        void requestSuccess(String t);

        /**
         * 请求失败回调
         *
         * @param message 回调返回失败消息
         */
        void requestFailure(String message);
    }

}