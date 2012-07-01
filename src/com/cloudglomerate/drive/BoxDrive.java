package com.cloudglomerate.drive;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

import javax.swing.tree.DefaultMutableTreeNode;

import cn.com.believer.songyuanframework.openapi.storage.box.BoxExternalAPI;
import cn.com.believer.songyuanframework.openapi.storage.box.factories.BoxRequestFactory;
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
	private BoxFolder boxFolder;
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
				folderID = ((BoxFolder)folder).getBoxFolder().getFolderId();
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
				if (file.isFolder())
				{
					BoxFolderImpl bfolder = (BoxFolderImpl) file;
					BoxFolder myFold = new BoxFolder(myID);
					
					folder.addFile(myFold);
				}
				else 
				{
					BoxFileImpl bfile = (BoxFileImpl) file;
					BoxFile boxfile = new BoxFile(myID);
					boxfile.setBoxFile(bfile);
					folder.addFile(boxfile);
				}
				if (file != null)
				{
					System.out.println(file.getName());
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
	public IDrive listParentDirectory(CloudFolder folder) {
		// TODO Auto-generated method stub
		return null;
	}

}