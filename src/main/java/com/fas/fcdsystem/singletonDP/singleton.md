## What is Singleton Design Pattern?
<b>Singleton design pattern</b> is one of the most used design patterns in Java. This type of design pattern comes under <b>creational pattern</b> as this pattern provides one of the best ways to create an object.

![](https://cdn-media-1.freecodecamp.org/images/1*GOAK3XdRvjrcpX9dq0fUrQ.png)

This pattern involves a <b>SINGLE CLASS </b> which is responsible to create an object while making sure that <b>ONLY SINGLE OBJECT </b> gets created. This class provides a way to access its only object which can be accessed directly without need to instantiate the object of the class.

![](https://treewebsolutions.com/uploads/article/65/the-singleton-pattern-in-php_ys3fRymTJ-TCrLVu.png)

## Implementation

We're going to create a <b>App</b> class having its <b>constructor</b> as <b>private</b> and a static method to get its <b>object</b>. <b>Logger</b> class provides a way to access its only object which can be accessed directly without need to instantiate the object of the class.

Logger.java
```java
    public class Logger {
        private static Logger logger = null;
        private Logger() {
        }
        public static Logger getInstance() {
            if (logger == null) {
                logger = new Logger();
            }
            return logger;
        }
        public void log(String message) {
            System.out.println(message);
        }
    }
 ```
 
## Advantages of Singleton Design Pattern

* <b>Single Responsibility</b> - The single responsibility principle states that a class should have
* <b>Single Point of Access</b> or <b>Permits Controlled Access</b> - There is only one instance of the class, so there is only one point of access to it.
* <b>Controlled Access</b> and <b>Reduced Name Space</b> - The class controls the access to the single instance, so it can decide when and how to instantiate the instance.
 
## Disadvantages of Singleton Design Pattern

* <b>Singletons are Global</b> - The single instance is global to the class, so it is not possible to subclass the class with a local instance.
* <b>Hard to Test</b>  or <b> Change</b>- The class is difficult to test because the class is tightly coupled to the single instance.

    
## How Many Ways to Implement Singleton Design Pattern in Java?

### <b>LAZY SINGLETON</b>

```java
public class LazyAlfrescoSingleton implements Serializable {
    private static LazyAlfrescoSingleton instance = null;

    //prevent instantiation from other classes
    private LazyAlfrescoSingleton() {
    }

    // a static method to get the instance
    public static LazyAlfrescoSingleton getInstance() {
        if (instance == null) {
            instance = new LazyAlfrescoSingleton();
        }
        return instance;
    }

    // prevent cloning
    protected Object readResolve() {
        return instance;
    }
}

```
#### Disadvantages of Lazy Singleton
The above implementation works fine in case of <b>single threaded environment</b> but when it comes to <b>multithreaded systems,</b> it can cause issues if multiple threads are inside the <i>if condition at the same time</i>. It will destroy the singleton pattern and both threads will get the different instances of singleton class.
```java
/* Thread 1 enters the if block*/
if (instance == null) {
    // Thread 2 enters the if block
    if (instance == null) {
        instance = new LazyAlfrescoSingleton();
    }
}
```

### <b>EAGER SINGLETON</b>
From the start of the <b>JVM is loaded,</b> the instance of the singleton class is created. This is the easiest method to create a singleton class but it has a <b>drawback that instance </b> is created even though client application might not be using it.

```java
public class EagerAlfrescoSingleton implements Serializable {
    private static EagerAlfrescoSingleton instance = new EagerAlfrescoSingleton();

    //prevent instantiation from other classes
    private EagerAlfrescoSingleton() {
    }

    // a static method to get the instance
    public static EagerAlfrescoSingleton getInstance() {
        return instance;
    }

    // prevent cloning
    protected Object readResolve() {
        return instance;
    }
}
```

### <b>THREAD SAFE SINGLETON</b>
The above implementation works fine and provides thread-safety but it reduces the performance because of <b>synchronized method,</b> although we need it only for the first few threads who might create the separate instances (Read: Java Synchronization).

```java
public class ThreadSafeAlfrescoSingleton implements Serializable {
    private static ThreadSafeAlfrescoSingleton instance = null;

    //prevent instantiation from other classes
    private ThreadSafeAlfrescoSingleton() {
    }

    // a static method to get the instance
    public static synchronized ThreadSafeAlfrescoSingleton getInstance() {
        if (instance == null) {
            instance = new ThreadSafeAlfrescoSingleton();
        }
        return instance;
    }

    // prevent cloning
    protected Object readResolve() {
        return instance;
    }
}
```

### <b>DOUBLE CHECKED LOCKING SINGLETON</b>

 <b>synchronized Blocks,</b>   Is used to reduce the overhead of <b>method-level synchronization,</b> but it does not <b>work prior to Java 5.</b> If multiple threads can access the singleton class simultaneously, it can cause issues as well. So <b>double checked locking</b> principle is used to solve this issue.
```java
public class DoubleCheckedLockingAlfrescoSingleton implements Serializable {
    private static DoubleCheckedLockingAlfrescoSingleton instance = null;

    //prevent instantiation from other classes
    private DoubleCheckedLockingAlfrescoSingleton() {
    }

    // a static method to get the instance
    public static DoubleCheckedLockingAlfrescoSingleton getInstance() {
        if (instance == null) {
            synchronized (DoubleCheckedLockingAlfrescoSingleton.class) {
                if (instance == null) {
                    instance = new DoubleCheckedLockingAlfrescoSingleton();
                }
            }
        }
        return instance;
    }

    // readResolve method to prevent creating new instance while deserializing
    //return the instance created during the class loading
    protected Object readResolve() {
        return instance;
    }
}
```
NOTICE: The above is best in multi-threaded environment but it can cause issues in <b>multi-core environment</b> as well. So it is recommended to use <b>Bill Pugh Singleton Implementation</b> for <b>Java</b> projects.

### <b>BILL PUGH SINGLETON</b>
Before Java 5, <b>java memory model</b> had a lot of issues and above approaches used to fail in certain scenarios where too many threads try to get the instance of the <b>Singleton class</b> simultaneously. So <b>Bill Pugh came</b> up with a different approach to create the Singleton class using a <b>static inner helper class.</b>
```java
public class BillPughAlfrescoSingleton implements Serializable {
    private BillPughAlfrescoSingleton() {
    }

    private static class SingletonHelper {
        private static final BillPughAlfrescoSingleton INSTANCE = new BillPughAlfrescoSingleton();
    }

    public static BillPughAlfrescoSingleton getInstance() {
        return SingletonHelper.INSTANCE;
    }

    // This is the key method which is responsible during serialization and deserialization
    // This method get invoked, and we are simple returning the already created instance
    protected Object readResolve() {
        return getInstance();
    }
}
```

## How To Break Singleton Design Pattern in Java?

* <b>Reflection</b> - Reflection can be used to break the singleton pattern by calling the private constructor using <b>Reflection API.</b>
* <b>Serialization</b> - Serialization can be used to break the singleton pattern by calling the <b>readResolve()</b> method.
* <b>Cloning</b> - Cloning can be used to break the singleton pattern by calling the <b>clone()</b> method.
### Serialization Break of Singleton

```java
import com.fas.fcdsystem.singletonDP.LazyAlfrescoSingleton;

import java.io.ObjectOutputStream;

public class SerializationBreakAlfrescoSingleton implements Serializable {
    LazyAlfrescoSingleton instance = LazyAlfrescoSingleton.getInstance();
    ObjectOutputStream oos= new ObjectOutputStream(new FileOutputStream("fas.obj"));
    oos.writeObject(instance);
    oos.close();
    
    // read object from file
    ObjectInputStream ois = new ObjectInputStream(new FileInputStream("fas.obj"));
    LazyAlfrescoSingleton instance1 = (LazyAlfrescoSingleton) ois.readObject();
    ois.close();
    
    System.out.println("instance hashCode="+instance.hashCode());
    System.out.println("instance1 hashCode="+instance1.hashCode());
}
```

### Output without readResolve() method
```java
instance hashCode=1163157884
instance1 hashCode=1956725890
```
### Output with readResolve() method
```java
instance hashCode=1163157884
instance1 hashCode=1163157884
```

### Reflection Break of Singleton

```java
import com.fas.fcdsystem.singletonDP.LazyAlfrescoSingleton;

import java.lang.reflect.Constructor;

public class ReflectionBreakAlfrescoSingleton {
    LazyAlfrescoSingleton instanceOne = LazyAlfrescoSingleton.getInstance();
    Constructor[] constructors = LazyAlfrescoSingleton.class.getDeclaredConstructors();
    for (Constructor constructor : constructors) {
        // Below code will destroy the singleton pattern
        constructor.setAccessible(true);
        instanceTwo = (LazyAlfrescoSingleton) constructor.newInstance();
        break;
    }
    System.out.println("instanceOne hashCode="+instanceOne.hashCode());
    System.out.println("instanceTwo hashCode="+instanceTwo.hashCode());
}
```

### Output without readResolve() method
```java
instanceOne hashCode=3125656
instanceTwo hashCode=2312424
```
### Output with readResolve() method
```java
instanceOne hashCode=2055036994
instanceTwo hashCode=2055036994
```

 
## How to prevent Singleton Design Pattern from being broken in Java?

* <b>Reflection</b> - To prevent singleton pattern from being broken by Reflection, we can throw an exception in the private constructor.
* <b>Serialization</b> - To prevent singleton pattern from being broken by Serialization, we can implement the <b>readResolve()</b> method.
* <b>Cloning</b> - To prevent singleton pattern from being broken by Cloning, we can throw an exception in the <b>clone()</b> method.
* <b>Enum</b> - Enum is the best way to create a singleton class as it is <b>thread-safe</b> and <b>prevents from Reflection API.</b>
 



### REFERENCES

* [stackoverflow-Java serialization](https://stackoverflow.com/questions/1168348/java-serialization-readobject-vs-readresolve)
* [Digital Ocean](https://www.digitalocean.com/community/tutorials/java-singleton-design-pattern-best-practices-examples)
* [Daily Code Buffer Youtube](https://www.youtube.com/watch?v=ASI0TfcY_7U&t=522s&ab_channel=DailyCodeBuffer)