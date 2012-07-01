package com.cloudglomerate.connection;

public class ConnectionFactory {
	public static BoxConnection newBoxConnection()
	{
		return new BoxConnection();
	}

	public static Connection newBoxConnection(Response resp) {
		// TODO Auto-generated method stub
		return new BoxConnection(resp);
	}

}
