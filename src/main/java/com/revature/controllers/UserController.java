package com.revature.controllers;

import com.revature.model.User;
import com.revature.services.UserServices;
import io.javalin.http.Context;

import java.util.List;

public class UserController {
    private final UserServices userService = new UserServices();

    public void handleCreate(Context ctx){
        User newUser = ctx.bodyAsClass(User.class);
        boolean success = userService.createUser(newUser);

        if(success){
            ctx.status(201);
        } else {
            ctx.status(400);
        }
    }

    public void handleGetAll(Context ctx){
        List<User> user = userService.getAll();
        ctx.json(user);
    }

    public void handleGetOne(Context ctx){
        String idParam = ctx.pathParam("id");
        int id = Integer.parseInt(idParam);
        User user = userService.getById(id);
        // return a 404 if not found
        ctx.json(user);
    }

    public void handleUpdate(Context ctx){

        String idParam = ctx.pathParam("id");
        User userToUpdate = ctx.bodyAsClass(User.class);
        int idToUpdate = Integer.parseInt(idParam);
        userToUpdate.setUserId(idToUpdate);

        boolean success = userService.updateUser(userToUpdate);

        if(success){
            ctx.status(200);
        } else {
            ctx.status(400);
        }
    }

    public void handleDelete(Context ctx){
        String idParam = ctx.pathParam("id");
        int id = Integer.parseInt(idParam);
        boolean success = userService.deleteUser(id);

        if (success) {
            ctx.status(200);
        } else {
            ctx.status(400);
        }
    }

}
