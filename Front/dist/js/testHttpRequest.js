function httpGet(theUrl)
{
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open( "GET", theUrl, false ); // false for synchronous request
    xmlHttp.send( null );
    return xmlHttp.responseText;
}

function httpGetAsync(theUrl, callback)
{
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.onreadystatechange = function() { 
        if (xmlHttp.readyState == 4 && xmlHttp.status == 200)
            callback(xmlHttp.responseText);
    }
    xmlHttp.open("GET", theUrl, true); // true for asynchronous 
    xmlHttp.send(null);
}

async function apiTestProducts() {
    await fetch('http://localhost:8080/api/v1/products')
  .then(response => response.json())
  .then(response => console.log(response));
}

$.ajax({
    type: "GET",
    url: "http://localhost:8080/api/v1/products",
    data: "data",
    dataType: "List<Products>",
    success: function (response) {
        
    }
});

/*Para parsear un JSON hay que hacer JSON.parse(myObject) */