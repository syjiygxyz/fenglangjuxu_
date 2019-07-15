package com.fenglangjuxu.model.main.social;

import android.app.Application;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import com.fenglangjuxu.BR;
import com.fenglangjuxu.R;
import com.fenglangjuxu.base.model.BaseListViewModel;
import com.fenglangjuxu.model.main.amusement.AmusementVideoItemMp;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.utils.Utils;
import me.tatarka.bindingcollectionadapter2.collections.MergeObservableList;
import me.tatarka.bindingcollectionadapter2.itembindings.OnItemBindClass;

/**
 * @author syj
 * @date 2019/6/17
 */
public class SocialPagerFragmentMp extends BaseListViewModel<BaseViewModel> {
    private String type;

    public SocialPagerFragmentMp(Application context) {
        super(context);
    }

    public SocialPagerFragmentMp(Application context, String type){
        super(context);
        this.type = type;
    }

    @Override
    protected void loadData(int pageNo) {

    }

    @Override
    protected void initViewMapping(OnItemBindClass onItemBindClass) {
        multipleItems.map(AmusementVideoItemMp.class, BR.item, R.layout.item_social_video)
                    .map(SocialImageItemMp.class, BR.item, R.layout.item_social_image);
    }

    @Override
    protected void initHeaderFooter(MergeObservableList<Object> headerFooterItems) {
        ObservableList<Object> itemList = new ObservableArrayList<>();
        for (int i = 0; i < 10; i++){
            if (i % 2 == 0){
                itemList.add(new AmusementVideoItemMp((Application) Utils.getContext()));
            }else {
                itemList.add(new SocialImageItemMp((Application) Utils.getContext()));
            }

        }
        headerFooterItems.insertList(itemList);
    }

    @Override
    public void accept(Object o) throws Exception {

    }
}
