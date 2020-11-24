async function loadSalesList(){
    let url = 'http://localhost:8080/api/v1/sales'
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
        console.log(response)
        let final = 1;
        if (response.length > 20){
            final = 20;
        } else {
            final = response.length;
        }
        for (let i = 0; i < final; i++) {
            let a = document.createElement('a');
            let urlClient = 'clients.html?id=' + response[i].client;
            a.setAttribute('href', urlClient);

            let li = document.createElement('li');

            let ii = document.createElement('i');
            ii.setAttribute('class', 'fas fa-clipboard mr-2');
            li.appendChild(ii);

            li.innerHTML = li.innerHTML + ' ' + response[i].id + ' - ' + response[i].receipt.receiptDate;

            a.appendChild(li);

            document.getElementById('LastClientList').appendChild(a);
        }
    })
}