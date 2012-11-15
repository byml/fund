package net.byml.fund.service;

import net.byml.fund.model.Fund;
import net.byml.fund.repository.FundRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("fundService")
public class FundServiceImpl implements FundService {

	@Autowired
	private FundRepository repository;

	public Fund findById(Long id) {
		return repository.findOne(id);
	}

	public Iterable<Fund> findAll() {
		return repository.findAll();
	}
 
	@Transactional
	public void delete(Fund fund) {
		repository.delete(fund);
	}

	@Transactional
	public void save(Fund fund) {
		repository.save(fund);
	}

	public Fund findByCode(String code) {
		return repository.findByCode(code);
	}

	public Iterable<Fund> findByIds(Iterable<Long> ids) {
		return repository.findAll(ids);
	}

}
