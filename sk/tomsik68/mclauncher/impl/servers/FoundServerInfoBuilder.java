package sk.tomsik68.mclauncher.impl.servers;

import java.util.HashMap;

import sk.tomsik68.mclauncher.api.servers.FoundServerInfo;
import sk.tomsik68.mclauncher.api.servers.IServerFinder;

/**
 * Builder class for FoundServerInfo. May be used in server finders
 */
final class FoundServerInfoBuilder {
	private String motd, ip, icon;
	private int port;
	private IServerFinder finder;
	private HashMap<String, Object> properties = new HashMap<String, Object>();

	public FoundServerInfo build() {
		return new FoundServerInfo(this.finder, this.ip, this.icon, this.port, this.motd, this.properties);
	}

	public FoundServerInfoBuilder finder(IServerFinder sf) {
		this.finder = sf;
		return this;
	}

	/**
	 *
	 * @param ico
	 *            - Icon image encoded as base64 string
	 * @return <code>this</code> for chaining
	 */
	public FoundServerInfoBuilder icon(String ico) {
		this.icon = ico;
		return this;
	}

	public FoundServerInfoBuilder ip(String s) {
		this.ip = s;
		return this;
	}

	public FoundServerInfoBuilder motd(String s) {
		this.motd = s;
		return this;
	}

	public FoundServerInfoBuilder port(int p) {
		this.port = p;
		return this;
	}

	public FoundServerInfoBuilder property(String key, Object value) {
		this.properties.put(key, value);
		return this;
	}

}
