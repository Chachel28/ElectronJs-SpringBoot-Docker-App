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
    await fetch('http://localhost:8080/api/v1/clients')
  .then(response => response.json())
  .then(response => {console.log(response)});
}

/*function fetchWithAjaxProducts() {
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.open("GET", 'http://localhost:8080/api/v1/products', true),
    xmlhttp.onreadystatechange = function() 
    {
    if (this.readyState == 4 && this.status == 200) 
        {
        var responseJsonObj = JSON.parse(this.responseText);
 
        console.log(responseJsonObj.name);
        console.log(responseJsonObj.description);
        }
    },
    //Si hay cuerpo de la aplicación
     var data = {"name" : "Lokesh"};
    xmlhttp.send( JSON.stringify( data ) );
    xmlhttp.send(),
    $.ajax({
        url: 'http://localhost:8080/api/v1/products',
        contentType: "application/json",
        dataType: 'json',
        success: function(result){
            console.log(result);
        }
    }
}*/

/*Para parsear un JSON hay que hacer JSON.parse(myObject) */