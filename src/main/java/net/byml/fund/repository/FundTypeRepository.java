package net.byml.fund.repository;

import java.util.Collection;
import java.util.List;

import net.byml.fund.model.FundType;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface FundTypeRepository extends
		PagingAndSortingRepository<FundType, Long>,
		JpaSpecificationExecutor<FundType> {

	public FundType findByCode(String code);

	public List<FundType> findByCodeIn(Collection<String> codeColl);

}
