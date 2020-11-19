async function validate_login(){
    let url = "localhost:8080/api/v1/login";
    let email = document.getElementById("email").value;
    let pass = document.getElementById("password").value;
    let body = {email: email}
    let getInit = {
        method: "GET",
        headers:{            
            "Content-Type": "application/json",
            "Accept": "application/json"
        },
        body: JSON.stringify(body)
    }
    document.getElementById("access").setAttribute("href", "views/index.html");
    /*await fetch(url, getInit)
    .then(response => response.json)
    .then(response => {

    });*/
}