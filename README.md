# CaloriesWatcher

CaloriesWatcher is an Android application that helps the user keep track of their calories gained and burnt.
The application has a simple algorithm for calculating calories burned while doing exercises and depends on the user to input the exercises, excluding the walking.

The application also is dependant on the user to input the foods they eat, including the amount of calories/100g of the meal.

How to get CaloriesWatcher?

Clone the github repository (https://github.com/Mtk112/CaloriesWatcher) and run the application while your phone is connected to the computer.

Features:
- Food input
- Exercise input
- History
- Pedometer

Food input

The user has to give name for each meal, calories in 100g of that meal and the portion of that meal eaten.
Each entry is saved into the database and the user can review their eating history through the history feature.

Exercise input

The user can add exercises outside walking to the application. Each exercise must have a name, duration and intensity.
The application will calculate (somewhat inaccurate) the calories burned by doing the exercise and save the information to the database.
These entries can be loaded by typing already existing exercise name and pressing the Load/Confirm button. The loaded data ALWAYS picks the first enty that had the name that the user typed.

The data can be reviewd through history feature.

History

By utilizing this feature the user can review lists of his/her exercises and meals.The user can also view these information in a bar chart
that calculates and displays calories gained, calories burnt and the difference in sepparate bars.
