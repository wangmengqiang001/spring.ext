package com.crossbridge.kernel.dba;

import java.util.Collection;

import com.crossbridge.kernel.dba.bean.News;

public interface NewsDao {
	 public Collection<News> loadNews();
	 void add(News news);
}
