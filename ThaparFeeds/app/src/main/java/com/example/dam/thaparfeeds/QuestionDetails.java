package com.example.dam.thaparfeeds;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by dam on 13/11/16.
 */
public class QuestionDetails
{
	private static final String TAG = QuestionDetails.class.getSimpleName();
	ArrayList<Answer> answers;
	ArrayList<Comment> comments;
	int votes;
	String question, description, askedBy;
	OnArrayListFilled onArrayListFilled;
	public void setOnArrayListFilled(OnArrayListFilled onArrayListFilled)
	{
		this.onArrayListFilled = onArrayListFilled;
		JSONObject j1 = onArrayListFilled.onArrayListSet();
		Log.e(TAG, "setOnArrayListFilled: " + j1.toString() );
		set(j1);
	}
	void set(JSONObject jsonObject){
		try
		{
			Log.e(TAG, "set: " +jsonObject.toString() );

			votes = jsonObject.getInt("votes");
			question = jsonObject.getString("question");
			description = jsonObject.getString("description");
			askedBy = jsonObject.getString("asked_by");
			JSONArray comments2 =  jsonObject.getJSONArray("comments");
			for (int i = 0; i < comments2.length(); i++)
			{
				comments.add(new Comment(comments2.getJSONObject(i)));
			}
			JSONArray answers2 = jsonObject.getJSONArray("answers");
			for (int i = 0; i < answers2.length(); i++)
			{
				answers.add(new Answer(answers2.getJSONObject(i)));
			}
		}
		catch (Exception e)
		{
			Log.e(TAG, "set: c" + jsonObject.toString() );
			e.printStackTrace();
		}
	}
	public QuestionDetails()
	{
		answers = new ArrayList<Answer>();
		comments = new ArrayList<Comment>();
	}


	public interface OnArrayListFilled{
		 JSONObject onArrayListSet();
	}

	public class Comment
	{
		public String getUser()
		{
			return user;
		}
		public String getComment()
		{
			return comment;
		}
		String user, comment;
		public Comment(JSONObject jsonObject)
		{
			try
			{
				this.user = jsonObject.getString("user");
				this.comment = jsonObject.getString("comment");
			}
			catch (JSONException e)
			{
				e.printStackTrace();
			}
		}
	}

	public class Answer
	{
		ArrayList<Comment> comments;
		String user, answer;
		int id2;
		int votes;
		public ArrayList<Comment> getComments()
		{
			return comments;
		}
		public String getUser()
		{
			return user;
		}
		public String getAnswer()
		{
			return answer;
		}
		public Answer(JSONObject jsonObject)
		{
			comments = new ArrayList<>();
			try
			{
				id2 = jsonObject.getInt("id");
				Log.e(TAG, "Answer: " +id2 );
				user = jsonObject.getString("user");
				answer = jsonObject.getString("answer");
				votes = jsonObject.getInt("votes");
				JSONArray comments = jsonObject.getJSONArray("comments");
				for (int i = 0; i < comments.length(); i++)
				{
					this.comments.add(new Comment(comments.getJSONObject(i)));
				}
			}
			catch (JSONException e)
			{
				e.printStackTrace();
			}
		}
	}
}
