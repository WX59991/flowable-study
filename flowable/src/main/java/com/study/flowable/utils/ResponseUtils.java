package com.study.flowable.utils;

import com.alibaba.fastjson.JSONObject;

import java.util.Collection;

public class ResponseUtils {

    public static final ResponseUtils SUCCESS = new ResponseUtils("0000");
    public static final ResponseUtils FAIL = new ResponseUtils("9999");

    public static final ResponseUtils TOKEN_NOT_CURRENT = new ResponseUtils("9998");
    public static final ResponseUtils USER_NOT_RESOURCE = new ResponseUtils("8001");
    public static final ResponseUtils TOKEN_NOT_FIND = new ResponseUtils("8002");
    public static final ResponseUtils LOGIN_TIME_OUT = new ResponseUtils("8003");
    public static final ResponseUtils SUPPER_FAIL = new ResponseUtils("8888");

    private JSONObject result = new JSONObject();

    private ResponseUtils(String code) {
        result.put("code", code);
    }


    private ResponseUtils() {
    }

    public static ResponseUtils failInstance(String rspDesc){
        ResponseUtils responseUtils = new ResponseUtils();
        responseUtils.result.put("respCode", "9999");
        responseUtils.result.put("rspDesc", rspDesc);
        return responseUtils;
    }


    public ResponseUtils msg(Object msg) {
        ResponseUtils responseUtil = new ResponseUtils();
        responseUtil.result.put("msg", msg);
        responseUtil.result.putAll(this.result);
        return responseUtil;
    }

    public ResponseUtils msg(Object msg,Object apptx) {
        ResponseUtils responseUtil = new ResponseUtils();
        responseUtil.result.put("msg", msg);
        responseUtil.result.put("apptx", apptx);
        responseUtil.result.putAll(this.result);
        return responseUtil;
    }

    public ResponseUtils body(Object body) {
        ResponseUtils responseUtil = new ResponseUtils();
        responseUtil.result.put("body", body);
        responseUtil.result.putAll(this.result);
        return responseUtil;
    }

    /**
     * 作为msg()的特殊场景,入参是JSONObject类型，但是还想使用固定的格式
     *
     * @param msg JSONObject
     * @return
     */
    public ResponseUtils msgJson(JSONObject msg) {
        ResponseUtils responseUtil = new ResponseUtils();
        responseUtil.result.put("msg", msg);
        responseUtil.result.putAll(this.result);
        return responseUtil;
    }

    /**
     * 封装result.
     *
     * @param coll  集合
     * @param total total
     * @return data
     */
    public static ResponseUtils returnData(Collection<?> coll, long total) {
        ResponseUtils responseUtil = new ResponseUtils();
        responseUtil.result.put("data", coll);
        responseUtil.result.put("total", total);
        responseUtil.result.put("code", 200);
        return responseUtil;
    }

    public JSONObject getResult() {
        return result;
    }

    @SuppressWarnings("unchecked")
    public <T> T getMsg() {
        return (T) result.get("msg");
    }

    @Override
    public String toString() {
        return result.toString();
    }


    public String getCode() {
        return this.result.getString("code");
    }
}
