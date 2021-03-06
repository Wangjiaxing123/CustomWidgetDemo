package com.newtrekwang.customwidgetdemo;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.newtrekwang.customwidgetdemo.activity.CommonActivity;
import com.newtrekwang.customwidgetdemo.activity.ImageActivity;
import com.newtrekwang.customwidgetdemo.activity.PullRefreshLayoutActivity;
import com.newtrekwang.customwidgetdemo.fragment.ItemListDialogFragment;
import com.newtrekwang.customwidgetdemo.service.DownloadService;
import com.newtrekwang.customwidgetdemo.toast.ToastBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    @BindView(R.id.mainActivty_listView)
    ListView mainActivtyListView;

    private List<Map<String,String>> list=new ArrayList<>();
    private SimpleAdapter simpleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initData();
        init();
    }

    private void initData() {
        Map<String,String> map_1=new HashMap<>();
        map_1.put("title","PullRefreshLayout");
        list.add(map_1);
        Map<String,String> map_2=new HashMap<>();
        map_2.put("title","CustomToast");
        list.add(map_2);
        Map<String,String> map_3=new HashMap<>();
        map_3.put("title","ImageLoader");
        list.add(map_3);
        Map<String,String> map_4=new HashMap<>();
        map_4.put("title","ExpandTextView");
        list.add(map_4);
        Map<String,String> map_5=new HashMap<>();
        map_5.put("title","AutoScrollViewPager");
        list.add(map_5);
        Map<String,String> map_6=new HashMap<>();
        map_6.put("title","Dialog");
        list.add(map_6);
        Map<String,String> map_7=new HashMap<>();
        map_7.put("title","ContentProvider");
        list.add(map_7);
        Map<String,String> map_8=new HashMap<>();
        map_8.put("title","BottemSheetDialogFragment");
        list.add(map_8);
        Map<String,String> map_9=new HashMap<>();
        map_9.put("title","startServiceDownload");
        list.add(map_9);
Map<String,String> map_10=new HashMap<>();
        map_10.put("title","notification");
        list.add(map_10);


    }

    private void init() {
        simpleAdapter=new SimpleAdapter(this,list,android.R.layout.simple_list_item_1,new String[]{"title"}, new int[]{android.R.id.text1});
        mainActivtyListView.setAdapter(simpleAdapter);
        mainActivtyListView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position){
            case 0:
               startActivity(new Intent(MainActivity.this, PullRefreshLayoutActivity.class));
                break;
            case 1:
               view.startActionMode(mActionModeCallback);
                break;
            case 2:
                startActivity(new Intent(MainActivity.this, ImageActivity.class));
                break;
            case 3:
                Intent intent_1=new Intent(MainActivity.this, CommonActivity.class);
                intent_1.putExtra("key",100);
                startActivity(intent_1);
                break;
            case 4:
                Intent intent_2=new Intent(MainActivity.this, CommonActivity.class);
                intent_2.putExtra("key",101);
                startActivity(intent_2);
                break;
            case 5:
                Intent intent_3=new Intent(MainActivity.this,CommonActivity.class);
                intent_3.putExtra("key",102);
                startActivity(intent_3);
                break;
            case 6:
                Intent intent_4=new Intent(MainActivity.this,CommonActivity.class);
                intent_4.putExtra("key",103);
                startActivity(intent_4);
                break;
            case 7:
                ItemListDialogFragment listDialogFragment=new ItemListDialogFragment();
                listDialogFragment.show(getSupportFragmentManager(),"BottemSheetDialogFragment");
                break;
            case 8:
                final EditText editText=new EditText(MainActivity.this);
                editText.setText("http://qiniu-app.pgyer.com/bee391e7cde131eedbdd7db7bfec1979.apk?e=1495978740&attname=app-release.apk&token=6fYeQ7_TVB5L0QSzosNFfw2HU8eJhAirMF5VxV9G:tF_xeFxOJfLB4GOUQ1y01Icxql0=&sign=371f4fba3f23eee42d4869920f7d2476&t=592ad2f4");
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("输入url")
                        .setView(editText)
                        .setNegativeButton("取消",null)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String url=editText.getText().toString();
                                if (!TextUtils.isEmpty(url)){
                                    Intent intent_5=new Intent(MainActivity.this, DownloadService.class);
                                    intent_5.putExtra("url",url);
                                    startService(intent_5);
                                }
                            }
                        }).create().show();
                break;
            case 9:

                break;
            default:
                break;
        }
    }
/**
 * 利用上下文菜单
 */
    private ActionMode.Callback mActionModeCallback=new ActionMode.Callback() {
    // Called when the action mode is created; startActionMode() was called
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            // Inflate a menu resource providing context menu items
            MenuInflater inflater = mode.getMenuInflater();
            inflater.inflate(R.menu.context_toast_menu, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }
    // Called when the user selects a contextual menu item
        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()) {
                case R.id.context_toast_menu_defaultToast:
                  new ToastBuilder()
                          .setContentString("hello world")
                          .createDefaultTextToast(MainActivity.this)
                          .show();
                    mode.finish(); // Action picked, so close the CAB
                    return  true;
                case R.id.context_toast_menu_customViewToast:
                    new ToastBuilder()
                            .setContentViewResId(R.layout.toast_layout)
                            .createCustomViewToast(MainActivity.this)
                            .show();
                    mode.finish();
                    return  true;
                default:
                    return false;
            }
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {

        }
    };

}
