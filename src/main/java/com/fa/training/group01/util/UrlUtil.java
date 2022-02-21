package com.fa.training.group01.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class UrlUtil {
	public static final String REDIRECT_PREFIX = "redirect:";

	public enum Public {
		HOME(PathName.HOME), LOGIN(PathName.LOGIN), REGISTER(PathName.REGISTER),
		RESET_PASSWORD(PathName.RESET_PASSWORD);

		private final String path;

		Public(String path) {
			this.path = path;
		}

		@UtilityClass
		public static final class PathName {
			public static final String HOME = "/";
			public static final String LOGIN = "/login";
			public static final String REGISTER = "/register";
			public static final String RESET_PASSWORD = "/reset-password";
		}

		@Override
		public String toString() {
			return path;
		}
	}

}
