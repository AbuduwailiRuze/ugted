package com.bilig.ugted.util;

import java.io.File;
import java.io.IOException;

import android.os.Environment;

public class CommonUtils {

	public static String getNameFromUrl(String url) {
		String name = url;
		// Http://www/wwwdd/
		int start = name.lastIndexOf("/");
		name = name.substring(start + 1);
		return name;
	}

	public static File createSDFile(String fileName) throws IOException {
		File file = new File(fileName);
		file.createNewFile();
		return file;
	}

	public static File createSDDir(String dirName) {
		File dir = new File(dirName);
		if (!dir.exists())
			if (Environment.getExternalStorageState().equals(
					Environment.MEDIA_MOUNTED)) {

				System.out.println("createSDDir:" + dir.getAbsolutePath());
				System.out.println("createSDDir:" + dir.mkdir());
			}
		return dir;
	}

}
