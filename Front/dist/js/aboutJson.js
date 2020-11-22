// Ejemplo implementando el metodo POST:
async function postData(url = '', data = {}) {
    // Opciones por defecto estan marcadas con un *
    const response = await fetch(url, {
      method: 'POST', // *GET, POST, PUT, DELETE, etc.
      mode: 'cors', // no-cors, *cors, same-origin
      cache: 'no-cache', // *default, no-cache, reload, force-cache, only-if-cached
      credentials: 'same-origin', // include, *same-origin, omit
      headers: {
        'Content-Type': 'application/json'
        // 'Content-Type': 'application/x-www-form-urlencoded',
      },
      redirect: 'follow', // manual, *follow, error
      referrerPolicy: 'no-referrer', // no-referrer, *no-referrer-when-downgrade, origin, origin-when-cross-origin, same-origin, strict-origin, strict-origin-when-cross-origin, unsafe-url
      body: JSON.stringify(data) // body data type must match "Content-Type" header
    });
    return response.json(); // parses JSON response into native JavaScript objects
  }
  
  postData('https://example.com/answer', { answer: 42 })
    .then(data => {
      console.log(data); // JSON data parsed by `data.json()` call
    });

    fetch('flores.jpg').then(function(response) {
        if(response.ok) {
          response.blob().then(function(miBlob) {
            var objectURL = URL.createObjectURL(miBlob);
            miImagen.src = objectURL;
          });
        } else {
          console.log('Respuesta de red OK pero respuesta HTTP no OK');
        }
      })
      .catch(function(error) {
        console.log('Hubo un problema con la petición Fetch:' + error.message);
      });


      //Con tu propio objeto Request
    var myHeaders = new Headers();

    var myInit = { method: 'GET',
               headers: myHeaders,
               mode: 'cors',
               cache: 'default' };

    var myRequest = new Request('flowers.jpg', myInit);

    fetch(myRequest)
        .then(function(response) {
        return response.blob();
    })

    .then(function(myBlob) {
        var objectURL = URL.createObjectURL(myBlob);
        myImage.src = objectURL;
    });


    //Enviando Datos Json
    var url = 'https://example.com/profile';
    var data = {username: 'example'};

    fetch(url, {
    method: 'POST', // or 'PUT'
    body: JSON.stringify(data), // data can be `string` or {object}!
    headers:{
        'Content-Type': 'application/json'
    }
    }).then(res => res.json())
    .catch(error => console.error('Error:', error))
    .then(response => console.log('Success:', response));