package com.bilig.ugted.activity;


import com.bilig.ugted.activity.R;
import com.bilig.ugted.domain.HeadTitle;
import com.bilig.ugted.fragment.BestTedListFragment;
import com.bilig.ugted.fragment.NewestTedListFragment;
import com.bilig.ugted.listener.MainBtnListener;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.TextView;

public class MainActivity extends BaseActivity{
	
	//实现tabhost
	private TextView bestTedContent;
	private TextView newestTedContent;
	
	public MainBtnListener mbl;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState,R.layout.activity_main);
        
        initView();
        
        //默认 fragment
    	mbl.selectFragment(100);
        
        
        
    }
    private void initView() {
    	mbl=new MainBtnListener(this);
    	
    	bestTedContent=(TextView) this.findViewById(R.id.tv_bestTed_content);
    	newestTedContent=(TextView) this.findViewById(R.id.tv_newestTed_content);
    	//监听
    	bestTedContent.setOnClickListener(mbl);
    	newestTedContent.setOnClickListener(mbl);
    	
		
	}
    
	@Override 
	public void onAttachFragment(Fragment fragment) {
		//TODO Auto-generated method stub 
		super.onAttachFragment(fragment);
		if (mbl.newestTedsFragment == null && fragment instanceof NewestTedListFragment) {
			mbl.newestTedsFragment = (NewestTedListFragment)fragment;
		}else if (mbl.bestTedsFragment == null && fragment instanceof BestTedListFragment) {
			mbl.bestTedsFragment = (BestTedListFragment)fragment;
		}
	}

	
	@Override
	protected HeadTitle getHeadTitle() {
		// TODO Auto-generated method stub
		return new HeadTitle(this);
	}
	
	

    
}
