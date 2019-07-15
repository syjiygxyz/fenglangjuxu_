package com.fenglangjuxu.takeout.model.home;

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
 * @date 2019/6/19
 */
public class TakeOutMenuMp extends BaseListViewModel<ItemViewModel> {

    public TakeOutMenuMp(Application context) {
        super(context);
    }

    @Override
    protected void loadData(int pageNo) {

    }

    @Override
    protected void initViewMapping(OnItemBindClass onItemBindClass) {
        multipleItems.map(MenuItemMp.class , BR.item, R.layout.item_menu);
    }

    @Override
    protected void initHeaderFooter(MergeObservableList<Object> headerFooterItems) {
        headerFooterItems
                .insertItem(new MenuItemMp(this, Utils.getContext().getResources().getDrawable(R.mipmap.ic_takeout_food), "美食", "/takeout/store/branch", 0))
                .insertItem(new MenuItemMp(this, Utils.getContext().getResources().getDrawable(R.mipmap.ic_takeout_articles), "生活用品", "/takeout/store/branch", 1))
                .insertItem(new MenuItemMp(this, Utils.getContext().getResources().getDrawable(R.mipmap.ic_takeout_medicine), "医疗用品", "/takeout/store/branch", 2))
                .insertItem(new MenuItemMp(this, Utils.getContext().getResources().getDrawable(R.mipmap.ic_takeout_supermarket), "便利超市", "/takeout/store/branch", 3))
                .insertItem(new MenuItemMp(this, Utils.getContext().getResources().getDrawable(R.mipmap.ic_takeout_fruit), "干果零食", "/takeout/store/branch", 4));
    }

    @Override
    public void accept(Object o) throws Exception {

    }
}
