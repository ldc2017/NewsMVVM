package com.ldc.newsmvvm.ui.main;

import android.Manifest;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;

import com.ldc.baselib.base.BaseNoModelActivity;
import com.ldc.newsmvvm.R;
import com.ldc.newsmvvm.common.cmConstants;
import com.ldc.newsmvvm.databinding.ActivityMainBinding;
import com.ldc.newsmvvm.ui.news.NewsFragment;
import com.yanzhenjie.permission.AndPermission;

import java.util.ArrayList;

import me.yokeyword.fragmentation.SupportFragment;

public class MainActivity extends BaseNoModelActivity<ActivityMainBinding> {

    private final Handler uiHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            return false;
        }
    });
    //
    /*https://interface.sina.cn/ent/feed.d.json?ch=ent&col=ent&act=more&t=1484477669001&show_num=10&page=4
    参数说明：
    ch:频道
    娱乐：ent
    体育：sports
    科技：tech
    教育：edu
    健康：health
    时尚：fashion
    博客：blog
    col：分类
    show_num
    page
。*/
    private static final String[] tabs = {"娱乐", "体育", "科技", "教育", "健康", "时尚", "博客"};
    private static final String[] chs = {"ent", "sports", "tech", "edu", "health", "fashion", "blog"};
    private ArrayList<SupportFragment> fragments = new ArrayList<>(16);
    private final MainAdapter mainAdapter = new MainAdapter(getSupportFragmentManager());


    @Override
    protected void onDestroy() {
        super.onDestroy();
        uiHandler.removeCallbacksAndMessages(null);
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_main;
    }

    @SuppressLint("WrongConstant")
    @Override
    protected void initView() {
        init_adapter();

        AndPermission.with(activity)
                .runtime()
                .permission(Manifest.permission.ACCESS_NETWORK_STATE)
                .start();

    }

    @Override
    protected void initData() {

    }


    //初始化适配器
    private void init_adapter() {
        uiHandler.post(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < tabs.length; i++) {
                    mBinding.tabLayout.addTab(mBinding.tabLayout.newTab().setText(tabs[i]));
                    final NewsFragment fragment = new NewsFragment();
                    final Bundle bundle = new Bundle();
                    bundle.putString(NewsFragment.curr_showcid_key, chs[i]);
                    fragment.setArguments(bundle);
                    //
                    Log.e(TAG, "run:---" + chs[i]);
                    //添加
                    fragments.add(fragment);
                }
                mBinding.fragmentContainer.setOffscreenPageLimit(fragments.size() - 1);
                mainAdapter.setNewData(tabs, fragments);
                mBinding.fragmentContainer.setAdapter(mainAdapter);
                mBinding.tabLayout.setupWithViewPager(mBinding.fragmentContainer);
            }
        });
    }

    @Override
    public boolean isBaseOnWidth() {
        return cmConstants.isBaseOnWidth;
    }

    @Override
    public float getSizeInDp() {
        return cmConstants.SizeInDp;
    }
}
