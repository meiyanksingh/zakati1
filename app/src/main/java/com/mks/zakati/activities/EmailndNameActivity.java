package com.mks.zakati.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.mks.zakati.R;
import com.mks.zakati.base.BaseActivity;
import com.mks.zakati.base.BaseToolBarActivity;
import com.mks.zakati.utils.AppConstants;
import com.mks.zakati.utils.ValidationHelper;

import butterknife.BindView;
import butterknife.OnClick;

public class EmailndNameActivity extends BaseToolBarActivity implements AppConstants {

    @BindView(R.id.etEmail)
    EditText etEmail;
    @BindView(R.id.etUserName)
    EditText etUserName;

    @Override
    public void initializeUi() {
        setUpToolbar("");
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_email_and_username;
    }

    @OnClick(R.id.next)
    public void onClick(View v) {
        if (isValid()) {
            Bundle bundle = getIntent().getExtras();
            bundle.putString(EMAIL, etEmail.getText() + "");
            bundle.putString(U_NAME, etUserName.getText() + "");
            switchActivity(CreatePassActivity.class, bundle);
        }

    }

    public boolean isValid() {
        ValidationHelper vh = new ValidationHelper(this);
        if (vh.isTextFieldEmpty(etEmail, etUserName))
            return false;
        if (vh.isEmailValid(etEmail.getText() + ""))
            return false;
        return true;
    }
}
