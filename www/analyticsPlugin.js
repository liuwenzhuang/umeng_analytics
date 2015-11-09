/*
message为数组，[{key,value},{key,value}]
*/
/*(function() {
  var exec;

  exec = require('cordova/exec');

  module.exports = {
    onEvent:function(UmengAnalyticsPlugin.m,onSuccess,onError){
      return exec(onSuccess,onError,'AnalyticsPlugin','onEvent',[UmengAnalyticsPlugin.m]);
    },
    onEventValue:function(UmengAnalyticsPlugin.m,onSuccess,onError){
      return exec(onSuccess,onError,'AnalyticsPlugin','onEventValue',[UmengAnalyticsPlugin.m]);
    }
  };

}).call(this);*/
var AnalyticsPlugin = function () {
};

AnalyticsPlugin.prototype.error_callback = function (msg) {
    console.log("Javascript Callback Error: " + msg)
}

AnalyticsPlugin.prototype.call_native = function (name, args, callback) {
    return cordova.exec(callback, this.error_callback, 'AnalyticsPlugin', name, args);
}

AnalyticsPlugin.prototype.init = function () {
    data = [];
    this.call_native("init", data, null);
};

AnalyticsPlugin.prototype.setDebugMode = function (mode) {
    this.call_native("setDebugMode", [mode], null);
}

AnalyticsPlugin.prototype.onKillProcess = function () {
    data = []
    this.call_native("onKillProcess", data, null);
}

AnalyticsPlugin.prototype.onResume = function () {
    data = []
    this.call_native("onResume", data, null);
}
AnalyticsPlugin.prototype.onPause = function () {
    data = []
    this.call_native("onPause", data, null);
}

AnalyticsPlugin.prototype.onEvent = function(message) {
    return cordova.exec(null,null,'AnalyticsPlugin','onEvent',[message]);
};

AnalyticsPlugin.prototype.onEventValue = function (message) {
    return cordova.exec(null,null,'AnalyticsPlugin','onEventValue',[message]);
};

if(!window.plugins) {
  window.plugins = {};
}

if(!window.plugins.analyticsPlugin) {
  window.plugins.analyticsPlugin = new AnalyticsPlugin();
}

module.exports = new AnalyticsPlugin();
