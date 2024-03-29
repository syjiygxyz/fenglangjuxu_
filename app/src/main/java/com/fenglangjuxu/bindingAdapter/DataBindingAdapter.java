package com.fenglangjuxu.bindingAdapter;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.fenglangjuxu.base.utils.ScreenUtils;
import com.fenglangjuxu.view.FloatingBarItemDecoration;
import com.fenglangjuxu.view.NumberView;
import com.fenglangjuxu.view.RecyclerItemDecorationLine;
import com.jakewharton.rxbinding2.view.RxView;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import cn.bingoogolapple.bgabanner.BGABanner;
import io.reactivex.functions.Consumer;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter;
import me.tatarka.bindingcollectionadapter2.ItemBinding;
import me.tatarka.bindingcollectionadapter2.LayoutManagers;

public class DataBindingAdapter {
    public static final int CLICK_INTERVAL = 1;
    /**
     * requireAll 是意思是是否需要绑定全部参数, false为否
     * View的onClick事件绑定
     * onClickCommand 绑定的命令,
     * isThrottleFirst 是否开启防止过快点击
     */
    @BindingAdapter(value = {"onClickCommand", "isThrottleFirst"}, requireAll = false)
    public static void onClickCommand(View view, final BindingCommand clickCommand, final boolean isThrottleFirst) {
        if (isThrottleFirst) {
            RxView.clicks(view)
                    .subscribe(new Consumer<Object>() {
                        @Override
                        public void accept(Object object) throws Exception {
                            if (clickCommand != null) {
                                clickCommand.execute();
                            }
                        }
                    });
        } else {
            RxView.clicks(view)
                    .throttleFirst(CLICK_INTERVAL, TimeUnit.SECONDS)//1秒钟内只允许点击1次
                    .subscribe(new Consumer<Object>() {
                        @Override
                        public void accept(Object object) throws Exception {
                            if (clickCommand != null) {
                                clickCommand.execute();
                            }
                        }
                    });
        }
    }

    /**
     * view的onLongClick事件绑定
     */
    @BindingAdapter(value = {"onLongClickCommand"}, requireAll = false)
    public static void onLongClickCommand(View view, final BindingCommand clickCommand) {
        RxView.longClicks(view)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object object) throws Exception {
                        if (clickCommand != null) {
                            clickCommand.execute();
                        }
                    }
                });
    }

    /**
     * 回调控件本身
     *
     * @param currentView
     * @param bindingCommand
     */
    @BindingAdapter(value = {"currentView"}, requireAll = false)
    public static void replyCurrentView(View currentView, BindingCommand bindingCommand) {
        if (bindingCommand != null) {
            bindingCommand.execute(currentView);
        }
    }

    /**
     * view是否需要获取焦点
     */
    @BindingAdapter({"requestFocus"})
    public static void requestFocusCommand(View view, final Boolean needRequestFocus) {
        if (needRequestFocus) {
            view.setFocusableInTouchMode(true);
            view.requestFocus();
        } else {
            view.clearFocus();
        }
    }

    /**
     * view的焦点发生变化的事件绑定
     */
    @BindingAdapter({"onFocusChangeCommand"})
    public static void onFocusChangeCommand(View view, final BindingCommand<Boolean> onFocusChangeCommand) {
        view.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (onFocusChangeCommand != null) {
                    onFocusChangeCommand.execute(hasFocus);
                }
            }
        });
    }

    /**
     * view的显示隐藏
     */
    @BindingAdapter(value = {"isVisible"}, requireAll = false)
    public static void isVisible(View view, final Boolean visibility) {
        if (visibility) {
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.GONE);
        }
    }

    @BindingAdapter({"banner_adapter", "banner_data"})
    public static void setBannerAdapter(BGABanner banner, BGABanner.Adapter<ImageView, Object> adapter, List<Object> list){
        int width = (int)(ScreenUtils.getScreenWidth(banner.getContext()) - ScreenUtils.dp2px(banner.getContext(), 24f));
        ViewGroup.LayoutParams layoutParams = banner.getLayoutParams();
        layoutParams.height = (int)(width * 0.51);
        banner.setAdapter(adapter);
        banner.setData(list, null);
    }
    @SuppressWarnings("unchecked")
    @BindingAdapter(value = {"decorationItemBinding","decorationItems","decorationAdapter","decorationTitles"})
    public static <T> void setAdapterWithDecoration(RecyclerView recyclerView, ItemBinding<T> itemBinding, List<T> items, BindingRecyclerViewAdapter<T> adapter, LinkedHashMap<Integer, String> titles){
        if (itemBinding == null){
            throw new IllegalArgumentException("itemBinding must not be null");
        }
        recyclerView.addItemDecoration(new FloatingBarItemDecoration(recyclerView.getContext(), titles));
        BindingRecyclerViewAdapter oldAdapter = (BindingRecyclerViewAdapter) recyclerView.getAdapter();
        if (adapter == null) {
            if (oldAdapter == null) {
                adapter = new BindingRecyclerViewAdapter<>();
            } else {
                adapter = oldAdapter;
            }
        }
        adapter.setItemBinding(itemBinding);
        adapter.setItems(items);
        if (oldAdapter != adapter) {
            recyclerView.setAdapter(adapter);
        }
    }

    @BindingAdapter("layoutManager")
    public static void setLayoutManager(RecyclerView recyclerView, LayoutManagers.LayoutManagerFactory layoutManagerFactory) {
        recyclerView.setLayoutManager(layoutManagerFactory.create(recyclerView));
    }

    @BindingAdapter("divider")
    public static void setItemDecoration(RecyclerView recyclerView, boolean isDicider){
        if (isDicider){
            recyclerView.addItemDecoration(new RecyclerItemDecorationLine(recyclerView.getContext()));
        }
    }

    @BindingAdapter("number")
    public static void setNumberView(NumberView numberView, int number){
        numberView.setAmount(number);
    }

    @BindingAdapter("number_listener")
    public static void setNumberListener(NumberView numberView, NumberView.OnNumberChangeListener listener){
        numberView.setOnNumberChangedListener(listener);
    }
}
