package com.cloudglomerate.drive;

import com.cloudglomerate.interfaces.Cloud;

public class GoogleFolder extends CloudFolder{
	
	private static String mimetype = "application/vnd.google-apps.folder";
	private com.google.api.services.drive.model.File gFile;
	
	public static String getFolderMimeType()
	{
		return mimetype;
	}

	public com.google.api.services.drive.model.File getgFile() {
		return gFile;
	}

	public void setgFile(com.google.api.services.drive.model.File gFile) {
		this.gFile = gFile;
	}
	
	@Override
	public String getFileName() {
		// TODO Auto-generated method stub
		return gFile.getTitle();
	}
	
	@Override
	public Cloud whichCloud() {
		// TODO Auto-generated method stub
		return Cloud.GOOGLE;
	}
	
	public String toString()
	{
		return getFileName() + " - Drive Folder";
	}

}
