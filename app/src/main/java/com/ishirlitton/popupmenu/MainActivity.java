package com.ishirlitton.popupmenu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.littonishir.popupmenu.IshirPopupMenu;
import com.littonishir.popupmenu.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.tv_click);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IshirPopupMenu ishirPopupMenu = new IshirPopupMenu(MainActivity.this);
                List<MenuItem> menuItems = new ArrayList<>();
                menuItems.add(new MenuItem(R.mipmap.ic_launcher, "扫一扫"));
                menuItems.add(new MenuItem(R.mipmap.ic_launcher, "添加好友"));
                ishirPopupMenu
                        .setHeight(0)     //默认高度wrap_content
                        .setWidth(0)      //默认宽度wrap_content
                        .showIcon(true)     //显示菜单图标，默认为true
                        .darkenBackground(true)           //背景变暗，默认为true
                        .needAnimationStyle(true)   //显示动画，默认为true
                        .setAnimationStyle(R.style.ANIM_STYLE)  //默认为R.style.TRM_ANIM_STYLE
                        .addMenuList(menuItems)
                        .setItemDecoration()
//                        .setItemDecoration(new DividerItemDecoration(MainActivity.this,DividerItemDecoration.VERTICAL))
                        .setOnMenuItemClickListener(new IshirPopupMenu.OnMenuItemClickListener() {
                            @Override
                            public void onMenuItemClick(int position) {
                                Toast.makeText(MainActivity.this, "click：" + position+" item", Toast.LENGTH_SHORT).show();

                            }
                        })
                        .showAsDropDown(textView, -150, 0);

            }
        });

    }
}
