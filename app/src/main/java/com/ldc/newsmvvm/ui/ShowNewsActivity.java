package com.ldc.newsmvvm.ui;

import android.app.Activity;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.just.agentweb.AgentWeb;
import com.ldc.baselib.base.BaseNoModelActivity;
import com.ldc.newsmvvm.R;
import com.ldc.newsmvvm.common.cmConstants;
import com.ldc.newsmvvm.databinding.ActivityShowNewsBinding;

public class ShowNewsActivity extends BaseNoModelActivity<ActivityShowNewsBinding> {

    private static String curr_link = "";
    private AgentWeb agentWeb;

    public static void actionStart(Activity activity, final String link) {
        if (null == activity) return;
        Intent intent = new Intent(activity, ShowNewsActivity.class);
        curr_link = link;
        activity.startActivity(intent);

    }


    @Override
    protected void onResume() {
        if (null != agentWeb) {
            agentWeb.getWebLifeCycle().onResume();
        }
        super.onResume();

    }

    @Override
    protected void onPause() {
        if (null != agentWeb) {
            agentWeb.getWebLifeCycle().onPause();
        }
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        if (null != agentWeb) {
            agentWeb.getWebLifeCycle().onDestroy();
        }
        super.onDestroy();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_show_news;
    }

    @Override
    protected void initView() {
        mBinding.setEventHandle(new EventHandle());
        agentWeb = AgentWeb.with(this)
                .setAgentWebParent(mBinding.webViewLin, new LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator()
                .createAgentWeb()
                .ready()
                .go(curr_link);
    }

    @Override
    protected void initData() {

    }

    @Override
    public boolean isBaseOnWidth() {
        return cmConstants.isBaseOnWidth;
    }

    @Override
    public float getSizeInDp() {
        return cmConstants.SizeInDp;
    }


    public class EventHandle {
        public void onBack(View view) {
            finish();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (agentWeb.handleKeyEvent(keyCode, event)) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
