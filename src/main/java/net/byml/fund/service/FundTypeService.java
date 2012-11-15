package net.byml.fund.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import net.byml.fund.model.FundType;

public interface FundTypeService {

	public Iterable<FundType> findAll();

	public FundType findByCode(String code);

	public void save(FundType entity);

	public List<FundType> findByCodeIn(Collection<String> codeColl);
	
	public void saveByMapList(List<Map<String, Object>> mapList);
}
