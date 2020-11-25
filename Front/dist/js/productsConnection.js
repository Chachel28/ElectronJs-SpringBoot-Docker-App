async function getTwentyLastProducts() {
    let url = 'http://localhost:8080/api/v1/lastproducts'
    let getInit = {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json'
        }
    }
    await fecth(url, getInit)
        .then(response => response.json())
        .then(response => {
            response.forEach(product => {
                let ul = document.getElementById('lastProductsList');
                let a = document.createElement('a');
                let urlProduct = "products.html?id=" + product.id;
                a.setAttribute('href', urlProduct);
                let li = document.createElement('li');
                let i = document.createElement('i');
                i.setAttribute("class", "fas fa-portrait mr-2");
                li.appendChild(i);
                li.innerHTML = li.innerHTML + product.name + " - " + product.stock;
                a.appendChild(li)
                ul.appendChild(a)

            });
        })

}

async function loadProduct() {
    const querystring = location.search;
    const params = new URLSearchParams(querystring)
    let id = params.get('id')
    if (id == undefined) id = 1;
    let urlProducto = 'http://localhost:8080/api/v1/products/' + id;
    let getInit = {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json'
        }
    }
    await fetch(urlProducto, getInit)
        .then(response => response.json())
        .then(response => {
            let nameProduct = document.getElementById('nombreProducto');
            nameProduct.innerHTML = response.name;
            let idProduct = document.getElementById('idProducto')
            idProduct.innerHTML = idProduct.innerHTML + response.id;
            let tipo = document.getElementById('tipoProducto')
            tipo.innerHTML = tipo.innerHTML + response.type;
            let pvp = document.getElementById('pvpProducto')
            pvp.innerHTML = pvp.innerHTML + response.sellPrice;
            let pvr = document.getElementById('pvrProducto')
            pvr.innerHTML = pvr.innerHTML + response.buyPrice;
            let descripcion = document.getElementById('descripcionProducto')
            descripcion.innerHTML = descripcion.innerHTML + response.description;
            let stock = document.getElementById('stockProducto')
            stock.innerHTML = stock.innerHTML + response.stock;

            let nameModificar = document.getElementById('inputProduct')
            nameModificar.value = response.name;
            let descripcionModificar = document.getElementById('inputDescription')
            descripcionModificar.value = response.description;
            let tipoModificar = document.getElementById('inputTipo')
            tipoModificar.value = response.type;
            let pvpModificar = document.getElementById('inputPVP')
            pvpModificar.value = response.sellPrice;
            let pvrModificar = document.getElementById('inputPVR')
            pvrModificar.value = response.buyPrice;
            let stockModificar = document.getElementById('inputStock')
            stockModificar.value = response.stock;

        })

}