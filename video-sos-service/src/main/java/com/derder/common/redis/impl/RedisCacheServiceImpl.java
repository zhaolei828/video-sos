package com.derder.common.redis.impl;

import com.derder.common.redis.CacheException;
import com.derder.common.redis.CacheService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service("cacheService")
public class RedisCacheServiceImpl implements CacheService
{
	private final static Logger logger = Logger.getLogger(RedisCacheServiceImpl.class);

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	/**
	 * 批量删除对应的value
	 * 
	 * @param keys
	 */
	public void remove(final String... keys)
	{
		for (String key : keys)
		{
			removeObject(key);
		}
	}

	/**
	 * 批量删除key
	 * 
	 * @param pattern
	 */
	public void removePattern(final String pattern)
	{
		Set<String> keys = redisTemplate.keys(pattern);
		if (keys.size() > 0)
			redisTemplate.delete(keys);
	}

	/**
	 * 删除对应的value
	 * 
	 * @param key
	 */
	public void removeObject(final String key)
	{
		if (exists(key))
		{
			redisTemplate.delete(key);
		}
	}

	/**
	 * 判断缓存中是否有对应的value
	 * 
	 * @param key
	 * @return
	 */
	public boolean exists(final String key)
	{
		return redisTemplate.hasKey(key);
	}

