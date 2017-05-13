package com.mks.zakati.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.mks.zakati.R;
import com.mks.zakati.base.App;
import com.mks.zakati.base.BaseToolBarActivity;
import com.mks.zakati.models.events.ListResp;
import com.mks.zakati.models.events.res.SignUpRes;
import com.mks.zakati.networkapi.ApiClient;
import com.mks.zakati.utils.DialogUtil;
import com.mks.zakati.utils.Utils;

import java.io.File;
import java.util.Map;

import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends BaseToolBarActivity {
Bundle bundle;
    @Override
    public void initializeUi() {
        setUpToolbar("", R.menu.menu_signup, getMenuItemCliCkListener());
        bundle=getIntent().getExtras();
    }

    private Toolbar.OnMenuItemClickListener getMenuItemCliCkListener() {
        return new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.login:
                        //switchActivity();
                        return true;
                    default:
                        return false;
                }

            }
        };
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_before_sign_up;
    }

    @OnClick({R.id.btnFb, R.id.btnSignup, R.id.llOptions})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnFb:
                break;
            case R.id.btnSignup:
                switchActivity(TermsActivity.class,bundle);
                break;
            case R.id.llOptions:
                break;
        }
    }

    public void signUp() {
        if (!App.isConnected(true))
            return;


            RequestBody profilePic = RequestBody.create(MediaType.parse("image/*"), new File(mFilePath));
            Map<String,Object> map= Utils.getBeanToMap(getReq());


            Call<ListResp<SignUpRes>> call = ApiClient.getClient().getApis().signUP(profilePic,map);
            call.enqueue(new Callback<ListResp<SignUpRes>>() {

                @Override
                public void onResponse(Call<ListResp<SignUpRes>> call, Response<ListResp<SignUpRes>> response) {
                    if(response.isSuccessful()) {
//                        DialogUtil.showToast(SetProfilePicActivity.this, "Signup successfully");
//                        Intent intent = new Intent(getApplicationContext(), CausesActivity.class);
//                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
//                        startActivity(intent);
                    }
                    DialogUtil.showToast(SignupActivity.this,response.body().getMessage());
                }

                @Override
                public void onFailure(Call<ListResp<SignUpRes>> call, Throwable t) {
                }
            });

        }



    public SignUpRes getReq() {
        SignUpRes req = new SignUpRes();
        Bundle bundle = getIntent().getExtras();



        return req;

    }


}
