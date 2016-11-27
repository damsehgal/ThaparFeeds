package com.example.dam.thaparfeeds;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;

import java.util.HashMap;

public class AddAnswerActivity extends AppCompatActivity
{
	public static final String GET_ANSWER = "GET_ANSWER ";
	int question_id;
	Button sendAnswer;
	EditText answer;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_answer);
		Intent intent = getIntent();
		question_id =intent.getIntExtra(GET_ANSWER,0);
		answer = (EditText) findViewById(R.id.answer_details);
		sendAnswer = (Button) findViewById(R.id.send_answer);
		sendAnswer.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				HashMap <String,String> hashMap = new HashMap<String, String>();
				hashMap.put("answer",answer.getText().toString());
				hashMap.put("name",MainActivity.user_id);

				PostRequestSend postRequestSend = new PostRequestSend("https://thaparfeeds.herokuapp.com/answers/"+question_id+"/",hashMap);
				postRequestSend.setTaskDoneListener(new PostRequestSend.TaskDoneListener()
				{
					@Override
					public String onTaskDone(String str) throws JSONException
					{
						Toast.makeText(AddAnswerActivity.this, "Successful", Toast.LENGTH_SHORT).show();
						Intent intent = new Intent(getApplicationContext(),QuestionDetailActivity.class);
						intent.putExtra("id of question",question_id);
						startActivity(intent);
						finish();
						return null;
					}
				});
				postRequestSend.execute();
			}
		});
	}
}
