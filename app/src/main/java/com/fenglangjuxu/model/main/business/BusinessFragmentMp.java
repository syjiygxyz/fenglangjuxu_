package com.fenglangjuxu.model.main.business;

import android.app.Application;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;

import com.fenglangjuxu.BR;
import com.fenglangjuxu.R;
import com.fenglangjuxu.base.model.BaseListViewModel;

import me.goldze.mvvmhabit.base.ItemViewModel;
import me.goldze.mvvmhabit.utils.Utils;
import me.tatarka.bindingcollectionadapter2.collections.MergeObservableList;
import me.tatarka.bindingcollectionadapter2.itembindings.OnItemBindClass;

/**
 * @author syj
 * @date 2019/6/6
 */
public class BusinessFragmentMp extends BaseListViewModel<ItemViewModel> {
    public static ObservableList<ItemCouponMp> couponList = new ObservableArrayList<>();

    public BusinessFragmentMp(@NonNull Application application) {
        super(application);
    }

    @Override
    protected void loadData(int pageNo) {

    }

    @Override
    protected void initViewMapping(OnItemBindClass onItemBindClass) {
        multipleItems
                .map(BusinessBannerMp.class, BR.banner, R.layout.business_banner)
                .map(BusinessMenuMp.class, BR.menu, R.layout.business_menu)
                .map(ItemCouponMp.class, BR.item, R.layout.item_business_coupon)
                .map(String.class, BR.item, R.layout.business_coupon_title);
    }

    @Override
    protected void initHeaderFooter(MergeObservableList<Object> headerFooterItems) {
        BusinessBannerMp bannerMp = new BusinessBannerMp(this);
        BusinessMenuMp menuMp = new BusinessMenuMp((Application)Utils.getContext());
        for (int i = 0; i < 10 ; i++){
            couponList.add(new ItemCouponMp(this));
        }
        headerFooterItems.insertItem(bannerMp)
                         .insertItem(menuMp)
                         .insertItem("")
                         .insertList(couponList);
    }

    @Override
    public void accept(Object o) throws Exception {

    }
}
