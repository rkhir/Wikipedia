
## Wikipedia Automated Test Solution:
  
  This framework is using [Playwright]() and [Junit]() to execute UI tests, and Spotless for code styling and formatting.

### Running the Tests:

 the project is shipped with [Gradle](https://docs.gradle.org/6.1.1/userguide/userguide.html) wrapper v6.1.1. 
  To run the tests follow the steps below:
  
  MacOS
  ```bash
    ./gradelw build
    ./gradlew test
``` 

  Windows OS
  ```bash
        ./gradelw.bat build
        ./gradlew.bat test
```

#### Screenshots:
On Test failure Playwright will take screenshot of the current page and saves it to `./screenshots`

  