package com.cloudglomerate.drive;

import com.cloudglomerate.interfaces.Cloud;
import com.cloudglomerate.util.ID;

public interface AbstractFile {

	
	/**
	 * The name of the file/folder.
	 * @return
	 */
	@Override
	public String toString();
	
	/**
	 * Which cloud is the file in.
	 * @return
	 */
	public Cloud whichCloud();
	
	
	/**
	 * Returns true if a folder false otherwise.
	 * @return
	 */
	public boolean isFolder();
	
	/**
	 * Which drive based on ID
	 */
	public ID getID();
	
	
	
	
	public String getFileName();
	
	
	
}
