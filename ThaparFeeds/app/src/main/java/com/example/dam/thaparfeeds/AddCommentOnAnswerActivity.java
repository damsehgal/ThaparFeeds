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

public class AddCommentOnAnswerActivity extends AppCompatActivity
{
	private static final String TAG = AddCommentOnAnswerActivity.class.getSimpleName();
	EditText comment;
	Button send;
	int questionId , answerId;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_comment_on_answer);
		comment = (EditText) findViewById(R.id.comment_on_answer);
		send = (Button) findViewById(R.id.send_answer_comment);
		Intent intent = getIntent();
		questionId = intent.getIntExtra("question_id",0);
		answerId = intent.getIntExtra("answer_id",0);
		Log.e(TAG, "onCreate: " + answerId );
		send.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				if (comment.getText().toString().isEmpty() ||comment.getText().toString().length() < 20 )
				{
					Toast.makeText(AddCommentOnAnswerActivity.this, "short comment", Toast.LENGTH_SHORT).show();
				}
				else
				{
					HashMap<String, String> hashMap = new HashMap<String, String>();
					hashMap.put("comment", comment.getText().toString());
					hashMap.put("name", MainActivity.user_id);
					PostRequestSend postRequestSend = new PostRequestSend("https://thaparfeeds.herokuapp.com/commentAnswer/" + questionId + "/" + answerId + "/", hashMap);
					postRequestSend.setTaskDoneListener(new PostRequestSend.TaskDoneListener()
					{
						@Override
						public String onTaskDone(String str) throws JSONException
						{
							Toast.makeText(AddCommentOnAnswerActivity.this, "Successful", Toast.LENGTH_SHORT).show();
							Intent intent = new Intent(getApplicationContext(), QuestionDetailActivity.class);
							intent.putExtra("id of question", questionId);
							startActivity(intent);
							finish();
							return null;
						}
					});
					postRequestSend.execute();
				}
			}
		});
	}
}
