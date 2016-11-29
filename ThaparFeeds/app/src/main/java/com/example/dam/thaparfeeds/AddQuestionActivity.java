package com.example.dam.thaparfeeds;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;

import java.util.HashMap;

public class AddQuestionActivity extends AppCompatActivity
{
	EditText question , description;
	Button send;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_question);
		question = (EditText) findViewById(R.id.add_question_question);
		description = (EditText) findViewById(R.id.add_question_description);
		send= (Button) findViewById(R.id.add_question_send);
		send.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				if (question.getText().toString().isEmpty() || question.getText().toString().length() < 20  || description.getText().toString().isEmpty() || description.getText().toString().length() < 20) 		{
					Toast.makeText(AddQuestionActivity.this, "question or description too short", Toast.LENGTH_SHORT).show();
				}
				else
				{HashMap <String , String> hashMap = new HashMap<String, String>();

				hashMap.put("question", question.getText().toString());
				hashMap.put("description", description.getText().toString());
				hashMap.put("name",MainActivity.user_id);
				PostRequestSend postRequestSend = new PostRequestSend("https://thaparfeeds.herokuapp.com/questions",hashMap);
				postRequestSend.setTaskDoneListener(new PostRequestSend.TaskDoneListener()
				{
					@Override
					public String onTaskDone(String str) throws JSONException
					{
						Log.e("TAG", "onTaskDone: "+ str );
						Toast.makeText(getApplicationContext(), "send successfully", Toast.LENGTH_SHORT).show();
						Intent intent = new Intent(getApplicationContext(),MainActivity.class);
						intent.putExtra("USER_ID",MainActivity.user_id);
						startActivity(intent);
						finish();
						return null;

					}
				});
				postRequestSend.execute();
			}}
		});
	}
}
