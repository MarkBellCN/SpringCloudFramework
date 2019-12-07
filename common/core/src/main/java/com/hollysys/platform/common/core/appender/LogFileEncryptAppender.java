package com.hollysys.platform.common.core.appender;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.rolling.RollingFileAppender;
import ch.qos.logback.core.spi.DeferredProcessingAware;
import ch.qos.logback.core.status.ErrorStatus;

import java.io.IOException;

public class LogFileEncryptAppender extends RollingFileAppender<ILoggingEvent> {

    @Override
    protected void subAppend(ILoggingEvent event){
        if (this.isStarted()) {
            try {
                if (event instanceof DeferredProcessingAware) {
                    ((DeferredProcessingAware)event).prepareForDeferredProcessing();
                }

                byte[] byteArray = this.encoder.encode(event);
                // TODO 后期进行加密处理
                this.writeBytes(byteArray);
            } catch (Exception var3) {
                this.started = false;
                this.addStatus(new ErrorStatus("IO failure in appender", this, var3));
            }

        }
    }

    private void writeBytes(byte[] byteArray) throws IOException {
        if (byteArray != null && byteArray.length != 0) {
            this.lock.lock();
            try {
                this.getOutputStream().write(byteArray);
                if (this.isImmediateFlush()) {
                    this.getOutputStream().flush();
                }
            } finally {
                this.lock.unlock();
            }
        }
    }
}
