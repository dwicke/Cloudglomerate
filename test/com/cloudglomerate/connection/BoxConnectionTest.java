package com.cloudglomerate.connection;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.xml.ws.soap.AddressingFeature.Responses;

import org.junit.Test;

import com.cloudglomerate.connection.Response.Status;
import com.cloudglomerate.interfaces.Cloud;
import com.cloudglomerate.util.ID;

public class BoxConnectionTest {

	@Test
	public void testConnectionManagerBasicPublisher() {
		fail("Not yet implemented");
	}

	@Test
	public void testConnectionManager() {
		fail("Not yet implemented");
	}

	@Test
	public void testRequestConnection() {
		ConnectionManager man = new ConnectionManager();
		Response re = man.requestConnection(Cloud.BOX);
		assertEquals(re.getStatus().ordinal(), Status.INITIATED.ordinal());
		System.out.println(re.getConfirmURL());
		
	}

	@Test
	public void testConnect() {
		ConnectionManager man = new ConnectionManager();
		Response re = man.requestConnection(Cloud.BOX);
		assertEquals(re.getStatus().ordinal(), Status.INITIATED.ordinal());
		System.out.println(re.getConfirmURL());
		System.out.println(">>>>>>>>>>> press enter after you are authenticated from box.net page.");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br = new BufferedReader(new InputStreamReader(System.in));
		try {
			br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ID id = man.connect(re);
		assertNotNull(id);
		
		
	}

	@Test
	public void testDisconnect() {
		fail("Not yet implemented");
	}

}
