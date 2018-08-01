package server;

import io.netty.channel.socket.SocketChannel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ChannelMap {
    private static Map<String, SocketChannel> channelMap = new ConcurrentHashMap<>();

    public static void add(String clientId,SocketChannel socketChannel){
        channelMap.put(clientId,socketChannel);
    }

    public static SocketChannel get(String clientId){
        return channelMap.get(clientId);
    }

    public static boolean remove(String clientId) {
        return channelMap.remove(clientId) == null;
    }

    public static void remove(SocketChannel socketChannel){
        for (Map.Entry entry : channelMap.entrySet()){

            if (entry.getValue() == socketChannel){
                channelMap.remove(entry.getKey());
            }

        }
    }

}
