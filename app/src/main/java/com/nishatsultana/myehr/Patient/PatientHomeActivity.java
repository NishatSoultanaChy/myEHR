package com.nishatsultana.myehr.Patient;


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


import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.nishatsultana.myehr.Adapters.OrdersPagerAdapter_P;
import com.nishatsultana.myehr.OpeningActivity;
import com.nishatsultana.myehr.Prevalent.Prevalent;
import com.nishatsultana.myehr.R;

import io.paperdb.Paper;



public class PatientHomeActivity extends AppCompatActivity implements  NavigationView.OnNavigationItemSelectedListener{
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    Toolbar toolbar;
    NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

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



        userNameTextView.setText(Prevalent.CurrentOnlineUser.getFullname());
        useremailTextView.setText(Prevalent.CurrentOnlineUser.getEmail());

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PatientHomeActivity.this, update_patient_info_activity.class);
                startActivity(intent);
            }
        });


        ViewPager2 viewPager2 = findViewById(R.id.view_pager);
        viewPager2.setAdapter(new OrdersPagerAdapter_P(this));

        Fragment fragment;

        TabLayout tabs = findViewById(R.id.tabs);
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(
                tabs, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                Fragment fragment;

                switch (position)
                {
                    case 0:
                    {
                        tab.setText("My Doctors");
                        tab.setIcon(R.drawable.ic_mydoctors);
                        break;
                    }
                    case 1:
                    {
                        tab.setText("My Health Track");
                        tab.setIcon(R.drawable.ic_poll_black_24dp);
                        break;
                    }
                    case 2:
                    {
                        tab.setText("Prescriptions");
                        tab.setIcon(R.drawable.ic_prescriptions);
                        break;
                    }
                    case 3:
                    {
                        tab.setText("Reports");
                        tab.setIcon(R.drawable.ic_reports);
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
            case R.id.find_A_Doctor:
                //fragment=new FindDoctorFragment();
                //loadFragment(fragment);
                Toast.makeText(this, "Btn is clicked.", Toast.LENGTH_SHORT).show();

                break;
            case R.id.my_health_docs:

                //Intent intent= new Intent(PatientHomeActivity.this,MyHealthDocActivity.class);
                startActivity(new Intent(PatientHomeActivity.this,MyHealthDocActivity.class));//loadFragment(fragment);
                //Toast.makeText(this, "Btn is clicked.", Toast.LENGTH_SHORT).show();

                break;
            case R.id.appointments:
                //fragment=new AppointmentsFragment();
                //loadFragment(fragment);
                Toast.makeText(this, "Btn is clicked.", Toast.LENGTH_SHORT).show();

                break;
            case R.id.symptomps:
                startActivity(new Intent(PatientHomeActivity.this,SymptomsActivity.class));//loadFragment(fragment);

                //Toast.makeText(this, "Btn is clicked.", Toast.LENGTH_SHORT).show();

                break;
            case R.id.progress_report:
                //fragment=new SettingsFragment();
                //loadFragment(fragment);
                Toast.makeText(this, "Btn is clicked.", Toast.LENGTH_SHORT).show();

                break;
            case R.id.support:
                //fragment=new SupportFragment();
                //loadFragment(fragment);
                startActivity(new Intent(PatientHomeActivity.this,SupportActivity.class));//loadFragment(fragment);

                //Toast.makeText(this, "Btn is clicked.", Toast.LENGTH_SHORT).show();

                break;
            case R.id.sign_out:
                //fragment=new SignOutFragment();
                //loadFragment(fragment);
                Paper.book().destroy();

                Intent intent = new Intent(PatientHomeActivity.this, OpeningActivity.class);
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



