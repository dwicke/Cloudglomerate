package com.cloudglomerate.drive;

import cn.com.believer.songyuanframework.openapi.storage.box.impl.simple.objects.BoxFolderImpl;
import cn.com.believer.songyuanframework.openapi.storage.box.objects.BoxAbstractFile;

import com.cloudglomerate.interfaces.Cloud;
import com.cloudglomerate.util.ID;

public class BoxFolder extends CloudFolder {

	private BoxAbstractFile folder;

	public BoxFolder(ID myID)
	{
		this.myID = myID;
	}

	@Override
	public Cloud whichCloud() {
		// TODO Auto-generated method stub
		return Cloud.BOX;
	}

	BoxAbstractFile getBoxFolder()
	{
		return folder;
	}

	void setBoxFolder(BoxAbstractFile file)
	{
		this.folder = file;
	}

	@Override
	public String getFileName() {
		// TODO Auto-generated method stub
		return folder.getName();
	}

	public String toString()
	{
		return folder.getName() + " - " + whichCloud() + " folder";
	}
	@Override
	protected BoxFolder clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		
		BoxFolder fold = (BoxFolder) super.clone();
		fold.setBoxFolder(this.getBoxFolder());
		return fold;
	}
	
	void copyInto(CloudFolder fold)
	{
		super.copyInto(fold);
		((BoxFolder)(fold)).setBoxFolder(folder);
	}
	
}
