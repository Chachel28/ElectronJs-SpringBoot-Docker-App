async function loadLastStaffs() {
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
    let urlReceipt = 'http://localhost:8080/api/v1/receipt/' + idReceipt;
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