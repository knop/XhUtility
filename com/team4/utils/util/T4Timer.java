package com.team4.utils.util;

import java.util.Timer;
import java.util.TimerTask;

import android.os.Handler;
import android.os.Message;

public class T4Timer {
	
	private boolean mIsPaused;
	private Timer mTimer;
	private TimerTask mTask;
	private long mDelay;
	private long mPeriod;
	private TimerHandler mHandler;
	
	public T4Timer() {
		mHandler = new TimerHandler(this);
		mIsPaused = false;
	}
	
	public void schedule(TimerTask task, long delay, long period) {
		mTask = task;
		mDelay = delay;
		mPeriod = period;
		
		restart();
	}
	
	private void restart() {
		if (mTimer == null) {
			mTimer = new Timer();
			mTimer.schedule(new TimerTask() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					Message msg = new Message();
					mHandler.sendMessage(msg);
				}
			}, mDelay, mPeriod);
		}
	}
	
	public boolean isPaused() {
		return mIsPaused;
	}
	
	public void resume() {
		if (mIsPaused) {
			mIsPaused = false;
			restart();
		}
	}
	
	public void pause() {
		if (!mIsPaused) {
			mIsPaused = true;
			stop();
		}
	}
	
	public void stop() {
		if (mTimer != null) {
			mTimer.cancel();
			mTimer = null;
		}
	}
	
	public TimerTask getTask() {
		return mTask;
	}
	
	private static class TimerHandler extends Handler {
		
		T4Timer mTimer;
		
		public TimerHandler(T4Timer timer) {
			mTimer = timer;
		}
		
		@Override
		public void handleMessage(Message msg) {
			TimerTask task = mTimer.getTask();
			if (task != null) {
				task.run();
			}
		}
	}
}
