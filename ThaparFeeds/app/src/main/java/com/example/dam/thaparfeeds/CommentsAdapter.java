package com.example.dam.thaparfeeds;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.dam.thaparfeeds.QuestionDetails;
import com.example.dam.thaparfeeds.R;

import java.util.ArrayList;

/**
 * Created by dam on 15/11/16.
 */
public class  CommentsAdapter extends BaseAdapter
{
	ArrayList<QuestionDetails.Comment> arrayList;
	Context context;
	public class  CommentViewHolder
	{
		TextView comment, username;
	}
	public CommentsAdapter(ArrayList<QuestionDetails.Comment> arrayList , Context context)
	{
		this.arrayList = arrayList;
		this.context = context;
	}
	@Override
	public int getCount()
	{
		return arrayList.size();
	}
	@Override
	public QuestionDetails.Comment getItem(int position)
	{
		return arrayList.get(position);
	}
	@Override
	public long getItemId(int position)
	{
		return 0;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		CommentViewHolder commentViewHolder;
		if (convertView == null)
		{
			convertView = layoutInflater.inflate(R.layout.custom_comment_layout, null);
			commentViewHolder= new CommentViewHolder();
			commentViewHolder.comment = (TextView) convertView.findViewById(R.id.custom_comment);
			commentViewHolder.username = (TextView) convertView.findViewById(R.id.custom_comment_username);
			convertView.setTag(commentViewHolder);
		}
		else
		{
			commentViewHolder = (CommentViewHolder) convertView.getTag();
		}
		commentViewHolder.comment.setText(arrayList.get(position).getComment());
		commentViewHolder.username.setText(arrayList.get(position).getUser());

		return convertView;
	}
}
