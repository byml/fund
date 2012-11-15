package net.byml.fund.service;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.byml.fund.model.FundType;
import net.byml.fund.repository.FundTypeRepository;

import org.apache.commons.beanutils.BeanUtils;
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

	public void saveByMapList(List<Map<String, Object>> mapList) {
		if (mapList != null && !mapList.isEmpty()) {
			Map<String, Map<String, Object>> mapMap = new HashMap<String, Map<String, Object>>();
			for (Iterator<Map<String, Object>> it = mapList.iterator(); it
					.hasNext();) {
				Map<String, Object> map = it.next();
				String code = (String) map.get("code");
				mapMap.put(code, map);
			}
			List<FundType> fundTypeList = findByCodeIn(mapMap.keySet());
			System.out.println("----"+fundTypeList);
			if (fundTypeList != null && !fundTypeList.isEmpty()) {
				for (Iterator<FundType> it = fundTypeList.iterator(); it
						.hasNext();) {
					FundType fundType = it.next();
					String code = fundType.getCode();
					Map<String, Object> map = mapMap.remove(code);
					try {
						BeanUtils.populate(fundType, map);
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			for (Iterator<Map<String, Object>> it = mapMap.values().iterator(); it
					.hasNext();) {
				Map<String, Object> map = it.next();
				FundType fundType = new FundType();
				try {
					BeanUtils.populate(fundType, map);
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				fundTypeList.add(fundType);
			}
			System.out.println("----"+fundTypeList);
			repository.save(fundTypeList);
		}

	}

	public List<FundType> findByCodeIn(Collection<String> codeColl) {
		return repository.findByCodeIn(codeColl);
	}

	public void save(FundType entity) {
		repository.save(entity);
	}
}
