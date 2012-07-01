package com.cloudglomerate.drive;

import com.cloudglomerate.interfaces.Cloud;
import com.cloudglomerate.util.ID;
import com.cloudglomerate.connection.*;
public class DriveFactory {

	// factory methods to create the various drive
	// implementations such as DropBox, box
	public static IDrive getDrive(Response conn, ID id)
	{
		switch(conn.getCloud())
		{
		case BOX:
			return new BoxDrive((BoxResponse)conn, id);
		default:
			return null;
		}

	}

}
