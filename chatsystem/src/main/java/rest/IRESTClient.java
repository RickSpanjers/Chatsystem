package rest;

import domain.User;
import dto.rest.RestResultDTO;

public interface IRESTClient {
    User login(String username, String password);

    boolean register(String username, String mail, String password);

    RestResultDTO forgotPassword(String mail);

    boolean forgotMail(String phone);

    boolean resetPassword(String mail, String password, String confirmPassword);

    RestResultDTO rememberAccount(String phone);
}
