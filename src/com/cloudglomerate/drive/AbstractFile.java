package com.cloudglomerate.drive;

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
	public String whichCloud();
	
	
	/**
	 * Returns true if a folder false otherwise.
	 * @return
	 */
	public boolean isFolder();
	
	
	
}
