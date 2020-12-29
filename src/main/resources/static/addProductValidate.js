function validate(){
    var EAN= document.getElementsByName("EAN")[0];
    var quantity= document.getElementsByName("quantity")[0];
    var name = document.getElementsByName('name')[0];
    console.log("ean: "+EAN);
    console.log("quantity: "+quantity);
    console.log("name: "+name);

    var regexEAN = new RegExp("[0-9]{12}");
    var regexQuantity = new RegExp("[0-9]+");
    var flag = true;

//na wszelki wypadek zostawiam oznaczanie pól

    if(name.value == "" || name.value == null) {
        EAN.style.backgroundColor = 'RED';
        flag = false;
    } else {
        EAN.style.backgroundColor = 'WHITE';
    }

    if(!regexEAN.test(EAN.value)) {
        console.log(EAN.value)
        EAN.style.backgroundColor = 'RED';
        flag=false;
    } else {
        EAN.style.backgroundColor = 'WHITE';
    }

    if(!regexQuantity.test(quantity.value)) {
        quantity.style.backgroundColor = 'RED';
        flag = false;
    } else {
        quantity.style.backgroundColor = 'WHITE';
    }

    return flag;
}

document.getElementById("EAN").oninput = function(){

   var EAN= document.getElementsByName("EAN")[0];
   var regexEAN = new RegExp("[0-9]{12}");

  if (regexEAN.test(EAN.value)) {
    EAN.setCustomValidity("");
  }
  else {
    EAN.setCustomValidity("kod musi posiadać 12 cyfr");
  }
};