package e.gustav.projekt;

import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Gustav on 2017-12-11.
 */

public class PwStrengthMeter extends LinearLayout{

    Context c;
    EditText pW;
    TextView strengthText;
    Boolean pWOK = false;
	DefaultPasswordChecker checker;
    public PwStrengthMeter(Context context) {
        super(context);
        this.c = context;
		checker =new DefaultPasswordChecker();
        init();
    }
    public void init(){

        LinearLayout test = new LinearLayout(c);
        test.setOrientation(VERTICAL);
        test.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        strengthText = new TextView(c);
        strengthText.setTextSize(18);
        strengthText.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        strengthText.setTextColor(getResources().getColor(android.R.color.black));
        strengthText.setText("Strength");


        pW = new EditText(c);
        pW.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        pW.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        pW.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
				int values = checker.measureStrength(pW.getText().toString());
				updateLabels(values);
            }
        });

        test.addView(pW);
        test.addView(strengthText);

        addView(test);

    }
	public void setPasswordChecker(DefaultPasswordChecker checker){
		this.checker= checker;
	}

	 protected void updateLabels(Integer strength) {
			strengthText.setText(checker.getMessage(strength));
			strengthText.setTextColor(getResources().getColor(checker.getStrColor(strength)));
        }


		public class DefaultPasswordChecker implements PwStrength{
			public int measureStrength(String password){
            int strength = 0;

            Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE); // pattern for checking special char
            Matcher m = p.matcher(password);

            if(password.length()>= minLen){
                strength++;
                if(!(password.equals(password.toLowerCase())) &&
                        !(password.equals(password.toUpperCase()))) // password contains lower and uppercase letters
                    strength++;
                if(password.matches(".*\\d+.*")) // password contains one or more numbers
                    strength++;
                if(m.find()) // password contains special char
                    strength++;
                if(password.length()>= strongLen) // password has more than x chars
                    strength++;
            }
            if(strength >= acceptThresh)
                pWOK = true;
            return strength;
        }
            public String getMessage(int value){
                switch (value){
                    case 0: return "Too short!";

                    case 1: return "Very weak!";

                    case 2: return "Weak!";

                    case 3: return "Weak!";

                    case 4: return "Good!";

                    case 5: return "Strong!";


                    default:    return "Something went wrong!";
                }
            }
            public int getStrColor(int value){
                switch (value){
                    case 0: return android.R.color.black;

                    case 1: return android.R.color.holo_red_dark;

                    case 2: return android.R.color.holo_orange_dark;

                    case 3: return android.R.color.holo_orange_dark;

                    case 4:
                        return android.R.color.holo_blue_dark;

                    case 5:
                        return android.R.color.holo_green_dark;


                    default:    return android.R.color.holo_red_dark;

                }
            }

			
		}


}


