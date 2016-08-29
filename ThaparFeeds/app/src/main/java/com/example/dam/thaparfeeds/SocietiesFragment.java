package com.example.dam.thaparfeeds;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class SocietiesFragment extends Fragment
{
	private static final String TAG = SocietiesFragment.class.getSimpleName();
	ListView societyListView;
	ArrayList<SocietyEvents.SocietyEvent> arrayList;
	public SocietiesFragment()
	{
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState)
	{
		View rootView = inflater.inflate(R.layout.fragment_societies, container, false);
		Log.e(TAG, "onCreateView: called");
		societyListView = (ListView) rootView.findViewById(R.id.society_list_view);
		arrayList = SocietyEvents.getSocietyEvents();
		societyListView.setAdapter(new SocietyListViewAdapter());
		return rootView;
	}
	private class SocietyListViewAdapter extends BaseAdapter
	{
		class SocietyHolder
		{
			TextView textViewSocietyName, textViewEventName;
		}
		@Override
		public int getCount()
		{
			return arrayList.size();
		}
		@Override
		public Object getItem(int position)
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
			SocietyHolder societyHolder;
			if (convertView == null)
			{
				convertView = layoutInflater.inflate(R.layout.layout_socitety_custom, null);
				societyHolder = new SocietyHolder();
				societyHolder.textViewSocietyName = (TextView) convertView.findViewById(R.id.society_name);
				societyHolder.textViewEventName = (TextView) convertView.findViewById(R.id.society_event);
				convertView.setTag(societyHolder);
			}
			else
			{
				societyHolder = (SocietyHolder) convertView.getTag();
			}
			societyHolder.textViewEventName.setText(arrayList.get(position).nameOfEvent);
			societyHolder.textViewSocietyName.setText(arrayList.get(position).nameOfSociety);
			return convertView;
		}
	}
}
