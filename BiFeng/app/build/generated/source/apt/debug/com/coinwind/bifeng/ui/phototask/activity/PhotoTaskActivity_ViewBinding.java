// Generated code from Butter Knife. Do not modify!
package com.coinwind.bifeng.ui.phototask.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.coinwind.bifeng.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class PhotoTaskActivity_ViewBinding implements Unbinder {
  private PhotoTaskActivity target;

  private View view2131165350;

  @UiThread
  public PhotoTaskActivity_ViewBinding(PhotoTaskActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public PhotoTaskActivity_ViewBinding(final PhotoTaskActivity target, View source) {
    this.target = target;

    View view;
    target.titleTitleTv = Utils.findRequiredViewAsType(source, R.id.title_title_tv, "field 'titleTitleTv'", TextView.class);
    target.titleBar = Utils.findRequiredViewAsType(source, R.id.title_bar, "field 'titleBar'", RelativeLayout.class);
    view = Utils.findRequiredView(source, R.id.photo_task_next_btn, "field 'photoTaskNextBtn' and method 'onViewClicked'");
    target.photoTaskNextBtn = Utils.castView(view, R.id.photo_task_next_btn, "field 'photoTaskNextBtn'", LinearLayout.class);
    view2131165350 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    PhotoTaskActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.titleTitleTv = null;
    target.titleBar = null;
    target.photoTaskNextBtn = null;

    view2131165350.setOnClickListener(null);
    view2131165350 = null;
  }
}
