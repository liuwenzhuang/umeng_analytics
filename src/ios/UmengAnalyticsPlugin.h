#import <Cordova/CDVPlugin.h>

@interface AnalyticsPlugin : CDVPlugin

- (void)init:(CDVInvokedUrlCommand*)command;

- (void)onEvent:(CDVInvokedUrlCommand*)command;

- (void)onEventValue:(CDVInvokedUrlCommand*)command;

- (void)setDebugMode:(CDVInvokedUrlCommand*)command;
@end