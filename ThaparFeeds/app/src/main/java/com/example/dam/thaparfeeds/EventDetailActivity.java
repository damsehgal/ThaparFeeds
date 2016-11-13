package com.example.dam.thaparfeeds;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EventDetailActivity extends AppCompatActivity
{
	TextView name  , date , description;
	Button link;
	String myUrl;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_event_detail);
		final Intent intent = getIntent();
		name = (TextView) findViewById(R.id.event__name);
		date = (TextView) findViewById(R.id.event__date);
		description = (TextView) findViewById(R.id.event__description);
		link = (Button) findViewById(R.id.event__button);
		name.setText(intent.getStringExtra("__name"));
		date.setText(intent.getStringExtra("__date"));
		description.setText(intent.getStringExtra("__description"));
		myUrl = intent.getStringExtra("__link");
		link.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Intent chromeIntent = new Intent(Intent.ACTION_VIEW);
				chromeIntent.setData(Uri.parse(myUrl));
				startActivity(chromeIntent);
			}
		});
	}

}
