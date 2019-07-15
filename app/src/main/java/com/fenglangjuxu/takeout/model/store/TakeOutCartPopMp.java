package com.fenglangjuxu.takeout.model.store;

import android.app.Application;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;
import android.util.SparseArray;
import android.widget.PopupWindow;

import com.fenglangjuxu.R;
import com.fenglangjuxu.BR;
import com.fenglangjuxu.base.model.BaseListViewModel;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.tatarka.bindingcollectionadapter2.collections.MergeObservableList;
import me.tatarka.bindingcollectionadapter2.itembindings.OnItemBindClass;

/**
 * @author syj
 * @date 2019/6/27
 */
public class TakeOutCartPopMp extends BaseListViewModel {
    private PopupWindow popupWindow;
    public ObservableList<TakeOutCartPopItemMp> popItems = new ObservableArrayList<>();
    public TakeOutCartPopMp(@NonNull Application application, SparseArray<TakeOutCartVo> cartArray) {
        super(application);
        for (int i = 0; i < cartArray.size(); i++){
            TakeOutCartVo vo = cartArray.valueAt(i);
            popItems.add(new TakeOutCartPopItemMp(this,vo.getId(), vo.getTag(), vo.getNum(), vo.getPrice(), vo.getName()));
        }
    }


    @Override
    protected void loadData(int pageNo) {

    }

    @Override
    protected void initViewMapping(OnItemBindClass onItemBindClass) {
        multipleItems.map(TakeOutCartPopItemMp.class, BR.item, R.layout.item_takeout_cart_pop);
    }

    @Override
    protected void initHeaderFooter(MergeObservableList headerFooterItems) {

    }

    public void setPopupWindow(PopupWindow popupWindow) {
        this.popupWindow = popupWindow;
    }

    @Override
    public void accept(Object o) throws Exception {

    }
}
