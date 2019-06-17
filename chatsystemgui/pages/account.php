<!DOCTYPE html>
<html>
<head>
    <title>Chatsystem</title>
    <link rel="stylesheet" type="text/css" href="../css/bootstrap/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="../css/account.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css"
          integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="../js/bootstrap/bootstrap.min.js" type="text/javascript"></script>
    <script src="../js/chat.js"></script>
    <script src="../js/account.js"></script>
    <script src="../js/cookie.js"></script>
    <script src="../js/websockets/websockets.js"></script>
    <script src="../js/websockets/clientmessagehandler.js"></script>
    <script src="../js/websockets/clientmessageprocessor.js"></script>
</head>

<body>
<section style="display:none;" class="temporarytest">
    <div id="messages"></div>
</section>
<div class="row">
    <input type="hidden" id="accountId" value="">
    <input type="hidden" id="accountMail" value="">
    <input type="hidden" id="currentOpenChat" value="">
    <div class="col-md-1 channels-left text-center">
        <h3 class="logo">LOGO</h3>
        <p><0> online</p>
        <div class="channel-list ">
            <div class="single-channel">
                <p>Channel 01</p>
            </div>
            <div class="single-channel">
                <p>Channel 02</p>
            </div>
            <div class="single-channel">
                <p>Channel 03</p>
            </div>
        </div>
    </div>
    <div class="col-md-2 menu-left">
        <div class="menu-left-options">
            <div class="urlstyling btn-option" onclick="showProfile()">Profile</div>
            <div class="urlstyling btn-option">Library</div>
            <div class="urlstyling btn-option" onclick="showFriends()">Friends</div>
            <div class="urlstyling btn-option" onclick="logout()">Logout</div>
        </div>
        <div id="openChatsLeft" class="menu-left-chats">
            <h2 class="personal-chat-title">Personal chat messages</h2>
        </div>
    </div>
    <div class="col-md-9" id="friendList">
        <div class="friend-options">
            <div class="btn-add-friend" onclick="showAddFriend()">Add friend</div>
            <a class="filter-option" href="#">All</a>
            <a class="filter-option" href="#">Online</a>
            <a class="filter-option" href="#">Awaiting</a>
            <a class="filter-option" href="#">Blocked</a>
        </div>
        <div class="friend-list" id="currentFriendsInSystem">
            <div class="friend-list-titles">
                <div class="row">
                    <div class="col-md-3">
                        <p>Name</p>
                    </div>
                    <div class="col-md-3">
                        <p>Status</p>
                    </div>
                    <div class="col-md-3">
                        <p>Shared servers</p>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="col-md-9" id="chatFriend">
        <div class="friend-details">
            <h2 class="friend-chat-details" id="chatPersonName">@Person <span class="status-online">Online</span></h2>
        </div>
        <div class="chat-open" id="chatMessageList">
            <input type="hidden" id="chatPartnerId">
            <div class="chatmsgpersistent">
                <div class="row">
                    <div class="col-md-1 friend-icon-col">
                        <img class="friend-icon" src="../images/noimage.jpg">
                    </div>
                    <div class="col-md-11">
                        <p class="sender-details">System <span class="send-details">Last saturday at 16:36</span></p>
                        <div class="chat-content">
                            <p class="msg-sentence">This is the start of the conversation</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="messagebox">
            <div class="row">
                <div class="col-md-10">
                    <input type="text" class="input-chat" id="chatInput" placeholder="Send a message to @Person">
                </div>
                <div class="col-md-2" id="btnSendLocation">
                    <input type="hidden" name="friendId" id="friendId" value="">
                    <div class="btn-chat-send" id="sendChatMsgBtn">Send</div>
                </div>
            </div>
        </div>
    </div>
    <div class="col-md-9" id="userProfile">
        <div class="profile-data">
            <div id="profileUpdateResult"></div>
            <form>
                <div class="input-group form-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"><i class="fas fa-user"></i></span>
                    </div>
                    <input type="text" id="AccountUsername" class="form-control" placeholder="Username">
                </div>
                <div class="input-group form-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"><i class="fas fa-user"></i></span>
                    </div>
                    <input type="password" id="AccountPassword" class="form-control" placeholder="Password">
                </div>
                <div class="input-group form-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"><i class="fas fa-user"></i></span>
                    </div>
                    <input type="password" id="AccountConfirmPassword" class="form-control"
                           placeholder="Confirm password">
                </div>
                <div class="input-group form-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"><i class="fas fa-user"></i></span>
                    </div>
                    <input type="text" id="AccountUpdateMail" class="form-control" placeholder="Mail">
                </div>
                <div class="input-group form-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"><i class="fas fa-user"></i></span>
                    </div>
                    <input type="text" id="AccountFirstname" class="form-control" placeholder="Firstname">
                </div>
                <div class="input-group form-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"><i class="fas fa-user"></i></span>
                    </div>
                    <input type="text" id="AccountLastname" class="form-control" placeholder="Lastname">
                </div>
                <div class="input-group form-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"><i class="fas fa-user"></i></span>
                    </div>
                    <input type="text" id="AccountPhone" class="form-control" placeholder="Phone">
                </div>
                <div class="input-group form-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"><i class="fas fa-user"></i></span>
                    </div>
                    <input type="text" id="AccountAddress" class="form-control" placeholder="Address">
                </div>
                <div class="input-group form-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"><i class="fas fa-user"></i></span>
                    </div>
                    <input type="text" id="AccountZipcode" class="form-control" placeholder="Zipcode">
                </div>
                <div class="input-group form-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"><i class="fas fa-user"></i></span>
                    </div>
                    <input type="text" id="AccountPlace" class="form-control" placeholder="Place">
                </div>
                <div class="btn btn-chat-send" onclick="updateProfile()">Update profile</div>
            </form>
        </div>
    </div>
    <div class="col-md-9" id="addFriend">
        <div class="friend-options">
            <div class="btn-add-friend" onclick="showAddFriend()">Add friend</div>
            <a class="filter-option" href="#">All</a>
            <a class="filter-option" href="#">Online</a>
            <a class="filter-option" href="#">Awaiting</a>
            <a class="filter-option" href="#">Blocked</a>
        </div>
        <div id="profileUpdateResult"></div>
        <form>
            <div class="row">
                <div class="col-md-8">
                    <div class="input-group form-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text"><i class="fas fa-user"></i></span>
                        </div>
                        <input type="text" id="SearchInput" class="form-control" placeholder="Username">
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="btn btn-chat-send" onclick="searchUsers()">Search</div>
                </div>
            </div>
            <div id="searchResults">
                <div id="addFriendResultText"></div>
            </div>
        </form>
    </div>

</body>
</html>