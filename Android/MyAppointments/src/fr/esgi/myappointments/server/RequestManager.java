package fr.esgi.myappointments.server;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class RequestManager {

	private static final String BASE_URL = "http://myapps-main.cloudapp.net:1664/";

	private static AsyncHttpClient client = new AsyncHttpClient();

	public static void get(String url, RequestParams params, JsonHttpResponseHandler responseHandler) {
		Log.d("REQUEST", "Url= "+getAbsoluteUrl(url)+" - Params= "+((params!=null)?params.toString():""));
		client.get(getAbsoluteUrl(url), params, responseHandler);
	}

	public static void post(String url, RequestParams params, JsonHttpResponseHandler responseHandler) {
		Log.d("REQUEST", "Url= "+getAbsoluteUrl(url)+" - Params= "+((params!=null)?params.toString():""));
		client.post(getAbsoluteUrl(url), params, responseHandler);
	}
	
	public static void delete(String url, RequestParams params, JsonHttpResponseHandler responseHandler) {
		Log.d("REQUEST", "Url= "+getAbsoluteUrl(url)+" - Params= "+((params!=null)?params.toString():""));
		client.delete(getAbsoluteUrl(url), responseHandler);
	}

	private static String getAbsoluteUrl(String relativeUrl) {
		return BASE_URL + relativeUrl;
	}
	
	private static AsyncHttpClient getClient() {
		return client;
	}
}
