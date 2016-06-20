package com.example.apptest;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Environment;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.WindowManager;
import android.widget.TextView;

public class ConnectView extends SurfaceView implements Callback {
	
	ImageThread mThread;		//쓰레드보존
	SurfaceHolder mHolder;		//SurfaceHolder 보존.
	Context mContext;			//Context 보존.
	String ipAddress = null;	
	
	boolean isLoop;			//Run().
	boolean isWait = false;	//Thread제어.
	public static File root;
	public static File file;
	public static String path;
	private InetAddress serverAddr;
	private InputStream in;
	private BufferedInputStream bis;
	private BufferedOutputStream bos;
	private final int PORT = 60010;
	private int BUFFER_SIZE = 262144;
	private byte[] buf = new byte[BUFFER_SIZE];
	private String receiveFile = "test.bmp";
	TextView receivedState;
	//생성자함수
	public ConnectView(Context context, AttributeSet attrs) {
		super(context, attrs);
		SurfaceHolder holder = getHolder();
		holder.addCallback(this);
		mHolder = holder;
		mThread = new ImageThread(holder, context); 
		setFocusable(true);	// View가 포커스 받을수 있도록 설정.
	}
	
	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
	}

	@Override
	public void surfaceCreated(SurfaceHolder arg0) {
		//쓰레드 시작
		try {
			mThread.start();
		}
		catch(Exception e) {
			RestartGame();
		}
	}
	
	//SurfaceView 가 해제 될때가지.
	@Override
	public void surfaceDestroyed(SurfaceHolder arg0) {
		StopGame();
	}
	
	//액티비티가 완전히 종료될때 사용
	public void destoryView() {
		boolean retry = true;
		mThread.SetLoop(false);
		
	    while (retry) {
	    	try {
	    		mThread.join(); //쓰레드가 현재 step을 끝낼 때까지 대기 
	    		retry = false;
	 		} 
	    	catch(InterruptedException e) {    
	 			e.getStackTrace();
	 		} 
	    }// while
	}
	//Activity 생명주기..
	//Thread 완전 정지
	public void StopGame(){		mThread.StopThread();	}
	
	//Thread 일시정지
	public void PauseGame(){	mThread.PauseNResume(true);		}
	
	//Thread 재가동
	public void ResumeGame(){	mThread.PauseNResume(false);	}
	
	//Thread 초기화
	public void RestartGame()
	{
		mThread.StopThread();		//Thread 중지.
		
		//현재의 쓰레드를 비우고 다시 생성
		mThread = null;
		mThread = new ImageThread(mHolder, mContext);
		mThread.start();
	}
	
