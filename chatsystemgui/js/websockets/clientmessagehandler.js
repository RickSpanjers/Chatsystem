function handleMessage(operation, message){
    switch(operation){
        case "REGISTERSUCCESS":
            notifyRegistrationSuccess();
            break;
        case "REGISTERFAIL":
            notifyRegistrationFail();
            break;
        case "LOGINSUCCESS":
            notifyLoginSuccess(message);
            break;
        case "LOGINFAIL":
            notifyLoginFail();
            break;
        case "FORGOTPASSWORDSUCCESS":
            notifyForgotPasswordSuccess(message);
            break;
        case "RESETPASSWORDSUCCESS":
            notifyResetPasswordSuccess();
            break;
        case "FORGOTMAILSUCCESS":
            notifyForgotMailSuccess(message);
            break;
        case "REQUESTFAIL":
            notifyErrorMsg();
            break;
        case "SEEFRIENDLIST":
            notifySeeOnlineList(message);
            break;
        case "USERPROFILE":
            notifyRetrieveProfile(message);
            break;
        case "UPDATEPROFILESUCCESS":
            notifyUpdateProfileSuccess();
            break;
        case "OPENCHATSUCCESS":
            notifyOpenChatSuccess(message);
            break;
        case "SENDMSGSUCCESS":
            notifySendMsgSuccess(message);
            break;
        case "RECEIVEMSG":
            notifyReceiveMsg(message);
            break;
        case "SEARCHRESULT":
            notifySearchResults(message);
            break;
        case "ADDFRIENDSUCCESS":
            notifyAddFriendSuccess();
            break;
        case "GETCHATROOMSSUCCESS":
            notifyOpenChatroomsSuccess(message);
            break;
    }


    function notifyRegistrationSuccess() {
        window.location.replace("http://localhost/Fontys/S3/Personal/chat/chatsystem/chatsystemgui/pages/login.php");
    }


    function notifyRegistrationFail(){
        document.getElementById("resultRegister").innerHTML = "Registration unsuccessful";
    }


    function notifyLoginSuccess(message){
        let user ={id:"", username:"", mail:""};
        user = JSON.parse(message);
        loggedIn(user.id, user.mail);
        window.location.replace("http://localhost/Fontys/S3/Personal/chat/chatsystem/chatsystemgui/pages/account.php");
    }


    function notifyLoginFail(){
        document.getElementById("resultLogin").innerHTML = "Login unsuccessful";
    }


    function notifyErrorMsg(){
        document.getElementById("result").innerHTML = "Request failed";
    }


    function notifyForgotPasswordSuccess(message) {
        let user ={id:"", username:"", mail:"", password:""};
        user = JSON.parse(message);
        $( "#forgetPassword" ).hide();
        $( "#resetPassword" ).show();
        document.getElementById("pageTitleForgetPass").innerHTML = "Reset password";
        document.getElementById("resetMail").value = user.mail;
        document.getElementById("userId").value = user.id;
    }


    function notifyResetPasswordSuccess() {
        document.getElementById("result").innerHTML = "Password successfully changed";
    }


    function notifyForgotMailSuccess(message) {
        document.getElementById("result").innerHTML = "Account mail is " + message;
    }


    function notifySeeOnlineList(message){
        let jsonData = JSON.parse(message);
        for (let i = 0; i < jsonData.length; i++) {
            let counter = jsonData[i];
            var online = "offline";
            if(counter.online == true){
                online = "online";
            }
            $('<div class="single-friend" id="singleFriend'+i+'"><div class="row"><div class="col-md-3"><p>'+counter.username+'</p><input type="hidden" id="userOnline'+i+'" value="'+counter.id+'"></div><div class="col-md-3"><p class="big-status-'+online+'">'+online+'</p></div><div class="col-md-3"><p>None</p></div></div></div>').appendTo(document.getElementById("currentFriendsInSystem"));
        }
        for(let i = 0; i < jsonData.length; i++){
            let counter = jsonData[i];
            var inputElement = document.getElementById('singleFriend'+i);
            inputElement.addEventListener('click', function(){
                openChat(counter.id, counter.mail);
            });

        }
    }


    function notifyRetrieveProfile(message){
        let profile ={username:"", mail:"", firstname:"", lastname:"", phone:"", address:"", zipcode:"", place:""};
        profile = JSON.parse(message);
        document.getElementById("AccountUsername").value = profile.username;
        document.getElementById("AccountUpdateMail").value = profile.mail;
        document.getElementById("AccountFirstname").value = profile.firstname;
        document.getElementById("AccountLastname").value = profile.lastname;
        document.getElementById("AccountAddress").value = profile.address;
        document.getElementById("AccountZipcode").value = profile.zipcode;
        document.getElementById("AccountPhone").value = profile.phone;
        document.getElementById("AccountPlace").value = profile.place;
    }


    function notifyUpdateProfileSuccess(){
        alert("Profile successfully updated");
    }


    function notifyOpenChatSuccess(message){
        console.log(message);
        $('.chatmsg').remove();
        showChat();

        let jsonData = JSON.parse(message);
        for(let i = 0; i < jsonData.users.length; i++){
            let counter = jsonData.users[i];
            if(document.getElementById("accountId").value != counter.id) {
                document.getElementById("chatPersonName").innerHTML = "@"+counter.username;
                document.getElementById("chatInput").placeholder = "Send a message to @" +counter.username;
                document.getElementById("chatPartnerId").value = counter.id;
                document.getElementById("currentOpenChat").value = jsonData.id;
            }
        }

        var inputElementToRemove = document.getElementById('sendChatMsgBtn');
        inputElementToRemove.remove();

        $('<div class="btn-chat-send" id="sendChatMsgBtn">Send</div>').appendTo(document.getElementById("btnSendLocation"));
        var inputElement = document.getElementById('sendChatMsgBtn');
        inputElement.addEventListener('click', function(){
            sendPrivateMessage(document.getElementById("accountId").value, document.getElementById("chatPartnerId").value, document.getElementById("chatInput").value);
        });

        for(let i = 0; i < jsonData.messages.length; i++){
            let counter = jsonData.messages[i];
            if(counter.userSent.id == document.getElementById("accountId").value){
                $('<div class="chatmsg"><div class="row"><div class="col-md-1 friend-icon-col"><img class="friend-icon" src="../images/noimage.jpg"></div><div class="col-md-11"><p class="sender-details">You<span class="send-details"> '+counter.sent+'</span></p><div class="chat-content"><p class="msg-sentence">'+counter.content+'</p></div></div> </div> </div>').appendTo(document.getElementById("chatMessageList"));
            }else{
                $('<div class="chatmsg"><div class="row"><div class="col-md-1 friend-icon-col"><img class="friend-icon" src="../images/noimage.jpg"></div><div class="col-md-11"><p class="sender-details">'+document.getElementById("chatPersonName").innerHTML+'<span class="send-details"> '+counter.sent+'</span></p><div class="chat-content"><p class="msg-sentence">'+counter.content+'</p></div></div> </div> </div>').appendTo(document.getElementById("chatMessageList"));
            }
        }
    }


    function notifySendMsgSuccess(message){
        let newmessage ={userThatSendsId:"", userThatReceivesId:"", message:""};
        newmessage = JSON.parse(message);
        var d = new Date();
        $(' <div class="chatmsg"><div class="row"><div class="col-md-1 friend-icon-col"><img class="friend-icon" src="../images/noimage.jpg"></div><div class="col-md-11"><p class="sender-details">You<span class="send-details"> Today at '+ d.getHours()+':'+ d.getMinutes()+'</span></p><div class="chat-content"><p class="msg-sentence">'+newmessage.message+'</p></div></div> </div> </div>').appendTo(document.getElementById("chatMessageList"));
        document.getElementById("chatInput").value = "";
    }


    function notifyReceiveMsg(message){
        let newmessage ={userThatSendsId:"", userThatReceivesId:"", message:""};
        newmessage = JSON.parse(message);
        console.log(message);
        var d = new Date();

        var chatId = parseInt(newmessage.userThatSendsId) + parseInt(newmessage.userThatReceivesId);
        let element = document.getElementById("unreadMsgChat"+chatId);
        let elementNr = document.getElementById("unreadMsgChatNr"+chatId);

        if(document.getElementById("currentOpenChat").value == newmessage.userThatSendsId + newmessage.userThatReceivesId){
            $(' <div class="chatmsg"><div class="row"><div class="col-md-1 friend-icon-col"><img class="friend-icon" src="../images/noimage.jpg"></div><div class="col-md-11"><p class="sender-details">'+document.getElementById("chatPersonName").innerHTML+'<span class="send-details"> Today at '+ d.getHours()+':'+ d.getMinutes()+'</span></p><div class="chat-content"><p class="msg-sentence">'+newmessage.message+'</p></div></div> </div> </div>').appendTo(document.getElementById("chatMessageList"));
        }
        else{
            element.style.display = "block";
            elementNr.innerHTML = parseInt(elementNr.innerHTML)+1;
        }
    }


    function notifySearchResults(message){
        let jsonData = JSON.parse(message);
        for(let i = 0; i < jsonData.length; i++){
            let counter = jsonData[i];
            $('<div class="single-search-user"><div class="row"><div class="col-md-1 friend-icon-col"><img class="friend-icon" src="../images/noimage.jpg"></div><div class="col-md-11"><p class="friend-name">'+counter.username+'</p><input type="hidden" value="0"><div class="btn-add-friend-small" onclick="addFriend('+counter.id+')">Add as friend</div></div></div></div>').appendTo(document.getElementById("searchResults"));
        }
    }


    function notifyAddFriendSuccess(){
        document.getElementById("addFriendResultText").innerHTML = "Friend successfully added";
    }


    function notifyOpenChatroomsSuccess(message){
        console.log(message);
        let jsonData = JSON.parse(message);
        for(let i = 0; i < jsonData.length; i++){
            let counter = jsonData[i].users;
            for(x = 0; x < counter.length; x++){
                let online = "offline";
                if(counter[x].online == true){
                    online = "online";
                }
                if(Boolean(counter[x].id == document.getElementById("accountId").value) == false){
                    var chatId = parseInt(counter[x].id) + parseInt(document.getElementById("accountId").value);
                    $('<div class="single-chat" id="chatId'+chatId+'"><div class="urlstyling"><div class="row"><div class="col-md-4 friend-icon-col"><img class="friend-icon" src="../images/noimage.jpg"></div><div class="col-md-8"><p class="friend-name">'+counter[x].username+'</p><p class="status-'+online+'">'+online+'</p><p class="unreadMsg" id="unreadMsgChat'+chatId+'" style="display:none;"><span class="newMsgNr" id="unreadMsgChatNr'+chatId+'">0</span> new message</p></div></div></div></div>').appendTo(document.getElementById("openChatsLeft"));
                }
            }
        }
    }

}