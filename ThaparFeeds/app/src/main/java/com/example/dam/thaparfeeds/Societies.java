package com.example.dam.thaparfeeds;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.SystemClock;
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
import java.util.Date;
import java.util.HashMap;

/**
 * Created by dam on 29/8/16.
 */
public class Societies
{
	private static final String TAG = Societies.class.getSimpleName();
	static Context context ;
	public static void setContext(Context context)
	{
		Societies.context = context;
	}
	public static class Society
	{
		String nameOfSociety;
		int societyId;
		Context context ;
		/*Bitmap imageOfSociety;*/
		public Society(String nameOfSociety, int societyId , Context context)
		{
			this.nameOfSociety = nameOfSociety;
			this.societyId = societyId;
			this.context = context;
			/*this.imageOfSociety = bmp;*/
		}
		public Society(String nameOfSociety, int societyId)
		{
			this.nameOfSociety = nameOfSociety;
			this.societyId = societyId;
		}
	}

	public static ArrayList<Society> getSocieties (final SocietiesFragment.SocietyListViewAdapter s)
	{
		Log.e(TAG, "fill: " + "called" );
		final ArrayList<Society> arrayList = new ArrayList<>();
		RequestQueue q = Volley.newRequestQueue(context);


		String url = "https://thaparfeeds.herokuapp.com/societies";
		StringRequest stringRequest= new StringRequest(Request.Method.GET, url, new Response.Listener<String>()
		{
			@Override
			public void onResponse(String response)
			{
				try
				{
					JSONArray jsonArray = new JSONArray(response);
					Log.e(TAG, "onResponse: " + jsonArray.length() );
					for (int i = 0; i < jsonArray.length();i++)
					{
						arrayList.add(new Society(((JSONObject) jsonArray.getJSONObject(i)).getString("name"),((JSONObject) jsonArray.getJSONObject(i)).getInt("id") , context));
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