	/**
	 * 写入缓存
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean set(final String key, Object value)
	{
		boolean result = false;
		try
		{
			ValueOperations<String, Object> operations = redisTemplate.opsForValue();
			operations.set(key, value);
			result = true;
		} catch (Exception e)
		{
			logger.error(e.getMessage(), e);
		}
		return result;
	}

	/**
	 * 写入缓存
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean set(final String key, Object value, Long expireTime)
	{
		boolean result = false;
		try
		{
			ValueOperations<String, Object> operations = redisTemplate.opsForValue();
			operations.set(key, value);
			redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
			result = true;
		} catch (Exception e)
		{
			logger.error(e.getMessage(), e);
		}
		return result;
	}

	@Override
	public Long increment(final String key, Long value) throws CacheException
	{
		Long result = 0L;
		try
		{
			Long operations = redisTemplate.opsForValue().increment(key, value);
			result = operations;
		} catch (Exception e)
		{
			logger.error(e.getMessage(), e);
			throw new CacheException("EXC_CACHE_ERROR", e.getMessage());
		}
		return result;
	}

	@Override
	public Long getIncrValue(String key) throws CacheException
	{
		Long result = 0L;
		try
		{
			Long operations = redisTemplate.opsForValue().increment(key, 0L);
			result = operations;
		} catch (Exception e)
		{
			logger.error(e.getMessage(), e);
			throw new CacheException("EXC_CACHE_ERROR", e.getMessage());
		}
		return result;
	}
	
	@Override
	public void add(String key, Object value, int minutes) throws CacheException
	{
		try
		{
			redisTemplate.opsForValue().set(key, value);
			redisTemplate.expire(key, minutes, TimeUnit.MINUTES);
		} catch (Exception ex)
		{
			ex.printStackTrace();
			throw new CacheException("EXC_CACHE_ERROR", ex.getMessage());
		}
	}

	@Override
	public void add(String key, Object value) throws CacheException
	{
		try
		{
			redisTemplate.opsForValue().set(key, value);
		} catch (Exception ex)
		{
			ex.printStackTrace();
			throw new CacheException("EXC_CACHE_ERROR", ex.getMessage());
		}

	}

	@Override
	public void addList(String key, Collection<? extends Object> values) throws CacheException
	{
		try
		{
			if (values != null && values.size() > 0)
			{
				remove(key);

				// redisTemplate.opsForList().leftPushAll(key, values);
				for (Object value : values)
				{
					redisTemplate.opsForList().leftPush(key, value);
				}
			}
		} catch (Exception ex)
		{
			ex.printStackTrace();
			throw new CacheException("EXC_CACHE_ERROR", ex.getMessage());
		}

	}

	@Override
	public void addList(String key, Collection<? extends Object> values, int minutes) throws CacheException
	{
		try
		{
			remove(key);
			// redisTemplate.opsForList().leftPushAll(key, values);
			for (Object value : values)
			{
				redisTemplate.opsForList().leftPush(key, value);
			}
			redisTemplate.expire(key, minutes, TimeUnit.MINUTES);
		} catch (Exception ex)
		{
			ex.printStackTrace();
			throw new CacheException("EXC_CACHE_ERROR", ex.getMessage());
		}
	}

	@Override
	public void addOneToList(String key, Object value) throws CacheException
	{
		try
		{
			redisTemplate.opsForList().leftPush(key, value);
		} catch (Exception ex)
		{
			ex.printStackTrace();
			throw new CacheException("EXC_CACHE_ERROR", ex.getMessage());
		}
	}

	@Override
	public Object get(String key) throws CacheException
	{
		try
		{
			return redisTemplate.opsForValue().get(key);
		} catch (Exception ex)
		{
			ex.printStackTrace();
			throw new CacheException("EXC_CACHE_ERROR", ex.getMessage());
		}
	}

	@Override
	public List<? extends Object> getList(String key) throws CacheException
	{
		try
		{
			Long size = redisTemplate.opsForList().size(key);
			return redisTemplate.opsForList().range(key, 0, size);
		} catch (Exception ex)
		{
			ex.printStackTrace();
			throw new CacheException("EXC_CACHE_ERROR", ex.getMessage());
		}
	}

	@Override
	public Object getListFirstOne(String key) throws CacheException
	{
		try
		{
			List<Object> le = redisTemplate.opsForList().range(key, 0, 1);
			if (le != null && le.size() > 0)
			{
				return le.get(0);
			} else
			{
				return null;
			}
		} catch (Exception ex)
		{
			ex.printStackTrace();
			throw new CacheException("EXC_CACHE_ERROR", ex.getMessage());
		}
	}

	@Override
	public long getCountLike(String keyPrefix) throws CacheException
	{
		try
		{
			if (StringUtils.isEmpty(keyPrefix))
			{
				return 0;
			} else
			{
				Set<String> matchedCacheKeys = redisTemplate.keys(keyPrefix + "*");
				return matchedCacheKeys.size();
				
			}
		} catch (Exception ex)
		{
			ex.printStackTrace();
			throw new CacheException("EXC_CACHE_ERROR", ex.getMessage());
		}
	}

	@Override
	public void remove(String key) throws CacheException
	{
		try
		{
			//redisTemplate.delete(redisTemplate.keys(key));
			if (exists(key))
			{
				redisTemplate.delete(key);
			}
		} catch (Exception ex)
		{
			ex.printStackTrace();
			throw new CacheException("EXC_CACHE_ERROR", ex.getMessage());
		}

	}

	@Override
	public void removeLike(String keyPrefix) throws CacheException
	{
		try
		{
			if (!StringUtils.isEmpty(keyPrefix))
			{
				Set<String> matchedCacheKeys = redisTemplate.keys(keyPrefix + "*");
				for (String cacheKey : matchedCacheKeys)
				{
					this.remove(cacheKey);
				}
			}
		} catch (Exception ex)
		{
			ex.printStackTrace();
			throw new CacheException("EXC_CACHE_ERROR", ex.getMessage());
		}
	}

	@Override
	public int countItemsLike(String keyPrefix) throws CacheException
	{

		try
		{
			if (StringUtils.isEmpty(keyPrefix))
			{
				return 0;
			} else
			{
				Set<String> matchedCacheKeys = redisTemplate.keys(keyPrefix + "*");
				return matchedCacheKeys.size();
			}
		} catch (Exception ex)
		{
			ex.printStackTrace();
			throw new CacheException("EXC_CACHE_ERROR", ex.getMessage());
		}
	}

	@Override
	public void expire(String key, int minutes) throws CacheException
	{
		try
		{
			redisTemplate.expire(key, minutes, TimeUnit.SECONDS);
		} catch (Exception e)
		{
			e.printStackTrace();
			throw new CacheException("EXC_CACHE_ERROR", e.getMessage());
		}
	}
}
