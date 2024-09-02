# AndroidComponents
This is repository has a demo or example Android applications for each android components(Activity,service,Fragment,ConetentProviders,BroadcastReciever,Inetent))

Names app is Content Resolver App

---

# AndroidComponents

This repository contains demo or example Android applications for each of the following Android components:

- Activities
- Services
- Broadcast Receivers
- Content Providers
- Fragments
- Intents

## Content Resolver App

### 1. Activities

**What is an Activity?**

An Activity represents a single screen with a user interface. It’s the entry point for interacting with the user. Whenever you launch an app and see a screen, you’re interacting with an Activity.

**Example:**

In a messaging app, the screen where you see a list of messages is an Activity. When you open a message to read it, that's another Activity.

**Activity Lifecycle:**

- **onCreate():** Initialize your UI and components.
- **onStart():** Make the Activity visible, but not interactive yet.
- **onResume():** The Activity is now in the foreground and interactive.
- **onPause():** Partially visible but not interactive; release lightweight resources.
- **onStop():** Activity is no longer visible; release heavy resources.
- **onRestart():** Preparing to come back to the foreground.
- **onDestroy():** Final cleanup before the Activity is destroyed.

### 2. Services

**What is a Service?**

A Service is a component that runs in the background to perform long-running operations or to do work for remote processes. Unlike Activities, Services don't have a user interface.

**Example:**

Playing music in the background while you browse other apps or downloading files in the background are tasks handled by Services.

**Types of Services:**

- **Started Service:** Runs until it stops itself. A started service can be a background service or a foreground service.
- **Bound Service:** Runs as long as another application component is bound to it.

**Flags:**

- **START_REDELIVER_INTENT:** The system will restart the service if it is killed and re-deliver the original Intent that was used to start the service.
- **START_NOT_STICKY:** The system will not restart the service if it is killed.
- **START_STICKY:** The system will restart the service if it is killed.

### 3. Broadcast Receivers

**What is a Broadcast Receiver?**

Broadcast Receivers respond to broadcast messages from other applications or the system itself. These messages are announcements that something has happened, like the battery being low or the screen turning off.

**Example:**

A weather app might listen for changes in network connectivity to update the weather data only when the device is connected to the internet.

**Usage:**

Broadcast Receivers are an important tool for creating event-driven apps.

### 4. Content Providers

**What is a Content Provider?**

Content Providers manage access to a structured set of data. They allow different applications to share data in a secure way.

**Example:**

The Contacts app uses a Content Provider to share contact data with other apps, such as the Messaging app when you select a contact to send a message.

**Usage:**

Content Providers are powerful for enabling data sharing between different apps.

### 5. Fragments

**What is a Fragment?**

Fragments are reusable portions of your UI. They represent a portion of the user interface in an Activity, and you can combine multiple Fragments within a single Activity to create a multi-pane UI.

**Example:**

In a tablet interface, you might see a list of items on the left and details of the selected item on the right, both handled by Fragments.

**Fragment Lifecycle:**

Fragments also have their own lifecycle, similar to Activities, and understanding how they interact with their hosting Activity is key to managing complex UIs.

**Layouts:**

- **Single-Pane Layout (Mobile):** You would need two activities (MainActivity for the list and DetailActivity for the details).
- **Two-Pane Layout (Tablet):** You would need one activity (MainActivity), which handles both the list and the details in a two-pane layout.

### 6. Intents

**What are Intents?**

Intents are messaging objects that allow components to request actions from other components, either within the same app or in other apps.

In simple terms, Intents are like messages that one part of an app sends to another part, asking it to do something. These messages can be sent to parts of the same app or even to parts of other apps on your phone.

**Example:**

If you want to share a photo from your app to another app, like an email or social media app, you use an Intent to request that action.

**Types of Intents:**

- **Explicit Intent:** Directly specify the component to start. The component can be an activity, service, or broadcast.
- **Implicit Intent:** Declare a general action to perform, which allows another app to handle it.

**Example:**

Giving a URL and letting the system decide which browser to use.

---

