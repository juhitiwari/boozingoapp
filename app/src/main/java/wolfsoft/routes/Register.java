package wolfsoft.routes;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import wolfsoft.Constants.Urls;
import wolfsoft.Interfaces.ConfirmOTP;
import wolfsoft.Interfaces.OTP;

/**
 * Created by admin on 3/3/2016.
 */
public class Register extends MainActivity{
    private TextView account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2_main);


        account = (TextView) findViewById(R.id.account);


        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(getApplication(),OTPTest.class);
                startActivity(intent);
                //generateOTP();

            }
        });}
    }




