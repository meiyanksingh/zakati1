package com.mks.zakati.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.mks.zakati.R;
import com.mks.zakati.ui.RotateLoading;

import java.util.List;


/**
 * Created by Rahil on 8/9/15.
 */
public class DialogUtil {

    private static final String TAG = DialogUtil.class.getSimpleName();

    public static ProgressDialog showProgressDialog(Context mContext) {
        try {
            if (mContext != null && !((Activity) mContext).isFinishing()) {
                ProgressDialog mProgressDialog = ProgressDialog.show(mContext,
                        "", mContext.getString(R.string.please_wait));
                return mProgressDialog;
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return new ProgressDialog(mContext);
    }

    public static void showProgressDialog(@NonNull ProgressDialog progressDialog) {
        try {
            if (progressDialog != null) {
                Activity activity = (Activity) progressDialog.getContext();
                if (activity != null && !activity.isFinishing()) {
                    if (!progressDialog.isShowing()) progressDialog.show();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void hideProgressDialog(@NonNull ProgressDialog progressDialog) {
        try {
            if (progressDialog.isShowing()) progressDialog.dismiss();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    /* public static ProgressDialog getProgressDialog(Context mContext) {
         if (mContext != null) {
             ProgressDialog mProgressDialog = new ProgressDialog(mContext);
             mProgressDialog.setMessage(mContext.getString(R.string.please_wait));
             return mProgressDialog;
         }

         return null;
     }
 */
    public static void showNoNetworkToast(Context context) {
        //Toast.makeText(context.getApplicationContext(), msg, Toast.LENGTH_LONG).show();
        String msg = context.getResources().getString(R.string.no_network_msg);
        showToastShortLength(context, msg);
    }

    public static Snackbar showRetrySnackBar(@NonNull View anyView, String msg, final View.OnClickListener retryListener) {
        //Toast.makeText(context.getApplicationContext(), msg, Toast.LENGTH_LONG).show();
        try {
            final Snackbar snackBar = Snackbar.make(anyView, msg, Snackbar.LENGTH_INDEFINITE);
            snackBar.setActionTextColor(Color.WHITE);
            View view = snackBar.getView();
            TextView tv = (TextView)
                    view.findViewById(android.support.design.R.id.snackbar_text);
            tv.setTextColor(Color.WHITE);
            snackBar.setAction(R.string.retry, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    snackBar.dismiss();
                    retryListener.onClick(v);
                }
            });
            snackBar.show();
            return snackBar;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public static Snackbar showNoNetworkSnackBar(@NonNull View anyView, final View.OnClickListener listener) {
        String msg = anyView.getContext().getResources().getString(R.string.no_network_msg);
        return showRetrySnackBar(anyView, msg, listener);
    }

    public static Snackbar showNoNetworkSnackBar(@NonNull View anyView) {
        return showSnackBar(anyView, R.string.no_network_msg);
    }

    public static Snackbar showSnackBar(View anyView, String msg) {
        //Toast.makeText(context.getApplicationContext(), msg, Toast.LENGTH_LONG).show();
        final Snackbar snackBar = Snackbar.make(anyView, msg, Snackbar.LENGTH_LONG);
        snackBar.setActionTextColor(Color.WHITE);
        View view = snackBar.getView();
        TextView tv = (TextView)
                view.findViewById(android.support.design.R.id.snackbar_text);
        tv.setTextColor(Color.WHITE);
        snackBar.setAction(R.string.dismiss, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackBar.dismiss();
            }
        });
        snackBar.show();
        return snackBar;
    }

    public static Snackbar showSnackBar(View anyView, int msg) {
        //Toast.makeText(context.getApplicationContext(), msg, Toast.LENGTH_LONG).show();
        Resources res = anyView.getContext().getResources();
        return showSnackBar(anyView, res.getString(msg));
    }


    public static void showToast(@NonNull Context context, String msg) {
        Toast.makeText(context.getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }

    public static void showToast(@NonNull Context context, int msg) {
        Toast.makeText(context.getApplicationContext(), context.getResources().getString(msg), Toast.LENGTH_LONG).show();
    }

    public static void showToastShortLength(@NonNull Context context, String msg) {
        Toast.makeText(context.getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    public static void showTryAgainToast(@NonNull Context context) {
        String msg = context.getString(R.string.server_error);
        Toast.makeText(context.getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }


    public static Snackbar showTryAgainSnackBar(@NonNull View anyView, final View.OnClickListener listener) {
        String msg = anyView.getContext().getResources().getString(R.string.server_error);
        return showRetrySnackBar(anyView, msg, listener);
    }

    public static Snackbar showTryAgainSnackBar(@NonNull View anyView) {
        String msg = anyView.getContext().getResources().getString(R.string.server_error);
        return showSnackBar(anyView, msg);
    }

    /*public static void showProgressBar(Activity activity) {
        View v = activity.findViewById(R.id.progress);
        if (v != null) {
            ProgressWheel wheel = (ProgressWheel) v;
            if (!wheel.isSpinning()) wheel.spin();
            if (v.getVisibility() == View.GONE || v.getVisibility() == View.INVISIBLE) {
                v.setVisibility(View.VISIBLE);
            }
        } else {
            Log.e("PROGRESS BAR", "progressbar is not attached");
        }
    }*/






    public static android.support.v7.app.AlertDialog getOkCancelDialog(final Context context, int msg, AlertDialog.OnClickListener yesListner) {
        return getOkCancelDialog(context, context.getResources().getString(msg), yesListner);
    }

    public static android.support.v7.app.AlertDialog getOkCancelDialog(final Context context, String msg, AlertDialog.OnClickListener yesListner) {

        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(context, R.style.AppDialog);
        builder.setMessage(msg);
        builder.setTitle(context.getString(R.string.app_name));
        builder.setPositiveButton(context.getString(R.string.text_yes), yesListner);
        builder.setNegativeButton(context.getString(R.string.text_no), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        final android.support.v7.app.AlertDialog alert = builder.create();
        alert.setMessage(msg);
        return builder.create();

    }

    public static android.support.v7.app.AlertDialog getOkCancelDialog(final Context context, AlertDialog.OnClickListener yesListner, String msg) {

        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(context, R.style.AppDialog);
        builder.setMessage(msg);
        builder.setTitle(context.getString(R.string.app_name));
        builder.setPositiveButton(context.getString(R.string.ok), yesListner);
        final android.support.v7.app.AlertDialog alert = builder.create();
        alert.setMessage(msg);
        return builder.create();

    }

    public static android.support.v7.app.AlertDialog getOkDialog(final Context context, int msg, AlertDialog.OnClickListener yesListner) {
        return getOkDialog(context, context.getResources().getString(msg), yesListner);
    }

    public static android.support.v7.app.AlertDialog getOkDialog(final Context context, String msg, AlertDialog.OnClickListener yesListner) {

        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(context, R.style.AppDialog);
        builder.setMessage(msg);
        builder.setTitle(context.getString(R.string.app_name));
        builder.setPositiveButton(context.getString(R.string.ok), yesListner);
        builder.setNegativeButton("", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        final android.support.v7.app.AlertDialog alert = builder.create();
        alert.setMessage(msg);
        return builder.create();

    }

    public static void modifyDialogBounds2(Dialog dialog) {
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        Window window = dialog.getWindow();
        lp.copyFrom(window.getAttributes());
        //This makes the dialog take up the full width
//        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.width = (int) (dialog.getContext().getResources().getDisplayMetrics().widthPixels * 0.8);
        lp.height = (int) (dialog.getContext().getResources().getDisplayMetrics().heightPixels * 0.30);
        window.setAttributes(lp);
    }

    public static android.support.v7.app.AlertDialog getOkDialog(final Context context, String title, String msg) {
        android.support.v7.app.AlertDialog.Builder builder1 = new android.support.v7.app.AlertDialog.Builder(context);
        builder1.setTitle(title);
        builder1.setMessage(msg);
        builder1.setCancelable(true);
        builder1.setNeutralButton(android.R.string.ok,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        final android.support.v7.app.AlertDialog alert = builder1.create();
        alert.setMessage(msg);
        return builder1.create();
    }


    public static android.support.v7.app.AlertDialog getSingleChoiceDialog(Context context, int title, int items, int checkedItem, final DialogInterface.OnClickListener listener) {
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(context);
        builder.setTitle(context.getResources().getString(title));
        builder.setSingleChoiceItems(items, checkedItem, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listener.onClick(dialog, which);
            }
        });
        return builder.create();
    }

    public static android.support.v7.app.AlertDialog getSingleChoiceDialog(Context context, List<? extends  Object> items, int checkedItem, final DialogInterface.OnClickListener listener) {
        int size = items.size();
        String[] itemArray = new String[size];
        for (int i = 0; i < size; i++) {
            itemArray[i] = items.get(i).toString();
        }
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(context);
        //builder.setTitle(context.getResources().getString(R.string.lbl_select_lang));

        builder.setSingleChoiceItems(itemArray, checkedItem,listener);
        return builder.create();
    }

    public static <T>android.support.v7.app.AlertDialog getMultiChoiceDialog(Context context, List<T> items, DialogInterface.OnMultiChoiceClickListener multilistener, DialogInterface.OnClickListener listener) {
        int size = items.size();
        String[] itemArray = new String[size];
        for (int i = 0; i < size; i++) {
            itemArray[i] = items.get(i).toString();
        }
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(context);
        //builder.setTitle(context.getResources().getString(R.string.lbl_select_lang));

       // builder.setSingleChoiceItems(itemArray, checkedItem,listener);

        builder.setMultiChoiceItems(itemArray,null,multilistener);
        builder.setPositiveButton(R.string.ok,listener);
       /* builder.setNegativeButton(R.string.cancel,null);*/
        return builder.create();
    }

    public static <T> android.support.v7.app.AlertDialog getListDialog(Context context, List<T> items, String title, final DialogInterface.OnClickListener listener) {
        int size = items.size();
        String[] itemArray = new String[size];
        for (int i = 0; i < size; i++) {
            itemArray[i] = items.get(i).toString();
        }
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(context, R.style.AppDialog);
        builder.setTitle(title);
        builder.setItems(itemArray, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (listener != null) listener.onClick(dialog, which);
            }
        });
        return builder.create();
    }


    public static void closeAlertDialog(android.support.v7.app.AlertDialog dialog) {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    public static void closeDialog(Dialog dialog) {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    public static void showDialog(Dialog dialog) {
        if (dialog != null && !dialog.isShowing()) {
            dialog.show();
        }
    }

    public static android.support.v7.app.AlertDialog getOkCancelDialog(Context context, int msg, int style, final DialogInterface.OnClickListener listener) {
        return getOkCancelDialog(context, msg, style, "Ok", "Cancel", listener);
    }

    public static android.support.v7.app.AlertDialog getOkCancelDialog(Context context, int msg, int style, String positiveBtn, String negetiveBtn, final DialogInterface.OnClickListener listener) {
        //android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(this, R.style.Theme_AppCompat_Dialog_Alert);
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(context, style);
        builder.setCancelable(true);
        builder.setTitle(context.getString(R.string.app_name));
        builder.setNegativeButton(positiveBtn, null);
        builder.setPositiveButton(negetiveBtn, listener);

        builder.setMessage(msg);
        //builder.setCustomTitle()
        final android.support.v7.app.AlertDialog alertDialog = builder.create();

        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {

            @Override
            public void onShow(final DialogInterface dialog) {

                Button b = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                b.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        // TODO Do something
                        listener.onClick(dialog, 1);

                    }
                });
            }
        });
        return alertDialog;
    }

    public static android.support.v7.app.AlertDialog getOkCancelDialog(final Context context, String msg, String yesText, String noText, AlertDialog.OnClickListener yesListner) {

        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(context, R.style.AppDialog);
        builder.setMessage(msg);
        builder.setTitle(context.getString(R.string.app_name));
        builder.setPositiveButton(yesText, yesListner);
        builder.setNegativeButton(noText, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        final android.support.v7.app.AlertDialog alert = builder.create();
        alert.setMessage(msg);
        return builder.create();

    }


    public static void showImagePickerDialog(Context context, final DialogInterface.OnClickListener callback) {
        final CharSequence[] items = {"Take photo", "Choose from Gallery", "Cancel"};
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(context);
        builder.setTitle("Add photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface Dialog, int item) {
                if (items[item].equals("Take photo") || items[item].equals("Choose from Gallery")) {
                    callback.onClick(Dialog, item);
                } else if (items[item].equals("Cancel")) {
                    Dialog.dismiss();
                }
            }
        });
        builder.show();

    }

    public static android.support.v7.app.AlertDialog showOkCancelDialog(Context context, String message, String ok, String cancel, final DialogInterface.OnClickListener dialogListener) {
        android.support.v7.app.AlertDialog dialog = null;
        if (context != null && context instanceof Activity && !((Activity) context).isFinishing()) {
            final android.support.v7.app.AlertDialog.Builder builder =
                    new android.support.v7.app.AlertDialog.Builder(context);
            builder.setMessage(message);
            builder.setCancelable(false);
            builder.setPositiveButton(ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogListener.onClick(dialogInterface, i);
                    dialogInterface.cancel();
                }
            });
            builder.setNegativeButton(cancel, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    dialogInterface.cancel();
                }
            });
            dialog = builder.create();
            builder.show();


        }
        return dialog;
    }


    public static void showOkDialog(Context context, String title, String message) {
        if (context != null && context instanceof Activity && !((Activity) context).isFinishing()) {

            final android.support.v7.app.AlertDialog.Builder builder =
                    new android.support.v7.app.AlertDialog.Builder(context);

            if (!TextUtils.isEmpty(title))
                builder.setTitle(title);
            builder.setMessage(message);
            builder.setPositiveButton(context.getString(android.R.string.ok), null);

            builder.show();
        }
    }

    public static void invisibleView(View v) {
        v.setVisibility(View.INVISIBLE);
    }

    public static void showRotateLoading(RotateLoading progress) {
        Utils.showView(progress);
        progress.start();

    }

    public static void hideRotateLoading(RotateLoading progress) {
        progress.stop();
        Utils.hideView(progress);
    }

    public static void closeDialogFragment(DialogFragment dialogFragment) {
        if (dialogFragment != null) dialogFragment.dismiss();
    }

    public static android.support.v7.app.AlertDialog getChooseImageDialog(Context ctx, DialogInterface.OnClickListener callback) {
        android.support.v7.app.AlertDialog alertDialog = new android.support.v7.app.AlertDialog.Builder(ctx).create();
        alertDialog.setTitle(ctx.getString(R.string.single_image));
        alertDialog.setMessage(ctx.getString(R.string.select_picture_source));
       // alertDialog.setIcon(R.drawable.ic_launcher);
        alertDialog.setButton(android.support.v7.app.AlertDialog.BUTTON_NEGATIVE, ctx.getString(R.string.gallery), callback);
        alertDialog.setButton(android.support.v7.app.AlertDialog.BUTTON_POSITIVE, ctx.getString(R.string.camera), callback);
        return alertDialog;
    }
}
