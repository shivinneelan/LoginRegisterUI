package com.mca.loginregisteractivityui;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class UserHomeActivity extends AppCompatActivity {

    EditText name,password,repassword,phone,email;
    TextView username;
    Button update;
    ImageView imageView;
    String uname,uusername,upass,uphone,uemail,user_id;
    DBHelper dbHelper;
    Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);

        username=(TextView) findViewById(R.id.uname);
        name=(EditText) findViewById(R.id.name);
        password=(EditText) findViewById(R.id.pass);
        phone=(EditText) findViewById(R.id.phone);
        email=(EditText) findViewById(R.id.email);
        update=(Button) findViewById(R.id.btnSignup);
        imageView=(ImageView) findViewById(R.id.imageView);

        dbHelper=new DBHelper(this);
        final String MobilePattern = "[0-9]{10}";
        final String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        Bundle bundle=getIntent().getExtras();
        if(bundle!=null)
        {
            user_id=bundle.getString("user_id");
            cursor=dbHelper.getAllDetails(user_id);
        }
        if(cursor.getCount()!=0)
        {
            cursor.moveToNext();
            uusername=cursor.getString(1);
            uname=cursor.getString(2);
            upass=cursor.getString(3);
            uphone=cursor.getString(4);
            uemail=cursor.getString(5);

            username.setText(uusername);
            name.setText(uname);
            password.setText(upass);
            phone.setText(uphone);
            email.setText(uemail);

        }


        update.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                uname = name.getText().toString();
                uusername = username.getText().toString();
                upass = password.getText().toString();
                uphone = phone.getText().toString();
                uemail=email.getText().toString();

                if (uname.isEmpty() || uusername.isEmpty() || upass.isEmpty() || uphone.isEmpty())  // check all fields are enterd
                {
                    Toast.makeText(UserHomeActivity.this,"Enter all fields",Toast.LENGTH_LONG).show();

                } else
                {
                        if (uphone.matches(MobilePattern))  // check phone number valid 10 numbers
                        {
                            if (email.getText().toString().matches(emailPattern)) // check email id
                            {
                                // enter code for register new user.........................

                                Boolean result=dbHelper.updateDetails(user_id,uusername,uname,upass,uphone,uemail);
                                dbHelper.closeDB();
                                if (result==true) {
                                    Toast.makeText(UserHomeActivity.this, "Success", Toast.LENGTH_LONG).show();

                                }

                                else
                                    Toast.makeText(UserHomeActivity.this,"updation Fail",Toast.LENGTH_LONG).show();


                            }else
                            {
                                Toast.makeText(UserHomeActivity.this,"email id not valid",Toast.LENGTH_LONG).show();

                            }

                        } else
                        {
                            Toast.makeText(UserHomeActivity.this,"phone number not valid",Toast.LENGTH_LONG).show();
                        }



                }

            }

        });


        // make password visible
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(password.getInputType()== InputType.TYPE_TEXT_VARIATION_PASSWORD)
                {
                    password.setInputType(InputType.TYPE_CLASS_TEXT);
                }



            }
        });
    }
}
