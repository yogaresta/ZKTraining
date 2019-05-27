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

import service.MstCustomerService;
import dto.MstCustomerDto;
import dto.MstKaryawanDto;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class CustomerVmd {

	private List<MstCustomerDto> listCustomer = new ArrayList<MstCustomerDto>();
	private MstCustomerDto mstCustomerDto = new MstCustomerDto();
	private boolean readonly = false;
	
	@WireVariable
	private MstCustomerService mstCustomerSvc;
	
	@Init
	public void load(){
		listCustomer = mstCustomerSvc.findAll();
	}
	@Command(value="add")
	public void add(){
		MstCustomerDto mstCustomerDto = new MstCustomerDto();
		
		Sessions.getCurrent().setAttribute("objCustomer", mstCustomerDto);
		
		Include inc = (Include) Executions.getCurrent().getDesktop().getPage("index").getFellow("mainInclude");
		
		inc.setSrc("/master/customer/customeredit.zul");
	}
	
	@Command(value="edit")
	public void edit(){
		
		if(mstCustomerDto == null && mstCustomerDto.getId() == null){
			Messagebox.show("Pilih data yang akan diedit");
		}
		else{
			Sessions.getCurrent().setAttribute("objCustomer", mstCustomerDto);
			Include inc = (Include) Executions.getCurrent().getDesktop().getPage("index").getFellow("mainInclude");
			inc.setSrc("/master/customer/customeredit.zul");
		}
		
	}
	
	@Command("delete")
	public void delete(){
		if(mstCustomerDto == null && mstCustomerDto.getId() == null){
			Messagebox.show("Pilih data yang akan di delete");
		}
		else{
			mstCustomerSvc.delete(mstCustomerDto);
			listCustomer.remove(mstCustomerDto);
			BindUtils.postNotifyChange(null, null, CustomerVmd.this, "listCustomer");
			Clients.showNotification("Data Berhasil di Delete", Clients.NOTIFICATION_TYPE_INFO, null, null, 500);
		}
	}
	public List<MstCustomerDto> getListCustomer() {
		return listCustomer;
	}
	public void setListCustomer(List<MstCustomerDto> listCustomer) {
		this.listCustomer = listCustomer;
	}
	public MstCustomerDto getMstCustomerDto() {
		return mstCustomerDto;
	}
	public void setMstCustomerDto(MstCustomerDto mstCustomerDto) {
		this.mstCustomerDto = mstCustomerDto;
	}
	public boolean isReadonly() {
		return readonly;
	}
	public void setReadonly(boolean readonly) {
		this.readonly = readonly;
	}
	public MstCustomerService getMstCustomerSvc() {
		return mstCustomerSvc;
	}
	public void setMstCustomerSvc(MstCustomerService mstCustomerSvc) {
		this.mstCustomerSvc = mstCustomerSvc;
	}
}
