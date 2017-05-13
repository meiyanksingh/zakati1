package com.mks.zakati.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.AnimRes;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.mks.zakati.R;
import com.mks.zakati.interfaces.BaseInterfaces;
import com.mks.zakati.networkapi.ApiClient;
import com.mks.zakati.networkapi.Apis;
import com.mks.zakati.ui.RotateLoading;
import com.mks.zakati.utils.DialogUtil;
import com.mks.zakati.utils.PrefMgr;

import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public abstract class BaseActivity extends AppCompatActivity implements BaseInterfaces {

    protected Activity mThis;
    protected String TAG;
    private Resources mRes;
    protected PrefMgr mPrefMgr;
    protected RotateLoading mProgress;
    protected Apis mApis;
    private ProgressDialog mProgressDialog;
    private ProgressDialog progressDialog;

    private static final float LOCATION_DISPLACEMENT = 50;
    //public static final long UPDATE_INTERVAL_IN_MILLISECONDS = 60*1000;
    public static final long FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS = 60 * 1000;
    protected Location mLocation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mThis = this;
        TAG = getClass().getSimpleName();
        //API_LEVEL = Build.VERSION.SDK_INT;
        mRes = getResources();
        mPrefMgr = PrefMgr.get();


        mApis = ApiClient.getClient().getApis();

        int layoutResId = getLayoutId();
        if (layoutResId != 0)
            setContentView(layoutResId);

        ButterKnife.bind(this);
        initLoader();
        initializeUi();

    }

    public boolean toggleEmptyView(boolean isEmptyVisible, @IdRes int toBeHide, @IdRes int emptyView) {
        if (isEmptyVisible) {
            hideView(findViewById(toBeHide));
            findViewById(emptyView).setAnimation(AnimationUtils.makeInAnimation(this, true));
            showView(findViewById(emptyView));
        } else {
            showView(findViewById(toBeHide));
            findViewById(emptyView).setAnimation(AnimationUtils.makeOutAnimation(this, true));
            //hideView(findViewById(R.id.empty_view));
        }
        return isEmptyVisible;
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    private void initLoader() {
        mProgress = (RotateLoading) findViewById(R.id.progress_wheel);
    }

    /* protected void showProgress() {
         DialogUtil.showProgressWheel(mProgress);
     }


     protected void hideProgress() {
         DialogUtil.hideProgressWheel(mProgress);
     }
 */
    public Apis getApis() {
        return mApis;
    }


    public boolean isShowingProgressDialog() {
        return mProgressDialog != null && mProgressDialog.isShowing();
    }

    protected void closeProgressDialog() {
        //DialogUtil.hideProgressDialog(mProgressDialog);
        if (mProgressDialog != null && mProgressDialog.isShowing() && !isFinishing()) {
            mProgressDialog.dismiss();
        }
    }

    protected void showProgressDialog() {
        try {
            if (mProgressDialog == null) {
                mProgressDialog = DialogUtil.showProgressDialog(this);
            }
            if (!mProgressDialog.isShowing() && !isFinishing() /*&& !isDestroyed()*/)
                mProgressDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void hideView(View view) {
        if (view != null)
            view.setVisibility(View.GONE);
    }

    protected void enableView(View view) {
        view.setEnabled(true);
    }

    protected void disableView(View view) {
        view.setEnabled(false);
    }

    protected void showView(View view) {
        if (view != null)
            view.setVisibility(View.VISIBLE);
    }

    protected void inivisibleView(View view) {
        if (view != null)
            view.setVisibility(View.INVISIBLE);
    }

    public String getTrimmedText(TextView textView) {
        return textView.getText().toString().trim();
    }


    public void popLastFragment() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        }
    }

    /**
     * This method adds fragment to the container
     *
     * @param fragment
     * @param isAddToBackStack
     */
    protected void setFragment(Fragment fragment, boolean isAddToBackStack) {
       /* setFragment(fragment, R.id.container, isAddToBackStack, R.anim.slide_in_right,
                R.anim.slide_out_right,
                R.anim.slide_in_left,
                R.anim.slide_out_left);*/
        setFragment(fragment, R.id.container, isAddToBackStack, 0,
                0,
                0,
                0);
    }

    protected void setFragment(Fragment fragment, int container, boolean isAddToBackStack) {
        setFragment(fragment, container, isAddToBackStack, 0,
                0,
                0,
                0);
    }

    protected void hideFragment(Class<? extends Fragment> fragment, @AnimRes int enter,
                                @AnimRes int exit, @AnimRes int popEnter, @AnimRes int popExit) {
        Fragment frag = getSupportFragmentManager().findFragmentByTag(fragment.getSimpleName());
        if (frag != null)
            getSupportFragmentManager().beginTransaction().setCustomAnimations(enter,
                    exit,
                    popEnter,
                    popExit).hide(frag).commit();
    }

    protected void hideFragment(Class<? extends Fragment> fragment) {
        hideFragment(fragment, 0,
                0,
                0,
                0);
    }

    protected void showFragment(Class<? extends Fragment> fragment, @AnimRes int enter,
                                @AnimRes int exit, @AnimRes int popEnter, @AnimRes int popExit) {
        Fragment frag = getSupportFragmentManager().findFragmentByTag(fragment.getSimpleName());
        if (frag != null) {
            getSupportFragmentManager().beginTransaction().setCustomAnimations(enter,
                    exit, popEnter, popExit).addToBackStack(fragment.getSimpleName()).show(frag).commit();
        }
    }


    protected void showFragment(Class<? extends Fragment> fragment) {
        if (isFinishing() /*|| isDestroyed()*/) return;
        try {
            Fragment frag = getSupportFragmentManager().findFragmentByTag(fragment.getSimpleName());
            if (frag != null)
                getSupportFragmentManager().beginTransaction().setCustomAnimations(0,
                        0,
                        0,
                        0).show(frag).commitAllowingStateLoss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void setFragment(Fragment fragment, int containerId, boolean isAddToBackStack, @AnimRes int enter,
                               @AnimRes int exit, @AnimRes int popEnter, @AnimRes int popExit) {


        if (isFinishing() /*|| isDestroyed()*/) return;
        String fragmentName = fragment.getClass().getSimpleName();
        if (isAddToBackStack) {

            popLastFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations(enter,
                            exit, popEnter, popExit)
                    .add(containerId, fragment, fragmentName)
                    .addToBackStack(fragmentName)
                    .commitAllowingStateLoss();
        } else {
            getSupportFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations(enter,
                            exit, popEnter, popExit)
                    .add(containerId, fragment, fragmentName)
                    .commitAllowingStateLoss();
        }
    }

    /**
     * Method used to switch from current activity to other
     *
     * @param destinationActivity activity to open
     */
    public void switchActivity(Class<?> destinationActivity) {
        startActivity(new Intent(this, destinationActivity));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    /**
     * Method used to switch from current activity to other with data
     *
     * @param destinationActivity activity to open
     * @param bundle              data that carry to destination activity
     */
    public void switchActivity(Class<?> destinationActivity, Bundle bundle) {
        Intent intent = new Intent(this, destinationActivity);
        if (bundle != null)
            intent.putExtras(bundle);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    /**
     * method used to starting another activity for result
     *
     * @param destinationActivity activity to open
     * @param bundle              data that carry to destination activity
     * @param requestCode         result code
     */
    public void switchActivity(Class<?> destinationActivity, Bundle bundle, int requestCode) {
        Intent intent = new Intent(this, destinationActivity);
        if (bundle != null)
            intent.putExtras(bundle);
        startActivityForResult(intent, requestCode);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }


    /**
     * method used to starting another activity for result
     *
     * @param destinationActivity activity to open
     * @param requestCode         result code
     */
    public void switchActivity(Class<?> destinationActivity, int requestCode) {
        Intent intent = new Intent(this, destinationActivity);
        startActivityForResult(intent, requestCode);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }


    public Fragment getFragmentByTag(Class<? extends Fragment> fragment) {
        return getSupportFragmentManager().findFragmentByTag(fragment.getSimpleName());
    }


    protected void setStatusBarColor(int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(this, color));
        }
    }

    protected void setStatusTranslucent() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            // window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    /*protected void showRotateLoading(RotateLoading rotateLoading) {
       // showView(rotateLoading);
        rotateLoading.start();
    }

    protected void hideRotateLoading(RotateLoading rotateLoading) {
        hideView(rotateLoading);
        rotateLoading.stop();
    }*/


    protected void pushFragment(int containerId, Fragment fragment) {
        if (mThis != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(containerId, fragment, fragment.getClass().getSimpleName()).commit();
        }
    }







    private void displayProgress() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.getWindow().addFlags(Window.FEATURE_NO_TITLE);
            progressDialog.setMessage("Getting location...");
        }

        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        dismissProgress();
    }

    private void dismissProgress() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

}
