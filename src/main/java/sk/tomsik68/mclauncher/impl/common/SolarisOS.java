package sk.tomsik68.mclauncher.impl.common;

import java.io.File;

import sk.tomsik68.mclauncher.api.common.IOperatingSystem;
import sk.tomsik68.mclauncher.api.common.MCLauncherAPI;

final class SolarisOS implements IOperatingSystem {
	private File workDir;

	@Override
	public String getArchitecture() {
		return System.getProperty("sun.arch.data.model");
	}

	@Override
	public String getDisplayName() {
		return "Solaris/Sun OS";
	}

	@Override
	public String getMinecraftName() {
		return "solaris";
	}

	@Override
	public File getWorkingDirectory() {
		if (workDir != null) {
			return workDir;
		}
		String userHome = System.getProperty("user.home");
		workDir = new File(userHome, ".minecraft");
		MCLauncherAPI.log.fine("Minecraft working directory: ".concat(workDir.getAbsolutePath()));
		return workDir;
	}

	@Override
	public boolean isCurrent() {
		String osName = System.getProperty("os.name").toLowerCase();
		return osName.contains("solaris") || osName.contains("sunos");
	}

}
