package com.fa.training.group01.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class UrlUtil {
	public static final String REDIRECT_PREFIX = "redirect:";

	public enum Public {
		HOME(PathName.HOME), LOGIN(PathName.LOGIN), REGISTER(PathName.REGISTER),
		RESET_PASSWORD(PathName.RESET_PASSWORD);

		public final String path;

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

	public static final class StudentArea {
		public enum User {
			PROFILE(PathName.PROFILE), UPDATE_PASSWORD(PathName.UPDATE_PASSWORD);

			public static final String PATH = "/user";
			public final String path;

			User(String path) {
				this.path = path;
			}

			public static final class PathName {
				public static final String PROFILE = "";
				public static final String UPDATE_PASSWORD = "/update-password";
			}
		}
	}

	public static final class AdminArea {
		public static final String PATH = "/admin";

		public enum User {
			ACCOUNT_LIST(PathName.ACCOUNT_LIST);

			public static final String PATH = "/user";
			public final String path;

			User(String path) {
				this.path = path;
			}

			public static final class PathName {
				public static final String ACCOUNT_LIST = "";
			}
		}
	}

}
