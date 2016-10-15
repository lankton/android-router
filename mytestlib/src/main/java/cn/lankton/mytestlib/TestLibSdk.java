package cn.lankton.mytestlib;

import cn.lankton.router.annotation.RoutePackage;
import cn.lankton.router.library.Router;

import static cn.lankton.mytestlib.TestLibSdk.PACKAGE;


/**
 * Created by taofangxin on 16/10/15.
 */

@RoutePackage(PACKAGE)
public class TestLibSdk {
    public static final String PACKAGE = "cn.lankton.mytestlib";
    public static void init() {
        Router.addPackage(PACKAGE);
    }
}
