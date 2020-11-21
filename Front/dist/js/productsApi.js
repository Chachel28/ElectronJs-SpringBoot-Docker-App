async function getTwentyLastProducts() {
    fetch('http://localhost:8080/api/v1/lastproducts', { 
    method: 'GET'
    })
    .then(function(response) { return response.json(); })
    .then(function(json) {
        console.log(json.name);
        return json;
    }).catch(error => console.log("Error: $error"));
}