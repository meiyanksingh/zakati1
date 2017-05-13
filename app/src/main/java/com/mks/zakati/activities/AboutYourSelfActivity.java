package com.mks.zakati.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.mks.zakati.R;
import com.mks.zakati.base.BaseToolBarActivity;
import com.mks.zakati.utils.AppConstants;
import com.mks.zakati.utils.ValidationHelper;

import butterknife.BindView;
import butterknife.OnClick;

public class AboutYourSelfActivity extends BaseToolBarActivity implements AppConstants {
    @BindView(R.id.etAbout)
    EditText etAbout;
    @BindView(R.id.etHappy)
    EditText etHappy;

    @Override
    public void initializeUi() {
        setUpToolbar("");

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_setup_profile;
    }

    public boolean isValid() {
        ValidationHelper vh = new ValidationHelper(this);
        if (vh.isTextFieldEmpty(etAbout, etHappy))
            return false;

        return true;
    }

    @OnClick(R.id.next)
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.next:
                if (isValid()) {
                    Bundle bundle = getIntent().getExtras();
                    bundle.putString(ABOUT_ME, etAbout.getText() + "");
                    bundle.putString(HAPPY, etHappy.getText() + "");
                    switchActivity(SetProfilePicActivity.class,bundle);
                }
                break;
        }

    }
}
