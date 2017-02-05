package knowhere.mx.milanopartner.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.LinkedList;

import knowhere.mx.milanopartner.fragments.AccountFragment;
import knowhere.mx.milanopartner.fragments.FinancesFragment;
import knowhere.mx.milanopartner.fragments.RateFragment;

/**
 * Created by Corona on 6/24/2016.
 */
public class MainPagerAdapter extends FragmentStatePagerAdapter {

    private LinkedList<Fragment> mainFragments;

    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
        mainFragments = new LinkedList<>();
       // mainFragments.add(new HomeFragment());
        mainFragments.add(new FinancesFragment());
        mainFragments.add(new RateFragment());
        mainFragments.add(new AccountFragment());

    }

    @Override
    public Fragment getItem(int position) {
        return mainFragments.get(position);
    }

    @Override
    public int getCount() {
        return mainFragments.size();
    }
}
