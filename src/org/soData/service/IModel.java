package org.soData.service;

import java.util.List;

import org.soData.pojo.Enity;
import org.soData.pojo.Rule;

public interface IModel {
	public  List<Enity> crawl(Rule rule);
}
