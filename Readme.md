# umeng_analytics_event_cordova

## Quick Start
### Android
Android support comman analytic and user-defined events.

#### 1.setup your own UMENG_APPKEY and UMENG_CHANNEL.

Open plugin.xml located in plugin's directory, and find:
```shell
<meta-data android:name="UMENG_CHANNEL" android:value="YOUR_CHANNEL"/>
<meta-data android:name="UMENG_APPKEY" android:value="YOUR_APP_KEY"/>
```
YOUR_CHANNEL: your own app's channel, arbitray, some channle like play store, wandoujia and so on.<br>
YOUR_APP_KEY: the app key that could be received after you add app at www.umeng.com.

#### 2.install plugin
```shell
cd /PATH/TO/YOUR/PROJECT
cordova plugin add /PATH/TO/YOUR/PLUGIN_DIRECTORY/umeng_analytics_event_cordova
```

#### 3.integrate plugin into js:
Open your project's app.js that located in www/js and insert follow lines to run method:
```javascript
$ionicPlatform.ready(function () {
  window.plugins.analyticsPlugin.init();//start umeng analytic
  window.plugins.analyticsPlugin.setDebugMode(true);//optional, just use for debug
}
```
#### 4.send user-defined event
Before you define user-defined event data in your app, guarantee you haved define your app's event at umeng.
Whenever you want trigger user-defined event, just use follow snippet:
```javascript
window.plugins.analyticsPlugin.onEvent(message, null, null);//count event, analyse just about counts, like button tap's count
```
message is an object, {{key, value}, {key,valye}...}
something like:
```javascript
var message = {
  key:'trade_account',//necessary, equal to event id that you define at umeng
  value:'loginTest',//value corresponding to event id
  param:[//array, stand for event params, necessary, can be null
    {
      type:'testType1',
      value:'testValue1'
    },
    {
      type:'testType2',
      value:'testValue2'
    }
  ]
};
```

or
```javascript
window.plugins.analyticsPlugin.onEventValue(message, null, null);//calculate event, analyse more data, like goods value
```
and message is like:
```javascript
var message = {
  key:'trade_account',//necessary, equal to event id that you define at umeng
  value:'loginTest',//value corresponding to event id
  param:[//array, stand for event params, necessary, can be null
    {
      type:'testType1',
      value:'testValue1'
    },
    {
      type:'testType2',
      value:'testValue2'
    }
  ],
  amount:count//necessary
};
```

#### 5.save analytic data before app exit
Insert follow line before exiting app:
```javascript
window.plugins.analyticsPlugin.onKillProcess();
```

#### See [Umeng analytic android integate doc](http://dev.umeng.com/analytics/android-doc/integration)

### IOS
IOS now just support comman analytic.

#### 1.setup your own UMENG_APPKEY and UMENG_CHANNEL.

Open src/ios/UmengAnalyticsPlugin.m located in plugin's directory, and find:
```shell
[MobClick startWithAppkey:@"YOU_APP_KEY" reportPolicy:BATCH   channelId:@"Web"];
```
channelId: your own app's channel, arbitray, some channle like play store, App Store, wandoujia and so on. If channelId is set to nil or @"", then it will treat as "App Store".<br>
YOUR_APP_KEY: the app key that could be received after you add app at www.umeng.com. Note android and ios use different keys.<br>
reportPolicy: the policy that how analytic data to be sent to umeng, BATCH stands for send data when app start. User can define.

#### 2.install plugin
```shell
cd /PATH/TO/YOUR/PROJECT
cordova plugin add /PATH/TO/YOUR/PLUGIN_DIRECTORY/umeng_analytics_event_cordova
```

#### 3.integrate plugin into js:
Open your project's app.js that located in www/js and insert follow lines to run method:
```javascript
$ionicPlatform.ready(function () {
  window.plugins.analyticsPlugin.init();//start umeng analytic
}
```

#### See [Umeng analytic ios integate doc](http://dev.umeng.com/analytics/ios-doc/integration)
