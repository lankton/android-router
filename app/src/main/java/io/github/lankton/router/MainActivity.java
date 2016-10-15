package io.github.lankton.router;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import cn.lankton.mytestlib.TestLibSdk;
import cn.lankton.router.annotation.Route;
import cn.lankton.router.library.Router;


@Route("main")
public class MainActivity extends AppCompatActivity {
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TestLibSdk.init();
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.tv);
//        Router.route(this, "truetest/secondtest?a=99.3&b=true&c=this is from Lan&d=2333&e=lan=lankton");
        Router.route(this, "truetest?a=99.3&b=true&c=this is from Lan&d=2333&e=lan=lankton");
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Router.backFrom() != null && Router.backFrom() == TestActivity.class) {
            int res1 = Router.getResult().getInt("res1");
            String res2 = Router.getResult().getString("res2");
            tv.append("\nres1:" + res1);
            tv.append("\nres2:" + res2);
        }
    }
}
