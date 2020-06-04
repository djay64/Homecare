package com.example.homecare;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private FirebaseAuth mAuth;

    //FOR DESIGN
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();

        // 6 - Configure all views

        this.configureToolBar();

        this.configureDrawerLayout();

        this.configureNavigationView();

        // 2 - Show First Fragment
        this.showFirstFragment();

    }

    @Override
    public void onBackPressed() {
        // 5 - Handle back click to close menu
        if (this.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    // ---------------------
    // CONFIGURATION
    // ---------------------

    // 1 - Configure Toolbar
    private void configureToolBar(){
        this.toolbar = (Toolbar) findViewById(R.id.activity_main_toolbar);
        setSupportActionBar(toolbar);
    }

    // 2 - Configure Drawer Layout
    private void configureDrawerLayout(){
        this.drawerLayout = (DrawerLayout) findViewById(R.id.activity_main_drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    // 3 - Configure NavigationView
    private void configureNavigationView(){
        this.navigationView = (NavigationView) findViewById(R.id.activity_main_nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    //FOR FRAGMENTS
    // 1 - Declare fragment handled by Navigation Drawer
    private Fragment fragmentAccueilProprietaire;
    private Fragment fragmentPlansProprietaire;
    private Fragment fragmentFacturesProprietaire;
    private Fragment fragmentInterventionsProprietaire;
    private Fragment fragmentAlertesProprietaire;
    private Fragment fragmentAutorisationsProprietaire;

    //FOR DATAS
    // 2 - Identify each fragment with a number
    private static final int FRAGMENT_ACCUEIL_PROPRIETAIRE = 0;
    private static final int FRAGMENT_PLANS_PROPRIETAIRE = 1;
    private static final int FRAGMENT_FACTURES_PROPRIETAIRE = 2;
    private static final int FRAGMENT_INTERVENTIONS_PROPRIETAIRE = 3;
    private static final int FRAGMENT_ALERTES_PROPRIETAIRE = 4;
    private static final int FRAGMENT_AUTORISATIONS_PROPRIETAIRE = 5;

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        // 6 - Show fragment after user clicked on a menu item
        switch (id){
            case R.id.activity_main_drawer_profile:
                this.showFragment(FRAGMENT_ACCUEIL_PROPRIETAIRE);
                break;
            case R.id.activity_main_drawer_plans :
                this.showFragment(FRAGMENT_PLANS_PROPRIETAIRE);
                break;
            case R.id.activity_main_drawer_factures:
                this.showFragment(FRAGMENT_FACTURES_PROPRIETAIRE);
                break;
            case R.id.activity_main_drawer_interventions:
                this.showFragment(FRAGMENT_INTERVENTIONS_PROPRIETAIRE);
                break;
            case R.id.activity_main_drawer_alertes:
                this.showFragment(FRAGMENT_ALERTES_PROPRIETAIRE);
                break;
            case R.id.activity_main_drawer_autorisations:
                this.showFragment(FRAGMENT_AUTORISATIONS_PROPRIETAIRE);
                break;
            case R.id.activity_main_drawer_paramettre:
                //.showFragment(FRAGMENT_PARAMS);
                break;
            case R.id.activity_main_drawer_deconnexion:
                mAuth.signOut();
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
        this.drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    // ---------------------
    // FRAGMENTS
    // ---------------------

    // 5 - Show fragment according an Identifier

    private void showFragment(int fragmentIdentifier){
        switch (fragmentIdentifier){
            case FRAGMENT_ACCUEIL_PROPRIETAIRE :
                this.showAccueilFragment();
                break;
            case FRAGMENT_PLANS_PROPRIETAIRE :
                this.showPlansFragment();
                break;
            case FRAGMENT_FACTURES_PROPRIETAIRE :
                this.showFacturesFragment();
                break;
            case FRAGMENT_INTERVENTIONS_PROPRIETAIRE :
                this.showInterventionsFragment();
                break;
            case FRAGMENT_ALERTES_PROPRIETAIRE :
                this.showAlertesFragment();
                break;
            case FRAGMENT_AUTORISATIONS_PROPRIETAIRE :
                this.showAutorisationsFragment();
                break;
            default:
                break;
        }
    }

    // 4 - Create each fragment page and show it

    private void showAccueilFragment(){
        if (this.fragmentAccueilProprietaire == null) this.fragmentAccueilProprietaire = AccueilProprietaireFragment.newInstance();
        this.startTransactionFragment(this.fragmentAccueilProprietaire);
    }

    private void showPlansFragment(){
        if (this.fragmentPlansProprietaire == null) this.fragmentPlansProprietaire = PlanProprietaireFragment.newInstance();
        this.startTransactionFragment(this.fragmentPlansProprietaire);
    }

    private void showFacturesFragment(){
        if (this.fragmentFacturesProprietaire == null) this.fragmentFacturesProprietaire = FacturesProprietaireFragment.newInstance();
        this.startTransactionFragment(this.fragmentFacturesProprietaire);
    }

    private void showInterventionsFragment(){
        if (this.fragmentInterventionsProprietaire == null) this.fragmentInterventionsProprietaire = InterventionsProprietaireFragment.newInstance();
        this.startTransactionFragment(this.fragmentInterventionsProprietaire);
    }

    private void showAlertesFragment(){
        if (this.fragmentAlertesProprietaire == null) this.fragmentAlertesProprietaire = AlertesProprietaireFragment.newInstance();
        this.startTransactionFragment(this.fragmentAlertesProprietaire);
    }

    private void showAutorisationsFragment(){
        if (this.fragmentAutorisationsProprietaire == null) this.fragmentAutorisationsProprietaire = AutorisationsProprietaireFragment.newInstance();
        this.startTransactionFragment(this.fragmentAutorisationsProprietaire);
    }

    // 3 - Generic method that will replace and show a fragment inside the MainActivity Frame Layout
    private void startTransactionFragment(Fragment fragment){
        if (!fragment.isVisible()){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.activity_main_frame_layout, fragment).commit();
        }
    }

    // 1 - Show first fragment when activity is created
    private void showFirstFragment(){
        Fragment visibleFragment = getSupportFragmentManager().findFragmentById(R.id.activity_main_frame_layout);
        if (visibleFragment == null){
            // 1.1 - Show News Fragment
            this.showFragment(FRAGMENT_ACCUEIL_PROPRIETAIRE);
            // 1.2 - Mark as selected the menu item corresponding to NewsFragment
            this.navigationView.getMenu().getItem(0).setChecked(true);
        }
    }
}
