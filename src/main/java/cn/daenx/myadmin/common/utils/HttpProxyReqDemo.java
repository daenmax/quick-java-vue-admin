package cn.daenx.myadmin.common.utils;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;

import java.net.*;

/**
 * HTTP请求使用http、socks代理demo，包含有认证和无认证
 *
 * @author DaenMax
 */
public class HttpProxyReqDemo {
    public static void main(String[] args) {
        //Hutool普通请求
        HutoolReq();
        //Hutool使用无认证的代理
        HutoolReqProxy();
        //Hutool使用有认证的代理
        HutoolReqProxyAuth();
        //Java.net使用有认证的代理
        JavaNetReqProxyAuth();
    }

    /**
     * Hutool普通请求
     */
    public static void HutoolReq() {
        HttpRequest request = HttpUtil.createGet("https://www.baidu.com/");
        String body = request.execute().body();
        System.out.println(body);
    }

    /**
     * Hutool使用无认证的代理
     */
    public static void HutoolReqProxy() {
        HttpRequest request = HttpUtil.createGet("https://www.baidu.com/");
        request = request.setProxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 7890)));
//        request = request.setProxy(new Proxy(Proxy.Type.SOCKS, new InetSocketAddress("mylisg.fun", 36160)));
        String body = request.execute().body();
        System.out.println(body);
    }

    /**
     * Hutool使用有认证的代理
     */
    public static void HutoolReqProxyAuth() {
        Authenticator.setDefault(new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("CRsKrsNzJ0", "5tBgyWKrgy".toCharArray());
            }
        });
        HttpRequest request = HttpUtil.createGet("https://www.baidu.com/");
        request = request.setProxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 7890)));
//        request = request.setProxy(new Proxy(Proxy.Type.SOCKS, new InetSocketAddress("mylisg.fun", 36160)));
        String body = request.execute().body();
        System.out.println(body);
    }

    /**
     * Java.net使用有认证的代理
     *
     * @throws Exception
     */
    public static void JavaNetReqProxyAuth() {
        Authenticator.setDefault(new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("CRsKrsNzJ0", "5tBgyWKrgy".toCharArray());
            }
        });
        try {
            // 设置代理服务器地址和端口
//            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 7890));
            Proxy proxy = new Proxy(Proxy.Type.SOCKS, new InetSocketAddress("mylisg.fun", 36160));
            // 创建URL对象
            URL url = new URL("https://www.baidu.com/");
            // 打开连接
            HttpURLConnection connection = (HttpURLConnection) url.openConnection(proxy);
            connection.setRequestMethod("GET");
            connection.connect();
            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
