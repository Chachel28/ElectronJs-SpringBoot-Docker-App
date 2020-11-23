//const { dialog } = require("electron");

async function validate_login(){
    const url = "http://localhost:8080/api/v1/login";
    const email = document.getElementById("email");
    const pass = document.getElementById("password");
    const form = document.getElementById("form-login")
    let body = {email: email.value}
    let postInit = {
        method: 'POST',
        headers:{            
            'Content-Type': 'application/json',
            'Accept': 'application/json'
        },
        body: JSON.stringify(body)
    }
    form.addEventListener('submit', async function(e) {
        e.preventDefault();
        await fetch(url, postInit)
        .then(response => response.json())
        .then(result => {
            if(pass.value == result.password){
                location.href = "views/index.html";
            }else{
                await notifyDialog('Contraseña errónea')
            }
        })
        .catch(() => {
            await notifyDialog('Algunos datos son erróneos')
        })

    });
}
// función para enseñar notificaciones en Electron
async function notifyDialog (message) {
    let options = {
        buttons: ["Yes","No","Cancel"],
        message: message,
        title: 'Application Error'
    };
    dialog.showMessageBox(null,options,response => {
        console.log(response)
    });
}