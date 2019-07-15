package com.fenglangjuxu.takeout.model.screening;

import android.app.Application;

import com.fenglangjuxu.R;
import com.fenglangjuxu.BR;
import com.fenglangjuxu.base.model.BaseListViewModel;

import me.tatarka.bindingcollectionadapter2.collections.MergeObservableList;
import me.tatarka.bindingcollectionadapter2.itembindings.OnItemBindClass;

/**
 * @author syj
 * @date 2019/7/4
 */
public class TakeOutBranchFragmentMp extends BaseListViewModel {
    public TakeOutBranchFragmentMp(Application context) {
        super(context);
    }

    @Override
    protected void loadData(int pageNo) {

    }

    @Override
    protected void initViewMapping(OnItemBindClass onItemBindClass) {
        multipleItems.map(TakeOutBranchItemMp.class, BR.item, R.layout.item_takout_branch);
    }

    @Override
    protected void initHeaderFooter(MergeObservableList headerFooterItems) {
        for (int i = 0; i < 10 ; i++){
            headerFooterItems.insertItem(new TakeOutBranchItemMp(this));
        }
    }

    @Override
    public void accept(Object o) throws Exception {

    }
}
