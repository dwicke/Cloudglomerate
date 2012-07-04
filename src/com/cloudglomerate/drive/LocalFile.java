package com.cloudglomerate.drive;

import java.io.File;

import com.cloudglomerate.interfaces.Cloud;
import com.cloudglomerate.util.ID;

public class LocalFile implements AbstractFile {

	File file;
	
	public LocalFile(File file) {
		// TODO Auto-generated constructor stub
		this.file = file;
	}
	
	@Override
	public Cloud whichCloud() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isFolder() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ID getID() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getFileName() {
		// TODO Auto-generated method stub
		return file.getName();
	}
	
	public File getFile()
	{
		return file;
	}
	
	void setFile(File file)
	{
		this.file = file;
	}

}
