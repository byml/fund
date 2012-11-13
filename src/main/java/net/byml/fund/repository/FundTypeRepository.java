package net.byml.fund.repository;

import net.byml.fund.model.FundType;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface FundTypeRepository extends
		PagingAndSortingRepository<FundType, Long>,
		JpaSpecificationExecutor<FundType> {
	public FundType findByCode(String code);

}
