package com.mks.zakati.models.events;

/**
 * Created by rahil on 13/5/16.
 */
public class GeneralResp {


    /**
     * success : true
     * status : 200
     * message : Successfully login
     * session : true
     */

    private boolean status;
    private String msg;

    public boolean isStatus() {
        return status;
    }

    public String getMessage() {
        return msg;
    }
}
