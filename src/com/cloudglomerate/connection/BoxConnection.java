package com.cloudglomerate.connection;

import java.io.IOException;

import com.cloudglomerate.connection.Response.Status;
import com.cloudglomerate.interfaces.Cloud;

import cn.com.believer.songyuanframework.openapi.storage.box.BoxExternalAPI;
import cn.com.believer.songyuanframework.openapi.storage.box.factories.BoxRequestFactory;
import cn.com.believer.songyuanframework.openapi.storage.box.functions.GetAuthTokenRequest;
import cn.com.believer.songyuanframework.openapi.storage.box.functions.GetAuthTokenResponse;
import cn.com.believer.songyuanframework.openapi.storage.box.functions.GetTicketRequest;
import cn.com.believer.songyuanframework.openapi.storage.box.functions.GetTicketResponse;
import cn.com.believer.songyuanframework.openapi.storage.box.functions.LogoutRequest;
import cn.com.believer.songyuanframework.openapi.storage.box.impl.simple.SimpleBoxImpl;
import cn.com.believer.songyuanframework.openapi.storage.box.objects.BoxException;

public class BoxConnection implements Connection {

	private BoxResponse resp;
	private String apiKey = "kvh1n6veb9c4ver0j5bf21yrkz3f148x";

	public BoxConnection(Response resp) {
		// TODO Auto-generated constructor stub
		this.resp = (BoxResponse) resp;
	}

	public BoxConnection() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public
	Response connect() {
		// TODO Auto-generated method stub
		BoxExternalAPI iBoxExternalAPI = new SimpleBoxImpl();
		GetAuthTokenRequest getAuthTokenRequest = BoxRequestFactory.createGetAuthTokenRequest(apiKey,
				resp.getTicketResponse().getTicket());
		GetAuthTokenResponse getAuthTokenResponse = null;
		try {
			getAuthTokenResponse = iBoxExternalAPI.getAuthToken(getAuthTokenRequest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BoxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (getAuthTokenResponse != null)
		{
			resp.setToken(getAuthTokenResponse);
			resp.setStatus(Status.CONNECTED);
		}
		else
		{
			resp.setStatus(Status.ERROR);
		}

		
		return resp;
	}

	@Override
	public
	Response disconnect() {
		// TODO Auto-generated method stub
		BoxExternalAPI iBoxExternalAPI = new SimpleBoxImpl();
		LogoutRequest logoutRequest = BoxRequestFactory.createLogoutRequest(apiKey, resp.getAuthToken().getAuthToken());
        try {
			iBoxExternalAPI.logout(logoutRequest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BoxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		resp.setStatus(Status.DISCONNECTED);
		return resp;
	}

	@Override
	public
	Response requestConnection() {
		// TODO Auto-generated method stub
		BoxExternalAPI iBoxExternalAPI = new SimpleBoxImpl();
		// get the ticket and obtain the response
		GetTicketRequest getTicketRequest = BoxRequestFactory.createGetTicketRequest(apiKey);
		GetTicketResponse getTicketResponse = null;
		try {
			getTicketResponse = iBoxExternalAPI.getTicket(getTicketRequest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BoxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		if (getTicketRequest != null)
		{
			resp = new BoxResponse(Status.INITIATED, Cloud.BOX);
			resp.setTicketResponse(getTicketResponse);
		}
		else
		{
			resp = new BoxResponse(Status.ERROR, Cloud.BOX);
		}
		return resp;
	}

	@Override
	public Cloud getCloud() {
		// TODO Auto-generated method stub
		return Cloud.BOX;
	}

}
