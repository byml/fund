package net.byml.fund.service;

import net.byml.fund.model.FundType;

public interface FundTypeService {

	public Iterable<FundType> findAll();

	public FundType findByCode(String code);

	public void save(FundType entity);
}
