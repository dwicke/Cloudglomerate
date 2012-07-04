package com.cloudglomerate.connection;

import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.Test;

import com.cloudglomerate.interfaces.Cloud;
import com.cloudglomerate.interfaces.CloudManager;
import com.cloudglomerate.util.Browser;
import com.cloudglomerate.util.ID;

public class GoogleConnect {

	@Test
	public void testConnect() {
		GoogleResponse resp = (GoogleResponse) CloudManager.getConnectionManager().requestConnection(Cloud.GOOGLE);
		System.out.println("The url is " + resp.getConfirmURL());
		Browser.browse(resp.getConfirmURL());
		 String code;
		    do {
		      System.out.print("Please enter code: ");
		      code = new Scanner(System.in).nextLine();
		    } while (code.isEmpty());
		    resp.setCode(code);
		    ID id = CloudManager.getConnectionManager().connect(resp);
		    
		    System.out.println(resp.getStatus().toString());
		    
	}

	@Test
	public void testDisconnect() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCloud() {
		fail("Not yet implemented");
	}

	@Test
	public void testRequestConnection() {
		GoogleResponse resp = (GoogleResponse) CloudManager.getConnectionManager().requestConnection(Cloud.GOOGLE);
		System.out.println("The url is " + resp.getConfirmURL());
	}

}
