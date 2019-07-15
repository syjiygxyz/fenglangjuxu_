package com.fenglangjuxu.takeout.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.fenglangjuxu.BR;
import com.fenglangjuxu.R;
import com.fenglangjuxu.adapter.BaseFragmentPagerAdapter;
import com.fenglangjuxu.adapter.TabStyleAdapter;
import com.fenglangjuxu.adapter.WrapTabStyleAdapter;
import com.fenglangjuxu.base.activity.BaseImmersionActivity;
import com.fenglangjuxu.base.model.NavigationMp;
import com.fenglangjuxu.databinding.ActivityTakeoutBranchBinding;
import com.fenglangjuxu.takeout.model.screening.TakeOutBranchMp;
import com.fenglangjuxu.takeout.takeoutFragment.TakeOutBranchFragment;

import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @author syj
 * @date 2019/7/4
 */
@Route(path = "/takeout/store/branch")
public class TakeOutBranchActivity extends BaseImmersionActivity<ActivityTakeoutBranchBinding, TakeOutBranchMp> {
    private List<String> titles;
    private List<Fragment> fragments = new ArrayList<>();
    private int id;

    @Override
    public void initImmersionBar() {

    }

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_takeout_branch;
    }

    @Override
    public int initVariableId() {
        return BR.branch;
    }

    @Override
    public TakeOutBranchMp initViewModel() {
        String name = getIntent().getStringExtra("name");
        id = getIntent().getIntExtra("id", 0);
        binding.setNavigation(new NavigationMp(getApplication(),name));
        return new TakeOutBranchMp(getApplication());
    }

    @Override
    public void initData() {
        initTitleList();
        initFragments();
        initPager();
        initIndicator();
    }

    private void initTitleList() {
        switch (id){
            case 0:
                titles = Arrays.asList("推荐", "家常菜", "便利简餐", "西方美食", "日料", "韩餐");
                break;
            case 1:
                titles = Arrays.asList("推荐", "超市", "便利店", "美妆", "酒水", "冰淇淋");
                break;
            case 2:
                titles = Arrays.asList("推荐", "感冒用药", "喉咙不适", "肠胃用药", "营养保健", "验孕避孕");
                break;
            case 3:
                titles = Arrays.asList("推荐", "炒货", "干果", "饮料", "酒水", "肉食品");
                break;
            case 4:
                titles = Arrays.asList("推荐", "炒货", "干果", "饮料", "酒水", "肉食品");
                break;
            default:
                break;
        }
    }

    private void initFragments() {
        for (String title : titles){
            if (!title.isEmpty()){
                fragments.add(new TakeOutBranchFragment());
            }
        }
    }

    private void initPager() {
        binding.takeoutBranchPager.setAdapter(new BaseFragmentPagerAdapter(getSupportFragmentManager(), fragments));
        binding.takeoutBranchPager.setOffscreenPageLimit(titles.size());
        binding.takeoutBranchPager.setCanScroll(false);
    }

    private void initIndicator() {
        WrapTabStyleAdapter tabStyleAdapter = new WrapTabStyleAdapter(titles, binding.takeoutBranchPager, LinePagerIndicator.MODE_EXACTLY);
        CommonNavigator commonNavigator = new CommonNavigator(TakeOutBranchActivity.this);
        commonNavigator.setAdjustMode(false);
        commonNavigator.setSkimOver(true);
        commonNavigator.setScrollPivotX(0.65f);
        commonNavigator.setAdapter(tabStyleAdapter);
        binding.indicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(binding.indicator, binding.takeoutBranchPager);
    }
}
