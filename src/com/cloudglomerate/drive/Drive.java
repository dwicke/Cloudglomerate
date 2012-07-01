package com.cloudglomerate.drive;

import java.io.File;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

import com.cloudglomerate.connection.Connection;
import com.cloudglomerate.connection.Response;
import com.cloudglomerate.connection.Response.Status;
import com.cloudglomerate.util.ID;
import com.cloudglomerate.util.IDManager;
import com.cloudglomerate.util.Subscriber;

public class Drive implements IDrive, Subscriber {

	// integer is the key that identifies the drive
	private Map<ID, IDrive> drives;

	public Drive()
	{
		drives = new HashMap<ID, IDrive>();
	}


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
		
		for (IDrive drive : drives.values())
		{
			drive.list(folder);
		}
		
		return this;
	}

	@Override
	public IDrive newFolder(String folderName, CloudFolder where) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Object pub, Object code) throws RemoteException {
		// TODO Auto-generated method stub
		Response conn = (Response) code;
		if (conn.getStatus() == Status.CONNECTED)
		{
			ID id = IDManager.currentID();
			drives.put(id, DriveFactory.getDrive(conn, id));
		}
		else
		{
			// the ID of the connection is the
			// the pub
			drives.remove(pub);
		}

	}


	@Override
	public IDrive listParentDirectory(CloudFolder folder) {
		// TODO Auto-generated method stub
		return null;
	}


}
