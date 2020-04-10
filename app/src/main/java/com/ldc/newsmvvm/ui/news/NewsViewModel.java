package com.ldc.newsmvvm.ui.news;

import androidx.lifecycle.MutableLiveData;

import com.ldc.baselib.base.BaseViewModel;
import com.ldc.newsmvvm.common.BaseBean;
import com.ldc.newsmvvm.http.Api2Request;
import com.ldc.newsmvvm.http.ApiServer;

import java.util.ArrayList;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class NewsViewModel extends BaseViewModel {
    private ApiServer apiServer;
    private final MutableLiveData<BaseBean<ArrayList<NewsBean>>> newsBean = new MutableLiveData<>();

    NewsViewModel() {
        apiServer = Api2Request.getInstance().CreateServer(ApiServer.class);
    }


    /**
     * 获取新闻
     *
     * @param index
     */
    public void get_news_bean(String showcid, int index) {

        showLoading.setValue(true);
        apiServer.get_news_data(showcid, 20, index)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseBean<ArrayList<NewsBean>>>() {

                    @Override
                    public void onSubscribe(Disposable d) {
                        addDisposable(d);

                    }

                    @Override
                    public void onNext(BaseBean<ArrayList<NewsBean>> dt) {
                        newsBean.setValue(dt);
                    }

                    @Override
                    public void onError(Throwable e) {
                        showLoading.setValue(false);
                    }

                    @Override
                    public void onComplete() {
                        showLoading.setValue(false);

                    }
                });
    }

    public MutableLiveData<BaseBean<ArrayList<NewsBean>>> getNewsBean() {
        return newsBean;
    }
}
