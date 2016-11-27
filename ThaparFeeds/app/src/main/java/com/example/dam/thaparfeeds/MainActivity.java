package com.example.dam.thaparfeeds;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity
{
	public static String user_id;
	private static final String TAG = MainActivity.class.getSimpleName();
	private SectionsPagerAdapter mSectionsPagerAdapter;
	private ViewPager mViewPager;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		final Intent intent = getIntent();
		user_id = intent.getStringExtra("USER_ID");
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
		mViewPager = (ViewPager) findViewById(R.id.container);
		mViewPager.setAdapter(mSectionsPagerAdapter);
		mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener()
		{
			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
			{
			}
			@Override
			public void onPageSelected(int position)
			{
				String first ="QA's";
				String second = "Society";
				if (position == 0)
				{
					getSupportActionBar().setTitle(first);
				}
				else
				{
					getSupportActionBar().setTitle(second);
				}
			}
			@Override
			public void onPageScrollStateChanged(int state)
			{
			}
		});
		FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
		fab.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View view)
			{
				Intent intent1 = new Intent(getApplicationContext(),AddQuestionActivity.class);
				startActivity(intent1);
//				finish();
			}
		});
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		int id = item.getItemId();
		if (id == R.id.action_settings)
		{
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	public class SectionsPagerAdapter extends FragmentPagerAdapter
	{
		public SectionsPagerAdapter(FragmentManager fm)
		{
			super(fm);
		}
		@Override
		public Fragment getItem(int position)
		{
			Log.e(TAG, "getItem: " +  getPageTitle(position) + " " + position);
			if(position == 0)
			{
				Log.e(TAG, "getItem: created QA" );
				return new QuestionFragment();
			}
			return new SocietiesFragment();
		}
		@Override
		public int getCount()
		{
			return 2;
		}
		@Override
		public CharSequence getPageTitle(int position)
		{

			switch (position)
			{
				case 0:
					return "QA's";
				case 1:
					return "Societies";
			}
			return null;
		}
	}
}