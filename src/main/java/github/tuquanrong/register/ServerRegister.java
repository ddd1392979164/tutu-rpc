package github.tuquanrong.register;

import github.tuquanrong.transport.NettyServer;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

/**
 * tutu
 * 2021/1/19
 */
public class ServerRegister {
    private static final ServerRegister SERVER_REGISTER = new ServerRegister();
    private ZkController zkController;

    ServerRegister(){
        zkController=ZkController.getInstance();
    }

    public static ServerRegister getInstance() {
        return SERVER_REGISTER;
    }

    public void registerServer(String className) {
        try {
            String ip = InetAddress.getLocalHost().getHostAddress();
            int port = NettyServer.PORT;
            zkController.createNode(ZkController.ZK_RPC_LINK + "/" + className + new InetSocketAddress(ip, port).toString());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
