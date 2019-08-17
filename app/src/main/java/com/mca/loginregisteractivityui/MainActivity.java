package com.mca.loginregisteractivityui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText username,password;
    Button login,signup;
    TextView forgetPass;
    String uname,upassword;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username=(EditText) findViewById(R.id.username);
        password=(EditText) findViewById(R.id.password);
        login=(Button) findViewById(R.id.login);
        signup=(Button) findViewById(R.id.signup);
        imageView=(ImageView) findViewById(R.id.imageView);
        forgetPass=(TextView) findViewById(R.id.forgetPass);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // calling sign up activity
                Intent intent=new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             // code for login
                uname=username.getText().toString();
                upassword=password.getText().toString();
                if(uname.isEmpty()||upassword.isEmpty())
                {
                    Toast.makeText(MainActivity.this,"enter Username and password",Toast.LENGTH_LONG).show();
                }
                else
                {
                    // call for authentication method
                    Toast.makeText(MainActivity.this,"touch on login button",Toast.LENGTH_LONG).show();


                }
            }
        });

        forgetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // code for forget password
                Toast.makeText(MainActivity.this,"touch on forget password",Toast.LENGTH_LONG).show();

            }
        });

    }
}
