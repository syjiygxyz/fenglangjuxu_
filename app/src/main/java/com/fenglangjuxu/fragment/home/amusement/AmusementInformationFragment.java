package com.fenglangjuxu.fragment.home.amusement;

import android.app.Application;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.fenglangjuxu.R;
import com.fenglangjuxu.BR;
import com.fenglangjuxu.databinding.FragmentAmusementInfomationBinding;
import com.fenglangjuxu.model.main.amusement.AmusementInformationFragmentMp;

import me.goldze.mvvmhabit.base.BaseFragment;
import me.goldze.mvvmhabit.utils.Utils;

/**
 * @author syj
 * @date 2019/6/12
 */
public class AmusementInformationFragment extends BaseFragment<FragmentAmusementInfomationBinding, AmusementInformationFragmentMp> {
    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_amusement_infomation;
    }

    @Override
    public int initVariableId() {
        return BR.information;
    }

    @Override
    public AmusementInformationFragmentMp initViewModel() {
        return new AmusementInformationFragmentMp((Application) Utils.getContext());
    }
}
