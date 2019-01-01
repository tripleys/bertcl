package bert.rotmg.net;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import bert.crypto.RC4;
import bert.rotmg.constants.NetworkConstants;
import bert.rotmg.net.packets.Listen;
import bert.rotmg.net.packets.Packet;
import bert.rotmg.net.packets.PacketHandler;
import bert.rotmg.net.packets.Packets;
import bert.util.Util;

public class Client implements Runnable {

	/**
	 * Method cache that {@link PacketHandler} will use
	 */
    private static Method[] cache = new Method[256];
    /**
     * Loads all the methods with {@link Listen} annotation, will be done only once when the class is loaded in runtime
     */
    static {
    	for(Method m : PacketHandler.class.getDeclaredMethods()) {
    		if (m.isAnnotationPresent(Listen.class)) {
    			cache[m.getAnnotation(Listen.class).value()] = m;
    		}
    	}
    }
    /**
     * Client socket
     */
	private Socket socket;
	private DataInputStream input;
	private DataOutputStream output;
	/**
	 * Reconnect data of the client
	 */
	private Reconnect reconnect;
	/**
	 * Incoming RC4 Encryption
	 */
	private RC4 incomingEncryption;
	/**
	 * Outgoing RC4 Encryption
	 */
	private RC4 outgoingEncryption;
	/**
	 * List of {@link PacketHandler}(s) that will handle incoming {@link Packet}(s)
	 */
	private ArrayList<PacketHandler> handlers = new ArrayList<>();
	/**
	 * List of {@link ClientListener}(s) that will handle events from the client
	 */
	private ArrayList<ClientListener> listeners = new ArrayList<>();
	
	private boolean running;
	/**
	 * Creates a new client instance with {@link Reconnect}
	 * @param reconnect
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	public Client(Reconnect reconnect) throws UnknownHostException, IOException {
		this.socket = new Socket(reconnect.getHost(), reconnect.getPort());
		this.socket.setTcpNoDelay(true);
		this.input = new DataInputStream(this.socket.getInputStream());
		this.output = new DataOutputStream(this.socket.getOutputStream());
		this.reconnect = reconnect;
		this.incomingEncryption = new RC4(Util.hexString(NetworkConstants.INCOMING_KEY));
		this.outgoingEncryption = new RC4(Util.hexString(NetworkConstants.OUTGOING_KEY));
	}
	/**
	 * Adds a {@link PacketHandler} to the client
	 * @param handler
	 */
	public void addPacketHandler(PacketHandler handler) {
		this.handlers.add(handler);
	}
	/**
	 * Adds a {@link ClientListener} to the client
	 * @param listener
	 */
	public void addClientListener(ClientListener listener) {
		this.listeners.add(listener);
	}

	@Override
	public void run() {

		this.listeners.forEach(l -> l.connected(this.reconnect));
		
		this.running = true;
		
		while(this.running) {
			try {
				
				// RotMG packet protocol is simple
				
				// the header is 5 bytes, it includes 
				// Length of the packet
				// Type of the packet
				
				// length comes first as a 32 bit integer
				int length = input.readInt();
				// type comes after as a 8 bit integer
				int type = input.readUnsignedByte();

				// rest of the packet bytes are encrypted with RC4
				byte[] buf = new byte[length - 5];
				input.readFully(buf);
				handlePacket(type, this.incomingEncryption.rc4(buf));
				
			} catch (Exception e) {
				e.printStackTrace();
				this.disconnect(1);
				return;
			}
		}
		this.disconnect(0);
	}
	
	/**
	 * Handles a raw packet buffer received from the server
	 * @param type Type of the packet
	 * @param buf Raw packet bytes
	 */
	private void handlePacket(int type, byte[] buf) {
		if (type == Packets.SUCCESS) {
			this.listeners.forEach(l -> l.ready());
		}
		Packet packet = Packets.get(type);
		ByteArrayInputStream bs = new ByteArrayInputStream(buf);
		DataInputStream in = new DataInputStream(bs);
		if (packet != null) {
			try {
				packet.read(in);
				Method m = cache[type];
				if (m != null) {
					for(PacketHandler h : this.handlers) {
						try {
							m.invoke(h, packet);
						} catch(Exception e) {
							e.printStackTrace();
						}
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * Sends a {@link Packet} to the server
	 * @param packet The {@link Packet} that will be sent
	 */
	public void sendPacket(Packet packet) {
		if (!this.isConnected()) return;
		int type = packet.type();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    DataOutputStream out = new DataOutputStream(baos);
	    try {
	    	packet.write(out);
	    } catch(Exception e) {
	    	e.printStackTrace();
	    	return;
	    } finally {
	    	try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }
	    byte[] buffer = this.outgoingEncryption.rc4(baos.toByteArray());

	    try {
	    	// packet size as int
		    output.writeInt(buffer.length + 5);
		    // packet type as byte
		    output.writeByte(type);
		    // packet data
		    output.write(buffer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Returns true if the client socket is connected
	 * 
	 * This might be inaccurate.
	 * 
	 * @return {@linkplain Socket#isConnected()}
	 */
	public boolean isConnected() {
		return socket.isConnected();
	}
	
	/**
	 * Disconnects the client with an exit code
	 * @param exitCode
	 */
	public void disconnect(int exitCode) {
		if (!this.running) return;
		this.running = false;
		try {
			this.socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.listeners.forEach(l -> l.disconnect(exitCode));
	}
}
