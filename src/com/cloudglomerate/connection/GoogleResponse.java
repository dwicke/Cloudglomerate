package com.cloudglomerate.connection;

import com.cloudglomerate.interfaces.Cloud;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;

public class GoogleResponse extends Response{

	private GoogleAuthorizationCodeFlow flow;
	private Credential creds;
	private String browseURL;
	private String code;
	
	
	public GoogleResponse(Status code, Cloud type) {
		super(code, type);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getConfirmURL() {
		// TODO Auto-generated method stub
		return browseURL;
	}
	
	void setURL(String url)
	{
		browseURL = url;
	}
	
	void setAuthCodeFlow(GoogleAuthorizationCodeFlow flow)
	{
		this.flow = flow;
	}
	
	GoogleAuthorizationCodeFlow getAuthCodeFlow()
	{
		return flow;
	}
	
	public void setCode(String code)
	{
		this.code = code;
	}
	
	public String getCode()
	{
		return code;
	}

	public Credential getCreds() {
		return creds;
	}

	 void setCreds(Credential creds) {
		this.creds = creds;
	}
	

}
