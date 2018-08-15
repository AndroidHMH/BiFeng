package com.coinwind.bifeng.ui.share.presenter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import com.coinwind.bifeng.app.BFApplication;
import com.coinwind.bifeng.model.http.RetrofitHelp;
import com.coinwind.bifeng.ui.share.contract.InvitationContract;
import com.coinwind.bifeng.ui.task.biz.AnswerTaskService;
import com.coinwind.bifeng.ui.task.contract.AnswerTaskContract;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class InvitationPresenter implements InvitationContract.Presenter {
    private AnswerTaskService service;
    private InvitationContract.View view;

    public InvitationPresenter() {
        service = RetrofitHelp.getInstance().getService(AnswerTaskService.class);
    }

    @Override
    public void loadImg(String imgUrl) {
        service.loadImg(imgUrl).subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        InputStream is = responseBody.byteStream();
                        Bitmap bitmap = BitmapFactory.decodeStream(is);
                        saveBitmap(bitmap);
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showImgError();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void actualView(InvitationContract.View view) {
        this.view = view;
    }

    @Override
    public void unActualView() {
        this.view = null;
    }
    /**
     * 保存到图库
     *
     * @param bitmap
     */
    private void saveBitmap(Bitmap bitmap) {
        // 首先保存图片
        File appDir = new File(Environment.getExternalStorageDirectory(), "bfphone");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = System.currentTimeMillis() + ".png";
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 把文件插入到系统图库
        try {
            MediaStore.Images.Media.insertImage(BFApplication.context.getContentResolver(), file.getAbsolutePath(), fileName, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // 通知图库更新
        BFApplication.context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + "/sdcard/namecard/")));
        view.showImgLoad();
    }

}
