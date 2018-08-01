package com.coinwind.bifeng.ui.submittask.config;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 管理提交任务图片
 */
public class UpdateFile {
    private List<File> updateFiles;

    public UpdateFile() {
        updateFiles = new ArrayList<>();
    }

    /**
     * 添加文件
     *
     * @param file
     */
    public void addFile(File file) {
        updateFiles.add(file);
    }

    /**
     * 删除图片
     *
     * @param position
     */
    public void removeFile(int position) {
        updateFiles.remove(position);
    }
}
