package com.fenglangjuxu.takeout.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.fenglangjuxu.BR;
import com.fenglangjuxu.R;
import com.fenglangjuxu.adapter.BaseFragmentPagerAdapter;
import com.fenglangjuxu.adapter.TabStyleAdapter;
import com.fenglangjuxu.animator.AddShopCartAnimatorManager;
import com.fenglangjuxu.base.activity.BaseImmersionActivity;
import com.fenglangjuxu.base.utils.CurrentActivityUtil;
import com.fenglangjuxu.databinding.ActivityTakeoutStoreBinding;
import com.fenglangjuxu.takeout.model.store.TakeOutStoreMp;
import com.fenglangjuxu.takeout.takeoutFragment.TakeOutCommentFragment;
import com.fenglangjuxu.takeout.takeoutFragment.TakeOutFoodFragment;
import com.gyf.immersionbar.ImmersionBar;

import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author syj
 * @date 2019/6/20
 */
@Route(path = "/takeout/store")
public class TakeOutStoreActivity extends BaseImmersionActivity<ActivityTakeoutStoreBinding, TakeOutStoreMp> {
    private static final int PAGE_NUM = 2;
    private List<String> titles;
    private List<Fragment> fragments;
    private TakeOutFoodFragment foodFragment;
    @Override
    public void initImmersionBar() {
        ImmersionBar.with(this).init();
    }

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_takeout_store;
    }

    @Override
    public int initVariableId() {
        return BR.store;
    }

    @Override
    public TakeOutStoreMp initViewModel() {
        return new TakeOutStoreMp(getApplication());
    }

    @Override
    public void initData() {
//        setUpAppBarHeight();
        initTitles();
        initFragments();
        initPager();
        initIndicator();
        showBottomDialog();
    }

    private void showBottomDialog() {

    }

    private void setUpAppBarHeight() {
        ViewGroup.LayoutParams layoutParams = binding.takeoutAppBar.getLayoutParams();
        int indicatorHeight = binding.indicator.getLayoutParams().height;
        layoutParams.height += indicatorHeight;
        binding.takeoutAppBar.setLayoutParams(layoutParams);
    }

    private void initPager() {
        binding.takeoutStorePager.setAdapter(new BaseFragmentPagerAdapter(getSupportFragmentManager(), fragments));
        binding.takeoutStorePager.setOffscreenPageLimit(PAGE_NUM);
        binding.takeoutStorePager.setCanScroll(false);
        binding.takeoutStorePager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                foodFragment.pageSelected(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    private void initFragments() {
        fragments = new ArrayList<>(PAGE_NUM);
        foodFragment = new TakeOutFoodFragment();
        fragments.add(foodFragment);
        fragments.add(new TakeOutCommentFragment());
    }

    private void initTitles() {
        titles = new ArrayList<>(PAGE_NUM);
        titles.add("点餐");titles.add("评价");
    }

    private void initIndicator() {
        TabStyleAdapter tabStyleAdapter = new TabStyleAdapter(titles, binding.takeoutStorePager, LinePagerIndicator.MODE_EXACTLY);
        CommonNavigator commonNavigator = new CommonNavigator(TakeOutStoreActivity.this);
        commonNavigator.setAdjustMode(true);
        commonNavigator.setSkimOver(true);
        commonNavigator.setScrollPivotX(0.65f);
        commonNavigator.setAdapter(tabStyleAdapter);
        binding.indicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(binding.indicator, binding.takeoutStorePager);
    }

}
