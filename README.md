# SimpleTimerTaskHandler

  ### [中文版]( https://github.com/lgw666/SimpleTimerTaskHandler/blob/master/README_CH.md)

- A simple timer task handler based on android.os.Handler - You can decide when to execute tasks.
- Author: Luo Guowen 
- Email: <a href="#">luoguowen123@qq.com</a>

* ### Setup

  **Step 1. Add the JitPack repository to your build file Add it in your root build.gradle at the end of repositories:**

  ```groovy
  	allprojects {
  		repositories {
  			...
  			maven { url 'https://jitpack.io' }
  		}
  	}
  ```

  ​

  **Step 2. Add the dependency：**

  ```groovy
  	dependencies {
  	        compile 'com.github.lgw666:SimpleTimerTaskHandler:v1.0'
  	}
  ```

  ​



* ### Usage

     **Step 1. Get the instance of SimpleTimerTaskHandler**

     `SimpleTimerTaskHandler handler = SimpleTimerTaskHandler.getInstance();`

     ​

     **Step 2. Create your task**

     ```Java
     SimpleTimerTask task = new SimpleTimerTask() {
                 @Override
                 public void run() {
                   // Do what you want
                 }
             };
     ```

     ​	or

     ```Java
      SimpleTimerTask loopTask = new SimpleTimerTask(loop interval) {
                 @Override
                 public void run() {
                   // Do what you want
                 }
             };
     ```

     ​	*Tip: the task will be a loop task once you use the second constructor.*

     ​

     **Step 3. Execute your task by using SimpleTimerTaskHandler**

     Execute real-time task

     ```Java
     void sendTask(int taskNum, SimpleTimerTask task); 
     ```

     Execute delay task

     ```java
     void sendTaskDelayed(int taskNum, SimpleTimerTask task, long delayMillis);
     ```

     Execute timer task, accurate to hour.

     ```java
     void sendTimerTask(int taskNum, SimpleTimerTask task, int hour); 
     ```

     Execute timer task, accurate to minute.

     ```java
     void sendTimerTask(int taskNum, SimpleTimerTask task, int hour, int minute); 
     ```

     Execute timer task, accurate to second.
     ```java
     void sendTimerTask(int taskNum, SimpleTimerTask task, int hour, int minute, int second); 
     ```

     ​

* ### Params: 

   Task id, eg: 0, 1, 2...

   ```Java
   int taskNum;
   ```

   The task you want to execute.

   ```java
   SimpleTimerTask task;
   ```

   The hour to execute this task, based on the day.

   ```java
   int hour
   ```
   The minute in the hour, based on the day.

   ```java
   int minute
   ```

   The seconds in the minute, based on the day.

   ```java
   int second
   ```

   *e.g.: If you want execute a task at 13:50:30, you can invoke*

   ```java
   handler.sendTimerTask(task num, task, 13, 50, 30);
   ```

* ### SimpleTimerTask:

   Must use SimpleTimerTaskHandler and SimpleTimerTask together.

   ​

   **Public constructors**

   Default constructor  associates this task.

   ```Java
   SimpleTimerTask();
   ```


   Constructor associates this task change its task type from default to loop, and sets the loop interval.

   ```Java
   SimpleTimerTask(long loopInterval);
   ```

      



* ### Tips：

  - The task number of every task should be different if there are more than one tasks.
  - You`d better use a work Thread or AsyncTask to do some long time operations, e.g: network request.
  - You can do UI operation in the task
