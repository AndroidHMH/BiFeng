// Generated code from Butter Knife. Do not modify!
package com.coinwind.bifeng.ui.share.activity;

import android.support.annotation.UiThread;
import android.view.View;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.coinwind.bifeng.R;
import com.coinwind.bifeng.base.BaseActivity_ViewBinding;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ShareActivity_ViewBinding extends BaseActivity_ViewBinding {
  private ShareActivity target;

  private View view2131231118;

  private View view2131231120;

  private View view2131231203;

  @UiThread
  public ShareActivity_ViewBinding(ShareActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ShareActivity_ViewBinding(final ShareActivity target, View source) {
    super(target, source);

    this.target = target;

    View view;
    target.titleTitleTv = Utils.findRequiredViewAsType(source, R.id.title_title_tv, "field 'titleTitleTv'", TextView.class);
    target.titleBar = Utils.findRequiredViewAsType(source, R.id.title_bar, "field 'titleBar'", RelativeLayout.class);
    target.shareWebView = Utils.findRequiredViewAsType(source, R.id.share_web_view, "field 'shareWebView'", WebView.class);
    view = Utils.findRequiredView(source, R.id.share_fen_xiang_btn, "field 'shareFenXiangBtn' and method 'onViewClicked'");
    target.shareFenXiangBtn = Utils.castView(view, R.id.share_fen_xiang_btn, "field 'shareFenXiangBtn'", LinearLayout.class);
    view2131231118 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.shareAllLayout = Utils.findRequiredViewAsType(source, R.id.share_all_layout, "field 'shareAllLayout'", LinearLayout.class);
    view = Utils.findRequiredView(source, R.id.share_next_btn, "field 'shareNextBtn' and method 'onViewClicked'");
    target.shareNextBtn = Utils.castView(view, R.id.share_next_btn, "field 'shareNextBtn'", LinearLayout.class);
    view2131231120 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
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
  }

  @Override
  public void unbind() {
    ShareActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.titleTitleTv = null;
    target.titleBar = null;
    target.shareWebView = null;
    target.shareFenXiangBtn = null;
    target.shareAllLayout = null;
    target.shareNextBtn = null;
    target.titleLayoutReturnBtn = null;
    target.titleBarLayout = null;

    view2131231118.setOnClickListener(null);
    view2131231118 = null;
    view2131231120.setOnClickListener(null);
    view2131231120 = null;
    view2131231203.setOnClickListener(null);
    view2131231203 = null;

    super.unbind();
  }
}
