package com.test.test;

import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TabLayout tabLayout = findViewById(R.id.layout_tab);
        List<MainCategoryData> mainCategoryDataList = prepareData();
        for (MainCategoryData data : mainCategoryDataList) {
            tabLayout.addTab(tabLayout.newTab().setText(data.getTitle()));
        }
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), mainCategoryDataList);

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        tabLayout.setupWithViewPager(mViewPager);
        mViewPager.addOnPageChangeListener(listener);
        mViewPager.post(new Runnable() {
            @Override
            public void run() {
               listener.onPageSelected(0);
            }
        });


    }

    ViewPager.OnPageChangeListener listener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {
        }

        @Override
        public void onPageSelected(int i) {
            PlaceholderFragment placeholderFragment = (PlaceholderFragment) getSupportFragmentManager().findFragmentByTag("android:switcher:" + R.id.container + ":" + ((ViewPager)findViewById(R.id.container)).getCurrentItem());
            placeholderFragment.showToast();
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };

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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        private static String ARG_SUB = "sub";
        private ViewPager viewById;

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber, MainCategoryData mainCategoryData) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            args.putSerializable(ARG_SUB, mainCategoryData);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            MainCategoryData data = (MainCategoryData) getArguments().getSerializable(ARG_SUB);
            TabLayout tabLayout = rootView.findViewById(R.id.layout_tab2);
            List<SubCategoryData> subCategoryDataList = data.getSubCategoryDataList();
            for (SubCategoryData subCategoryData : subCategoryDataList) {
                tabLayout.addTab(tabLayout.newTab());
            }
            SectionsPagerAdapter adapter = new SectionsPagerAdapter(getChildFragmentManager(), subCategoryDataList);
            viewById = rootView.findViewById(R.id.container2);
            viewById.setAdapter(adapter);
            tabLayout.setupWithViewPager(viewById);
            viewById.addOnPageChangeListener(listener);
            return rootView;
        }

        ViewPager.OnPageChangeListener listener = new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                BlankFragment framentInstance = getFramentInstance();
                framentInstance.showToast();
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        };

        private BlankFragment getFramentInstance() {
            BlankFragment placeholderFragment = (BlankFragment) getChildFragmentManager().findFragmentByTag("android:switcher:" + R.id.container2 + ":" + viewById.getCurrentItem());
            return placeholderFragment;
        }

        public void showToast() {
            BlankFragment framentInstance = getFramentInstance();
            framentInstance.showToast();
        }

        /**
         * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
         * one of the sections/tabs/pages.
         */
        public class SectionsPagerAdapter extends FragmentPagerAdapter {

            private final List<SubCategoryData> list;

            public SectionsPagerAdapter(FragmentManager fm, List<SubCategoryData> mainCategoryDataList) {
                super(fm);
                this.list = mainCategoryDataList;
            }

            @Override
            public Fragment getItem(int position) {
                // getItem is called to instantiate the fragment for the given page.
                // Return a PlaceholderFragment (defined as a static inner class below).
                return BlankFragment.newInstance(list.get(position).getTitle(), "");
            }

            @Override
            public int getCount() {
                // Show 3 total pages.
                return list.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return list.get(position).getTitle();
            }
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        private final List<MainCategoryData> list;

        public SectionsPagerAdapter(FragmentManager fm, List<MainCategoryData> mainCategoryDataList) {
            super(fm);
            this.list = mainCategoryDataList;
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1, list.get(position));
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return list.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return list.get(position).getTitle();
        }
    }


    private List<MainCategoryData> prepareData() {
        List<MainCategoryData> mainCategoryDataList = new ArrayList<>();

        List<SubCategoryData> l1 = new ArrayList<>();
        l1.add(new SubCategoryData("Sub 01"));
        l1.add(new SubCategoryData("Sub 02"));
        l1.add(new SubCategoryData("Sub 03"));
        l1.add(new SubCategoryData("Sub 04"));
        List<SubCategoryData> l2 = new ArrayList<>();
        l2.add(new SubCategoryData("Sub 11"));
        l2.add(new SubCategoryData("Sub 12"));
        l2.add(new SubCategoryData("Sub 13"));
        l2.add(new SubCategoryData("Sub 14"));
        List<SubCategoryData> l3 = new ArrayList<>();
        l3.add(new SubCategoryData("Sub 21"));
        l3.add(new SubCategoryData("Sub 22"));
        l3.add(new SubCategoryData("Sub 23"));
        l3.add(new SubCategoryData("Sub 24"));
        List<SubCategoryData> l4 = new ArrayList<>();
        l4.add(new SubCategoryData("Sub 31"));
        l4.add(new SubCategoryData("Sub 32"));
        l4.add(new SubCategoryData("Sub 33"));
        l4.add(new SubCategoryData("Sub 34"));
        mainCategoryDataList.add(new MainCategoryData("Tab 1", l1));
        mainCategoryDataList.add(new MainCategoryData("Tab 2", l2));
        mainCategoryDataList.add(new MainCategoryData("Tab 3", l3));
        mainCategoryDataList.add(new MainCategoryData("Tab 4", l4));
        return mainCategoryDataList;
    }
}
