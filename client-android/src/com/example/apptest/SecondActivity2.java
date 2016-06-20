package com.example.apptest;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class SecondActivity2 extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);  //상단에 타이틀바 없애기.
        setContentView(R.layout.second_activity2);
	}
}