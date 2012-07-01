package com.cloudglomerate.connection;

import java.util.HashMap;
import java.util.Map;

import com.cloudglomerate.interfaces.Cloud;
import com.cloudglomerate.util.BasicPublisher;
import com.cloudglomerate.util.ID;
import com.cloudglomerate.util.IDManager;

public class ConnectionManager {

	private Map<ID, Connection> conns;
	private BasicPublisher pub;
	
	public ConnectionManager(BasicPublisher pub)
	{
		conns = new HashMap<ID, Connection>();
		this.pub = pub;
	}
	
	public ConnectionManager()
	{
		conns = new HashMap<ID, Connection>();
	}

	/**
	 * This method starts the request for the connection
	 * Usually the connection is a two step process.
	 * If response is good then call connect once necessary
	 * steps have been completed.
	 * @param connType
	 * @return
	 */
	public Response requestConnection(Cloud connType)
	{
		switch(connType)
		{
		case BOX:
			return ConnectionFactory.newBoxConnection().requestConnection();
		default:
			return null;
		}
	}

	/**
	 * Creates the connection and returns the ID
	 * that is used to identify the connection and the
	 * corresponding drive.
	 * @param resp
	 * @return
	 */
	public ID connect(Response resp)
	{
		ID id = null;
		Connection conn = null;
		switch(resp.getCloud())
		{
		case BOX:
			conn = ConnectionFactory.newBoxConnection(resp);
			if (conn.connect().getStatus() == Response.Status.CONNECTED)
			{
				id = IDManager.nextID();
				conns.put(id, conn);
			}
			break;

		default:
			return null;
		}
		
		
		if (id == null)
		{
			return null;
		}
		else
		{
			// notify the observers of connection
			pub.notifySubscribers(this, conn);
			// return the id
			return id;
		}
		
	}

	/**
	 * Disconnects the connection
	 * corresponding to the ID.
	 * @param connection
	 * @return
	 */
	public Response disconnect(ID connection)
	{
		// Must also notify the drive that to disconnect
		// this ID.
		Response re = conns.get(connection).disconnect();
		pub.notifySubscribers(this, conns.get(connection));
		return re;
	}


}
