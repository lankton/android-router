package cn.lankton.mytestlib;

import android.app.Activity;
import android.os.Bundle;

import cn.lankton.router.annotation.Route;

/**
 * Created by taofangxin on 16/10/15.
 */

@Route("libtwo")
public class LibTwoActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testlib_activity_two);
    }
}
