package com.iws.main;

import com.iws.utils.DbUtils;

public class MainProcess {

	public static void main(String[] args) {
		String driver = args[0];
		String url = args[1];
		String username = args[2];
		String password = args[3];
		DbUtils.process(driver, url, username, password);
	}
}
