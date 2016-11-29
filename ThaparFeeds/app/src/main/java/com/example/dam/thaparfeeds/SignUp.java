package com.example.dam.thaparfeeds;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp extends AppCompatActivity
{
	EditText username , password , repPassword ,name;
	Button login , signup;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_up);
		username = (EditText) findViewById(R.id.signup_username);
		password = (EditText) findViewById(R.id.signup_password);
		repPassword = (EditText) findViewById(R.id.repeat_password);
		name = (EditText) findViewById(R.id.name_user);
		login = (Button) findViewById(R.id.btn_login_2);
		signup = (Button) findViewById(R.id.btn_signup_2);
		login.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Intent intent =  new Intent(getApplicationContext(),LoginActivity.class);
				startActivity(intent);
				finish();
			}

		});
		signup.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
					if (password.getText().toString().equals(repPassword.getText().toString()) || username.getText().toString().equals("") ||username.getText().toString().isEmpty() || password.getText().toString().equals("") ||password.getText().toString().isEmpty() ||name.getText().toString().equals("") ||name.getText().toString().isEmpty())
					{
						TaskDone2 taskDone2 = new TaskDone2(" https://thaparfeeds.herokuapp.com/signup/" + username.getText().toString()+ "/" + password.getText().toString()+"/" + name.getText().toString(),getApplicationContext());
						taskDone2.getString(new TaskDone2.VolleyCallBack()
						{
							@Override
							public void onSuccess(String result)
							{
								if (result.equals("0"))
								{
									Toast.makeText(SignUp.this, "User already exists", Toast.LENGTH_SHORT).show();
								}
								else
								{
									Intent intent = new Intent(getApplicationContext(),MainActivity.class);
									intent.putExtra("USER_ID",username.getText().toString());
									startActivity(intent);
									finish();
								}
							}
						});
					}
					else
					{
						Toast.makeText(SignUp.this, "Try Again", Toast.LENGTH_SHORT).show();
					}
			}
		});
	}
}
