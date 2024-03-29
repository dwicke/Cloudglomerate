/*
 * Copyright (c) 2011 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package com.cloudglomerate.connection;

import com.cloudglomerate.connection.Response.Status;
import com.cloudglomerate.interfaces.Cloud;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleOAuthConstants;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.common.base.Preconditions;

import java.awt.Desktop;
import java.awt.Desktop.Action;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

/**
 * Implements OAuth authentication "native" flow recommended for installed clients in which the end
 * user must grant access in a web browser and then copy a code into the application.
 *
 * <p>
 * The client_secrets.json file contains your client application's client ID and client secret. They
 * can be found in the <a href="https://code.google.com/apis/console/">Google APIs Console</a>. If
 * this is your first time, click "Create project...". Then, activate the Google APIs your client
 * application uses and agree to the terms of service. Now, click on "API Access", and then on
 * "Create an OAuth 2.0 Client ID...". Enter a product name and click "Next". >Select "Installed
 * application" and click "Create client ID". Finally, enter the "Client ID" and "Client secret"
 * shown under "Client ID for installed applications" into
 * {@code src/main/resources/client_secrets.json}.
 * </p>
 *
 * <p>
 * Warning: the client ID and secret are not secured and are plainly visible to users of your
 * application. It is a hard problem to secure client credentials in installed applications.
 * </p>
 *
 * <p>
 * In this sample code, it attempts to open the browser using {@link Desktop#isDesktopSupported()}.
 * If that fails, on Windows it tries {@code rundll32}. If that fails, it opens the browser
 * specified in {@link #BROWSER}, though note that currently we've only tested this code with Google
 * Chrome (hence this is the default value).
 * </p>
 *
 * @author Yaniv Inbar
 */
public class OAuth2Native {

	private static final String RESOURCE_LOCATION = getLoc();

	private static final String RESOURCE_PATH =
			("resources" + RESOURCE_LOCATION).replace(
					'/', File.separatorChar);



	/** Google client secrets or {@code null} before initialized in {@link #authorize}. */
	private static GoogleClientSecrets clientSecrets = null;

	/** Returns the Google client secrets or {@code null} before initialized in {@link #authorize}. */
	public static GoogleClientSecrets getClientSecrets() {
		return clientSecrets;
	}

	private static String getLoc() {
		// TODO Auto-generated method stub
		String path = null;
		
			//System.out.println(dir1.getCanonicalPath() + "/resources/client_secrets.json");
			path = "/resources/client_secrets.json";
		
		return path;
	}

	/**
	 * Loads the Google client secrets (if not already loaded).
	 *
	 * @param jsonFactory JSON factory
	 */
	private static GoogleClientSecrets loadClientSecrets(JsonFactory jsonFactory) throws IOException {
		if (clientSecrets == null) {
			InputStream inputStream = OAuth2Native.class.getResourceAsStream(RESOURCE_LOCATION);
			Preconditions.checkNotNull(inputStream, "missing resource %s", RESOURCE_LOCATION);
			clientSecrets = GoogleClientSecrets.load(jsonFactory, inputStream);
			Preconditions.checkArgument(!clientSecrets.getDetails().getClientId().startsWith("[[")
					&& !clientSecrets.getDetails().getClientSecret().startsWith("[["),
					"Please enter your client ID and secret from the Google APIs Console in %s from the "
							+ "root samples directory", RESOURCE_PATH);
		}
		return clientSecrets;
	}


	/**
	 * Authorizes the installed application to access user's protected data.
	 *
	 * @param transport HTTP transport
	 * @param jsonFactory JSON factory
	 * @param receiver verification code receiver
	 * @param scopes OAuth 2.0 scopes
	 */
	public static GoogleResponse authorize(HttpTransport transport, JsonFactory jsonFactory,
			Iterable<String> scopes) {

		String redirectUri = GoogleOAuthConstants.OOB_REDIRECT_URI;
		GoogleClientSecrets clientSecrets = null;
		try {
			clientSecrets = loadClientSecrets(jsonFactory);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		GoogleResponse gResp = new GoogleResponse(Status.INITIATED, Cloud.GOOGLE);
		GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
				transport, jsonFactory, clientSecrets, scopes).setAccessType("online")
				.setApprovalPrompt("auto").build();

		gResp.setAuthCodeFlow(flow);

		String url = flow.newAuthorizationUrl().setRedirectUri(redirectUri).build();
		gResp.setURL(url);
		return gResp;
	}

	public static void getCredential(GoogleResponse resp)
	{
		String redirectUri = GoogleOAuthConstants.OOB_REDIRECT_URI;
		GoogleTokenResponse response = null;
		try {
			response = resp.getAuthCodeFlow().newTokenRequest(resp.getCode()).setRedirectUri(redirectUri).execute();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		// store credential and return it
		try {
			resp.setCreds( resp.getAuthCodeFlow().createAndStoreCredential(response, null));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		resp.setStatus(Status.CONNECTED);
	}



	private OAuth2Native() {
	}
}
