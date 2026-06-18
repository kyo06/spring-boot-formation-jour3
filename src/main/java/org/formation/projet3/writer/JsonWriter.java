package org.formation.projet3.writer;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class JsonWriter implements IWriter {
    @Override
    public void write(Object object) {
        System.out.println("{ value: \"" + object.toString() + "\" }");
    }
}
