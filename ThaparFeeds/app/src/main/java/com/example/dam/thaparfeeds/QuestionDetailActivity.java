package com.example.dam.thaparfeeds;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
	Button addAnswer , addComment;

	TextView voteCount, question, description, askedBy;
	ListView commentsListView;
	ExpandableListView  answersListView;
	ArrayList<QuestionDetails.Comment> commentsArrayList;
	ArrayList<QuestionDetails.Answer> answerArrayList;
	public static boolean flag = false;
	public static boolean flag2 = false;
	QuestionDetails currentQuestion;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_question_detail);
		Intent intentFromPrevious = getIntent();
		id = intentFromPrevious.getIntExtra("id of question", 0);
		question = (TextView) findViewById(R.id.question_details_question);
		voteCount = (TextView) findViewById(R.id.question_details_votes);
		description = (TextView) findViewById(R.id.question_details_description);
		upvote = (ImageView) findViewById(R.id.up_vote);
		downvote = (ImageView) findViewById(R.id.down_vote);
		upvote.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				if (!flag2)
				{
					String temp  =""+(Integer.parseInt(voteCount.getText().toString())+1);
					voteCount.setText(temp);
					flag2 = true;
				}
				else
				{
					Toast.makeText(QuestionDetailActivity.this, "You already upvoted", Toast.LENGTH_SHORT).show();
				}

			}
		});
		downvote.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				if (!flag2)
				{
					String temp  =""+(Integer.parseInt(voteCount.getText().toString())+1);
					voteCount.setText(temp);
					flag2 = true;
				}
				else
				{
					Toast.makeText(QuestionDetailActivity.this, "You already upvoted", Toast.LENGTH_SHORT).show();
				}
			}
		});
		addComment = (Button) findViewById(R.id.add_comment_on_question);
		addComment.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Intent intent = new Intent(getApplicationContext(),AddCommentOnComment.class);
				intent.putExtra("question_id",id);
				startActivity(intent);
				finish();

			}
		});
		addAnswer = (Button) findViewById(R.id.add_answer);
		addAnswer.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Intent intent = new Intent(getApplicationContext(),AddAnswerActivity.class);
				intent.putExtra(AddAnswerActivity.GET_ANSWER,id);
				startActivity(intent);
			}
		});
		askedBy = (TextView) findViewById(R.id.question_details_user_name);
		commentsListView = (ListView) findViewById(R.id.question_details_question_comment);
		answersListView = (ExpandableListView) findViewById(R.id.question_details_all_answers);
		getQuestion();

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
					question.setText(currentQuestion.question);
					Log.e(TAG, "onSuccess: " + currentQuestion.question );
					String curr_vote = Integer.toString(currentQuestion.votes);
					voteCount.setText(curr_vote);
					description.setText(currentQuestion.description);
					askedBy.setText(currentQuestion.askedBy);
					commentsArrayList = currentQuestion.comments;
					answerArrayList = currentQuestion.answers;
					for (int i = 0 ; i< commentsArrayList.size() ;i++)
						Log.e(TAG, "onSuccess: " + commentsArrayList.get(i).getComment());
					commentsListView.setAdapter(new CommentsAdapter(commentsArrayList,getApplicationContext()));
					answersListView.setAdapter(new AnswerExpandableListViewAdapter());

				}
				catch (JSONException e)
				{
					e.printStackTrace();
				}
			}
		});
		Log.e(TAG, "getQuestion: here"  );

	}
	public class AnswerExpandableListViewAdapter extends BaseExpandableListAdapter{
		private class AnswerHolder
		{
			ImageView answerUpVote , answerDownVote;
			TextView votes ,answer , username;
			//Button addCommentOnAnswer;
		}
		public class CommentView
		{
			TextView username;
			TextView comment;
		}
		@Override
		public int getGroupCount()
		{
			return answerArrayList.size();
		}
		@Override
		public int getChildrenCount(int groupPosition)
		{
			return answerArrayList.get(groupPosition).comments.size();
		}
		@Override
		public QuestionDetails.Answer getGroup(int groupPosition)
		{
			return answerArrayList.get(groupPosition);
		}
		@Override
		public QuestionDetails.Comment getChild(int groupPosition, int childPosition)
		{
			return answerArrayList.get(groupPosition).comments.get(childPosition);
		}
		@Override
		public long getGroupId(int groupPosition)
		{
			return 0;
		}
		@Override
		public long getChildId(int groupPosition, int childPosition)
		{
			return 0;
		}
		@Override
		public boolean hasStableIds()
		{
			return false;
		}
		@Override
		public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent)
		{
			LayoutInflater li = getLayoutInflater();
			final AnswerHolder answerHolder;
			if (convertView == null)
			{
				convertView = li.inflate(R.layout.custom_answer_layout,null);
				answerHolder = new AnswerHolder();
				answerHolder.answer = (TextView) convertView.findViewById(R.id.answer_answer);
				answerHolder.answerDownVote = (ImageView) convertView.findViewById(R.id.answer_down_vote);
				answerHolder.answerUpVote = (ImageView) convertView.findViewById(R.id.answer_up_vote);
				answerHolder.votes = (TextView) convertView.findViewById(R.id.answer_question_details_votes);
				answerHolder.username = (TextView) convertView.findViewById(R.id.answer_user_name);
			//	answerHolder.addCommentOnAnswer = (Button) convertView.findViewById(R.id.add_comment_on_answer);
				convertView.setTag(answerHolder);
			}
			else
			{
				answerHolder = (AnswerHolder) convertView.getTag();
			}
			Log.e(TAG, "getGroupView: " + answerArrayList.get(groupPosition).user + " " + answerHolder.username.getHint());
			answerHolder.username.setText(answerArrayList.get(groupPosition).user);
			final String[] votes = {Integer.toString(answerArrayList.get(groupPosition).votes)};
			answerHolder.votes.setText(votes[0]);
			answerHolder.answer.setText(answerArrayList.get(groupPosition).answer);


			answerHolder.answerUpVote.setOnClickListener(new View.OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					if (!flag)
					{
						flag = true;
						answerArrayList.get(groupPosition).votes++;
						String temp =  Integer.toString(answerArrayList.get(groupPosition).votes);
						answerHolder.votes.setText(temp);
					}
					else
					{
						Toast.makeText(getApplicationContext(), "You already voted", Toast.LENGTH_SHORT).show();
					}
				}
			});

			answerHolder.answerDownVote.setOnClickListener(new View.OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					if (!flag)
					{
						flag = true;
						answerArrayList.get(groupPosition).votes--;
						String temp =  Integer.toString(answerArrayList.get(groupPosition).votes);
						answerHolder.votes.setText(temp);
					}
					else
					{
						Toast.makeText(getApplicationContext(), "You already voted", Toast.LENGTH_SHORT).show();
					}
				}
			});
			return convertView;

		}
		@Override
		public View getChildView(final int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent)
		{
			LayoutInflater li = getLayoutInflater();
			CommentView commentView ;
			Log.e(TAG, "getChildView: " );
			if (convertView == null)
			{
				convertView = li.inflate(R.layout.custom_comment_layout, null);
				commentView = new CommentView();
				commentView.comment = (TextView) convertView.findViewById(R.id.custom_comment);
				commentView.username = (TextView) convertView.findViewById(R.id.custom_comment_username);
				convertView.setTag(commentView);
			}
			else
			{
				commentView = (CommentView) convertView.getTag();
			}
			Log.e(TAG, "getChildView: " + answerArrayList.get(groupPosition).comments.get(childPosition).user );
			commentView.username.setText( answerArrayList.get(groupPosition).comments.get(childPosition).user);
			commentView.comment.setText(answerArrayList.get(groupPosition).comments.get(childPosition).comment);

			commentView.username.setOnClickListener(new View.OnClickListener()
			{

				@Override
				public void onClick(View v)
				{


					Intent intent = new Intent(getApplicationContext(),AddCommentOnAnswerActivity.class);
					intent.putExtra("question_id",id);
					intent.putExtra("answer_id",groupPosition+2);
					Log.e(TAG, "onClick: " + (groupPosition+2));
					startActivity(intent);
					finish();
				}
			});
			commentView.comment.setOnClickListener(new View.OnClickListener()
			{
				@Override
				public void onClick(View v)
				{

					Intent intent = new Intent(getApplicationContext(),AddCommentOnAnswerActivity.class);
					intent.putExtra("question_id",id);
					intent.putExtra("answer_id",groupPosition+1);
					Log.e(TAG, "onClick: " + answerArrayList.get(groupPosition).id2);
					startActivity(intent);
					finish();
				}
			});
			return convertView;
		}
		@Override
		public boolean isChildSelectable(int groupPosition, int childPosition)
		{
			return false;
		}
	}
}
