package com.cloudglomerate.drive;

import java.util.ArrayList;
import java.util.List;

import com.cloudglomerate.interfaces.Cloud;
import com.cloudglomerate.util.ID;

public class CloudFolder implements AbstractFile, Cloneable{
	
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
	void clearFiles()
	{
		files.clear();
	}
	void setFiles(List<AbstractFile> files)
	{
		this.files = files; 
	}

	@Override
	public ID getID() {
		// TODO Auto-generated method stub
		return myID;
	}
	
	/**
	 * sets the id
	 * @return
	 */
	void setID(ID id)
	{
		myID = id;
	}

	@Override
	public String getFileName() {
		// TODO Auto-generated method stub
		return "root";
	}
	
	@Override
	protected CloudFolder clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		CloudFolder fold = new CloudFolder();
		fold.setFiles(this.getContents());
		fold.setID(this.getID());
		
		return fold;
	}
	
	void copyInto(CloudFolder fold)
	{
		fold.setFiles(files);
		fold.setID(myID);
	}
	
	
}
