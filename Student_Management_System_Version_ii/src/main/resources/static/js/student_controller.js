function send_student_data()
{
   console.log("STUDENT FORM HIT");
   fire_studentajax_submit();
}

function fire_studentajax_submit() 
{
    var search = {};
    var password = $("#student_password_box").val();
    var username =  $("#student_username_box").val();
    console.log(password);
    console.log(username);
    
    search["username"] = $("#student_username_box").val();
	search["password"] = $("#student_password_box").val();
	
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/student_api/search",
        data: JSON.stringify(search),
        dataType: 'json',
        cache: false,
        
        success: function (data) {
            var json = JSON.stringify(data);
            obj = JSON.parse(json);
            
            console.log("Return result");
            console.log(obj.msg);
            
            if(obj.msg == "user_err")
            {
            document.getElementById("student_error_span").style.color = "red";
            document.getElementById("student_error_span").innerHTML = "No User Found";
            alert("No User Found");
            student_login_box();
            }
            else if(obj.msg == "password_err")
        	{
        	document.getElementById("student_error_span").style.color = "red";
        	document.getElementById("student_error_span").innerHTML = "Password Mismatch";
        	alert("Password Mismatch");
        	}
            else if(obj.msg == "empty_err")
            {
            	document.getElementById("student_error_span").style.color = "red";
            	document.getElementById("student_error_span").innerHTML = "Empty";
            	alert("Empty");
           	}
            else if(obj.msg == "password_empty_err")
            {
            	document.getElementById("student_error_span").style.color = "red";
            	document.getElementById("student_error_span").innerHTML = "Password Empty";
            	alert("Password Empty");
           	}
            else if(obj.msg == "username_empty_err")
            {
            	document.getElementById("student_error_span").style.color = "red";
            	document.getElementById("student_error_span").innerHTML = "Username Empty";
            	alert("Username Empty");
           	}
            else if(obj.msg == "match")
            {
            	console.log("SUCCESS : ", obj.result.id);
            	alert("SUCCESS");
            	httpGet("/sms_student/index/"+obj.result.id); 	
            }
           },
        error: function (e) {
            var json = e.responseText + "</pre>";
            console.log("ERROR : ", e);
        }
   	 
    });
}

function clear_error_warning()
{
	console.log("Clear Error Warning!");
	document.getElementById("student_error_span").innerHTML = "";
}

function httpGet(theUrl)
{    
    console.log("---- go to "+ theUrl + " page ----"); 
	window.location = theUrl;
}

function student_login_box(){
	console.log("Student Modal");
	document.getElementById("student_error_span").innerHTML = "";
    $("#studentloginmodal").modal();
    document.getElementById("student_username_box").value = "";
    document.getElementById("student_password_box").value = "";
}

function Enroll_Course()
{
	var link_value = document.getElementById("enroll_course_link").href;	
	console.log(link_value);
}

