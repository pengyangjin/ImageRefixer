import java.io.File;
import java.util.Collection;

import org.apache.commons.io.FileUtils;

public class Main {

	public static void main(String[] args) {
		// String in = "C:\\Users\\yangjin1\\Desktop\\04.jpg";
		// String out = "C:\\Users\\yangjin1\\Desktop\\a04.jpg";

		// Utils.unpackJpg(in, out);

		String uri = "C:\\Users\\yangjin1\\Downloads\\sgqyz\\assets";
		//String uri2 = "C:\\Users\\yangjin1\\Desktop\\4\\files\\y2_data\\down_patch";

		Collection<File> files;
		files = FileUtils.listFiles(new File(uri), new String[] { "png", "jpg" }, true);

		for (File f : files) {
			String in = f.getPath();
			// String out = in.replace("down_patch", "down_patch2");
			String out = in.replace("assets", "assets2");
			System.out.println(in);

			Utils.ImageType type = null;
			
			if (in.endsWith(".jpg")) {
				type = Utils.ImageType.JPG;
			} else if (in.endsWith(".png")) {
				type = Utils.ImageType.PNG;
			}

			Utils.unpack(in, out, type);
		}
		System.out.println("ok.");
	}

}
