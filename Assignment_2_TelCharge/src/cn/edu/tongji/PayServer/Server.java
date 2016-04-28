package cn.edu.tongji.PayServer;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by wangdechang on 2016/4/28.
 */
public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(8888);
            System.out.println("启动服务器....");
            Socket s = ss.accept();
            System.out.println("客户端:" + s.getInetAddress().getLocalHost() + "已连接到服务器");

            BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            //读取客户端发送来的消息
            String mess = br.readLine();
            String[] strArr = mess.split(",");
            for (int i = 0; i < 3; i++) {
                System.out.println(strArr[i]);
            }

//            System.out.println("客户端："+mess+strArr[1]);
            double cost = Double.parseDouble(strArr[0]);
            double payCount = Double.parseDouble(strArr[1]);
            String password = strArr[2];
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
            String messRes = "支付结果";
            if (payCount < 1000 && password.equals("123456")) {
                double balance = payCount - cost;
                messRes = "支付成功 " + "支付金额为：" + strArr[1] + "当前账户余额为：" + balance + "\n";
            } else {
                messRes = "支付失败" + "\n";
            }
            bw.write(messRes);

            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
