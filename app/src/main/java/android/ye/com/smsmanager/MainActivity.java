package android.ye.com.smsmanager;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TabHost;

public class MainActivity extends TabActivity implements View.OnClickListener{

    private TabHost tabHost;
    private LinearLayout ll_conversation;
    private LinearLayout ll_folder;
    private LinearLayout ll_group;
    private ImageView ivSide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabHost = getTabHost();

        addTab("conversation", "会话", R.drawable.tab_conversation, ConversationUI.class);
        addTab("folder", "文件夹", R.drawable.tab_folder, FolderUI.class);
        addTab("group","群组",R.drawable.tab_group,GruopUI.class);

        ll_conversation = (LinearLayout) findViewById(R.id.ll_conversation);
        ll_folder = (LinearLayout)findViewById(R.id.ll_folder);
        ll_group = (LinearLayout) findViewById(R.id.ll_group);

        ll_group.setOnClickListener(this);
        ll_folder.setOnClickListener(this);
        ll_conversation.setOnClickListener(this);

        ivSide = (ImageView) findViewById(R.id.iv_slide);

        // 初始化滑动图块的大小，位置 ，让滑动图块大小，位置，与llConversation 一致

        int widght = ll_conversation.getWidth();
        int height = ll_conversation.getHeight();
        /**
         * 在onCreate执行时，仅仅是创建了各个view对象，这些view对象，还没有测量大小，也没有指定位置，所以，是不能直接获得 真实大小的
         */
        System.out.println(widght + height);
        // 获得当前View树的观察者，添加一个全局的，位置改变的监听
        ll_conversation.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            /**
             * view树上，所有的view，layout方法执行完以后，回调此方法 ，
             */
            public void onGlobalLayout() {
                int widght = ll_conversation.getWidth();
                int height = ll_conversation.getHeight();

                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) ivSide.getLayoutParams();

                params.height = height;
                params.width= widght;

                int left = ll_conversation.getLeft();




            }
        });





    }


    private void addTab(String tag,CharSequence label,int iconid,Class<? extends Activity> clazz){
        //创建一个新的标签，tag值为conversation
        TabHost.TabSpec tabSpec = tabHost.newTabSpec(tag);
        //设置文字与图标
        tabSpec.setIndicator(label,getResources().getDrawable(iconid));
        Intent intent = new Intent(this,clazz);
        tabSpec.setContent(intent);
        tabHost.addTab(tabSpec);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_conversation:


                break;
            case R.id.ll_folder:


                break;
            case R.id.ll_group:



                break;
        }
    }
}
