$(document).ready(function () {
    $("#chatFriend").hide();
    $("#userProfile").hide();
    $("#addFriend").hide();
    checkLoggedIn();

    document.getElementById("chatInput")
        .addEventListener("keyup", function (event) {
            event.preventDefault();
            if (event.keyCode === 13) {
                document.getElementById("sendChatMsgBtn").click();
            }
        });

});

function checkLoggedIn() {
    if (getCookie("userId") == null) {
        alert("No cookie set");
        logout();
        window.location.replace("http://localhost/Fontys/S3/Personal/chat/chatsystem/chatsystemgui/pages/login.php");
    } else {
        document.getElementById("accountId").value = getCookie("userId");
        document.getElementById("accountMail").value = getCookie("userMail");
        $('.single-chat').remove();
        seeFriendList();
        getChatrooms();
    }
}

function showChat() {
    $("#userProfile").hide();
    $("#friendList").hide();
    $("#addFriend").hide();
    $("#chatFriend").show();
}

function showFriends() {
    $("#userProfile").hide();
    $("#chatFriend").hide();
    $("#addFriend").hide();
    $("#friendList").show();
    $('.single-friend').remove();
    seeFriendList();
    document.getElementById("currentOpenChat").value = 0;
}

function showProfile() {
    $("#addFriend").hide();
    $("#friendList").hide();
    $("#chatFriend").hide();
    $("#userProfile").show();
    getProfileData();
    document.getElementById("currentOpenChat").value = 0;
}

function showAddFriend() {
    $("#addFriend").show();
    $("#friendList").hide();
    $("#chatFriend").hide();
    $("#userProfile").hide();
    document.getElementById("currentOpenChat").value = 0;
}