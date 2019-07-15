package com.fenglangjuxu.takeout.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.fenglangjuxu.R;
import com.fenglangjuxu.BR;
import com.fenglangjuxu.base.activity.BaseImmersionActivity;
import com.fenglangjuxu.base.model.NavigationMp;
import com.fenglangjuxu.databinding.ActivityTakeOutHomeBinding;
import com.fenglangjuxu.takeout.model.home.TakeOutHomeMp;

import java.util.List;

/**
 * @author syj
 * @date 2019/6/19
 */

@Route(path = "/takeout/home")
public class TakeOutHomeActivity extends BaseImmersionActivity<ActivityTakeOutHomeBinding, TakeOutHomeMp> {
    @Override
    public void initImmersionBar() {

    }

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_take_out_home;
    }

    @Override
    public int initVariableId() {
        return BR.home;
    }

    @Override
    public TakeOutHomeMp initViewModel() {
        binding.setNavigation(new NavigationMp(getApplication(), "外卖"));
        return new TakeOutHomeMp(getApplication());
    }
}
