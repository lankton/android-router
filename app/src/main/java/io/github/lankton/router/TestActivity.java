package io.github.lankton.router;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import cn.lankton.router.annotation.Route;
import cn.lankton.router.annotation.RouteParam;
import cn.lankton.router.library.Router;


@Route("secondtest")
@RouteParam({"a=int", "b=boolean", "c=String"})
public class TestActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String str = "TestActivity\n" + getIntent().getIntExtra("a", -1) + "\n" + getIntent().getBooleanExtra("b", false)
                + "\n" + getIntent().getStringExtra("c")
                + "\n" + getIntent().getStringExtra("d")
                + "\n" + getIntent().getStringExtra("e");
        ((TextView) findViewById(R.id.tv)).setText(str);
        findViewById(R.id.tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Router.route(TestActivity.this, "secondtest");
            }
        });
    }
}
