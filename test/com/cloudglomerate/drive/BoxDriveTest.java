package com.cloudglomerate.drive;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.Test;

import com.cloudglomerate.connection.Response;
import com.cloudglomerate.interfaces.Cloud;
import com.cloudglomerate.interfaces.CloudManager;
import com.cloudglomerate.util.ID;

public class BoxDriveTest {

	@Test
	public void testBoxDrive() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpload() {
		fail("Not yet implemented");
	}

	@Test
	public void testDownload() {
		fail("Not yet implemented");
	}

	@Test
	public void testDelete() {
		fail("Not yet implemented");
	}

	@Test
	public void testMove() {
		fail("Not yet implemented");
	}

	@Test
	public void testRename() {
		fail("Not yet implemented");
	}

	@Test
	public void testList() {
		
		Response resp = CloudManager.getConnectionManager().requestConnection(Cloud.BOX);
		System.out.println(resp.getConfirmURL());
		System.out.println(">>>>>>>>>>> press enter after you are authenticated from box.net page.");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br = new BufferedReader(new InputStreamReader(System.in));
		try {
			br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ID id = CloudManager.getConnectionManager().connect(resp);
		CloudFolder folder = new CloudFolder();
		CloudManager.getDrive().list(folder);
		
		for (AbstractFile file : folder.getContents())
		{
			System.out.println(file.isFolder());
		}
		CloudManager.getConnectionManager().disconnect(id);
		
	}

	@Test
	public void testNewFolder() {
		fail("Not yet implemented");
	}

	@Test
	public void testListParentDirectory() {
		fail("Not yet implemented");
	}

}
