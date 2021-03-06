package net.byml.fund.service;

import net.byml.fund.model.Fund;

public interface FundService {
	public Fund findById(Long id);

	public Fund findByCode(String code);

	public Iterable<Fund> findByIds(Iterable<Long> ids);
	/**
	 * Remove the associated Fund record from the data repository.
	 * 
	 * @param fund
	 */
	public void delete(Fund fund);

	/**
	 * Save the state of the provided Fund object into the data repository.
	 * 
	 * @param fund
	 */
	public void save(Fund fund);

	public Iterable<Fund> findAll();
}
