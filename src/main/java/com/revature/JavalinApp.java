package com.revature;

import com.revature.controllers.ReimbursementController;
import com.revature.controllers.UserController;
import io.javalin.Javalin;
import static io.javalin.apibuilder.ApiBuilder.*;
import com.revature.controllers.AppExceptionHandler;
import com.revature.controllers.AuthController;
import com.revature.util.LoggingSingletonUtil;
import io.javalin.http.staticfiles.Location;

public class JavalinApp {

    //private final LoggingSingletonUtil loggingUtil = new LoggingSingletonUtil();
    private final AppExceptionHandler appExceptionHandler = new AppExceptionHandler();
    private AuthController authController = new AuthController();
    private final UserController userController = new UserController();
    private final ReimbursementController reimbursementController = new ReimbursementController();
    private final LoggingSingletonUtil logger = LoggingSingletonUtil.getLogger();

    private Javalin app = Javalin.create((config->{
        config.enableCorsForAllOrigins();
        config.addStaticFiles("/static", Location.CLASSPATH);
    })).routes(()->{

        // Admin paths -------------------------------------------------------------------------------------------

        // app.get("/admin/reimbursement", reimbursementController::handleGetAllReimbursements);
        path("admin",()->{
           // before(authController::authorizeAdminToken);
            path("reimbursement",()->{
                get(reimbursementController::handleGetAllReimbursements);
                post(reimbursementController::handleCreate);
                put(reimbursementController::handleUpdate);
                delete(reimbursementController::handleDelete);
                path("status",()->{
                    get(reimbursementController::handleGetAllReimbursementByStatus);
                    path("{id}",()->{
                        get(reimbursementController::handleGetAllReimbursementByStatusAndId);
                    });
                });
                path("{id}",()->{
                    get(reimbursementController::handleGetOne);
                    put(reimbursementController::handleUpdate);
                    delete(reimbursementController::handleDelete);
                });
                path("users",()->{
                    path("{id}",()->{
                        get(reimbursementController::handleGetAllReimbursementByUserId);
                    });
                });
            });
            // admin/users
            path("users",()->{
                get(userController::handleGetAll);
                post(userController::handleCreate);
                put(userController::handleUpdate);
                path("{id}",()->{
                    get(userController::handleGetOne);
                    put(userController::handleUpdate);
                    delete(userController::handleDelete);
                });
            });
        });

        // Employee paths --------------------------------------------------------------------------------------

        path("employee",()->{
         //   before(authController::authorizeEmployeeToken);
            path("user",()->{
                path("{id}",()->{
                    get(userController::handleGetOne);
                    put(userController::handleUpdate);
                });
            });

            path("reimbursement",()->{
                get(reimbursementController::handleGetAllReimbursements);
                post(reimbursementController::handleCreate);
                path("status",()->{
                    get(reimbursementController::handleGetAllReimbursementByStatus);
                    path("{id}",()->{
                        get(reimbursementController::handleGetAllReimbursementByStatusAndId);
                    });
                });
                path("{id}",()->{
                    get(reimbursementController::handleGetOne);
                    put(reimbursementController::handleUpdate);
                });
            });

        });

        // Logins and logouts ----------------------------------------------------------------------------------

        path("login", ()->{
            post(authController::authenticateLogin);
        });
        path("verify", ()->{
            get(authController::verify);
        });
        path("logout", ()->{
            get(authController::logout);
        });
        before("*",logger::logRequest);
    }).exception(NumberFormatException.class, appExceptionHandler::handleNumberFormatException);
    public void start(int port){
        app.start(port);
    }

}
