package vextracraft;

import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.util.ResourceLocation;

public class ServerDataFeatured extends ServerData {
	public static final ResourceLocation STAR_ICON = new ResourceLocation("vextracraft/star.png");

	public ServerDataFeatured(String name, String ip, boolean isLan) {
		super(name, ip, isLan);
	}
}
