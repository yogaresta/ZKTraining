package vmd;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Include;
import org.zkoss.zul.Messagebox;

import service.MstKaryawanService;
import dto.MstKaryawanDto;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class KaryawanVmd {
	
	private List<MstKaryawanDto> listKaryawan = new ArrayList<MstKaryawanDto>();
	private MstKaryawanDto mstKaryawanDto = new MstKaryawanDto();
	private boolean readonly = false;
	
	@WireVariable
	private MstKaryawanService mstKaryawanSvc;
	
	@Init
	public void load(){
		listKaryawan = mstKaryawanSvc.findAll();
	}
	
	@Command(value="add")
	public void add(){
		MstKaryawanDto mstKaryawanDto = new MstKaryawanDto();
		
		Sessions.getCurrent().setAttribute("objKaryawan", mstKaryawanDto);
		
		Include inc = (Include) Executions.getCurrent().getDesktop().getPage("index").getFellow("mainInclude");
		
		inc.setSrc("/master/karyawan/karyawanedit.zul");
	}
	
	@Command(value="edit")
	public void edit(){
		
		if(mstKaryawanDto == null && mstKaryawanDto.getId() == null){
			Messagebox.show("Pilih data yang akan diedit");
		}
		else{
			Sessions.getCurrent().setAttribute("objKaryawan", mstKaryawanDto);
			Include inc = (Include) Executions.getCurrent().getDesktop().getPage("index").getFellow("mainInclude");
			inc.setSrc("/master/karyawan/karyawanedit.zul");
		}
		
	}
	
	@Command("delete")
	public void delete(){
		if(mstKaryawanDto == null && mstKaryawanDto.getId() == null){
			Messagebox.show("Pilih data yang akan di delete");
		}
		else{
			mstKaryawanSvc.delete(mstKaryawanDto);
			listKaryawan.remove(mstKaryawanDto);
			BindUtils.postNotifyChange(null, null, KaryawanVmd.this, "listKaryawan");
			Clients.showNotification("Data Berhasil di Delete", Clients.NOTIFICATION_TYPE_INFO, null, null, 500);
		}
	}

	/* ----- Setter Getter ----- */
	public List<MstKaryawanDto> getListKaryawan() {
		return listKaryawan;
	}

	public void setListKaryawan(List<MstKaryawanDto> listKaryawan) {
		this.listKaryawan = listKaryawan;
	}

	public MstKaryawanDto getMstKaryawanDto() {
		return mstKaryawanDto;
	}

	public void setMstKaryawanDto(MstKaryawanDto mstKaryawanDto) {
		this.mstKaryawanDto = mstKaryawanDto;
	}

	public boolean isReadonly() {
		return readonly;
	}

	public void setReadonly(boolean readonly) {
		this.readonly = readonly;
	}

	public MstKaryawanService getMstKaryawanSvc() {
		return mstKaryawanSvc;
	}

	public void setMstKaryawanSvc(MstKaryawanService mstKaryawanSvc) {
		this.mstKaryawanSvc = mstKaryawanSvc;
	}
	
}
