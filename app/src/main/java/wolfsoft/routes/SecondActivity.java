package wolfsoft.routes;

import android.content.Intent;
import android.os.Bundle;
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
import wolfsoft.Interfaces.OTP;

/**
 * Created by admin on 3/3/2016.
 */
public class SecondActivity extends MainActivity{

    String phoneNo;
    String otp;
    private TextView signup;
    private TextView signin;
    private TextView fb;
    private TextView account;
    private EditText email;
    private EditText password;
    private EditText user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.otp);


        fb = (TextView) findViewById(R.id.fb);
        account = (TextView) findViewById(R.id.account);
        email = (EditText) findViewById(R.id.email);

        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phoneNo = email.getText().toString();
                Intent intent=new Intent(getApplication(),OTPTest.class);
                startActivity(intent);
                //generateOTP();

            }
        });}


    public void generateOTP() {
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(Urls.ROOT_URL).build();
        OTP api = adapter.create(OTP.class);
        api.generateOTP(
                "true",
                phoneNo,


                new retrofit.Callback<Response>() {

                    @Override
                    public void success(Response result, Response response) {
                        BufferedReader reader = null;
                        String output = "";
                        try {
                            reader = new BufferedReader(new InputStreamReader(result.getBody().in()));

                            //Reading the output in the string
                            output = reader.readLine();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Toast.makeText(getApplication(), output, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Toast.makeText(getApplication(), error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

}

