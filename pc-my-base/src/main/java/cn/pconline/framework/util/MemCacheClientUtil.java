package cn.pconline.framework.util;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

/*
import com.danga.MemCached.MemCachedClient;
*/

/**
 * 调用MemCacheClient处理的封装工具类 
 * @author pconline
 *
 */
public class MemCacheClientUtil {

	/*MemCachedClient mcc;

	public void setMemCachedClient(MemCachedClient mcc) {
		this.mcc = mcc;
	}

	public Object get(String key) {
		return mcc.get(key);
	}
	
	public MemCacheClientUtil(MemCachedClient mcc){
		this.mcc = mcc;
	}
	
	*//**
	 * 获取解压缩后的值 
	 *//*
	public String getComValue(String key,String encode) {
		String value=(String) mcc.get(key);
		try {
			value = ZipUtil.uncompress(value,encode);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return value;
	}

	public Map<String, Object> getMulti(String[] keys) {
		return mcc.getMulti(keys);
	}

	public Object[] getMultiArray(String[] keys) {
		return mcc.getMultiArray(keys);
	}

	public boolean set(String key,Object value) {
		return mcc.set(key, value);
	}
	
	*//**
	 * 存储压缩后的值 
	 *//*
	public boolean set(String key,String value,String encode) {
		try {
			value=ZipUtil.compress(value, encode);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return mcc.set(key, value);
	}

	public boolean delete(String key) {
		return mcc.delete(key);
	}
	
	public boolean set(String key, String value, Date expiryAt) {
		return mcc.set(key, value, expiryAt);
	}

	public boolean set(String key, String value, long milliSecond) {
		return mcc.set(key,value, new Date(milliSecond));
	}
	
	*//**
	 * 存储压缩后的值 
	 *//*
	public boolean setComValue(String key, String value, Date expiryAt,String encode) {
		try {
			value=ZipUtil.compress(value, encode);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return mcc.set(key, value, expiryAt);
	}
	
	*//**
	 * 存储压缩后的值 
	 *//*
	public boolean setComValue(String key, String value, long milliSecond,String encode) {
		try {
			value=ZipUtil.compress(value, encode);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return mcc.set(key,value, new Date(milliSecond));
	}*/
}
