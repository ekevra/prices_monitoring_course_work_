const url = 'http://localhost:8080/controller_war';

const categoryRequest = new XMLHttpRequest();
categoryRequest.open('GET', url + '/categories/all', false);
var categories = [];
categoryRequest.onload = function() {
    categories = JSON.parse(this.response);
}
categoryRequest.send();

const subcategoryRequest = new XMLHttpRequest();
subcategoryRequest.open('GET', url + '/subcategories/all', false);
var subcategories = [];
subcategoryRequest.onload = function() {
    subcategories = JSON.parse(this.response);
}
subcategoryRequest.send();

const storesRequest = new XMLHttpRequest();
storesRequest.open('GET', url + '/stores/all', false);
var stores = [];
storesRequest.onload = function() {
    stores = JSON.parse(this.response);
}
storesRequest.send();

var verstka = '';
var isFirstSubcategory = true;
var countOfSubcategories = 0;
for(var i = 0; i < categories.length; i++) {   
    verstka += "<li><a href=\"#\">" + categories[i].name + "</a>";

    for(var j = 0; j < subcategories.length; j++) {
        if(subcategories[j].category.id === categories[i].id) {

            // var products = getProductsBySubcategory(subcategories[j].id);

            if(isFirstSubcategory === true) {
                verstka += "<ul>";
                isFirstSubcategory = false;
            }
            countOfSubcategories++;
            verstka += "<li><a href=\"javascript:getProductsBySubcategory(" + subcategories[j].id +
             ");\">" + subcategories[j].name + "</a></li>";
        }               
    }
    isFirstSubcategory = true;
    if(countOfSubcategories > 0) {
        verstka += "</ul>";
        countOfSubcategories = 0;
    }    
    verstka += "</li>";        
}
document.getElementById('categoriesUl').innerHTML = verstka;

var verstkaForStores = '';
for(var i = 0; i < stores.length; i++) {   
    verstkaForStores += "<li><a href=\"#\">" + stores[i].name + ", " + stores[i].city + ", " + stores[i].address + "</a></li>";       
}
document.getElementById('storesUl').innerHTML = verstkaForStores;

function contactsButtonClick(){
    document.getElementById('mainText').innerHTML = "<h2 style=\"text-align: center;\">Наши контакты:</h2><div>Электронная почта: ekevra@yandex.ru</div><div>Мобильные телефоны: +375-29-57-11-329</div><div>+375-44-557-94-09  +375-29-588-89-04</div>";
}

function getProductsBySubcategory(subcategoryId){
    const productRequest = new XMLHttpRequest();
    productRequest.open('GET', url + '/products/subcategory/' + subcategoryId, false);
    var products = [];
    productRequest.onload = function() {
        products = JSON.parse(this.response);
    }
    productRequest.send();

    var subcategoryDiv = "";
    subcategoryDiv += "<h2 style=\"text-align: center; background: rgba(232, 228, 169, 0.4);\">" +
    "Продукты из подкатегории: <span style=\"color: rgb(25, 25, 112)\">" + products[0].subcategory.name + "</span></h2>";
    document.getElementById('productsDiv').innerHTML = subcategoryDiv;

    var productsVerstka = "";
    productsVerstka += "<table cellspacing=\"0\"><tr><th>Наименование продукта</th><th>Производитель</th><th>Вес</th>" + 
    "<th>Цена</th><th>Магазины</th></tr>";
    for(var i = 0; i < products.length; i++) {
        if(products[i].name != undefined) {
            productsVerstka += "<tr><td>" + products[i].name + "</td>";
        } else {
            productsVerstka += "<tr><td></td>";
        }

        if(products[i].manufacture != undefined) {
            productsVerstka += "<td>" + products[i].manufacture + "</td>";
        } else {
            productsVerstka += "<td></td>";
        }

        if(products[i].weight != undefined) {
            productsVerstka += "<td>" + products[i].weight + "</td>";
        } else {
            productsVerstka += "<td></td>";
        }

        if(products[i].price != undefined && products[i].price.price != undefined) {
            productsVerstka += "<td>" + products[i].price.price + "</td>";
        } else {
            productsVerstka += "<td></td>";
        }

        if(products[i].stores != undefined && products[i].stores.length != 0) {            
            productsVerstka += "<td>" + getArrayOfUniqueStores(products[i].stores).join() + "</td>";
        } else {
            productsVerstka += "<td></td></tr>";
        }
    }
    productsVerstka += "</table>";
    document.getElementById('productTable').innerHTML = productsVerstka;
}

function getArrayOfUniqueStores(stores) {
    var uniqueStoresNames = [];
    var count = 0;
    for (var i = 0; i < stores.length; i++) {
        for (var j = 0; j < uniqueStoresNames.length; j++) {
            if(uniqueStoresNames[j] == stores[i].name) {
                count++;
            }
        }
        if (count > 0) {
            count = 0;
        } else {
            uniqueStoresNames.push(stores[i].name);
        }
    }
    
    return uniqueStoresNames;
}