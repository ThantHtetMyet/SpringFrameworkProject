package Ajax;

import com.sms.model.*;

public class AjaxResponseBodyApproveEnrollCourse {
	String msg;
    tblApplicantUser result;
	public AjaxResponseBodyApproveEnrollCourse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AjaxResponseBodyApproveEnrollCourse(String msg, tblApplicantUser result) {
		super();
		this.msg = msg;
		this.result = result;
	}
	@Override
	public String toString() {
		return "AjaxResponseBodyApproveEnrollCourse [msg=" + msg + ", result=" + result + "]";
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public tblApplicantUser getResult() {
		return result;
	}
	public void setResult(tblApplicantUser result) {
		this.result = result;
	}

    
}
