package com.example.android;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

public class TempConvertAsyncTaskActivity extends Activity {
	private static final String PREFERENCES = "PREFERENCES";
	private static final String URL = "url";
	private String lastUrl, url;
    private DefaultHttpClient client;
    private HttpGet httpGet;

	private TextView tv_host;
	private TextView tv_temp;
	private TextView tv_response;
	private RadioButton r_cel;
	private Button b_convert;
	public static final String MyPREFERENCES = "MyPrefs";
	public static final String Host = "hostKey";
	public static final String Temp = "tempKey";

	SharedPreferences sharedpreferences;
	
	/** Called when the activity is first created. */

		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.main);
			loadPreferences();
			
			client = new DefaultHttpClient();
			httpGet = new HttpGet();

			tv_host = (TextView) findViewById(R.id.etv_host);
			// Added for testing
			tv_host.setText("http://192.168.1.75/TempConvert/convert.php");
			tv_temp = (TextView) findViewById(R.id.etv_temp);
			tv_response = (TextView) findViewById(R.id.tv_response);
			r_cel = (RadioButton) findViewById(R.id.r_cel);

			loadPreferences();

		}

	public void run(View view){
		String h  = tv_host.getText().toString();
		String t  = tv_temp.getText().toString();
		SharedPreferences.Editor editor = sharedpreferences.edit();
		editor.putString(Host, h);
		editor.putString(Temp, t);

		editor.commit();

	}

		/** * Demonstrates loading of preferences The last value in the URL string will * be loaded */

		private void loadPreferences() {
			sharedpreferences = getSharedPreferences(PREFERENCES, Activity.MODE_PRIVATE);
			// Set this to connect to the my Apache Servlet program (NameHitServlet)
			lastUrl = sharedpreferences.getString(URL, "");
			if (sharedpreferences.contains(Host))
			{
				tv_host.setText(sharedpreferences.getString(Host, ""));
			}
			if (sharedpreferences.contains(Temp))
			{
				tv_temp.setText(sharedpreferences.getString(Temp, ""));
			}
		}
		
		private class DownloadWebPageTask extends AsyncTask<String, Void, String> {
			@Override
			protected String doInBackground(String... urls) {
				String response = "";
				for (String url : urls) {
		
					try {
						
						httpGet.setURI(new URI(url));
						HttpResponse execute = client.execute(httpGet);
						InputStream content = execute.getEntity().getContent();

						BufferedReader buffer = new BufferedReader(
								new InputStreamReader(content));
						String s = "";
						while ((s = buffer.readLine()) != null) {
							response += s;
						}

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				return response;
			}

			@Override
			protected void onPostExecute(String result) {
				String UOM = r_cel.isChecked() ? "CF" : "FC";
 				tv_response.setText(tv_temp.getText() + "" + UOM.charAt(0) + " = " + result.trim() + UOM.charAt(1));
			}
		}

		public void readWebpage(View view) {
			DownloadWebPageTask task = new DownloadWebPageTask();
			url = tv_host.getText().toString()+"?degree="+ tv_temp.getText().toString() +
					"&" + "type=" + (r_cel.isChecked() ? "celsius" : "fahrenheit");
			task.execute(new String[] { url });
		}
		
	}