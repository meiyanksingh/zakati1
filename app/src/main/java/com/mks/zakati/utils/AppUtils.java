package com.mks.zakati.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;


import com.mks.zakati.R;
import com.mks.zakati.ui.RotateLoading;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.mks.zakati.utils.Utils.hideView;
import static com.mks.zakati.utils.Utils.showView;


/**
 * Created by Rahil on 13/11/15.
 */
public class AppUtils {




    public static <T> void enqueueCall(final View parentView, final RotateLoading progressWheel, final Call<T> call, final Callback<T> callback) {
        progressWheel.start();
        if (progressWheel.getId() == R.id.toolbar_progress) {
            parentView.setEnabled(false);
        } else {
            parentView.setEnabled(true);
            hideView(parentView);
        }
        enqueueCall(call, new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                progressWheel.stop();
                showView(parentView);
                parentView.setEnabled(true);
                callback.onResponse(call, response);
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                parentView.setEnabled(true);
                progressWheel.stop();
                callback.onFailure(call, t);
            }
        });
    }

    public static <T> void enqueueCall(final View parentView, final RotateLoading progressWheel, int page, final Call<T> call, final Callback<T> callback) {

        enqueueCall(parentView, progressWheel, call, callback);
        if (page > 1) {
            parentView.setEnabled(true);
            parentView.setVisibility(View.VISIBLE);
            progressWheel.stop();
        }
    }

    public static <T> void enqueueCall(final View parentView, final int pageNo, final RotateLoading progressWheel, final RotateLoading toolbarProgressWheel, final Call<T> call, final Callback<T> callback) {
        if (pageNo == 1) {
            progressWheel.start();
            parentView.setVisibility(View.GONE);
        } else {
            toolbarProgressWheel.start();
            parentView.setVisibility(View.VISIBLE);
        }
        enqueueCall(call, new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                parentView.setVisibility(View.VISIBLE);
                progressWheel.stop();
                toolbarProgressWheel.stop();
                callback.onResponse(call, response);
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                if (pageNo == 1) {
                    parentView.setVisibility(View.GONE);
                }
                progressWheel.stop();
                toolbarProgressWheel.stop();
                callback.onFailure(call, t);

            }
        });
    }

    //T extends Comparable<? super T
    public static <T> void enqueueCall(final Call<T> call, final Callback<T> callback) {
        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                if (response.isSuccessful()) {
                    if (response.body() == null) {

                        try {
                            callback.onFailure(call, new Throwable(response.errorBody().string()));
                        } catch (IOException | NullPointerException e) {
                            callback.onFailure(call, new Throwable("Unknown"));
                            e.printStackTrace();
                        }
                    } else
                        callback.onResponse(call, response);
                } else {
                    callback.onFailure(call, new Throwable("Response Failed Code : " + response.code()));
                }


            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                t.printStackTrace();
                callback.onFailure(call, t);

            }
        });
    }

    public static <T> void enqueueCall(final MenuItem menuItem, final RotateLoading loader, final Call<T> call, final Callback<T> callback) {

        menuItem.setVisible(false);
        DialogUtil.showRotateLoading(loader);
        enqueueCall(call, new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {

                DialogUtil.hideRotateLoading(loader);
                menuItem.setVisible(true);
                callback.onResponse(call, response);
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {

                DialogUtil.hideRotateLoading(loader);
                menuItem.setVisible(true);
                callback.onFailure(call, t);
            }
        });

    }


    public static void setProfileImage(ImageView imageView, String url) {
    }

    public static void setImage(ImageView imageView, String url) {
    }


   /* public static void openWebViewActivity(Context context, String url) {
        Intent webUrlIntent = new Intent(context, WebViewActivity.class);
        webUrlIntent.putExtra(AppConstants.EXTRA_URL, url);
        context.startActivity(webUrlIntent);
    }*/

    public static void openPlayStore(Context context) {
        final String appPackageName = context.getPackageName(); // getPackageName() from Context or Activity object
        try {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
        } catch (android.content.ActivityNotFoundException e) {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
        }
    }


    public static <T> void enqueueCall(View viewTobeHide, int pageNo, RotateLoading progress, Call<T> call, Callback<T> callback) {
        if (pageNo == 1) {
            enqueueCall(viewTobeHide, progress, call, callback);
        } else {
            enqueueCall(call, callback);
        }


    }






}
