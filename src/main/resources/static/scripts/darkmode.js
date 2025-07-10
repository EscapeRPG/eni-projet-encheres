const darkButton = document.getElementById("darkmode"),
	logo = document.getElementById("logo-eni-encheres")
	returnIndex = document.getElementById("return-index");

darkButton.addEventListener("click", darkMode);
returnIndex.addEventListener("mouseover", logoHover);
returnIndex.addEventListener("mouseout", logoOut);

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

function logoHover() {
	if (localStorage.getItem("darkmode") == "off") {
		logo.src = "/images/logo-encheres-darkgoldenrod.png";
	} else {
		logo.src = "/images/logo-encheres-gold.png";
	}
}

function logoOut() {
	logo.src = "/images/logo-encheres.png";
}