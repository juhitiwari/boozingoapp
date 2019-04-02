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
public class OTPTest extends MainActivity{

    String phoneNo;
    String otp;
    EditText et1,et2,et3,et4,et5,et6;

    private TextView account;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.otptest);


        account = (TextView) findViewById(R.id.account);
        et1 = findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);
        et3 = findViewById(R.id.et3);
        et4 = findViewById(R.id.et4);
        et5 = findViewById(R.id.et5);
        et6 = findViewById(R.id.et6);

        et1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 1) {
                    et2.requestFocus();
                } else if (s.length() == 0) {
                    et1.clearFocus();
                }
            }
        });

        et2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 1) {
                    et3.requestFocus();
                } else if (s.length() == 0) {
                    et1.requestFocus();
                }
            }
        });

        et3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 1) {
                    et4.requestFocus();
                } else if (s.length() == 0) {
                    et2.requestFocus();
                }
            }
        });

        et4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 1) {
                    et5.requestFocus();
                } else if (s.length() == 0) {
                    et3.requestFocus();
                }
            }
        });

        et5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 1) {
                    et6.requestFocus();
                } else if (s.length() == 0) {
                    et4.requestFocus();
                }
            }
        });

        et6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 1) {
                    et6.clearFocus();
                } else if (s.length() == 0) {
                    et5.requestFocus();
                }
            }
        });


        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                otp = et1.getText().toString()+et2.getText().toString()+et3.getText().toString()+et4.getText().toString()+et5.getText().toString()+et6.getText().toString();
                Toast.makeText(OTPTest.this, otp, Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getApplication(),Register.class);
                startActivity(intent);
                //confirmOTP();

            }
        });}


    private void confirmOTP() {
        RestAdapter adapter=new RestAdapter.Builder()
                .setEndpoint(Urls.ROOT_URL).build();
        ConfirmOTP api=adapter.create(ConfirmOTP.class);
        api.confirmOTP(
                otp,
                phoneNo,



                new retrofit.Callback<Response>() {

                    @Override
                    public void success(Response result, Response response) {
                        BufferedReader reader = null;
                        String output = "";
                        try{
                            reader = new BufferedReader(new InputStreamReader(result.getBody().in()));

                            //Reading the output in the string
                            output = reader.readLine();
                        }
                        catch (IOException e){
                            e.printStackTrace();
                        }
                        if(output.equals("OK")) {
                            Intent intent=new Intent(getApplication(),Register.class);
                           /* Bundle bundle = new Bundle();
                            bundle.putString("phone",phoneNo);
                            intent.putExtras(bundle);*/
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Toast.makeText(getApplication(), error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        );

    }


}

