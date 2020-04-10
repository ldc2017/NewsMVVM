package com.ldc.baselib.base;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModel;

import android.os.Bundle;

public abstract class BaseActivity<B extends ViewDataBinding, VM extends BaseViewModel> extends BaseNoModelActivity<B> {

    protected VM viewModel;

    @Override
    protected B initViewDataBinding(int layoutId) {
        B vb = super.initViewDataBinding(layoutId);
        viewModel = initViewModel();
        return vb;
    }

    protected abstract VM initViewModel();
}
