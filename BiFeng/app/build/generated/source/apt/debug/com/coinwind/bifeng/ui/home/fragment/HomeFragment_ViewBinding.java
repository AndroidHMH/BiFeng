// Generated code from Butter Knife. Do not modify!
package com.coinwind.bifeng.ui.home.fragment;

import android.support.annotation.UiThread;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.internal.Utils;
import com.coinwind.bifeng.R;
import com.coinwind.bifeng.base.BaseFragment_ViewBinding;
import java.lang.IllegalStateException;
import java.lang.Override;

public class HomeFragment_ViewBinding extends BaseFragment_ViewBinding {
  private HomeFragment target;

  @UiThread
  public HomeFragment_ViewBinding(HomeFragment target, View source) {
    super(target, source);

    this.target = target;

    target.mainList = Utils.findRequiredViewAsType(source, R.id.main_list, "field 'mainList'", ListView.class);
    target.titleLayoutReturnBtn = Utils.findRequiredViewAsType(source, R.id.title_layout_return_btn, "field 'titleLayoutReturnBtn'", LinearLayout.class);
    target.titleTitleTv = Utils.findRequiredViewAsType(source, R.id.title_title_tv, "field 'titleTitleTv'", TextView.class);
  }

  @Override
  public void unbind() {
    HomeFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mainList = null;
    target.titleLayoutReturnBtn = null;
    target.titleTitleTv = null;

    super.unbind();
  }
}
