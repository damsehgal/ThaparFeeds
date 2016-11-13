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
 * Created by dam on 9/11/16.
 */
public class Events
{
	private static final String TAG = Events.class.getSimpleName();

	static Context context;
	static int societyId;
	static String societyName;
	public static void setSocietyId(int societyId)
	{
		Events.societyId = societyId;
	}
	public static void setContext(Context context)
	{
		Events.context = context;
	}
	public static class Event
	{
		String name;
		String link;
		String date;
		String description;
		int eventId;
		Context context ;

		public Event(JSONObject jsonObject, Context context)
		{
			try
			{
				this.date = jsonObject.getString("date");
				this.description = jsonObject.getString("description");
				this.eventId = jsonObject.getInt("id");
				this.context = context;
				this.name = jsonObject.getString("name");
				this.link = jsonObject.getString("link");
			}
			catch (JSONException e)
			{
				e.printStackTrace();
			}
		}
	}

	public static ArrayList<Event> getEvents (final EventListActivity.EventsListViewAdapter s)
	{
		Log.e(TAG, "fill: " + "called" );
		final ArrayList<Event> arrayList = new ArrayList<>();
		RequestQueue q = Volley.newRequestQueue(context);


		String url = "https://thaparfeeds.herokuapp.com/societies/" + societyId;
		StringRequest stringRequest= new StringRequest(Request.Method.GET, url, new Response.Listener<String>()
		{
			@Override
			public void onResponse(String response)
			{
				try
				{
					JSONObject jsonObject = new JSONObject(response);
					societyName = jsonObject.getString("name");
					JSONArray events = jsonObject.getJSONArray("events");
					for (int i = 0 ;i <  events.length();i++)
					{
						arrayList.add(new Event(events.getJSONObject(i),context));
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
				Log.e(TAG, "onErrorResponse: " + error.toString() );
			}
		});
		q.add(stringRequest);
		return  arrayList;

	}
}
