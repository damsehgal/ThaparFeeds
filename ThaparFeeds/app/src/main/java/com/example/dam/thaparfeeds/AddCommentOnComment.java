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

public class AddCommentOnComment extends AppCompatActivity
{
	int question_id;
	EditText comment;
	Button send;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_comment_on_comment);
		Intent intent = getIntent();
		question_id = intent.getIntExtra("question_id",0);
		comment = (EditText) findViewById(R.id.add_comment_on_question_in);
		send = (Button) findViewById(R.id.send_question_comment);
		send.setOnClickListener(new View.OnClickListener()
		{
				@Override
				public void onClick(View v)
				{
					HashMap<String,String> hashMap = new HashMap<String, String>();
					hashMap.put("comment",comment.getText().toString());
					hashMap.put("name",MainActivity.user_id);

					PostRequestSend postRequestSend = new PostRequestSend("https://thaparfeeds.herokuapp.com/commentQuestion/"+question_id+"/",hashMap);
					postRequestSend.setTaskDoneListener(new PostRequestSend.TaskDoneListener()
					{
						@Override
						public String onTaskDone(String str) throws JSONException
						{
							Toast.makeText(AddCommentOnComment.this, "Successful", Toast.LENGTH_SHORT).show();
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
