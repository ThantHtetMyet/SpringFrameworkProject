package Ajax;
import com.sms.model.*;

public class AjaxResponseBodyAdmin {
	String msg;
    tblAdminUser result;

    @Override
	public String toString() {
		return "AjaxResponseBodyAdmin [msg=" + msg + ", result=" + result + "]";
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public tblAdminUser getResult() {
		return result;
	}

	public void setResult(tblAdminUser result) {
		this.result = result;
	}
    
}
