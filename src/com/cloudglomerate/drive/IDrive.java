package com.cloudglomerate.drive;

import java.io.File;

public interface IDrive {

	/**
	 * Uploads file to the specified CloudFolder location.
	 * Returns itself so that you can chain calls.
	 * @param file
	 * @param toLocation
	 * @return
	 */
	public IDrive upload(AbstractFile file, CloudFolder toLocation);
	/**
	 * Downloads the file to the location on your hard drive.
	 * returns this drive so you can chain.
	 * @param file
	 * @param toLocation
	 * @return
	 */
	public IDrive download(AbstractFile file, File toLocation);
	/**
	 * Deletes the file/folder from the drive
	 * @param file
	 * @return
	 */
	public IDrive delete(AbstractFile file);
	/**
	 * This moves one cloud file/folder to another file/folder.
	 * 
	 * @param what
	 * @param toWhere
	 * @return
	 */
	public IDrive move(AbstractFile what, AbstractFile toWhere);
	/**
	 * renames the file/folder to the new name.
	 * @param file
	 * @param newName
	 * @return
	 */
	public IDrive rename(AbstractFile file, String newName);
	/**
	 * The method constructs a list of the files/folders and stores it
	 * inside folder argument.  Returns this Drive.
	 * @param folder
	 * @return
	 */
	public IDrive list(CloudFolder folder);
	/**
	 * Creates a new folder with the provided name in the specified cloud folder.
	 * @param folderName
	 * @param where
	 * @return
	 */
	public IDrive newFolder(String folderName, CloudFolder where);
}