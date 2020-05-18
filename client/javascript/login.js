
const url = 'http://localhost:8080/controller_war';

const request = new XMLHttpRequest();
request.open('GET', url + '/categories/all', true);

request.onload = function() {
    var categories = JSON.parse(this.response);
}

request.send();

function getInputValue(){
    var name = document.getElementById("inputName").value;
    var surname = document.getElementById("inputSurname").value;
    var login = document.getElementById("inputLogin").value;
    var password = document.getElementById("inputPassword").value;
    var repeatPassword = document.getElementById("inputRepeatPassword").value;
    var email = document.getElementById("inputEmail").value;

    if(name == "" || surname == undefined || login == undefined
        || password == undefined || repeatPassword == undefined || email == undefined) {
            alert('Заполните все поля');
        }

    if(password !== repeatPassword) {
        alert('Пароли не совпадают');
    } else {
        
    }    
}
