
var webSocket = new WebSocket('ws://localhost:8095/chatsystem/');

webSocket.onerror = function(event) {
    onError(event)
};
webSocket.onopen = function(event) {
    onOpen(event)
};
webSocket.onmessage = function(event) {
    onMessage(event)
};
webSocket.onclose = function(event){
    onClose(event)
};


function onMessage(event) {
    document.getElementById('messages').innerHTML += '<br />' + event.data;
    processMessage(event);
}
function onOpen(event) {
    document.getElementById('messages').innerHTML = 'Connection established';
}
function onClose(event){
    document.getElementById('messages').innerHTML = 'Connection closed';
}
function onError(event) {
    alert("An unexpected error has occurred");
}


function login() {
    let data = {mail: document.getElementById("loginMail").value, password: document.getElementById("loginPass").value};
    let dataString = JSON.stringify(data);
    let completeObj = {operationMessage: "LOGIN", data: dataString};
    webSocket.send(JSON.stringify(completeObj));
}


function logout(){
    let data = {userId: document.getElementById("accountId").value, userMail: document.getElementById("accountMail").value};
    let dataString = JSON.stringify(data);
    let completeObj = {operationMessage: "LOGOUT", data: dataString};
    webSocket.send(JSON.stringify(completeObj));
    window.location.replace("http://localhost/Fontys/S3/Personal/chat/chatsystem/chatsystemgui/pages/login.php");
}


function register(){
    let data = {id: 0, username: document.getElementById("registerUsername").value, mail: document.getElementById("registerMail").value, password: document.getElementById("registerPass").value};
    let dataString = JSON.stringify(data);
    let completeObj = {operationMessage: "REGISTER", data: dataString};
    webSocket.send(JSON.stringify(completeObj));
}


function forgotPassword(){
    let data = {mail: document.getElementById("forgotPassMail").value};
    let dataString = JSON.stringify(data);
    let completeObj = {operationMessage: "FORGOTPASSWORD", data: dataString};
    webSocket.send(JSON.stringify(completeObj));
}


function resetPassword(){
    let data = {mail: document.getElementById("resetMail").value, password: document.getElementById("resetPass").value, confirmPassword: document.getElementById("resetConfirmPass").value};
    let dataString = JSON.stringify(data);
    let completeObj = {operationMessage: "RESETPASSWORD", data: dataString};
    webSocket.send(JSON.stringify(completeObj));
}


function rememberAccount(){
    let data = {phone: document.getElementById("phone").value};
    let dataString = JSON.stringify(data);
    let completeObj = {operationMessage: "FORGOTMAIL", data: dataString};
    webSocket.send(JSON.stringify(completeObj));
}


function seeFriendList(){
    let data = {userId: document.getElementById("accountId").value, userMail: document.getElementById("accountMail").value};
    let dataString = JSON.stringify(data);
    let completeObj = {operationMessage: "SEEFRIENDLIST", data: dataString};
    webSocket.send(JSON.stringify(completeObj));
}


function getProfileData(){
    let data = {userId: document.getElementById("accountId").value, userMail: document.getElementById("accountMail").value};
    let dataString = JSON.stringify(data);
    let completeObj = {operationMessage: "USERPROFILE", data: dataString};
    webSocket.send(JSON.stringify(completeObj));
}


function updateProfile(){
    let data = {id: document.getElementById("accountId").value, username:document.getElementById("AccountUsername").value, password:document.getElementById("AccountPassword").value, confirmPassword:document.getElementById("AccountConfirmPassword").value, firstname:document.getElementById("AccountFirstname").value, lastname:document.getElementById("AccountLastname").value, phone:document.getElementById("AccountPhone").value, address:document.getElementById("AccountAddress").value, zipcode:document.getElementById("AccountZipcode").value, place:document.getElementById("AccountPlace").value, mail: document.getElementById("AccountUpdateMail").value};
    let dataString = JSON.stringify(data);
    let completeObj = {operationMessage: "UPDATEPROFILE", data: dataString};
    webSocket.send(JSON.stringify(completeObj));
}


function openChat(id, mail){
    let data ={accountId: document.getElementById("accountId").value, friendUserId: id, friendMail: mail}
    let dataString = JSON.stringify(data);
    let completeObj = {operationMessage: "OPENCHAT", data: dataString};
    webSocket.send(JSON.stringify(completeObj));
    $('.single-chat').remove();
    getChatrooms();
}


function sendPrivateMessage(accountId, partnerId, message){
    console.log("sending message");
    let data ={userThatSendsId: accountId, userThatReceivesId: partnerId, message: message};
    let dataString = JSON.stringify(data);
    let completeObj = {operationMessage: "SENDMSG", data: dataString};
    webSocket.send(JSON.stringify(completeObj));
}


function searchUsers(){
    let data ={accountId: document.getElementById("accountId").value, searchText: document.getElementById("SearchInput").value};
    let dataString = JSON.stringify(data);
    let completeObj = {operationMessage: "SEARCH", data: dataString}
    webSocket.send(JSON.stringify(completeObj));
}


function addFriend(userToAdd){
    let data ={userToGainFriend: document.getElementById("accountId").value, userToBeFriend: userToAdd};
    let dataString = JSON.stringify(data);
    let completeObj = {operationMessage: "ADDFRIEND", data: dataString}
    webSocket.send(JSON.stringify(completeObj));
}


function getChatrooms(){
    let data = {userId: document.getElementById("accountId").value, userMail: document.getElementById("accountMail").value};
    let dataString = JSON.stringify(data);
    let completeObj = {operationMessage: "GETCHATROOMS", data: dataString};
    webSocket.send(JSON.stringify(completeObj));
}
