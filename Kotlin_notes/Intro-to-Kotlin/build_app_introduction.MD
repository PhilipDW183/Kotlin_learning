# Introduction to building an application

## Configuring a project 

- <b>Name</b> is the name of your app.
- <b>Package Name</b> is the name used by the android system to uniquely identify your app. 
Usually this defaults to the name of your organisation followed by the name of the app, all in lower case.
- <b>Save location</b> is the location where all the files related to your project are saved. 
- <b>Language</b> defines which programming language you want to use for your project
- <b>Minimum SDK</b> indicates the minimum version of andoird that your app can run on. 

## Getting started with tests

### Unit test code

Local tests in Android are located in the `test` directory and they are usually unit tests. These test a
small piece of code to ensure it is functioning properly. With unit tests you can test functions,
classes and properties. Local tests are executed on the Java virtual Machine, which means
that they run in a development environment without needing a device or emulator. 

### Insturmentation-test code.

instumentation tests is usually a user-interface test (UI test). This lets you test part parts
of an app that depend on activity and fragment lifecycles and platform APIs and services.

UI etsts don't test code directory. Instead they test UI to make sure that the correct UI components 
are displayed and that the UI behaves as expected when actinops are taken within the UI. These must run
on a physical device or emulator. 

When this is run the code is built into its own APK that is installed on the device along with the regular
app APK. The test APK then runs its test against the APK.