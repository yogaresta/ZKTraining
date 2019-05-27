package dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import entity.MstCustomer;
import entity.MstKaryawan;
import entity.pk.MstCustomerPk;
import entity.pk.MstKaryawanPk;

public interface MstCustomerDao extends 
JpaRepository<MstCustomer, MstCustomerPk>{

}
