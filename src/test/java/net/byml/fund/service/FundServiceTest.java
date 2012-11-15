package net.byml.fund.service;

import java.util.ArrayList;
import java.util.List;

import net.byml.fund.model.Fund;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
@Transactional
public class FundServiceTest {
	@Autowired
	FundService service;

	@Test
	public void testFindById() {
		Fund fund = service.findById(new Long(3));
		System.out.println(fund);
	}
	
	@Test
	public void testFindByIds() {
		List<Long> ids = new ArrayList<Long>();
		ids.add(3L);
		ids.add(new Long(2));
		Iterable<Fund> funds = service.findByIds(ids);
		System.out.println(funds);
	}
	 
	@Test
	public void testFindAll() {
		Iterable<Fund> funds = service.findAll();
		System.out.println(funds);
	}
	
	@Test
	public void testSave() {
		Fund fund = new Fund();
		fund.setCode("fff");
		fund.setShortName("ss");
		service.save(fund);
		fund = service.findByCode("fff");
		System.out.println(fund);
		service.delete(fund);
	}
}
