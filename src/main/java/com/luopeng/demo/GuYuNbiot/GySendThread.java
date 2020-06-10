package com.luopeng.demo.GuYuNbiot;

import java.io.File;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

//import edu.smxy.db.CMDUs;
//import edu.smxy.user.UserDao;

public class GySendThread extends Thread {
	// UDPIp
	private String ServerIp = "115.29.240.46";
	private int port = 6000;
	private DatagramSocket socket;

	public GySendThread(DatagramSocket socket) {
		this.socket = socket;
	}

	public void setSocket(DatagramSocket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		sendExecute(socket, ServerIp, port);
	}

	public static void sendExecute(DatagramSocket socket, String ServerIp,
			int port) {
		try {
			while (true) {
				String str = "ep=6375EATNESNM6DZY&pw=123456";
				new Gysendstr(str).start();
				sleep(1000*10);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
