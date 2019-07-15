package com.fenglangjuxu.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.fenglangjuxu.BR;
import com.fenglangjuxu.R;
import com.fenglangjuxu.adapter.BaseFragmentPagerAdapter;
import com.fenglangjuxu.databinding.ActivityMainBinding;
import com.fenglangjuxu.fragment.home.AmusementFragment;
import com.fenglangjuxu.fragment.home.BusinessFragment;
import com.fenglangjuxu.fragment.home.MineFragment;
import com.fenglangjuxu.fragment.home.SocialFragment;
import com.fenglangjuxu.model.main.MainMp;
import com.gyf.immersionbar.ImmersionBar;
import com.fenglangjuxu.base.activity.BaseImmersionActivity;

import java.util.ArrayList;
import java.util.List;

@Route(path = "/app/main")
public class MainActivity extends BaseImmersionActivity<ActivityMainBinding, MainMp> {
    private List<Fragment> fragments = new ArrayList<>(4);


    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_main;
    }

    @Override
    public int initVariableId() {
        return BR.main;
    }

    @Override
    public MainMp initViewModel() {
        initFragment();
        return new MainMp(getApplication(), binding);
    }

    private void initFragment() {
        BusinessFragment businessFragment = new BusinessFragment();
        SocialFragment socialFragment = new SocialFragment();
        AmusementFragment amusementFragment = new AmusementFragment();
        MineFragment mineFragment = new MineFragment();
        fragments.add(businessFragment);
        fragments.add(socialFragment);
        fragments.add(amusementFragment);
        fragments.add(mineFragment);
        BaseFragmentPagerAdapter pagerAdapter = new BaseFragmentPagerAdapter(getSupportFragmentManager(), fragments);
        binding.mainPager.setAdapter(pagerAdapter);
        binding.mainPager.setCanScroll(false);
        binding.mainPager.setOffscreenPageLimit(4);
    }

    @Override
    public void initImmersionBar() {
        ImmersionBar.with(this).init();
    }
}
