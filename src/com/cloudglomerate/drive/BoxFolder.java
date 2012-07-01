package com.cloudglomerate.drive;

import cn.com.believer.songyuanframework.openapi.storage.box.impl.simple.objects.BoxFolderImpl;

import com.cloudglomerate.interfaces.Cloud;
import com.cloudglomerate.util.ID;

public class BoxFolder extends CloudFolder {
	
	private BoxFolderImpl folder;
	
	public BoxFolder(ID myID)
	{
		this.myID = myID;
	}
	
	@Override
	public Cloud whichCloud() {
		// TODO Auto-generated method stub
		return Cloud.BOX;
	}
	
	BoxFolderImpl getBoxFolder()
	{
		return folder;
	}
	
	void setBoxFolder(BoxFolderImpl folder)
	{
		this.folder = folder;
	}
	
	
}
