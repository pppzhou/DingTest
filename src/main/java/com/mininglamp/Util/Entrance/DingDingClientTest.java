package com.mininglamp.Util.Entrance;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.request.OapiAttendanceListRequest;
import com.dingtalk.api.request.OapiGettokenRequest;
import com.dingtalk.api.response.OapiAttendanceListResponse;
import com.dingtalk.api.response.OapiGettokenResponse;
import com.mininglamp.Util.HttpUtils;
import com.taobao.api.ApiException;

/**
 * 钉钉接口测试类
 */
public class DingDingClientTest {

    //key
    private final static String APP_KEY = "dingiza4fgstbalf1l6u";
    //scecret
    private final static String APP_SECRET = "Udqkt0iGiu6fMzNh6yB-pca86HJx2dUVvLcGHxv1P1G3rnOiBW8qFJqJ8_pPZ9a0";

    public static void main(String[] args) {

        String workDateFrom = "2019-12-01 08:00:00";//打卡记录的开始日期
        String workDateTo = "2019-12-13 18:00:00"; //开卡记录的结束日期
        Long offset = 0L;//获取考勤数据的起始点
        Long limit = 10L;//获取考勤数据的最大条数
        String accessToken = getAccessToken();
        System.out.println(accessToken);
        if (accessToken != null) {
            Object cartList2 = getCartList1(accessToken, workDateFrom, workDateTo, offset, limit);
            System.out.println(cartList2);
		}
      /*  if (accessToken != null) {
            JSONArray cardList = getCardList(accessToken, workDateFrom, workDateTo, offset, limit);
            System.out.println(cardList);
        }*/

    }


    //根据key和secret获取到access_token
    public static String getAccessToken() {
        DefaultDingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/gettoken");

        //获取token请求对象
        OapiGettokenRequest req = new OapiGettokenRequest();

        req.setAppkey(APP_KEY);
        req.setAppsecret(APP_SECRET);

        req.setHttpMethod("GET");

        OapiGettokenResponse rsp = null;

        try {
            rsp = client.execute(req);
        } catch (ApiException e) {
            e.printStackTrace();
        }

        return rsp.getAccessToken();
    }

    //钉钉接口获取考勤打卡数据
    public static JSONArray getCardList(String accessToken, String workDateFrom,
                                        String workDateTo, Long offset,Long limit){
        String recordUrl = "https://oapi.dingtalk.com/attendance/list?access_token="+accessToken;

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("workDateFrom",workDateFrom);
        jsonObject.put("workDateTo",workDateTo);
        jsonObject.put("offset",offset);
        jsonObject.put("limit",limit);

        String result = HttpUtils.doPost(recordUrl,jsonObject,"utf-8");
        JSONObject resutJSON = JSONObject.parseObject(result);
        String msg = (String)resutJSON.get("errmsg");
        JSONArray jsonArray = null;
        //errmsg 为ok时，成功请求到数据
        if("ok".equals(msg)){
            jsonArray = (JSONArray) resutJSON.get("recordresult");
        }
        return jsonArray;
    }


    //钉钉接口获取打卡记录数据
    public static Object getCartList1(String accessToken ,String workDateFrom,
                                      String workDateTo,Long offset,Long limit){
        DefaultDingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/attendance/list");
        OapiAttendanceListRequest request = new OapiAttendanceListRequest();

        //设置请求参数
        request.setWorkDateFrom(workDateFrom);
        request.setWorkDateTo(workDateTo);
        request.setOffset(offset);
        request.setLimit(limit);

        OapiAttendanceListResponse response = null;
        try {
            response = client.execute(request, accessToken);
        } catch (ApiException e) {
            e.printStackTrace();
        }

        return response.getRecordresult();
    }
}
