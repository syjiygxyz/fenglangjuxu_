package com.fenglangjuxu.fragment.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.fenglangjuxu.BR;
import com.fenglangjuxu.R;
import com.fenglangjuxu.adapter.BaseFragmentPagerAdapter;
import com.fenglangjuxu.adapter.TabStyleAdapter;
import com.fenglangjuxu.databinding.FragmentMainAmusementBinding;
import com.fenglangjuxu.fragment.BaseImmersionFragment;
import com.fenglangjuxu.fragment.home.amusement.AmusementInformationFragment;
import com.fenglangjuxu.fragment.home.amusement.AmusementRecommendationFragment;
import com.fenglangjuxu.fragment.home.amusement.AmusementVideoFragment;
import com.fenglangjuxu.model.main.amusement.AmusementFragmentMp;
import com.gyf.immersionbar.ImmersionBar;

import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author syj
 * @date 2019/6/6
 */
public class AmusementFragment extends BaseImmersionFragment<FragmentMainAmusementBinding, AmusementFragmentMp> {
    private static final int PAGE_NUM = 3;
    private List<String> titleList;
    private List<Fragment> fragments;

    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_main_amusement;
    }

    @Override
    public int initVariableId() {
        return BR.amusement;
    }

    @Override
    public AmusementFragmentMp initViewModel() {
        return new AmusementFragmentMp(getActivity().getApplication());
    }

    @Override
    public void initImmersionBar() {
        ImmersionBar.with(this)
                .statusBarView(binding.statusBar)
                .statusBarDarkFont(true, 0.2f)
                .init();
    }

    @Override
    public void initData() {
        initPager();
    }

    private void initPager() {
        initTitles();
        initFragment();
        binding.amusementPager.setAdapter(new BaseFragmentPagerAdapter(getChildFragmentManager(), fragments));
        binding.amusementPager.setOffscreenPageLimit(PAGE_NUM);
        initIndicator();
    }

    private void initTitles() {
        titleList = new ArrayList<>(PAGE_NUM);
        titleList.add("推荐");titleList.add("看资讯");titleList.add("看视频");
    }

    private void initFragment() {
        fragments = new ArrayList<>(PAGE_NUM);
        fragments.add(new AmusementRecommendationFragment());
        fragments.add(new AmusementInformationFragment());
        fragments.add(new AmusementVideoFragment());
    }

    private void initIndicator() {
        TabStyleAdapter tabStyleAdapter = new TabStyleAdapter(titleList, binding.amusementPager, LinePagerIndicator.MODE_EXACTLY);
        CommonNavigator commonNavigator = new CommonNavigator(getActivity());
        commonNavigator.setAdjustMode(true);
        commonNavigator.setSkimOver(true);
        commonNavigator.setScrollPivotX(0.65f);
        commonNavigator.setAdapter(tabStyleAdapter);
        binding.indicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(binding.indicator, binding.amusementPager);
    }
}
