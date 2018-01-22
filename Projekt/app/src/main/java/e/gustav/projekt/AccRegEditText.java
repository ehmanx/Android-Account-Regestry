package e.gustav.projekt;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Gustav on 2018-01-18.
 */

public class AccRegEditText extends LinearLayout{
    
    Context c;
    
    EditText field;
    TextView lable;

    String name;
    int pad;
    int textSize;
    boolean obligatory;
    
    public AccRegEditText(Context context, String lableName, int padding, int size, boolean mandatory) {
        super(context);
        this.c = context;
        name = lableName;
        pad = padding;
        textSize = size;
        obligatory = mandatory;
        init();
    }

    public void init() {
        LinearLayout accRegLayout = new LinearLayout(c);
        accRegLayout.setOrientation(VERTICAL);
        accRegLayout.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        
        field = new EditText(c);
        lable = new TextView(c);

        field.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));


        if(obligatory)
            lable.setText(name + "*");
        else
            lable.setText(name);
        lable.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        lable.setTextSize(textSize);
        lable.setPadding(0,pad,0,0);

        accRegLayout.addView(lable);
        accRegLayout.addView(field);
        addView(accRegLayout);
    }
    Boolean checkObligatory(){
        if(obligatory){
            if ((field.getText().toString().isEmpty()))
                return false;
            else
                return true;
        }

        return true;
    }
}
