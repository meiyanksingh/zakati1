package com.mks.zakati.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.mks.zakati.R;
import com.mks.zakati.base.BaseActivity;
import com.mks.zakati.utils.AppConstants;

import butterknife.OnClick;

public class SelectTypeActivity extends BaseActivity {



    @Override
    public void initializeUi() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_select_type;
    }
    @OnClick({R.id.btnOrganization,R.id.btnDonor})
    public void onClick(View view){
        Bundle bundle=new Bundle();
        switch (view.getId()){
            case R.id.btnDonor:
                bundle.putInt(AppConstants.USER_TYPE,AppConstants.OFF);
                switchActivity(SignupActivity.class,bundle);
                break;
            case R.id.btnOrganization:
                bundle.putInt(AppConstants.USER_TYPE,AppConstants.OFF);
                switchActivity(SignupActivity.class,bundle);
                break;
        }


    }
}
