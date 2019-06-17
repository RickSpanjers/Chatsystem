<!DOCTYPE html>
<html>
<head>
    <title>Register Page</title>
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
        <div class="card card-register">
            <div class="card-header">
                <h3>Register an account</h3>
                <div class="d-flex justify-content-end social_icon">
                    <span><i class="fab fa-facebook-square"></i></span>
                    <span><i class="fab fa-google-plus-square"></i></span>
                    <span><i class="fab fa-twitter-square"></i></span>
                </div>
                <p id="resultLogin"></p>
            </div>
            <div class="card-body">
                <form>
                    <div class="input-group form-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text"><i class="fas fa-user"></i></span>
                        </div>
                        <input type="text" class="form-control" id="registerMail" placeholder="Mail">
                    </div>
                    <div class="input-group form-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text"><i class="fas fa-user"></i></span>
                        </div>
                        <input type="text" class="form-control" id="registerUsername" placeholder="username">
                    </div>
                    <div class="input-group form-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text"><i class="fas fa-key"></i></span>
                        </div>
                        <input type="password" class="form-control" id="registerPass" placeholder="password">
                    </div>
                    <div class="form-group">
                        <div class="btn float-right login_btn" onclick="register()">Register</div>
                    </div>
                </form>
            </div>
            <div class="card-footer">
                <div class="d-flex justify-content-center links">
                    Already have an account?<a href="login.php">Log in</a>
                </div>
                <div class="d-flex justify-content-center">
                    <a href="forgotpassword.php">Forgot your password?</a>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>