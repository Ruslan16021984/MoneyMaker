package carbit3333333.gmail.com.moneytracker;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MainPagerAdapter extends FragmentPagerAdapter {
    private static final int PAGE_INCOMES =0;
    private static final int PAGE_EXPENSES =1;
    private static final int PAGE_BALANCE =2;
    private String[]title;
    public MainPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        title = context.getResources().getStringArray(R.array.tab_title);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case PAGE_INCOMES:{

                return ItemsFragment.createItemFragment(ItemsFragment.TYPE_INCOMES);
            }

            case PAGE_EXPENSES:{
                return ItemsFragment.createItemFragment(ItemsFragment.TYPE_EXPENSES);
            }
            case PAGE_BALANCE:
                return null;
                default:
                    return null;
        }

    }
    @Override
    public int getCount() {
        return 2;
    }
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
       return title[position];

    }
}
