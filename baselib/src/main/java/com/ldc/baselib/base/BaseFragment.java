package com.ldc.baselib.base;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ldc.baselib.R;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class BaseFragment<B extends ViewDataBinding, VM extends BaseViewModel> extends BaseNoModelFragment<B> {

    protected VM viewModel;

    @Override
    protected B initDataBinding(@NonNull LayoutInflater inflater, int layoutId, @Nullable ViewGroup container) {
        B b = super.initDataBinding(inflater, layoutId, container);
        viewModel = initViewModel();
        return b;
    }

    protected abstract VM initViewModel();
}
