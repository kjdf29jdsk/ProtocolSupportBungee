package protocolsupport.protocol.transformer.v_1_4_1_5_1_6_core.packets;

import io.netty.buffer.ByteBuf;
import protocolsupport.protocol.transformer.TransformedPacket;
import protocolsupport.protocol.transformer.v_1_4_1_5_1_6_core.PacketDataSerializer;
import net.md_5.bungee.EncryptionUtil;
import net.md_5.bungee.protocol.packet.EncryptionRequest;

public class EncryptionRequestPacket extends EncryptionRequest implements TransformedPacket {

	private String serverId;
	private byte[] publicKey = EncryptionUtil.keys.getPublic().getEncoded();
	private byte[] verifyToken;

	public EncryptionRequestPacket() {
	}

	public EncryptionRequestPacket(String serverId, byte[] verifyToken) {
		this.serverId = serverId;
		this.verifyToken = verifyToken.clone();
	}

	@Override
	public void read(ByteBuf buf) {
		serverId = PacketDataSerializer.readString(buf);
		publicKey = PacketDataSerializer.readArray(buf);
		verifyToken = PacketDataSerializer.readArray(buf);
	}

	@Override
	public void write(ByteBuf buf) {
		PacketDataSerializer.writeString(serverId, buf);
		PacketDataSerializer.writeArray(publicKey, buf);
		PacketDataSerializer.writeArray(verifyToken, buf);
	}

	@Override
	public String getServerId() {
		return this.serverId;
	}

	@Override
	public byte[] getPublicKey() {
		return this.publicKey;
	}

	@Override
	public byte[] getVerifyToken() {
		return this.verifyToken;
	}

	@Override
	public void setServerId(final String serverId) {
		this.serverId = serverId;
	}

	@Override
	public void setPublicKey(final byte[] publicKey) {
		this.publicKey = publicKey;
	}

	@Override
	public void setVerifyToken(final byte[] verifyToken) {
		this.verifyToken = verifyToken;
	}

	@Override
	public boolean shouldWrite() {
		return true;
	}

	@Override
	public int getId() {
		return 0xFD;
	}

}
