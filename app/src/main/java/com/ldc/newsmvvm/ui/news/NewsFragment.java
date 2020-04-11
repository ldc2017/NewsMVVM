package com.ldc.newsmvvm.ui.news;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.ldc.baselib.base.BaseFragment;
import com.ldc.baselib.bean.DialogBean;
import com.ldc.newsmvvm.R;
import com.ldc.newsmvvm.common.BaseBean;
import com.ldc.newsmvvm.common.cmConstants;
import com.ldc.newsmvvm.databinding.FragmentNewsBinding;
import com.ldc.newsmvvm.ui.ShowNewsActivity;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends BaseFragment<FragmentNewsBinding, NewsViewModel> {


    private NewsAdapter newsAdapter = new NewsAdapter();
    //
    private volatile int curr_index = 1;
    private volatile String curr_ch = "";
    public static final String curr_showcid_key = "curr_showcid";
    //通知ui
    private Observer<BaseBean<ArrayList<NewsBean>>> newsBeanNotifyUI = new Observer<BaseBean<ArrayList<NewsBean>>>() {
        @Override
        public void onChanged(BaseBean<ArrayList<NewsBean>> newsBean) {
            if (1 == curr_index) {
                //新数据
                newsAdapter.setNewData(newsBean.getData());
            } else {
                // 旧数据
                newsAdapter.addData(newsBean.getData());
            }
        }
    };
    // 显示加载对话框
    private Observer<DialogBean> loadingObserver = new Observer<DialogBean>() {
        @Override
        public void onChanged(DialogBean dialogBean) {
            mBinding.loadingProgress.setVisibility(dialogBean.isShow() ? View.VISIBLE : View.GONE);
            if (mBinding.refreshView.getState().isOpening) {
                mBinding.refreshView.finishLoadMore();
                mBinding.refreshView.finishRefresh();
            }
        }
    };

    @Override
    protected NewsViewModel initViewModel() {
        return new NewsViewModel();
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_news;
    }

    @Override
    protected void initView() {
        mBinding.refreshView.setOnRefreshLoadMoreListener(onRefreshLoadMoreListener);
        mBinding.refreshView.setEnableAutoLoadMore(true);
        //
        //初始化适配器
        init_adapter();
        //
        curr_ch = (String) getArguments().getString(curr_showcid_key);
        //
        viewModel.getNewsBean().observe(this, newsBeanNotifyUI);
        viewModel.show_loading(this, loadingObserver);


    }

    @Override
    protected void initData() {
        viewModel.get_news_bean(curr_ch, curr_index);//请求数据
    }


    //刷新事件
    private OnRefreshLoadMoreListener onRefreshLoadMoreListener = new OnRefreshLoadMoreListener() {
        @Override
        public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
            curr_index++;
            viewModel.get_news_bean(curr_ch, curr_index);

        }

        @Override
        public void onRefresh(@NonNull RefreshLayout refreshLayout) {
            curr_index = 1;
            viewModel.get_news_bean(curr_ch, curr_index);
        }
    };

    //初始化适配器
    private void init_adapter() {
        mBinding.dataList.setHasFixedSize(true);
        mBinding.dataList.setItemViewCacheSize(10);
        mBinding.dataList.setAdapter(newsAdapter);
        mBinding.dataList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    //空闲状态
                    newsAdapter.setScroll(false);
                    newsAdapter.notifyDataSetChanged();
                    Picasso.get().resumeTag(recyclerView);
                } else {
                    Picasso.get().pauseTag(recyclerView);
                    newsAdapter.setScroll(true);
                }
                super.onScrollStateChanged(recyclerView, newState);
            }
        });
        //点击事件
        newsAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
                ArrayList<NewsBean> dts = (ArrayList<NewsBean>) adapter.getData();
                if (null == dts) return;
                NewsBean dt = dts.get(position);
                if (null == dt) return;
                ShowNewsActivity.actionStart(getActivity(), dt.getLink());
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
