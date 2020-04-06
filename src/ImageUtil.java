import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;

/**
 * 图片处理
 * @author yangjin1
 *
 */
public class ImageUtil {

	//加密的替换串，取决于具体的算法。
	private static int[] JPG_HEAD = new int[] { 0xff, 0xd8, 0xff, 0xe0, 0x00, 0x10, 0x4a, 0x46 };
	private static int[] PNG_HEAD = new int[] { 0x89, 0x50, 0x4e, 0x47, 0x0d, 0x0a, 0x1a, 0x0a };
	
	public static enum ImageType {
		PNG, JPG
	};

	/**
	 * 解码加密的图片文件
	 * @param in 源路径
	 * @param out 输出路径
	 * @param type 文件类型
	 */
	public static void unpack(String in, String out, ImageType type) {
		String pathStr = in;
		Path p = Paths.get(pathStr);
		byte[] buf;
		try {
			buf = Files.readAllBytes(p);

			int[] HEAD = null;
			switch (type) {
			case JPG:
				HEAD = JPG_HEAD;
				break;
			case PNG:
				HEAD = PNG_HEAD;
				break;
			default:
				HEAD = new int[] {};
				break;
			}

			for (int i = 0; i < HEAD.length; i++) {
				int a = HEAD[i];
				buf[i] = (byte) a;
			}

			/*
			int position = 0;
			for (int i = 0; i < 8; i++) {
				position = i;
				int value = (int) buf[position] & 0xFF;
				System.out.println(Integer.toHexString(value));
			}*/

			FileUtils.writeByteArrayToFile(new File(out), buf);
		} catch (IOException e) {
			throw new Error(e);
		}
	}
}
