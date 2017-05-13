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

public class YourDetailActivity extends BaseToolBarActivity implements AppConstants{

    @BindView(R.id.etCity)
    EditText etCity;
    @BindView(R.id.etCountry)
    EditText etCountry;
    @BindView(R.id.etPhone)
    EditText etPhone;

    @Override
    public void initializeUi() {
        setUpToolbar("");
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_your_details;
    }

    @OnClick(R.id.next)
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.next:
                 if(isValid()){
                     Bundle bundle=getIntent().getExtras();
                     bundle.putString(CITY,etCity.getText()+"");
                     bundle.putString(COUNTRY,etCountry.getText()+"");
                     bundle.putString(TELEPHONE,etPhone.getText()+"");
                     switchActivity(AboutYourSelfActivity.class,bundle);
                 }
                break;
        }

    }


    public boolean isValid() {
        ValidationHelper vh = new ValidationHelper(this);
        if (vh.isTextFieldEmpty(etCity, etCountry, etPhone))
            return false;
        return true;
    }
}
