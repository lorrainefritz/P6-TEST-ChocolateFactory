function validate() {
	var name = document.getElementById("name").value;
	if (emptyName()) {
		document.getElementById("error").innerHTML = "Le champs du nom est vide signé JS";
		event.preventDefault();
	}
}
function validateLogin() {
	var name = document.getElementById("name").value;
	var password=document.getElementById("password").value;
	if (emptyName()) {
		document.getElementById("error_name").innerHTML = "Le champs du nom est vide signé JS";
		event.preventDefault();
	}
	if (emptyPassword()) {
		document.getElementById("error_password").innerHTML = "Le champs du nom est vide signé JS";
		event.preventDefault();
	}
}

function emptyName() {
	var name = document.getElementById("name").value;
	if (name == "") {
		return true;
	}
function emptyPassword() {
		var name = document.getElementById("password").value;
		if (name == "") {
			return true;
		}
	function pomme() {
		var name = document.getElementById("name").value;
		alert(name);
	}

	function poire() {
		alert("poire");
	}

}