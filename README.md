# PlanMyDay

This app is designed for Android Studio Giraffe | 2022.3.1 Patch 2

Emulator Device: Pixel 2
API Version: 34
Account credentials:
> Username: grader@usc.edu
> Password: Hundred

Potential Issues with with 3rd party services:
- Firebase: Can take a while to load after signing up or logging in
- Google Maps: May sometimes not draw the routes between points, which can be quickly fixed by
resetting the app and/or android studio. It could also be due to poor connection

Steps on running this application:
1. Download the files and open on android studio
2. Make sure you have a device with Google Play capability and internet, we use the Pixel 2 API 34
3. You can download this through the device manager, make sure there's a google play icon and API 34
4. Open and run the application with the "run button"
5. You can use the credentials provided above to log in, or make a new account
6. If you forget your password, you can reset it by sending an reset email to your account
![forgetpassword!](../forgetpassword.jpg)
7. You will be logged in automatically when you register, but you can log out if desired
8. Start creating an itinerary by clicking the "+" button on the bottom right
9. Specify which attractions to choose from and over how many days
10. View the map and itinerary, you may select which days to view

Notes:
- You may make an account with valid email to view forget password functionality, but an example
image was placed into the implementation documentation to show what the email looks like
- Travel time between places includes the time it takes for places to open
- The itinerary assumes starting the route at the earliest time the first place opens
- All times are in drive time despite the routes being shown as walking or public transport
- In our app, Day One Represents Sunday