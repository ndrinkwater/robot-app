package com.stem.server;

public class ServerSocketTest {
	public static void main(String[] args) throws InterruptedException {
		SocketThreadListen a = new SocketThreadListen(30001);
		a.init();
		Thread t = new Thread(a);
		t.start();
		for(int i = 0; i < 1000; i++) {
			System.out.println(": " + i);
			Thread.sleep(1000);
		}
	}
}
