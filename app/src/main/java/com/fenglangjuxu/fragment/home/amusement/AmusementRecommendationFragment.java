package com.fenglangjuxu.fragment.home.amusement;

import android.app.Application;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.fenglangjuxu.BR;
import com.fenglangjuxu.R;
import com.fenglangjuxu.databinding.FragmentAmusementRecommendationBinding;
import com.fenglangjuxu.model.main.amusement.AmusementRecommendationFragmentMp;

import me.goldze.mvvmhabit.base.BaseFragment;
import me.goldze.mvvmhabit.utils.Utils;

/**
 * @author syj
 * @date 2019/6/12
 */
public class AmusementRecommendationFragment extends BaseFragment<FragmentAmusementRecommendationBinding, AmusementRecommendationFragmentMp> {
    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_amusement_recommendation;
    }

    @Override
    public int initVariableId() {
        return BR.recommendation;
    }

    @Override
    public AmusementRecommendationFragmentMp initViewModel() {
        return new AmusementRecommendationFragmentMp((Application) Utils.getContext());
    }
}
