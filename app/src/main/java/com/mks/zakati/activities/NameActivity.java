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

public class NameActivity extends BaseToolBarActivity {

    @BindView(R.id.etFirstName)
    EditText etFirstName;
    @BindView(R.id.etLastName)
    EditText etLastName;

    @Override
    public void initializeUi() {
        setUpToolbar("");
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_name;
    }

    @OnClick(R.id.next)
    public void onClick(View v) {
        if (isValid()) {
            Bundle bundle=getIntent().getExtras();
            bundle.putString(AppConstants.F_NAME,etFirstName.getText()+"");
            bundle.putString(AppConstants.L_NAME,etLastName.getText()+"");

            switchActivity(EmailndNameActivity.class,bundle);
        }

    }

    public boolean isValid() {
        ValidationHelper vh = new ValidationHelper(this);
        if (vh.isTextFieldEmpty(etFirstName, etLastName))
            return false;
        return true;
    }
}
