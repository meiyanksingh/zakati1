package com.mks.zakati.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.mks.zakati.R;
import com.mks.zakati.base.BaseToolBarActivity;
import com.mks.zakati.utils.AppConstants;
import com.mks.zakati.utils.ValidationHelper;

import butterknife.BindView;
import butterknife.OnClick;

public class CreatePassActivity extends BaseToolBarActivity implements AppConstants {

    @BindView(R.id.etPassword)
    EditText etPassword;
    @BindView(R.id.etConfrmPass)
    EditText etConfrmPass;
    @BindView(R.id.tvShow)
    TextView tvShow;
    private boolean isShowPass;

    @Override
    public void initializeUi() {
        setUpToolbar("");
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_create_password;
    }

    @OnClick({R.id.next, R.id.tvShow})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.next:
                if (isValid()) {
                    Bundle bundle = getIntent().getExtras();
                    bundle.putString(PASSWORD, etPassword.getText() + "");
                    switchActivity(YourDetailActivity.class, bundle);
                }
                break;
            case R.id.tvShow:
                if (!isShowPass) {
                    etPassword.setTransformationMethod(null);
                    isShowPass = true;
                    tvShow.setText(getResources().getString(R.string.caps_hide));
                } else {
                    etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    isShowPass = false;
                    tvShow.setText(getResources().getString(R.string.caps_show));
                }
                break;
        }

    }

    public boolean isValid() {
        ValidationHelper vh = new ValidationHelper(this);
        if (vh.isTextFieldEmpty(etPassword, etConfrmPass))
            return false;
        if (vh.isPasswordMatched(etPassword, etConfrmPass))
            return false;
        return true;
    }
}
