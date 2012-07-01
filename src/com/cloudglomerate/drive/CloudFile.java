package com.cloudglomerate.drive;

import com.cloudglomerate.util.ID;

public abstract class CloudFile implements AbstractFile {

	protected ID myid;

	@Override
	public boolean isFolder() {
		// TODO Auto-generated method stub
		return false;
	}

}
