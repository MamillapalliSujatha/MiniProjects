package com.suji.runners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

import com.suji.entity.ReportEntity;
import com.suji.repo.ReportRepo;

@Configuration
public class AppRunner implements ApplicationRunner {

	@Autowired
	private ReportRepo repo1;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {

		ReportEntity entity1 = new ReportEntity();
		entity1.setSno(1);
		entity1.setName("john");
		entity1.setEmail("jogn@gmail.com");
		entity1.setGender("Male");
		entity1.setSsn(34567L);
		entity1.setMobile(9876564536L);
		entity1.setPlanName("food");
		entity1.setPlanStatus("denied");
		repo1.save(entity1);

		ReportEntity entity2 = new ReportEntity();
		entity2.setSno(2);
		entity2.setName("Raju");
		entity2.setEmail("raju@gmail.com");
		entity2.setGender("Male");
		entity2.setSsn(87567L);
		entity2.setMobile(9988776655L);
		entity2.setPlanName("Medical");
		entity2.setPlanStatus("Approved");
		repo1.save(entity2);

		ReportEntity entity3 = new ReportEntity();
		entity3.setSno(3);
		entity3.setName("priya");
		entity3.setEmail("priya@gmail.com");
		entity3.setGender("female");
		entity3.setSsn(76767L);
		entity3.setMobile(8976234556L);
		entity3.setPlanName("Unemployeed");
		entity3.setPlanStatus("denied");
		repo1.save(entity3);

		ReportEntity entity4 = new ReportEntity();
		entity4.setSno(4);
		entity4.setName("Devi");
		entity4.setEmail("devi@gmail.com");
		entity4.setGender("Female");
		entity4.setSsn(87667L);
		entity4.setMobile(8523824564L);
		entity4.setPlanName("Food");
		entity4.setPlanStatus("approved");
		repo1.save(entity4);

		ReportEntity entity5 = new ReportEntity();
		entity5.setSno(5);
		entity5.setName("ravi");
		entity5.setEmail("ravi@gmail.com");
		entity5.setGender("Male");
		entity5.setSsn(65456L);
		entity5.setMobile(7676512345L);
		entity5.setPlanName("Midical");
		entity5.setPlanStatus("teminated");
		repo1.save(entity5);

	}

}
