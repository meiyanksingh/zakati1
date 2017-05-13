package com.mks.zakati.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

import com.mks.zakati.R;
import com.mks.zakati.base.BaseToolBarActivity;
import com.mks.zakati.utils.DialogUtil;
import com.mks.zakati.utils.ValidationHelper;

import butterknife.BindView;
import butterknife.OnClick;

public class TermsActivity extends BaseToolBarActivity {

   @BindView(R.id.chkTerms)
    CheckBox chkTerms;



    @Override
    public void initializeUi() {
        setUpToolbar("");

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_terms_conditions;
    }

    @OnClick(R.id.next)
    public void onClick(View v){
        if(isValid()){
            Bundle bundle=getIntent().getExtras();
            switchActivity(NameActivity.class,bundle);
        }

    }

    public boolean isValid(){

        if(!chkTerms.isChecked()) {
            DialogUtil.showToast(this, getResources().getString(R.string.check_term_condition));
            return false;
        }
        return true;

    }

}
