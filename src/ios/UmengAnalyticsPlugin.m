#import "UmengAnalyticsPlugin.h"
#import "MobClick.h"
#import <Cordova/CDVPluginResult.h>
#import <Foundation/Foundation.h>

@implementation AnalyticsPlugin

- (void)init:(CDVInvokedUrlCommand*)command
{
    NSString* callbackId = command.callbackId;
    [MobClick startWithAppkey:@"56205c82e0f55adda500a6da" reportPolicy:BATCH   channelId:@""];

    CDVPluginResult* pluginResult = nil;
    [self.commandDelegate sendPluginResult:pluginResult callbackId:callbackId];
}

- (void)onEvent:(CDVInvokedUrlCommand*)command
{
    CDVPluginResult* pluginResult = nil;
    NSMutableDictionary* message = [command.arguments objectAtIndex:0];
    NSString* eventId = [message objectForKey:@"key"];
    if(eventId != nil) {
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK];
    } else {
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:@"eventId was null"];
        return;
    }
    [MobClick event:eventId];
    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];

}

- (void)onEventValue:(CDVInvokedUrlCommand*)command
{
    CDVPluginResult* pluginResult = nil;
    NSMutableDictionary* message = [command.arguments objectAtIndex:0];
    NSString* eventId = [message objectForKey:@"key"];
    if(eventId != nil) {
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK];
    } else {
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:@"eventId was null"];
        return;
    }
    int amount = [[message objectForKey:@"amount"] intValue] ?: 0;
    NSLog(@"amount:%d", amount);
    [MobClick event:eventId acc:amount];
    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}

- (void)setDebugMode:(CDVInvokedUrlCommand*)command
{
    CDVPluginResult* pluginResult = nil;
    bool enabled = [[command.arguments objectAtIndex:0] boolValue];
    if(enabled != nil) {
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK];
    } else {
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:@"Arg was null"];
    }
    [MobClick setLogEnabled:enabled];
    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}
@end
