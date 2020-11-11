function Validation(){
    var email  =  document.getElementById("email-login").value;
    var password = document.getElementById("password-login").value;

    if ((email == "admin@gmail.com") && (password == "1234")) {
        alert('Login successfully');
        return true;
    }
    else {
        alert('Login failed');
        return false;
    }
}