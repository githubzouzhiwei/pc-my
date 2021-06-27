package cn.pconline.pcloud.base.service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import cn.pconline.framework.util.SpringCtxUtils;
import cn.pconline.pcloud.base.config.BaseProperties;
import cn.pconline.pcloud.base.util.RedisTemplateUtil;
import cn.pconline.pcloud.base.util.ReflectUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 服务基类
 * @author jzc
 *
 * @param <T> Entity
 * @param <E> Mapper
 */
public abstract class AbstractService<T, E> {

    protected final static Logger LOG = LoggerFactory.getLogger(AbstractService.class);

    protected Class<T> type;

    protected Class<E> mapperType;
    
    @Autowired
	public RedisTemplateUtil redisTemplateUtil;
    
    @Autowired
    public BaseProperties baseProperties;

    /**
     * 
     * @param type
     * @param mapperType
     */
    protected AbstractService(Class<T> type, Class<E> mapperType) {
        this.type = type;
        this.mapperType = mapperType;
    }

    /**
     * 根据主键查询
     *
     * @param id
     * @return
     */
    public T find(Long id) {
    	String rdKey = this.getRedisKey(id);
    	Object rdObj = redisTemplateUtil.get(rdKey);
		if(rdObj != null && this.type.isInstance(rdObj)){
			return this.type.cast(rdObj);
		}
    	
    	E commonMapper = SpringCtxUtils.getBean(this.mapperType);
        Method method = null;
        T entity = null;
        try {
            method = commonMapper.getClass().getMethod("selectByPrimaryKey", Long.class);
            Object o = method.invoke(commonMapper, id);
            
            if(o != null && this.type.isInstance(o)){
            	entity = this.type.cast(o);
            	redisTemplateUtil.set(rdKey, entity, 24 * 3600);
            }
        } catch (NoSuchMethodException e) {
        	LOG.error(type.getTypeName()+"类缺少selectByPrimaryKey方法", e);
            e.printStackTrace();
        } catch (IllegalAccessException e) {
        	LOG.error(type.getTypeName()+".selectByPrimaryKey 参数错误",e);
            e.printStackTrace();
        } catch (InvocationTargetException e) {
        	LOG.error(type.getTypeName()+".selectByPrimaryKey 执行异常",e);
            e.printStackTrace();
        }
        return entity;
    }
    
    /**
     * 新增
     * @param entity
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int create(T entity){
    	int result = 0;
    	E commonMapper = SpringCtxUtils.getBean(this.mapperType);
        Method method = null;
    	
        try {
			method = commonMapper.getClass().getMethod("insertSelective", this.type);
			Object o = method.invoke(commonMapper, entity);
			
			if(o != null){
				result = (int) o;
			}
		} catch (NoSuchMethodException e) {
			LOG.error(type.getTypeName()+"类缺少insertSelective方法", e);
			e.printStackTrace();
		} catch (IllegalAccessException e) {
        	LOG.error(type.getTypeName()+".insertSelective 参数错误", e);
            e.printStackTrace();
        } catch (InvocationTargetException e) {
			LOG.error(type.getTypeName()+".insertSelective 执行异常", e);
            e.printStackTrace();
        }
    	
    	return result;
    }
    
    /**
     * 修改（根据主键）
     * @param entity
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int update(T entity){
    	int result = 0;
    	E commonMapper = SpringCtxUtils.getBean(this.mapperType);
        Method method = null;
    	
        try {
			method = commonMapper.getClass().getMethod("updateByPrimaryKeySelective", this.type);
			Object o = method.invoke(commonMapper, entity);
			
			if(o != null){
				result = (int) o;
				// 删除旧缓存
				String rdKey = this.getRedisKey(ReflectUtil.reflectPrimaryAttribute(entity));
                System.out.println("rdKey==>" + rdKey);
				redisTemplateUtil.del(rdKey);
			}
		} catch (NoSuchMethodException e) {
			LOG.error(type.getTypeName()+"类缺少updateByPrimaryKeySelective方法", e);
			e.printStackTrace();
		} catch (IllegalAccessException e) {
        	LOG.error(type.getTypeName()+".updateByPrimaryKeySelective 参数错误", e);
            e.printStackTrace();
        } catch (InvocationTargetException e) {
			LOG.error(type.getTypeName()+".updateByPrimaryKeySelective 执行异常", e);
            e.printStackTrace();
        }
    	
    	return result;
    }
    
    /**
     * 删除
     * @param id
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int delete(Long id) {
    	E commonMapper = SpringCtxUtils.getBean(this.mapperType);
        Method method = null;
        int result = 0;
        try {
            method = commonMapper.getClass().getMethod("deleteByPrimaryKey", Long.class);
            Object o = method.invoke(commonMapper, id);
            
            if(o != null){
            	result = (int) o;
            	// 删除旧缓存
            	String rdKey = this.getRedisKey(id);
				redisTemplateUtil.del(rdKey);
            }
        } catch (NoSuchMethodException e) {
        	LOG.error(type.getTypeName()+"类缺少deleteByPrimaryKey方法", e);
            e.printStackTrace();
        } catch (IllegalAccessException e) {
        	LOG.error(type.getTypeName()+".deleteByPrimaryKey 参数错误", e);
            e.printStackTrace();
        } catch (InvocationTargetException e) {
        	LOG.error(type.getTypeName()+".deleteByPrimaryKey 执行异常", e);
            e.printStackTrace();
        }
        return result;
    }
    
    private String getRedisKey(long id){
    	return baseProperties.getApplicationName() + "#" + this.type.getSimpleName().toUpperCase() + "_" + id;
    }

}
