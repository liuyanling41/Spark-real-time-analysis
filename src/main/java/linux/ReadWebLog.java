package linux;
import java.io.*;
/**
 * Created by liuyanling on 2018/3/18
 * 需要打包到linux服务器上
 */
public class ReadWebLog {

    private static String readFileName;
    private static String writeFileName;

    public static void main(String args[]) {
        readFileName = args[0];
        writeFileName = args[1];
        readFile(readFileName);

    }

    public static void readFile(String fileName) {

        try {
            FileInputStream fis = new FileInputStream(fileName);
            InputStreamReader isr = new InputStreamReader(fis, "GBK");
            //以上两步已经可以从文件中读取到一个字符了，但每次只读取一个字符不能满足大数据的需求。故需使用BufferedReader，它具有缓冲的作用，可以一次读取多个字符
            BufferedReader br = new BufferedReader(isr);
            int count = 0;
            while (br.readLine() != null) {
                String line = br.readLine();
                count++;
                // 显示行号
                Thread.sleep(300);
                String str = new String(line.getBytes("UTF8"), "GBK");
                System.out.println("row:" + count + ">>>>>>>>" + line);
                writeFile(writeFileName, line);
            }
            isr.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void writeFile(String fileName, String conent) {
        try {
            FileOutputStream fos = new FileOutputStream(fileName, true);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            BufferedWriter bw = new BufferedWriter(osw);
            bw.write("\n");
            bw.write(conent);
            bw.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
