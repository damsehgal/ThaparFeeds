package com.example.dam.thaparfeeds;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class QuestionDetailActivity extends AppCompatActivity
{
	private static final String TAG = QuestionDetailActivity.class.getSimpleName();
	int id;
	ImageView upvote, downvote;
	TextView voteCount, question, description, askedBy;
	ListView comments, answers;
	ArrayList<QuestionDetails.Comment> commentsArrayList;
	ArrayList<QuestionDetails.Answer> answerArrayList;
	QuestionDetails currentQuestion;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_question_detail);
		Intent intentFromPrevious = getIntent();
		id = intentFromPrevious.getIntExtra("id of question", 0);
		comments = (ListView) findViewById(R.id.question_details_question_comment);
		answers = (ListView) findViewById(R.id.question_details_all_answers);
		getQuestion();
		//commentsArrayList = currentQuestion.comments;
		//

	}
	public void getQuestion()
	{
		TaskDone2 taskDone = new TaskDone2("https://thaparfeeds.herokuapp.com/questions/"+id,getApplicationContext());
		final QuestionDetails[] temp = new QuestionDetails[1];

		taskDone.getString(new TaskDone2.VolleyCallBack()
		{
			@Override
			public void onSuccess(String result)
			{
				Log.e(TAG, "onSuccess: " + result );
				try
				{
					final JSONObject json = new JSONObject(result);
					currentQuestion = new QuestionDetails();
					currentQuestion.setOnArrayListFilled(new QuestionDetails.OnArrayListFilled()
					{
						@Override
						public JSONObject onArrayListSet()
						{
							return json;
						}
					});
					commentsArrayList = currentQuestion.comments;
					answerArrayList = currentQuestion.answers;
					for (int i = 0 ; i< commentsArrayList.size() ;i++)
						Log.e(TAG, "onSuccess: " + commentsArrayList.get(i).getComment());
					comments.setAdapter(new CommentsAdapter(commentsArrayList,getApplicationContext()));
				}
				catch (JSONException e)
				{
					e.printStackTrace();
				}
			}
		});
		Log.e(TAG, "getQuestion: here"  );

	}
}
