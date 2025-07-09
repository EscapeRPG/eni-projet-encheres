const darkButton = document.getElementById("darkmode");

darkButton.addEventListener("click", darkMode());

function darkMode() {
	if (document.documentElement.className == "darkmode") {
		document.documentElement.classList.remove("darkmode");
	} else {
		document.documentElement.classList.add("darkmode");
	}
}