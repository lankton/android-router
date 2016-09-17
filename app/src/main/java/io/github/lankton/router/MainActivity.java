package io.github.lankton.router;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cn.lankton.router.annotation.Router;
import io.github.lankton.router.library.RouterCenter;


@Router("main")
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RouterCenter.route(this, "test?a=99&b=true&c=this is from Lan");
    }
}
