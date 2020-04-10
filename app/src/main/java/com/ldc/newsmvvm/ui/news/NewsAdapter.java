package com.ldc.newsmvvm.ui.news;

import android.text.TextUtils;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.ldc.newsmvvm.R;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

public class NewsAdapter extends BaseQuickAdapter<NewsBean, BaseViewHolder> {
    public NewsAdapter() {
        super(R.layout.layout_item_news);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, NewsBean item) {
        if (null == item) return;
        baseViewHolder.setText(R.id.tv_title, item.getTitle())
                .setText(R.id.tv_source, item.getSource())
                .setText(R.id.tv_cTime, item.getDate());
        if (TextUtils.isEmpty(item.getImg())) {
            baseViewHolder.setVisible(R.id.iv_image, false);
        } else {

            Picasso.get().load(item.getImg())
                    .resize(200, 160)
                    .placeholder(R.mipmap.img_placeholder)
                    .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                    .into((ImageView) baseViewHolder.getView(R.id.iv_image));

        }

    }
}
