package com.codeproj.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Main {
	public static void main(String [] args) {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		String result = bCryptPasswordEncoder.encode("jelszo");
		System.out.println(result);
	}
}
