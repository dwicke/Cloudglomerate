package com.cloudglomerate.connection;

import java.io.IOException;
import java.util.Arrays;

import com.cloudglomerate.interfaces.Cloud;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.LowLevelHttpRequest;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson.JacksonFactory;
import com.google.api.services.drive.DriveScopes;

public class GoogleConnection implements Connection{

	private GoogleClientSecrets secrets;
	GoogleResponse resp;
	
	public GoogleConnection(Response resp2) {
		// TODO Auto-generated constructor stub
		this.resp = (GoogleResponse) resp2;
	}

	public GoogleConnection() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Response connect() {
		// TODO Auto-generated method stub
		OAuth2Native.getCredential(resp);
		return resp;
	}

	@Override
	public Response disconnect() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cloud getCloud() {
		// TODO Auto-generated method stub
		return Cloud.GOOGLE;
	}

	@Override
	public Response requestConnection() {
		// TODO Auto-generated method stub
		
		resp = OAuth2Native.authorize(new NetHttpTransport(), new JacksonFactory(), Arrays.asList(DriveScopes.DRIVE));
		
		
		return resp;
	}

	

}
