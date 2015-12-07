package com.zykj.yuliang.activity;

import com.zykj.yuliang.R;
import com.zykj.yuliang.R.layout;
import com.zykj.yuliang.R.menu;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;

public class DetailActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        flater = LayoutInflater.from(this);
        initViews();
        initClick();
        initEvents();
        
    }
    private RadioButton rb_quanbu;
    private RadioButton rb_renwu;
    private RadioButton rb_xuetu;
    private RadioButton rb_duihuan;
    private LinearLayout log_frame;
    private View view_t;
    private ImageButton btn_detail_back;
    
    LayoutInflater flater;

    protected void initClick(){
    	btn_detail_back.setOnClickListener(this);
    	rb_quanbu.setOnClickListener(this);
    	rb_renwu.setOnClickListener(this);
    	rb_xuetu.setOnClickListener(this);
    	rb_duihuan.setOnClickListener(this);
    }
    
    protected void initViews() {
        rb_quanbu= (RadioButton) findViewById(R.id.rb_quanbu);
        rb_renwu= (RadioButton) findViewById(R.id.rb_renwu);
        rb_xuetu= (RadioButton) findViewById(R.id.rb_xuetu);
        rb_duihuan= (RadioButton) findViewById(R.id.rb_duihuan);
        log_frame = (LinearLayout) findViewById(R.id.log_frame);
        btn_detail_back = (ImageButton)findViewById(R.id.btn_detail_back);
    }

    protected void initEvents() {
        rb_quanbu.setChecked(true);
        View v_quanbu =flater.inflate(R.layout.activity_quanbu_content,log_frame,false);
        log_frame.addView(v_quanbu);
    }


    @Override
    public void onClick(View v) {
    	if(v.getId()==R.id.btn_detail_back){
    		this.finish();
        }
        if(v.getId()==R.id.rb_quanbu){
        	view_t =flater.inflate(R.layout.activity_quanbu_content,log_frame,false);
        	log_frame.removeAllViews();
        	log_frame.addView(view_t);
        }
        if(v.getId()==R.id.rb_renwu){
        	view_t =flater.inflate(R.layout.activity_renwu_content,log_frame,false);
        	log_frame.removeAllViews();
        	log_frame.addView(view_t);
        }
        if(v.getId()==R.id.rb_xuetu){
        	view_t =flater.inflate(R.layout.activity_xuetu_content,log_frame,false);
        	log_frame.removeAllViews();
        	log_frame.addView(view_t);
        }
        if(v.getId()==R.id.rb_duihuan){
        	view_t =flater.inflate(R.layout.activity_duihuan_content,log_frame,false);
        	log_frame.removeAllViews();
        	log_frame.addView(view_t);
        }
        
    }
}
