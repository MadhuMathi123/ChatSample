package com.chatsample.register;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.chatsample.R;
import com.chatsample.register.mvp.RegisterContract;
import com.chatsample.register.mvp.RegisterPresenter;
import com.chatsample.user.UserActivity;
import com.chatsample.user.mvp.addUser.AddUserContract;
import com.chatsample.user.mvp.addUser.AddUserPresenter;
import com.google.firebase.auth.FirebaseUser;

/**
 * A placeholder fragment containing a simple view.
 */
public class RegisterFragment extends Fragment  implements RegisterContract.View, View.OnClickListener,AddUserContract.view {
    private static final String TAG = RegisterFragment.class.getSimpleName();
    EditText etEmailId,etPassword;
    Button btnRegister;
    RegisterPresenter mRegisterPresenter;
    private ProgressDialog mProgressDialog;
    private AddUserPresenter mAddUserPresenter;

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
        btnRegister.setOnClickListener(this);
        mRegisterPresenter=new RegisterPresenter(this);
        mAddUserPresenter=new AddUserPresenter(this);
        mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setTitle(getString(R.string.loading));
        mProgressDialog.setMessage(getString(R.string.please_wait));
        mProgressDialog.setIndeterminate(true);

    }

    @Override
    public void onRegistrationSuccess(FirebaseUser firebaseUser) {
        mProgressDialog.setMessage(getString(R.string.adding_user_to_db));
        Toast.makeText(getActivity(), "Registration Successful!", Toast.LENGTH_SHORT).show();
        mAddUserPresenter.addUser(getActivity().getApplicationContext(), firebaseUser);
    }

    @Override
    public void onRegistrationFailure(String message) {
        mProgressDialog.dismiss();
        mProgressDialog.setMessage(getString(R.string.please_wait));
        Log.e(TAG, "onRegistrationFailure: " + message);
        Toast.makeText(getActivity(), "Registration failed!+\n" + message, Toast.LENGTH_LONG).show();

    }

    @Override
    public void onClick(View view) {
        int viewId = view.getId();

        switch (viewId) {
            case R.id.btnRegister:
                onRegister();
                break;
        }
    }

    private void onRegister() {
        String emailId = etEmailId.getText().toString();
        String password = etPassword.getText().toString();

        mRegisterPresenter.onRegister(getActivity(), emailId, password);
        mProgressDialog.show();
    }

    @Override
    public void onAddUserSuccess(String message) {
        mProgressDialog.dismiss();
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
        UserActivity.startActivity(getActivity(),
                Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
    }

    @Override
    public void onAddUserFailure(String message) {
        mProgressDialog.dismiss();
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }
}
