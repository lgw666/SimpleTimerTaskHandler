# SimpleTimerTaskHandler
- 一个基于android.os.Handler的简单定时任务处理器 - 你可以决定什么时候执行任务.
- 作者: Luo Guowen 
- 邮箱: <a href="#">luoguowen123@qq.com</a>

* ### 设置

  **Step 1. 添加JitPack到你的Project的build.gradle::**

  ```groovy
  	allprojects {
  		repositories {
  			...
  			maven { url 'https://jitpack.io' }
  		}
  	}
  ```

  ​

  **Step 2. 添加依赖：**

  ```groovy
  	dependencies {
  	        compile 'com.github.lgw666:SimpleTimerTaskHandler:v1.0'
  	}
  ```

  ​



* ### 使用方法

     **Step 1. 获取到SimpleTimerTaskHandler的对象**

     `SimpleTimerTaskHandler handler = SimpleTimerTaskHandler.getInstance();`

     ​

     **Step 2. 创建你的任务**

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

     ​	*温馨提示: 一旦使用了第二个构造，任务将会变成循环任务.*

     ​

     **Step 3. 用SimpleTimerTaskHandler来执行你的任务**

     执行即时任务

     ```Java
     void sendTask(int taskNum, SimpleTimerTask task); 
     ```

     执行延时任务。

     ```java
     void sendTaskDelayed(int taskNum, SimpleTimerTask task, long delayMillis);
     ```

     执行定时任务, 精确到小时。

     ```java
     void sendTimerTask(int taskNum, SimpleTimerTask task, int hour); 
     ```

     执行定时任务, 精确到分。

     ```java
     void sendTimerTask(int taskNum, SimpleTimerTask task, int hour, int minute); 
     ```

     执行定时任务, 精确到秒。
     ```java
     void sendTimerTask(int taskNum, SimpleTimerTask task, int hour, int minute, int second); 
     ```

     ​

* ### 参数: 

   任务编号, eg: 0, 1, 2...

   ```Java
   int taskNum;
   ```

   需要执行的任务。

   ```java
   SimpleTimerTask task;
   ```

   执行任务的某一小时时刻, 基于当天.

   ```java
   int hour
   ```
   执行任务的某一小时内的分钟时刻, 基于当天.

   ```java
   int minute
   ```

   执行任务的某一小时内某一分钟内的秒数时刻, 基于当天.

   ```java
   int second
   ```

   *例: 如果你想在 13:50:30 时执行一个任务, 可以像下面一样调用*

   ```java
   handler.void sendTimerTask(task num, task, 13, 50, 30);
   ```

* ### SimpleTimerTask:

   SimpleTimerTaskHandler 和 SimpleTimerTask 必须搭配使用.

   ​

   **公共构造**

   任务的默认构造。

   ```Java
   SimpleTimerTask();
   ```


   带参的任务构造将任务类型从默认变为循环并设置循环间隔。

   ```Java
   SimpleTimerTask(long loopInterval);
   ```

      



* ### Tips：

  - 当任务数超过一个时，每个任务的任务编号都应不同
  - 进行耗时操作时最好启动一个异步线程或者使用AsyncTask, 例: 网络请求.
  - 可以在任务里操作UI
