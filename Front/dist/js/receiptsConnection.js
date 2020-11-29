async function loadLasReceipts() {
    let url = 'http://localhost:8080/api/v1/receipts'
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
            let final = 1;
            if (response.length > 20) {
                final = 20;
            } else {
                final = response.length;
            }
            for (let i = 0; i < final; i++) {
                let a = document.createElement('a');
                let urlReceipt = 'receipt.html?id=' + response[i].id;
                a.setAttribute('href', urlReceipt);

                let li = document.createElement('li');

                let ii = document.createElement('i');
                ii.setAttribute('class', 'fas fa-adress-card mr-2');
                li.appendChild(ii);

                li.innerHTML = li.innerHTML + ' ' + response[i].id + ' - ' + response[i].receiptDate;

                a.appendChild(li);

                document.getElementById('lastReceiptsList').appendChild(a);
            }
        })
    
}

async function loadReceipt() {
    const querystring = location.search;
    const params = new URLSearchParams(querystring)
    let id = params.get('id')
    let idReceipt = params.get('id');
    if (id == undefined) id=1;
    let urlReceipt = 'http://localhost:8080/api/v1/receipts/' + idReceipt;
    let getInit = {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json'
        }
    }
    await fetch(urlReceipt,getInit)
    .then(response => response.json())
    .then(response => {
            let name = document.getElementById('nameStaff')
            name.innerHTML = name.innerHTML + ' ' + response.name;
            let email = document.getElementById('emailStaff')
            email.innerHTML = email.innerHTML + ' ' + response.email;
            let position = document.getElementById('namePositionStaff')
            position.innerHTML = position.innerHTML + ' ' + response.positionStaff.name;
            let section = document.getElementById('sectionPositionStaff')
            section.innerHTML = section.innerHTML + ' ' + response.positionStaff.section;
            let privilege = document.getElementById('privilegePositionStaff')
            privilege.innerHTML = privilege.innerHTML + ' ' + response.positionStaff.privilege;

            //Modificacion
            let nameModificar = document.getElementById('personalNameM')
            nameModificar.value = response.name;
            let emailModificar = document.getElementById('personalEmailM')
            emailModificar.value = response.email;
            let positionModificar = document.getElementById('positionStaffM')
            positionModificar.value = response.positionStaff.name;
            let sectionModificar = document.getElementById('seccionPersonalM')
            sectionModificar.value = response.positionStaff.section;
            let privilegeModificar = document.getElementById('privilegioPersonalM')
            privilegeModificar.value = response.positionStaff.privilege;

            //Carga del dato en la celda de la tabla
            let table = document.getElementById('tableStaff');
            let tblBody = document.getElementById('bodyTableStaff');
            let row = document.createElement('tr');
            let celda1 = document.createElement('td');
            celda1.innerHTML = response.id;
            row.appendChild(celda1);
            let celda2 = document.createElement('td');
            celda2.innerHTML = response.name;
            row.appendChild(celda2);
            let celda3 = document.createElement('td');
            celda3.innerHTML = response.email;
            row.appendChild(celda3);
            let celda4 = document.createElement('td');
            celda4.innerHTML = response.positionStaff.name;
            row.appendChild(celda4);
            let celda5 = document.createElement('td');
            celda5.innerHTML = response.positionStaff.section;
            row.appendChild(celda5);
            let celda6 = document.createElement('td');
            celda6.innerHTML = response.positionStaff.privilege;
            row.appendChild(celda6);

            tblBody.appendChild(row);
            table.appendChild(tblBody);
        })
    }

    async function addReceipt() {
        let form = document.getElementById('addReceipt');
        form.addEventListener('submit', async(e) => {
            e.preventDefault();
            const querystring = location.search;
            const params = new URLSearchParams(querystring)
            let id = params.get("id");
            if (id == undefined) id = 1
            let fecha = document.getElementById('inputDateReceiptA');
            let subtotal = document.getElementById('inputSubtotalA');
            let descuento = document.getElementById('inputDiscountA');
            let iva = document.getElementById('inputIvaA');
            let total = document.getElementById('inputTotalA');
            
            let ivavalue = (iva.value/100);
            console.log('valor del iva: ' + ivavalue);
            iva.innerHTML = ivavalue;
            subtotal.innerHTML = total.value - (total.value * ivavalue);
            console.log('subtotal: ' + subtotal.value);
            if (descuento.value.length != 0) {
                total = total - (total * (descuento.value/100));
            }

            let data = {
                receiptDate: fecha.value,
                discounts: descuento.value,
                subtotal: subtotal.value,
                iva: iva.value,
                total: total.value,
            }
    
            let url = 'http://localhost:8080/api/v1/receipts'
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
    
            location.href = 'receipt.html?id=' + id;
        })
    }

    async function updateReceipt() {
        let form = document.getElementById('updateReceipt')
    
        form.addEventListener('submit', async(e) => {
            const querystring = location.search;
            const params = new URLSearchParams(querystring)
            let id = params.get("id");
            if (id == undefined) id = 1
            e.preventDefault();
            let fecha = document.getElementById('inputDateReceiptM');
            let subtotal = document.getElementById('inputSubtotalM');
            let descuento = document.getElementById('inputDiscountM');
            let iva = document.getElementById('inputIvaM');
            let total = document.getElementById('inputTotalM');
    
            let data = {
                receiptDate: fecha.value,
                discounts: descuento.value,
                subtotal: subtotal.value,
                iva: iva.value,
                total: total.value,
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
    
            location.href = 'receipt.html?id=' + id;
        })
    }

    async function deleteReceipt() {
        let form = document.getElementById('deleteReceipt')
        form.addEventListener('submit', async(e) => {
            e.preventDefault();
            const querystring = location.search;
            const params = new URLSearchParams(querystring)
            let id = params.get("id");
            let url = 'http://localhost:8080/api/v1/receipts/' + id;
            let deleteInit = {
                method: 'DELETE',
                headers: {
                    'Content-Type': 'application/json',
                    'Accept': 'application/json'
                }
            }
    
            await fetch(url, deleteInit)
                .then(response => console.log(response))
    
            location.href = 'receipt.html';
        })
    
    }

    async function loadAllReceipts() {
            let url = 'http://localhost:8080/api/v1/receipts';
            let getInit = {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json',
                    'Accept': 'application/json'
                }
            }
            let table = document.getElementById('tableAllReceipts');
            let tblBody = document.getElementById('bodyTableReceipts');
            await fetch(url, getInit)
            .then(response => response.json())
            .then (response => {
                let row = document.createElement('tr');
                let celda1 = document.createElement('td');
                celda1.innerHTML = response.id;
                row.appendChild(celda1);
                let celda2 = document.createElement('td');
                celda2.innerHTML = response.receiptDate;
                row.appendChild(celda2);
                let celda3 = document.createElement('td');
                celda3.innerHTML = response.discounts;
                row.appendChild(celda3);
                let celda4 = document.createElement('td');
                celda4.innerHTML = response.subtotal;
                row.appendChild(celda4);
                let celda5 = document.createElement('td');
                celda5.innerHTML = response.iva;
                row.appendChild(celda5);
                let celda6 = document.createElement('td');
                celda6.innerHTML = response.total;
                row.appendChild(celda6);

                tblBody.appendChild(row);
                table.appendChild(tblBody);
            })

    }