package com.fenglangjuxu.model.main.amusement;

import android.app.Application;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import com.fenglangjuxu.BR;
import com.fenglangjuxu.R;
import com.fenglangjuxu.base.model.BaseListViewModel;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.tatarka.bindingcollectionadapter2.collections.MergeObservableList;
import me.tatarka.bindingcollectionadapter2.itembindings.OnItemBindClass;

/**
 * @author syj
 * @date 2019/6/12
 */
public class AmusementRecommendationFragmentMp extends BaseListViewModel<BaseViewModel> {
    public AmusementRecommendationFragmentMp(Application context) {
        super(context);
    }

    @Override
    protected void loadData(int pageNo) {

    }

    @Override
    protected void initViewMapping(OnItemBindClass onItemBindClass) {
        multipleItems.map(AmusementRecommendationTopItemMp.class, BR.item, R.layout.item_amusement_recommendation_top)
                        .map(AmusementRecommendationItemMp.class, BR.item, R.layout.item_amusement_recommendation);
    }

    @Override
    protected void initHeaderFooter(MergeObservableList<Object> headerFooterItems) {
        ObservableList<AmusementRecommendationItemMp> itemMpList = new ObservableArrayList<>();
        for (int i = 0; i < 10; i++ ){
            itemMpList.add(new AmusementRecommendationItemMp(this));
        }
        headerFooterItems.insertItem(new AmusementRecommendationTopItemMp(this))
                            .insertList(itemMpList);
    }

    @Override
    public void accept(Object o) throws Exception {

    }
}
