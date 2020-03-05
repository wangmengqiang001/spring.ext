package com.crossbridge.kernel.dba;

import java.util.Collection;

import com.crossbridge.kernel.dba.bean.Forum;

public interface ForumDao {
	 public Collection<Forum> loadForums();
	 void add(Forum forum);
}
