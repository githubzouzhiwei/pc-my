package cn.pconline.pcloud.base.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import org.springframework.data.annotation.Id;


/**
 * 反射工具类
 * @author jzc
 *
 */
public class ReflectUtil {
	/**
	 * 获得主键Id值
	 * @param entity
	 * @return
	 * @throws IllegalAccessException  
	 * @throws IllegalArgumentException 
	 */
	@SuppressWarnings("rawtypes")
	public static long reflectPrimaryAttribute(Object entity) throws IllegalArgumentException, IllegalAccessException {
		Class cls = entity.getClass();
		
		Field[] fields = cls.getDeclaredFields();
		if(fields == null || fields.length == 0){
			return 0;
		}
		
		for (Field field : fields) {
			field.setAccessible(true); // 打开私有访问权限
			
			// 判断是否带主键注解
			Id id = field.getAnnotation(Id.class);
			if(id != null) {
				return (long) field.get(entity);
			}
		}
		
		for (Field field : fields) {
			// 默认名称匹配
			if(field.getName().equals("id") || field.getName().toLowerCase().equals(cls.getSimpleName().toLowerCase() + "id")){
				return (long) field.get(entity);
			}
		}
		
		for (Field field : fields) {
			// 获取非静态的Long类型字段，且其名称为类名后缀的属性值
			if(!Modifier.isStatic(field.getModifiers())
					&& field.getType().getName().equals("java.lang.Long")
					&& (cls.getSimpleName().toLowerCase() + "id").endsWith(field.getName().toLowerCase())){
				return (long) field.get(entity);
			}
		}
		
		return 0;
	}
}
