package cn.pconline.pcloud.admin.interceptor;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import cn.pconline.pcloud.base.util.HttpUtil;

/**
 * Request包装类(重新读取body数据流)
 * @author jzc
 *
 */
public class PostBodyRequestWrapper extends HttpServletRequestWrapper {
	private final byte[] body;
	
	private String bodyString;
	
	public String getBodyString(){
		return this.bodyString;
	}

    public PostBodyRequestWrapper(HttpServletRequest request) throws IOException {
    	super(request);
        this.bodyString = HttpUtil.getRequestBody(request);
        this.body = this.bodyString.getBytes(Charset.forName("UTF-8"));
    }
       
    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }
    
    @Override
    public ServletInputStream getInputStream() throws IOException {
        final ByteArrayInputStream bais = new ByteArrayInputStream(this.body);
        
        return new ServletInputStream() {
            @Override
            public int read() throws IOException {
                return bais.read(); // 读取以前读到的数据，因request只能被读取一次数据
            }

            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {
            }
        };
    }
}
