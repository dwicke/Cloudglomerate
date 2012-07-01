package com.cloudglomerate.connection;

interface Connection {

	Response requestConnection();
	Response connect();
	Response disconnect();
}
