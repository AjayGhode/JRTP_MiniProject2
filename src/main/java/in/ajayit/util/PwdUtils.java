package in.ajayit.util;

import org.apache.commons.lang3.RandomStringUtils;

public class PwdUtils {
	
	public static String generateRandomPwd() { //static - by using this class name we can call this method
		//search in google as generate random password in java and added dependency
		
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		String pwd = RandomStringUtils.random( 6, characters );
		return pwd; 
	}

}
