package com.coinwind.bifeng.ui.setting.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.coinwind.bifeng.R;
import com.coinwind.bifeng.base.NoNetworkBaseActivity;
import com.coinwind.bifeng.view.headimgview.ClipViewLayout;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ClipActivity extends NoNetworkBaseActivity {

    @BindView(R.id.clip_return_btn)
    LinearLayout clipReturnBtn;
    @BindView(R.id.clip_ok_btn)
    LinearLayout clipOkBtn;
    @BindView(R.id.clip_clip_view)
    ClipViewLayout clipView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_clip;
    }

    @Override
    protected void init() {
        String type = getIntent().getStringExtra("type");
        if ("1".equals(type)) {
            clipView.setImageSrc(getIntent().getData());
        } else {
            clipView.setImageUri(getIntent().getData());
        }
    }

    @OnClick({R.id.clip_return_btn, R.id.clip_ok_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.clip_return_btn:
                finish();
                break;
            case R.id.clip_ok_btn:
                generateUriAndReturn();
                break;
        }
    }

    /**
     * 生成Uri并且通过setResult返回给打开的activity
     */
    private void generateUriAndReturn() {
        //调用返回剪切图
        Bitmap zoomedCropBitmap = clipView.clip();
        if (zoomedCropBitmap == null) {
            Log.e("android", "zoomedCropBitmap == null");
            return;
        }
        Uri mSaveUri = Uri.fromFile(new File(getCacheDir(), "cropped_" + System.currentTimeMillis() + ".jpg"));
        if (mSaveUri != null) {
            OutputStream outputStream = null;
            try {
                outputStream = getContentResolver().openOutputStream(mSaveUri);
                if (outputStream != null) {
                    zoomedCropBitmap.compress(Bitmap.CompressFormat.JPEG, 90, outputStream);
                }
            } catch (IOException ex) {
                Log.e("android", "Cannot open file: " + mSaveUri, ex);
            } finally {
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            Intent intent = new Intent();
            intent.setData(mSaveUri);
            setResult(RESULT_OK, intent);
            finish();
        }
    }
}
