package com.cloudglomerate.connection;

import com.cloudglomerate.interfaces.Cloud;

import cn.com.believer.songyuanframework.openapi.storage.box.functions.GetAuthTokenResponse;
import cn.com.believer.songyuanframework.openapi.storage.box.functions.GetTicketResponse;

public class BoxResponse extends Response{

	private GetTicketResponse ticket;
	private GetAuthTokenResponse token;
	private String apiKey = "kvh1n6veb9c4ver0j5bf21yrkz3f148x";
	
	public BoxResponse(Status code, Cloud type) {
		super(code, type);
		// TODO Auto-generated constructor stub
	}

	public void setTicketResponse(GetTicketResponse response)
	{
		ticket = response;
	}
	public GetTicketResponse getTicketResponse()
	{
		return ticket;
	}
	
	public void setToken(GetAuthTokenResponse getAuthTokenResponse)
	{
		token = getAuthTokenResponse;
		
	}
	public GetAuthTokenResponse getAuthToken()
	{
		return token;
	}
	
	public String getAPIKey()
	{
		return apiKey;
	}
	
	
	@Override
	public String getConfirmURL() {
		// TODO Auto-generated method stub
		return "http://www.box.net/api/1.0/auth/"
				+ ticket.getTicket();
	}
	
	

}
