<!DOCTYPE html>
<html>
<head>
    <title>Remember password</title>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="../css/bootstrap/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script
    <script src="../js/bootstrap/bootstrap.min.js" type="text/javascript"></script>
    <script src="../js/websockets/websockets.js"></script>
    <script src="../js/websockets/clientmessagehandler.js"></script>
    <script src="../js/websockets/clientmessageprocessor.js"></script>
    <script src="../js/chat.js"></script>
    <link rel="stylesheet" type="text/css" href="../css/style.css">
    <link rel="stylesheet" type="text/css" href="../css/login.css"
</head>
<body>
<section class="temporarytest">
<div id="messages"></div>
</section>
<div class="container">
    <div class="d-flex justify-content-center h-100">
        <div class="card">
            <div class="card-header">
                <h3 id="pageTitleForgetPass">Forget password</h3>
                <div class="d-flex justify-content-end social_icon">
                    <span><i class="fab fa-facebook-square"></i></span>
                    <span><i class="fab fa-google-plus-square"></i></span>
                    <span><i class="fab fa-twitter-square"></i></span>
                </div>
                <p id="result"></p>
            </div>
            <div class="card-body">
                <form id="forgetPassword">
                    <div class="input-group form-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text"><i class="fas fa-user"></i></span>
                        </div>
                        <input type="text" id="forgotPassMail" class="form-control" placeholder="Mail">
                    </div>
                    <div class="form-group">
                        <div class="btn float-right login_btn" onclick="forgotPassword()">Forget password</div>
                    </div>
                </form>
                <form id="resetPassword">
                    <div class="input-group form-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text"><i class="fas fa-user"></i></span>
                        </div>
                        <input type="text" id="resetMail" class="form-control" placeholder="Mail">
                    </div>
                    <div class="input-group form-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text"><i class="fas fa-user"></i></span>
                        </div>
                        <input type="password" id="resetPass" class="form-control" placeholder="Password">
                    </div>
                    <div class="input-group form-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text"><i class="fas fa-user"></i></span>
                        </div>
                        <input type="password" id="resetConfirmPass" class="form-control" placeholder="Confirm password">
                        <input type="hidden" id="userId" name="userId" value="0">
                    </div>
                    <div class="form-group">
                        <div class="btn float-right login_btn" onclick="resetPassword()">Reset password</div>
                    </div>
                </form>
            </div>
            <div class="card-footer">
                <div class="d-flex justify-content-center links">
                    Don't have an account?<a href="register.php">Sign Up</a>
                </div>
                <div class="d-flex justify-content-center">
                    <a href="forgotmail.php">Forgot your mail?</a>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>