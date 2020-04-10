package com.ldc.baselib.base;

import androidx.annotation.LayoutRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import me.jessyan.autosize.internal.CustomAdapt;
import me.yokeyword.fragmentation.SupportActivity;

public abstract class BaseNoModelActivity<B extends ViewDataBinding> extends SupportActivity implements CustomAdapt {

    public static final String TAG = "BaseNoModelActivity";
    protected B mBinding;
    protected Activity activity;
    protected Context context;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != mBinding) {
            mBinding.unbind();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            int layoutId = layoutId();
            activity = this;
            context = getApplicationContext();
            mBinding = initViewDataBinding(layoutId);
            initView();
            initData();
        } catch (Exception e) {
            errorHandle(e);
        }
    }


    protected abstract int layoutId();

    protected B initViewDataBinding(@LayoutRes int layoutId) {
        return DataBindingUtil.setContentView(this, layoutId);
    }

    protected abstract void initView();

    protected abstract void initData();

    protected void errorHandle(Exception e) {
        e.printStackTrace();
    }
}
