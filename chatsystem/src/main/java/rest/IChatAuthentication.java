package rest;

import dto.rest.*;

public interface IChatAuthentication {
    String login(RestLoginDTO loginRequest);

    String register(RestRegistrationDTO registrationRequest);

    String forgotPassword(RestForgotPasswordDTO forgotPasswordRequest);

    String forgotMail(RestForgotMailDTO forgotMailRequest);

    String resetPassword(RestResetPasswordDTO resetPasswordRequest);

    String rememberAccount(String phone);
}
