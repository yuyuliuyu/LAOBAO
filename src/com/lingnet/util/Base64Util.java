package com.lingnet.util;

import java.io.IOException;

import sun.misc.BASE64Decoder;

public class Base64Util {

	private static BASE64Decoder decoder = new BASE64Decoder();
	
	public static byte [] transferImageStringToByteArray(String image) throws IOException{
		
		return decoder.decodeBuffer(image);
		
	}
}
