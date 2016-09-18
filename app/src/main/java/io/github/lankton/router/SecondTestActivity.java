package io.github.lankton.router;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import cn.lankton.router.annotation.Route;
import cn.lankton.router.annotation.RouteParam;


@Route("test")
public class SecondTestActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((TextView) findViewById(R.id.tv)).setText("Second TestActivity");
    }
}
