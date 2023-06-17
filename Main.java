package ism;

import java.security.Security;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class Main {
	
	private static final String pwdHash = "66f12623a2fa30f5db67af3b10d4afa72d54eb39c088571b2f681bd37f50f24d";

	public static void main(String[] args) throws NoSuchAlgorithmException, IOException, NoSuchProviderException {
		Security.addProvider(new BouncyCastleProvider());
		MessageDigest md = MessageDigest.getInstance("MD5");
		MessageDigest sha = MessageDigest.getInstance("SHA-256");
		
		File dict = new File("ignis-10M.txt");
		FileReader fr = new FileReader(dict);
		BufferedReader br = new BufferedReader(fr);

		long stime = System.currentTimeMillis();
		
		String line = br.readLine();
		while (line != null) {
			if (pwdHash.equals(getString(sha.digest(md.digest(("ismsap" + line).getBytes()))))) {
				break;
			}
			line = br.readLine();
		}
		
		long etime = System.currentTimeMillis();
		br.close();
		System.out.print("pwd: " + line + '\n' + "time: " + (etime-stime));
	}
	
	private static String getString(byte[] bytes) {
		StringBuilder sb = new StringBuilder();
		for(byte value : bytes) {
			sb.append(String.format("%02x", value).toLowerCase());
		}
		return sb.toString();
	}

}