package com.chatsample.register;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.chatsample.R;
import com.chatsample.register.mvp.RegisterContract;
import com.chatsample.register.mvp.RegisterPresenter;

/**
 * A placeholder fragment containing a simple view.
 */
public class RegisterFragment extends Fragment  implements RegisterContract{
    private static final String TAG = RegisterFragment.class.getSimpleName();
    EditText etEmailId,etPassword;
    Button btnRegister;
    RegisterPresenter mRegisterPresenter;

    public RegisterFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        etEmailId=view.findViewById(R.id.etEmailId);
        etPassword=view.findViewById(R.id.etPassword);
        btnRegister=view.findViewById(R.id.btnRegister);

    }
}
