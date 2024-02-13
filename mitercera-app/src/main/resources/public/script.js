function loadGetMsg() {
    let nameVar = document.getElementById("name").value;
    const xhttp = new XMLHttpRequest();
    xhttp.onload = function() {
        document.getElementById("getrespmsg").innerHTML =
            this.responseText;
    }
    xhttp.open("GET", "/title?name=" + nameVar);
    xhttp.send();
    xhttp.overrideMimeType('text/html');
    xhttp.open("GET", "/title?name=" + nameVar);
    xhttp.send();
}
