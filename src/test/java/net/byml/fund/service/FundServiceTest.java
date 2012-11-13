package net.byml.fund.service;

import net.byml.fund.model.Fund;
import net.byml.fund.service.FundService;

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
}
