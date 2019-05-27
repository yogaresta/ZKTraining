package vmd;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Include;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import service.MstDepartmentService;
import service.MstKaryawanService;
import dao.MstDepartmentDao;
import dto.MstDepartmentDto;
import dto.MstKaryawanDto;
import entity.MstDepartment;
import entity.enumcol.GenderEnum;
import entity.pk.MstDepartmentPk;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class KaryawanEditVmd {
	
	@WireVariable
	private MstKaryawanService mstKaryawanSvc;
	
	@WireVariable
	private MstDepartmentService mstDepartmentSvc;
	
	@WireVariable
	private MstDepartmentDao mstDepartmentDao;
	
	private MstDepartment mstDepartment;
	
	private MstKaryawanDto mstKaryawanDto = new MstKaryawanDto();
	private GenderEnum gender;
	private List<MstDepartmentDto> listDepartment = new ArrayList<MstDepartmentDto>();
	private MstDepartmentDto mstDepartmentDto;
	
	private String genderChoice;

	@Init
	@NotifyChange(value={"genderChoice"})
	public void load(){
		mstKaryawanDto = (MstKaryawanDto) Sessions.getCurrent().getAttribute("objKaryawan");
		genderChoice = mstKaryawanDto.getGender() != null ? mstKaryawanDto.getGender().getCode() : "";
		getAllDepartment();
	}
	
	@Command(value="save")
	public void save(){
		MstKaryawanDto mstKaryawanFindOne = new MstKaryawanDto();
		mstKaryawanFindOne = mstKaryawanSvc.findOne(mstKaryawanDto);
		
		if(mstKaryawanFindOne != null && mstKaryawanFindOne.getId() != null){
			MstDepartmentPk deptPk = new MstDepartmentPk();
			deptPk.setId(mstDepartmentDto.getId());
			mstDepartment = mstDepartmentDao.findOne(deptPk);
			
			mstKaryawanDto.setDepartment(mstDepartment);
			gender = genderChoice.equalsIgnoreCase("M")? GenderEnum.MALE : GenderEnum.FEMALE;
			mstKaryawanDto.setGender(gender);
			mstKaryawanSvc.update(mstKaryawanDto);
			Clients.showNotification("Data berhasil diupdate", Clients.NOTIFICATION_TYPE_INFO, null, null, 1500);
			Include inc = (Include) Executions.getCurrent().getDesktop().getPage("index").getFellow("mainInclude");
			inc.setSrc("/master/karyawan/karyawan.zul");
		}
		else{
			mstKaryawanSvc.save(mstKaryawanDto);
			Clients.showNotification("Data berhasil disimpan", Clients.NOTIFICATION_TYPE_INFO, null, null, 1500);
			Include inc = (Include) Executions.getCurrent().getDesktop().getPage("index").getFellow("mainInclude");
			inc.setSrc("/master/karyawan/karyawan.zul");
		}
	}
	
	@Command(value="back")
	public void back(){
		Include inc = (Include) Executions.getCurrent().getDesktop().getPage("index").getFellow("mainInclude");
		inc.setSrc("/master/karyawan/karyawan.zul");
	}
	
	
	
	private void getAllDepartment(){
		if(mstDepartmentSvc.findAllNotDeleted() != null 
				&& !mstDepartmentSvc.findAllNotDeleted().isEmpty()
				&& mstDepartmentSvc.findAllNotDeleted().size() > 0){
			listDepartment = mstDepartmentSvc.findAllNotDeleted();
		}
		else{
			Messagebox.show("Tidak ada daftar Department yang bisa ditampilkan");
		}
	}
	
	/* ----- Setter Getter ----- */
	
	public MstKaryawanDto getMstKaryawanDto() {
		return mstKaryawanDto;
	}

	public void setMstKaryawanDto(MstKaryawanDto mstKaryawanDto) {
		this.mstKaryawanDto = mstKaryawanDto;
	}

	public GenderEnum getGender() {
		return gender;
	}

	public void setGender(GenderEnum gender) {
		this.gender = gender;
	}

	public List<MstDepartmentDto> getListDepartment() {
		return listDepartment;
	}

	public void setListDepartment(List<MstDepartmentDto> listDepartment) {
		this.listDepartment = listDepartment;
	}

	public MstDepartmentDto getMstDepartmentDto() {
		return mstDepartmentDto;
	}

	public void setMstDepartmentDto(MstDepartmentDto mstDepartmentDto) {
		this.mstDepartmentDto = mstDepartmentDto;
	}

	public MstDepartment getMstDepartment() {
		return mstDepartment;
	}

	public void setMstDepartment(MstDepartment mstDepartment) {
		this.mstDepartment = mstDepartment;
	}

	public String getGenderChoice() {
		return genderChoice;
	}

	public void setGenderChoice(String genderChoice) {
		this.genderChoice = genderChoice;
	}
	
}
