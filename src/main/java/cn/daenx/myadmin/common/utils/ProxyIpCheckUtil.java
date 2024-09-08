package cn.daenx.myadmin.common.utils;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Base64;
import java.util.Date;

/**
 * 代理IP检测工具
 *
 * @author DaenMax
 */
public class ProxyIpCheckUtil {

    public static void main(String[] args) {
//        System.out.println(testSpeed("http", "127.0.0.1", 7890, null, null));
        System.out.println(new Date());
        System.out.println(testSpeed("http", "test.daez.cn", 5055, null, null));
        System.out.println(new Date());
//        System.out.println(testSpeed("socks5", "http.dfjfeuylys.com", 10008, "ZCDP999_area-FR", "ZCDP999"));
//        System.out.println(testSpeed("http", "proxy.packetstream.io", 31112, "sky520", "s7OFn9KuiXY2ITKy_country-UnitedKingdom_session-uJJkZc6k"));
    }

    /**
     * 测试代理IP延时以及连通性
     *
     * @param type 代理类型，http/socks5
     * @param host IP
     * @param port 端口
     * @param user 账号
     * @param pass 密码
     * @return 延时，如果无法连接则返回失败原因
     */
    public static String testSpeed(String type, String host, int port, String user, String pass) {
        if ("http".equals(type)) {
            return testSpeedHttp(host, port, user, pass);
        } else {
            return testSpeedSocks5(host, port, user, pass);
        }
    }

    public static String testSpeedHttp(String proxyHost, int proxyPort, String username, String password) {

        try {
            long startTime = System.currentTimeMillis();
            // 创建套接字
            Socket socket = new Socket();

            // 创建代理地址
            InetSocketAddress proxyAddr = new InetSocketAddress(proxyHost, proxyPort);

            // 连接代理服务器
            socket.connect(proxyAddr);

            // 设置超时时间为10秒
            int timeoutMillis = 10000;
            socket.setSoTimeout(timeoutMillis);
            // 获取套接字输出流
            OutputStream outputStream = socket.getOutputStream();

            // 发送一个带有身份验证信息的HTTP请求头
            String authHeader = createAuthHeader(username, password);
            String httpRequest = "GET / HTTP/1.1\r\nHost: www.example.com\r\nProxy-Authorization: " + authHeader + "\r\n\r\n";
            outputStream.write(httpRequest.getBytes());
            outputStream.flush();

            // 获取套接字输入流
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String response = bufferedReader.readLine();

            // 判断代理是否需要身份验证
            if (response != null && response.contains("407 Proxy Authentication Required")) {
                throw new IOException("代理需要身份验证，但提供的用户名和密码不正确");
            }

            // 关闭套接字
            socket.close();
            long endTime = System.currentTimeMillis();
            return (endTime - startTime) + "ms";
        } catch (SocketTimeoutException e) {
            return "超时";
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    private static String createAuthHeader(String username, String password) {
        String credentials = username + ":" + password;
        String encodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes());
        return "Basic " + encodedCredentials;
    }

    public static String testSpeedSocks5(String proxyHost, int proxyPort, String username, String password) {
        try {
            long startTime = System.currentTimeMillis();
            // 创建套接字
            Socket socket = new Socket();

            // 创建代理地址
            InetSocketAddress proxyAddr = new InetSocketAddress(proxyHost, proxyPort);

            // 连接代理服务器
            socket.connect(proxyAddr);

            // 设置超时时间为10秒
            int timeoutMillis = 10000;
            socket.setSoTimeout(timeoutMillis);

            // 进行 SOCKS5 协议身份验证
            performSocks5Authentication(socket.getOutputStream(), socket.getInputStream(), username, password);

            // 如果没有抛出异常，则代理身份验证成功

            // 关闭套接字
            socket.close();
            long endTime = System.currentTimeMillis();
            return (endTime - startTime) + "ms";
        } catch (SocketTimeoutException e) {
            return "超时";
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    private static void performSocks5Authentication(OutputStream outputStream, InputStream inputStream, String username, String password) throws IOException {
        // 发送 SOCKS5 协议版本和支持的身份验证方法
        byte[] socks5Init = {5, 2, 0, 2}; // SOCKS5, 两种身份验证方法
        outputStream.write(socks5Init);
        outputStream.flush();

        // 读取代理服务器的响应，验证是否支持用户名密码身份验证
        byte[] response = new byte[2];
        inputStream.read(response);

        if (response[0] != 5 || response[1] != 2) {
            throw new IOException("代理服务器不支持用户名密码身份验证");
        }

        // 发送用户名密码进行身份验证
        byte[] usernameBytes = username.getBytes();
        byte[] passwordBytes = password.getBytes();
        byte[] authRequest = new byte[3 + usernameBytes.length + passwordBytes.length];
        authRequest[0] = 1; // 用户名密码版本
        authRequest[1] = (byte) usernameBytes.length; // 用户名长度
        System.arraycopy(usernameBytes, 0, authRequest, 2, usernameBytes.length); // 用户名
        authRequest[2 + usernameBytes.length] = (byte) passwordBytes.length; // 密码长度
        System.arraycopy(passwordBytes, 0, authRequest, 3 + usernameBytes.length, passwordBytes.length); // 密码

        outputStream.write(authRequest);
        outputStream.flush();

        // 读取代理服务器的身份验证响应
        byte[] authResponse = new byte[2];
        inputStream.read(authResponse);

        // 判断身份验证是否成功
        if (authResponse[0] != 1 || authResponse[1] != 0) {
            throw new IOException("用户名密码身份验证失败");
        }
    }
}
