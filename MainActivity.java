package ir.parkban.app;

import android.Manifest;import android.app.*;import android.content.*;import android.content.pm.PackageManager;import android.graphics.Color;import android.net.*;import android.os.*;import android.view.*;import android.webkit.*;import android.widget.*;import androidx.activity.*;import androidx.appcompat.app.AppCompatActivity;import androidx.core.app.ActivityCompat;import androidx.core.content.ContextCompat;import androidx.core.splashscreen.SplashScreen;

public class MainActivity extends AppCompatActivity {
 WebView web; ProgressBar progress; TextView error; final String URL="http://park-ban.ir/";
 @Override public void onCreate(Bundle b){ SplashScreen.installSplashScreen(this); super.onCreate(b); getWindow().setStatusBarColor(Color.rgb(6,52,93)); getWindow().setNavigationBarColor(Color.rgb(6,52,93));
  getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE); requestNotif();
  FrameLayout root=new FrameLayout(this); web=new WebView(this); progress=new ProgressBar(this); error=new TextView(this); error.setText("اتصال به اینترنت برقرار نیست.\n\nبرای تلاش دوباره ضربه بزنید."); error.setTextSize(18); error.setGravity(Gravity.CENTER); error.setTextColor(Color.DKGRAY); error.setVisibility(View.GONE);
  root.addView(web,new FrameLayout.LayoutParams(-1,-1)); FrameLayout.LayoutParams pp=new FrameLayout.LayoutParams(100,100,Gravity.CENTER); root.addView(progress,pp); root.addView(error,new FrameLayout.LayoutParams(-1,-1)); setContentView(root); setup(); load(); }
 void setup(){ web.setBackgroundColor(Color.WHITE); WebSettings s=web.getSettings(); s.setJavaScriptEnabled(true); s.setDomStorageEnabled(true); s.setSupportZoom(false); s.setBuiltInZoomControls(false); s.setDisplayZoomControls(false); s.setMediaPlaybackRequiresUserGesture(false); web.setWebViewClient(new WebViewClient(){public void onPageStarted(WebView v,String u,android.graphics.Bitmap x){progress.setVisibility(View.VISIBLE);error.setVisibility(View.GONE);} public void onPageFinished(WebView v,String u){progress.setVisibility(View.GONE);} public void onReceivedError(WebView v,WebResourceRequest r,WebResourceError e){if(r.isForMainFrame()) showError();}}); }
 void load(){ if(isOnline()) web.loadUrl(URL); else showError(); }
 boolean isOnline(){ConnectivityManager c=(ConnectivityManager)getSystemService(CONNECTIVITY_SERVICE); Network n=c.getActiveNetwork(); return n!=null && c.getNetworkCapabilities(n)!=null && c.getNetworkCapabilities(n).hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET);}
 void showError(){progress.setVisibility(View.GONE);error.setVisibility(View.VISIBLE);error.setOnClickListener(v->load());}
 void requestNotif(){if(Build.VERSION.SDK_INT>=33 && ContextCompat.checkSelfPermission(this,Manifest.permission.POST_NOTIFICATIONS)!=PackageManager.PERMISSION_GRANTED) ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.POST_NOTIFICATIONS},10);}
 @Override public void onBackPressed(){if(web.canGoBack()) web.goBack(); else super.onBackPressed();}
}
