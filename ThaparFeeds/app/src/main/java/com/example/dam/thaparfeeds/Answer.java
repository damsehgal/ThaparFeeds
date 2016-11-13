package com.example.dam.thaparfeeds;

import java.util.Date;

/**
 * Created by dam on 29/8/16.
 */
public class Answer extends QAParent
{
	Questions.Question mainQuestion;
	public Answer(String sender, int upVotes, int downVotes, Date date, String details, Questions.Question mainQuestion)
	{
		super(sender, upVotes, downVotes, date, details);
		this.mainQuestion = mainQuestion;
	}
}
