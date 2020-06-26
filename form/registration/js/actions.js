function register(){
  var url = "http://localhost:8081/register";

    console.log(document.getElementById("mobileNumber").value);
    var data = {};
    data.mobileNumber = document.getElementById("mobileNumber").value;
    data.firstName = document.getElementById("firstName").value;
    data.lastName = document.getElementById("lastName").value;
    data.monthOfBirth = document.getElementById("monthOfBirth").value;
    data.dateOfBirth = document.getElementById("dateOfBirth").value;
    data.yearOfBirth = document.getElementById("yearOfBirth").value;
    if (document.getElementById("male").checked) {
      data.gender = document.getElementById("male").value;
    } else if (document.getElementById("female").checked) {
      data.gender = document.getElementById("female").value;
    }
    data.email = document.getElementById("email").value;
    var json = JSON.stringify(data);

    var xhr = new XMLHttpRequest();
    xhr.open("POST", url, true);
    xhr.setRequestHeader('Content-type','application/json; charset=utf-8');
    xhr.onload = function () {
      var response = JSON.parse(xhr.responseText);
      if (xhr.status == "200") {
        console.table(response);
        if(response.responseCode == '00'){
          //success
          document.getElementById("overlay").style.display = "block";
          document.getElementById("wrapperLogin").style.display = "block";
        } else {
          //failed
          document.getElementById("responseMessage").innerHTML = response.responseMessage;
          document.getElementById("responseMessage").style.display = "block";
        }
      } else {
        console.error(response);
        alert('There are some errors!');
      }
    }
    xhr.send(json);
}

window.onload=function() {
  document.getElementById("LoginButton").onclick=function() {
    window.location.replace("login/login.html");
    return false;
  }
}