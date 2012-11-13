package net.byml.fund.repository;

import net.byml.fund.model.Fund;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface FundRepository extends PagingAndSortingRepository<Fund, Long>,
		JpaSpecificationExecutor<Fund> {
	public Fund findByCode(String code);

}
