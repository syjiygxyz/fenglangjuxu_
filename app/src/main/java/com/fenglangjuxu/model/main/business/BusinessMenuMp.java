package com.fenglangjuxu.model.main.business;

import android.app.Application;

import com.fenglangjuxu.BR;
import com.fenglangjuxu.R;
import com.fenglangjuxu.base.model.BaseListViewModel;

import me.goldze.mvvmhabit.base.ItemViewModel;
import me.goldze.mvvmhabit.utils.Utils;
import me.tatarka.bindingcollectionadapter2.collections.MergeObservableList;
import me.tatarka.bindingcollectionadapter2.itembindings.OnItemBindClass;

/**
 * @author syj
 * @date 2019/6/11
 */
public class BusinessMenuMp extends BaseListViewModel<ItemViewModel> {

    public BusinessMenuMp(Application context) {
        super(context);
    }

    @Override
    protected void loadData(int pageNo) {

    }

    @Override
    protected void initViewMapping(OnItemBindClass onItemBindClass) {
        multipleItems.map(ItemBusinessMenuMp.class, BR.item, R.layout.item_business_menu);
    }

    @Override
    protected void initHeaderFooter(MergeObservableList<Object> headerFooterItems) {
        headerFooterItems
                .insertItem(new ItemBusinessMenuMp(this, Utils.getContext().getResources().getDrawable(R.mipmap.ic_main_taxi), "打车", ""))
                .insertItem(new ItemBusinessMenuMp(this, Utils.getContext().getResources().getDrawable(R.mipmap.ic_main_takeout), "外卖", "/takeout/home"))
                .insertItem(new ItemBusinessMenuMp(this, Utils.getContext().getResources().getDrawable(R.mipmap.ic_main_callservice), "上门", ""))
                .insertItem(new ItemBusinessMenuMp(this, Utils.getContext().getResources().getDrawable(R.mipmap.ic_main_renting), "租房", ""))
                .insertItem(new ItemBusinessMenuMp(this, Utils.getContext().getResources().getDrawable(R.mipmap.ic_main_shopping), "购物", ""));
    }

    @Override
    public void accept(Object o) throws Exception {

    }
}
