# GamerApp

An Android application built with Kotlin and Jetpack Compose, with a gaming-focused interface for browsing game-related content.

## Current Screens

The source code includes UI screens and navigation for:

- Sign in and account creation
- Password recovery and reset
- OTP validation
- Gaming news
- Game store
- User profile

This repository documents the current implementation; backend integration and production readiness should be verified separately.

## Tech Stack

- Kotlin
- Android SDK
- Jetpack Compose
- Material 3
- Navigation Compose
- Gradle Kotlin DSL

## Requirements

- Android Studio with a compatible Android SDK
- JDK compatible with the project's Gradle configuration

## Run Locally

1. Clone the repository.
2. Open the project in Android Studio.
3. Allow Gradle to sync.
4. Run the `app` configuration on an emulator or Android device.

You can also build from the project root using the Gradle wrapper:

```bash
./gradlew assembleDebug
```

## Project Structure

```text
app/src/main/java/tn/esprit/gamerapp/
├── data/       # Game and news data models
├── elements/   # Reusable UI components
├── screens/    # Compose screens
└── MainActivity.kt
```

## Status

This README describes the visible project structure and current screens. Add screenshots, a short demo, and notes about completed versus planned features before presenting the app as production-ready.
