package com.ldc.baselib.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import me.jessyan.autosize.internal.CustomAdapt;
import me.yokeyword.fragmentation.SupportActivity;

public abstract class BaseNoModelActivity<B extends ViewDataBinding> extends SupportActivity implements CustomAdapt {

    public static final String TAG = "BaseNoModelActivity";
    protected B mBinding;
    protected Activity activity;
    protected Context context;
    protected final Handler uiHandler =new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            return uiHandleMessage(msg);
        }
    });

    protected boolean uiHandleMessage(@NonNull Message msg){
        return false;
    }

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
