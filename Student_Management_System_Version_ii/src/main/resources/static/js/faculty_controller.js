/**
 * 
 */
function faculty_login_box(){
	console.log("FACULTY MODAL");
    $("#facultyloginmodal").modal();
    document.getElementById("faculty_username_box").value = "";
    document.getElementById("faculty_password_box").value = "";
}

function send_faculty_data()
{
   console.log("FACULTY FORM HIT");	
   fire_facultyajax_submit();
}

function fire_facultyajax_submit() 
{
    var search = {};
    var password = $("#faculty_password_box").val();
    var username =  $("#faculty_username_box").val();
    console.log(password);
    console.log(username);
    
    search["username"] = $("#faculty_username_box").val();
	search["password"] = $("#faculty_password_box").val();
	
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/faculty_api/search",
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
            	httpGet("/sms_faculty/index/"+obj.result.id); 	
            }
           },
        error: function (e) {
            var json = e.responseText + "</pre>";
            console.log("ERROR : ", e);
        }
    });
}
