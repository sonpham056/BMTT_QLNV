package app.gui;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA {
	public static final String toHexString(String user, String pass) throws NoSuchAlgorithmException {
		String chuoi = "";
		chuoi = user + pass;
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(chuoi.getBytes());
		byte[] byteData = md.digest();
		StringBuffer sb = new StringBuffer();
		for(int i=0; i<byteData.length; i++)
			sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
		String sha = sb.toString();
		return sha;
	}
}
