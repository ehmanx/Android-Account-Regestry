package e.gustav.projekt;

import android.content.Context;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Gustav on 2017-12-11.
 */

public class AccountReg extends LinearLayout {

    Context c;
    TextView pWLable, checkBoxLable, obligatoryLable;
    
    AccRegEditText uNameView, fNameView,lNameView, emailView,
            dOBView, rePWView;

    ArrayList<AccRegEditText> viewList = new ArrayList<AccRegEditText>();

    AccRegSpinner genderView, regionView;

    PwStrengthMeter pWview;

    CheckBox checkBoxView;

    Button submitButton;

    JSONObject account;

    int textSize = 12;
    int pad = 10;

    public AccountReg(Context context) {
        super(context);
        this.c = context;
        init();
    }
    public void init(){


        LinearLayout accRegLayout = new LinearLayout(c);
        accRegLayout.setOrientation(VERTICAL);
        accRegLayout.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        setUp();
        makeLayout(accRegLayout);

        submitButton = new Button(c);
        submitButton.setText("Submit");
        submitButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkObligatory()){
                    if(pWview.pWOK){
                        if(pWview.pW.getText().toString().contentEquals(rePWView.field.getText().toString())){
                            if(checkBoxView.isChecked()){
                                //Create JSON object?
                                //send toast to user with success message
                                account = makeAccountObject();
                                System.out.println(account);
                                Toast.makeText(c, "Account created successfully!", Toast.LENGTH_LONG).show();
                            }
                            else {
                                Toast.makeText(c, "Please agree to terms and conditions", Toast.LENGTH_LONG).show();

                            }
                        }
                        else {
                            Toast.makeText(c, "Passwords do not match!", Toast.LENGTH_LONG).show();
                            rePWView.lable.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
                            pWLable.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
                        }
                    }
                    else{
                        Toast.makeText(c, "Password strength to weak, please choose a stronger password", Toast.LENGTH_LONG).show();
                        pWLable.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
                    }
                }
                else{
                    Toast.makeText(c, "All obligatory fields are not filled!", Toast.LENGTH_LONG).show();
                }
            }
        });

        accRegLayout.addView(submitButton);

        addView(accRegLayout);
        
    }
    //
    // make the layout for the account reg
    //
    public void makeLayout(LinearLayout inputLayout){

        inputLayout.addView(obligatoryLable);

        inputLayout.addView(uNameView);

        inputLayout.addView(fNameView);

        inputLayout.addView(lNameView);

        inputLayout.addView(emailView);

        inputLayout.addView(dOBView);

        inputLayout.addView(genderView);

        inputLayout.addView(regionView);

        inputLayout.addView(pWLable);

        inputLayout.addView(pWview);

        inputLayout.addView(rePWView);

        inputLayout.addView(checkBoxLable);

        inputLayout.addView(checkBoxView);

    }
    //
    // Check if all obligatory fields are used, returns true if no faults
    //
    public Boolean checkObligatory(){
        int faults = 0;
        for(AccRegEditText obj : viewList){
            if(!obj.checkObligatory()){
                obj.lable.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
                faults ++;
            }
            else
                obj.lable.setTextColor(getResources().getColor(android.R.color.black));
        }
        if(faults>0)
            return false;
        else
            return true;

    }
    //
    // Creates a JSON object with all account information
    //
    public JSONObject makeAccountObject(){
        try{
            JSONObject account = new JSONObject();
            account.put("Username", uNameView.field.getText().toString());
            account.put("First Name", fNameView.field.getText().toString());
            account.put("Last Name", lNameView.field.getText().toString());
            account.put("Email", emailView.field.getText().toString());
            account.put("Date of Birth", dOBView.field.getText().toString());
            account.put("Gender", genderView.spin.getSelectedItem().toString());
            account.put("Region", regionView.spin.getSelectedItem().toString());
            account.put("Password", pWview.pW.getText().toString());

            return account;

        }catch (JSONException e){
            System.out.println("JSON error");
        }
        return  null;
    }
    //
    //Sets up all input fields and their lables
    //
    public void setUp(){
        obligatoryLable = new TextView(c);
        obligatoryLable.setText("Fields marked with * are obligatory");
        obligatoryLable.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        obligatoryLable.setTextSize(textSize);
        obligatoryLable.setPadding(0,pad,0,0);

        uNameView = new AccRegEditText(c, "Username", pad, textSize, true);
        viewList.add(uNameView);

        fNameView = new AccRegEditText(c, "Firstname", pad, textSize, false);
        viewList.add(fNameView);

        lNameView = new AccRegEditText(c, "Lastname", pad, textSize, false);
        viewList.add(lNameView);

        emailView = new AccRegEditText(c, "Email", pad, textSize, false);;
        emailView.field.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        viewList.add(emailView);

        dOBView = new AccRegEditText(c, "Date of Birth", pad, textSize, false);;
        dOBView.field.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_DATETIME_VARIATION_DATE);
        viewList.add(dOBView);


        String[] genderOptions = new String[]{"Male","Female","Other"};
        genderView = new AccRegSpinner(c, "Gender", genderOptions, pad, textSize);

        String[] regionOptions = new String[]{"Sweden","Norway","Denmark", "Finland", "Other"};
        regionView = new AccRegSpinner(c, "Region", regionOptions, pad, textSize);

        pWLable = new TextView(c);
        pWLable.setText("Password*");
        pWLable.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        pWLable.setTextSize(textSize);
        pWLable.setPadding(0,pad,0,0);

        pWview = new PwStrengthMeter(c);

        rePWView = new AccRegEditText(c, "Repeat Password", pad, textSize, true);;
        rePWView.field.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        viewList.add(rePWView);

        checkBoxLable = new TextView(c);
        checkBoxLable.setText("Check if you agree to all terms and conditions");
        checkBoxLable.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        checkBoxLable.setTextSize(textSize);
        checkBoxLable.setPadding(0,pad,0,0);

        checkBoxView = new CheckBox(c);
        checkBoxView.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    }
}
