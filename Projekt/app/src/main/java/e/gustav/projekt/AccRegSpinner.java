package e.gustav.projekt;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by Gustav on 2018-01-18.
 */

public class AccRegSpinner extends LinearLayout{
    Context c;

    TextView lable;
    Spinner spin;

    String name;
    String[] ops;
    int pad;
    int textSize;

    public AccRegSpinner(Context context, String lableName,String[] options,  int padding, int size) {
        super(context);
        this.c = context;
        name = lableName;
        ops = options;
        pad = padding;
        textSize = size;
        init();
    }
    public void init() {
        LinearLayout accRegLayout = new LinearLayout(c);
        accRegLayout.setOrientation(VERTICAL);
        accRegLayout.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        lable = new TextView(c);
        spin = new Spinner(c);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(c, android.R.layout.simple_spinner_dropdown_item, ops);
        spin.setAdapter(adapter);
        spin.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));


        lable.setText(name);
        lable.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        lable.setTextSize(textSize);
        lable.setPadding(0,pad,0,0);

        accRegLayout.addView(lable);
        accRegLayout.addView(spin);
        addView(accRegLayout);
    }

}
