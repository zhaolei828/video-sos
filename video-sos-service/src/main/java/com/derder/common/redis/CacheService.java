package com.derder.common.redis;

import java.util.Collection;
import java.util.List;

/**
 * 缓存组件-缓存接口
 * @author 
 *
 */
public interface CacheService {

	/**
	 * 添加对象到缓存
	 * @param key 缓存key
	 * @param value 缓存对象
	 * @param minutes 过期时间
	 * @throws CacheException
	 */
	public void add(String key, Object value, int minutes)throws CacheException;
	
	/**
	 * 添加对象到缓存
	 * @param key 缓存key
	 * @param value 缓存对象
	 * @throws CacheException
	 */
	public void add(String key, Object value)throws CacheException;
	
	/**
	 * 添加集合到缓存
	 * @param key 缓存key
	 * @param values 缓存对象
	 * @throws CacheException
	 */
	public void addList(String key, Collection<? extends Object> values)throws CacheException;
	
	/**
	 * 添加集合到缓存
	 * @param key 缓存key
	 * @param values 缓存对象
	 * @param minutes 过期时间
	 * @throws CacheException
	 */
	public void addList(String key, Collection<? extends Object> values, int minutes)throws CacheException;
	
	/**
	 * 添加对象到缓存集合中
	 * @param key 缓存key
	 * @param value 缓存对象
	 * @throws CacheException
	 */
	public void addOneToList(String key, Object value)throws CacheException;
	
	/**
	 * 获取缓存对象
	 * @param key 缓存key
	 * @return
	 * @throws CacheException
	 */
	public Object get(String key)throws CacheException;
	
	/**
	 * 获取缓存列表
	 * @param key 缓存key
	 * @return
	 * @throws CacheException
	 */
	public List<? extends Object> getList(String key)throws CacheException;
	
	/**
	 * 获取列表
	 * @param key
	 * @return
	 * @throws CacheException
	 */
	public Object getListFirstOne(String key)throws CacheException;
	
	/**
	 * 获得缓存数量
	 * @param keyPrefix key前缀
	 * @return
	 * @throws CacheException
	 */
	public long getCountLike(String keyPrefix)throws CacheException;
	
	/**
	 * 删除缓存
	 * @param key 缓存key
	 * @throws CacheException
	 */
	public void remove(String key)throws CacheException;
	
	/**
	 * 模糊删除缓存
	 * @param keyPrefix 缓存前缀
	 * @throws CacheException
	 */
	public void removeLike(String keyPrefix)throws CacheException;
	
	/**
	 * 
	 * @param keyPrefix
	 * @return
	 */
	public int countItemsLike(String keyPrefix);
	
	/**
	 * @param key
	 * @param value
	 * @return
	 */
	public Long increment(final String key, Long value);

	/**
	 * @param key
	 * @return
	 * @throws CacheException
	 */
	public Long getIncrValue(String key) throws CacheException;
	
	void expire(String key, int minutes) throws CacheException;
	
	public boolean exists(final String key);
}