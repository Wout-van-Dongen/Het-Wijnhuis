//Variables
var input;
var errorbox;
var button;

//Listeners
window.addEventListener('load', init);




//Methods

function disableButton(bool) {
    button.disabled = bool;
}

function init() {

    input = document.getElementById("input");
    errorbox = document.getElementById("error_box");
    button = document.getElementById("toevoegen");

    input.addEventListener('mouseup', checkInput);
    input.addEventListener('keyup', checkInput);
    
    input.value = 1;

    disableButton(false);
}

function checkInput() {
    var value = parseInt(input.value);
    if(isNaN(value)){
     
        errorbox.innerHTML="Gelieve een getal in te vullen!";
        disableButton(true);
    }else   if (value <= 0) {
        errorbox.innerHTML = "Een bestelling moet minimum uit 1 artikel bestaan!";
        disableButton(true);
    }else {
        errorbox.innerHTML="";
        disableButton(false);
    }
}