package com.revature.controllers;

import com.revature.model.User;
import com.revature.services.UserServices;
import io.javalin.http.Context;
import io.javalin.http.ForbiddenResponse;
import io.javalin.http.UnauthorizedResponse;

public class AuthController {
    private final UserServices userService = new UserServices();

    public void authenticateLogin(Context ctx){
        // interpret request
        String username = ctx.formParam("username");
        String password = ctx.formParam("password");

        // fulfill the request
        User user =  userService.getUserByUsernameAndPassword(username, password);

        // preparing response
        if(user==null){
            throw new UnauthorizedResponse("Incorrect username or password");
        } else {
            String simpleToken = user.getType()+"-TOKEN"; // Employee-token or Admin-token
            ctx.header("Authorization", simpleToken);
            ctx.status(200);
        }
    }
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


}
