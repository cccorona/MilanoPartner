package knowhere.mx.milanopartner;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import knowhere.mx.milanopartner.adapters.ViewPagerAdapter;
import knowhere.mx.milanopartner.fragments.FinancesFragment;
import knowhere.mx.milanopartner.fragments.HomeFragment;
import knowhere.mx.milanopartner.fragments.RateFragment;

public class MainActivity extends AppCompatActivity {


    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_desing);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar= getSupportActionBar();
        //actionBar.setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();
    }



    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new HomeFragment());
        adapter.addFragment(new FinancesFragment());
        adapter.addFragment(new RateFragment());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void setupTabIcons(){
        TabLayout.Tab home = tabLayout.getTabAt(0);
        home.setIcon(R.drawable.home_selector);
        TabLayout.Tab find = tabLayout.getTabAt(1);
        find.setIcon(R.drawable.finances_selector);
        TabLayout.Tab perfil = tabLayout.getTabAt(2);
        perfil.setIcon(R.drawable.rate_selector);
    }



}
