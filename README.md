# Apache-Tomcat-Redis-Remote-Code-Execution

#### 0x01 Add the following JARs to /lib directory.

```
commons-pool2-2.2.jar
jedis-3.0.0.jar
tomcat8.5-redis-session-manager.jar
```

#### 0x02 Modify the configuration file, `` conf/context.xml `` then start Tomcat Server and MongoDB Server.

```
<Valve className="com.s.tomcat.redissessions.RedisSessionHandlerValve"/> 
    <Manager className="com.s.tomcat.redissessions.RedisSessionManager" 
      host="127.0.0.1"
      port="6379"
      database="0"
      maxInactiveInterval="60" /> 
```

#### 0x03 Writing the Java Serialization Gadget into Redis
<img src="https://github.com/pyn3rd/Apache-Tomcat-Redis-Remote-Code-Execution/blob/master/redis-rce2.png">


#### 0x04 When users login again, there is Remote Code Execution.

<img src="https://github.com/pyn3rd/Apache-Tomcat-Redis-Remote-Code-Execution/blob/master/redis-rce1.png">



