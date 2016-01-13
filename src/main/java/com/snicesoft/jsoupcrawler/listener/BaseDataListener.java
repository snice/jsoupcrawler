package com.snicesoft.jsoupcrawler.listener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.snicesoft.jsoupcrawler.config.entity.Info;

public abstract class BaseDataListener implements DataAcceptListener {
	private Map<String, List<Object[]>> params;

	public BaseDataListener() {
		params = new HashMap<String, List<Object[]>>();
		initParas();
	}

	@Override
	public long getCount(Info info) {
		if (params.get(info.getId()) == null)
			return 0;
		return params.get(info.getId()).size();
	}

	@Override
	public Object[] getParams(Info info, int position) {
		if (getCount(info) == 0)
			return null;
		return params.get(info.getId()).get(position);
	}

	public final void addParam(String id, List<Object[]> list) {
		params.put(id, list);
	}

	public final void addParam(String id, Object[] param) {
		if (params.get(id) == null)
			params.put(id, new ArrayList<Object[]>());
		params.get(id).add(param);
	}

	public abstract void initParas();
}
