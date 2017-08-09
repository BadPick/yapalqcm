var chrono = document.getElementById("chrono");
var compteur = Number(chrono.textContent);
var compteurInvisible = compteur;
var tempsEcoule = document.getElementById("tempsEcoule");

// Diminue le compteur jusqu'à 0
function DiminuerCompteur() {
	chrono.style.visibility="visible";
    // Conversion en nombre du texte du compteur
    if (compteurInvisible > 1) {
    	chrono.textContent = secondsToTime(--compteurInvisible);
    } else {
    	chrono.textContent = secondsToTime(0);
        // Annule l'exécution répétée
        clearInterval(intervalId);
    }
    tempsEcoule.value=compteur-compteurInvisible.toString();
}
// Appelle la fonction DiminuerCompteur toutes les secondes (1000 millisecondes)
var intervalId = setInterval(DiminuerCompteur, 1000);

function secondsToTime (seconds) {
    var hours   = Math.floor(seconds / 3600);
    var minutes = Math.floor((seconds - (hours * 3600)) / 60);
    var seconds = seconds - (hours * 3600) - (minutes * 60);
    var time = "";

    if (hours != 0) {
      time = hours+":";
    }
    if (minutes != 0 || time !== "") {
      minutes = (minutes < 10 && time !== "") ? "0"+minutes : String(minutes);
      time += minutes+":";
    }
    if (time === "") {
      time = seconds+"s";
    }
    else {
      time += (seconds < 10) ? "0"+seconds : String(seconds);
    }
    return time;
}

function envoyerChrono(tempsEcoule){
	document.getElementById("tempsEcoule").value=tempsEcoule+(compteur-compteurInvisible);
}

function envoyerChronoParam(tempsEcoule ,numeroQuestion){
	document.getElementById("tempsEcoule"+numeroQuestion).value=tempsEcoule+(compteur-compteurInvisible);
}