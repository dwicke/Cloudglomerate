package com.cloudglomerate.drive;

import java.io.File;
import java.rmi.RemoteException;
import java.util.Map;

import com.cloudglomerate.util.Subscriber;

public class Drive implements IDrive, Subscriber {
	
	// integer is the key that identifies the drive
	private Map<Integer, IDrive> drives;

	@Override
	public IDrive upload(AbstractFile file, CloudFolder toLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IDrive download(AbstractFile file, File toLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IDrive delete(AbstractFile file) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IDrive move(AbstractFile what, AbstractFile toWhere) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IDrive rename(AbstractFile file, String newName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IDrive list(CloudFolder folder) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IDrive newFolder(String folderName, CloudFolder where) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Object pub, Object code) throws RemoteException {
		// TODO Auto-generated method stub
		
	}
	
	
}
