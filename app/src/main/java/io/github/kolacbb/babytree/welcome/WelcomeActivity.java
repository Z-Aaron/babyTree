package io.github.kolacbb.babytree.welcome;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import io.github.kolacbb.babytree.R;
import io.github.kolacbb.babytree.model.Account;
import io.github.kolacbb.babytree.net.RetrofitManager;
import io.github.kolacbb.babytree.net.service.AccountService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WelcomeActivity extends AppCompatActivity {

    private EditText mEmailText;
    private EditText mPasswordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        mEmailText = (EditText) findViewById(R.id.text_input_email);
        mPasswordText = (EditText) findViewById(R.id.text_input_password);
    }

    public void onButtonClicked(View v) {
        switch (v.getId()) {
            case R.id.login:
                login();
                break;
        }
    }

    public void login() {
        String emailJson = "%7B%22name%22:%22" + mEmailText.getText().toString() + "%22%7D";
//        try {
//            emailJson = URLEncoder.encode(emailJson, "UTF-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }

        Log.e("EMAIL JSON:", emailJson);
        AccountService service = RetrofitManager.getInstance().create(AccountService.class);
        Call<Account> call = service.query(emailJson);
        call.enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                Gson gson = new Gson();
                Log.e("ACCOUNT:", gson.toJson(response.body()));
            }

            @Override
            public void onFailure(Call<Account> call, Throwable throwable) {

            }
        });
    }
}
