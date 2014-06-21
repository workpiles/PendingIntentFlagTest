package com.example.pendingintentflag;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class MainActivity extends Activity implements DialogInterface.OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}

		Button test0 = (Button)findViewById(R.id.button1);
		test0.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				final String[] items = {"Test0", "Test1", "Test2", "Test3"};
				new AlertDialog.Builder(MainActivity.this)
					.setItems(items, MainActivity.this).show();
			}
			
		});
	}
	
	@Override
	public void onClick(DialogInterface dialog, int which) {
		switch (which) {
		case 0: runTest0(); break;
		case 1: runTest1(); break;
		case 2: runTest2(); break;
		case 3: runTest3(); break;
		}
	}

	private void runTest0() {
		Log.d("PendingFlagTest", "runTest0");
		setTestIntent(0, PendingIntent.FLAG_CANCEL_CURRENT);
		setTestIntent(1, PendingIntent.FLAG_CANCEL_CURRENT);
		setTestIntent(2, PendingIntent.FLAG_CANCEL_CURRENT);		
	}
	
	private void runTest1() {
		Log.d("PendingFlagTest", "runTest1");
		setTestIntent(3, PendingIntent.FLAG_NO_CREATE);
		setTestIntent(4, PendingIntent.FLAG_NO_CREATE);
		setTestIntent(5, PendingIntent.FLAG_NO_CREATE);		
	}
	
	private void runTest2() {
		Log.d("PendingFlagTest", "runTest2");
		setTestIntent(6, PendingIntent.FLAG_ONE_SHOT);
		setTestIntent(7, PendingIntent.FLAG_ONE_SHOT);
		setTestIntent(8, PendingIntent.FLAG_ONE_SHOT);		
	}
	
	private void runTest3() {
		Log.d("PendingFlagTest", "runTest3");
		setTestIntent(9, PendingIntent.FLAG_UPDATE_CURRENT);
		setTestIntent(10, PendingIntent.FLAG_UPDATE_CURRENT);
		setTestIntent(11, PendingIntent.FLAG_UPDATE_CURRENT);		
	}
	
	private void setTestIntent(int id, int flag) {		
		Intent intent = new Intent(MainActivity.this, HogeIntentService.class);
		intent.setAction("TestIntent");
		intent.putExtra("extra", id);
		PendingIntent sender = PendingIntent.getService(this.getApplicationContext(), 0, intent, flag);
		if (sender == null) {
			Log.d("createIntent", "Not create:" + id);
			return;
		}
		
		Log.d("createIntent", "create TestIntent@" + sender.hashCode() + " : extra=" + id);		
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.SECOND, 10);
		AlarmManager am = (AlarmManager)this.getSystemService(Context.ALARM_SERVICE);
		am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), sender);
	}
		
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			
			return rootView;
		}
	}

}
