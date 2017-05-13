package com.mks.zakati.models.events;

import android.content.Intent;

/**
 * Created by Mayank on 1/30/2017.
 */

public class OnActivityResult {

    public  OnActivityResult(int requestCode, int resultCode, Intent data){
        this.requestCode=requestCode;
        this.resultCode=resultCode;
        this.data=data;
    }
    public int getRequestCode() {
        return requestCode;
    }

    public void setRequestCode(int requestCode) {
        this.requestCode = requestCode;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public Intent getData() {
        return data;
    }

    public void setData(Intent data) {
        this.data = data;
    }

    int requestCode;
    int resultCode;
    Intent data;

}
