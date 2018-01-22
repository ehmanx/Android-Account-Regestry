package e.gustav.projekt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ScrollView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        //PwStrengthMeter test = new PwStrengthMeter(this);
        AccountReg test = new AccountReg(this);
        ScrollView scroll = new ScrollView(this);
        layout.addView(scroll);
        scroll.addView(test);
        setContentView(layout);
    }
}
