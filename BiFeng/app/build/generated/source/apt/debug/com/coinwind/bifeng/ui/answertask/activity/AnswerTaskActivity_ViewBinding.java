// Generated code from Butter Knife. Do not modify!
package com.coinwind.bifeng.ui.answertask.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.coinwind.bifeng.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AnswerTaskActivity_ViewBinding implements Unbinder {
  private AnswerTaskActivity target;

  private View view2131165225;

  private View view2131165231;

  @UiThread
  public AnswerTaskActivity_ViewBinding(AnswerTaskActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public AnswerTaskActivity_ViewBinding(final AnswerTaskActivity target, View source) {
    this.target = target;

    View view;
    target.titleTitleTv = Utils.findRequiredViewAsType(source, R.id.title_title_tv, "field 'titleTitleTv'", TextView.class);
    target.titleBar = Utils.findRequiredViewAsType(source, R.id.title_bar, "field 'titleBar'", RelativeLayout.class);
    target.answerTaskImg = Utils.findRequiredViewAsType(source, R.id.answer_task_img, "field 'answerTaskImg'", ImageView.class);
    target.answerTaskTitleTv = Utils.findRequiredViewAsType(source, R.id.answer_task_title_tv, "field 'answerTaskTitleTv'", TextView.class);
    target.answerTaskCountTv = Utils.findRequiredViewAsType(source, R.id.answer_task_count_tv, "field 'answerTaskCountTv'", TextView.class);
    target.answerTaskNameTv = Utils.findRequiredViewAsType(source, R.id.answer_task_name_tv, "field 'answerTaskNameTv'", TextView.class);
    target.answerTaskHuisuoImg = Utils.findRequiredViewAsType(source, R.id.answer_task_huisuo_img, "field 'answerTaskHuisuoImg'", ImageView.class);
    target.answerTaskHuiSuoTv = Utils.findRequiredViewAsType(source, R.id.answer_task_hui_suo_tv, "field 'answerTaskHuiSuoTv'", TextView.class);
    target.answerTaskTypeImg = Utils.findRequiredViewAsType(source, R.id.answer_task_type_img, "field 'answerTaskTypeImg'", ImageView.class);
    target.answerTaskTypeTv = Utils.findRequiredViewAsType(source, R.id.answer_task_type_tv, "field 'answerTaskTypeTv'", TextView.class);
    target.answerTaskShengyuImg = Utils.findRequiredViewAsType(source, R.id.answer_task_shengyu_img, "field 'answerTaskShengyuImg'", ImageView.class);
    target.answerTaskShengyuTv = Utils.findRequiredViewAsType(source, R.id.answer_task_shengyu_tv, "field 'answerTaskShengyuTv'", TextView.class);
    target.answerTaskTimeImg = Utils.findRequiredViewAsType(source, R.id.answer_task_time_img, "field 'answerTaskTimeImg'", ImageView.class);
    target.answerTaskTimeTv = Utils.findRequiredViewAsType(source, R.id.answer_task_time_tv, "field 'answerTaskTimeTv'", TextView.class);
    target.answerTaskRenWuContentTv = Utils.findRequiredViewAsType(source, R.id.answer_task_ren_wu_content_tv, "field 'answerTaskRenWuContentTv'", TextView.class);
    target.answerTaskGuZhuContentTv = Utils.findRequiredViewAsType(source, R.id.answer_task_gu_zhu_content_tv, "field 'answerTaskGuZhuContentTv'", TextView.class);
    target.answerTaskZhengCountTv = Utils.findRequiredViewAsType(source, R.id.answer_task_zheng_count_tv, "field 'answerTaskZhengCountTv'", TextView.class);
    view = Utils.findRequiredView(source, R.id.answer_task_fen_xiang_btn, "field 'answerTaskFenXiangBtn' and method 'onViewClicked'");
    target.answerTaskFenXiangBtn = Utils.castView(view, R.id.answer_task_fen_xiang_btn, "field 'answerTaskFenXiangBtn'", LinearLayout.class);
    view2131165225 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.answerTaskRenWuTv = Utils.findRequiredViewAsType(source, R.id.answer_task_ren_wu_tv, "field 'answerTaskRenWuTv'", TextView.class);
    view = Utils.findRequiredView(source, R.id.answer_task_ren_wu_btn, "field 'answerTaskRenWuBtn' and method 'onViewClicked'");
    target.answerTaskRenWuBtn = Utils.castView(view, R.id.answer_task_ren_wu_btn, "field 'answerTaskRenWuBtn'", LinearLayout.class);
    view2131165231 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    AnswerTaskActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.titleTitleTv = null;
    target.titleBar = null;
    target.answerTaskImg = null;
    target.answerTaskTitleTv = null;
    target.answerTaskCountTv = null;
    target.answerTaskNameTv = null;
    target.answerTaskHuisuoImg = null;
    target.answerTaskHuiSuoTv = null;
    target.answerTaskTypeImg = null;
    target.answerTaskTypeTv = null;
    target.answerTaskShengyuImg = null;
    target.answerTaskShengyuTv = null;
    target.answerTaskTimeImg = null;
    target.answerTaskTimeTv = null;
    target.answerTaskRenWuContentTv = null;
    target.answerTaskGuZhuContentTv = null;
    target.answerTaskZhengCountTv = null;
    target.answerTaskFenXiangBtn = null;
    target.answerTaskRenWuTv = null;
    target.answerTaskRenWuBtn = null;

    view2131165225.setOnClickListener(null);
    view2131165225 = null;
    view2131165231.setOnClickListener(null);
    view2131165231 = null;
  }
}
