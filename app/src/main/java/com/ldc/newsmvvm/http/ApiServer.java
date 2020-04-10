package com.ldc.newsmvvm.http;

import com.ldc.newsmvvm.common.BaseBean;
import com.ldc.newsmvvm.ui.news.NewsBean;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiServer {

    @GET(value = "/ent/feed.d.json")
    Observable<BaseBean<ArrayList<NewsBean>>> get_news_data(
            @Query(value = "ch") String ch,
            @Query(value = "show_num") int show_num,
            @Query(value = "page") int page
    );
}
