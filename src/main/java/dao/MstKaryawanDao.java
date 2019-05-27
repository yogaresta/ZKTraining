package dao;

import org.springframework.data.jpa.repository.JpaRepository;

import entity.MstKaryawan;
import entity.pk.MstKaryawanPk;

public interface MstKaryawanDao extends 
	JpaRepository<MstKaryawan, MstKaryawanPk>{

}
