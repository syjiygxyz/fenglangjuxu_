package com.fenglangjuxu.fragment.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.fenglangjuxu.BR;
import com.fenglangjuxu.R;
import com.fenglangjuxu.databinding.FragmentMainMineBinding;
import com.fenglangjuxu.fragment.BaseImmersionFragment;
import com.fenglangjuxu.model.main.MineFragmentMp;
import com.gyf.immersionbar.ImmersionBar;

/**
 * @author syj
 * @date 2019/6/6
 */
public class MineFragment extends BaseImmersionFragment<FragmentMainMineBinding, MineFragmentMp> {
    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_main_mine;
    }

    @Override
    public int initVariableId() {
        return BR.mine;
    }

    @Override
    public MineFragmentMp initViewModel() {
        return new MineFragmentMp(getActivity().getApplication());
    }

    @Override
    public void initImmersionBar() {
        ImmersionBar.with(this)
                .init();
    }
}
