package com.mca.loginregisteractivityui;

import android.content.Intent;
import android.database.Cursor;
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

    DBHelper dbHelper;
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
        dbHelper=new DBHelper(this);

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
                  Cursor cursor =dbHelper.login(uname,upassword);

                  if(cursor.getCount()==0)
                  {
                      Toast.makeText(MainActivity.this,"Login Fail",Toast.LENGTH_LONG).show();

                  }
                  else
                  {
                      // call next activity after login
                      cursor.moveToNext();
                      String user_id=cursor.getString(0);
                      dbHelper.closeDB();
                      Intent intent=new Intent(MainActivity.this,UserHomeActivity.class);
                      intent.putExtra("user_id",user_id);
                      startActivity(intent);
                  }

                }
            }
        });

        forgetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // code for forget password
                Intent intent=new Intent(MainActivity.this,ActivityForgetPassword.class);
                startActivity(intent);

            }
        });

    }
}
