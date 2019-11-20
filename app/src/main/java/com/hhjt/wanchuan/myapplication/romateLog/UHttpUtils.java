package com.hhjt.wanchuan.myapplication.romateLog;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class UHttpUtils {


//    public static String httpGet(String url, Map<String, String> params) {
//
//        List<NameValuePair> lst = new ArrayList<NameValuePair>();
//        if (params != null) {
//            Iterator<String> keyItors = params.keySet().iterator();
//            while (keyItors.hasNext()) {
//                String key = keyItors.next();
//                String val = params.get(key);
//                lst.add(new BasicNameValuePair(key, val));
//            }
//        }
//
//        return httpGet(url, lst);
//    }

//    public static String httpPost(String url, String params) {
//
////        List<String> lst = new ArrayList<String>();
////        if (params != null) {
////            Iterator<String> keyItors = params.keySet().iterator();
////            while (keyItors.hasNext()) {
////                String key = keyItors.next();
////                String val = params.get(key);
////                lst.add(val);
////            }
////        }
//
//        return httpPost(url, params);
//    }

//    public static String httpGet(String urlStr, List<NameValuePair> params) {
//
//        String paramsEncoded = "";
//        if (params != null) {
//            paramsEncoded = URLEncodedUtils.format(params, "UTF-8");
//        }
//
//        String fullUrl = urlStr + "?" + paramsEncoded;
//
//        String result = null;
//        URL url = null;
//        HttpURLConnection connection = null;
//        InputStreamReader in = null;
//        try {
//            url = new URL(fullUrl);
//            connection = (HttpURLConnection) url.openConnection();
//            in = new InputStreamReader(connection.getInputStream());
//            BufferedReader bufferedReader = new BufferedReader(in);
//            StringBuffer strBuffer = new StringBuffer();
//            String line = null;
//            while ((line = bufferedReader.readLine()) != null) {
//                strBuffer.append(line);
//            }
//            result = strBuffer.toString();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (connection != null) {
//                connection.disconnect();
//            }
//            if (in != null) {
//                try {
//                    in.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//
//        }
//        return result;
//    }

    public static String httpPost(String urlStr, String params) {
        HttpOkHttp.getInstance().requestPost(urlStr,params,new HttpOkHttp.OkHttpCallBack<String> (){

            @Override
            public void requestSuccess(String s) {
                System.out.println(s);
            }

            @Override
            public void requestFailure(String s) {
                System.out.println(s);
            }
        } ,String.class);
        return "";
    }

}
