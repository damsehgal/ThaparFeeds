package com.example.dam.thaparfeeds;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class SplashScreenActivity extends AppCompatActivity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash_screen);
		Intent intent = new Intent(this , LoginActivity.class);
		Log.e("TAG ","onCreate: splash" );

		startActivity(intent);
		finish();
	}
}
