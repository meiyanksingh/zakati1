package com.mks.zakati.activities;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.luminous.pick.controller.MediaFactory;
import com.mks.zakati.R;
import com.mks.zakati.base.BaseToolBarActivity;
import com.mks.zakati.utils.DialogUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class SetProfilePicActivity extends BaseToolBarActivity {
    @BindView(R.id.flImage)
    FrameLayout flImage;
    @BindView(R.id.ivProfilePic)
    SimpleDraweeView ivProfilePic;
    private String mFrom;
    private MediaFactory mediaFactory;

    @Override
    public void initializeUi() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_set_profile_pic;
    }

    @OnClick({R.id.next, R.id.flImage})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.flImage:
                showImageChooserDialog();
                break;
            case R.id.next:
                Bundle bundle=getIntent().getExtras();
                break;
        }

    }


    private void showImageChooserDialog() {
        DialogUtil.getChooseImageDialog(this, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mFrom = which == AlertDialog.BUTTON_NEGATIVE ? getString(R.string.gallery) : getString(R.string.camera);
                checkPermissions();
            }

        }).show();
    }

    private void checkPermissions() {
        Dexter.withActivity(this)
                .withPermissions(
                        Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                ).withListener(new MultiplePermissionsListener() {
            @Override
            public void onPermissionsChecked(MultiplePermissionsReport report) {

                if (report.areAllPermissionsGranted()) {

                    MediaFactory.MediaBuilder mediaBuilder = new MediaFactory.MediaBuilder(mThis)
                            .getSingleMediaFiles()
                            .setPickCount(1);
                    if (mFrom.equalsIgnoreCase(getString(R.string.gallery))) {
                        mediaBuilder.fromGallery();
                    } else if (mFrom.equalsIgnoreCase(getString(R.string.camera))) {
                        mediaBuilder.fromCamera()
                                .doCropping();
                    }
                    mediaFactory = MediaFactory.create().start(mediaBuilder);
                }

            }

            @Override
            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                token.continuePermissionRequest();
            }
        }).check();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (mediaFactory != null) {
            ArrayList<String> all_path = mediaFactory.onActivityResult(requestCode, resultCode, data);
            if (all_path != null && !all_path.isEmpty()) {
                ivProfilePic.setImageURI(all_path.get(0));
            }

        }


    }

}
