package com.cloudglomerate.drive;

import cn.com.believer.songyuanframework.openapi.storage.box.impl.simple.objects.BoxFileImpl;

import com.cloudglomerate.interfaces.Cloud;
import com.cloudglomerate.util.ID;

public class BoxFile extends CloudFile {
	
	private BoxFileImpl bFile;

	public BoxFile(ID id) {
		// TODO Auto-generated constructor stub
		this.myid = id;
	}
	
	@Override
	public Cloud whichCloud() {
		// TODO Auto-generated method stub
		return Cloud.BOX;
	}

	@Override
	public ID getID() {
		// TODO Auto-generated method stub
		return myid;
	}

	void setBoxFile(BoxFileImpl file)
	{
		bFile = file;
	}
	
	
	
}
