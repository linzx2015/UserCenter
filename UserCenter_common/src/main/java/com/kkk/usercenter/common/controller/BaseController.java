package com.kkk.usercenter.common.controller;

import java.util.HashMap;
import java.util.Map;

public class BaseController
{
	private Map<String,Object> paramMap=new HashMap<String,Object>();
	protected Map<String,Object> getParamMap()
	{
		this.paramMap.clear();
		return this.paramMap;
	}
}
