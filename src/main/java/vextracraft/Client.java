package vextracraft;

public class Client {

	private static final Client INSTANCE = new Client();
	public static final Client getInstance() {
		return INSTANCE;
	}
	private DiscordRP discordRP = new DiscordRP();
	public void init() {
		discordRP.start();
	}
	public void shutdown() {
		discordRP.shutdown();
	}
}
