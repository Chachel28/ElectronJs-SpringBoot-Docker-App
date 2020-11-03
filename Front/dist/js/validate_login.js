
function validacion() {
    let email  =  document.getElementById("email-login").value();
    let password = document.getElementById("password-login").value();
    if (email != "admin@gmail.com") {
        alert('Email no registrado')
        console.log("false")
        return false;
    }
    else if (password != "Contraseña") {
        alert('Contraseña incorrecta')
        console.log("false")
        return false;
    }
    console.log(email)
    return true;
}