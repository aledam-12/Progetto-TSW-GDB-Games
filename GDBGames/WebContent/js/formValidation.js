var lForm = document.getElementById("loginForm");
var sForm = document.getElementById("signUpForm");

function changeForm(str) {
    if (str === "Sign Up") {
        lForm.style.display = "none";
        sForm.style.display = "block";
    } else {
        sForm.style.display = "none";
        lForm.style.display = "block";
    }
}
function showMessage (field) {
	let temp = document.getElementById(field+"-feed");
	temp.style.display = "block";
}
function hideMessage (field) {
	let temp = document.getElementById(field+"-feed");
	temp.style.display = "none";
}


// Validazione Email
let email = document.getElementById("emailReg");
const regexEmail = /^[a-z0-9._%-]+@[a-z0-9.-]+\.[a-z]{2,4}$/;

email.addEventListener('input', function(event) {
     
    let valEmail = email.value;
    if (!regexEmail.test(valEmail)) {
        event.preventDefault();
        showMessage("email");
    }
    else hideMessage("email");
});

// Validazione Password
let password = document.getElementById("reg-password");
const regexPass = /^[A-Za-z0-9.]{3,16}$/;

password.addEventListener('input', function(event) {
     
    let passwordVal = password.value;
    if (!regexPass.test(passwordVal)) {
        event.preventDefault();
        showMessage("password");
    }
    else hideMessage("password");
});

// Validazione Nome
let nome = document.getElementById("reg-nome");
const regexNome = /^[A-Za-z]{2,30}$/;

nome.addEventListener('input', function(event) {
     
    let nomeVal = nome.value;
    if (!regexNome.test(nomeVal)) {
        event.preventDefault();
        showMessage("nome");
    }
    else hideMessage("nome");
});

// Validazione Cognome
let cognome = document.getElementById("reg-cognome");
const regexCognome = /^[A-Za-z]{2,30}$/;

cognome.addEventListener('input', function(event) {
     
    let cognomeVal = cognome.value;
    if (!regexCognome.test(cognomeVal)) {
        event.preventDefault();
        showMessage("cognome");
    }
    else hideMessage("cognome");
});

// Validazione Via
let via = document.getElementById("reg-via");
const regexVia = /^[A-Za-z ]{2,30}$/;

via.addEventListener('input', function(event) {
     
    let viaVal = via.value;
    if (!regexVia.test(viaVal)) {
        event.preventDefault();
        showMessage("via");
    }
    else hideMessage("via");
});

// Validazione Civico
let civico = document.getElementById("reg-civico");
const regexCivico = /^\d{1,3}$/;

civico.addEventListener('input', function(event) {
     
    let civicoVal = civico.value;
    if (!regexCivico.test(civicoVal)) {
        event.preventDefault();
        showMessage("civico");
    }
    else hideMessage("civico");
});

// Validazione CAP
let cap = document.getElementById("reg-cap");
const regexCap = /^\d{5}$/;

cap.addEventListener('input', function(event) {
     
    let capVal = cap.value;
    if (!regexCap.test(capVal)) {
        event.preventDefault();
        showMessage("cap");
    }
    else hideMessage("cap");
});

// Validazione Provincia
let provincia = document.getElementById("reg-provincia");
const regexProvincia = /^[A-Z]{2}$/;

provincia.addEventListener('input', function(event) {
     
    let provinciaVal = provincia.value;
    if (!regexProvincia.test(provinciaVal)) {
        event.preventDefault();
        showMessage("provincia");
    }
    else hideMessage ("provincia");
});

// Validazione Città
let citta = document.getElementById("reg-citta");
const regexCitta = /^[A-Za-z ]{2,30}$/;

citta.addEventListener('input', function(event) {
     
    let cittaVal = citta.value;
    if (!regexCitta.test(cittaVal)) {
        event.preventDefault();
        showMessage("citta");
    }
    else hideMessage("citta");
});
