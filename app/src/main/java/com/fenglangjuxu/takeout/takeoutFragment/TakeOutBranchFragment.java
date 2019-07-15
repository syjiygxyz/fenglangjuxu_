package com.fenglangjuxu.takeout.takeoutFragment;

import android.app.Application;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.fenglangjuxu.R;
import com.fenglangjuxu.BR;
import com.fenglangjuxu.databinding.FragmentTakeoutBranchBinding;
import com.fenglangjuxu.fragment.BaseImmersionFragment;
import com.fenglangjuxu.takeout.model.screening.TakeOutBranchFragmentMp;

import me.goldze.mvvmhabit.utils.Utils;

/**
 * @author syj
 * @date 2019/7/4
 */
public class TakeOutBranchFragment extends BaseImmersionFragment<FragmentTakeoutBranchBinding, TakeOutBranchFragmentMp> {
    @Override
    public void initImmersionBar() {

    }

    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_takeout_branch;
    }

    @Override
    public int initVariableId() {
        return BR.branch;
    }

    @Override
    public TakeOutBranchFragmentMp initViewModel() {
        return new TakeOutBranchFragmentMp((Application) Utils.getContext());
    }
}
