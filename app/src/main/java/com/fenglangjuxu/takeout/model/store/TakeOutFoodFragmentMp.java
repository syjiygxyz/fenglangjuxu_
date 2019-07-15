package com.fenglangjuxu.takeout.model.store;

import android.app.Application;
import android.support.v7.widget.LinearLayoutManager;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.fenglangjuxu.BR;
import com.fenglangjuxu.R;
import com.fenglangjuxu.base.model.BaseListViewModel;
import com.fenglangjuxu.base.utils.CurrentActivityUtil;
import com.fenglangjuxu.databinding.FragmentTakeoutFoodBinding;
import com.fenglangjuxu.event.AddShoppingCartAnimatorEvent;
import com.fenglangjuxu.takeout.event.TakeOutFoodClassificationSelectedEvent;
import com.fenglangjuxu.takeout.event.TakeOutFoodNumChangedEvent;
import com.fenglangjuxu.takeout.view.TakeOutStoreWindow;
import com.fenglangjuxu.tools.BigDecimalUtils;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import io.reactivex.disposables.Disposable;
import me.goldze.mvvmhabit.base.ItemViewModel;
import me.goldze.mvvmhabit.bus.RxBus;
import me.goldze.mvvmhabit.bus.RxSubscriptions;
import me.goldze.mvvmhabit.utils.ToastUtils;
import me.goldze.mvvmhabit.utils.Utils;
import me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter;
import me.tatarka.bindingcollectionadapter2.ItemBinding;
import me.tatarka.bindingcollectionadapter2.collections.MergeObservableList;
import me.tatarka.bindingcollectionadapter2.itembindings.OnItemBindClass;

/**
 * @author syj
 * @date 2019/6/20
 */
public class TakeOutFoodFragmentMp extends BaseListViewModel<ItemViewModel> {
    public BindingRecyclerViewAdapter classificationAdapter;
    public MergeObservableList<ItemTakeoutFoodClassificationMp> classificationList;
    public ItemBinding classificationBinding;
    private Disposable classificationSubscribe;
    public LinkedHashMap<Integer, String> titles = new LinkedHashMap<>();
    private FragmentTakeoutFoodBinding binding;
    private Disposable foodNumChangedSubscribe;
    public SparseArray<TakeOutCartVo> cartArray = new SparseArray<>();
    private TakeOutStoreBottomMp bottomMp;
    private float minimumPrice = 20.00F;
    private TakeOutCartPopMp popMp;

    public TakeOutFoodFragmentMp(Application context, FragmentTakeoutFoodBinding binding, TakeOutStoreBottomMp bottomMp) {
        super(context);
        this.binding = binding;
        this.bottomMp = bottomMp;
        bottomMp.setBottomClickListener(bottomListener);
        initClassification();
    }

    private void initClassification() {
        classificationAdapter = new BindingRecyclerViewAdapter();
        classificationList = new MergeObservableList<>();
        classificationBinding = ItemBinding.of(BR.item, R.layout.item_takeout_food_classification);
        classificationList.insertItem(new ItemTakeoutFoodClassificationMp(this,"我呀新品", true));
        classificationList.insertItem(new ItemTakeoutFoodClassificationMp(this,"我呀便当", false));
        classificationList.insertItem(new ItemTakeoutFoodClassificationMp(this,"我呀单品", false));
        classificationList.insertItem(new ItemTakeoutFoodClassificationMp(this,"我呀饮料", false));
        classificationList.insertItem(new ItemTakeoutFoodClassificationMp(this,"我呀组合", false));
        for (int j = 0; j < classificationList.size(); j++){
            titles.put(items.size(), classificationList.get(j).name.get());
            for (int i = 0; i < 5; i++){
                items.add(new TakeOutGoodItemMp(this, classificationList.get(j).name.get(), j, j*10+i));
            }
        }
    }

    @Override
    protected void loadData(int pageNo) {

    }

