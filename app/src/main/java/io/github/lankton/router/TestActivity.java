package io.github.lankton.router;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import cn.lankton.router.annotation.Route;
import cn.lankton.router.annotation.RouteParam;


@Route("test")
@RouteParam({"a=int", "b=boolean", "c=String"})
public class TestActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String str = "TestActivity\n" + getIntent().getIntExtra("a", -1) + "\n" + getIntent().getBooleanExtra("b", false)
                + "\n" + getIntent().getStringExtra("c");
        ((TextView) findViewById(R.id.tv)).setText(str);
    }
}
