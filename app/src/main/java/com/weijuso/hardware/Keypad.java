package com.weijuso.hardware;

public class Keypad {
	static {
		System.loadLibrary ("led");
	}
	public native int ledsetting(int ledname,int onoff);
	public native int readgpio(int buf);
}
