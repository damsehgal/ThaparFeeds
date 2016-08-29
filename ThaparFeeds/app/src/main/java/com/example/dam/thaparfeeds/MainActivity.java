package com.example.dam.thaparfeeds;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity
{
	private SectionsPagerAdapter mSectionsPagerAdapter;
	private ViewPager mViewPager;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
		mViewPager = (ViewPager) findViewById(R.id.container);
		mViewPager.setAdapter(mSectionsPagerAdapter);
		FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
		fab.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View view)
			{
				Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
					.setAction("Action", null).show();
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
