package cn.pconline.pcloud.base.util;

/**
 * 通用工具类
 * @author jzc
 *
 */
public class HelperUtil {

	/**
	 * 驼峰转下划线格式
	 * @param value
	 * @return
	 */
	public static String toUnderline(String value) {
		  return value.replaceAll("([A-Z])", "_$1").toLowerCase();
	}
	
}
