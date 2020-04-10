package com.ldc.baselib.base;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ldc.baselib.R;

import me.jessyan.autosize.internal.CustomAdapt;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class BaseNoModelFragment<B extends ViewDataBinding> extends SupportFragment implements CustomAdapt {

    protected B mBinding;


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (null != mBinding) {
            mBinding.unbind();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mBinding = initDataBinding(inflater, layoutId(), container);

        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        try {
            initView();
            initData();
        } catch (Exception e) {
            errorHandle(e);
        }
    }

    protected abstract int layoutId();

    protected B initDataBinding(@NonNull LayoutInflater inflater, int layoutId, @Nullable ViewGroup container) {
        return DataBindingUtil.inflate(inflater, layoutId, container, false);
    }

    protected abstract void initView();

    protected abstract void initData();

    protected void errorHandle(Exception e) {
        e.printStackTrace();
    }

}
