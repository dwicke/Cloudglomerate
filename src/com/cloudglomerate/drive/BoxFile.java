package com.cloudglomerate.drive;

import cn.com.believer.songyuanframework.openapi.storage.box.impl.simple.objects.BoxFileImpl;
import cn.com.believer.songyuanframework.openapi.storage.box.objects.BoxAbstractFile;

import com.cloudglomerate.interfaces.Cloud;
import com.cloudglomerate.util.ID;

public class BoxFile extends CloudFile {
	
	private BoxAbstractFile bFile;

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

	void setBoxFile(BoxAbstractFile file)
	{
		bFile = file;
	}
	
	BoxAbstractFile getBoxFile()
	{
		return bFile;
	}

	@Override
	public String getFileName() {
		// TODO Auto-generated method stub
		return bFile.getName();
	}
        
        public String toString()
        {
            return bFile.getName() + " - " + whichCloud().name() + " file";
        }
	
}
