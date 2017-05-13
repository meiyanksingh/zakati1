package com.mks.zakati.models.events;

/**
 * Created by rahil on 13/5/16.
 */
public class ObjResp<T> extends GeneralResp {
    private T result;

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        result = result;
    }
}
