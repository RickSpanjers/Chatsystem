package rest;

import com.google.gson.Gson;
import context.mssql.UserMSSQLContext;
import domain.User;
import dto.rest.*;
import dto.sockets.RememberAccountDTO;
import logging.ILogger;
import logging.Logger;
import repository.UserRepository;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/chatsystem")
public class ChatREST implements IChatAuthentication {

    private UserRepository userRepo = new UserRepository(new UserMSSQLContext());
    private ILogger logger = Logger.getInstance();
    private Gson gson = new Gson();

    @POST
    @Path("login/")
    @Consumes("application/json")
    @Produces("application/json")
    @Override
    public String login(RestLoginDTO loginRequest) {
        String result = "Successful login";
        if (loginRequest == null) {
            result = "Unable to login. Did you use a wrong password?";
        }
        try {
            User retrievedUser = userRepo.getUserByMail(loginRequest.getUserName());
            User u = new User(retrievedUser.getUsername(), retrievedUser.getMail());
            String password = userRepo.toSHA256(loginRequest.getHashedPassword());
            if (retrievedUser.getId() != 0 && retrievedUser.getPassword().equals(password)) {
                u.setId(retrievedUser.getId());
                result = gson.toJson(u);
            }
        } catch (Exception e) {
            logger.log(e);
        }
        return result;
    }


    @POST
    @Path("register/")
    @Consumes("application/json")
    @Produces("application/json")
    @Override
    public String register(RestRegistrationDTO registrationRequest) {
        RestResultDTO resultDTO = new RestResultDTO(false, "REGISTER");
        User u = new User(0, registrationRequest.getUsername(), registrationRequest.getMail(), registrationRequest.getPassword());
        if (userRepo.registerUser(u)) {
            resultDTO.setSuccess(true);
        }
        return gson.toJson(resultDTO);
    }

    @POST
    @Path("forgotPassword/")
    @Consumes("application/json")
    @Produces("application/json")
    @Override
    public String forgotPassword(RestForgotPasswordDTO forgotPasswordRequest) {
        RestResultDTO result = new RestResultDTO(false, "FORGOTPASSWORD");
        if (forgotPasswordRequest.getMail() != null) {
            User retrievedUser = userRepo.getUserByMail(forgotPasswordRequest.getMail());
            if (retrievedUser != null) {
                retrievedUser.setPassword("");
                if (userRepo.updateProfile(retrievedUser)) {
                    result.setSuccess(true);
                    result.setData(gson.toJson(retrievedUser));
                }
            }
        }
        return gson.toJson(result);
    }

    @POST
    @Path("resetPassword/")
    @Consumes("application/json")
    @Produces("application/json")
    @Override
    public String resetPassword(RestResetPasswordDTO resetPasswordRequest) {
        RestResultDTO result = new RestResultDTO(false, "RESETPASSWORD");
        if (resetPasswordRequest.getMail() != null && resetPasswordRequest.getPassword() != null) {
            User retrievedUser = userRepo.getUserByMail(resetPasswordRequest.getMail());
            if (retrievedUser != null) {
                if (resetPasswordRequest.getPassword().equals(resetPasswordRequest.getConfirmPassword())) {
                    retrievedUser.setPassword(userRepo.toSHA256(resetPasswordRequest.getPassword()));
                    if (userRepo.updateProfile(retrievedUser)) {
                        result.setSuccess(true);
                    }
                }
            }
        }
        return gson.toJson(result);
    }

    @POST
    @Path("rememberAccount/")
    @Consumes("application/json")
    @Produces("application/json")
    @Override
    public String rememberAccount(String phone) {
        RestResultDTO result = new RestResultDTO(false, "REMEMBERACCOUNT");
        RememberAccountDTO dto = gson.fromJson(phone, RememberAccountDTO.class);
        if(dto.getPhone() != null){
            User retrievedUser = userRepo.getUserByPhone(dto.getPhone());
            if(retrievedUser.getId() != 0){
                result.setSuccess(true);
                result.setData(retrievedUser.getMail());
            }
        }
        return gson.toJson(result);
    }

    @POST
    @Path("forgotMail/")
    @Consumes("application/json")
    @Produces("application/json")
    @Override
    public String forgotMail(RestForgotMailDTO forgotMailRequest) {
        RestResultDTO result = new RestResultDTO(false, "FORGOTPASSWORD");
        if (forgotMailRequest.getPhone() != null) {
            User retrievedUser = userRepo.getUserByPhone(forgotMailRequest.getPhone());
            if (retrievedUser != null) {
                result.setSuccess(true);
                result.setData(retrievedUser.getMail());
            } else {
                result.setData("Phone number not set or no account linked to the number");
            }
        }
        return gson.toJson(result);
    }

}
