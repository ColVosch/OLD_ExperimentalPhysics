package experimentalPhysics.tileEntitys;

import experimentalPhysics.util.Position;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityBasic extends TileEntity
{
	@Override
    public S35PacketUpdateTileEntity getDescriptionPacket() 
	{
        NBTTagCompound tag = new NBTTagCompound();
        this.writeToNBT(tag);
        return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, tag);
    }
	
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt)
    {
		if (worldObj.isRemote)
		{
			this.readFromNBT(pkt.func_148857_g());
		}
    }
	
	public Position getPosition()
	{
		return new Position(xCoord, yCoord, zCoord);
	}
}
