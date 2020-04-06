import java.io.File;
import java.io.IOException;
import java.util.Collection;

import org.apache.commons.io.FileUtils;

public class Main {

	public static void main(String[] args) {
		//输入和输出
		//String inPath = "/Users/yangjin1/Downloads/assets";
		if(args.length < 1)
		{
			System.out.println("请输入路径");
			return;
		}
		String inPath = args[0];
		File inFile = new File(inPath);
		if(!inFile.exists())
		{
			System.out.println("源文件不存在");
			return;
		}
		String outDir;
		String outPath;
		ImageUtil.ImageType imageType = null;
		//判断输入是一个文件还是一个目录
		if(inFile.isDirectory())//如果是一个目录
		{
			//如果是一个目录的话 就 构建一个新的输出路径 原来的路径上 添加一个后缀
			outDir = inFile.getParent() +File.separator + "FFOutput" +File.separator;
			System.out.println(outDir);
			
			File outFile=new File(outDir);
			if(!outFile.exists())
			{
				outFile.mkdirs();
			}else {
				try {
					FileUtils.cleanDirectory(outFile);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			Collection<File> files;
			files = FileUtils.listFiles(inFile, new String[] { "png", "jpg" }, true);
			for (File f : files) {
				outPath = f.getPath().replace(inPath, outDir);
				System.out.println(outPath);
				if (f.getName().toLowerCase().endsWith(".jpg")) {
					imageType = ImageUtil.ImageType.JPG;
				} else if (f.getName().toLowerCase().endsWith(".png")) {
					imageType = ImageUtil.ImageType.PNG;
				}
				ImageUtil.unpack(f.getPath(), outPath, imageType);
			}
		}else if(inFile.isFile()) {//如果是一个文件
			//如果是一个目录的话 就 构建一个新的输出路径 原来的路径上 添加一个后缀
			outDir = inFile.getParent() +File.separator + "FFOutput" +File.separator;
			outPath = outDir + inFile.getName();
			System.out.println(outPath);
			if (inFile.getName().toLowerCase().endsWith(".jpg")) {
				imageType = ImageUtil.ImageType.JPG;
			} else if (inFile.getName().toLowerCase().endsWith(".png")) {
				imageType = ImageUtil.ImageType.PNG;
			}
			ImageUtil.unpack(inPath, outPath, imageType);
		}else {//如果是其他
		
		}
		System.out.println("转换完毕.");
		
	}

}
