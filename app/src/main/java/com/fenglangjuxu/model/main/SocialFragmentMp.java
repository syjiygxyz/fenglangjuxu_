package com.fenglangjuxu.model.main;

import android.app.Application;
import android.databinding.ObservableBoolean;
import android.support.annotation.NonNull;
import android.text.TextPaint;

import com.fenglangjuxu.databinding.FragmentMainSocialBinding;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;

/**
 * @author syj
 * @date 2019/6/6
 */
public class SocialFragmentMp extends BaseViewModel {
    private FragmentMainSocialBinding binding;
    public ObservableBoolean selectedAll = new ObservableBoolean(true);
//    private boolean selectedAll = true;
    private TextPaint allPaint, friendsPaint;

    public SocialFragmentMp(@NonNull Application application) {
        super(application);
    }

    public SocialFragmentMp(@NonNull Application application, FragmentMainSocialBinding binding){
        super(application);
        this.binding = binding;
        allPaint = binding.socialTabAll.getPaint();
        friendsPaint = binding.socialTabFriends.getPaint();
        allPaint.setFakeBoldText(true);
        binding.socialTabAll.postInvalidate();
        friendsPaint.setFakeBoldText(true);
        binding.socialTabFriends.postInvalidate();
    }
    public BindingCommand allCLick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            selectedAll.set(true);
            binding.socialTabAll.setTextSize(16);
            binding.socialTabFriends.setTextSize(14);
            binding.socialPager.setCurrentItem(0, false);
        }
    });

    public BindingCommand friendsClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            selectedAll.set(false);
            binding.socialTabAll.setTextSize(14);
            binding.socialTabFriends.setTextSize(16);
            binding.socialPager.setCurrentItem(1, false);
        }
    });
}