    @Override
    protected void initViewMapping(OnItemBindClass onItemBindClass) {
        multipleItems.map(TakeOutGoodItemMp.class, BR.item, R.layout.item_takeout_good);
    }

    @Override
    protected void initHeaderFooter(MergeObservableList<Object> headerFooterItems) {
        headerFooterItems.insertList(items);
    }

    @Override
    public void accept(Object o) throws Exception {

    }

    @Override
    public void registerRxBus() {
        super.registerRxBus();
        classificationSubscribe = RxBus.getDefault().toObservable(TakeOutFoodClassificationSelectedEvent.class)
                .subscribe(takeOutFoodClassificationSelectedEvent -> {
                    if (takeOutFoodClassificationSelectedEvent != null && !takeOutFoodClassificationSelectedEvent.getName().isEmpty()) {
                        selectedClassificationChanged(takeOutFoodClassificationSelectedEvent.getName());
                        scrollToPosition(takeOutFoodClassificationSelectedEvent.getName());
                    }
                });
        RxSubscriptions.add(classificationSubscribe);
        foodNumChangedSubscribe = RxBus.getDefault().toObservable(TakeOutFoodNumChangedEvent.class)
                .subscribe(takeOutFoodNumChangedEvent -> {
                    notifyUi(takeOutFoodNumChangedEvent);
                    notifyCart(takeOutFoodNumChangedEvent);
                });
        RxSubscriptions.add(foodNumChangedSubscribe);

    }

    private void notifyCart(TakeOutFoodNumChangedEvent takeOutFoodNumChangedEvent) {
        Iterator<Object> iterator = headerFooterItems.iterator();
        while (iterator.hasNext()){
            TakeOutGoodItemMp next = (TakeOutGoodItemMp)iterator.next();
            if (next.getId() == takeOutFoodNumChangedEvent.getId()){
                next.number.set(takeOutFoodNumChangedEvent.getNum());
            }
        }
        if (cartArray.get(takeOutFoodNumChangedEvent.getId()) == null){
            if (takeOutFoodNumChangedEvent.getDifference() == 1){
                cartArray.put(takeOutFoodNumChangedEvent.getId(), new TakeOutCartVo(
                        takeOutFoodNumChangedEvent.getName(),
                        takeOutFoodNumChangedEvent.getId(),
                        takeOutFoodNumChangedEvent.getPrice(),
                        takeOutFoodNumChangedEvent.getNum(),
                        takeOutFoodNumChangedEvent.getTag()));
            }
        }else {
            if (takeOutFoodNumChangedEvent.getNum() == 0){
                cartArray.delete(takeOutFoodNumChangedEvent.getId());
                if (popMp != null){
                   for (int i = popMp.popItems.size() -1; i >= 0 ; i--){
                       if (popMp.popItems.get(i).getId() == takeOutFoodNumChangedEvent.getId()){
                           popMp.popItems.remove(i);
                       }
                   }
                }
            }else {
                cartArray.get(takeOutFoodNumChangedEvent.getId()).setNum(takeOutFoodNumChangedEvent.getNum());
            }
        }
        computePrice();
    }

    private void computePrice(){
        String totalPrice = "0.00";
        for (int i = 0; i < cartArray.size(); i++){
            String simplePrice = BigDecimalUtils.mul(cartArray.valueAt(i).getPrice(), String.valueOf(cartArray.valueAt(i).getNum()), 2 );
            totalPrice = BigDecimalUtils.add(totalPrice, simplePrice, 2);
        }
        if (Float.valueOf(totalPrice) == 0.00F){
            bottomMp.totalPriceStr.set("");
        }else {
            bottomMp.totalPriceStr.set("￥" + totalPrice);
        }
        bottomMp.totalPrice.set(Float.valueOf(totalPrice));
        if (Float.valueOf(totalPrice) >= minimumPrice){
            bottomMp.enable.set(true);
        }else {
            bottomMp.enable.set(false);
        }
    }

