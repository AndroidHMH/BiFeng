// Generated code from Butter Knife. Do not modify!
package com.coinwind.bifeng.ui.home.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.coinwind.bifeng.R;
import com.coinwind.bifeng.view.XCFlowLayout;
import com.makeramen.roundedimageview.RoundedImageView;
import com.recker.flybanner.FlyBanner;
import java.lang.IllegalStateException;
import java.lang.Override;

public class HomeFragment_ViewBinding implements Unbinder {
  private HomeFragment target;

  private View view2131165294;

  private View view2131165295;

  private View view2131165284;

  private View view2131165293;

  private View view2131165282;

  private View view2131165280;

  private View view2131165283;

  private View view2131165287;

  @UiThread
  public HomeFragment_ViewBinding(final HomeFragment target, View source) {
    this.target = target;

    View view;
    target.homeBanner = Utils.findRequiredViewAsType(source, R.id.home_banner, "field 'homeBanner'", FlyBanner.class);
    view = Utils.findRequiredView(source, R.id.home_zhaufna_btn, "field 'homeZhaufnaBtn' and method 'onViewClicked'");
    target.homeZhaufnaBtn = Utils.castView(view, R.id.home_zhaufna_btn, "field 'homeZhaufnaBtn'", LinearLayout.class);
    view2131165294 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.home_zhuce_btn, "field 'homeZhuceBtn' and method 'onViewClicked'");
    target.homeZhuceBtn = Utils.castView(view, R.id.home_zhuce_btn, "field 'homeZhuceBtn'", LinearLayout.class);
    view2131165295 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.home_pinglun_btn, "field 'homePinglunBtn' and method 'onViewClicked'");
    target.homePinglunBtn = Utils.castView(view, R.id.home_pinglun_btn, "field 'homePinglunBtn'", LinearLayout.class);
    view2131165284 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.home_zhangfen_btn, "field 'homeZhangfenBtn' and method 'onViewClicked'");
    target.homeZhangfenBtn = Utils.castView(view, R.id.home_zhangfen_btn, "field 'homeZhangfenBtn'", LinearLayout.class);
    view2131165293 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.home_jiaoyan_btn, "field 'homeJiaoyanBtn' and method 'onViewClicked'");
    target.homeJiaoyanBtn = Utils.castView(view, R.id.home_jiaoyan_btn, "field 'homeJiaoyanBtn'", LinearLayout.class);
    view2131165282 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.home_dati_btn, "field 'homeDatiBtn' and method 'onViewClicked'");
    target.homeDatiBtn = Utils.castView(view, R.id.home_dati_btn, "field 'homeDatiBtn'", LinearLayout.class);
    view2131165280 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.home_paizhao_btn, "field 'homePaizhaoBtn' and method 'onViewClicked'");
    target.homePaizhaoBtn = Utils.castView(view, R.id.home_paizhao_btn, "field 'homePaizhaoBtn'", LinearLayout.class);
    view2131165283 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.homeGuangBoContentTv = Utils.findRequiredViewAsType(source, R.id.home_guang_bo_content_tv, "field 'homeGuangBoContentTv'", TextView.class);
    target.homeQiangUserIconImg = Utils.findRequiredViewAsType(source, R.id.home_qiang_user_icon_img, "field 'homeQiangUserIconImg'", RoundedImageView.class);
    target.homeQiangUserNameTv = Utils.findRequiredViewAsType(source, R.id.home_qiang_user_name_tv, "field 'homeQiangUserNameTv'", TextView.class);
    target.homeQiangCountTv = Utils.findRequiredViewAsType(source, R.id.home_qiang_count_tv, "field 'homeQiangCountTv'", TextView.class);
    target.homeQiangIconImg = Utils.findRequiredViewAsType(source, R.id.home_qiang_icon_img, "field 'homeQiangIconImg'", ImageView.class);
    target.homeQiangTitleTv = Utils.findRequiredViewAsType(source, R.id.home_qiang_title_tv, "field 'homeQiangTitleTv'", TextView.class);
    target.homeXcfLauout = Utils.findRequiredViewAsType(source, R.id.home_xcf_lauout, "field 'homeXcfLauout'", XCFlowLayout.class);
    target.homeTuiJianList = Utils.findRequiredViewAsType(source, R.id.home_tui_jian_list, "field 'homeTuiJianList'", RecyclerView.class);
    view = Utils.findRequiredView(source, R.id.home_qiang_ren_wu_btn, "method 'onViewClicked'");
    view2131165287 = view;
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
    HomeFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.homeBanner = null;
    target.homeZhaufnaBtn = null;
    target.homeZhuceBtn = null;
    target.homePinglunBtn = null;
    target.homeZhangfenBtn = null;
    target.homeJiaoyanBtn = null;
    target.homeDatiBtn = null;
    target.homePaizhaoBtn = null;
    target.homeGuangBoContentTv = null;
    target.homeQiangUserIconImg = null;
    target.homeQiangUserNameTv = null;
    target.homeQiangCountTv = null;
    target.homeQiangIconImg = null;
    target.homeQiangTitleTv = null;
    target.homeXcfLauout = null;
    target.homeTuiJianList = null;

    view2131165294.setOnClickListener(null);
    view2131165294 = null;
    view2131165295.setOnClickListener(null);
    view2131165295 = null;
    view2131165284.setOnClickListener(null);
    view2131165284 = null;
    view2131165293.setOnClickListener(null);
    view2131165293 = null;
    view2131165282.setOnClickListener(null);
    view2131165282 = null;
    view2131165280.setOnClickListener(null);
    view2131165280 = null;
    view2131165283.setOnClickListener(null);
    view2131165283 = null;
    view2131165287.setOnClickListener(null);
    view2131165287 = null;
  }
}
