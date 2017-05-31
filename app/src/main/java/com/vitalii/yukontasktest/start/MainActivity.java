package com.vitalii.yukontasktest.start;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.vitalii.yukontasktest.R;
import com.vitalii.yukontasktest.controllers.ClearInfo;
import com.vitalii.yukontasktest.controllers.DeleteInfo;
import com.vitalii.yukontasktest.controllers.EditInfo;
import com.vitalii.yukontasktest.controllers.ReadInfo;
import com.vitalii.yukontasktest.controllers.SaveInfo;
import com.vitalii.yukontasktest.interfaces.People;
import com.vitalii.yukontasktest.objects.Student;
import com.vitalii.yukontasktest.objects.Teacher;
import com.vitalii.yukontasktest.utils.DemoData;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public LinearLayout linearLayoutContentMain;
    private boolean editFlag = false;
    FloatingActionButton fab;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        linearLayoutContentMain = (LinearLayout) findViewById(R.id.LinearLayoutContentMain);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linearLayoutContentMain.removeAllViews();
                linearLayoutContentMain.addView(createAddPersonLayout());
            }
        });
        fillContentMain();
    }

    private View createAddPersonLayout() {
        fab.hide();
        editFlag = true;
        final View viewAdd = getLayoutInflater().inflate(R.layout.add_layout, null);
        final ImageView imageView = (ImageView) viewAdd.findViewById(R.id.imageViewAddLayout);
        final TextView textView1 = (TextView) viewAdd.findViewById(R.id.textView1AddLayout);
        final TextView textView5 = (TextView) viewAdd.findViewById(R.id.textView5AddLayout);
        final EditText addTextFName = (EditText) viewAdd.findViewById(R.id.editTextFName);
        final EditText addTextLName = (EditText) viewAdd.findViewById(R.id.editTextLName);
        final EditText addTextPhone = (EditText) viewAdd.findViewById(R.id.editTextPhone);
        final EditText addTextCourseSubject = (EditText) viewAdd.findViewById(R.id.editTextCourseSubject);
        Button btnAdd = (Button) viewAdd.findViewById(R.id.btnAdd);
        Button btnCancel = (Button) viewAdd.findViewById(R.id.btnCancel);
        final RadioGroup radioGroup = (RadioGroup) viewAdd.findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (group.getCheckedRadioButtonId()) {
                    case R.id.radioButtonS:
                        imageView.setBackgroundResource(R.drawable.student60);
                        textView1.setText("Add Student");
                        textView5.setText("Course");
                        break;
                    case R.id.radioButtonT:
                        imageView.setBackgroundResource(R.drawable.teacher60);
                        textView1.setText("Add Teacher");
                        textView5.setText("Subject");
                        break;
                }
            }
        });
        radioGroup.check(R.id.radioButtonS);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fillContentMain();
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textPerson = "";
                if (radioGroup.getCheckedRadioButtonId() == R.id.radioButtonS) {
                    textPerson = "add_student";
                } else {
                    textPerson = "add_teacher";
                }
                new SaveInfo(viewAdd.getContext(), textPerson, addTextFName.getText().toString(),
                        addTextLName.getText().toString(), addTextPhone.getText().toString(),
                        addTextCourseSubject.getText().toString()).saveData();
                fillContentMain();
            }
        });
        return viewAdd;
    }

    private void fillContentMain() {
        linearLayoutContentMain.removeAllViews();
        fab.show();
        editFlag = false;
        ArrayList<People> list = new ArrayList<>();
        list = new ReadInfo(this, Student.TABLE_NAME).readData();
        list.addAll(new ReadInfo(this, Teacher.TABLE_NAME).readData());
        for (People person : list) {
            addViewPersonLayout(person);
        }
    }

    public void addViewPersonLayout(final People person) {
        View view = getLayoutInflater().inflate(R.layout.person_layout, null);
        RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(R.id.person_rl);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageViewPersonLayout);
        TextView textView1 = (TextView) view.findViewById(R.id.textView1PersonLayout);
        TextView textView2 = (TextView) view.findViewById(R.id.textView2PersonLayout);
        Button btnEdit = (Button) view.findViewById(R.id.btnEdit);
        btnEdit.setBackgroundResource(R.drawable.ic_border_color_black_36dp);
        if (person instanceof Teacher) {
            relativeLayout.setBackgroundColor(0xff00ffff);
            imageView.setBackgroundResource(R.drawable.teacher60);
            textView1.setText("Teacher of " + ((Teacher) person).getSubject());
        } else {
            imageView.setBackgroundResource(R.drawable.student60);
            textView1.setText("Student of " + ((Student) person).getCourse() + " course");
        }
        textView2.setText(person.getfName() + " " + person.getlName());
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editPeople(person);
            }
        });
        linearLayoutContentMain.addView(view);
    }

    private void editPeople(People person) {
        linearLayoutContentMain.removeAllViews();
        linearLayoutContentMain.addView(createViewEditLayout(person));
        editFlag = true;
    }

    private View createViewEditLayout(final People person) {
        fab.hide();
        final Context ctx = this;
        final View viewEdit = getLayoutInflater().inflate(R.layout.edit_layout, null);
        ImageView imageView = (ImageView) viewEdit.findViewById(R.id.imageViewEditLayout);
        TextView textView1 = (TextView) viewEdit.findViewById(R.id.textView1EditLayout);
        TextView textView5 = (TextView) viewEdit.findViewById(R.id.textView5EditLayout);
        final EditText editTextFName = (EditText) viewEdit.findViewById(R.id.editTextFName);
        final EditText editTextLName = (EditText) viewEdit.findViewById(R.id.editTextLName);
        final EditText editTextPhone = (EditText) viewEdit.findViewById(R.id.editTextPhone);
        final EditText editTextCourseSubject = (EditText) viewEdit.findViewById(R.id.editTextCourseSubject);
        if (person instanceof Teacher) {
            imageView.setBackgroundResource(R.drawable.teacher60);
            textView1.setText("Teacher of " + ((Teacher) person).getSubject());
            editTextCourseSubject.setText(((Teacher) person).getSubject());
            textView5.setText("Subject:");
        } else {
            imageView.setBackgroundResource(R.drawable.student60);
            textView1.setText("Student of " + ((Student) person).getCourse() + " course");
            editTextCourseSubject.setText(((Student) person).getCourse() + "");
            textView5.setText("Course:");
        }
        editTextFName.setText(person.getfName());
        editTextLName.setText(person.getlName());
        editTextPhone.setText(person.getPhone());
        Button btnSave = (Button) viewEdit.findViewById(R.id.btnSave);
        Button btnCancel = (Button) viewEdit.findViewById(R.id.btnCancel);
        Button btnDelete = (Button) viewEdit.findViewById(R.id.btnDelete);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fillContentMain();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textPerson;
                if (person instanceof Teacher) {
                    textPerson = "edit_teacher";
                } else {
                    textPerson = "edit_student";
                }
                new EditInfo(ctx, textPerson, Integer.toString(person.getId()), editTextFName.getText().toString(),
                        editTextLName.getText().toString(), editTextPhone.getText().toString(),
                        editTextCourseSubject.getText().toString()).editData();
                fillContentMain();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DeleteInfo(ctx, person).deleteData();
                fillContentMain();
            }
        });
        return viewEdit;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.action_settings:
            {
                final Dialog dialog = new Dialog(MainActivity.this);
                fab.hide();
                dialog.setContentView(R.layout.dialog_layout);
                Button aboutBtnOk = (Button) dialog.findViewById(R.id.aboutBtnOk);
                dialog.setCancelable(false);
                dialog.setTitle("About this project");
                aboutBtnOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        fab.show();
                    }
                });
                dialog.show();
            }
                return true;
            case R.id.action_demo_data:
                new DemoData(this).fillDemoData();
                fillContentMain();
                return true;
            case R.id.action_clear_data:
                new ClearInfo(this).clearData();
                fillContentMain();
                return true;
            case android.R.id.home: {
                if (!editFlag) {
                    finish();
                } else {
                    fillContentMain();
                }
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
