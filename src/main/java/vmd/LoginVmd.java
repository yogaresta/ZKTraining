package vmd;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Messagebox;

import dto.MstUserDto;
import service.MstUserService;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class LoginVmd {
	
	@WireVariable
	private MstUserService mstUserSvc;
	
	private MstUserDto mstUserDto;
	private String username;
	private String password;
	
	@Command("login")
	@NotifyChange({"username", "password"})
	public void login() {
		if(username != null && !username.equalsIgnoreCase("") && password != null && !password.equalsIgnoreCase("")) {
			
			mstUserDto = mstUserSvc.findByUsernamePassword(username, password);
			
//			if(username.equalsIgnoreCase("admin") && password.equalsIgnoreCase("admin")) {
			if(mstUserDto != null && mstUserDto.getId() != null){
				Sessions.getCurrent().setAttribute("user", mstUserDto);
				Executions.sendRedirect("/index.zul");
			}
			else {
				Messagebox.show("Invalid Login");
				setUsername(null);
				setPassword(null);
			}
		}
		else {
			Messagebox.show("Harap diisi terlebih dahulu");
		}
	}
	

	public MstUserDto getMstUserDto() {
		return mstUserDto;
	}


	public void setMstUserDto(MstUserDto mstUserDto) {
		this.mstUserDto = mstUserDto;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
