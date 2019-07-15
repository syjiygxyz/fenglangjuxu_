package com.fenglangjuxu.takeout.model.store;

import android.app.Application;

import com.fenglangjuxu.R;
import com.fenglangjuxu.BR;
import com.fenglangjuxu.base.model.BaseListViewModel;

import me.goldze.mvvmhabit.utils.Utils;
import me.tatarka.bindingcollectionadapter2.collections.MergeObservableList;
import me.tatarka.bindingcollectionadapter2.itembindings.OnItemBindClass;

/**
 * @author syj
 * @date 2019/7/3
 */
public class TakeOutCommentFragmentMp extends BaseListViewModel {
    public TakeOutCommentFragmentMp(Application context) {
        super(context);
    }

    @Override
    protected void loadData(int pageNo) {

    }

    @Override
    protected void initViewMapping(OnItemBindClass onItemBindClass) {
        multipleItems.map(TakeOutCommentItemMp.class, BR.item, R.layout.item_take_out_comment);
    }

    @Override
    protected void initHeaderFooter(MergeObservableList headerFooterItems) {
        for (int i = 0; i < 10; i++){
            headerFooterItems.insertItem(new TakeOutCommentItemMp((Application) Utils.getContext()));
        }
    }

    @Override
    public void accept(Object o) throws Exception {

    }
}
