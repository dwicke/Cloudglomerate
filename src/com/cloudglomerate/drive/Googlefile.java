package com.cloudglomerate.drive;

import com.cloudglomerate.interfaces.Cloud;
import com.cloudglomerate.util.ID;

public class Googlefile extends CloudFile{

	private com.google.api.services.drive.model.File gFile;
	
	
	public Googlefile(ID id) {
		// TODO Auto-generated constructor stub
		myid = id;
	}
	
	@Override
	public Cloud whichCloud() {
		// TODO Auto-generated method stub
		return Cloud.GOOGLE;
	}

	@Override
	public ID getID() {
		// TODO Auto-generated method stub
		return myid;
	}
	
	public com.google.api.services.drive.model.File getFile()
	{
		return gFile;
	}

	public void setFile(com.google.api.services.drive.model.File file)
	{
		gFile = file;
	}
	
	@Override
	public String getFileName() {
		// TODO Auto-generated method stub
		return gFile.getTitle();
	}
	
	public String toString()
	{
		return getFileName() + " - Drive File";
	}

}
