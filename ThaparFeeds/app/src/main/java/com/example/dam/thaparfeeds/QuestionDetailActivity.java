package com.example.dam.thaparfeeds;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
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

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_question_detail);
		Intent intentFromPrevious = getIntent();
		id = intentFromPrevious.getIntExtra("id of question", 0);
		comments = (ListView) findViewById(R.id.question_details_question_comment);
		QuestionDetails currentQuestion = getQuestion();
		//commentsArrayList = currentQuestion.comments;
		//
	}
	public QuestionDetails getQuestion()
	{
		Log.e(TAG, "fill: " + "called");
		final QuestionDetails[] questionDetails = new QuestionDetails[1];
		RequestQueue q = Volley.newRequestQueue(this);
		final JSONObject[] jsonObject = new JSONObject[1];
		String url = "https://thaparfeeds.herokuapp.com/questions/" + id;
		StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>()
		{
			@Override
			public void onResponse(String response)
			{
				try
				{
					jsonObject[0] = new JSONObject(response);
					questionDetails[0] = new QuestionDetails(jsonObject[0]);
					commentsArrayList = questionDetails[0].comments;
					comments.setAdapter(new CommentsAdapter(getApplicationContext(),commentsArrayList));
				}
				catch (JSONException e)
				{
					e.printStackTrace();
				}
			}
		}, new Response.ErrorListener()
		{
			@Override
			public void onErrorResponse(VolleyError error)
			{
				Log.e(TAG, "onErrorResponse: " + error.toString());
			}
		});
		q.add(stringRequest);
		return questionDetails[0];
	}
}
