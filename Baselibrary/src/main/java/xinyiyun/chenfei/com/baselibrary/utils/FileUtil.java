package xinyiyun.chenfei.com.baselibrary.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.Environment;
import android.text.TextUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class FileUtil {


	/** 分隔符. */
	public final static String FILE_EXTENSION_SEPARATOR = ".";
	/**
	 * 将Bitmap 图片保存到本地路径，并返回路径
	 * @param c
	 * //@param mType 资源类型，参照  MultimediaContentType 枚举，根据此类型，保存时可自动归类
	 * @param fileName 文件名称
	 * @param bitmap 图片
	 * @return
	 */
	public static String saveFile(Context c, String fileName, Bitmap bitmap) {
		return saveFile(c, "", fileName, bitmap);
	}
	
	public static String saveFile(Context c, String filePath, String fileName, Bitmap bitmap) {
		byte[] bytes = bitmapToBytes(bitmap);
		return saveFile(c, filePath, fileName, bytes);
	}
	
	public static byte[] bitmapToBytes(Bitmap bm) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bm.compress(CompressFormat.JPEG, 100, baos);
		return baos.toByteArray();
	}
	
	public static String saveFile(Context c, String filePath, String fileName, byte[] bytes) {
		String fileFullName = "";
		FileOutputStream fos = null;
		String dateFolder = new SimpleDateFormat("yyyyMMdd", Locale.CHINA)
				.format(new Date());
		try {
			String suffix = "";
			if (filePath == null || filePath.trim().length() == 0) {
				filePath = Environment.getExternalStorageDirectory() + "/JiaXT/" + dateFolder + "/";
			}
			File file = new File(filePath);
			if (!file.exists()) {
				file.mkdirs();
			}
			File fullFile = new File(filePath, fileName + suffix);
			fileFullName = fullFile.getPath();
			fos = new FileOutputStream(new File(filePath, fileName + suffix));
			fos.write(bytes);
		} catch (Exception e) {
			fileFullName = "";
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					fileFullName = "";
				}
			}
		}
		return fileFullName;
	}

	/**
	 * 删除指定目录中特定的文件
	 * @param dir
	 * @param filter
	 */
	public static void delete(String dir, FilenameFilter filter) {
		if (TextUtils.isEmpty(dir))
			return;
		File file = new File(dir);
		if (!file.exists())
			return;
		if (file.isFile())
			file.delete();
		if (!file.isDirectory())
			return;

		File[] lists = null;
		if (filter != null)
			lists = file.listFiles(filter);
		else
			lists = file.listFiles();

		if (lists == null)
			return;
		for (File f : lists) {
			if (f.isFile()) {
				f.delete();
			}
		}
	}

	/**
	 * 获得不带扩展名的文件名称
	 * @param filePath 文件路径
	 * @return
	 */
	public static String getFileNameWithoutExtension(String filePath) {
		if (TextUtils.isEmpty(filePath)) {
			return filePath;
		}
		int extenPosi = filePath.lastIndexOf(FILE_EXTENSION_SEPARATOR);
		int filePosi = filePath.lastIndexOf(File.separator);
		if (filePosi == -1) {
			return (extenPosi == -1 ? filePath : filePath.substring(0,
					extenPosi));
		}
		if (extenPosi == -1) {
			return filePath.substring(filePosi + 1);
		}
		return (filePosi < extenPosi ? filePath.substring(filePosi + 1,
				extenPosi) : filePath.substring(filePosi + 1));
	}
	/**
	 * 判断SD卡是否可用
	 * @return SD卡可用返回true
	 */
	public static boolean hasSdcard() {
		String status = Environment.getExternalStorageState();
		return Environment.MEDIA_MOUNTED.equals(status);
	}
}
