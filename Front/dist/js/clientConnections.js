async function loadClient() {
    const querystring = location.search;
    const params = new URLSearchParams(querystring)
    let id = params.get("id");
    if (id == undefined) id = 1
    let url = 'http://localhost:8080/api/v1/clients';
    let getInit = {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json'
        }
    }
    await fetch(url + '/' + id, getInit)
        .then(response => response.json())
        .then(response => {
            let name = document.getElementById('NombreCliente')
            name.innerHTML = name.innerHTML + response.fullName;
            let inputName = document.getElementById('inputCompleteName2');
            inputName.value = response.fullName;
            let id = document.getElementById('id')
            id.innerHTML = id.innerHTML + response.id;
            let dni = document.getElementById('dni')
            dni.innerHTML = dni.innerHTML + response.dni;
            let inputDni = document.getElementById('inputDNI2')
            inputDni.value = response.dni;
            let email = document.getElementById('email')
            email.innerHTML = email.innerHTML + response.email;
            let inputEmail = document.getElementById('inputEmail2')
            inputEmail.value = response.email;
            let iban = document.getElementById('iban')
            iban.innerHTML = iban.innerHTML + response.iban;
            let inputIban = document.getElementById('inputIBAN2')
            inputIban.value = response.iban;
            let tele = document.getElementById('telefonos')
            tele.innerHTML = tele.innerHTML + response.telephones[0].number;
            let inputTelephones = document.getElementById('inputTelephone2')
            inputTelephones.value = response.telephones[0].number;
            let dire = document.getElementById('direcciones')
            dire.innerHTML = dire.innerHTML + response.directions[0].direction;
            let inputDirections = document.getElementById('inputDirection2')
            inputDirections.value = response.directions[0].direction;
        })

    await fetch(url, getInit)
        .then(response => response.json())
        .then(response => {
            let final = 1;
            if (response.length > 20) {
                final = 20;
            } else {
                final = response.length;
            }
            for (let i = 0; i < final; i++) {
                let a = document.createElement('a');
                let urlClient = 'clients.html?id=' + response[i].id;
                a.setAttribute('href', urlClient);

                let li = document.createElement('li');

                let ii = document.createElement('i');
                ii.setAttribute('class', 'fas fa-clipboard mr-2');
                li.appendChild(ii);

                li.innerHTML = li.innerHTML + ' ' + response[i].fullName + ' - ' + response[i].dni;

                a.appendChild(li);

                document.getElementById('LastClientList').appendChild(a);
            }
        })
}

async function addClient() {
    let form = document.getElementById('addClient')

    form.addEventListener('submit', async(e) => {
        e.preventDefault();
        const querystring = location.search;
        const params = new URLSearchParams(querystring)
        let id = params.get("id");
        if (id == undefined) id = 1
        let name = document.getElementById('inputCompletName')
        let email = document.getElementById('inputEmail')
        let iban = document.getElementById('inputIBAN')
        let dni = document.getElementById('inputDNI')
        let telephone = document.getElementById('inputTelephone')
        let direction = document.getElementById('inputDirection')

        let data = {
            fullName: name.value,
            email: email.value,
            iban: iban.value,
            dni: dni.value,
            telephones: [{
                number: telephone.value
            }],
            directions: [{
                direction: direction.value
            }],
            sales: []
        }

        let url = 'http://localhost:8080/api/v1/clients';
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
            .then(respone => console.log(respone))

        location.href = 'clients.html?id=' + id;
    })
}

async function updateClient() {
    let form = document.getElementById('updateClient')

    form.addEventListener('submit', async(e) => {
        const querystring = location.search;
        const params = new URLSearchParams(querystring)
        let id = params.get("id");
        if (id == undefined) id = 1
        e.preventDefault();
        let name = document.getElementById('inputCompleteName2')
        let email = document.getElementById('inputEmail2')
        let iban = document.getElementById('inputIBAN2')
        let dni = document.getElementById('inputDNI2')
        let telephone = document.getElementById('inputTelephone2')
        let direction = document.getElementById('inputDirection2')

        let data = {
            fullName: name.value,
            email: email.value,
            iban: iban.value,
            dni: dni.value,
            telephones: [{
                number: telephone.value
            }],
            directions: [{
                direction: direction.value
            }],
            sales: []
        }

        console.log(data)

        let url = 'http://localhost:8080/api/v1/clients/' + id;
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

        location.href = 'clients.html?id=' + id;
    })
}

async function deleteProduct() {
    let form = document.getElementById('deleteClient')
    form.addEventListener('submit', async(e) => {
        const querystring = location.search;
        const params = new URLSearchParams(querystring)
        let id = params.get("id");
        let url = 'http://localhost:8080/api/v1/clients/' + id;
        let deleteInit = {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            }
        }

        await fetch(url, deleteInit)
            .then(response => console.log(response))

        location.href = 'clients.html';
    })

}

// <a href=""><li><i class="fas fa-clipboard mr-2"></i>Lorem ipsum dolor sit amet consectetur</li></a>