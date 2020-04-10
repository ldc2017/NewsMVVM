package com.ldc.baselib.lifecycle;

import androidx.lifecycle.MutableLiveData;

import com.ldc.baselib.bean.DialogBean;

public final class DialogLivaData<T> extends MutableLiveData<T> {

    private DialogBean bean = new DialogBean();

    public void setValue(boolean show) {
        bean.setShow(show);
        bean.setMessage("加载中···");
        setValue((T) bean);
    }

    public void setValue(boolean show, String message) {
        bean.setShow(show);
        bean.setMessage(message);
        setValue((T) bean);
    }

}
