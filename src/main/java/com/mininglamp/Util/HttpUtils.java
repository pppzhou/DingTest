package com.mininglamp.Util;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * HttpUtils请求工具类
 */
public class HttpUtils {

    //post请求
    public static String doPost(String url, JSONObject jsonObject, String charset){

        HttpClient httpClient = null;
        HttpPost httpPost = null;
        String result = null;

        try {
            httpClient = HttpClients.createDefault();
            httpPost = new HttpPost(url);
            //设置请求参数
            List<NameValuePair> list = new ArrayList<NameValuePair>();

            //设置请求体
            StringEntity entity = new StringEntity(jsonObject.toString(), charset);
            entity.setContentEncoding("utf-8");
            entity.setContentType("application/json");
            httpPost.setEntity(entity);
            //执行请求
            HttpResponse response = httpClient.execute(httpPost);
            //返回状态码
            int code = response.getStatusLine().getStatusCode();
            System.out.println(code);
            //请求成功(200),获取数据
            if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
                HttpEntity resEntity = response.getEntity();
                if(resEntity != null){
                    result = EntityUtils.toString(resEntity,charset);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    //Get请求
    public static String doGet(String url) {
        String result = null;
        HttpGet request = new HttpGet(url);
        HttpClient httpClient = HttpClients.createDefault();
        try {
            HttpResponse response = httpClient.execute(request);
            int code = response.getStatusLine().getStatusCode();
            System.out.println(code);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                result = EntityUtils.toString(response.getEntity(), "utf-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
