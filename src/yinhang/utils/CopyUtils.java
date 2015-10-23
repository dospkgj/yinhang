package yinhang.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;

public class CopyUtils {
	
	public static String copyAssetFileToDatabase(Context c, String fromFile,
			String toFile) {
		InputStream ips = null;
		try {
			ips = c.getAssets().open(fromFile);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		File f = c.getDatabasePath(toFile);
		File dir = new File(f.getParent());
		if (!dir.exists()) {
			dir.mkdir();
		}
		if (!f.exists()) {
			try {
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			OutputStream ops = new FileOutputStream(f);
			copyStream(ips, ops);

			return f.getPath();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static File copyStreamToDatabase(Context c, InputStream ips,
			String fileName) {

		File f = c.getDatabasePath(fileName);
		File dir = new File(f.getParent());
		if (!dir.exists()) {
			dir.mkdir();
		}
		if (!f.exists()) {
			try {
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			OutputStream ops = new FileOutputStream(f);
			copyStream(ips, ops);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return f;
	}

	public static void copyStream(InputStream ips, OutputStream ops)
			throws IOException {
		byte[] b = new byte[1024];
		int len = -1;
		while ((len = ips.read(b)) != -1) {
			ops.write(b, 0, len);
		}
		ips.close();
		ops.close();
	}

}
