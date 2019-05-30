package com.anshuye.j2cache;

import java.io.IOException;

import net.oschina.j2cache.J2Cache;

public class J2CacheTest {
	
	public static void main(String[] args) throws IOException {
		new J2CachePlugin().start();
		String str1 = "高阁客竟去，小园花乱飞。参差连曲陌，迢递送斜晖。";
		String str2 = "肠断未忍扫，眼穿仍欲归。芳心向春尽，所得是沾衣。";
		String key1 = "ab1"; 
		String key2 = "ab2"; 
		String cacheName = "example";
		
		//读写
		System.out.println("写入：" + str1);
		System.out.println("写入：" + str2);
		
		J2CacheKit.put(cacheName, key1, str1);
		J2CacheKit.put(cacheName, key2, str2);
		
		System.out.println("获取key1：" + J2CacheKit.get(cacheName, key1));
		System.out.println("获取key2：" + J2CacheKit.get(cacheName, key2));
		
//		//删除
		J2CacheKit.remove(cacheName, key1);
		System.out.println("删除：" + key1);
		System.out.println("删除后输出:"+key1 +  J2CacheKit.get(cacheName, key1));
		System.out.println("删除后输出:"+key2 +  J2CacheKit.get(cacheName, key2));
//		
		//删除全部
		J2CacheKit.removeAll(cacheName);
		J2Cache.getChannel().clear(cacheName);
		System.out.println("删除全部数据：");
		System.out.println("删除后输出:"+key1 +  J2CacheKit.get(cacheName, key1));
		System.out.println("删除后输出:"+key2 +  J2CacheKit.get(cacheName, key2));
	}

}
