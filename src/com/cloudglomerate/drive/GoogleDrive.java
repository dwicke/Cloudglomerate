package com.cloudglomerate.drive;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import javax.activation.MimetypesFileTypeMap;

import com.cloudglomerate.connection.GoogleResponse;
import com.cloudglomerate.interfaces.Cloud;
import com.cloudglomerate.util.ID;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.http.json.JsonHttpRequest;
import com.google.api.client.http.json.JsonHttpRequestInitializer;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson.JacksonFactory;
import com.google.api.services.drive.Drive.Builder;
import com.google.api.services.drive.Drive.Children;
import com.google.api.services.drive.Drive.Files;
import com.google.api.services.drive.DriveRequest;
import com.google.api.services.drive.model.ChildList;
import com.google.api.services.drive.model.ChildReference;
import com.google.api.services.drive.model.FileList;
import com.google.api.services.drive.model.ParentReference;


public class GoogleDrive implements IDrive {

	private com.google.api.services.drive.Drive drive;
	private GoogleResponse conn;
	private ID myID;
	private final String apikey = "4/-GWbwVdjlPGTj6pbQevkrMnrMDUv.kiIcljjhProSuJJVnL49Cc9Uks6kcAI";

	public GoogleDrive() {
		// TODO Auto-generated constructor stub

	}
	public GoogleDrive(GoogleResponse conn, ID id) {
		// TODO Auto-generated constructor stub
		this.conn = conn;
		drive = buildService(conn.getCreds().getAccessToken(), apikey);
		myID = id;

		//System.out.println("The drive is " + drive.);

	}
	@Override
	public IDrive upload(AbstractFile file, CloudFolder toLocation) {
		// TODO Auto-generated method stub
		LocalFile lFile = (LocalFile) file;
		
		String mime = new MimetypesFileTypeMap().getContentType(lFile.getFile());
		com.google.api.services.drive.model.File body = new com.google.api.services.drive.model.File();
	    body.setTitle(lFile.getFileName());
	    body.setDescription("");
	    body.setMimeType(mime);
	    
	    GoogleFolder gFold = (GoogleFolder) toLocation;
	    
	    
	 // Set the parent folder.
	    if (gFold.getgFile().getId() != null && gFold.getgFile().getId().length() > 0) {
	      body.setParents(
	          Arrays.asList(new ParentReference().setId(gFold.getgFile().getId())));
	    }

	    // File's content.
	    FileContent mediaContent = new FileContent(mime, lFile.getFile());
	    try {
	    	com.google.api.services.drive.model.File insertedFile =
	    			drive.files().insert(body, mediaContent).execute();
	    	Googlefile gFile = new Googlefile(myID);
	    	gFile.setFile(insertedFile);
	    	toLocation.addFile(gFile);
	    }catch (IOException e) {
	        System.out.println("An error occured: " + e);
	        return null;
	      }
		return null;
	}

	@Override
	public IDrive download(AbstractFile file, File toLocation) {
		// TODO Auto-generated method stub
		InputStream st = downloadFile(((Googlefile)file).getFile());
		
		return null;
	}

	
	private InputStream downloadFile(com.google.api.services.drive.model.File file) {
	    if (file.getDownloadUrl() != null && file.getDownloadUrl().length() > 0) {
	      try {
	        HttpResponse resp =
	            drive.getRequestFactory().buildGetRequest(new GenericUrl(file.getDownloadUrl()))
	                .execute();
	        return resp.getContent();
	      } catch (IOException e) {
	        // An error occurred.
	        e.printStackTrace();
	        return null;
	      }
	    } else {
	      // The file doesn't have any content stored on Drive.
	      return null;
	    }
	  }
	@Override
	public IDrive delete(AbstractFile file) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IDrive move(AbstractFile what, AbstractFile toWhere) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IDrive rename(AbstractFile file, String newName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IDrive list(CloudFolder folder) {
		// TODO Auto-generated method stub

		if (folder.whichCloud() == Cloud.ROOT ||
				folder.whichCloud() == Cloud.GOOGLE)
		{
			try {

				if (folder.whichCloud() == Cloud.GOOGLE)
				{
					// then it is a google folder and I want to list the children


					Children.List request = drive.children().list(((GoogleFolder)folder).getgFile().getId());
					do {
						//List<ChildReference> l = //drive.children().list(((GoogleFolder)folder).getgFile().getId()).execute().getItems();
						ChildList children = request.execute();
						for (ChildReference file : children.getItems())
						{
							com.google.api.services.drive.model.File chFile = 
									drive.files().get(file.getId()).execute();
							if (chFile.getMimeType().equals( GoogleFolder.getFolderMimeType()))
							{
								GoogleFolder gFold = new GoogleFolder();
								gFold.setID(myID);
								gFold.setgFile(chFile);
								folder.addFile(gFold);
							}
							else
							{
								// it is just a file
								Googlefile gfile = new Googlefile(myID);
								gfile.setFile(chFile);
								folder.addFile(gfile);
							}
						}
						request.setPageToken(children.getNextPageToken());
					}while (request.getPageToken() != null &&
							request.getPageToken().length() > 0);
				}
				else
				{
					Files.List request = drive.files().list();
					// Only want the root files
					do
					{
						FileList f = request.execute();
						List<com.google.api.services.drive.model.File> items = f.getItems();

						for (com.google.api.services.drive.model.File file : items)
						{
							System.out.println("MIME: " + file.getMimeType() + " Parent:" );

							List<ParentReference> refs = file.getParents();
							boolean isRoot = false;
							if (refs == null)
							{
								isRoot = true;
							}
							else
							{	
								for (ParentReference ref : refs)
								{
									if (ref.getIsRoot())
									{
										isRoot = true;
										break;
									}
								}
							}
							if (isRoot)
							{
								if (file.getMimeType().equals( GoogleFolder.getFolderMimeType()))
								{
									GoogleFolder gFold = new GoogleFolder();
									gFold.setID(myID);
									gFold.setgFile(file);
									folder.addFile(gFold);
								}
								else
								{
									// it is just a file
									Googlefile gfile = new Googlefile(myID);
									gfile.setFile(file);
									folder.addFile(gfile);
								}
							}
						}
						request.setPageToken(f.getNextPageToken());

					}
					while (request.getPageToken() != null &&
							request.getPageToken().length() > 0);
				}


			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return this;
	}

	@Override
	public IDrive newFolder(String folderName, CloudFolder where) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CloudFolder listParentDirectory(CloudFolder folder) {
		// TODO Auto-generated method stub
		return null;
	}


	com.google.api.services.drive.Drive buildService(final String AuthToken, final String ApiKey) {
		HttpTransport httpTransport = this.conn.getCreds().getTransport();//new NetHttpTransport();
		JsonFactory jsonFactory = this.conn.getCreds().getJsonFactory();

		Builder b = new Builder(httpTransport, jsonFactory, null);
		b.setJsonHttpRequestInitializer(new JsonHttpRequestInitializer() {

			@Override
			public void initialize(JsonHttpRequest request) throws IOException {
				DriveRequest driveRequest = (DriveRequest) request;
				driveRequest.setPrettyPrint(true);
				driveRequest.setKey(ApiKey);
				driveRequest.setOauthToken(AuthToken);
			}
		});

		return b.build();
	}

}
