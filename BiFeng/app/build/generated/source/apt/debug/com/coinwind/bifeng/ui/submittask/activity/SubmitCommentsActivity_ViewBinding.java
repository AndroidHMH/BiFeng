// Generated code from Butter Knife. Do not modify!
package com.coinwind.bifeng.ui.submittask.activity;

import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.coinwind.bifeng.R;
import com.coinwind.bifeng.base.BaseActivity_ViewBinding;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SubmitCommentsActivity_ViewBinding extends BaseActivity_ViewBinding {
  private SubmitCommentsActivity target;

  private View view2131230751;

  private View view2131231155;

  private View view2131231203;

  @UiThread
  public SubmitCommentsActivity_ViewBinding(SubmitCommentsActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SubmitCommentsActivity_ViewBinding(final SubmitCommentsActivity target, View source) {
    super(target, source);

    this.target = target;

    View view;
    target.titleTitleTv = Utils.findRequiredViewAsType(source, R.id.title_title_tv, "field 'titleTitleTv'", TextView.class);
    target.titleBar = Utils.findRequiredViewAsType(source, R.id.title_bar, "field 'titleBar'", RelativeLayout.class);
    target.submitCommentsDescriptionEt = Utils.findRequiredViewAsType(source, R.id.submit_comments_description_et, "field 'submitCommentsDescriptionEt'", EditText.class);
    target.submitCommentsPhoneEt = Utils.findRequiredViewAsType(source, R.id.submit_comments_phone_et, "field 'submitCommentsPhoneEt'", EditText.class);
    target.submitCommentsTextCountTv = Utils.findRequiredViewAsType(source, R.id.submit_comments_text_count_tv, "field 'submitCommentsTextCountTv'", TextView.class);
    view = Utils.findRequiredView(source, R.id.add_img_btn, "field 'addImgBtn' and method 'onViewClicked'");
    target.addImgBtn = Utils.castView(view, R.id.add_img_btn, "field 'addImgBtn'", ImageView.class);
    view2131230751 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.submitCommentsPhotoCountTv = Utils.findRequiredViewAsType(source, R.id.submit_comments_photo_count_tv, "field 'submitCommentsPhotoCountTv'", TextView.class);
    view = Utils.findRequiredView(source, R.id.submit_comments_submit_btn, "field 'submitCommentsSubmitBtn' and method 'onViewClicked'");
    target.submitCommentsSubmitBtn = Utils.castView(view, R.id.submit_comments_submit_btn, "field 'submitCommentsSubmitBtn'", TextView.class);
    view2131231155 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.submitCommentsImgRecycler = Utils.findRequiredViewAsType(source, R.id.submit_comments_img_recycler, "field 'submitCommentsImgRecycler'", RecyclerView.class);
    view = Utils.findRequiredView(source, R.id.title_layout_return_btn, "field 'titleLayoutReturnBtn' and method 'onViewClicked'");
    target.titleLayoutReturnBtn = Utils.castView(view, R.id.title_layout_return_btn, "field 'titleLayoutReturnBtn'", LinearLayout.class);
    view2131231203 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.titleBarLayout = Utils.findRequiredViewAsType(source, R.id.title_bar_layout, "field 'titleBarLayout'", LinearLayout.class);
    target.submitCommentsAllLayout = Utils.findRequiredViewAsType(source, R.id.submit_comments_all_layout, "field 'submitCommentsAllLayout'", LinearLayout.class);
    target.submitCommentsShuoMingTv = Utils.findRequiredViewAsType(source, R.id.submit_comments_shuo_ming_tv, "field 'submitCommentsShuoMingTv'", TextView.class);
    target.submitCommentsShuCuLayout = Utils.findRequiredViewAsType(source, R.id.submit_comments_shu_cu_layout, "field 'submitCommentsShuCuLayout'", LinearLayout.class);
  }

  @Override
  public void unbind() {
    SubmitCommentsActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.titleTitleTv = null;
    target.titleBar = null;
    target.submitCommentsDescriptionEt = null;
    target.submitCommentsPhoneEt = null;
    target.submitCommentsTextCountTv = null;
    target.addImgBtn = null;
    target.submitCommentsPhotoCountTv = null;
    target.submitCommentsSubmitBtn = null;
    target.submitCommentsImgRecycler = null;
    target.titleLayoutReturnBtn = null;
    target.titleBarLayout = null;
    target.submitCommentsAllLayout = null;
    target.submitCommentsShuoMingTv = null;
    target.submitCommentsShuCuLayout = null;

    view2131230751.setOnClickListener(null);
    view2131230751 = null;
    view2131231155.setOnClickListener(null);
    view2131231155 = null;
    view2131231203.setOnClickListener(null);
    view2131231203 = null;

    super.unbind();
  }
}
