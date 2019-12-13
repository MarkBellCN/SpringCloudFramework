package com.hollysys.platform.auth.server.exception;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class BootOauthExceptionSerializer extends StdSerializer<BootOauthException> {
    public BootOauthExceptionSerializer() {
        super(BootOauthException.class);
    }

    @Override
    public void serialize(BootOauthException value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeObject(value.getResult());
    }
}