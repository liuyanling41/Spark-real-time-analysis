
package linux;

import java.io.UnsupportedEncodingException;
import java.util.Random;
import java.util.UUID;

/**
 * 这个类是需要改写的，Flume和 Hbase整合的类，由于缺少很多依赖包，所以我注释掉。自行复制到Flume源码对应文件上。
 */
public class SimpleRowKeyGenerator {

  public static byte[] getUUIDKey(String prefix) throws UnsupportedEncodingException {
    return (prefix + UUID.randomUUID().toString()).getBytes("UTF8");
  }

  public static byte[] getRandomKey(String prefix) throws UnsupportedEncodingException {
    return (prefix + String.valueOf(new Random().nextLong())).getBytes("UTF8");
  }

  public static byte[] getTimestampKey(String prefix) throws UnsupportedEncodingException {
    return (prefix + String.valueOf(System.currentTimeMillis())).getBytes("UTF8");
  }

  public static byte[] getNanoTimestampKey(String prefix) throws UnsupportedEncodingException {
    return (prefix + String.valueOf(System.nanoTime())).getBytes("UTF8");
  }
  // 定义Hbase的RowKey
  public static byte[] getFlumeKey(String userid,String datetime) throws UnsupportedEncodingException {
    return (userid + datetime + String.valueOf(System.currentTimeMillis())).getBytes("UTF8");
  }

}
