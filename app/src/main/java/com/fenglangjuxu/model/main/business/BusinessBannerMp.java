package com.fenglangjuxu.model.main.business;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.fenglangjuxu.R;

import java.util.Arrays;
import java.util.List;

import cn.bingoogolapple.bgabanner.BGABanner;
import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.base.ItemViewModel;

/**
 * @author syj
 * @date 2019/6/10
 */
public class BusinessBannerMp extends ItemViewModel {
    public List<Object> images = Arrays.asList(R.drawable.test_image, R.drawable.test_image, R.drawable.test_image);

    public BusinessBannerMp(@NonNull BaseViewModel viewModel) {
        super(viewModel);
    }
    public BGABanner.Adapter<ImageView, Object> bannerAdapter = new BGABanner.Adapter<ImageView, Object>() {
        @Override
        public void fillBannerItem(BGABanner banner, ImageView itemView, @Nullable Object model, int position) {
            RequestOptions requestOptions = new RequestOptions().centerCrop().dontAnimate();
            Glide.with(itemView.getContext())
                    .load(model)
                    .apply(requestOptions)
                    .into(itemView);
        }
    };

    public BGABanner.Adapter<ImageView, Object> getBannerAdapter() {
        return bannerAdapter;
    }

    public void setBannerAdapter(BGABanner.Adapter<ImageView, Object> bannerAdapter) {
        this.bannerAdapter = bannerAdapter;
    }

    public List<Object> getImages() {
        return images;
    }

    public void setImages(List<Object> images) {
        this.images = images;
    }
}
