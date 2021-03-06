package sk.tomsik68.mclauncher.impl.common;

import java.io.File;

import sk.tomsik68.mclauncher.api.common.IOperatingSystem;
import sk.tomsik68.mclauncher.api.common.MCLauncherAPI;

final class LinuxOS implements IOperatingSystem {
	private File workDir;

	@Override
	public String getArchitecture() {
		return System.getProperty("sun.arch.data.model");
	}

	@Override
	public String getDisplayName() {
		return "Linux/Unix";
	}

	@Override
	public String getMinecraftName() {
		return "linux";
	}

	// Minecraft on *Nix systems will live defaultly in ~/.minecraft
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
		String os = System.getProperty("os.name").toLowerCase();
		return os.contains("unix") || os.contains("linux");
	}

}
