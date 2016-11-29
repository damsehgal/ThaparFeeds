package com.example.dam.thaparfeeds;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

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

public class EventListActivity extends AppCompatActivity
{
	int societyId;
	ListView eventsListView;
	ArrayList<Events.Event> arrayList;
	private static final String TAG = EventListActivity.class.getSimpleName();
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_event_list);
		societyId = getIntent().getIntExtra(SocietiesFragment.INTENT_PASS,0);
		eventsListView = (ListView) findViewById(R.id.event_list_adapter);
		Events.setContext(this);
		Events.setSocietyId(societyId);
		EventsListViewAdapter eventsListViewAdapter = new EventsListViewAdapter();

		arrayList = Events.getEvents(eventsListViewAdapter);

		eventsListView.setAdapter(eventsListViewAdapter);
		eventsListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id)
			{
				Intent intent = new Intent(getApplicationContext(), EventDetailActivity.class);
				intent.putExtra("__date",arrayList.get(position).date);
				intent.putExtra("__link",arrayList.get(position).link);
				intent.putExtra("__name",arrayList.get(position).name);
				intent.putExtra("__description",arrayList.get(position).description);
				startActivity(intent);
			}
		});
	}
	public class EventsListViewAdapter extends BaseAdapter
	{


		class EventListViewHolder
		{
			TextView name , description , date;
		}
		@Override
		public int getCount()
		{
			return arrayList.size();
		}
		@Override
		public Events.Event getItem(int position)
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
			LayoutInflater layoutInflater = getLayoutInflater();
			EventListViewHolder eventListViewHolder;
			if (convertView == null)
			{
				convertView = layoutInflater.inflate(R.layout.custom_event_details, null);
				eventListViewHolder = new EventListViewHolder();
				eventListViewHolder.name = (TextView) convertView.findViewById(R.id.event_name);
				eventListViewHolder.date = (TextView) convertView.findViewById(R.id.event_date);
				eventListViewHolder.description = (TextView) convertView.findViewById(R.id.event_description);
				convertView.setTag(eventListViewHolder);
			}
			else
			{
				eventListViewHolder = (EventListViewHolder) convertView.getTag();
			}
			//societyHolder.societyPic.setImageBitmap(arrayList.get(position).imageOfSociety);
			Log.e(TAG, "getView: " + arrayList.get(position).date );
			eventListViewHolder.date.setText(arrayList.get(position).date);
			eventListViewHolder.name.setText(arrayList.get(position).name);
			eventListViewHolder.description.setText(arrayList.get(position).description);
			return convertView;
		}
	}


}
