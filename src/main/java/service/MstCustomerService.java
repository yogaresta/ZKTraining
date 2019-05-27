package service;

import java.util.List;

import dto.MstCustomerDto;

public interface MstCustomerService {

	public void save(MstCustomerDto mstCustomerDto);
	public List<MstCustomerDto> findAll();
	public void delete(MstCustomerDto mstCustomerDto);
	public MstCustomerDto findOne(MstCustomerDto mstCustomerDto);
	public void update(MstCustomerDto mstCustomerDto);
}
