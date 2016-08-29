package com.example.dam.thaparfeeds;

import android.os.SystemClock;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by dam on 29/8/16.
 */
public class SocietyEvents
{
	public static class SocietyEvent
	{
		String nameOfSociety;
		Date date;
		String nameOfEvent;
		String linkOfEvent;
		public SocietyEvent(String nameOfSociety, Date date, String nameOfEvent , String linkOfEvent)
		{
			this.nameOfSociety = nameOfSociety;
			this.date = date;
			this.nameOfEvent = nameOfEvent;
			this.linkOfEvent = linkOfEvent;
		}
	}
	public static ArrayList<SocietyEvent> getSocietyEvents()
	{
		//TODO -> build a methood to fetch json and give it
		ArrayList<SocietyEvent> arrayList = new ArrayList<>();
		for (int i = 0 ; i < 10 ; i++)
			arrayList.add(new SocietyEvent("Name of Society" + i,new Date(SystemClock.uptimeMillis()),"name of event" + i , "link Of event"));
		return  arrayList;
	}
}
