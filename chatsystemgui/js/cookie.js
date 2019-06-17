
function getCookie(name) {
    let value = document.cookie;
    let selectedCookie = value.split("; ");
    let cookie = null;
    for (let i = 0; i < selectedCookie.length; i++) {
        if (selectedCookie[i].includes(name)) {
            cookie = selectedCookie[i];
        }
    }
    try {
        cookie = cookie.substring(cookie.indexOf("=") + 1);
    } catch (e) {
        console.log(e);
    }

    return cookie;
}

function setCookie(name, value, time) {
    let date = new Date();
    date.setMinutes(date.getMinutes() + time);
    document.cookie = name + "=" + value + "; expires=" + date.toUTCString();
}

