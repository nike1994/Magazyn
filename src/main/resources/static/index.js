function getCheckedBoxes(){
    var inputs = document.getElementsByTagName('input');
    var checkboxesChecked = [];

    for(var i=0; i<inputs.length;i++){
        if(inputs[i].type.toLowerCase() == 'checkbox'){
            if(inputs[i].checked){
                checkboxesChecked.push(inputs[i]);
            }
        }
    }

    return checkboxesChecked.length >0 ? checkboxesChecked : null;
}

function getIDFromCheckboxes(array){
    var IDs = [];
    for(var i=0; i<array.length;i++){
        IDs.push(parseInt(array[i].name));
    }
    return IDs;
}

function getQuantityChecked(arraycheckboxes){
    var quantityArray = [];
    for(var i=0; i<arraycheckboxes.length;i++){
        var quantityBox = arraycheckboxes[i].parentElement.parentElement.getElementsByClassName("quantity-box")[0];
        quantityArray.push(quantityBox.textContent);
    }
    return quantityArray;
}

function ajax(url,type,object,callback){
    console.log(object);
   var xmlhttp = new XMLHttpRequest();   // new HttpRequest instance
    xmlhttp.open(type, url);
    xmlhttp.setRequestHeader("Content-Type", "application/json");
    xmlhttp.onreadystatechange = function () {
        try {
            responseText = xmlhttp.responseText
        } catch (e) {
            alert(e.toString());
        }
        console.log(responseText);
        if(xmlhttp.responseText=="ok"){
            window.location.reload(true);
        }
    }
    //xmlhttp.onreadystatechange = callback;
    xmlhttp.send(JSON.stringify(object));

}

function deleteProduct(){
    var checkedBoxes = getCheckedBoxes();
    if(checkedBoxes == null){
        alert("nie zaznaczyłeś żadnego produktu do usunięcia");
    }else{
        var checkedBoxesID =getIDFromCheckboxes(checkedBoxes);
        ajax("/removeProducts","POST",{"id": checkedBoxesID})
    }
}

function increaseQuantity(){
       var checkedBoxes = getCheckedBoxes();
       if(checkedBoxes == null){
           alert("nie zaznaczyłeś żadnego produktu do zmienienia");
       }else{
           var editBox = document.getElementById("editBox");
           editBox.classList.toggle('transparent');

           document.getElementById('change').onclick= function(){
                var changeValue = parseInt(editBox.getElementsByTagName('input')[0].value);
                var checkedBoxesID = getIDFromCheckboxes(checkedBoxes);

                ajax("/increaseQuantity","POST",{"id": checkedBoxesID, "quantity":changeValue});

                editBox.classList.toggle('transparent');
           }


       }
}

function reduceQuantity(){
           var checkedBoxes = getCheckedBoxes();
           if(checkedBoxes == null){
               alert("nie zaznaczyłeś żadnego produktu do zmienienia");
           }else{
               var editBox = document.getElementById("editBox");
               editBox.classList.toggle('transparent');

               document.getElementById('change').onclick= function(){
                   var changeValue = parseInt(editBox.getElementsByTagName('input')[0].value);
                   var quantityCheckedProducts = getQuantityChecked(checkedBoxes);
                   var checkedBoxesID = getIDFromCheckboxes(checkedBoxes);
                   var error = false;

                   for(var i=0; i<quantityCheckedProducts.length;i++){
                       if(changeValue>quantityCheckedProducts[i]){
                            alert("wartość o którą zmniejszamy nie może być większa od ilości produktów w magazynie")
                            error=true;
                       }
                   }

                   editBox.classList.toggle('transparent');

                   if(!error){
                        ajax("/reduceQuantity","POST",{"id": checkedBoxesID, "quantity":changeValue});
                   }
               }
           }
}
