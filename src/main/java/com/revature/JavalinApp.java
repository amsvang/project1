package com.revature;

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
    private final LoggingSingletonUtil logger = LoggingSingletonUtil.getLogger();

    private Javalin app = Javalin.create().routes(()->{

        //TODO: UPDATE ADMIN ACCESS ROUTES
        /*path("admin",()->{
            before(authController::authorizeAdminToken);
            path("reimbursement",()->{
                get(reimbController::handleGetAll);
                post(reimbController::handleCreate);
                put(reimbController::handleUpdate);
                delete(reimbController::handleDelete);
                path("{id}",()->{
                    get(reimbController::handleGetOne);
                    put(reimbController::handleUpdate);
                    delete(reimbController::handleDelete);
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
        });*/

        path("employee",()->{
            before(authController::authorizeEmployeeToken);
            path("user",()->{
                path("{id}",()->{
                    get(userController::handleGetOne);
                    put(userController::handleUpdate);
                });
            });
            /*
            path("reimbursement",()->{
                post();
                path("pending",()->{
                    get();
                });
                path("resolved",()->{
                    get();
                });
                path("{id}",()->{
                    get();
                });
            });
            */
        });

        path("login", ()->{
            post(authController::authenticateLogin);
        });
        before("*",logger::logRequest);
    }).exception(NumberFormatException.class, appExceptionHandler::handleNumberFormatException);

    public void start(int port){
        app.start(port);
        }
}
