package com.example.apptest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SecondActivity extends Activity implements OnClickListener {
	public static String serverIp = null;
	private EditText ipInput;
	private Button secondConnection;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second_activity);
		ipInput = (EditText)findViewById(R.id.IpAddress);
		secondConnection = (Button)findViewById(R.id.secondConnect);
		secondConnection.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
    	AlertDialog.Builder set = new AlertDialog.Builder(this);
    	set.setMessage("접속 하시겠습니까?\n"+"wifi연결이 안되있을시\n"+"많은 데이터가 소모됩니다.").setTitle("  접속").setCancelable(
    			false).setPositiveButton("확인",
    			new DialogInterface.OnClickListener(){ 
    			
    			@Override
    			public void onClick(DialogInterface dialog, int which) {
    				//Action for "Yes" Button
    				serverIp = ipInput.getText().toString();
    				boolean value = false;
    				SocketCheck check = new SocketCheck(serverIp); 
    				value = check.SockCheck();
    				if(TextUtils.isEmpty(ipInput.getText().toString())){	
    					onMessage();
    					dialog.cancel();
    				}
    				else if(value == false)
    				{
    					onMessage2();
    					dialog.cancel();
    				}
    				else{	
    					Intent intent = new Intent(SecondActivity.this,
    							Connect.class);
    			    	startActivity(intent);
    				}
				}
			}
		).setNegativeButton("취소", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
			//Action for "No" Button
				onMessage1();
				dialog.cancel();
			}
		});
    	AlertDialog alert = set.create();
    	alert.getWindow().setFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND, 
    	WindowManager.LayoutParams.FLAG_DIM_BEHIND);	//배경이 뿌여지게.
    	alert.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));	//투명하게 만들기.	
    	alert.show();	//다이얼로그를 보이게함
    }
    
    public void onMessage() {
    	Toast toast = Toast.makeText(this,"IP주소를 입력하지 않았습니다.",Toast.LENGTH_SHORT);
    	toast.setGravity(Gravity.CENTER, 0, 0);
    	toast.show();
    }
    
    public void onMessage1() {
    	Toast toast1= Toast.makeText(this,"연결을 취소합니다.",Toast.LENGTH_SHORT);
    	toast1.setGravity(Gravity.CENTER, 0, 0);
    	toast1.show();
    }
    
    public void onMessage2() {
    	Toast toast1= Toast.makeText(this,"서버를 찾을수 없습니다.",Toast.LENGTH_SHORT);
    	toast1.setGravity(Gravity.CENTER, 0, 0);
    	toast1.show();
    }
}