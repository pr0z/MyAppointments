package fr.esgi.myappointments.activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import fr.esgi.myappointments.R;
import fr.esgi.myappointments.adapter.MenuListAdapter;
import fr.esgi.myappointments.adapter.MenuListAdapter.SlideMenuItem;
import fr.esgi.myappointments.fragment.CalendarFragment;

public class MenuActivity extends ActionBarActivity {
	
	public static final String TAG = "MenuActivity";
	
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private MenuListAdapter mMenuAdapter;
    private ActionBarDrawerToggle mDrawerToggle;
    private String mTitle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        
        // set a custom shadow that overlays the main content when the drawer opens
//        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        // Set the adapter for the list view
//        mDrawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item, mMenu));
        mMenuAdapter = new MenuListAdapter(this, R.menu.sidemenu);
        Log.v(TAG, "MENU = "+mMenuAdapter.getCount());
        //Add items menu to the Menu
        mDrawerList.setAdapter(mMenuAdapter);
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.drawable.ic_drawer, R.string.drawer_open, R.string.drawer_close) {
            public void onDrawerClosed(View view) {
//                supportInvalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
            public void onDrawerOpened(View drawerView) {
                getSupportActionBar().setTitle(R.string.drawer_menu);
//                supportInvalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        // Set the drawer toggle as the DrawerListener
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        
        if (savedInstanceState == null) {
            selectItem(R.id.item_home);
        }
    }
    
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
    	super.onPostCreate(savedInstanceState);
    	// Sync the toggle state after onRestoreInstanceState has occurred.
    	mDrawerToggle.syncState();
    }
    
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
    	super.onConfigurationChanged(newConfig);
    	
    	mDrawerToggle.onConfigurationChanged(newConfig);
    }
    
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
    	super.onRestoreInstanceState(savedInstanceState);
    }
    
    @Override
    protected void onSaveInstanceState(Bundle outState) {
//        outState.putString("fragment", mFragment != null ? mFragment : "");
    	super.onSaveInstanceState(outState);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//    	MenuInflater inflater = getSupportMenuInflater();
//    	inflater.inflate(R.menu.main, menu);
         
    	return super.onCreateOptionsMenu(menu);
    }
    
    /* Called whenever we call invalidateOptionsMenu() */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
    	// If the nav drawer is open, hide action items related to the content view
    	boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
    	
    	return super.onPrepareOptionsMenu(menu);
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	Log.d("MENU", "Item= "+item.getItemId()+" - "+item.getTitle());
    	
        // Handle your other action bar items...
    	switch (item.getItemId()) {
    	case android.R.id.home:
    		if (mDrawerLayout.isDrawerOpen(mDrawerList)) {
                mDrawerLayout.closeDrawer(mDrawerList);
            } else {
                mDrawerLayout.openDrawer(mDrawerList);
            }
    		return true;
        
        default:
           return super.onOptionsItemSelected(item);
    	}
    }
    
    //Listener for the DrawerLayout
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(((SlideMenuItem) parent.getAdapter().getItem(position)).id); 
        }
    }

    /** Swaps fragments in the main content view */
    private void selectItem(int id) {
        Fragment fragment = null;
        int position = 0;
        Log.d("MENUITEM", "ITEM=" +id);
        
        // Create a new fragment and specify the planet to show based on position
    	switch (id) {
//    	case R.id.item_about :
//    		fragment = new AboutFragment();
//    		break;
//    	
//    	case R.id.item_categories :
//    		fragment = new CategoriesFragment();
//    		break;
//    		
//    	case R.id.item_favorites :
//    		fragment = new ListFavoriteFragment();
//    		break;
//    		
//    	case R.id.item_account :
////    		fragment = new AccountFragment();
//    		break;
//    		
//    	case R.id.item_excuses :
//    		fragment = new ListExcuseFragment();
//    		break;
//    		
    	default : 
    		fragment = new CalendarFragment();
    		break;
    	}
    	
    	for (int i=0 ; i < mDrawerList.getAdapter().getCount() ; i++) {
    		SlideMenuItem menuItem = (SlideMenuItem) mDrawerList.getAdapter().getItem(i);
    		if (menuItem.id == id) {
    			position = i;
    			break;
    		}
    	}
    	
    	Log.d("POSITION", "n°"+position);
    	
//        Bundle args = new Bundle();
//        args.putInt(PlanetFragment.ARG_PLANET_NUMBER, position);
//        fragment.setArguments(args);

    	if (fragment != null) {
	        // Insert the fragment by replacing any existing fragment
	        FragmentManager fragmentManager = getSupportFragmentManager();
	        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
	
	        // Highlight the selected item, update the title, and close the drawer
	        mDrawerList.setItemChecked(position, true);
    	}
    	
        mDrawerLayout.closeDrawer(mDrawerList);
    }

    public void setTitle(String title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }
}
