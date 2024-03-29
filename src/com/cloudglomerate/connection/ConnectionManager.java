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
		pub = new BasicPublisher();
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
		case GOOGLE:
			return ConnectionFactory.newGoogleConnection().requestConnection();
		default:
			return null;
		}
	}

	/**
	 * Creates the connection and returns the ID
	 * that is used to identify the connection and the
	 * corresponding drive.  Notifies subs by passing the conn
	 * with a connected status.
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
		case GOOGLE:
			conn = ConnectionFactory.newGoogleConnection(resp);
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
			pub.notifySubscribers(this, resp);
			// return the id
			return id;
		}
		
	}

	/**
	 * Disconnects the connection
	 * corresponding to the ID.
	 * Notify sub by passing the connection 
	 * that has a disconnected status.
	 * @param connection
	 * @return
	 */
	public Response disconnect(ID connection)
	{
		// Must also notify the drive that to disconnect
		// this ID.
		Response re = conns.get(connection).disconnect();
		conns.remove(connection);
		pub.notifySubscribers(connection, re);
		return re;
	}


}
