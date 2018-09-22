package org.lggl.multiplayer;

public abstract class ServerHandler {

	protected PackageServer owner;
	
	public ServerHandler(PackageServer owner) {
		this.owner = owner;
	}
	
	public abstract void putValue(String name, String value);
	
	public abstract String getValue(String name);
	
	/**
	 * Not executed for packets of type -2 (GET_VALUE) or -3 (SET_VALUE)
	 * @param type
	 * @param data
	 */
	public abstract void onPacket(short type, byte[] data);
	
}