package wolfsoft.routes;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import wolfsoft.Constants.Urls;
import wolfsoft.Interfaces.Login;
import wolfsoft.helper.SQLiteHandler;
import wolfsoft.helper.SessionManager;

public class MainActivity extends AppCompatActivity {

    String uname,pass;
    private SessionManager session;
    private SQLiteHandler db;
    private TextView signup;
    private TextView signin;
    private TextView fb;
    private TextView btnsignin;
    private TextView account;
    private EditText email;
    private EditText password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        signup = (TextView)findViewById(R.id.signup);
        signin = (TextView)findViewById(R.id.signin);
        fb = (TextView)findViewById(R.id.fb);
        account = (TextView)findViewById(R.id.account);
        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);
        btnsignin=(TextView) findViewById(R.id.buttonsignin);
        db = new SQLiteHandler(getApplication());
        session = new SessionManager(getApplication());
        if (session.isLoggedIn()) {
            // User is already logged in. Take him to main activity
            /*Intent intent = new Intent(getActivity(),MainActivity.class);
            startActivity(intent);
            getActivity().finish();*/
        }
btnsignin.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        uname=email.getText().toString();
        pass=password.getText().toString();
        loginUser();
    }
});
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent it = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(it);
            }
        });



    }


    private void loginUser() {
        RestAdapter adapter=new RestAdapter.Builder()
                .setEndpoint(Urls.ROOT_URL).build();
        Login api=adapter.create(Login.class);
        //final ProgressDialog progressDoalog;
       // progressDoalog = new ProgressDialog(getApplication());
       // progressDoalog.setTitle("Logging you in...");
        //progressDoalog.show();
        api.login(

                uname,
                pass,




                new retrofit.Callback<Response>() {

                    @Override
                    public void success(Response result, Response response) {
                        BufferedReader reader = null;
                        String output = "";
                        try{
                            reader = new BufferedReader(new InputStreamReader(result.getBody().in()));

                            //Reading the output in the string
                            output = reader.readLine();
                            //if(progressDoalog.isShowing()) {
                              //  progressDoalog.dismiss();
                            //}

                        }
                        catch (IOException e){
                            e.printStackTrace();
                        }

                        try {
                            JSONObject jObj = new JSONObject(output);
                            session.setLogin(true);

                            // Now store the user in SQLite

                            String token=jObj.getString("token");


                            // Inserting row in users table
                            db.addUser(uname, token);

                            // Launch main activity
                            //Intent intent = new Intent(getApplication(),
                              //      MainActivity.class);
                            //startActivity(intent);
                            Intent intent=new Intent(getApplication(), ListActivity.class);
                            startActivity(intent);
                            finish();
                        } catch (JSONException e) {
                            Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Toast.makeText(getApplication(), error.toString(), Toast.LENGTH_LONG).show();
                        //if(progressDoalog.isShowing()) {
                          //  progressDoalog.dismiss();
                        //}

                    }
                }
        );

    }


}
