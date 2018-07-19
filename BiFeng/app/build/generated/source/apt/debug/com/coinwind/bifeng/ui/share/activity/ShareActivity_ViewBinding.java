// Generated code from Butter Knife. Do not modify!
package com.coinwind.bifeng.ui.share.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.coinwind.bifeng.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ShareActivity_ViewBinding implements Unbinder {
  private ShareActivity target;

  private View view2131165366;

  private View view2131165367;

  @UiThread
  public ShareActivity_ViewBinding(ShareActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ShareActivity_ViewBinding(final ShareActivity target, View source) {
    this.target = target;

    View view;
    target.titleTitleTv = Utils.findRequiredViewAsType(source, R.id.title_title_tv, "field 'titleTitleTv'", TextView.class);
    target.titleBar = Utils.findRequiredViewAsType(source, R.id.title_bar, "field 'titleBar'", RelativeLayout.class);
    target.shareWebView = Utils.findRequiredViewAsType(source, R.id.share_web_view, "field 'shareWebView'", WebView.class);
    view = Utils.findRequiredView(source, R.id.share_fen_xiang_btn, "field 'shareFenXiangBtn' and method 'onViewClicked'");
    target.shareFenXiangBtn = Utils.castView(view, R.id.share_fen_xiang_btn, "field 'shareFenXiangBtn'", LinearLayout.class);
    view2131165366 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.share_next_btn, "field 'shareNextBtn' and method 'onViewClicked'");
    target.shareNextBtn = Utils.castView(view, R.id.share_next_btn, "field 'shareNextBtn'", LinearLayout.class);
    view2131165367 = view;
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
    ShareActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.titleTitleTv = null;
    target.titleBar = null;
    target.shareWebView = null;
    target.shareFenXiangBtn = null;
    target.shareNextBtn = null;

    view2131165366.setOnClickListener(null);
    view2131165366 = null;
    view2131165367.setOnClickListener(null);
    view2131165367 = null;
  }
}
