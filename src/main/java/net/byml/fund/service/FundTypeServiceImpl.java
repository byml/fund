package net.byml.fund.service;

import net.byml.fund.model.FundType;
import net.byml.fund.repository.FundTypeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("fundTypeService")
public class FundTypeServiceImpl implements FundTypeService {
	@Autowired
	private FundTypeRepository repository;

	public Iterable<FundType> findAll() {
		return repository.findAll();
	}

	public FundType findByCode(String code) {
		return repository.findByCode(code);
	}

	public void save(FundType entity) {
		repository.save(entity);
	}
}
