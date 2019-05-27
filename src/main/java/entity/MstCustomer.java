package entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import component.BaseEntity;
import entity.enumcol.GenderEnum;
import entity.pk.MstCustomerPk;


@Entity
@Table(name="mst_customer")
@IdClass(value=MstCustomerPk.class)
public class MstCustomer extends BaseEntity implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5714697743026436614L;

	@Id
	@Column(name="id")
	private Integer id;
	
	@Column(name="nama_customer")
	private String namaCustomer;
	
	@Column(name="kota_customer")
	private String kotaCustomer;
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNamaCustomer() {
		return namaCustomer;
	}

	public void setNamaCustomer(String namaCustomer) {
		this.namaCustomer = namaCustomer;
	}

	public String getKotaCustomer() {
		return kotaCustomer;
	}

	public void setKotaCustomer(String kotaCustomer) {
		this.kotaCustomer = kotaCustomer;
	}
	
		
	
}
