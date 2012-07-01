package com.cloudglomerate.util;

public class IDManager {
	private static int currentNum = 0;
	private static ID currentID;
	public static ID nextID()
	{
		currentID = new ID(++currentNum);
		return currentID;
	}
	public static ID currentID()
	{
		return currentID;
	}
}
