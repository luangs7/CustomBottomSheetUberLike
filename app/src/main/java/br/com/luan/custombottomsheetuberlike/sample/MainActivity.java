package br.com.luan.custombottomsheetuberlike.sample;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import br.com.luan.custombottomsheetuberlike.R;
import br.com.luan.custombottomsheetuberlike.lib.BottomSheetBehaviorUberLike;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    protected NavigationView navView;
    protected DrawerLayout drawerLayout;
    protected View contentView;
    private static final float END_SCALE = 0.7f;
    protected LinearLayout bottomContent;
    ItemPagerAdapter adapter;
    ViewPager viewPager;

    int[] mDrawables = {
            R.drawable.cheese_3
    };

    TextView bottomSheetTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_main);
        navView = (NavigationView) findViewById(R.id.nav_view);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        contentView = findViewById(R.id.content);
        bottomContent = (LinearLayout) findViewById(R.id.bottom_content);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(" ");
        }

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        navView.setNavigationItemSelectedListener(this);


        drawerLayout.setScrimColor(getResources().getColor(android.R.color.transparent));
        drawerLayout.setScrimColor(Color.TRANSPARENT);
        drawerLayout.setElevation(0.f);
        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
                                           @Override
                                           public void onDrawerSlide(View drawerView, float slideOffset) {

                                               // Scale the View based on current slide offset
                                               final float diffScaledOffset = slideOffset * (1 - END_SCALE);
                                               final float offsetScale = 1 - diffScaledOffset;
                                               contentView.setScaleX(offsetScale);
                                               contentView.setScaleY(offsetScale);

                                               // Translate the View, accounting for the scaled width
                                               final float xOffset = drawerView.getWidth() * slideOffset;
                                               final float xOffsetDiff = contentView.getWidth() * diffScaledOffset / 2;
                                               final float xTranslation = xOffset - xOffsetDiff;
                                               contentView.setTranslationX(xTranslation);
                                               contentView.setBackgroundColor(Color.parseColor("#000000"));
                                           }

                                           @Override
                                           public void onDrawerClosed(View drawerView) {
                                           }
                                       }
        );

        /**
         * If we want to listen for states callback
         */
        CoordinatorLayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorlayout);
        View bottomSheet = coordinatorLayout.findViewById(R.id.bottom_sheet);
        final BottomSheetBehaviorUberLike behavior = BottomSheetBehaviorUberLike.from(bottomSheet);
        behavior.addBottomSheetCallback(new BottomSheetBehaviorUberLike.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {
                    case BottomSheetBehaviorUberLike.STATE_COLLAPSED:
                        onCollapsed();
                        Log.d("bottomsheet-", "STATE_COLLAPSED");
                        break;
                    case BottomSheetBehaviorUberLike.STATE_DRAGGING:
                       // onDraggin();
                        Log.d("bottomsheet-", "STATE_DRAGGING");
                        break;
                    case BottomSheetBehaviorUberLike.STATE_EXPANDED:
                        onExpanded();
                        Log.d("bottomsheet-", "STATE_EXPANDED");
                        break;
                    case BottomSheetBehaviorUberLike.STATE_ANCHOR_POINT:
                        onExpanded();
                        Log.d("bottomsheet-", "STATE_ANCHOR_POINT");
                        break;
                    case BottomSheetBehaviorUberLike.STATE_HIDDEN:
                        Log.d("bottomsheet-", "STATE_HIDDEN");
                        break;
                    default:
                        Log.d("bottomsheet-", "STATE_SETTLING");
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
            }
        });


        adapter = new ItemPagerAdapter(this, mDrawables);
        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(adapter);


        behavior.setState(BottomSheetBehaviorUberLike.STATE_COLLAPSED);

    }

    public void onExpanded(){
        bottomContent.setBackgroundColor(getResources().getColor(android.R.color.black));
        viewPager.setBackgroundColor(getResources().getColor(android.R.color.black ));
        adapter.onExpanded(this);

    }

    public void onCollapsed(){
//        bottomContent.setBackgroundColor(getResources().getColor(android.R.color.transparent));
//        viewPager.setBackgroundColor(getResources().getColor(android.R.color.transparent));
        bottomContent.setBackground(getResources().getDrawable(R.drawable.rounded_card_top));
        viewPager.setBackground(getResources().getDrawable(R.drawable.rounded_card_top));
        adapter.onCollapsed(this);
    }

    public void onDraggin(){
        bottomContent.setBackgroundColor(getResources().getColor(R.color.myblack));
        viewPager.setBackgroundColor(getResources().getColor(R.color.myblack));
        adapter.onDraggin(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }



}
