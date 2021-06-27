package cn.pconline.pcloud.base.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

/**
 * 文件工具类
 * @author su*
 *
 */
public class FileUtil {

	/**
	 * 将PDF转换成PNG
	 * @param path
	 * @param type 图片类型
	 * @return
	 */
    public static int pdfToImg(String path, String type) {
        File file = new File(path);
        String outPathI, outPath = path.replace(".pdf", "_{0}." + type).replace("/pdf/", "/" + type + "/");
        int pageSize = 0;
        
        try {
            PDDocument doc = PDDocument.load(file);
            PDFRenderer renderer = new PDFRenderer(doc);
            pageSize = doc.getNumberOfPages();
            
            for (int i = 0; i < pageSize; i++) {
                BufferedImage image = renderer.renderImageWithDPI(i, 144);
                outPathI = outPath.replace("{0}", (i < 9 ? "0" : "") + (i+1));
                
                System.out.println(outPathI);
                ImageIO.write(image, type, new File(outPathI));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return pageSize;
    }
    
    /**
	  * 将图片转换成WEBP格式 cwebp -q 75 input_file -o output_file
	  * @param inputFile 原图路径
	  * @param outputFile 输出路径
	  * @param quality 图片精度 [1-100]
	  * @param cwebp 转换器路径
	  * @return
	  */
	public static boolean imgToWebp(String inputFile, String outputFile, Integer quality, String cwebp) {
		if (!new File(inputFile).exists()) {
            return false;
        }
		
	     boolean result = false;
	
	     try {
	         StringBuilder command = new StringBuilder(cwebp);
	         command.append(" -q " + (quality == 0 ? 75 : quality));
	         command.append(" " + inputFile);
	         command.append(" -o " + outputFile);
	         
	         System.out.println(command.toString());
	         Runtime.getRuntime().exec(command.toString());
	         result = true;
	     } catch (Exception e) {
	         e.printStackTrace();
	     }
	     return result;
	 }
    
}
