package test.org.lggl;

import org.lggl.multiplayer.PackageServer;
import org.lggl.multiplayer.ServerHandler;

public class Quest2Server extends ServerHandler {

	public Quest2Server(PackageServer owner) {
		super(owner);
	}

	@Override
	public void putValue(String name, String value) {
		// TODO Auto-generated method stub
		System.out.println("[QUEST2 SERVER] PUTTING " + name+":"+value);
		if (name.equals("server.playerConnect")) {
			System.out.println("[QUEST2 WELCOME] Welcome " + value + "!");
		}
	}

	@Override
	public String getValue(String name) {
		System.out.println("[QUEST2 SERVER] ASKING " + name);
		return "nothing";
	}

	@Override
	public void onPacket(short type, byte[] data) {
		// TODO Auto-generated method stub
		System.out.println("[QUEST2 DEBUG] PACKET");
	}

}