    private void notifyUi(TakeOutFoodNumChangedEvent takeOutFoodNumChangedEvent) {
        ItemTakeoutFoodClassificationMp classificationItem = classificationList.get(takeOutFoodNumChangedEvent.getTag());
        int classificationNum = classificationItem.pickNumber.get();
        classificationItem.pickNumber.set(classificationNum + takeOutFoodNumChangedEvent.getDifference());
        classificationItem.pickNumberStr.set(classificationItem.pickNumber.get() + "");
        bottomMp.totalNum.set(bottomMp.totalNum.get() + takeOutFoodNumChangedEvent.getDifference());
        bottomMp.totalNumStr.set(bottomMp.totalNum.get()+"");
    }


    public void selectedClassificationChanged(String name){
        for (int i = 0; i < classificationList.size(); i++){
            if (classificationList.get(i).name.get().equals(name)){
                classificationList.get(i).selected.set(true);
                scrollClassification(i);
            }else {
                classificationList.get(i).selected.set(false);
            }
        }
    }

    private void scrollClassification(int i) {
        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) binding.recyclerClassification.getLayoutManager();
        if (linearLayoutManager == null){
            return;
        }
        if (i < linearLayoutManager.findFirstVisibleItemPosition()){
            binding.recyclerClassification.scrollToPosition(i);
            linearLayoutManager.scrollToPositionWithOffset(i, 0);
        }else if (i > linearLayoutManager.findLastVisibleItemPosition()){
            binding.recyclerClassification.scrollToPosition(i);
        }
    }

    public void scrollToPosition(String name){
        for (Map.Entry<Integer, String> entry : titles.entrySet()){
            if (entry.getValue().equals(name)){
                int position = entry.getKey();
                binding.recyclerGood.scrollToPosition(position);
                if (binding.recyclerGood.getLayoutManager() != null){
                    LinearLayoutManager linearLayoutManager = (LinearLayoutManager) binding.recyclerGood.getLayoutManager();
                    linearLayoutManager.scrollToPositionWithOffset(position, 0);
                }
            }
        }
    }

    public void scrolledPosition(int position){
        if (position == -1){

        }else {
            if (titles.containsKey(position)){
                selectedClassificationChanged(titles.get(position));
            }
        }
    }

    private TakeOutStoreBottomMp.BottomClickListener bottomListener = new TakeOutStoreBottomMp.BottomClickListener() {
        @Override
        public void clickCart() {
            if (cartArray.size() > 0){
                showCartPop();
            }
        }

        @Override
        public void goPay() {
            ToastUtils.showShortSafe("结算");
        }
    };

    public void showCartPop(){
        popMp = new TakeOutCartPopMp((Application) Utils.getContext(), cartArray);
        PopupWindow cartPop = TakeOutStoreWindow.createCartListPopWindow(CurrentActivityUtil.getCurrentActivity(), popMp);
        Window window = CurrentActivityUtil.getCurrentActivity().getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.alpha = 0.3f;
        window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        window.setAttributes(attributes);

        cartPop.showAtLocation(binding.recyclerGood, Gravity.BOTTOM, 0, CurrentActivityUtil.getCurrentActivity().getResources().getDimensionPixelSize(R.dimen.item_takeout_bottom_height));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RxSubscriptions.remove(classificationSubscribe);
        RxSubscriptions.remove(foodNumChangedSubscribe);
    }

    public BindingRecyclerViewAdapter getClassificationAdapter() {
        return classificationAdapter;
    }

    public void setClassificationAdapter(BindingRecyclerViewAdapter classificationAdapter) {
        this.classificationAdapter = classificationAdapter;
    }

    public LinkedHashMap<Integer, String> getTitles() {
        return titles;
    }

    public void setTitles(LinkedHashMap<Integer, String> titles) {
        this.titles = titles;
    }

}
