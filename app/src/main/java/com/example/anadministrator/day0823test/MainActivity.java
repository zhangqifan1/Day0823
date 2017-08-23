package com.example.anadministrator.day0823test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.example.anadministrator.day0823test.Cache.getDiskCachePath;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private String path = "http://v.juhe.cn/toutiao/index?type=top&key=dbedecbcd1899c9785b95cc2d17131c5";
    /**
     * 价格从低到高
     */
    private Button mButUp;
    /**
     * 价格从高到低
     */
    private Button mButDown;
    private ListView mLv;
    private MyAdapter myAdapter;
    private List<Bean.ResultBean.DataBean> data;
    private File file;
    private Bean.ResultBean result1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        //先得到文件位置目录
        String path = getDiskCachePath(this);
        file = new File(path, "aa");
        if (!file.exists()) {
            try {
                file.createNewFile();
                System.out.println(file.getPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //请求数据
        RequestData();

    }

    private void RequestData() {
        x.http().get(new RequestParams(path), new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Bean bean = new Gson().fromJson(result, Bean.class);
                System.out.println(bean.result.data.size());
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    fileOutputStream.write(result.getBytes());
                    fileOutputStream.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                result1 = bean.result;
                data = result1.data;
                myAdapter = new MyAdapter(result1, MainActivity.this);
                mLv.setAdapter(myAdapter);


            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });

    }

    private void initView() {
        mButUp = (Button) findViewById(R.id.butUp);
        mButUp.setOnClickListener(this);
        mButDown = (Button) findViewById(R.id.butDown);
        mButDown.setOnClickListener(this);
        mLv = (ListView) findViewById(R.id.lv);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.butUp:
                result1.data=data;
                Collections.sort(data);
                myAdapter.notifyDataSetChanged();
                break;
            case R.id.butDown:

                Collections.sort(data);

                List<Bean.ResultBean.DataBean> dateNew = new ArrayList<>();

                for (int i = this.data.size()-1; i >=0; i--) {
                    dateNew.add(this.data.get(i));
                }
                for (int i = 0; i <dateNew.size() ; i++) {
                    System.out.println(dateNew.get(i).date);
                }
                result1.data=dateNew;
                myAdapter.notifyDataSetChanged();
                break;
        }
    }
}
