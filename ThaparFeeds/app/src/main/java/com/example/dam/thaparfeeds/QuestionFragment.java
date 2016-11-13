package com.example.dam.thaparfeeds;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionFragment extends Fragment
{
	ListView questionsListView;
	ArrayList<Questions.Question> arrayList;
	public QuestionFragment()
	{
		// Required empty public constructor
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState)
	{
		// Inflate the layout for this fragment
		View rootView = inflater.inflate(R.layout.fragment_question, container, false);
		questionsListView = (ListView) rootView.findViewById(R.id.questions_list_view);
		Questions.setContext(getContext());
		QuestionListViewAdapter questionListViewAdapter = new QuestionListViewAdapter();
		arrayList = Questions.getQuestions(questionListViewAdapter);
		questionsListView.setAdapter(questionListViewAdapter);
		return rootView;
	}
	public class QuestionListViewAdapter extends BaseAdapter
	{
		class QuestionHolder
		{
			TextView question, numberOfAnswers, votes, description;
		}
		@Override
		public int getCount()
		{
			return arrayList.size();
		}
		@Override
		public Questions.Question getItem(int position)
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
			LayoutInflater layoutInflater = getActivity().getLayoutInflater();
			QuestionHolder questionHolder;
			if (convertView == null)
			{
				convertView = layoutInflater.inflate(R.layout.custom_question_details, null);
				questionHolder = new QuestionHolder();
				questionHolder.numberOfAnswers = (TextView) convertView.findViewById(R.id.question_list_view_number_of_answers);
				questionHolder.description = (TextView) convertView.findViewById(R.id.question_list_view_description);
				questionHolder.question = (TextView) convertView.findViewById(R.id.question_list_view_question);
				questionHolder.votes = (TextView) convertView.findViewById(R.id.question_list_view_votes);
				convertView.setTag(questionHolder);
			}
			else
			{
				questionHolder = (QuestionHolder) convertView.getTag();
			}
			questionHolder.numberOfAnswers.setText(Integer.toString(arrayList.get(position).numberOfAnswers));
			questionHolder.description.setText(arrayList.get(position).description);
			questionHolder.question.setText(arrayList.get(position).question);
			questionHolder.votes.setText(Integer.toString(arrayList.get(position).votes));
			return convertView;
		}
	}
}
