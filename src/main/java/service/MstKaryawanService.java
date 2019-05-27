package service;

import java.util.List;

import dto.MstKaryawanDto;

public interface MstKaryawanService {
	
	public void save(MstKaryawanDto mstKaryawanDto);
	public List<MstKaryawanDto> findAll();
	public void delete(MstKaryawanDto mstKaryawanDto);
	public MstKaryawanDto findOne(MstKaryawanDto mstKaryawanDto);
	public void update(MstKaryawanDto mstKaryawanDto);

}
