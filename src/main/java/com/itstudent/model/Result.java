package com.itstudent.model;

import com.google.gson.Gson;

public class Result {

    private Object data;

    public Result(Object data) {
        this.data = data;
    }

    public static String send(Object data){
        return new Gson().toJson(new Result(data));
    }

    public Object getData() {
        return data;
    }

    public Result setData(Object data) {
        this.data = data;
        return this;
    }
}
