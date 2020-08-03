package org.ganjp.api.core.web.request;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;

public class ClonedBodyRequestWrapper extends HttpServletRequestWrapper {

    private byte[] body;

    public ClonedBodyRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(getBody());
        return new ServletInputStream() {
            @Override
            public boolean isFinished() {
                return true;
            }

            @Override
            public boolean isReady() {
                return true;
            }

            @Override
            public void setReadListener(ReadListener listener) {
            }

            public int read() throws IOException {
                return byteArrayInputStream.read();
            }
        };
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    private byte[] getBody() throws IOException {
        if(body == null) {
            readBody();
        }
        return body;
    }

    private void readBody() throws IOException {
        HttpServletRequest request = (HttpServletRequest) this.getRequest();
        InputStream inputStream = request.getInputStream();
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        int bytesRead;
        byte[] byteBuffer = new byte[4096];
        while ((bytesRead = inputStream.read(byteBuffer)) > 0) {
            byteStream.write(byteBuffer, 0, bytesRead);
        }
        body = byteStream.toByteArray();
    }

}
