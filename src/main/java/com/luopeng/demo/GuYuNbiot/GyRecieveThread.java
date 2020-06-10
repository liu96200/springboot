package com.luopeng.demo.GuYuNbiot;

import com.luopeng.demo.controller.NbController;
import com.luopeng.demo.controller.UserController;
import com.luopeng.demo.dao.DataDao;
import com.luopeng.demo.dao.UserDao;
import com.luopeng.demo.entity.Data;
import com.luopeng.demo.service.DataServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;

//import edu.smxy.user.UserDao;
//import edu.smxy.wbsocket.Customersocket;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.URISyntaxException;
import java.sql.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Pattern;

@Component
public class GyRecieveThread extends Thread {
    @Autowired
    DataServiceImpl dataService;


//	public static DatagramSocket socket=null;
	DatagramSocket socket = Gysocket.getsocket();
	private Logger logger = Logger.getLogger(GyRecieveThread.class);
	public static String st = "";
	public static String str = "";
    public static GyRecieveThread gyRecieveThread;
    @PostConstruct//解决非controller类注入为null的问题
    public void  init(){
        gyRecieveThread=this;
        gyRecieveThread.dataService=this.dataService;
    }
	@Override
	public void run() {
		// TODO Auto-generated method stub
		execute(socket);
	}
	public  void execute(DatagramSocket socket) {

		try {
			byte[] bs = new byte[1024 * 64];
			DatagramPacket packet = new DatagramPacket(bs, bs.length);
			Timer timer = new Timer();
			timer.schedule(new TimerTask() {
				public void run() {
					System.out.println("运行run方法");
					if (str.startsWith("M")) {
							String s[] = str.split("#");
							if (isNumber(s[1]) && isNumber(s[2])) {
								String wd="";String sd="";
								wd = s[2];sd = s[1];
								System.out.println("存入数据湿度：" + sd + "温度是：" + wd);
								logger.info("接收谷雨消息并存储 湿度："+sd);
//								gyRecieveThread.dataService.insertData(new Data(wd,sd));
//								UserController userController=new UserController();
//								userController.insert(new Data(wd,sd));
								gyRecieveThread.dataService.insertData(new Data(wd,sd));
							}
					}
					str = "";
				}
			}, 0, 1000 * 3);
			while (true) {
				socket.receive(packet);
				if (null != packet) {
					try {
						System.out.print("接收数据");
						st = new String(bs, 0, packet.getLength());
						if(st.equals("#")){}
						else{
							str=st;
							System.out.println(str);
						}

					} catch (Exception e) {
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public static String regist(){
//		if (str.equals("[iotxx:ok]")||str.equals("[iotxx:update]"))
//			return "设备连接成功";
//		else if(str.equals("[iotxx:send-reg-first]"))
//			return "请发送注册包";
//		else
//			return "数据接收中";
		return "数据接受中";//请连接设备
	}
	public static boolean isNumber(String str){
		return str.matches("^\\d+$");
	}

}
