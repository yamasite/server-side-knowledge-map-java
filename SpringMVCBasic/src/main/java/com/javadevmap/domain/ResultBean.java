package com.javadevmap.domain;
/**
 * 返回的实体bean
 */
public class ResultBean {
    private int code;// 状态码
    private Product data;// 业务数据
    private String msg;// 业务信息

    public ResultBean() {
    }
    public ResultBean(int code, Product data, String desc) {
        this.code = code;
        this.data = data;
        this.msg = desc;
    }

    public static ResultBean ofSuccess(Product obj){
        ResultBean ret=new ResultBean();
        ret.setCode(200);
        ret.setData(obj);
        return ret;
    }
    public static ResultBean ofSuccess(String msg){
        ResultBean ret=new ResultBean();
        ret.setCode(200);
        ret.setMsg(msg);
        return ret;
    }
    public static ResultBean ofSuccess(Product obj,String msg){
        ResultBean ret=new ResultBean();
        ret.setCode(200);
        ret.setData(obj);
        ret.setMsg(msg);
        return ret;
    }
    public static ResultBean ofSuccess(){
        ResultBean ret=new ResultBean();
        ret.setCode(200);
        return ret;
    }
    public static ResultBean ofFail(int code,String  desc){
        ResultBean ret=new ResultBean();
        ret.setCode(code);
        ret.setMsg(desc);
        return ret;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Product getData() {
        return data;
    }

    public void setData(Product data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "ResultBean{" +
                "code=" + code +
                ", data=" + data +
                ", msg='" + msg + '\'' +
                '}';
    }
}
