package com.hx.service;

public interface ResourceService {

	void addResource(String resourname, String url, long rightid) throws Exception;

	void delResource(long resourid);

}
