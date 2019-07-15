package com.fenglangjuxu.fragment.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.fenglangjuxu.R;
import com.fenglangjuxu.BR;
import com.fenglangjuxu.adapter.BaseFragmentPagerAdapter;
import com.fenglangjuxu.base.utils.ScreenUtils;
import com.fenglangjuxu.databinding.FragmentMainSocialBinding;
import com.fenglangjuxu.fragment.BaseImmersionFragment;
import com.fenglangjuxu.model.main.SocialFragmentMp;
import com.gyf.immersionbar.ImmersionBar;

import java.util.ArrayList;
import java.util.List;

/**
 * @author syj
 * @date 2019/6/6
 */
public class SocialFragment extends BaseImmersionFragment<FragmentMainSocialBinding, SocialFragmentMp> {
    private final static int SOCIAL_PAGER_NUM = 2;

    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_main_social;
    }

    @Override
    public int initVariableId() {
        return BR.social;
    }

    @Override
    public SocialFragmentMp initViewModel() {
        return new SocialFragmentMp(getActivity().getApplication(), binding);
    }

    @Override
    public void initImmersionBar() {
        ViewGroup.LayoutParams layoutParams = binding.socialToolBar.getLayoutParams();
        layoutParams.height = (int) ScreenUtils.dp2px(getActivity(), 60) + ImmersionBar.getStatusBarHeight(this);
        binding.socialToolBar.setLayoutParams(layoutParams);
        FrameLayout.LayoutParams imageLayoutParams = (FrameLayout.LayoutParams)binding.socialTopImg.getLayoutParams();
        imageLayoutParams.height = (int) (ScreenUtils.getScreenHeight(getActivity()) * 0.57);
    }

    @Override
    public void initData() {
        List<Fragment> fragments = new ArrayList<>(SOCIAL_PAGER_NUM);
        fragments.add(SocialPagerFragment.newInstance("all"));
        fragments.add(SocialPagerFragment.newInstance("friends"));
        binding.socialPager.setAdapter(new BaseFragmentPagerAdapter(getChildFragmentManager(), fragments));
        binding.socialPager.setCanScroll(false);
        binding.socialPager.setOffscreenPageLimit(SOCIAL_PAGER_NUM);
    }
}
