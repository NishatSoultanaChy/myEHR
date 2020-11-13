package com.nishatsultana.myehr.Doctor;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuth;


import com.nishatsultana.myehr.Adapters.OrdersPagerAdapter_D;
import com.nishatsultana.myehr.OpeningActivity;
import com.nishatsultana.myehr.Patient.SupportActivity;
import com.nishatsultana.myehr.Patient.update_patient_info_activity;
import com.nishatsultana.myehr.Prevalent.Prevalent;
import com.nishatsultana.myehr.R;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import io.paperdb.Paper;



public class DoctorHomeActivity extends AppCompatActivity implements  NavigationView.OnNavigationItemSelectedListener{
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    Toolbar toolbar;
    NavigationView navigationView;
    String NN;
    String PP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_home);

        Paper.init(this);

        drawerLayout=findViewById(R.id.drawer);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView=findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();


        View headerView = navigationView.getHeaderView(0);
        Button settings = headerView.findViewById(R.id.edit_Profile);


        TextView userNameTextView = headerView.findViewById(R.id.nav_usrname);
        TextView useremailTextView = headerView.findViewById(R.id.nav_user_email);


        NN = Prevalent.CurrentOnlineUser.getFullname();
        PP = Prevalent.CurrentOnlineUser.getEmail();

        userNameTextView.setText(NN);
        useremailTextView.setText(PP);

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DoctorHomeActivity.this, update_patient_info_activity.class);
                startActivity(intent);
            }
        });

        ViewPager2 viewPager2 = findViewById(R.id.view_pager);
        viewPager2.setAdapter(new OrdersPagerAdapter_D(this));

        TabLayout tabs = findViewById(R.id.tabs);
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(
                tabs, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {

                switch (position)
                {
                    case 0:
                    {
                        tab.setText("My Chambers");
                        tab.setIcon(R.drawable.ic_baseline_work_24);
                        break;
                    }

                    case 1:
                    {
                        tab.setText("Today's Appointments");
                        tab.setIcon(R.drawable.ic_baseline_schedule_24);
                        break;
                    }
                    case 2:
                    {
                        tab.setText("My Patients");
                        tab.setIcon(R.drawable.ic_mydoctors);
                        break;
                    }

                }

            }
        }
        );
        tabLayoutMediator.attach();
    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id= item.getItemId();
        drawerLayout.closeDrawer(GravityCompat.START);

        Fragment fragment=null;
        switch (id)
        {
            case R.id.add_chambers:
                //fragment=new FindDoctorFragment();
                //loadFragment(fragment);
                startActivity(new Intent(DoctorHomeActivity.this, AddchambersActivity.class));
                break;

            case R.id.patient_list:
                //fragment=new MyHealthDocFragment();
                //loadFragment(fragment);
                Toast.makeText(this, "Btn is clicked.", Toast.LENGTH_SHORT).show();

                break;


            case R.id.supportDoc:
                //fragment=new SupportFragment();
                //loadFragment(fragment);
                startActivity(new Intent(DoctorHomeActivity.this, SupportActivity.class));

                break;
            case R.id.sign_outDoc:
                //fragment=new SignOutFragment();
                //loadFragment(fragment);
                Paper.book().destroy();

                Intent intent = new Intent(DoctorHomeActivity.this, OpeningActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();


                break;
            default:
                return true;
        }
        return true;
    }



}



