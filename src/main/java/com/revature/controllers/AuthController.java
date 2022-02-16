package com.revature.controllers;

import com.revature.model.User;
import com.revature.services.AuthService;
import com.revature.services.UserServices;
import com.revature.util.LoggingSingletonUtil;
import io.javalin.http.Context;
import io.javalin.http.UnauthorizedResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.UserRoles;

public class AuthController {
    private final UserServices userService = new UserServices();
    //private final AuthService authService = new AuthService();
    LoggingSingletonUtil logger = LoggingSingletonUtil.getLogger();
    private ObjectMapper mapper = new ObjectMapper();

    // Validate user and set session data (respond to client with session info ---------------------------------------------

    public void authenticateLogin(Context ctx){
        // interpret request
        String username = ctx.formParam("username");
        String password = ctx.formParam("password");

        logger.setWriteToFile(true);
        logger.info(username + " Attempted login");
        logger.setWriteToFile(false);
        //System.out.println("PASS" + password);

        // fulfill the request
        User user =  userService.getUserByUsernameAndPassword(username, password);

        // preparing response
        if(user==null){
            throw new UnauthorizedResponse("Incorrect username or password");
        } else {

            //Not using. This method checks if person is Employee or Admin
            /*simpleToken = user.getRole()+"-TOKEN"; // Employee-token or Admin-token
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
    // Check session (Is person Admin or Employee) ----------------------------------------------------------------------------------

    public void authorizeAdminToken(Context ctx){

        String adminIdTemp = ctx.req.getSession().getAttribute("id").toString();
        int adminId = Integer.parseInt(adminIdTemp);

        User user = userService.getById(adminId);

        if (user.getRole().equals(UserRoles.ADMIN)) {
            return;
        } else {
            throw new UnauthorizedResponse("You are not authorized to perform this action.");
        }

    }
    public void authorizeEmployeeToken(Context ctx){
        System.out.println("printing string" + ctx);
        String employeeIdTemp = ctx.req.getSession().getAttribute("id").toString();
        System.out.println(employeeIdTemp);
        int employeeId = Integer.parseInt(employeeIdTemp);

        User user = userService.getById(employeeId);

        if (user.getRole().equals(UserRoles.EMPLOYEE)) {
            return;
        } else {
            throw new UnauthorizedResponse("You are not authorized to perform this action.");
        }

    }

    // Check if the person is still logged on ---------------------------------------------------------------

    public void verify(Context ctx) {
        ctx.header("Access-Control-Expose-Headers", "*");

        // Testing if id outputs to console
        //System.out.println(ctx.req.getSession().getAttribute("id"));

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
