package com.cloudglomerate.drive;

public class CloudFolder implements AbstractFile{
	
	private CloudFolder parent;
	
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
	public String whichCloud() {
		// TODO Auto-generated method stub
		return "root";
	}
	
	
	
}
