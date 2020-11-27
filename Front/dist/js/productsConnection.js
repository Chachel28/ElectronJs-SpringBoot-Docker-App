async function getTwentyLastProducts() {
    let url = 'http://localhost:8080/api/v1/lastproducts'
    let getInit = {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json'
        }
    }

    await fetch(url, getInit)
        .then(response => response.json())
        .then(response => {
            console.log(response)
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


            //Carga del dato en la celda de la tabla
            let table = document.getElementById('tableProductsLoad');
            let tblBody = document.getElementById('bodyTableProducts');
            let row = document.createElement('tr');
            let celda1 = document.createElement('td');
            celda1.innerHTML = response.id;
            row.appendChild(celda1);
            let celda2 = document.createElement('td');
            celda2.innerHTML = response.name;
            row.appendChild(celda2);
            let celda3 = document.createElement('td');
            celda3.innerHTML = response.type;
            row.appendChild(celda3);
            let celda4 = document.createElement('td');
            celda4.innerHTML = response.stock;
            row.appendChild(celda4);
            let celda5 = document.createElement('td');
            celda5.innerHTML = response.sellPrice;
            row.appendChild(celda5);
            let celda6 = document.createElement('td');
            celda6.innerHTML = response.buyPrice;
            row.appendChild(celda6);

            tblBody.appendChild(row);
            table.appendChild(tblBody);

        })

}

async function addProduct() {
    let form = document.getElementById('addProduct');
    form.addEventListener('submit', async(e) => {
        e.preventDefault();
        const querystring = location.search;
        const params = new URLSearchParams(querystring)
        let id = params.get("id");
        if (id == undefined) id = 1
        let name = document.getElementById('inputProductA');
        let description = document.getElementById('inputDescriptionA');
        let tipo = document.getElementById('inputTipoA');
        let pvp = document.getElementById('inputPVPA');
        let pvr = document.getElementById('inputPVRA');
        let stock = document.getElementById('inputStockA');

        let data = {
            name: name.value,
            description: description.value,
            buyPrice: pvr.value,
            sellPrice: pvp.value,
            type: tipo.value,
            stock: stock.value
        }

        let url = 'http://localhost:8080/api/v1/products'
        let postInit = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            },
            body: JSON.stringify(data)
        }

        await fetch(url, postInit)
            .then(response => response.json())
            .then(response => console.log(response))

        location.href = 'products.html?id=' + id;
    })
}

async function updateProduct() {
    let form = document.getElementById('updateProduct')

    form.addEventListener('submit', async(e) => {
        const querystring = location.search;
        const params = new URLSearchParams(querystring)
        let id = params.get("id");
        if (id == undefined) id = 1
        e.preventDefault();
        let name = document.getElementById('inputProduct');
        let description = document.getElementById('inputDescription');
        let tipo = document.getElementById('inputTipo');
        let pvp = document.getElementById('inputPVP');
        let pvr = document.getElementById('inputPVR');
        let stock = document.getElementById('inputStock');
        let tipoGlobal;
        switch (tipo.value) {
            case 'SIMPLE':
                tipoGlobal = 'SIMPLE';
                break;
            case 'COMPUESTO':
                tipoGlobal = 'COMPUESTO';
                break;
            case 'RECURSO':
                tipoGlobal = 'RECURSO';
            case 'SERVICIO':
                tipoGlobal = 'SERVICIO';
                break;
        }

        let data = {
            name: name.value,
            description: description.value,
            buyPrice: pvr.value,
            sellPrice: pvp.value,
            type: tipo.value,
            stock: stock.value
        }

        console.log(data)

        let url = 'http://localhost:8080/api/v1/products' + id;
        let postInit = {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            },
            body: JSON.stringify(data)
        }

        await fetch(url, postInit)
            .then(response => console.log(response))

        location.href = 'products.html?id=' + id;
    })
}
//TODO: Hacer que deleteProduct() funcione, no hace nada
async function deleteProduct() {
    let form = document.getElementById('deleteProduct')
    form.addEventListener('submit', async(e) => {
        e.preventDefault();
        const querystring = location.search;
        const params = new URLSearchParams(querystring)
        let id = params.get("id");
        let url = 'http://localhost:8080/api/v1/products/' + id;
        let deleteInit = {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            }
        }

        await fetch(url, deleteInit)
            .then(response => console.log(response))

        location.href = 'products.html';
    })

}