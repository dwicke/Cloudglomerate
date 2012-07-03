package com.cloudglomerate.drive;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.tree.DefaultMutableTreeNode;

import cn.com.believer.songyuanframework.openapi.storage.box.BoxExternalAPI;
import cn.com.believer.songyuanframework.openapi.storage.box.factories.BoxRequestFactory;
import cn.com.believer.songyuanframework.openapi.storage.box.functions.DownloadRequest;
import cn.com.believer.songyuanframework.openapi.storage.box.functions.GetAccountTreeRequest;
import cn.com.believer.songyuanframework.openapi.storage.box.functions.GetAccountTreeResponse;
import cn.com.believer.songyuanframework.openapi.storage.box.impl.simple.SimpleBoxImpl;
import cn.com.believer.songyuanframework.openapi.storage.box.impl.simple.objects.BoxFileImpl;
import cn.com.believer.songyuanframework.openapi.storage.box.impl.simple.objects.BoxFolderImpl;
import cn.com.believer.songyuanframework.openapi.storage.box.objects.BoxAbstractFile;
import cn.com.believer.songyuanframework.openapi.storage.box.objects.BoxException;

import com.cloudglomerate.connection.BoxConnection;
import com.cloudglomerate.connection.BoxResponse;
import com.cloudglomerate.interfaces.Cloud;
import com.cloudglomerate.util.ID;

public class BoxDrive implements IDrive {

	private BoxResponse boxData;
	private ID myID;
	
	BoxExternalAPI iBoxExternalAPI = new SimpleBoxImpl();


	public BoxDrive(BoxResponse conn, ID myID) {
		// TODO Auto-generated constructor stub
		boxData = conn;
		this.myID = myID;
	}



	@Override
	public IDrive upload(AbstractFile file, CloudFolder toLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IDrive download(AbstractFile file, File toLocation) {
		// TODO Auto-generated method stub
		
		if (file.isFolder())
		{
			System.out.println(file.getFileName() + " is a folder and is from " + Cloud.BOX.name());
			((BoxFolder)file).clearFiles();
			list(((BoxFolder)file));

			File newFolder = new File(toLocation, file.getFileName());
			newFolder.mkdir();
			for (AbstractFile abfile : ((BoxFolder)file).getContents())
			{
				System.out.println("item in folder is: " + abfile.getFileName());
			}
			downloadFolder(file, newFolder);
		}
		else
		{
			File newfile = new File(toLocation, file.getFileName());
			download(((BoxFile) file).getBoxFile().getId(), newfile);
		}
		return this;
	}
	
	private void downloadFolder(AbstractFile file, File toLocation)
	{
		BoxFolder folder = (BoxFolder) file;
		for (AbstractFile abfile : folder.getContents())
		{
		
			if (abfile.isFolder() )
			{
				File newFolder = new File(toLocation, abfile.getFileName());
				newFolder.mkdir();
				((BoxFolder)abfile).clearFiles();
				list(((BoxFolder)abfile));
				downloadFolder(abfile,newFolder);
			}
			else
			{
				File newFolder = new File(toLocation, abfile.getFileName());
				System.out.println("the new file is " + newFolder.toString());
				download(((BoxFile) abfile).getBoxFile().getId(), newFolder);
			}
		}
	}
	
	private void download(String uploadedFileId, File toLocation) {
		String apiKey = boxData.getAPIKey();
		String authToken = boxData.getAuthToken().getAuthToken();
		DownloadRequest downloadRequest = BoxRequestFactory.createDownloadRequest(authToken, uploadedFileId, true,
                toLocation);
        try {
			iBoxExternalAPI.download(downloadRequest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BoxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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



		if (folder.whichCloud() == Cloud.ROOT ||
				(folder.whichCloud() == Cloud.BOX &&
				folder.getID() == this.myID))
		{

			
			String apiKey = boxData.getAPIKey();
			String authToken = boxData.getAuthToken().getAuthToken();
			String[] params;
			// get account file/folder tree structure
			if (folder.getID() == null)
			{
				params = new String[3];
				params[0] =  "nozip";
				params[1] = "simple";
				params[2] = "onelevel";
			}
			else
			{
				params = new String[2];
				params[0] =  "nozip";
				params[1] = "onelevel";
			}

			String folderID;
			if (folder.whichCloud() == Cloud.ROOT)
			{
				folderID = "0";
			}
			else
			{
				folderID = ((BoxFolder)folder).getBoxFolder().getId();
			}



			GetAccountTreeRequest getAccountTreeRequest = BoxRequestFactory.createGetAccountTreeRequest(apiKey,
					authToken, folderID, params);
			GetAccountTreeResponse getAccountTreeResponse = null;
			try {
				getAccountTreeResponse = iBoxExternalAPI.getAccountTree(getAccountTreeRequest);
			} catch (IOException | BoxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


			Enumeration preOrder = getAccountTreeResponse.getTree().preorderEnumeration();
			while(preOrder.hasMoreElements())
			{
			
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) preOrder.nextElement();
				BoxAbstractFile file = (BoxAbstractFile) node.getUserObject();
				if (node.isRoot()){
					// ignore the root
				}
				else if (file.isFolder())
				{
					BoxFolder myFold = new BoxFolder(myID);
					myFold.setBoxFolder(file);
					folder.addFile(myFold);
				}
				else 
				{
					BoxFile boxfile = new BoxFile(myID);
					boxfile.setBoxFile(file);
					folder.addFile(boxfile);
				}

			}
		}
		return this;
	}

	@Override
	public IDrive newFolder(String folderName, CloudFolder where) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public CloudFolder listParentDirectory(CloudFolder folder) {
		// TODO Auto-generated method stub
		
		return null;
	}

}
