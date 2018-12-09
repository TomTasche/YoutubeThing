# YoutubeThing
Use Android Things to play a Youtube playlist on your TV

# Use case
I was looking for a solution to allow a person that is not tech-savy (read, grandparents) to watch a set of live streams on YouTube on their TV without having to get used to Android TV, Fire TV, Chromecast, etc. In other words, I needed a turn-on-in-the-morning-turn-off-in-the-night-solution.

# Ingredients
I used a normal Raspberry Pi 3 B+ starter kit to start working on this project. To that I connected a TV via HDMI and a mouse via USB (necessary to setup WiFi connection on Android Things!).

After having the basic functionality implemented I knew I'd need a remote control. For that I used the [Andoer® FM4 Magical 2.4G Wireless Remote Control for Android TV](https://www.amazon.de/dp/B015SO37SY/ref=cm_sw_r_tw_dp_U_x_mqsdCb2DNMNXA) because of its ease-of-use (just a few buttons).

Having the Raspberry run my project for more than 30 minutes I noticed that video playback started to stutter and the Raspberry Pi got very hot. My first attempt to fix this was to use [heatsinks from Aukru](https://www.amazon.de/dp/B00UCSO6SW/ref=cm_sw_r_tw_dp_U_x_jqsdCbVSNMSYW), which worked fine. However I still went for an [additional fan from the same vendor](https://www.amazon.de/dp/B0756YN92J/ref=cm_sw_r_tw_dp_U_x_hqsdCbRGEVXNR) to make sure it runs cool even during summer. This setup never ran hot again during my testing!

# Notes
There are a few things I would have liked to implement but are not possible due to limitations in Android Things. Most importantly, I would love to support HDMI-CEC to turn the TV off together with Raspberry Pi and vice versa. [This is not possible at the moment.](https://issuetracker.google.com/issues/120687875)

Moreover, I wanted to override the default behavior of the power button on the remote control. Unfortunately that is not possible in recent versions of Android, so I had to [disable the power button using root access](https://www.guidingtech.com/57291/disable-physical-buttons-android-phone/) (edit Generic.kl and Generic_Iot.kl).

# Setup
- download APK for the official Youtube App (nodpi version), e.g. from APKMirror
- flash Android Things on SDcard, https://developer.android.com/things/hardware/raspberrypi
- boot Raspberry Pi, connect via ADB and install the Youtube APK
- install this project and enjoy!
