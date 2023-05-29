package com.ruoyi.common.utils;

import org.springframework.boot.web.servlet.server.Session;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.UUID;

/**
 * 封装各种生成唯一性ID算法的工具类.
 * @author ThinkGem
 * @version 2013-01-15
 */
@Service
@Lazy(false)
public class IdGen {

	/**
	 * 封装JDK自带的UUID, 通过Random数字生成, 中间无-分割.
	 */
	public static String uuid() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}


	public Serializable generateId(Session session) {
		return IdGen.uuid();
	}

    protected static final String[] chars = new String[] { "a", "b", "c", "d", "e", "f",
		"g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
		"t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
		"6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",
		"J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
		"W", "X", "Y", "Z" };

	public static String generateShortUuid() {
		StringBuffer shortBuffer = new StringBuffer();
		String uuid = uuid();
		for (int i = 0; i < 8; i++) {
			String str = uuid.substring(i * 4, i * 4 + 4);
			int x = Integer.parseInt(str, 16);
			shortBuffer.append(chars[x % 0x3E]);
		}
		return shortBuffer.toString().toUpperCase();

	}

	public static void main(String[] args) {
		for (int i=0; i<100; i++){
			System.out.println(IdGen.uuid());
		}
	}

}
