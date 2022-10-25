package jm.task.core.jdbc.service;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class SystemInWrapper extends FilterInputStream {
    SystemInWrapper(InputStream inputStream) {
        super(inputStream);
    }

    @Override
    public void close() throws IOException {
    }
}
