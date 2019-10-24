package com.example.firebasesearchtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.firebasesearchtest.Model.Phone;
import com.example.firebasesearchtest.Model.PhoneCategory;

import java.util.ArrayList;
import java.util.List;

import iammert.com.expandablelib.ExpandableLayout;
import iammert.com.expandablelib.Section;

import static com.example.firebasesearchtest.AdapterClass.Tag2;
import static com.example.firebasesearchtest.AdapterClass.Tag3;

public class UsersOption extends AppCompatActivity {
    TextView textView;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    ExpandableLayout expandableLayout;
    String one;
    String one2;
    PhoneCategory phc = new PhoneCategory();
    Phone pho = new Phone();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_option);
        textView = findViewById(R.id.tvuserOption);
        textView2 = findViewById(R.id.tvuserOption2);
        textView3 = findViewById(R.id.tvParentName);
        textView4 = findViewById(R.id.tvChildName);

        // get value from other activity safe way;
//        Intent intent = getIntent();
//
//        String fName = intent.getStringExtra("firstName");
//        String lName = intent.getStringExtra("lastName");

        if (Tag2 != null) {
            one = Tag2;
            textView.setText(one);
        }

        if(Tag3 != null){
            one2 = Tag3;
            textView2.setText(one2);
        }

        expandableLayout = findViewById(R.id.expandaple_layout);
        expandableLayout.setRenderer(new ExpandableLayout.Renderer<PhoneCategory, Phone>() {

            @Override
            public void renderParent(View view, PhoneCategory model, boolean isExpanded, int parentPosition) {
                String few;
                few = phc.getName();
//                textView3.setText(few);
                //problem not passing value from phone class to text view 3 and 4
                if(few != null){
                    textView.setText(few);
                }else{
                    textView.setText("Failed");
                }
                view.findViewById(R.id.ivArrow).setBackgroundResource(isExpanded?R.drawable.ic_arrow_up:R.drawable.ic_arrow_down);
            }

            @Override
            public void renderChild(View view, Phone model, int parentPosition, int childPosition) {
                String low;
                low = pho.getName();
//                textView4.setText(low);
                if(low != null){
                    textView2.setText(low);
                }else{
                    textView2.setText("Failed");
                }
            }
        });
        expandableLayout.addSection(getSection());
        expandableLayout.addSection(getSection());
        expandableLayout.addSection(getSection());

    }

    private Section<PhoneCategory,Phone> getSection() {
        Section<PhoneCategory,Phone> section = new Section<>();
        PhoneCategory phc2 = new PhoneCategory("Phone");


        List<Phone> listPhone = new ArrayList<>();

        for(int i = 1; i <=10; i++){
            listPhone.add(new Phone ("Phone " + i));
            section.parent = phc2 ;
            section.children.addAll(listPhone);
        }
        return section;
    }
}

