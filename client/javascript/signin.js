function checkingLoginAndPassword(){

    if (document.getElementById("inputLogin").value == '' || document.getElementById("inputLogin").value == undefined) {
        alert("Введите логин.");
    } else {
        var login = document.getElementById("inputLogin").value;
        var password = document.getElementById("inputPassword").value;

        const url = 'http://localhost:8080/controller_war';

        const request = new XMLHttpRequest();
        request.open('GET', url + '/user/' + login, true);
        
        request.onload = function() {
            var creds = JSON.parse(this.response);
            console.log(request.status);
        }
        
        request.send(); 

        if (creds !== undefined && creds.password === password) {
            document.location.href = "categoriesPage.html";
        } else {
            alert("Неправильный логин или пароль.");
        }
    }
}