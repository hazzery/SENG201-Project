# SENG201-Group69 Project

The project's repository can be found on GitHub here: https://github.com/hazzery/SENG201-Project

Made by Harrison Parkes and Daniel Smith

## Importing into Eclipse

Eclipse is the most difficult IDE to import this project into. The following steps should be followed to import the project into Eclipse:

1. Extract zip archive file
2. Open Eclipse
3. Select `File` -> `Import...`
   1. `General` -> `Projects from Folder or Archive`
4. Click `Next`
5. Click `Directory` and select the folder containing the extracted files
6. Click `Finish`

## Building the project

Once imported into Eclipse, you can build the project by right-clicking on the project in the package explorer and selecting `Export...`.
From there, select `Runnable JAR file` and click `Next`.
Select the `Launch Configuration` as `Main - hpa101_dsm123_SportsTournament`. Click `Finish` to build the project.
Ignore any possible warnings, Eclipse thinks its smart, but it's not.

## Running the Application

To run the file, open a terminal window and `cd` into the directory containing the jar file.
Then run the command `chmod +x hpa101_dsm123_SportsTournament.jar` where `hpa101_dsm123_SportsTournament.jar` is the name of the jar file you exported.
Execute run the command `java -jar hpa101_dsm123_SportsTournament.jar` to run the application.
