package com.example.dam.thaparfeeds;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

/**
 * Created by dam on 25/11/16.
 */
public class TaskDone2
{
	String url;
	Context context;
	public TaskDone2(String url, Context context)
	{
		this.url = url;
		this.context = context;
	}
	public void getString(final VolleyCallBack volleyCallBack){
		RequestQueue q = Volley.newRequestQueue(context);
		q.add( new StringRequest(Request.Method.GET, url, new Response.Listener<String>()
		{
			@Override
			public void onResponse(String response)
			{
				volleyCallBack.onSuccess(response);
			}
		}, new Response.ErrorListener()
		{
			@Override
			public void onErrorResponse(VolleyError error)
			{
				Toast.makeText(context, "check Internet connection", Toast.LENGTH_SHORT).show();
			}
		}));
	}
	public interface  VolleyCallBack{
		void onSuccess(String result);
	}

}
