package com.fenglangjuxu.model.main.amusement;

import android.app.Application;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import com.fenglangjuxu.R;
import com.fenglangjuxu.BR;
import com.fenglangjuxu.base.model.BaseListViewModel;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.utils.Utils;
import me.tatarka.bindingcollectionadapter2.collections.MergeObservableList;
import me.tatarka.bindingcollectionadapter2.itembindings.OnItemBindClass;

/**
 * @author syj
 * @date 2019/6/12
 */
public class AmusementVideoFragmentMp extends BaseListViewModel<BaseViewModel> {
    public AmusementVideoFragmentMp(Application context) {
        super(context);
    }

    @Override
    protected void loadData(int pageNo) {

    }

    @Override
    protected void initViewMapping(OnItemBindClass onItemBindClass) {
        multipleItems.map(AmusementVideoItemMp.class, BR.item, R.layout.item_amusement_video);
    }

    @Override
    protected void initHeaderFooter(MergeObservableList<Object> headerFooterItems) {
        ObservableList<AmusementVideoItemMp> itemList = new ObservableArrayList<>();
        for (int i = 0; i < 10; i++){
            itemList.add(new AmusementVideoItemMp((Application) Utils.getContext()));
        }
        headerFooterItems.insertList(itemList);
    }

    @Override
    public void accept(Object o) throws Exception {

    }
}
