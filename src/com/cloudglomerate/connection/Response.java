package com.cloudglomerate.connection;

import com.cloudglomerate.interfaces.Cloud;

public abstract class Response {
	
	public enum Status{
		INITIATED,
		CONNECTED,
		DISCONNECTED,
		ERROR
	}
	
	private Status statusCode;
	private Cloud type;
	
	public Response(Status code, Cloud type)
	{
		this.statusCode = code;
		this.type = type;
	}
	
	public final Status getStatus()
	{
		return statusCode;
	}
	public void setStatus(Status stat)
	{
		statusCode = stat;
	}
	
	public final Cloud getCloud()
	{
		return type;
	}
	
	public abstract String getConfirmURL();
	
	
}
