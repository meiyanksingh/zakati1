package com.mks.zakati.models.events;

import java.util.List;

/**
 * Created by rahil on 13/5/16.
 */
public class ListResp<T> extends GeneralResp {
    List<T> result;

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        result = result;
    }
}
