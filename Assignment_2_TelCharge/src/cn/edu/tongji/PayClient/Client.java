package cn.edu.tongji.PayClient;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by wangdechang on 2016/4/28.
 */
public class Client {
    public Client(){}
    public static void start(double cost){
        Scanner in = new Scanner(System.in);
        System.out.print("请输入充值金额：");
        double payNumber = in.nextDouble();
        System.out.print("请输入密码：");
        Scanner input = new Scanner(System.in);
        String password = input.nextLine();

        Socket s = null;
        try {
            s = new Socket("127.0.0.1",8888);
            InputStream is = s.getInputStream();
            OutputStream os = s.getOutputStream();

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
            //向服务器端发送一条消息
            bw.write(cost +","+payNumber+","+password+"\n");  //向服务器端发送一条消息
            bw.flush();

            //读取服务器返回的消息
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String mess = br.readLine();
            System.out.println("服务器："+mess);

        } catch (IOException e) {
            System.out.println("支付失败");
        }

    }
}
