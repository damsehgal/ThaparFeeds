package com.example.dam.thaparfeeds;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by dam on 13/11/16.
 */
public class QuestionDetails
{
	ArrayList<Answer> answers;
	ArrayList<Comment> comments;
	int votes;
	String question, description, askedBy;
	public QuestionDetails(JSONObject jsonObject)
	{
		JSONArray comments = null;
		try
		{
			comments = jsonObject.getJSONArray("comments");
			for (int i = 0; i < comments.length(); i++)
			{
				this.comments.add(new Comment(comments.getJSONObject(i)));
			}
			JSONArray answers = jsonObject.getJSONArray("answers");
			for (int i = 0; i < answers.length(); i++)
			{
				this.answers.add(new Answer(answers.getJSONObject(i)));
			}
			votes = jsonObject.getInt("votes");
			question = jsonObject.getString("question");
			description = jsonObject.getString("description");
			askedBy = jsonObject.getString("asked_by");
		}
		catch (JSONException e)
		{
			e.printStackTrace();
		}
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
			try
			{
				user = jsonObject.getString("user");
				answer = jsonObject.getString("answer");
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
