package com.sandu.j2cache;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import com.jfinal.kit.LogKit;
import com.jfinal.plugin.ehcache.IDataLoader;

import net.oschina.j2cache.CacheChannel;
import net.oschina.j2cache.CacheObject;

public class J2CacheKit {
	
	private static CacheChannel cache;

	static void init(CacheChannel cache) {
		J2CacheKit.cache = cache;
	}
	
	public static CacheChannel getCacheChannel(){
		return cache;
	}

	@SuppressWarnings("unchecked")
	public static <T> T get(String cacheName, Object key) {
		try {
			CacheObject cacheObject = cache.get(cacheName, key.toString());
			return cacheObject != null ? (T) cacheObject.getValue() : null;
		} catch (IOException e) {
			LogKit.error(e.toString(), e);
		}
		return null;
	}

	public static void put(String cacheName, Object key, Object value) {
		try {
			cache.set(cacheName, key.toString(), (Serializable) value);
		} catch (IOException e) {
			LogKit.error(e.toString(), e);
		}
	}

	public static void remove(String cacheName, String... keys) {
		try {
			cache.evict(cacheName, keys);
		} catch (IOException e) {
			LogKit.error(e.toString(), e);
		}
	}

	public static void removeAll(String cacheName) {
		try {
			cache.clear(cacheName);
		} catch (IOException e) {
			LogKit.error(e.toString(), e);
		}
	}

	
	@SuppressWarnings("unchecked")
	public static <T> T get(String cacheName, Object key, IDataLoader dataLoader) {
		Object value = get(cacheName, key);
		if (value == null) {
			value = dataLoader.load();
			if (value != null) {
				put(cacheName, key, value);
			}
		}
		return (T) value;
	}
	

	public static Collection<String> getKeys(String cacheName) {
		try {
			Collection<String> keys = cache.keys(cacheName);
			return keys == null ? new ArrayList<String>(keys) : null;
		} catch (IOException e) {
			LogKit.error(e.toString(), e);
		}
		return null;
	}

}