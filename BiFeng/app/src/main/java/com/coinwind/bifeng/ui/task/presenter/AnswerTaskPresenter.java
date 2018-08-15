package com.coinwind.bifeng.ui.task.presenter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import com.coinwind.bifeng.app.BFApplication;
import com.coinwind.bifeng.config.Codes;
import com.coinwind.bifeng.config.SpHelp;
import com.coinwind.bifeng.config.Urls;
import com.coinwind.bifeng.model.http.RetrofitHelp;
import com.coinwind.bifeng.ui.submittask.config.PhotoHelp;
import com.coinwind.bifeng.ui.task.bean.ShareImgBean;
import com.coinwind.bifeng.ui.task.bean.TaskIdsBean;
import com.coinwind.bifeng.ui.task.biz.AnswerTaskService;
import com.coinwind.bifeng.ui.task.contract.AnswerTaskContract;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * 任务详情页面的P层
 */
public class AnswerTaskPresenter implements AnswerTaskContract.Presenter {

    private AnswerTaskService service;
    private AnswerTaskContract.View view;

    public AnswerTaskPresenter() {
        service = RetrofitHelp.getInstance().getService(AnswerTaskService.class);
    }

    @Override
    public void loadTaskIds(String taskId) {
        service.loadTaskIds(taskId).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TaskIdsBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(TaskIdsBean taskIdsBean) {
                        int code = taskIdsBean.getCode();
                        List<TaskIdsBean.DataBean> data = taskIdsBean.getData();
                        if (code == Codes.SUCCESS_CODE && data != null) {
                            if (data.size() != 0) {
                                view.showTaskIds(data);
                            } else {
                                view.showTaskIdsError();
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showTaskIdsError();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
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
    public void loadShareImg(String taskId, String userId) {
        String content = Urls.SHARE_IMG_HTML + "?userId=" + userId + "&taskId=" + taskId;
        service.loadShareImg(taskId, userId, content, SpHelp.getSign()).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ShareImgBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ShareImgBean shareImgBean) {
                        int code = shareImgBean.getCode();
                        ShareImgBean.DataBean data = shareImgBean.getData();
                        if (code == Codes.SUCCESS_CODE && data != null) {
                            view.showSuccess(data.getUrl());
                        } else {
                            view.showError("获取图片失败");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showError("获取图片失败");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void actualView(AnswerTaskContract.View view) {
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
