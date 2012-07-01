package com.cloudglomerate.connection;

import com.cloudglomerate.interfaces.Cloud;

public interface Connection {

	Response requestConnection();
	Response connect();
	Response disconnect();
	Cloud getCloud();
}
