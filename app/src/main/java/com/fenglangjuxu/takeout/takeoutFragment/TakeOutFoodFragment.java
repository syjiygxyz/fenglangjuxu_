package com.fenglangjuxu.takeout.takeoutFragment;

import android.app.Application;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.fenglangjuxu.BR;
import com.fenglangjuxu.R;
import com.fenglangjuxu.animator.AddShopCartAnimatorManager;
import com.fenglangjuxu.base.utils.CurrentActivityUtil;
import com.fenglangjuxu.databinding.FragmentTakeoutFoodBinding;
import com.fenglangjuxu.event.AddShoppingCartAnimatorEvent;
import com.fenglangjuxu.fragment.BaseImmersionFragment;
import com.fenglangjuxu.takeout.activity.TakeOutStoreActivity;
import com.fenglangjuxu.takeout.model.store.TakeOutFoodFragmentMp;
import com.fenglangjuxu.takeout.model.store.TakeOutStoreBottomMp;
import com.fenglangjuxu.takeout.view.TakeOutStoreWindow;

import io.reactivex.disposables.Disposable;
import me.goldze.mvvmhabit.bus.RxBus;
import me.goldze.mvvmhabit.bus.RxSubscriptions;
import me.goldze.mvvmhabit.utils.Utils;

/**
 * @author syj
 * @date 2019/6/20
 */
public class TakeOutFoodFragment extends BaseImmersionFragment<FragmentTakeoutFoodBinding, TakeOutFoodFragmentMp> {

    private PopupWindow bottomWindow;
    private TakeOutStoreBottomMp bottomMp;
    private AddShopCartAnimatorManager animatorManager;
    private Disposable animatorSubscribe;

    @Override
    public void initImmersionBar() {

    }

    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_takeout_food;
    }

    @Override
    public int initVariableId() {
        return BR.food;
    }

    @Override
    public TakeOutFoodFragmentMp initViewModel() {
        bottomMp = new TakeOutStoreBottomMp((Application) Utils.getContext());
        bottomWindow = TakeOutStoreWindow.createBottomPopWindow(getActivity(), bottomMp);
        bottomWindow.showAtLocation(binding.recyclerGood, Gravity.BOTTOM, 0, 0);
        initAnimator();
        return new TakeOutFoodFragmentMp((Application) Utils.getContext(), binding, bottomMp);
    }

    private void initAnimator() {
        animatorManager = new AddShopCartAnimatorManager(CurrentActivityUtil.getCurrentActivity(), binding.root, bottomMp.bottomBinding.ivShopCart);
        animatorManager.addAnimatorFinishListener(() -> {

        });
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initEvent();
    }


    public void pageSelected(int position){
        if (bottomWindow != null) {
            if (position == 0) {
                bottomWindow.showAtLocation(binding.recyclerGood, Gravity.BOTTOM, 0, 0);
            } else {
                bottomWindow.dismiss();
            }
        }
    }

    private void initEvent() {
        binding.recyclerGood.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (recyclerView.getLayoutManager() != null && recyclerView.getLayoutManager() instanceof LinearLayoutManager){
                    LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                    int firstPosition = layoutManager.findFirstVisibleItemPosition();
                    if (binding.getFood() != null){
                        binding.getFood().scrolledPosition(firstPosition);
                    }
                }
            }
        });
        animatorSubscribe = RxBus.getDefault().toObservable(AddShoppingCartAnimatorEvent.class)
                .subscribe(addShoppingCartAnimatorEvent -> {
                    animatorManager.startAddShopAnimator(addShoppingCartAnimatorEvent.getStartLocation());
                });
        RxSubscriptions.add(animatorSubscribe);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RxSubscriptions.remove(animatorSubscribe);
    }
}
