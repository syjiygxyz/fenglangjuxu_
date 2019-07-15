package com.fenglangjuxu.fragment.home;

import android.app.Application;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fenglangjuxu.R;
import com.fenglangjuxu.BR;
import com.fenglangjuxu.databinding.FragmentPagerCocialBinding;
import com.fenglangjuxu.fragment.BaseImmersionFragment;
import com.fenglangjuxu.model.main.social.SocialPagerFragmentMp;

import me.goldze.mvvmhabit.utils.Utils;

/**
 * @author syj
 * @date 2019/6/17
 */
public class SocialPagerFragment extends BaseImmersionFragment<FragmentPagerCocialBinding, SocialPagerFragmentMp> {
    private String type;

    public static SocialPagerFragment newInstance(String type){
        SocialPagerFragment fragment = new SocialPagerFragment();
        Bundle bundle = new Bundle();
        bundle.putString("type", type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (getArguments() != null){
            this.type = getArguments().getString("type");
        }

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void initImmersionBar() {

    }

    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_pager_cocial;
    }

    @Override
    public int initVariableId() {
        return BR.social;
    }

    @Override
    public SocialPagerFragmentMp initViewModel() {
        return new SocialPagerFragmentMp((Application) Utils.getContext(), type);
    }
}
