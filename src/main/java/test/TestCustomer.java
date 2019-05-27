package test;

import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import service.MstCustomerService;
import dao.MstCustomerDao;
import dao.MstDepartmentDao;
import dto.MstCustomerDto;
import entity.MstCustomer;
import entity.MstDepartment;
import entity.enumcol.GenderEnum;

public class TestCustomer {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("/META-INF/spring/app-config.xml");
		
		MstCustomerService mstCustomerSvc = context.getBean(MstCustomerService.class);
//		MstCustomerDao mstCustomerDao = context.getBean(MstCustomerDao.class);
		MstDepartmentDao mstDepartmentDao = context.getBean(MstDepartmentDao.class);
		
		MstDepartment dept = mstDepartmentDao.findByDeptName("DIVISI FINANCE");
		
		MstCustomerDto dto = new MstCustomerDto();
		dto.setCreatedDate(new Date());
		dto.setCreatedUser("ADMIN");
		dto.setDeleted(false);
		dto.setId(3);
		dto.setNamaCustomer("rez");
		dto.setKotaCustomer("bandung");
		mstCustomerSvc.save(dto);
	}
}
