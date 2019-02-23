package cn.iustu.site.common.model;

import cn.iustu.site.common.constant.ResultConstant;

import java.util.HashMap;
import java.util.Map;

public class Result {

    private Integer status;
    private String msg;
    private Map<String, Object> data;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public Result setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public static Result success(){
        Result result = new Result();
        result.setStatus(ResultConstant.SUCCESS);
        return result;
    }

    public static Result fail(){
        Result result = new Result();
        result.setStatus(ResultConstant.FAIL);
        return result;
    }

    public Result add(String name, Object value) {
        if (data == null)
            data = new HashMap<>();
        data.put(name, value);
        return this;
    }

    public Object get(String name) {
        return getData().get(name);
    }
}
