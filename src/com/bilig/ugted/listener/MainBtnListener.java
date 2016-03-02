package com.bilig.ugted.listener;

import com.bilig.ugted.activity.MainActivity;
import com.bilig.ugted.activity.R;
import com.bilig.ugted.fragment.NewestTedListFragment;
import com.bilig.ugted.fragment.BestTedListFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.TextView;

public class MainBtnListener implements View.OnClickListener {
	
	
	private MainActivity mainActivity;
	
	//fragment
	public Fragment newestTedsFragment;
	public Fragment bestTedsFragment;
	
	
	//¿Ø¼þ
	private TextView tv_newestBtn;
	private TextView tv_bestBtn;
	
	public MainBtnListener(MainActivity mainActivity) {
		// TODO Auto-generated constructor stub
		this.mainActivity=mainActivity;
		tv_newestBtn=(TextView) mainActivity.findViewById(R.id.tv_newestTed_content);
		tv_bestBtn=(TextView) mainActivity.findViewById(R.id.tv_bestTed_content);
	}
	
	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		
		int viewId=view.getId();
		switch(viewId){
		
			case R.id.tv_newestTed_content:
				resetBtn();
				selectFragment(100);
				break;
			case R.id.tv_bestTed_content:
				resetBtn();
				selectFragment(101);
				break;
				
		}
		
		
	}
	

	private void resetBtn(){
		tv_newestBtn.setBackgroundColor(mainActivity.getResources().getColor(R.color.text_normal));
		tv_bestBtn.setBackgroundColor(mainActivity.getResources().getColor(R.color.text_normal));
	
	}
	
	
	public void selectFragment(int fId) {
		
		FragmentManager fm = mainActivity.getSupportFragmentManager();
		FragmentTransaction transaction = fm.beginTransaction();
		hideFragment(transaction);
		switch (fId) {
			case 100:
				if (newestTedsFragment == null){
					newestTedsFragment=new NewestTedListFragment();
					transaction.add(R.id.id_content, newestTedsFragment);
				}else{
					transaction.show(newestTedsFragment);
				}
				tv_newestBtn.setBackgroundColor(mainActivity.getResources().getColor(R.color.text_press));
				break;
			case 101:
				if (bestTedsFragment == null){
					bestTedsFragment=new BestTedListFragment();
					transaction.add(R.id.id_content, bestTedsFragment);
				}else{
					transaction.show(bestTedsFragment);
				}
				tv_bestBtn.setBackgroundColor(mainActivity.getResources().getColor(R.color.text_press));
				break;

	
		default:
			break;
		}
		transaction.commit();
		
	}
	
	
	private void hideFragment(FragmentTransaction transaction)
	{
		
		if (newestTedsFragment != null){
			transaction.hide(newestTedsFragment);
		}
		if (bestTedsFragment != null){
			transaction.hide(bestTedsFragment);
		}
		
		
		
	}

	

}
