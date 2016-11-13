package com.example.dam.thaparfeeds;

import android.content.Context;
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

/**
 * Created by dam on 29/8/16.
 */
public class Questions
{
	private static final String TAG = Questions.class.getSimpleName();
	static Context context;
	public static void setContext(Context context)
	{
		Questions.context = context;
	}
	public static class Question
	{
		int votes, id, numberOfAnswers;
		String question, description;
		Question(JSONObject jsonObject, Context context2)
		{
			try
			{
				votes = jsonObject.getInt("votes");
				id = jsonObject.getInt("id");
				numberOfAnswers = jsonObject.getInt("number_of_answers");
				question = jsonObject.getString("question");
				description = jsonObject.getString("description");
				context = context2;
			}
			catch (JSONException e)
			{
				e.printStackTrace();
			}
		}
	}
	public static ArrayList<Question> getQuestions(final QuestionFragment.QuestionListViewAdapter s)
	{
		Log.e(TAG, "fill: " + "called");
		final ArrayList<Question> arrayList = new ArrayList<>();
		RequestQueue q = Volley.newRequestQueue(context);
		String url = "https://thaparfeeds.herokuapp.com/questions/";
		StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>()
		{
			@Override
			public void onResponse(String response)
			{
				try
				{
					JSONArray jsonArray = new JSONArray(response);
					for (int i = 0; i < jsonArray.length(); i++)
					{
						arrayList.add(new Question(jsonArray.getJSONObject(i), context));
					}
					s.notifyDataSetChanged();
				}
				catch (JSONException e)
				{
					e.printStackTrace();
				}
				Log.e(TAG, "onResponse: " + response);
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
		return arrayList;
	}
}
