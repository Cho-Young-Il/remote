package com.example.apptest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
        
		Button connection = (Button) findViewById(R.id.Connect);
		Button exit = (Button) findViewById(R.id.exit);
		connection.setOnClickListener(this);
		exit.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v.getId() == R.id.Connect) {
			Intent connectionIntent = new Intent(MainActivity.this,
					SecondMain.class);
			startActivity(connectionIntent);
			
		}
		
		if (v.getId() == R.id.exit) {
			finish();
			System.exit(0);
		}
		
	}

}
