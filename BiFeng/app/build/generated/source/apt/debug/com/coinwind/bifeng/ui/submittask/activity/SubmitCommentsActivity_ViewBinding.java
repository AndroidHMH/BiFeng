// Generated code from Butter Knife. Do not modify!
package com.coinwind.bifeng.ui.submittask.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.coinwind.bifeng.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SubmitCommentsActivity_ViewBinding implements Unbinder {
  private SubmitCommentsActivity target;

  private View view2131165219;

  private View view2131165388;

  @UiThread
  public SubmitCommentsActivity_ViewBinding(SubmitCommentsActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SubmitCommentsActivity_ViewBinding(final SubmitCommentsActivity target, View source) {
    this.target = target;

    View view;
    target.titleTitleTv = Utils.findRequiredViewAsType(source, R.id.title_title_tv, "field 'titleTitleTv'", TextView.class);
    target.titleBar = Utils.findRequiredViewAsType(source, R.id.title_bar, "field 'titleBar'", RelativeLayout.class);
    target.submitCommentsDescriptionEt = Utils.findRequiredViewAsType(source, R.id.submit_comments_description_et, "field 'submitCommentsDescriptionEt'", EditText.class);
    target.submitCommentsTextCountTv = Utils.findRequiredViewAsType(source, R.id.submit_comments_text_count_tv, "field 'submitCommentsTextCountTv'", TextView.class);
    view = Utils.findRequiredView(source, R.id.add_img_btn, "field 'addImgBtn' and method 'onViewClicked'");
    target.addImgBtn = Utils.castView(view, R.id.add_img_btn, "field 'addImgBtn'", ImageView.class);
    view2131165219 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.submitCommentsPhotoCountTv = Utils.findRequiredViewAsType(source, R.id.submit_comments_photo_count_tv, "field 'submitCommentsPhotoCountTv'", TextView.class);
    view = Utils.findRequiredView(source, R.id.submit_comments_submit_btn, "field 'submitCommentsSubmitBtn' and method 'onViewClicked'");
    target.submitCommentsSubmitBtn = Utils.castView(view, R.id.submit_comments_submit_btn, "field 'submitCommentsSubmitBtn'", TextView.class);
    view2131165388 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.submitCommentsImgRecycler = Utils.findRequiredViewAsType(source, R.id.submit_comments_img_recycler, "field 'submitCommentsImgRecycler'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    SubmitCommentsActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.titleTitleTv = null;
    target.titleBar = null;
    target.submitCommentsDescriptionEt = null;
    target.submitCommentsTextCountTv = null;
    target.addImgBtn = null;
    target.submitCommentsPhotoCountTv = null;
    target.submitCommentsSubmitBtn = null;
    target.submitCommentsImgRecycler = null;

    view2131165219.setOnClickListener(null);
    view2131165219 = null;
    view2131165388.setOnClickListener(null);
    view2131165388 = null;
  }
}
