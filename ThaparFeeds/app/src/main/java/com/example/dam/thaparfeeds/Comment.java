package com.example.dam.thaparfeeds;

import java.util.Date;

/**
 * Created by dam on 29/8/16.
 */
public class Comment extends QAParent
{
	Questions.Question question;
	Answer answer;
	boolean isQuestionComment;
	public Comment(String sender, int upVotes, int downVotes, Date date, String details, Answer answer)
	{
		super(sender, upVotes, downVotes, date, details);
		this.answer = answer;
		isQuestionComment = false;
	}
	public Comment(String sender, int upVotes, int downVotes, Date date, String details, Questions.Question question)
	{
		super(sender, upVotes, downVotes, date, details);
		this.question = question;
		isQuestionComment = true;
	}
}
