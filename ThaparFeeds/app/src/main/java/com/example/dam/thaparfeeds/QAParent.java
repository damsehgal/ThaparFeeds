package com.example.dam.thaparfeeds;

import java.util.Date;

/**
 * Created by dam on 29/8/16.
 */
public class QAParent
{
	String sender;
	int upVotes;
	int downVotes;
	Date date;
	String details;
	public QAParent(String sender, int upVotes, int downVotes, Date date, String details)
	{
		this.sender = sender;
		this.upVotes = upVotes;
		this.downVotes = downVotes;
		this.date = date;
		this.details = details;
	}
}
