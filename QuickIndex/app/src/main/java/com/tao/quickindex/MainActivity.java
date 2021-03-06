package com.tao.quickindex;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.nineoldandroids.view.ViewHelper;
import com.nineoldandroids.view.ViewPropertyAnimator;
import android.view.Window;
import android.view.animation.OvershootInterpolator;
import android.widget.ListView;
import android.widget.TextView;
import android.os.Handler;
import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    private QuickIndexBar quickIndexBar;
    private ListView listview;
    private TextView currentWord;

    private ArrayList<Friend> friends = new ArrayList<Friend>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去掉标题栏
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        quickIndexBar = (QuickIndexBar) findViewById(R.id.quickIndexBar);
        listview = (ListView) findViewById(R.id.listview);
        currentWord = (TextView) findViewById(R.id.currentWord);

        //1.准备数据
        fillList();
        //2.对数据进行排序
        Collections.sort(friends);
        //3.设置Adapter
        listview.setAdapter(new MyAdapter(this,friends));

        quickIndexBar.setOnTouchLetterListener(new QuickIndexBar.OnTouchLetterListener() {
            @Override
            public void onTouchLetter(String letter) {
                //根据当前触摸的字母，去集合中找那个item的首字母和letter一样，然后将对应的item放到屏幕顶端
                for (int i = 0; i < friends.size(); i++) {
                    String firstWord = friends.get(i).getPinyin().charAt(0)+"";
                    if(letter.equals(firstWord)){
                        //说明找到了，那么应该讲当前的item放到屏幕顶端
                        listview.setSelection(i);
                        break;//只需要找到第一个就行
                    }
                }

                //显示当前触摸的字母
                showCurrentWord(letter);
            }
        });


        //通过缩小currentWord来隐藏
        ViewHelper.setScaleX(currentWord, 0);
        ViewHelper.setScaleY(currentWord, 0);

//		Log.e("tag", PinYinUtil.getPinyin("黑    马"));//HEIMA
//		Log.e("tag", PinYinUtil.getPinyin("#黑**马"));//#HEI**MA
//		Log.e("tag", PinYinUtil.getPinyin("O(∩_∩)O~黑。，马"));//HEIMA
    }
    private boolean isScale = false;
    private Handler handler = new Handler();
    protected void showCurrentWord(String letter) {
        currentWord.setText(letter);
        if(!isScale){
            isScale = true;
            ViewPropertyAnimator.animate(currentWord).scaleX(1f)
                    .setInterpolator(new OvershootInterpolator())
                    .setDuration(450).start();
            ViewPropertyAnimator.animate(currentWord).scaleY(1f)
                    .setInterpolator(new OvershootInterpolator())
                    .setDuration(450).start();
        }

        //先移除之前的任务
        handler.removeCallbacksAndMessages(null);

        //延时隐藏currentWord
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
//				currentWord.setVisibility(View.INVISIBLE);
                ViewPropertyAnimator.animate(currentWord).scaleX(0f).setDuration(450).start();
                ViewPropertyAnimator.animate(currentWord).scaleY(0f).setDuration(450).start();
                isScale = false;
            }
        }, 1500);
    }

    private void fillList() {
        // 虚拟数据
        friends.add(new Friend("李伟"));
        friends.add(new Friend("张三"));
        friends.add(new Friend("阿三"));
        friends.add(new Friend("阿四"));
        friends.add(new Friend("段誉"));
        friends.add(new Friend("段正淳"));
        friends.add(new Friend("张三丰"));
        friends.add(new Friend("陈坤"));
        friends.add(new Friend("林俊杰1"));
        friends.add(new Friend("陈坤2"));
        friends.add(new Friend("王二a"));
        friends.add(new Friend("林俊杰a"));
        friends.add(new Friend("张四"));
        friends.add(new Friend("林俊杰"));
        friends.add(new Friend("王二"));
        friends.add(new Friend("王二b"));
        friends.add(new Friend("赵四"));
        friends.add(new Friend("杨坤"));
        friends.add(new Friend("赵子龙"));
        friends.add(new Friend("杨坤1"));
        friends.add(new Friend("李伟1"));
        friends.add(new Friend("宋江"));
        friends.add(new Friend("宋江1"));
        friends.add(new Friend("李伟3"));
    }

}
