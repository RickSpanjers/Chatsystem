$( document ).ready(function() {
    $( "#resetPassword" ).hide();
});


function loggedIn(userId, mail) {
    setCookie("userId", userId, 5);
    setCookie("userMail", mail, 5);
}