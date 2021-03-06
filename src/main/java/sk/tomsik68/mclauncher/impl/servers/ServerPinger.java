package sk.tomsik68.mclauncher.impl.servers;

import java.io.DataInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.Callable;

import javax.net.SocketFactory;

import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;
import sk.tomsik68.mclauncher.api.servers.ServerInfo;

final class ServerPinger implements Callable<ServerPingResult> {
	private final ServerPingPacketFactory pingPacketFactory;
	private final ServerInfo server;

	public ServerPinger(ServerInfo server, ServerPingPacketFactory pingPacketFactory) {
		this.server = server;
		this.pingPacketFactory = pingPacketFactory;
	}

	@Override
	public ServerPingResult call() throws Exception {
		// open a connection
		InetSocketAddress addr = InetSocketAddress.createUnresolved(server.getIP(), server.getPort());
		Socket socket = SocketFactory.getDefault().createSocket();
		socket.connect(addr);
		// get streams
		InputStream is = socket.getInputStream();
		DataInputStream dis = new DataInputStream(is);
		OutputStream os = socket.getOutputStream();

		// send data
		os.write(pingPacketFactory.createPingPacket(server));
		// wait for data
		while (is.available() == 0) {
			Thread.sleep(20l);
		}

		// receive data
		byte zero = dis.readByte();
		if (zero != 0) {
			return new ServerPingResult(new RuntimeException("Outdated protocol!"));
		}
		String jsonString = dis.readUTF();
		new ServerPingResult(new JSONPingedServerInfo47((JSONObject) JSONValue.parse(jsonString), server.getIP(),
				server.getName(), server.getPort()));
		socket.close();
		return null;
	}
}
