# GuitarStudio <img src="/app/src/main/res/mipmap-mdpi/guitar_studio_icon.png" />
Audio processing for Android using NDK, focusing Guitar

<p align="center">
<img src="/app/src/main/res/mipmap-xxhdpi/guitar_studio_icon.png" />
</p>

##Mockups
<p align="center">
<img src="https://cloud.githubusercontent.com/assets/5948318/12928254/8fd1e874-cf53-11e5-9b3f-21d936ba6001.jpg" />
<img src="https://cloud.githubusercontent.com/assets/5948318/12928253/8fc8c730-cf53-11e5-9239-cbad23bc131c.jpg" />
</p>

## Setup

### NDK
To setup the NDK, you must follow [this tutorial](http://kn-gloryo.github.io/Build_NDK_AndroidStudio_detail/)

You can watch the video of the tutorial [here](https://www.youtube.com/watch?v=RmPuwdxR1qs)

## Use Cases
| Use Case | Status | Implementation Notes |
|----------|:--------:|--------|
| The app should capture audio from an external audio source | :white_check_mark:  | It works if the device supports external audio source |
| The app should send the processed signal through an external audio output | :white_check_mark: |  |
| The process of capturing, processing and emitting the audio should have a very small latency | :white_check_mark: | The latency to process the signal is smaller than 2 ms, but the total latency is much bigger |
| The app should offer at least 2 effects (Distortion and Delay) |  |  |

## Limitations
* Due to hardware specificities, the app will not work in all the android devices

## Credits
* [Bruno Calou](https://github.com/brunocalou)

## TODO
* explain that some Android models are compatible and others not (give examples)
* explain something about the hardware (images please)

