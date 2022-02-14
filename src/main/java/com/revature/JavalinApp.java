package com.revature;

import com.revature.controllers.ReimbursementController;
import com.revature.controllers.UserController;
import io.javalin.Javalin;
import static io.javalin.apibuilder.ApiBuilder.*;
import com.revature.controllers.AppExceptionHandler;
import com.revature.controllers.AuthController;
import com.revature.util.LoggingSingletonUtil;

public class JavalinApp {

    //private final LoggingSingletonUtil loggingUtil = new LoggingSingletonUtil();
    private final AppExceptionHandler appExceptionHandler = new AppExceptionHandler();
    private AuthController authController = new AuthController();
    private final UserController userController = new UserController();
    private final ReimbursementController reimbursementController = new ReimbursementController();
    private final LoggingSingletonUtil logger = LoggingSingletonUtil.getLogger();

    private Javalin app = Javalin.create().routes(()->{

        //TODO: UPDATE ADMIN ACCESS ROUTES
        path("admin",()->{
            before(authController::authorizeAdminToken);
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
            path("users",()->{
                get(userController::handleGetAll);
                put(userController::handleUpdate);
                path("{id}",()->{
                    get(userController::handleGetOne);
                    post(userController::handleCreate);
                    put(userController::handleUpdate);
                    delete(userController::handleDelete);
                });
            });
        });

        path("employee",()->{
            before(authController::authorizeEmployeeToken);
            path("user",()->{
                path("{id}",()->{
                    get(userController::handleGetOne);
                    put(userController::handleUpdate);
                });
            });

            path("reimbursement",()->{
                post(reimbursementController::handleCreate);
                path("status",()->{

                    get(reimbursementController::handleGetAllReimbursementByStatus);
                });
                path("{id}",()->{
                    get(reimbursementController::handleGetAllReimbursements);

                    path("{id}", () ->{
                        get(reimbursementController::handleGetAllReimbursementByStatusAndId);
                    });
                });
                path("{id}",()->{
                    get(reimbursementController::handleGetOne);
                    put(reimbursementController::handleUpdate);

                });
            });

        });

        path("login", ()->{
            post(authController::authenticateLogin);
        });
        before("*",logger::logRequest);
    }).exception(NumberFormatException.class, appExceptionHandler::handleNumberFormatException);

}
