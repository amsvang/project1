package com.revature.controllers;

import com.revature.model.User;
import com.revature.services.AuthService;
import com.revature.services.UserServices;
import com.revature.util.LoggingSingletonUtil;
import io.javalin.http.Context;
import io.javalin.http.ForbiddenResponse;
import io.javalin.http.UnauthorizedResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AuthController {
    private final UserServices userService = new UserServices();
//    private final AuthService authService = new AuthService();
    LoggingSingletonUtil logger = LoggingSingletonUtil.getLogger();
    private ObjectMapper mapper = new ObjectMapper();

    // Validate user and set session data (respond to client with session info ---------------------------------------------

    public void authenticateLogin(Context ctx){
        // interpret request
        String username = ctx.formParam("username");
        String password = ctx.formParam("password");

        logger.setWriteToFile(true);
        logger.info(username + "Attemped login");
        logger.setWriteToFile(false);

        // fulfill the request
        User user =  userService.getUserByUsernameAndPassword(username, password);

        // preparing response
        if(user==null){
            throw new UnauthorizedResponse("Incorrect username or password");
        } else {
            /* simpleToken = user.getRole()+"-TOKEN"; // Employee-token or Admin-token
            ctx.header("Authorization", simpleToken);
            ctx.status(200);*/

            ctx.req.getSession().setAttribute("id", ""+user.getUserId());
            ctx.req.getSession().setAttribute("loggedIn", user.getEmail());

            ctx.header("userId", ""+user.getUserId());
            ctx.header("loggedIn", user.getEmail());
            try {
                ctx.result(mapper.writeValueAsString(user));
            } catch (Exception e) {
                ctx.status(500);
            }
        }
    }
    // Check session (who person is) ----------------------------------------------------------------------------------

    public void authorizeAdminToken(Context ctx){
        String authHeader = ctx.header("Authorization");

        if(authHeader!=null){
            if(authHeader.equals("ADMIN-TOKEN")) {
                return;
            } else if (authHeader.equals("EMPLOYEE-TOKEN")){
                throw new ForbiddenResponse("You are unable to access this feature");
            }
        }
        throw new UnauthorizedResponse("Please login and try again");
    }
    public void authorizeEmployeeToken(Context ctx){
        String authHeader = ctx.header("Authorization");{

            if(authHeader!=null)
                if(authHeader.equals("EMPLOYEE-TOKEN")) {
                    return;
                } else {
                    throw new ForbiddenResponse("You are unable to access this feature");
                }
        }
        throw new UnauthorizedResponse("please login and try again");
    }

    // Check if the person is still logged on ---------------------------------------------------------------

    public void verify(Context ctx) {
        ctx.header("Access-Control-Expose-Headers", "*");

        System.out.println(ctx.req.getSession().getAttribute("id"));

        if(ctx.req.getSession().getAttribute("id") == null) {
            ctx.status(400);
            ctx.result("User not logged in");
        }
        else {
            ctx.header("userId", ""+ctx.req.getSession().getAttribute("id"));
            ctx.result("User was verified as logged in");
        }
    }

    // Log user out ---------------------------------------------------------------------------------------

    public void logout(Context ctx) {
        ctx.req.getSession().invalidate();
        ctx.status(200);
        ctx.result("User logged out");
    }



}