//////////////////////////////////////////////////////////////////////////////////	
	
	// Thread Class
	class ImageThread extends Thread {
		
		SurfaceHolder mHolder;		// SurfaceHolder 보존
		int width, height;
		Bitmap mouseBackground;
		Bitmap mouseimg;		//마우스.
		Bitmap keyboard;		//키보드.
		Bitmap bm;				//전송되는 이미지	
		Bitmap resizebm;		//canvas에 그리기 위해 bm사이즈 조정한 이미지
		//쓰레드를 종료하기 위해
		boolean isLoop;
		public void SetLoop(boolean _bLoop){  isLoop = _bLoop; }
		
		//쓰레드 생성자
		public ImageThread(SurfaceHolder holder, Context context) {
			mHolder = holder;				// SurfaceHolder 보존
			mContext = context;
			
			Display display = ((WindowManager)context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
			
			width = display.getWidth();		// View의 가로
			height = display.getHeight();   // View의 세로
			Log.d("TCP", "device width :"+width);
			Log.d("TCP", "device height :"+height);
			isLoop = true; 					
		}
	
		//***쓰레드 조작***
		
		//완전 정지
		public void StopThread() {
			isLoop = false;
			synchronized(this){
				this.notify();
			}
		}
		
		//일시정지, 재가동
		public void PauseNResume(boolean wait) {
			isWait = wait;
			synchronized(this){
				this.notify();
			}
		}
		
	//이미지 받아서 canvas에 그리자
	public void run() {
		//쓰레드 쉬어주자..
		try {
			Thread.sleep(50);
		} 
		catch(InterruptedException e) {	
			StopThread();
			e.printStackTrace();
		}

    	try {	
	    	Canvas canvas = null; //canvas(그림판..)					
	    	while(isLoop && !Thread.currentThread().isInterrupted()) { //무한루프		
					try {
						Thread.sleep(50);		//쉬자
					}
					catch(InterruptedException e){
						e.printStackTrace();
					}
					canvas = mHolder.lockCanvas();		// canvas잠그고 이미지 받아오자		
					
					serverAddr = InetAddress.getByName(SecondActivity.serverIp);
					Log.d("TCP", "PCtoPhoneFileReceive : Connecting...");
					Socket socket = new Socket(serverAddr, PORT);
					
					try { // ScreenImage Save to Sdcard.
						pathAnalysis();
						
						if (file.canWrite()) {
							in = socket.getInputStream();
							bis = new BufferedInputStream(in);
							if (receiveFile.contains("폴더/")) {
								receiveFile = receiveFile.replace("폴더/", "");
							}
							bos = new BufferedOutputStream(new FileOutputStream(
									new File(file, receiveFile)));
							Log.d("TCP", "PCtoPhoneFileReceive : 이미지 수신 시작");
							
							int readCnt = 0;
							while ((readCnt = bis.read(buf)) > 0) {
								
								bos.write(buf, 0, readCnt);
								bos.flush();

							}
							Log.d("TCP", "PCtoPhoneFileReceive : 이미지 수신 완료");
						}
						
					} catch (Exception e) { // socket 에러
						Log.e("TCP", "PCtoPhoneFileReceive : 이미지 수신 에러", e);
					} finally {
						socket.close();
					}
					
					
					Resources res = getResources();
					path = path.concat("/test.bmp/");
					BitmapFactory.Options options = new BitmapFactory.Options();
					options.inPreferredConfig = Bitmap.Config.ARGB_8888;
					options.inSampleSize = 1;
					options.inJustDecodeBounds = false;
					options.inDither = true;
					
					bm = BitmapFactory.decodeFile(path, options);
					resizebm = Bitmap.createScaledBitmap(bm, 1224, 684, true);
					Log.d("TCP", "screen image width :"+resizebm.getWidth());
					Log.d("TCP", "screen image height :"+resizebm.getHeight());
					//resizebm의 width. height 참조해야함!!
					
					mouseBackground = BitmapFactory.decodeResource(res, R.drawable.mousebackground);
					mouseimg = BitmapFactory.decodeResource(res, R.drawable.mouse);
					keyboard = BitmapFactory.decodeResource(res, R.drawable.before);
					
					try {
						synchronized (mHolder) {		// 동기화 유지
							canvas.drawBitmap(resizebm, 0, 0, null);	//배경
							canvas.drawBitmap(mouseimg, Connect.touchX, Connect.touchY, null);	//마우스
							canvas.drawBitmap(mouseBackground, 1225, 1, null);
							canvas.drawBitmap(keyboard, 1233, 408, null);
						}
					} finally {							// 이미지를 다 canvas에 그렷으면 
						if (canvas != null)				// canvas를 view로 전송해야지
							mHolder.unlockCanvasAndPost(canvas);
							bm.recycle();
							resizebm.recycle();
							mouseimg.recycle();
							keyboard.recycle();
							mouseBackground.recycle();
					}
					
				    synchronized(this){
						if(isWait)
							try{
								wait();
							}
							catch(Exception e){}
					}//싱크로나이즈 동기화
			    	if(mThread.isInterrupted() == true){
			    		isLoop = false;
			    	}
				} // while
			}//try
		    catch(UnknownHostException e){
		    	StopThread();
		    	return;
		    }
		    catch(EOFException e){
		    	StopThread();
		    	return;
		    }
	    	catch(IOException e){	
	    		StopThread();
	    		return;
			}
    		return;
	  	} 		// run			
	}			// 쓰레드
	private void pathAnalysis() throws Exception {
		root = Environment.getExternalStorageDirectory();
		path = root.getPath();
		path = path.concat("/ScreenReceive/");
		file = new File(path);
		file.mkdir();
	}
}