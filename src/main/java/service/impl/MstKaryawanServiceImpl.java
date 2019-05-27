package service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.bytecode.buildtime.spi.ExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.MstDepartmentDao;
import dao.MstKaryawanDao;
import dto.MstKaryawanDto;
import entity.MstKaryawan;
import entity.pk.MstKaryawanPk;
import service.MstKaryawanService;

@Service(value="mstKaryawanSvc")
@Transactional
public class MstKaryawanServiceImpl implements MstKaryawanService{

	@Autowired
	private MstKaryawanDao mstKaryawanDao;
	
	@Autowired
	private MstDepartmentDao mstDepartmentDao;
	
	@Override
	public void save(MstKaryawanDto mstKaryawanDto) {
		try {
			MstKaryawan mstKaryawan = new MstKaryawan();
			mstKaryawan.setCreatedDate(mstKaryawanDto.getCreatedDate());
			mstKaryawan.setCreatedUser(mstKaryawanDto.getCreatedUser());
			mstKaryawan.setDateOfBirth(mstKaryawanDto.getDateOfBirth());
			mstKaryawan.setDeleted(mstKaryawanDto.getDeleted());
			mstKaryawan.setDepartment(mstKaryawanDto.getDepartment());
			mstKaryawan.setNamaKaryawan(mstKaryawanDto.getNamaKaryawan());
			mstKaryawan.setUpdatedDate(mstKaryawanDto.getUpdatedDate());
			mstKaryawan.setUpdatedUser(mstKaryawanDto.getUpdatedUser());
			mstKaryawan.setGender(mstKaryawanDto.getGender());
			mstKaryawan.setId(mstKaryawanDto.getId());
			mstKaryawan.setBirthPlace(mstKaryawanDto.getBirthPlace());
			mstKaryawanDao.save(mstKaryawan);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<MstKaryawanDto> findAll() {
		List<MstKaryawanDto> list = null;
		try {
			List<MstKaryawan> res = mstKaryawanDao.findAll();
			if(res != null && !res.isEmpty() && res.size() > 0){
				list = new ArrayList<MstKaryawanDto>();
				
				for(MstKaryawan karyawan : res){
					MstKaryawanDto dto = new MstKaryawanDto();
					dto.setCreatedDate(karyawan.getCreatedDate());
					dto.setCreatedUser(karyawan.getCreatedUser());
					dto.setDateOfBirth(karyawan.getDateOfBirth());
					dto.setDeleted(karyawan.getDeleted());
					dto.setDepartment(karyawan.getDepartment());
					dto.setGender(karyawan.getGender());
					dto.setId(karyawan.getId());
					dto.setNamaKaryawan(karyawan.getNamaKaryawan());
					dto.setUpdatedDate(karyawan.getUpdatedDate());
					dto.setUpdatedUser(karyawan.getUpdatedUser());
					dto.setBirthPlace(karyawan.getBirthPlace());
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
	public void delete(MstKaryawanDto mstKaryawanDto) {
		try {
			MstKaryawanPk karyPk = new MstKaryawanPk();
			karyPk.setId(mstKaryawanDto.getId());
			
			MstKaryawan karyawan = mstKaryawanDao.findOne(karyPk);
			if(karyawan != null && karyawan.getId() != null){
				karyawan.setDeleted(true);
				karyawan.setUpdatedDate(new Date());
				karyawan.setUpdatedUser("ADMIN");
				mstKaryawanDao.save(karyawan);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public MstKaryawanDto findOne(MstKaryawanDto mstKaryawanDto) {
		try {
			MstKaryawanPk karyPk = new MstKaryawanPk();
			karyPk.setId(mstKaryawanDto.getId());
			MstKaryawanDto dto = null;
			MstKaryawan karyawan = mstKaryawanDao.findOne(karyPk);
			if(karyawan != null && karyawan.getId() != null){
				dto = new MstKaryawanDto();
				dto.setCreatedDate(karyawan.getCreatedDate());
				dto.setCreatedUser(karyawan.getCreatedUser());
				dto.setDateOfBirth(karyawan.getDateOfBirth());
				dto.setDeleted(karyawan.getDeleted());
				dto.setDepartment(karyawan.getDepartment());
				dto.setGender(karyawan.getGender());
				dto.setId(karyawan.getId());
				dto.setNamaKaryawan(karyawan.getNamaKaryawan());
				dto.setUpdatedDate(karyawan.getUpdatedDate());
				dto.setUpdatedUser(karyawan.getUpdatedUser());
				dto.setBirthPlace(karyawan.getBirthPlace());
			}
			return dto;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void update(MstKaryawanDto mstKaryawanDto) {
		try {
			MstKaryawanPk karyPk = new MstKaryawanPk();
			karyPk.setId(mstKaryawanDto.getId());
			
			MstKaryawan karyawan = mstKaryawanDao.findOne(karyPk);
			if(karyawan != null && karyawan.getId() != null){
				karyawan.setDeleted(mstKaryawanDto.getDeleted());
				karyawan.setUpdatedDate(new Date());
				karyawan.setUpdatedUser("ADMIN");
				karyawan.setDateOfBirth(mstKaryawanDto.getDateOfBirth());
				karyawan.setDepartment(mstKaryawanDto.getDepartment());
				karyawan.setGender(mstKaryawanDto.getGender());
				karyawan.setNamaKaryawan(mstKaryawanDto.getNamaKaryawan());
				karyawan.setBirthPlace(mstKaryawanDto.getBirthPlace());
				mstKaryawanDao.save(karyawan);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
