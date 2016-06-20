package com.example.apptest;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.TabHost;

public class SecondMain extends TabActivity {
    
	//프로그램을 시작하면 처음으로 실행되어 지는 액티비티입니다.
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        //타이틀바를 없애자.
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.second_main);
        
        //탭 메뉴
        TabHost tabHost = getTabHost();
        
        tabHost.addTab(tabHost.newTabSpec("Tab01")
        		.setIndicator("Connect", getResources().getDrawable(R.drawable.join_an))
        		.setContent(new Intent(this, SecondActivity.class)));
        
        tabHost.addTab(tabHost.newTabSpec("Tab02")
        		.setIndicator("Info.", getResources().getDrawable(R.drawable.set_an))
        		.setContent(new Intent(this, SecondActivity2.class)));
    }
}
