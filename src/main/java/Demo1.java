import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

/**
 * ----|InputStream
 * --------|FileInputStream
 * --------|BufferedInputStream
 * ----|OutputStream
 * --------|FileOutputStream
 * --------|BufferedOutputStream
 * <p>
 * ----|writer
 * --------/FileWriter
 * --------/BufferedWriter
 * ----|Reader 所有输入字符流的基类
 * --------|FileReader 读取文件字符的输入字符流。
 * --------|BufferedReader 缓冲输入字符流。该类出现的目的是为提高读取文件字符的效率并且拓展功能（readLine()），内部维护了一个8192字符数组。
 * 输入字节流的转换流：输入字节流--------输入字符流
 * InputStreamReader
 * 输出字节流的转换流：输入字符流--------输入字节流
 * OutputStreamWriter
 * 转换流的作用：
 * 1.可以把对应的字节流转换成字符流使用。
 * 2.可以指定码表进行读写文件的数据。
 * <p>
 * <p>
 * <p>
 * FileReader,FileWriter这两个默认使用的是gbk编码表。
 */
public class Demo1 {
    public static void main(String[] args) throws Exception {
//        testInput();
//        writeFile();
        readFile();
    }


    public static void readFile() throws IOException {
        //建立文件与程序的输入数据通道
        FileInputStream inputStream = new FileInputStream("E://a.txt");
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        int content = 0;
        while ((content = inputStreamReader.read()) != -1) {
            System.out.println((char) content);
        }
        inputStreamReader.close();

    }


    //指定使用utf-8码表把数据写出到文件上
    public static void writeFile() throws IOException {
        //建立文件与程序的数据通道
        FileOutputStream fileOutputStream = new FileOutputStream("E://a.txt");
        //创建一个输出字节流的转换流并且指定码表进行写数据
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, "utf-8");
        outputStreamWriter.write("大家好！");//中文在utf-8码表中占三个字节。
        //关闭资源
        outputStreamWriter.close();
    }


    public static void testOutPut() throws IOException {
        Socket socket = new Socket(InetAddress.getLocalHost(), 9090);
        OutputStream outputStream = socket.getOutputStream();
//        outputStream.write("你饿吗？".getBytes());
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
        outputStreamWriter.write("不饿！");
    }

    public static void testInput() throws Exception {

        InputStream inputStream = System.in;

        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        System.out.println(bufferedReader.readLine());
    }
}
