package com.mca.loginregisteractivityui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText username,name,password,repassword,phone,email;
    Button signup;
    String uname,uusername,upass,urpass,uphone,uemail;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username=(EditText) findViewById(R.id.uname);
        name=(EditText) findViewById(R.id.name);
        password=(EditText) findViewById(R.id.pass);
        repassword=(EditText) findViewById(R.id.repass);
        phone=(EditText) findViewById(R.id.phone);
        email=(EditText) findViewById(R.id.email);
        signup=(Button) findViewById(R.id.btnSignup);
        dbHelper=new DBHelper(this);
        final String MobilePattern = "[0-9]{10}";
        final String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";



        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uname = name.getText().toString();
                uusername = username.getText().toString();
                upass = password.getText().toString();
                urpass = repassword.getText().toString();
                uphone = phone.getText().toString();
                uemail=email.getText().toString();

                if (uname.isEmpty() || uusername.isEmpty() || upass.isEmpty() || urpass.isEmpty() || uphone.isEmpty())  // check all fields are enterd
                {
                    Toast.makeText(RegisterActivity.this,"Enter all fields",Toast.LENGTH_LONG).show();

                } else
                {
                    if (upass.equals(urpass)) {   // checking password are equal
                        if (uphone.matches(MobilePattern))  // check phone number valid 10 numbers
                        {
                            if (email.getText().toString().matches(emailPattern)) // check email id
                            {
                                // enter code for register new user.........................

                               Boolean result=dbHelper.insertDetails(uusername,uname,upass,uphone,uemail);
                               if (result==true)
                                Toast.makeText(RegisterActivity.this,"Success",Toast.LENGTH_LONG).show();
                               else
                                   Toast.makeText(RegisterActivity.this,"Insertion Fail",Toast.LENGTH_LONG).show();


                            }else
                            {
                                Toast.makeText(RegisterActivity.this,"email id not valid",Toast.LENGTH_LONG).show();

                            }

                        } else
                        {
                            Toast.makeText(RegisterActivity.this,"phone number not valid",Toast.LENGTH_LONG).show();
                        }

                    } else {
                        Toast.makeText(RegisterActivity.this, "password cannot match", Toast.LENGTH_LONG).show();
                    }

                }

            }

        });

    }
}
