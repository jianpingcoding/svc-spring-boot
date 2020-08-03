package org.ganjp.api.core.model;

import lombok.Data;

@Data
public class ApplicationConfig {
	private static String applicationName;

	public static void setApplicationName(String name) {
		applicationName = name;
	}

	public static String getApplicationName() {
		return applicationName;
	}
}
