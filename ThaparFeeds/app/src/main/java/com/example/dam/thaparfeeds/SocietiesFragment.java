package com.example.dam.thaparfeeds;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class SocietiesFragment extends Fragment
{
	private static final String TAG = SocietiesFragment.class.getSimpleName();
	public static final String INTENT_PASS = "intent to edit";
	ListView societyListView;
	ArrayList<Societies.Society> arrayList;
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
		Societies.setContext(getContext());
		SocietyListViewAdapter societyListViewAdapter = new SocietyListViewAdapter();
		arrayList = Societies.getSocieties(societyListViewAdapter);
		societyListView.setAdapter(societyListViewAdapter);
		societyListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id)
			{
				Intent intent = new Intent(getContext(),EventListActivity.class);
				intent.putExtra(INTENT_PASS,arrayList.get(position).societyId);
				startActivity(intent);
			}
		});
		return rootView;
	}
	public class SocietyListViewAdapter extends BaseAdapter
	{
		class SocietyHolder
		{
			TextView textViewSocietyName;
			ImageView societyPic;
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
				societyHolder.societyPic = (ImageView) convertView.findViewById(R.id.society_image);
				convertView.setTag(societyHolder);
			}
			else
			{
				societyHolder = (SocietyHolder) convertView.getTag();
			}
			//societyHolder.societyPic.setImageBitmap(arrayList.get(position).imageOfSociety);
			societyHolder.textViewSocietyName.setText(arrayList.get(position).nameOfSociety);
			return convertView;
		}
	}
}
