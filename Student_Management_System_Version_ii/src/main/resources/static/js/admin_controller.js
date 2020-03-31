function send_admin_data()
{
   console.log("FACULTY FORM HIT");	
   fire_adminajax_submit();
}

function fire_adminajax_submit() 
{
    var search = {};
    var password = $("#admin_password_box").val();
    var username =  $("#admin_username_box").val();
    console.log(password);
    console.log(username);
    
    search["username"] = $("#admin_username_box").val();
	search["password"] = $("#admin_password_box").val();
	
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/admin_api/search",
        data: JSON.stringify(search),
        dataType: 'json',
        cache: false,
        success: function (data) 
        {
            var json = JSON.stringify(data);
            obj = JSON.parse(json);
            
            console.log("Return result");
            console.log(obj);
            
            if(obj.msg == "user_err")
            {
            document.getElementById("admin_error_span").style.color = "red";
            document.getElementById("admin_error_span").innerHTML = "No User Found";
            alert("No User Found");
            }
            else if(obj.msg == "password_err")
        	{
        	document.getElementById("admin_error_span").style.color = "red";
        	document.getElementById("admin_error_span").innerHTML = "Password Mismatch";
        	alert("Password Mismatch");
        	}
            else if(obj.msg == "empty_err")
            {
            	document.getElementById("admin_error_span").style.color = "red";
            	document.getElementById("admin_error_span").innerHTML = "Empty";
            	alert("Empty");
            }
            else if(obj.msg == "password_empty_err")
            {
            	document.getElementById("admin_error_span").style.color = "red";
            	document.getElementById("admin_error_span").innerHTML = "Password Empty";
            	alert("Password Empty");
            }
            else if(obj.msg == "username_empty_err")
            {
            	document.getElementById("admin_error_span").style.color = "red";
            	document.getElementById("admin_error_span").innerHTML = "Username Empty";
            	alert("Username Empty");
            }
            else if(obj.msg == "match")
            {
            	console.log("SUCCESS : ", obj.result.id);
            	alert("SUCCESS");
            	httpGet("/sms_admin/index/"+obj.result.id); 	
            }
           },
        error: function (e) {
            var json = e.responseText + "</pre>";
            console.log("ERROR : ", e);
        }
    });
}

function admin_login_box(){
	console.log("Admin Modal");
	document.getElementById("admin_error_span").innerHTML = "";
    $("#adminloginmodal").modal();
    document.getElementById("admin_username_box").value = "";
    document.getElementById("admin_password_box").value = "";
    document.getElementById("admin_username_box").innerHTML = "";
    document.getElementById("admin_password_box").innerHTML = "";
}


//ENROLL APPROVE 
function Approve_EnrollCourse(enroll_id,coursename,username,department_id)
{
	console.log("ENROLL");
	console.log(enroll_id);
	console.log(coursename);
	console.log(username);
	console.log(department_id);
	fire_approve_enrollcourse_ajax_submit(enroll_id,coursename,username,department_id);
}

function fire_approve_enrollcourse_ajax_submit(enrollID,course_name,user_name,department_id) 
{
    var search = {};
    
    var enrollusername = user_name;
    var enrollcoursename =  course_name;
    var enroll_id = enrollID;
    var enroll_department_id = department_id;
    
    search["attendcoursename"] = enrollcoursename + "*" + enroll_id;
	search["attendusername"] = enrollusername;
	search["attenddepartmentid"] = enroll_department_id;
	
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/admin_api/approve_enroll_course",
        data: JSON.stringify(search),
        dataType: 'json',
        cache: false,
        success: function (data) 
        {
            var json = JSON.stringify(data);
            obj = JSON.parse(json);
            
            console.log("Return result");
            console.log(obj);
            
            if(obj.msg == "match")
            {
            	alert("SUCCESS");
            	httpGet("/sms_admin/show_applicant_users"); 	
            }
           },
        error: function (e) {
            var json = e.responseText + "</pre>";
            console.log("ERROR : ", e);
        }
    });
}
