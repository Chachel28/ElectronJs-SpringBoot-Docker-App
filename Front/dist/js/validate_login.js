
function validation() {
    var email  =  document.getElementById("email-login").value;
    var password = document.getElementById("password-login").value;
    if (email == "admin@gmail.com" && password == "Password") {
        alert('Login succesfully.');
        return false;
    }
    else {
        alert('Wrong email or password');

    }

}