package io.gank.gank;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.gank.gank.collect.Collect;
import io.gank.gank.index.Index;
import io.gank.gank.meizi.Meizi;
import io.gank.gank.sort.Sort;
import io.gank.gank.xiandu.Xiandu;

public class MainActivity extends AppCompatActivity {
    private TranslateAnimation mShowAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
            Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
            -1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
    private TranslateAnimation mHiddenAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF,
            0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
            Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
            -1.0f);    ;
    @BindView(R.id.toolbar_layout)
    LinearLayout toolbarLayout;
    private String[] dates = new String[]{"3天前", "2天前", "昨天", "今天", "明天", "后天", "三天后"};
    @BindView(R.id.main_content)
    LinearLayout mainContent;
    @BindView(R.id.left_drawer)
    ListView leftDrawer;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.datepicker)
    TabLayout datepicker;
    private Index index;
    private Sort sort;
    private Collect collect;
    private Meizi meizi;
    private Xiandu xiandu;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.frame_layout)
    FrameLayout frameLayout;
    @BindView(R.id.bottom_navigation_view)
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getSupportActionBar().hide();

        for (int i = 0; i < dates.length; i++) {
            datepicker.addTab(datepicker.newTab(), i);
        }
        for (int i = 0; i < dates.length; i++) {
            datepicker.getTabAt(i).setText(dates[i]);
        }

        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                hideAllFragment(ft);
                switch (item.getItemId()) {
                    case R.id.index:
                        if (index == null) {
                            index = new Index();
                            ft.add(R.id.frame_layout, index);
                        } else {
                            ft.show(index);
                        }
                        break;
                    case R.id.sort:
                        if (sort == null) {
                            sort = new Sort();
                            ft.add(R.id.frame_layout, sort);
                        } else {
                            ft.show(sort);
                        }
                        break;
                    case R.id.meizi:
                        if (meizi == null) {
                            meizi = new Meizi();
                            ft.add(R.id.frame_layout, meizi);
                        } else {
                            ft.show(meizi);
                        }
                        break;
                    case R.id.xiandu:
                        if (xiandu == null) {
                            xiandu = new Xiandu();
                            ft.add(R.id.frame_layout, xiandu);
                        } else {
                            ft.show(xiandu);
                        }
                        break;
                    case R.id.collect:
                        if (collect == null) {
                            collect = new Collect();
                            ft.add(R.id.frame_layout, collect);
                        } else {
                            ft.show(collect);
                        }
                        break;
                }
                ft.commit();
                return true;
            }
        });


        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(View drawerView) {
                Toast.makeText(MainActivity.this, "侧滑栏已打开", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                Toast.makeText(MainActivity.this, "侧滑栏已关闭", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });

        leftDrawer.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, getData()));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        toolbar.inflateMenu(R.menu.index_nav_menu);
        mShowAction.setDuration(500);
        mHiddenAction.setDuration(500);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.datepick:
                        if(toolbarLayout.getVisibility()==View.GONE){
                            toolbarLayout.startAnimation(mShowAction);
                            toolbarLayout.setVisibility(View.VISIBLE);
                        }else {
                            toolbarLayout.startAnimation(mHiddenAction);
                            toolbarLayout.setVisibility(View.GONE);
                        }
                        break;
                }
                return true;
            }
        });

    }

    private void hideAllFragment(FragmentTransaction ft) {
        if (index != null) {
            ft.hide(index);
        }
        if (sort != null) {
            ft.hide(sort);
        }
        if (meizi != null) {
            ft.hide(meizi);
        }
        if (collect != null) {
            ft.hide(collect);
        }
        if (xiandu != null) {
            ft.hide(xiandu);
        }

    }

    private List<String> getData() {
        List<String> data = new ArrayList<>();
        data.add("测试数据1");
        data.add("测试数据2");
        data.add("测试数据3");
        data.add("测试数据4");

        return data;
    }
}
