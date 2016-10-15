package io.github.lankton.router;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import cn.lankton.router.annotation.Route;
import cn.lankton.router.annotation.RouteParam;
import cn.lankton.router.library.Router;


@Route("truetest")
@RouteParam({"a=int", "b=boolean", "c=String"})
public class TestActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        String str = "TestActivity\n" + getIntent().getIntExtra("a", -1) + "\n" + getIntent().getBooleanExtra("b", false)
                + "\n" + getIntent().getStringExtra("c")
                + "\n" + getIntent().getStringExtra("d")
                + "\n" + getIntent().getStringExtra("e");
        ((TextView) findViewById(R.id.tv)).setText(str);
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("res1", 997);
                bundle.putString("res2", "lan is lan");
                Router.setResult(bundle, TestActivity.this.getClass());
                finish();
            }
        });

    }

}
