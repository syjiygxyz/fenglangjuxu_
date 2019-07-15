package com.fenglangjuxu.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.fenglangjuxu.BR;
import com.fenglangjuxu.R;
import com.fenglangjuxu.base.activity.BaseImmersionActivity;
import com.fenglangjuxu.base.utils.ValidationUtils;
import com.fenglangjuxu.databinding.ActivityLoginBinding;
import com.fenglangjuxu.model.LoginMp;

@Route(path = "/app/login")
public class LoginActivity extends BaseImmersionActivity<ActivityLoginBinding, LoginMp> {


    private LoginMp loginMp;

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_login;
    }

    @Override
    public int initVariableId() {
        return BR.login;
    }

    @Override
    public LoginMp initViewModel() {
        loginMp = new LoginMp(getApplication());
        return loginMp;
    }

    @Override
    public void initData() {
        super.initData();
        if (loginMp != null){
            binding.etMobile.addTextChangedListener(mobileWatcher);
            binding.etCode.addTextChangedListener(codeWatcher);
        }
    }

    private TextWatcher mobileWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (ValidationUtils.checkTelPhone(s.toString())){
                loginMp.mobileLegal.set(true);
                if (loginMp.eCodeLegal.get()){
                    loginMp.loginEnable.set(true);
                }
            }else {
                loginMp.mobileLegal.set(false);
                loginMp.loginEnable.set(false);
            }
        }
    };

    private TextWatcher codeWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (!s.toString().isEmpty()){
                loginMp.eCodeLegal.set(true);
                if (loginMp.mobileLegal.get()){
                    loginMp.loginEnable.set(true);
                }
            }else {
                loginMp.loginEnable.set(false);
                loginMp.eCodeLegal.set(false);
            }
        }
    };

    @Override
    public void initImmersionBar() {

    }
}
