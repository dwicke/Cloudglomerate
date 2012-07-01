package com.cloudglomerate.drive;

import java.util.ArrayList;
import java.util.List;

import com.cloudglomerate.interfaces.Cloud;
import com.cloudglomerate.util.ID;

public class CloudFolder implements AbstractFile{
	
	protected CloudFolder parent;
	protected ID myID;
	protected List<AbstractFile> files = new ArrayList<AbstractFile>();
	
	
	
	@Override
	public boolean isFolder() {
		// TODO Auto-generated method stub
		return true;
	}
	
	public CloudFolder getParent()
	{
		return parent;
	}

	@Override
	public Cloud whichCloud() {
		// TODO Auto-generated method stub
		return Cloud.ROOT;
	}
	
	public List<AbstractFile> getContents()
	{
		return files;
	}
	
	void addFile(AbstractFile file)
	{
		files.add(file);
	}

	@Override
	public ID getID() {
		// TODO Auto-generated method stub
		return myID;
	}
	
	
}
