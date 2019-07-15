package com.fenglangjuxu.fragment.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.fenglangjuxu.BR;
import com.fenglangjuxu.R;
import com.fenglangjuxu.databinding.FragmentMainBusinessBinding;
import com.fenglangjuxu.fragment.BaseImmersionFragment;
import com.fenglangjuxu.model.main.business.BusinessFragmentMp;
import com.gyf.immersionbar.ImmersionBar;

/**
 * @author syj
 * @date 2019/6/6
 */
public class BusinessFragment extends BaseImmersionFragment<FragmentMainBusinessBinding, BusinessFragmentMp> {

    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_main_business;
    }

    @Override
    public int initVariableId() {
        return BR.business;
    }

    @Override
    public BusinessFragmentMp initViewModel() {
        return new BusinessFragmentMp(getActivity().getApplication());
    }

    @Override
    public void initImmersionBar() {
        ImmersionBar.with(this)
                .statusBarView(R.id.business_status_bar)
                .statusBarDarkFont(true, 0.2f)
                .init();
    }
}
