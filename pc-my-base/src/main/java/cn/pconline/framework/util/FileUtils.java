package cn.pconline.framework.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class FileUtils {

	/**
	 * 根据地址获得文件的数据流
	 *
	 * @param uri 网络连接地址
	 * @return
	 * @throws IOException 
	 */
	public static InputStream getFileStream(String uri) throws IOException {
		URL url = new URL(uri);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setConnectTimeout(5 * 1000);
        return conn.getInputStream();
	}
	
	/**
	 * 从输入流中获取数据
	 *
	 * @param inStream 输入流
	 * @return
	 * @throws Exception
	 */
	public static byte[] readInputStream(InputStream inStream) throws Exception {
	    ByteArrayOutputStream outStream = new ByteArrayOutputStream();
	    byte[] buffer = new byte[1024];
	    int len = 0;
	    while ((len = inStream.read(buffer)) != -1) {
	        outStream.write(buffer, 0, len);
	    }
	    inStream.close();
	    return outStream.toByteArray();
	}
	
	/**
	 * 生成文件到本地
	 *
	 * @param bt      文件字节流
	 * @param fileName 文件保存时的名称
	 * @throws IOException 
	 */
	public static void createFile(byte[] bt, String fileName) throws IOException {
        File file = new File(fileName);
        if (!file.exists()) {
            file.createNewFile();
        }
        FileOutputStream fops = new FileOutputStream(file);
    	fops.write(bt);
        fops.flush();
        fops.close();
	}
	
	
	public static void main(String[] args) {
	    String url = "http://local.pconline.com.cn:9051/data/report/abc-1.pdf";
	    try {
		    InputStream is = getFileStream(url); // 获取数据流
		    byte[] bt = readInputStream(is); // 读取字节流
		    
		    if (null != bt && bt.length > 0) {
				createFile(bt, "/data/chunya/pdf/abc-0324.pdf"); // 生成文件到本地
		    	System.out.println("## File 'abc-0324.pdf' generated successfully!");
		    }
	    } catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
