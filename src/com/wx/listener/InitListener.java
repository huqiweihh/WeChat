package com.wx.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.wx.util.Token;

public class InitListener implements ServletContextListener{
	Token tk = new Token();
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("΢�ŷ���˳���ر�......");
		
	}

	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("΢�ŷ���˳�������......");
		tk.getToken();
		
		
	}

}
