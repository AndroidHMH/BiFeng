package com.coinwind.bifeng.ui.task.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.coinwind.bifeng.R;
import com.coinwind.bifeng.base.BaseActivity;
import com.coinwind.bifeng.base.BaseFragment;
import com.coinwind.bifeng.base.NoNetworkBaseActivity;
import com.coinwind.bifeng.ui.task.fragment.TaskFragment;

public class IntermediateTaskActivity extends NoNetworkBaseActivity {

    public static void openIntermediateTaskActivity(Context context) {
        Intent intent = new Intent(context, IntermediateTaskActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_intermediate_task;
    }

    @Override
    protected void init() {
        setCreateView(R.id.intermediate_layout, TaskFragment.class);
    }

}
