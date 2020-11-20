function validate_login(){
    const url = "localhost:8080/api/v1/login";
    const email = document.getElementById("email");
    const pass = document.getElementById("password");
    const form = document.getElementById("form-login")
    form.addEventListener('submit', (e) => {
        let messages = [];

        if(pass.length <= 6){
            messages.push("La contraseña debe contener al menos 6 caracteres.")
        }

        let body = {email: email.value}
        let getInit = {
            method: 'POST',
            headers:{            
                "Content-Type": "application/json",
                "User-Agent": "alex"
            },
            body: JSON.stringify(body)
        }
        fetch(url, getInit)
        .then(response => response.json)
        .then(response => {
            console.log(response)
        });
        //location.href = "views/index.html"
    })
}