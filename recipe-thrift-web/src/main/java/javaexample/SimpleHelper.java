package javaexample;

import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.handler.codec.http.DefaultHttpResponse;
import org.jboss.netty.handler.codec.http.HttpResponse;
import org.jboss.netty.handler.codec.http.HttpResponseStatus;
import org.jboss.netty.handler.codec.http.HttpVersion;

import java.util.List;

public class SimpleHelper {
    public static HttpResponse makeTextResponse(String content) {
        HttpResponse response = new DefaultHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);
        response.setContent(ChannelBuffers.wrappedBuffer(content.getBytes()));
        return response;
    }

    public static String implode(List<String> stringList){
        String imploded = "";
        for(String string: stringList){
            imploded += string+" ";
        }
        return imploded;
    }
}
