package com.sandu.common.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.ext.interceptor.NotAction;
import com.jfinal.kit.Kv;
import com.jfinal.kit.StrKit;
import com.sandu.common.kit.Ipkit;

public class BaseController extends Controller{

	/**
	 * 用于批量接参，重复参数名只取第一个值
	 */
	@Before(NotAction.class)
	public Kv getParaToMap() {
		Kv kv = new Kv();
		Map<String, String[]> praaMap = getParaMap();
		for (Map.Entry<String, String[]> entry : praaMap.entrySet()) {
			kv.set(entry.getKey(), entry.getValue()[0]);
		}
		return kv;
	}

	/**
	 * 用于批量接数组参数 
	 * 例如： extCoverList[0]=/upload/temp/1.jpg  
	 * 		extCoverList[1]=/upload/temp/1.jpg
	 * @param paraName : 参数名称
	 * @param times:循环次数
	 */
	@Before(NotAction.class)
	public List<String> getParaToList(String paraName,int times) {
		times = times > 0 ? times : 1;
		List<String> list = new ArrayList<>();
		for (int i = 0; i < times; i++){
			String value = getPara(paraName+"["+i+"]");
			if (StrKit.notBlank(value)) {
				list.add(value);
			}else{
				break;
			}
		}
		if (list.size() == 0) {
			return null;
		}
		return list;
	}
	
	@Before(NotAction.class)
	public List<String> getParaToList(String paraName) {
		List<String> list = new ArrayList<>();
		for (int i = 0; i < 100; i++){
			String value = getPara(paraName+"["+i+"]");
			if (StrKit.notBlank(value)) {
				list.add(value);
			}else{
				break;
			}
		}
		if (list.size() == 0) {
			return null;
		}
		return list;
	}
	
	/**
	 * 用于批量接二维数组参数 
	 * 例如： extCoverList[0][name]:35.jpg
			extCoverList[0][url]:/upload/temp/35.jpg
			extCoverList[1][name]:14.jpg
			extCoverList[1][url]:/upload/temp/14.jpg
	 */
	@Before(NotAction.class)
	public List<String> getParaArrToList(String arrName,String paraName) {
		List<String> list = new ArrayList<>();
		for (int i = 0; i < 100; i++){
			String value = getPara(arrName+"["+i+"]"+"["+paraName+"]");
			if (StrKit.notBlank(value)) {
				list.add(value);
			}else{
				break;
			}
		}
		if (list.size() == 0) {
			return null;
		}
		return list;
	}
	
	/**
	 * 用于批量接二维数组参数 
	 * 例如： extCoverList[0][name]:35.jpg
			extCoverList[0][url]:/upload/temp/35.jpg
			extCoverList[1][name]:14.jpg
			extCoverList[1][url]:/upload/temp/14.jpg
	 */
	@Before(NotAction.class)
	public List<String> getParaArrToList(String arrName,String paraName,int times) {
		times = times > 0 ? times : 1;
		List<String> list = new ArrayList<>();
		for (int i = 0; i < times; i++){
			String value = getPara(arrName+"["+i+"]"+"["+paraName+"]");
			if (StrKit.notBlank(value)) {
				list.add(value);
			}else{
				break;
			}
		}
		if (list.size() == 0) {
			return null;
		}
		return list;
	}

	/**
	 * 获取访问IP
	 */
	@Before(NotAction.class)
    public String getIpAddress() {
    	return Ipkit.getRealIp(getRequest());
    }
}
