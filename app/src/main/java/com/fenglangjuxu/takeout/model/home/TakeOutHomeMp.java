package com.fenglangjuxu.takeout.model.home;

import android.app.Application;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import com.fenglangjuxu.BR;
import com.fenglangjuxu.R;
import com.fenglangjuxu.base.model.BaseListViewModel;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.utils.Utils;
import me.tatarka.bindingcollectionadapter2.collections.MergeObservableList;
import me.tatarka.bindingcollectionadapter2.itembindings.OnItemBindClass;

/**
 * @author syj
 * @date 2019/6/19
 */
public class TakeOutHomeMp extends BaseListViewModel<BaseViewModel> {
    public TakeOutHomeMp(Application context) {
        super(context);
    }

    @Override
    protected void loadData(int pageNo) {

    }

    @Override
    protected void initViewMapping(OnItemBindClass onItemBindClass) {
        multipleItems.map(TakeOutMenuMp.class, BR.menu, R.layout.takeout_menu)
                .map(String.class, BR.item, R.layout.layout_takeout_top)
                .map(TakeOutHotTitle.class, BR.item, R.layout.layout_takeout_title)
                .map(TakeOutItemMp.class, BR.item, R.layout.item_takeout);
    }

    @Override
    protected void initHeaderFooter(MergeObservableList<Object> headerFooterItems) {
        ObservableList<TakeOutItemMp> items = new ObservableArrayList<>();
        for (int i = 0; i < 10; i++){
            items.add(new TakeOutItemMp(this));
        }
        headerFooterItems
                .insertItem("")
                .insertItem(new TakeOutMenuMp((Application) Utils.getContext()))
                .insertItem(new TakeOutHotTitle(this))
                .insertList(items);
    }

    @Override
    public void accept(Object o) throws Exception {

    }
}
