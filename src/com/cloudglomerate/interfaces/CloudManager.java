package com.cloudglomerate.interfaces;

import com.cloudglomerate.connection.ConnectionManager;
import com.cloudglomerate.drive.Drive;
import com.cloudglomerate.util.BasicPublisher;

public class CloudManager {

	private static BasicPublisher pub = new BasicPublisher();
	private static Drive drive = new Drive();
	private static ConnectionManager connMan = setupConnMan();
	private static ConnectionManager setupConnMan() {
		pub.addSubscriber(drive);
		return new ConnectionManager(pub);
	}
	

	public static Drive getDrive()
	{
		return drive;
	}

	public static ConnectionManager getConnectionManager()
	{
		return connMan;
	}



}
