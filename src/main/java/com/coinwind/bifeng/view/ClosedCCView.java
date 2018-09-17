package com.coinwind.bifeng.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.coinwind.bifeng.R;
import com.coinwind.bifeng.config.TimeUtils;
import com.coinwind.bifeng.ui.home.bean.HomeItemCCBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ClosedCCView extends RelativeLayout {
    private Context context;
    private List<HomeItemCCBean.DataBean.CcListBean> list;
    private List<HomeItemCCBean.DataBean.CcListBean> showList;
    private ItemClick itemClick;


    public void setItemClick(ItemClick itemClick) {
        this.itemClick = itemClick;
    }

    private void setItemChangeListener(ItemChangeListener itemChangeListener) {
        itemChangeListener.itemChangeListener();
    }

    public ClosedCCView(Context context) {
        this(context, null);
    }

    public ClosedCCView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ClosedCCView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        list = new ArrayList<>();
        showList = new ArrayList<>();
        lists = new ArrayList<>();
        unClickList = new ArrayList<>();

    }

    /**
     * 创建itemView
     *
     * @param content
     * @return
     */
    private View createView(String content) {
        LinearLayout inflate = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.closed_cc_item_view, null);
        ImageView closedCCImg = inflate.findViewById(R.id.closed_cc_img);
        TextView closedCCCountTv = inflate.findViewById(R.id.closed_cc_count_tv);


        closedCCCountTv.setText(content);
        initAnimation(inflate);
        initAnim(inflate);

        return inflate;
    }


    /**
     * 初始化子布局上下抖动动画
     *
     * @param view
     */
    private void initAnimation(View view) {
        Animation anim = new TranslateAnimation(0.0F, 0.0F, -10.0F, 20.0F);
        anim.setInterpolator(new AccelerateDecelerateInterpolator());
        anim.setDuration(500L);
        anim.setRepeatCount(2147483647);
        anim.setRepeatMode(2);
        view.startAnimation(anim);
    }

    private void initAnim(View view) {
        view.setAlpha(0.0F);
        view.setScaleX(0.0F);
        view.setScaleY(0.0F);
        view.animate().alpha(1.0F).scaleX(1.0F).scaleY(1.0F).setDuration(500L).start();
    }

    private int tag = 0;
    private int newTag = -1;

    /**
     * 添加item
     *
     * @param listBean
     * @param isCountdown 是否倒计时   true需要倒计时   false不需要倒计时
     */
    private void addView(final HomeItemCCBean.DataBean.CcListBean listBean, boolean isCountdown) {
        final View itemView = createView(listBean.getCc() + "");
        if (tag != 10) {
            itemView.setTag(tag);
            tag++;
        } else {
            if (newTag != -1) {
                itemView.setTag(newTag);
            }
        }
        final TextView textView = itemView.findViewById(R.id.closed_cc_count_tv);
        final ImageView imageView = itemView.findViewById(R.id.closed_cc_img);
        if (isCountdown) {
//        if (isCountdown) {
            //给切图
            imageView.setImageResource(R.mipmap.un_click_feng_che);
//            un_click_feng_che
//            itemView.setBackground();
            new CountDownTimer((listBean.getDjs() * 1000), 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    String s = TimeUtils.converLongTimeToStr(millisUntilFinished);
                    textView.setText(s);
                }

                @Override
                public void onFinish() {
                    imageView.setImageResource(R.mipmap.feng_ce);
                    textView.setText(listBean.getCc() + "");
                    itemView.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            ClosedCCView.this.childClick(v, listBean);
                        }
                    });
                }
            }.start();

        } else {
            ObjectAnimator rotation = ObjectAnimator.ofFloat(imageView, "rotation", 0f, 359f);//最好是0f到359f，0f和360f的位置是重复的
            rotation.setRepeatCount(ObjectAnimator.INFINITE);
            rotation.setInterpolator(new LinearInterpolator());
            rotation.setDuration(3000);
            rotation.start();
            itemView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    ClosedCCView.this.childClick(v, listBean);
                }
            });
        }
        //摆放位置
        post(new Runnable() {
            @Override
            public void run() {
                setChildViewPosition(itemView);
            }
        });
        addView(itemView);
    }

    /**
     * 设置item位置
     *
     * @param childView
     */
    private void setChildViewPosition(View childView) {
        Random randomX = new Random();
        Random randomY = new Random();
        float x = randomX.nextFloat() * (float) (this.getMeasuredWidth() - childView.getMeasuredWidth());
        float y = randomY.nextFloat() * (float) (this.getMeasuredHeight() - childView.getMeasuredHeight());
        Log.d("FloatView", "setChildViewPosition: parentWidth=" + this.getMeasuredWidth() + ",parentHeight=" + this.getMeasuredHeight());
        Log.d("FloatView", "setChildViewPosition: childWidth=" + childView.getMeasuredWidth() + ",childHeight=" + childView.getMeasuredHeight());
        Log.d("FloatView", "setChildViewPosition: x=" + x + ",y=" + y);
        childView.setX(x);
        childView.setY(y);
    }

    /**
     * 点击item的点击事件
     *
     * @param view
     */
    private void childClick(View view, HomeItemCCBean.DataBean.CcListBean listBean) {
        newTag = (int) view.getTag();

        animRemoveView(view, listBean);
        TextView closedCCCountTv = view.findViewById(R.id.closed_cc_count_tv);

        itemClick.itemClick(listBean);
    }

    private int listsIndex = 0;

    /**
     * 删除item动画
     *
     * @param view
     */
    private void animRemoveView(final View view, final HomeItemCCBean.DataBean.CcListBean listBean) {
        ValueAnimator animator = ValueAnimator.ofFloat(new float[]{(float) this.getMeasuredHeight(), 0.0F});
        animator.setDuration(1000L);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                float Value = (Float) animation.getAnimatedValue();
//                Log.d("FloatView", "onAnimationUpdate: " + view.getTranslationY());
//                Log.d("FloatView", "onAnimationUpdate: " + view.getY());
                view.setAlpha(Value / (float) ClosedCCView.this.getMeasuredHeight());
                view.setTranslationY(view.getY() - ((float) ClosedCCView.this.getMeasuredHeight() - Value));
            }
        });
        animator.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animation) {
//                int index = (int) view.getTag() + 10;
//                if (index < ClosedCCView.this.list.size() - 1) {
//                    HomeItemCCBean.DataBean.CcListBean ccListBean = ClosedCCView.this.list.get(index);
//                    setData(ccListBean);
//                }

                showList.remove(listBean);
                ClosedCCView.this.removeView(view);
                if (showList.isEmpty()) {
                    showItemData(listsIndex);
                }
            }
        });

        animator.start();
    }

    /**
     * 设置数据
     *
     * @param list
     */
    public void setList(List<HomeItemCCBean.DataBean.CcListBean> list) {
        this.list.clear();
        this.list.addAll(list);
        if (this.list.size() > 10) {
            for (int i = 0; i < 10; i++) {
                HomeItemCCBean.DataBean.CcListBean ccListBean = this.list.get(i);
                setData(ccListBean);
            }
        } else {
            showList.addAll(this.list);
            for (HomeItemCCBean.DataBean.CcListBean ccListBean : this.list) {
                setData(ccListBean);
            }
        }
    }

    private List<List<HomeItemCCBean.DataBean.CcListBean>> lists;
    private List<HomeItemCCBean.DataBean.CcListBean> unClickList;

    /**
     * 设置数据
     */
    public void setList(List<List<HomeItemCCBean.DataBean.CcListBean>> lists, List<HomeItemCCBean.DataBean.CcListBean> unClickList) {
        if (this.lists != null && !this.lists.isEmpty() && this.unClickList != null && !this.lists.isEmpty()) {
            listsIndex = 0;
            this.lists.clear();
            this.unClickList.clear();
            this.removeAllViews();
            removeAllViewsInLayout();
        }
        this.lists.addAll(lists);
        this.unClickList.addAll(unClickList);
        if (this.lists.isEmpty()) {
            showUnClickItemDate();
        } else {
            showItemData(0);
        }
    }

    private void showUnClickItemDate() {
        if (!unClickList.isEmpty()) {
            if (unClickList.size() < 10) {
                for (HomeItemCCBean.DataBean.CcListBean ccListBean : unClickList) {
                    setData(ccListBean);
                }
            } else {
                for (int i = 0; i < 10; i++) {
                    setData(unClickList.get(i));
                }
            }
        }
    }

    private void showItemData(int index) {
        if (index < lists.size() - 1 || index == 0) {//可点击集合是否领取完毕
            List<HomeItemCCBean.DataBean.CcListBean> listBeans = this.lists.get(index);
            if (listBeans.size() == 10) {//当前可点击集合是否可以展示一屏
                for (HomeItemCCBean.DataBean.CcListBean listBean : listBeans) {
                    setData(listBean);
                }
            } else {
                for (int i = 0; i < listBeans.size(); i++) {//将可点击的全部展示
                    HomeItemCCBean.DataBean.CcListBean ccListBean = listBeans.get(i);
                    setData(ccListBean);
                }
                if (10 - listBeans.size() < unClickList.size()) {//不可点击的 + 可点击的  是否可显示一屏
                    int j = 0;
                    for (int i = listBeans.size(); i < 10; i++, j++) {
                        HomeItemCCBean.DataBean.CcListBean ccListBean = this.unClickList.get(j);
                        setData(ccListBean);
                    }
                } else {//不可显示一屏  不可点击的全部显示
                    for (HomeItemCCBean.DataBean.CcListBean ccListBean : unClickList) {
                        setData(ccListBean);
                    }
                }
            }
            listsIndex++;
        } else {
            //显示不可点击，倒计时
            if (unClickList.size() > 10) {
                for (int i = 0; i < 10; i++) {
                    HomeItemCCBean.DataBean.CcListBean ccListBean = unClickList.get(i);
                    setData(ccListBean);
                }
            } else {
                for (HomeItemCCBean.DataBean.CcListBean ccListBean : unClickList) {
                    setData(ccListBean);
                }
            }
        }
    }

    private void setData(HomeItemCCBean.DataBean.CcListBean ccListBean) {
        showList.add(ccListBean);
        boolean isCountdown = false;
        if (ccListBean.getDjs() != 0) {
            isCountdown = true;
        }
        addView(ccListBean, isCountdown);
    }

    public interface ItemClick {
        void itemClick(HomeItemCCBean.DataBean.CcListBean listBean);
    }

    private interface ItemChangeListener {
        void itemChangeListener();
    }


}