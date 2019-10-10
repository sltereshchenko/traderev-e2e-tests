Steps to run:
1) Preconditions:
    a) Java 1.8 (JDK) must be installed properly (follow oracle documentation)
    b) Maven must be installed properly (follow maven documentation)
    c) Make sure that access from your laptop to public maven repository is present
    d) Install chrome or firefox browser (by default framework will use chrome browser)

2) To run all tests from command line (terminal), run command: mvn clean test


Multi-browsers:
Framework supports multi-browsers, by default it will use chrome.
To change browser, make changes in /src/main/resources/envconfigs/production.properties
Example: browser=firefox
         browser=chrome


Config files (location: /src/main/resources/envconfigs/):
Config file name may be changed by sending parameter when run mvn: -DenvConfigFile=fileName
Example: mvn clean test -DenvConfigFile=production.properties -Dcucumber.options='--tags "@browser"'


Features files location: /src/test/resources/features/
Test Runner location: /src/test/java/com/stereshchenkot/raderev/runners/
Steps location: /src/test/java/com/stereshchenkot/raderev/steps/

Logging: log4j, INFO level (log4j.properties)


Tested on:
    1) macOS Mojave / Chrome Version 77.0.3865.90
    2) macOS Mojave / Firefox Version 69.0.2