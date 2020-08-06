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

#### 0x03 Send the request with PoC, when users login again, there is Remote Code Execution.

<img src="https://github.com/pyn3rd/Apache-Tomcat-MongoDB-Remote-Code-Execution/blob/master/tomcat-mongo.gif">

#### 0x04 start Tomcat Server, when users login the website, their login sessions will be stored in MongoDB Server, as you know, there are so many unauthorized MongoDB Servers on the Internet, just search them by Shodan :)
<img src="https://github.com/pyn3rd/Apache-Tomcat-MongoDB-Remote-Code-Execution/blob/master/shodan.png">

