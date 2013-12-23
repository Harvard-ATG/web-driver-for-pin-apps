###Summary
This repository contains (very) basic code using the Selenium WebDriver API that will use automated testing to test a Harvard PIN-enabled web application.

The classes here provide a simple framework that can be used to test a Harvard PIN enabled web application.  Since all PIN protected apps accessed via the PIN login page, this contains a class that will handle entering the XID and password (or HUID and password) for a particular PIN page, essentially allowing an automated way to log in. (WARNING: be careful with login credentials. Avoid Hardcoding them, and try to use low value credentials for testing (such as an XID). 

This code takes login credentials passed in as command line arguments.

The sample code here (in WebDriverTest.java) simply logs into my.harvard, does a course search in the course planner on the term "byzantine" and then takes a screenshot of the results. 

Please note, I am new to WebDriver, and am doing the most basic calls.  There are likely better ways to do the same tasks. This will hopefully be a starting point, and provide a set of code that can be downloaded and adapted to test Harvard specific PIN protected apps. 

###Requirements 
You must have first installed the Selenium WebDriver API .jar file. http://docs.seleniumhq.org/download/

###Running this code
This code will take an XID and PIN (or an HUID and password, but be careful not to leave these lying around where someone could get them...) as command line arguments, will fire-up an instance of Firefox and log into my.harvard, search the Course Planner on the keyword "Byzantine" and will then take a screenshot of the result. 


###Classes

- WebDriverTest.java - This is the main class that contains the specific logic that is used to interact with the my.harvard page.  This is the class that will need to be modified (or copied and modified) to customize to test other applications. 

To run from the command line with two arguments:   java WebDriverTest <xid or huid> <xid password or PIN>

- WebDriverTestQuizmo.java - This is a modified version of WebDriverTest.java that has been altered to run a more complex set of tests on a web application called Quizmo. (Quizmo is a Harvard Application used by instructors to give quizes to students.) 

- PinSession.java - This contains the logic to authenticate from the PIN webpage.  This can be completely reused for any PIN protected application.

- DefinedConstants.java - This is my ad hoc simple way to set up global constants and configurations without the overhead of a "real config file". It contains long URLs that map to PIN entry pages. It also defines some text sequences useful in testing. (Such as strings with problematic characters, strings with over 2000 chars, strings with multi-byte characters.)   This will need to be modified to add a URL for a new app that will be tested. 
