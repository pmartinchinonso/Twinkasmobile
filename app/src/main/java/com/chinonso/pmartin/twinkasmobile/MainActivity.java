package com.chinonso.pmartin.twinkasmobile;

import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    WebView webView;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                webView.loadUrl("https://twinkas.com/account?signin");
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        webView = (WebView) findViewById(R.id.webView);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setMax(100);
        progressBar.setVisibility(View.GONE);

        if (savedInstanceState != null){
            webView.restoreState(savedInstanceState);
        }else {
            webView.getSettings().setJavaScriptEnabled(true);
            webView.getSettings().setSupportZoom(true);
            webView.getSettings().setBuiltInZoomControls(false);
            webView.getSettings().setLoadWithOverviewMode(true);
            webView.getSettings().setUseWideViewPort(true);
            webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
            webView.setBackgroundColor(Color.WHITE);

            webView.setWebViewClient(new ourViewClient());

            webView.setWebChromeClient(new WebChromeClient(){
                @Override
                public void onProgressChanged(WebView view, int newProgress) {
                    progressBar.setProgress(newProgress);
                    if (newProgress < 100 && progressBar.getVisibility() == ProgressBar.GONE){
                        progressBar.setVisibility(ProgressBar.VISIBLE);
                    }
                    if (newProgress == 100){
                        progressBar.setVisibility(ProgressBar.GONE);
                    }

                }
            });

        }
    }

    public class ourViewClient extends WebViewClient{
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            CookieManager.getInstance().setAcceptCookie(true);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        webView.saveState(outState);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_back) {
            if (webView.canGoBack())
                webView.goBack();
            return true;
        }else if (id == R.id.action_forward){
            if (webView.canGoForward())
                webView.goForward();
            return true;
        }else if (id == R.id.action_refresh){
            webView.reload();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            webView.loadUrl("https://twinkas.com/home");
        } else if (id == R.id.nav_abouttwinkas) {
            webView.loadUrl("https://twinkas.com/about");
        } else if (id == R.id.nav_howitworks) {
            webView.loadUrl("https://twinkas.com/about-twinkas");
        } else if (id == R.id.nav_ewallet) {
            webView.loadUrl("https://twinkas.com/about-wallet");
        } else if (id == R.id.nav_paid) {
            webView.loadUrl("https://twinkas.com/paid");
        } else if (id == R.id.nav_faq) {
            webView.loadUrl("https://twinkas.com/faq");
        } else if (id == R.id.nav_testimonies) {
            webView.loadUrl("https://twinkas.com/testimonials");
        } else if (id == R.id.nav_twinkasblog) {
            webView.loadUrl("https://twinkas.com/blog");
        } else if (id == R.id.nav_ecommerce) {
            webView.loadUrl("https://twinkas.com/resellerspanel/");
        } else if (id == R.id.nav_signup) {
            webView.loadUrl("https://twinkas.com/account");
        } else if (id == R.id.nav_login) {
            webView.loadUrl("https://twinkas.com/account?signin");
        } else if (id == R.id.nav_contact) {
            webView.loadUrl("http://www.twinkas.com/contact");
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
