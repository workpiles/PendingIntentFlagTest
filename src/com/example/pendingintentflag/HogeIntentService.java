package com.example.pendingintentflag;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class HogeIntentService extends IntentService {

	public HogeIntentService() {
		super("HogeIntentService");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		Log.d("Hoge", intent.getAction() + " : extra=" + intent.getExtras().getInt("extra") + " " + intent.hashCode());
	}

}
