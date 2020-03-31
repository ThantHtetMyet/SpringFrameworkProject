package Ajax;
import com.sms.model.*;

public class AjaxResponseBodyStudent {

    String msg;
    tblStudentUser result;
    
	public AjaxResponseBodyStudent() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getMsg() {
		return msg;
	}
	@Override
	public String toString() {
		return "AjaxResponseBody [msg=" + msg + ", result=" + result + "]";
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public tblStudentUser getResult() {
		return result;
	}
	public void setResult(tblStudentUser result) {
		this.result = result;
	}
	
}