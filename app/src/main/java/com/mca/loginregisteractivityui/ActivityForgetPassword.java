package com.mca.loginregisteractivityui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityForgetPassword extends AppCompatActivity {


    Button reset;
    EditText username,password,email;
    String sUsername,sPassword,sEmail;
    DBHelper dbHelper;
    final String MobilePattern = "[0-9]{10}";
    final String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        reset=(Button) findViewById(R.id.buttonForget);
        username=(EditText) findViewById(R.id.usernameForget);
        password=(EditText) findViewById(R.id.passwordForget);
        email=(EditText) findViewById(R.id.emailForget);
        dbHelper=new DBHelper(this);


    reset.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            sUsername=username.getText().toString().trim();
            sPassword=password.getText().toString().trim();
            sEmail=email.getText().toString().trim();

            if(sUsername.isEmpty()||sPassword.isEmpty()||sEmail.isEmpty())  // check all field etered
            {
                Toast.makeText(ActivityForgetPassword.this,"fill all fields",Toast.LENGTH_LONG).show();
            }
            else
            {
                if(sEmail.matches(emailPattern)) // check email valid
                {

                    //  code for reset password
                    Boolean result=dbHelper.resetpassword(sUsername,sPassword,sEmail);
                    if(result==true)
                    {
                        Toast.makeText(ActivityForgetPassword.this,"password Reset",Toast.LENGTH_LONG).show();
                    }
                    else
                    { Toast.makeText(ActivityForgetPassword.this,"password Reset Failed",Toast.LENGTH_LONG).show();
                    }
                }
                else
                {
                    Toast.makeText(ActivityForgetPassword.this,"invalid email",Toast.LENGTH_LONG).show();
                }
            }

        }
    });

    }
}
