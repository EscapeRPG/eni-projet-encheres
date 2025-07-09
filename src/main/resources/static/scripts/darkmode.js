const darkButton = document.getElementById("darkmode");

darkButton.addEventListener("click", darkMode);

function darkMode() {
	if (document.documentElement.className == "darkmode") {
		document.documentElement.classList.remove("darkmode");
		localStorage.setItem("darkmode", "off");
		darkButton.innerText = "Dark";
	} else {
		document.documentElement.classList.toggle("darkmode");
		localStorage.setItem("darkmode", "on");
		darkButton.innerText = "Light";
	}
}

window.addEventListener("load", checkPreference);

function checkPreference() {
	if (localStorage.getItem("darkmode") == "on") {
		document.documentElement.classList.toggle("darkmode");
		darkButton.innerText = "Light";
	}
}