package experimentalPhysics.network.packets;

import io.netty.buffer.ByteBuf;

import cpw.mods.fml.common.network.simpleimpl.IMessage;

public class PacketSyncAdvancedRefiner extends PacketCoords implements IMessage
{
	public boolean formed;
	public short progress;
	public short refiningSpeed;
	public float heat;
	public short minHeat;
	public short maxHeat;
	public float dustChance;
	
	public PacketSyncAdvancedRefiner() {}

	public PacketSyncAdvancedRefiner(int x, int y, int z, boolean formed, short progress, short refiningSpeed, float heat, short minHeat, short maxHeat, float dustChance)
	{
		super(x, y, z);
		this.formed = formed;
		this.progress = progress;
		this.refiningSpeed = refiningSpeed;
		this.heat = heat;
		this.minHeat = minHeat;
		this.maxHeat = maxHeat;
		this.dustChance = dustChance;
	}

	@Override
	public void toBytes(ByteBuf buf)
	{
		super.toBytes(buf);
		buf.writeBoolean(formed);
		buf.writeInt(progress);
		buf.writeInt(refiningSpeed);
		buf.writeFloat(minHeat);
		buf.writeFloat(maxHeat);
		buf.writeFloat(dustChance);
	}

	@Override
	public void fromBytes(ByteBuf buf)
	{
		super.fromBytes(buf);
		formed = buf.readBoolean();
		progress = buf.readShort();
		refiningSpeed = buf.readShort();
		heat = buf.readFloat();
		minHeat = buf.readShort();
		maxHeat = buf.readShort();
		dustChance = buf.readFloat();
	}
}
