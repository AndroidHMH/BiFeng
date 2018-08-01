// Generated code from Butter Knife. Do not modify!
package com.coinwind.bifeng.ui.homepage.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.coinwind.bifeng.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MainActivity_ViewBinding implements Unbinder {
  private MainActivity target;

  private View view2131165344;

  private View view2131165352;

  private View view2131165349;

  @UiThread
  public MainActivity_ViewBinding(MainActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MainActivity_ViewBinding(final MainActivity target, View source) {
    this.target = target;

    View view;
    target.mainHomeImg = Utils.findRequiredViewAsType(source, R.id.main_home_img, "field 'mainHomeImg'", ImageView.class);
    target.mainHomeTv = Utils.findRequiredViewAsType(source, R.id.main_home_tv, "field 'mainHomeTv'", TextView.class);
    view = Utils.findRequiredView(source, R.id.main_home_btn, "field 'mainHomeBtn' and method 'onViewClicked'");
    target.mainHomeBtn = Utils.castView(view, R.id.main_home_btn, "field 'mainHomeBtn'", LinearLayout.class);
    view2131165344 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.mainTaskImg = Utils.findRequiredViewAsType(source, R.id.main_task_img, "field 'mainTaskImg'", ImageView.class);
    target.mainTaskTv = Utils.findRequiredViewAsType(source, R.id.main_task_tv, "field 'mainTaskTv'", TextView.class);
    view = Utils.findRequiredView(source, R.id.main_task_btn, "field 'mainTaskBtn' and method 'onViewClicked'");
    target.mainTaskBtn = Utils.castView(view, R.id.main_task_btn, "field 'mainTaskBtn'", LinearLayout.class);
    view2131165352 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.mainMyImg = Utils.findRequiredViewAsType(source, R.id.main_my_img, "field 'mainMyImg'", ImageView.class);
    target.mainMyTv = Utils.findRequiredViewAsType(source, R.id.main_my_tv, "field 'mainMyTv'", TextView.class);
    view = Utils.findRequiredView(source, R.id.main_my_btn, "field 'mainMyBtn' and method 'onViewClicked'");
    target.mainMyBtn = Utils.castView(view, R.id.main_my_btn, "field 'mainMyBtn'", LinearLayout.class);
    view2131165349 = view;
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
    MainActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mainHomeImg = null;
    target.mainHomeTv = null;
    target.mainHomeBtn = null;
    target.mainTaskImg = null;
    target.mainTaskTv = null;
    target.mainTaskBtn = null;
    target.mainMyImg = null;
    target.mainMyTv = null;
    target.mainMyBtn = null;

    view2131165344.setOnClickListener(null);
    view2131165344 = null;
    view2131165352.setOnClickListener(null);
    view2131165352 = null;
    view2131165349.setOnClickListener(null);
    view2131165349 = null;
  }
}
