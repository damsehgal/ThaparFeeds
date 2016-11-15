package com.example.dam.thaparfeeds;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class QuestionDetailActivity extends AppCompatActivity
{
	private static final String TAG = QuestionDetailActivity.class.getSimpleName() ;
	int id;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_question_detail);
		Intent intentFromPrevious = getIntent();
		id = intentFromPrevious.getIntExtra("id of question",0);

	}
	public JSONObject getQuestion ()
	{
		Log.e(TAG, "fill: " + "called" );
		final ArrayList<Societies.Society> arrayList = new ArrayList<>();
		RequestQueue q = Volley.newRequestQueue(this);
		final JSONObject[] jsonObject = new JSONObject[1];
		String url = "https://thaparfeeds.herokuapp.com/questions/" +id;
		StringRequest stringRequest= new StringRequest(Request.Method.GET, url, new Response.Listener<String>()
		{
			@Override
			public void onResponse(String response)
			{
				try
				{
					jsonObject[0] = new JSONObject(response);
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
				Log.e(TAG, "onErrorResponse: " + error.toString() );
			}
		});

		q.add(stringRequest);
		return jsonObject[0];
	}
}
