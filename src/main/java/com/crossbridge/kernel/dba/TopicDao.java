package com.crossbridge.kernel.dba;

import java.util.Collection;

import com.crossbridge.kernel.dba.bean.Topic;

public interface TopicDao {
	void add(Topic a);
	Collection<Topic> load();
	

}
