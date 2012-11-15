package net.byml.fund.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.byml.fund.model.FundType;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
@Transactional
public class FundTypeServiceImplTest {
	@Autowired
	FundTypeService service;

	@Test
	public void testFindAll() {
		service.findAll();
	}

	@Test
	public void testFindByCode() {
		service.findByCode("GPX");
	}

	@Test
	public void testFindByCodes() {
		List<String> codes = new ArrayList<String>();
		codes.add("GPX");
		codes.add("CCC");
		List<FundType> list = service.findByCodeIn(codes);
		System.out.println(list);
	}

	@Test
	public void testSave() {
		FundType entity = new FundType();
		entity.setCode("GPX");
		entity.setName("NNN");
		service.save(entity);
	}

	@Test
	public void testSaveByMapList() {
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		Map<String, Object> m1 = new HashMap<String, Object>();
		m1.put("code", "ccc");
		m1.put("name", "an1");
		Map<String, Object> m2 = new HashMap<String, Object>();
		m2.put("code", "dd");
		m2.put("name", "dn");
		mapList.add(m1);
		mapList.add(m2);
		service.saveByMapList(mapList);
	}

}
