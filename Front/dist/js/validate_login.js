async function validate_login() {
    const url = "http://localhost:8080/api/v1/login";
    const email = document.getElementById("email");
    const pass = document.getElementById("password");
    const form = document.getElementById("form-login")
    let body = { email: email.value }
    let postInit = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            },
            body: JSON.stringify(body)
        }
        //let dialog = electron.remote.dialog;
    form.addEventListener('submit', async function(e) {
        e.preventDefault();
        await fetch(url, postInit)
            .then(response => response.json())
            .then(result => {
                console.log(result)
                if (pass.value == result.password) {
                    location.href = "views/index.html";
                } else {
                    console.log('Ha fallado algo')
                    let p = document.getElementById('debug');
                    p.innerHTML = 'Las credenciales no son correctas';
                }
            }).catch(() => {
                let p = document.getElementById('debug');
                p.innerHTML = 'El usuario no existe en el sistema';
            })

    });
}