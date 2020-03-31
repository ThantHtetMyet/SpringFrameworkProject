package Ajax;

import com.sms.model.*;

public class AjaxResponseBodyFaculty {
	String msg;
    tblFacultyUser result;
	
    public AjaxResponseBodyFaculty() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AjaxResponseBodyFaculty(String msg, tblFacultyUser result) {
		super();
		this.msg = msg;
		this.result = result;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public tblFacultyUser getResult() {
		return result;
	}

	public void setResult(tblFacultyUser result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "AjaxResponseBodyFaculty [msg=" + msg + ", result=" + result + "]";
	}
    
    
}
