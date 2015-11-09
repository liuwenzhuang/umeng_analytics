package com.lwz.UmengAnalyticsEventPlugin;

import java.util.HashMap;
import android.content.Context;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.umeng.analytics.MobclickAgent;

public class AnalyticsPlugin extends CordovaPlugin{
	private static Context mContext;
	private final  String mPageName = "index";
	@Override
	public void initialize(CordovaInterface cordova, CordovaWebView webView) {
		super.initialize(cordova, webView);
		mContext = this.cordova.getActivity().getApplicationContext();
	}

	@Override
	public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) throws JSONException {
		
		
		if ("init".equals(action)) {
			init();
		}
		if ("setDebugMode".equals(action)) {
			boolean mode = args.getBoolean(0);
			setDebugMode(mode);
		}
		if ("onKillProcess".equals(action)) {
			onKillProcess();
		}

		if("onEvent".equals(action)){
			JSONObject obj = args.getJSONObject(0);
			String eventId = obj.getString("key");
			JSONArray paramArray = obj.getJSONArray("param");
			//单纯的统计点击次数
			if(paramArray.length() == 0){
				MobclickAgent.onEvent(webView.getContext(),eventId);
			}else{
				JSONObject tmp = new JSONObject();
				HashMap<String, String> map = new HashMap<String, String>();
				for(int i=0,len=paramArray.length();i<len;i++){
					tmp = paramArray.getJSONObject(i);
					map.put(tmp.getString("type"), tmp.getString("value"));
				}
				MobclickAgent.onEvent(webView.getContext(), eventId, map);
			}
			return true;
		}
		if("onEventValue".equals(action)){
			JSONObject obj = args.getJSONObject(0);
			String eventId = obj.getString("key");
			JSONArray paramArray = obj.getJSONArray("param");
			//计算事件
			int number = obj.getInt("amount");
			if(paramArray.length() == 0){
				MobclickAgent.onEventValue(webView.getContext(), eventId, null, number);
			}else{
				JSONObject tmp = new JSONObject();
				HashMap<String, String> map = new HashMap<String, String>();
				for(int i=0,len=paramArray.length();i<len;i++){
					tmp = paramArray.getJSONObject(i);
					map.put(tmp.getString("type"), tmp.getString("value"));
				}
				MobclickAgent.onEventValue(webView.getContext(), eventId, map, number);
			}
			return true;
		}
		return false;
	}

	void init() {
		onResume(false);
		MobclickAgent.openActivityDurationTrack(false);
		MobclickAgent.setAutoLocation(true);
		MobclickAgent.updateOnlineConfig(mContext);
	}

	void setDebugMode(boolean mode) {
		MobclickAgent.setDebugMode(mode);
	}

    @Override
    public void onPause(boolean multitasking) {
        MobclickAgent.onPageEnd(mPageName);
        MobclickAgent.onPause(mContext);
    }

    @Override
    public void onResume(boolean multitasking) {
        MobclickAgent.onPageStart(mPageName);
        MobclickAgent.onResume(mContext);
    }

    void onKillProcess() {
    	MobclickAgent.onKillProcess(mContext);
    }

}
