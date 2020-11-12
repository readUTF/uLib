package com.readutf.uLib.libraries;

import org.bson.Document;

public class DocumentBuilder {

    Document document;

    public DocumentBuilder(String key, String value) {
        document = new Document(key, value);
    }

    public DocumentBuilder set(String key, Object object) {
        if(object == null) {
            document.put(key, null);
        }
        document.put(key, object);
        return this;
    }

    public Document build() {
        return document;
    }
}
