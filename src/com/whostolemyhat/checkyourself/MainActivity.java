package com.whostolemyhat.checkyourself;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.widget.TextView;


public class MainActivity extends FragmentActivity implements ActionBar.TabListener {
	
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
	
	AlarmReceiver alarm = new AlarmReceiver();
	
	private TextView alarmTime;

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
        
//        alarmTime = (TextView) findViewById(R.id.alarm_time);
//        // set all alarms
//        Button setAlarm = (Button) findViewById(R.id.setAlarm);
//        setAlarm.setOnClickListener(new View.OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				alarm.setAlarm(MainActivity.this);
//			}
//		});
//        
//        
//        
//        Button dialog = (Button) findViewById(R.id.dialog);
//        dialog.setOnClickListener(new View.OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				DateTimeDialogFragment newFrag = new DateTimeDialogFragment(MainActivity.this);
//				newFrag.show(getFragmentManager(), "timepicker");
//				
//				Intent intent = new Intent(MainActivity.this, AlarmService.class);
//				PendingIntent pendingIntent = PendingIntent.getService(MainActivity.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//				AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
//				alarmManager.set(AlarmManager.RTC_WAKEUP, newFrag.getDateTimeMillis(), pendingIntent);
//				
//				Log.d("Check Yourself", newFrag.getDateTime());
//			}
//		});
//
//        
//        Button setNotification = (Button) findViewById(R.id.set_notification);
//        setNotification.setOnClickListener(new View.OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				Intent intent = new Intent(MainActivity.this, AlarmService.class);
//				PendingIntent pendingIntent = PendingIntent.getService(MainActivity.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//				
//				AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
//				Calendar calendar = Calendar.getInstance();
//				calendar.setTimeInMillis(System.currentTimeMillis());
//				// set alarm one hour from now
//				calendar.add(Calendar.HOUR, 1);
////				calendar.add(Calendar.MINUTE, 1);
////				calendar.add(Calendar.SECOND, 10);
//				alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
//				Log.d("Check yourself", calendar.getTime().toString());
//				// trigger notification
//				Toast.makeText(MainActivity.this, "Reminder set", Toast.LENGTH_LONG).show();
//				alarmTime.setText("Next reminder: " + DateFormat.getTimeFormat(getApplicationContext()).format(calendar.getTime()));
//			}
//		});   
        
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
				return new ButtonView();
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
			return "Test";
		}
    }
}
