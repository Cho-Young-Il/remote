package com.example.apptest;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnDoubleTapListener;
import android.view.GestureDetector.OnGestureListener;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Connect extends Activity implements OnGestureListener,
		OnClickListener, OnDoubleTapListener {
	Thread Keyboard = null;

	// HJLIM
	public static String DEBUG = "CONNECT_ACTIVITIY";

	private boolean _isBtnDown;
	// 드래그할 때 사용합니다.
	public static int touchX = 615; // 초기 마우스 이미지 위치
	public static int touchY = 270;
	public static boolean longKeyboardClick;
	// int cancelX =0, cancelY = 0;
	// int moveX = 0, moveY = 0;
	private int touchAction = -999;
	public static KeyboardHandle keyBoard;
	// boolean flag_Drag = false;
	ConnectView mConnectView;
	private GestureDetector detector = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(DEBUG, "onCreate");
		requestWindowFeature(Window.FEATURE_NO_TITLE);// 액션바 없애자
		setContentView(R.layout.connect);

		Button LClick = (Button) findViewById(R.id.LClick);
		Button RClick = (Button) findViewById(R.id.RClick);
		Button WheelUp = (Button) findViewById(R.id.WheelUp);
		Button WheelDown = (Button) findViewById(R.id.WheelDown);
		LClick.setOnClickListener(this);
		RClick.setOnClickListener(this);
		WheelUp.setOnClickListener(this);
		WheelDown.setOnClickListener(this);
		// 슬립모드 방지 설정
		super.getWindow().addFlags(
				WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		mConnectView = (ConnectView) findViewById(R.id.mImageView);
		detector = new GestureDetector(this, this); // 다양한 터치를 구현자.

		//설정 0:보임 / 8: 안보임
		LinearLayout keyboard_eng = (LinearLayout) findViewById(R.id.keyboard_eng); // 영어 소문자																			
		keyboard_eng.setVisibility(0);
		LinearLayout keyboard_3 = (LinearLayout) findViewById(R.id.b_keyboard_eng); // 영어 대문자											
		keyboard_3.setVisibility(8);
		LinearLayout keyboard_4 = (LinearLayout) findViewById(R.id.keyboard_kor); // 한글 일반
		keyboard_4.setVisibility(8);
		LinearLayout keyboard_5 = (LinearLayout) findViewById(R.id.b_keyboard_kor); // 한글 쌍자음																					
		keyboard_5.setVisibility(8);
		LinearLayout keyboard_6 = (LinearLayout) findViewById(R.id.keyboard_sym_1); // 숫자+기호
		keyboard_6.setVisibility(8);
		LinearLayout keyboard_7 = (LinearLayout) findViewById(R.id.keyboard_sym2_1); // 특수문자
		keyboard_7.setVisibility(8);
		LinearLayout keyboard_8 = (LinearLayout) findViewById(R.id.keyboard_ect_1); // 시스템제어 문자
		keyboard_8.setVisibility(8);

		// 키보드를 길게 터치할 필요가 있는 부분//
		findViewById(R.id.Del).setOnTouchListener(onBtnTouchListener);
		findViewById(R.id.BackSpace).setOnTouchListener(onBtnTouchListener);
		findViewById(R.id.BackSpace2).setOnTouchListener(onBtnTouchListener);
		findViewById(R.id.BackSpace3).setOnTouchListener(onBtnTouchListener);
		findViewById(R.id.BackSpace4).setOnTouchListener(onBtnTouchListener);
		findViewById(R.id.BackSpace5).setOnTouchListener(onBtnTouchListener);
		findViewById(R.id.BackSpace6).setOnTouchListener(onBtnTouchListener);
		findViewById(R.id.BackSpace7).setOnTouchListener(onBtnTouchListener);
		findViewById(R.id.Space).setOnTouchListener(onBtnTouchListener);
		findViewById(R.id.Space2).setOnTouchListener(onBtnTouchListener);
		findViewById(R.id.Space3).setOnTouchListener(onBtnTouchListener);
		findViewById(R.id.Space4).setOnTouchListener(onBtnTouchListener);
		findViewById(R.id.Space5).setOnTouchListener(onBtnTouchListener);
		findViewById(R.id.Space6).setOnTouchListener(onBtnTouchListener);
		findViewById(R.id.Space7).setOnTouchListener(onBtnTouchListener);
		findViewById(R.id.Return).setOnTouchListener(onBtnTouchListener);
		findViewById(R.id.Return2).setOnTouchListener(onBtnTouchListener);
		findViewById(R.id.Return3).setOnTouchListener(onBtnTouchListener);
		findViewById(R.id.Return4).setOnTouchListener(onBtnTouchListener);
		findViewById(R.id.Return5).setOnTouchListener(onBtnTouchListener);
		findViewById(R.id.Return6).setOnTouchListener(onBtnTouchListener);
		findViewById(R.id.Return7).setOnTouchListener(onBtnTouchListener);
		findViewById(R.id.Ctrl).setOnTouchListener(onBtnTouchListener);
		findViewById(R.id.Ctrl2).setOnTouchListener(onBtnTouchListener);
		findViewById(R.id.Ctrl3).setOnTouchListener(onBtnTouchListener);
		findViewById(R.id.Ctrl4).setOnTouchListener(onBtnTouchListener);
		findViewById(R.id.Ctrl5).setOnTouchListener(onBtnTouchListener);
		findViewById(R.id.Ctrl6).setOnTouchListener(onBtnTouchListener);
		findViewById(R.id.Ctrl7).setOnTouchListener(onBtnTouchListener);
		findViewById(R.id.Alt).setOnTouchListener(onBtnTouchListener);
		findViewById(R.id.Alt2).setOnTouchListener(onBtnTouchListener);
		findViewById(R.id.Alt3).setOnTouchListener(onBtnTouchListener);
		findViewById(R.id.Alt4).setOnTouchListener(onBtnTouchListener);
		findViewById(R.id.Alt5).setOnTouchListener(onBtnTouchListener);
		findViewById(R.id.Alt6).setOnTouchListener(onBtnTouchListener);
		findViewById(R.id.Alt7).setOnTouchListener(onBtnTouchListener);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		mConnectView.PauseGame();
 		Log.d(DEBUG, "onPause");
	}
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop(); 
	    mConnectView.PauseGame();
 		Log.d(DEBUG, "onStop");
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		mConnectView.ResumeGame();
 		Log.d(DEBUG, "onResume");
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		mConnectView.destoryView();
 		Log.d(DEBUG, "onDestroy");
	}
	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
 		Log.d(DEBUG, "onStart");
	}
	
	@Override
	protected void onRestart() {
		super.onRestart();
		mConnectView.RestartGame();
	}
	// back키 안눌리게 막아버리자
	@Override
	public void onBackPressed() {
		// super.onBackPressed();
	}

	// 옵션메뉴에 종료, disconnect 만들어보자
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		addOptionMenuItems(menu);
		return true;
	}

	private void addOptionMenuItems(Menu menu) {
		// TODO Auto-generated method stub
		int base = Menu.FIRST;
		MenuItem item01 = menu.add(base, base, Menu.NONE, "");
		item01.setIcon(R.drawable.disconnect_icon); // disconnect아이콘
		int base2 = 2;
		MenuItem item02 = menu.add(base2, base2, Menu.NONE, "");
		item02.setIcon(R.drawable.system_shutdown_icon); // 종료아이콘
	}

	public boolean onPrepareOptionsMenu(Menu menu) {
		return true;
	}

	// 이벤트
	public boolean onOptionsItemSelected(MenuItem item) {

		if (item.getItemId() == 1) {
			AlertDialog.Builder set = new AlertDialog.Builder(this);
			set.setMessage("연결을 종료하겠습니까?")
					.setTitle("  종료")
					.setCancelable(false)
					.setPositiveButton("확인",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									// 확인선택시이벤트
									System.exit(1);
								}
							})
					.setNegativeButton("취소",
							new DialogInterface.OnClickListener() {
								// 취소할시 이벤트
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									dialog.cancel();
								}
							});
			AlertDialog alert = set.create();

			alert.getWindow().setFlags(
					WindowManager.LayoutParams.FLAG_BLUR_BEHIND,
					WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
			alert.getWindow().setBackgroundDrawable(
					new ColorDrawable(Color.TRANSPARENT));
			alert.requestWindowFeature(Window.FEATURE_NO_TITLE);
			alert.show();
		}// if_1

		if (item.getItemId() == 2) {
			AlertDialog.Builder set = new AlertDialog.Builder(this);
			set.setMessage("PC시스템을 종료하겠습니까?\n" + "종료시 PC와의 연결이 중단됩니다.")
					.setTitle("  종료")
					.setCancelable(false)
					.setPositiveButton("확인",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									// 확인선택시이벤트
									Thread SSS = new Thread(new CommandSend(
											"SystemShutdown"));
									SSS.start();
									try {
										Thread.sleep(7000);
									} catch (InterruptedException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									System.exit(1);
								}
							})
					.setNegativeButton("취소",
							new DialogInterface.OnClickListener() {
								// 취소 선택시 이벤트
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									dialog.cancel();
								}
							});
			AlertDialog alert = set.create();

			alert.getWindow().setFlags(
					WindowManager.LayoutParams.FLAG_BLUR_BEHIND,
					WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
			alert.getWindow().setBackgroundDrawable(
					new ColorDrawable(Color.TRANSPARENT));
			alert.requestWindowFeature(Window.FEATURE_NO_TITLE);
			alert.show();
		}// if
		return false;
	}

	// //////////////////////MOUSE////////////////////// //
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int act = event.getAction();
		touchAction = event.getAction();
		if ((int) event.getX() <= 1239 && (int) event.getY() <= 708) {
			touchX = (int) event.getX() - 40; // x 좌표를 얻어온다.
			touchY = (int) event.getY() - 50; // y 좌표를 얻어온다.
			Log.d("TCP", "x :" + touchX);
			Log.d("TCP", "y :" + touchY);
			Thread rectSend = new Thread(new DeviceRectSend(touchX, touchY,
					1224, 684));
			rectSend.start();
			if ((act & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_UP) {
				Thread DragEnd = new Thread(new CommandSend("DragEnd"));
				DragEnd.start();
			}
		}
		return detector.onTouchEvent(event);
	}

	@Override
	public boolean onDoubleTap(MotionEvent event) {
		// touchAction = event.getAction();
		if ((int) event.getX() <= 1239 && (int) event.getY() <= 708) {
			touchX = (int) event.getX() - 40; // x 좌표를 얻어온다.
			touchY = (int) event.getY() - 50; // y 좌표를 얻어온다.
			Log.d("TCP", "x :" + touchX);
			Log.d("TCP", "y :" + touchY);
			Thread DoubleClick = new Thread(new CommandSend("DoubleClick"));
			DoubleClick.start();
		}
		return detector.onTouchEvent(event);
	}

	@Override
	public boolean onSingleTapConfirmed(MotionEvent e) {
		Thread LClick = new Thread(new CommandSend("LClick"));
		LClick.start();
		return false;
	}

	@Override
	public boolean onDoubleTapEvent(MotionEvent e) {
		return false;
	}

	@Override
	public boolean onDown(MotionEvent e) {
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {
	}

	@Override
	public boolean onSingleTapUp(MotionEvent event) {
		return false;
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		return false;
	}

	@Override
	public void onLongPress(MotionEvent event) {
		// int act = event.getAction();
		if ((int) event.getX() <= 1239 && (int) event.getY() <= 708) {
			touchX = (int) event.getX() - 40; // x 좌표를 얻어온다.
			touchY = (int) event.getY() - 50; // y 좌표를 얻어온다.
			Log.d("TCP", "x :" + touchX);
			Log.d("TCP", "y :" + touchY);
			Thread rectSend = new Thread(new DeviceRectSend(touchX, touchY,
					1224, 684));
			rectSend.start();
			Thread Drag = new Thread(new CommandSend("Drag"));
			Drag.start();
		}
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		return false;
	}

	@Override
	public void onClick(View v) {
		// 마우스
		if (v.getId() == R.id.LClick) {
			Thread LClick = new Thread(new CommandSend("LClick"));
			LClick.start();
		}
		if (v.getId() == R.id.RClick) {
			Thread RClick = new Thread(new CommandSend("RClick"));
			RClick.start();
		}
		if (v.getId() == R.id.WheelUp) {
			Thread WheelUp = new Thread(new CommandSend("WheelUp"));
			WheelUp.start();
		}
		if (v.getId() == R.id.WheelDown) {
			Thread WheelDown = new Thread(new CommandSend("WheelDown"));
			WheelDown.start();
		}// 마우스
	} // //////////////////////MOUSE////////////////////// //
		// 키보드

	public void KeyBoardOnClick(View v) {
		keyBoard = new KeyboardHandle();
		boolean key = keyBoard.KeyBtnOnClick(v);
		if (!key) {
			if (v.getId() == R.id.Eng_Shift) {
				LinearLayout keyboard_eng = (LinearLayout) findViewById(R.id.keyboard_eng); // 영문
																							// 소문자
				keyboard_eng.setVisibility(8);
				LinearLayout keyboard_3 = (LinearLayout) findViewById(R.id.b_keyboard_eng); // 영문
																							// 대문자
				keyboard_3.setVisibility(0);
				LinearLayout keyboard_4 = (LinearLayout) findViewById(R.id.keyboard_kor); // 한글
				keyboard_4.setVisibility(8);
				LinearLayout keyboard_5 = (LinearLayout) findViewById(R.id.b_keyboard_kor);// 한글
																							// 쌍자음
				keyboard_5.setVisibility(8);
				LinearLayout keyboard_6 = (LinearLayout) findViewById(R.id.keyboard_sym_1); // 숫자+기호
				keyboard_6.setVisibility(8);
				LinearLayout keyboard_7 = (LinearLayout) findViewById(R.id.keyboard_sym2_1); // 기호
				keyboard_7.setVisibility(8);
				LinearLayout keyboard_8 = (LinearLayout) findViewById(R.id.keyboard_ect_1); // ect
				keyboard_8.setVisibility(8);
				return;
			}
			if (v.getId() == R.id.B_Eng_Shift) {
				LinearLayout keyboard_eng = (LinearLayout) findViewById(R.id.keyboard_eng); // 영문
																							// 소문자
				keyboard_eng.setVisibility(0);
				LinearLayout keyboard_3 = (LinearLayout) findViewById(R.id.b_keyboard_eng); // 영문
																							// 대문자
				keyboard_3.setVisibility(8);
				LinearLayout keyboard_4 = (LinearLayout) findViewById(R.id.keyboard_kor); // 한글
				keyboard_4.setVisibility(8);
				LinearLayout keyboard_5 = (LinearLayout) findViewById(R.id.b_keyboard_kor);// 한글
																							// 쌍자음
				keyboard_5.setVisibility(8);
				LinearLayout keyboard_6 = (LinearLayout) findViewById(R.id.keyboard_sym_1); // 숫자+기호
				keyboard_6.setVisibility(8);
				LinearLayout keyboard_7 = (LinearLayout) findViewById(R.id.keyboard_sym2_1); // 기호
				keyboard_7.setVisibility(8);
				LinearLayout keyboard_8 = (LinearLayout) findViewById(R.id.keyboard_ect_1); // ect
				keyboard_8.setVisibility(8);
				return;
			}
			if (v.getId() == R.id.Kor_Shift) {
				LinearLayout keyboard_eng = (LinearLayout) findViewById(R.id.keyboard_eng); // 영문
																							// 소문자
				keyboard_eng.setVisibility(8);
				LinearLayout keyboard_3 = (LinearLayout) findViewById(R.id.b_keyboard_eng); // 영문
																							// 대문자
				keyboard_3.setVisibility(8);
				LinearLayout keyboard_4 = (LinearLayout) findViewById(R.id.keyboard_kor); // 한글
				keyboard_4.setVisibility(8);
				LinearLayout keyboard_5 = (LinearLayout) findViewById(R.id.b_keyboard_kor);// 한글
																							// 쌍자음
				keyboard_5.setVisibility(0);
				LinearLayout keyboard_6 = (LinearLayout) findViewById(R.id.keyboard_sym_1); // 숫자+기호
				keyboard_6.setVisibility(8);
				LinearLayout keyboard_7 = (LinearLayout) findViewById(R.id.keyboard_sym2_1); // 기호
				keyboard_7.setVisibility(8);
				LinearLayout keyboard_8 = (LinearLayout) findViewById(R.id.keyboard_ect_1); // ect
				keyboard_8.setVisibility(8);
				return;
			}
			if (v.getId() == R.id.B_Kor_Shift) {
				LinearLayout keyboard_eng = (LinearLayout) findViewById(R.id.keyboard_eng); // 영문
																							// 소문자
				keyboard_eng.setVisibility(8);
				LinearLayout keyboard_3 = (LinearLayout) findViewById(R.id.b_keyboard_eng); // 영문
																							// 대문자
				keyboard_3.setVisibility(8);
				LinearLayout keyboard_4 = (LinearLayout) findViewById(R.id.keyboard_kor); // 한글
				keyboard_4.setVisibility(0);
				LinearLayout keyboard_5 = (LinearLayout) findViewById(R.id.b_keyboard_kor);// 한글
																							// 쌍자음
				keyboard_5.setVisibility(8);
				LinearLayout keyboard_6 = (LinearLayout) findViewById(R.id.keyboard_sym_1); // 숫자+기호
				keyboard_6.setVisibility(8);
				LinearLayout keyboard_7 = (LinearLayout) findViewById(R.id.keyboard_sym2_1); // 기호
				keyboard_7.setVisibility(8);
				LinearLayout keyboard_8 = (LinearLayout) findViewById(R.id.keyboard_ect_1); // ect
				keyboard_8.setVisibility(8);
				return;
			}
			if (v.getId() == R.id.Earth || v.getId() == R.id.Earth2) {
				LinearLayout keyboard_eng = (LinearLayout) findViewById(R.id.keyboard_eng); // 영문
																							// 소문자
				keyboard_eng.setVisibility(8);
				LinearLayout keyboard_3 = (LinearLayout) findViewById(R.id.b_keyboard_eng); // 영문
																							// 대문자
				keyboard_3.setVisibility(8);
				LinearLayout keyboard_4 = (LinearLayout) findViewById(R.id.keyboard_kor); // 한글
				keyboard_4.setVisibility(0);
				LinearLayout keyboard_5 = (LinearLayout) findViewById(R.id.b_keyboard_kor);// 한글
																							// 쌍자음
				keyboard_5.setVisibility(8);
				LinearLayout keyboard_6 = (LinearLayout) findViewById(R.id.keyboard_sym_1); // 숫자+기호
				keyboard_6.setVisibility(8);
				LinearLayout keyboard_7 = (LinearLayout) findViewById(R.id.keyboard_sym2_1); // 기호
				keyboard_7.setVisibility(8);
				LinearLayout keyboard_8 = (LinearLayout) findViewById(R.id.keyboard_ect_1); // ect
				keyboard_8.setVisibility(8);
				return;
			}
			if (v.getId() == R.id.Earth3 || v.getId() == R.id.Earth4
					|| v.getId() == R.id.Earth5 || v.getId() == R.id.Earth6
					|| v.getId() == R.id.Earth7) {
				LinearLayout keyboard_eng = (LinearLayout) findViewById(R.id.keyboard_eng); // 영문
																							// 소문자
				keyboard_eng.setVisibility(0);
				LinearLayout keyboard_3 = (LinearLayout) findViewById(R.id.b_keyboard_eng); // 영문
																							// 대문자
				keyboard_3.setVisibility(8);
				LinearLayout keyboard_4 = (LinearLayout) findViewById(R.id.keyboard_kor); // 한글
				keyboard_4.setVisibility(8);
				LinearLayout keyboard_5 = (LinearLayout) findViewById(R.id.b_keyboard_kor);// 한글
																							// 쌍자음
				keyboard_5.setVisibility(8);
				LinearLayout keyboard_6 = (LinearLayout) findViewById(R.id.keyboard_sym_1); // 숫자+기호
				keyboard_6.setVisibility(8);
				LinearLayout keyboard_7 = (LinearLayout) findViewById(R.id.keyboard_sym2_1); // 기호
				keyboard_7.setVisibility(8);
				LinearLayout keyboard_8 = (LinearLayout) findViewById(R.id.keyboard_ect_1); // ect
				keyboard_8.setVisibility(8);
				return;
			}
			if (v.getId() == R.id.Number || v.getId() == R.id.Number2
					|| v.getId() == R.id.Number3 || v.getId() == R.id.Number4
					|| v.getId() == R.id.Number6 || v.getId() == R.id.Number7) {
				LinearLayout keyboard_eng = (LinearLayout) findViewById(R.id.keyboard_eng); // 영문
																							// 소문자
				keyboard_eng.setVisibility(9);
				LinearLayout keyboard_3 = (LinearLayout) findViewById(R.id.b_keyboard_eng); // 영문
																							// 대문자
				keyboard_3.setVisibility(8);
				LinearLayout keyboard_4 = (LinearLayout) findViewById(R.id.keyboard_kor); // 한글
				keyboard_4.setVisibility(8);
				LinearLayout keyboard_5 = (LinearLayout) findViewById(R.id.b_keyboard_kor);// 한글
																							// 쌍자음
				keyboard_5.setVisibility(8);
				LinearLayout keyboard_6 = (LinearLayout) findViewById(R.id.keyboard_sym_1); // 숫자+기호
				keyboard_6.setVisibility(0);
				LinearLayout keyboard_7 = (LinearLayout) findViewById(R.id.keyboard_sym2_1); // 기호
				keyboard_7.setVisibility(8);
				LinearLayout keyboard_8 = (LinearLayout) findViewById(R.id.keyboard_ect_1); // ect
				keyboard_8.setVisibility(8);
				return;
			}
			if (v.getId() == R.id.s_x || v.getId() == R.id.s_x2) {
				LinearLayout keyboard_eng = (LinearLayout) findViewById(R.id.keyboard_eng); // 영문
																							// 소문자
				keyboard_eng.setVisibility(8);
				LinearLayout keyboard_3 = (LinearLayout) findViewById(R.id.b_keyboard_eng); // 영문
																							// 대문자
				keyboard_3.setVisibility(8);
				LinearLayout keyboard_4 = (LinearLayout) findViewById(R.id.keyboard_kor); // 한글
				keyboard_4.setVisibility(8);
				LinearLayout keyboard_5 = (LinearLayout) findViewById(R.id.b_keyboard_kor);// 한글
																							// 쌍자음
				keyboard_5.setVisibility(8);
				LinearLayout keyboard_6 = (LinearLayout) findViewById(R.id.keyboard_sym_1); // 숫자+기호
				keyboard_6.setVisibility(8);
				LinearLayout keyboard_7 = (LinearLayout) findViewById(R.id.keyboard_sym2_1); // 기호
				keyboard_7.setVisibility(0);
				LinearLayout keyboard_8 = (LinearLayout) findViewById(R.id.keyboard_ect_1); // ect
				keyboard_8.setVisibility(8);
				return;
			}
			if (v.getId() == R.id.Ect || v.getId() == R.id.Ect2) {
				LinearLayout keyboard_eng = (LinearLayout) findViewById(R.id.keyboard_eng); // 영문
																							// 소문자
				keyboard_eng.setVisibility(8);
				LinearLayout keyboard_3 = (LinearLayout) findViewById(R.id.b_keyboard_eng); // 영문
																							// 대문자
				keyboard_3.setVisibility(8);
				LinearLayout keyboard_4 = (LinearLayout) findViewById(R.id.keyboard_kor); // 한글
				keyboard_4.setVisibility(8);
				LinearLayout keyboard_5 = (LinearLayout) findViewById(R.id.b_keyboard_kor);// 한글
																							// 쌍자음
				keyboard_5.setVisibility(8);
				LinearLayout keyboard_6 = (LinearLayout) findViewById(R.id.keyboard_sym_1); // 숫자+기호
				keyboard_6.setVisibility(8);
				LinearLayout keyboard_7 = (LinearLayout) findViewById(R.id.keyboard_sym2_1); // 기호
				keyboard_7.setVisibility(8);
				LinearLayout keyboard_8 = (LinearLayout) findViewById(R.id.keyboard_ect_1); // ect
				keyboard_8.setVisibility(0);
				return;
			}
			Toast toast1 = Toast.makeText(this, "Undefined Key Value",
					Toast.LENGTH_SHORT);
			toast1.setGravity(Gravity.CENTER, 0, 0);
			toast1.show();
		}
	}// 키보드

	public void SlidingDrawerOnClick(View v) {
		// SlidingDrawer가 활성화 됬을때 Screen View에 아무런 영향을 주면 안됨 XML과 연관.
		// 아무런 수행하지 않는다.
	}

	// 키보드 롱터치를 위한 메소드 정의
	private void onBtnDown(View v) {
		new TouchThread(v);
	}

	private Handler touchHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			Log.w("HomeActivity.touchHandler | handleMessage()", " : " + msg);
		}
	};

	private class TouchThread extends Thread {
		View v = null;

		TouchThread(View v) {
			this.v = v;
			start();
		}

		@Override
		public void run() {
			super.run();
			while (_isBtnDown) {
				touchHandler.sendEmptyMessage(9876);
				switch (v.getId()) {
				case R.id.Del: KeySend("LongDel"); break;
				case R.id.BackSpace: KeySend("LongBackSpace"); break;
				case R.id.BackSpace2: KeySend("LongBackSpace"); break;
				case R.id.BackSpace3: KeySend("LongBackSpace"); break;
				case R.id.BackSpace4: KeySend("LongBackSpace");	break;
				case R.id.BackSpace5: KeySend("LongBackSpace");	break;
				case R.id.BackSpace6: KeySend("LongBackSpace");	break;
				case R.id.BackSpace7: KeySend("LongBackSpace");	break;
				case R.id.Space: KeySend("LongSpace"); break;
				case R.id.Space2: KeySend("LongSpace");	break;
				case R.id.Space3: KeySend("LongSpace");	break;
				case R.id.Space4: KeySend("LongSpace"); break;
				case R.id.Space5: KeySend("LongSpace"); break;
				case R.id.Space6: KeySend("LongSpace"); break;
				case R.id.Space7: KeySend("LongSpace"); break;
				case R.id.Return: KeySend("LongReturn"); break;
				case R.id.Return2: KeySend("LongReturn"); break;
				case R.id.Return3: KeySend("LongReturn"); break;
				case R.id.Return4: KeySend("LongReturn"); break;
				case R.id.Return5: KeySend("LongReturn"); break;
				case R.id.Return6: KeySend("LongReturn"); break;
				case R.id.Return7: KeySend("LongReturn"); break;
				case R.id.Ctrl: KeySend("LongCtrl"); break;
				case R.id.Ctrl2: KeySend("LongCtrl"); break;
				case R.id.Ctrl3: KeySend("LongCtrl"); break;
				case R.id.Ctrl4: KeySend("LongCtrl"); break;
				case R.id.Ctrl5: KeySend("LongCtrl"); break;
				case R.id.Ctrl6: KeySend("LongCtrl"); break;
				case R.id.Ctrl7: KeySend("LongCtrl"); break;
				case R.id.Alt: KeySend("LongAlt"); break;
				case R.id.Alt2: KeySend("LongAlt"); break;
				case R.id.Alt3: KeySend("LongAlt"); break;
				case R.id.Alt4: KeySend("LongAlt"); break;
				case R.id.Alt5: KeySend("LongAlt"); break;
				case R.id.Alt6: KeySend("LongAlt"); break;
				case R.id.Alt7: KeySend("LongAlt"); break;
				}
				try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private void KeySend(String str) {
		Thread Keyboard = new Thread(new KeyboardSend(str));
		Keyboard.start();
	}

	/********************
	 * longtouchListener
	 ********************/
	private OnTouchListener onBtnTouchListener = new OnTouchListener() {
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			View v1 = v;
			switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN: _isBtnDown = true; onBtnDown(v1); break;
			case MotionEvent.ACTION_UP: _isBtnDown = false; break;
			default: break;
			}
			return false;
		}
	};// 키보드 롱터치

}
