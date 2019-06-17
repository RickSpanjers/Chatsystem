package rest;

import domain.User;
import dto.rest.*;
import dto.sockets.RememberAccountDTO;

public class ChatRESTClient extends BaseRESTClient implements IRESTClient {

    @Override
    public User login(String username, String password) {
        RestLoginDTO loginRequest = new RestLoginDTO(username, password);
        String query = "/login";
        return executeQueryPost(loginRequest, query, User.class);
    }

    @Override
    public boolean register(String username, String mail, String password) {
        RestRegistrationDTO registrationRequest = new RestRegistrationDTO(username, mail, password);
        boolean result = false;
        String query = "/register";
        RestResultDTO resultDTO =  executeQueryPost(registrationRequest, query, RestResultDTO.class);
        if(resultDTO.getSuccess()){
            result = true;
        }
        return result;
    }

    @Override
    public RestResultDTO forgotPassword(String mail) {
        RestForgotPasswordDTO forgotPasswordRequest = new RestForgotPasswordDTO(mail);
        String query = "/forgotPassword";
        RestResultDTO resultDTO =  executeQueryPost(forgotPasswordRequest, query, RestResultDTO.class);
        return resultDTO;
    }

    @Override
    public boolean forgotMail(String phone) {
        RestForgotMailDTO forgotMailRequest = new RestForgotMailDTO(phone);
        boolean result = false;
        String query = "/forgotMail";
        RestResultDTO resultDTO =  executeQueryPost(forgotMailRequest, query, RestResultDTO.class);
        if(resultDTO.getSuccess()){
            result = true;
        }
        return result;
    }

    @Override
    public boolean resetPassword(String mail, String password, String confirmPassword) {
        RestResetPasswordDTO resetPasswordRequest = new RestResetPasswordDTO(mail, password, confirmPassword);
        boolean result = false;
        String query = "/resetPassword";
        RestResultDTO resultDTO = executeQueryPost(resetPasswordRequest, query, RestResultDTO.class);
        if(resultDTO.getSuccess()){
            result = true;
        }
        return result;
    }

    @Override
    public RestResultDTO rememberAccount(String phone) {
        RememberAccountDTO dto = new RememberAccountDTO(phone);
        String query = "/rememberAccount";
        RestResultDTO resultDTO = executeQueryPost(dto, query, RestResultDTO.class);
        return resultDTO;
    }
}
