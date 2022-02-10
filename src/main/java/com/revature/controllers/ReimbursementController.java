package com.revature.controllers;

import com.revature.model.Reimbursement;
import com.revature.model.ReimbursementInput;
import com.revature.services.ReimbursementServices;
import io.javalin.http.Context;
import java.util.List;

public class ReimbursementController {

    private final ReimbursementServices reimbursementService = new ReimbursementServices();

    public void handleCreate(Context ctx) {
        Reimbursement newReimbursement = ctx.bodyAsClass(Reimbursement.class);
        boolean success = reimbursementService.createReimbursement(newReimbursement);

        if (success) {
            ctx.status(201);
        } else {
            ctx.status(400);
        }
    }

    public void handleGetAllReimbursements(Context ctx) {
        List<Reimbursement> reimbursements = reimbursementService.getAllReimbursements();
        ctx.json(reimbursements);
    }
    public void handleGetAllReimbursementByStatus(Context ctx) {
        ReimbursementInput reimbursementInput = ctx.bodyAsClass(ReimbursementInput.class);
        List<Reimbursement> reimbursements = reimbursementService.getAllReimbursementsByStatus(reimbursementInput.getStatus());
        ctx.json(reimbursements);
    }

    public void handleGetOne(Context ctx) {
        String idParam = ctx.pathParam("id");
        int id = Integer.parseInt(idParam);
        Reimbursement reimbursement = reimbursementService.getReimbursementById(id);
        ctx.json(reimbursement);
    }

    public void handleUpdate(Context ctx) {
        String idParam = ctx.pathParam("id");
        Reimbursement reimbursementToUpdate = ctx.bodyAsClass(Reimbursement.class);
        int idToUpdate = Integer.parseInt(idParam);
        reimbursementToUpdate.setReimbursementId(idToUpdate);

        boolean success = reimbursementService.updateReimbursement(reimbursementToUpdate);

        if (success) {
            ctx.status(200);
        } else {
            ctx.status(400);
        }
    }

    public void handleDelete(Context ctx) {
        String idParam = ctx.pathParam("id");
        int id = Integer.parseInt(idParam);
        boolean success = reimbursementService.deleteReimbursement(id);

        if (success) {
            ctx.status(200);
        } else {
            ctx.status(400);
        }
    }



}
