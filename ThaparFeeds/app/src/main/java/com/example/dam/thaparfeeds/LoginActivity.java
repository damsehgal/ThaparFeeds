package com.example.dam.thaparfeeds;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity
{
	private static final String TAG = LoginActivity.class.getSimpleName();
	EditText username , password;
	Button login , signup;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		Log.e(TAG, "onCreate: " );
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		username = (EditText) findViewById(R.id.username);
		password = (EditText) findViewById(R.id.password);
		login = (Button) findViewById(R.id.btn_login);
		signup = (Button) findViewById(R.id.btn_signup);
		login.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				TaskDone2 taskDone2 = new TaskDone2("https://thaparfeeds.herokuapp.com/login/"+username.getText().toString()+"/"+password.getText().toString(),getApplicationContext());
				taskDone2.getString(new TaskDone2.VolleyCallBack()
				{
					@Override
					public void onSuccess(String result)
					{
						Log.e(TAG, "onSuccess: " + result );
						if (result.equals( "0"))
						{
							Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
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
		});
		signup.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Intent intent  = new Intent(getApplicationContext(),SignUp.class);
				startActivity(intent);
				finish();
			}
		});/*
		Intent intent = new Intent(this, MainActivity.class);

		startActivity(intent);*/
	}
}
