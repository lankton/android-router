package cn.lankton.mytestlib;

import android.app.Activity;
import android.os.Bundle;

import cn.lankton.router.annotation.Route;
import cn.lankton.router.library.Router;

/**
 * Created by taofangxin on 16/10/15.
 */

@Route("libone")
public class LibOneActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testlib_activity_one);
        Router.route(this, "libtwo");
    }
}
