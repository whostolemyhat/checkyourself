package com.whostolemyhat.checkyourself;

import java.util.Locale;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;

import com.whostolemyhat.checkyourself.views.AlarmView;
import com.whostolemyhat.checkyourself.views.ButtonView;
import com.whostolemyhat.checkyourself.views.TimePickerFragment;


public class MainActivity extends FragmentActivity implements ActionBar.TabListener,
TimePickerFragment.OnCompleteListener {
	
	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
	 * will keep every loaded fragment in memory. If this becomes too memory
	 * intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;
	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;
	
//	AlarmReceiver alarm = new AlarmReceiver();
//	
//	private TextView alarmTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        final ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        
        // create adapter
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        
        // handle swipes
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
        	@Override
        	public void onPageSelected(int position) {
        		actionBar.setSelectedNavigationItem(position);
        	}
        });
        
        // add tab for each section
        for(int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
        	actionBar.addTab(actionBar.newTab()
        			.setText(mSectionsPagerAdapter.getPageTitle(i))
        			.setTabListener(this));
        }
         
        
    }


	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


	@Override
	public void onTabReselected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub
	}


	@Override
	public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
		mViewPager.setCurrentItem(tab.getPosition());
		
	}


	@Override
	public void onTabUnselected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub
		
	}

    public class SectionsPagerAdapter extends FragmentPagerAdapter {
    	public SectionsPagerAdapter(FragmentManager fm) {
    		super(fm);
    	}
    	
    	@Override
    	public Fragment getItem(int position) {
			switch(position) {
			case 0:
				// return new button view
				return new ButtonView();
			case 1:
				// edit alarm view
				return new AlarmView();
			}
    		
			return null;
    	}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 2;
		}
		
		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();
			switch(position) {
			case 0:
				return getString(R.string.button_title).toUpperCase(l);
			case 1:
				return getString(R.string.alarm_title).toUpperCase(l);
			}
			return "Test";
		}
    }


    // listen for time picker changes
	@Override
	public void onComplete(int hour, int minute) {
		Log.d("CheckYourself", "Called from alarm view");
		Log.d("CheckYourself", Integer.toString(hour) + " " + Integer.toString(minute));
	}
}
