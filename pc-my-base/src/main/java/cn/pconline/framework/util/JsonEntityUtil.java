package cn.pconline.framework.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*import org.gelivable.dao.GeliDao;
import org.gelivable.dao.GeliUtils;*/

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
/**
 * 处理json字符串转对象的工具类
 * @author pconline
 *
 */
public class JsonEntityUtil {

	private static final String suffix="###list";		//后缀标记
	
	public static <T> T string2Object(Class<T> type, String value) throws Exception{
		return null;
		/*
		return GeliDao.string2Object(type, value);
	*/}
	
	public static <T> String object2String(T obj) throws Exception{
/*
        return GeliUtils.getDao().object2String(obj);
*/
		return null;
    }
	
	public static <T> List<T> string2List(Class<T> type, String value) throws Exception{
		List<T> list = new ArrayList<T>();
		JSONArray array = JSON.parseArray(value);
		if(array!=null && array.size()>0){
			for(int i=0;i<array.size();i++){
				list.add(string2Object(type,array.getString(i)));
			}
		}
		return list;
	}
	
	public static <T> String list2String(List<T> list) throws Exception{
		if(list!=null && list.size()>0){
			JSONArray array = new JSONArray();
			for(T entity : list){
				array.add(object2String(entity));
			}
			return array.toJSONString();
		}
		return "";
	}
	
	
	@SuppressWarnings("unchecked")
	public static <T, K, V> Map<K,V> string2Map(Class<K> ktype,Class<T> type, String value) throws Exception{
		Map<K,V> result = new HashMap<K,V>();
		HashMap<K,String> map = string2Object(HashMap.class, value);
		for(Map.Entry<K, String> entry : map.entrySet()){
			String key=entry.getKey().toString();
			if(key.endsWith(suffix)){
				result.put(string2Object(ktype,key.substring(0,key.length()-suffix.length())),(V) string2List(type, entry.getValue()));
			}else{
				result.put(entry.getKey(),(V) string2Object(type,entry.getValue()));
			}
		}
		return result;
	}
	
	public static <K,V> String map2String(Map<K,V> map) throws Exception{
        if(map==null){
       		return "";
       	}
        HashMap<Object,String> result = new HashMap<Object,String>();
        for(Map.Entry<K, V> entry : map.entrySet()){
        	if(entry.getValue().getClass().getCanonicalName().equals("java.util.List")){
        		result.put(entry.getKey()+suffix, list2String((List<?>) entry.getValue()));
        	}else{
        		Class<?>[] intfs=entry.getValue().getClass().getInterfaces();
        		if(intfs!=null&&intfs.length>0){
        			for(Class<?> cs:intfs){
        				if("java.util.List".equals(cs.getCanonicalName())){
        					result.put(entry.getKey()+suffix, list2String((List<?>) entry.getValue()));
        					break;
        				}
        			}
        		}else{
        			result.put(entry.getKey(),object2String(entry.getValue()));
        		}
        	}
        }
        return object2String(result);
    }
	
}
