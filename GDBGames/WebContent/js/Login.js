var xhr = new XMLHttpRequest();
var xhr1 = new XMLHttpRequest();
var formEmail = document.getElementById("username");
var formEmailReg = document.getElementById("emailReg");
var EmailRegistrata = document.getElementById('EmailRegistrata');
var EmailNonRegistrata = document.getElementById("EmailNonRegistrata");
var emailReg = document.getElementById("EmailRegistrataReg");

function isValid(email) {
	const regexUser = /^[a-z0-9._%-]+@[a-z0-9.-]+\.[a-z]{2,4}$/;
	return regexUser.test(email);
}

function sendEmailCheckRequest(xhr, email, callback) {
	xhr.open("POST", "./LoginJSON", true);
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhr.setRequestHeader("Accept", "application/json");
	xhr.onreadystatechange = callback;
	xhr.send("email=" + encodeURIComponent(email));
}

formEmail.addEventListener('keyup', function(event) {
	let email = formEmail.value;
	if (isValid(email)) {
		sendEmailCheckRequest(xhr, email, function() {
			if (xhr.readyState === 4 && xhr.status === 200) {
				var response = JSON.parse(xhr.responseText);
				if (response.status === true) {
					changeForm("logIn");
					EmailRegistrata.style.display = 'block';
					EmailNonRegistrata.style.display = 'none';
					setTimeout(function() {
						EmailRegistrata.style.display = 'none';
					}, 5000);
				} else {
					EmailNonRegistrata.style.display = 'block';
					EmailRegistrata.style.display = 'none';
					setTimeout(function() {
						EmailNonRegistrata.style.display = 'none';
					}, 5000);
				}
			}
		});
	}
});

formEmailReg.addEventListener('keyup', function(event) {
	let email = formEmailReg.value;
	if (isValid(email)) {
		sendEmailCheckRequest(xhr1, email, function() {
			if (xhr1.readyState === 4 && xhr1.status === 200) {
				let response = JSON.parse(xhr1.responseText);
				if (response.status === true) {
					emailReg.style.display = "block";
				}
			}
		});
	}
});


function changeForm(str) {
	let lForm = document.getElementById("loginForm");
	let sForm = document.getElementById("signUpForm");
	
	if (str === "Sign Up") {
		lForm.style.display = "none";
		sForm.style.display = "block";
	} else {
		sForm.style.display = "none";
		lForm.style.display = "block";
	}
}
