async function loadClientList(){
    let url = 'http://localhost:8080/api/v1/clients'
    let getInit = {
        method:'GET',
        headers:{
            'Content-Type': 'application/json',
            'Accept': 'application/json'
        }
    }
    await fetch(url, getInit)
    .then(response => response.json())
    .then(response => {
        console.log(response);
    })
}