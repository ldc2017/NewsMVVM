package com.ldc.baselib.base;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.ldc.baselib.bean.DialogBean;
import com.ldc.baselib.lifecycle.DialogLivaData;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class BaseViewModel extends ViewModel {
    /**
     * 管理RxJava请求
     */
    private CompositeDisposable compositeDisposable;
    protected DialogLivaData<DialogBean> showLoading = new DialogLivaData<>();


    /**
     * 添加 rxJava 发出的请求
     */
    protected void addDisposable(Disposable disposable) {
        if (compositeDisposable == null || compositeDisposable.isDisposed()) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(disposable);
    }

    /**
     * 释放 引用
     *
     * @param disposable
     */
    protected void recycleDisposable(Disposable disposable) {
        if (null != disposable && !disposable.isDisposed()) {
            disposable.dispose();
        }
        disposable = null;
    }


    //监控是否显示对话框
    public void show_loading(LifecycleOwner owner, Observer<DialogBean> observer) {
        showLoading.observe(owner, observer);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (null != compositeDisposable) {
            compositeDisposable.clear();
            compositeDisposable = null;
        }
    }
}
