# BottomsUp :beer:

This is the delivery for group 3 in TDT4240 - Software architetcure. 

The project is written in Java and builds with Gradle. 
The main purpose of the project is to develop a simple multiplayer smartphone game on the development platform Android. The application has an online component and focuses on the quality attributes modifiability, usability and performance. 

The project is a game meant for youths who want to up the level of their pre-game. This is done by creating a game which draws inspiration from the mini-game "Totempole" from "Buzz! Junior: Jungle Party". 

## Overview

1. [Structure](#structure-file_folder)
2. [How to compile the project](#how-to-compile-the-project-hammer)
3. [How to run the project](#how-to-run-the-project-arrow_forward) 
 
<!-- Include readme-file to explain structure and how to compile/run project. -->

## Structure :file_folder:

Relative to the root of the code repository, the project is located in the folder `/bottomsup`.

The project is mainly spread over four different modules:

`/bottomsup/assets`  
Here lies the assets which is used in the game. The assets are mainly images, except for one font-file.

`/bottomsup/core`  
Here lies the game logic. This module is further split into three modules.

`/bottomsup/core/src/com.mygdx.bottomsup`  
Here lies the main game-file and interface for the database. 

`/bottomsup/core/src/components`  
Here lies the logic for the block tower which is used in the game.

`/bottomsup/core/src/screens`  
Here lies the different screens for the game. These contains the logic used in the game logic. 


# How to compile the project :hammer:
The project can be built using an APK.

## APK
To build the project as an APK file, do the following:
1. Open the project in Android Studio.
2. On the toolbar, click on Build -> Build Bundle(s) / APK(s) -> Build APK(s).
3. The compiled APK file can be found in `android/build/outputs/apk`.

# How to run the project :arrow_forward:
Running the project is **easy**. If the project was successfully built with the APK method, run the APK on an Android emulator. This can be done directly in Android Studio by creating a new emulator in device manager.
