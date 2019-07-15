package com.fenglangjuxu.takeout.takeoutFragment;

import android.app.Application;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.fenglangjuxu.R;
import com.fenglangjuxu.BR;
import com.fenglangjuxu.fragment.BaseImmersionFragment;
import com.fenglangjuxu.takeout.model.store.TakeOutCommentFragmentMp;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.utils.Utils;

/**
 * @author syj
 * @date 2019/7/3
 */
public class TakeOutCommentFragment extends BaseImmersionFragment {
    @Override
    public void initImmersionBar() {

    }

    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_takeout_comment;
    }

    @Override
    public int initVariableId() {
        return BR.comment;
    }

    @Override
    public BaseViewModel initViewModel() {
        return new TakeOutCommentFragmentMp((Application) Utils.getContext());
    }
}
