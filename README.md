# UrootCheck

UrootCheck is a lightweight Android root verification application designed for legacy Android devices.

## Features

- Root access verification
- Requests authorization through the installed root manager
- Executes the `id` command inside the root shell
- Detects whether the root shell command succeeds
- Displays a clear success or failure result
- Simple and lightweight interface
- Gray default interface
- Green success state
- Blue UrootCheck header
- Orange status messages
- Designed for older Android versions
- No unnecessary background services
- No root access is required for installation
- Uses the Android root authorization mechanism rather than attempting to bypass it

## Root Verification

When the user presses the check button, UrootCheck requests root authorization from the installed root manager.

If authorization is granted, the application executes:

    id

If the command succeeds, UrootCheck reports that root access has been successfully verified.

If authorization is denied or the command fails, UrootCheck reports that root access could not be verified.

## Project

- Name: UrootCheck
- Language: Java
- Build system: Gradle
- Package: `UrootCheck.app`
- Application type: Android application
- Status: Experimental

## Purpose

UrootCheck is intended to provide a simple and fast way to verify whether root access is functioning correctly on an Android device.
