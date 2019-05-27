package service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.MstDepartmentDao;
import dao.MstCustomerDao;
import dto.MstCustomerDto;
import dto.MstKaryawanDto;
import entity.MstCustomer;
import entity.MstKaryawan;
import entity.pk.MstCustomerPk;
import entity.pk.MstKaryawanPk;
import service.MstCustomerService;


@Service(value="mstCustomerSvc")
@Transactional
public class MstCustomerServiceImpl implements MstCustomerService{

	@Autowired
	private MstCustomerDao mstCustomerDao;
	
	@Autowired
private MstDepartmentDao mstDepartmentDao;
	@Override
	public void save(MstCustomerDto mstCustomerDto) {
		try {
			MstCustomer mstCustomer = new MstCustomer();
			mstCustomer.setCreatedDate(mstCustomerDto.getCreatedDate());
			mstCustomer.setCreatedUser(mstCustomerDto.getCreatedUser());
			mstCustomer.setDeleted(mstCustomerDto.getDeleted());
			mstCustomer.setNamaCustomer(mstCustomerDto.getNamaCustomer());
			mstCustomer.setUpdatedDate(mstCustomerDto.getUpdatedDate());
			mstCustomer.setUpdatedUser(mstCustomerDto.getUpdatedUser());
			mstCustomer.setId(mstCustomerDto.getId());
			mstCustomerDao.save(mstCustomer);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<MstCustomerDto> findAll() {
		List<MstCustomerDto> list = null;
		try {
			List<MstCustomer> res = mstCustomerDao.findAll();
			if(res != null && !res.isEmpty() && res.size() > 0){
				list = new ArrayList<MstCustomerDto>();
				
				for(MstCustomer customer : res){
					MstCustomerDto dto = new MstCustomerDto();
					dto.setCreatedDate(customer.getCreatedDate());
					dto.setCreatedUser(customer.getCreatedUser());
					dto.setDeleted(customer.getDeleted());
					dto.setId(customer.getId());
					dto.setNamaCustomer(customer.getNamaCustomer());
					dto.setUpdatedDate(customer.getUpdatedDate());
					dto.setUpdatedUser(customer.getUpdatedUser());
					dto.setKotaCustomer(customer.getKotaCustomer());
					list.add(dto);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			list = null;
		}
		return list;
	}

	@Override
	public void delete(MstCustomerDto mstCustomerDto) {
		try {
			MstCustomerPk custPk = new MstCustomerPk();
			custPk.setId(mstCustomerDto.getId());
			
			MstCustomer customer = mstCustomerDao.findOne(custPk);
			if(customer != null && customer.getId() != null){
				customer.setDeleted(true);
				customer.setUpdatedDate(new Date());
				customer.setUpdatedUser("ADMIN");
				mstCustomerDao.save(customer);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public MstCustomerDto findOne(MstCustomerDto mstCustomerDto) {
		try {
			MstCustomerPk custPk = new MstCustomerPk();
			custPk.setId(mstCustomerDto.getId());
			MstCustomerDto dto = null;
			MstCustomer customer = mstCustomerDao.findOne(custPk);
			if(customer != null && customer.getId() != null){
				dto = new MstCustomerDto();
				dto.setCreatedDate(customer.getCreatedDate());
				dto.setCreatedUser(customer.getCreatedUser());
				dto.setDeleted(customer.getDeleted());
				dto.setId(customer.getId());
				dto.setNamaCustomer(customer.getNamaCustomer());
				dto.setUpdatedDate(customer.getUpdatedDate());
				dto.setUpdatedUser(customer.getUpdatedUser());
				dto.setKotaCustomer(customer.getKotaCustomer());
			}
			return dto;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void update(MstCustomerDto mstCustomerDto) {
		try {
			MstCustomerPk custPk = new MstCustomerPk();
			custPk.setId(mstCustomerDto.getId());
			
			MstCustomer customer = mstCustomerDao.findOne(custPk);
			if(customer != null && customer.getId() != null){
				customer.setDeleted(mstCustomerDto.getDeleted());
				customer.setUpdatedDate(new Date());
				customer.setUpdatedUser("ADMIN");
				customer.setNamaCustomer(mstCustomerDto.getNamaCustomer());
				customer.setKotaCustomer(customer.getKotaCustomer());
				mstCustomerDao.save(customer);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
