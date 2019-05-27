package test;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dao.MstDepartmentDao;
import dto.MstDepartmentDto;
import dto.MstKaryawanDto;
import entity.MstDepartment;
import entity.enumcol.GenderEnum;
import service.MstDepartmentService;
import service.MstKaryawanService;

public class TestKaryawan {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("/META-INF/spring/app-config.xml");
		
		MstKaryawanService mstKaryawanSvc = context.getBean(MstKaryawanService.class);
		MstDepartmentDao mstDepartmentDao = context.getBean(MstDepartmentDao.class);
		
		MstDepartment dept = mstDepartmentDao.findByDeptName("DIVISI FINANCE");
		
		MstKaryawanDto dto = new MstKaryawanDto();
		dto.setCreatedDate(new Date());
		dto.setCreatedUser("ADMIN");
		dto.setDateOfBirth(new Date());
		dto.setDeleted(false);
		dto.setDepartment(dept);
		dto.setGender(GenderEnum.MALE);
		dto.setId(3);
		dto.setNamaKaryawan("Rizky");
		mstKaryawanSvc.save(dto);
	}

}
