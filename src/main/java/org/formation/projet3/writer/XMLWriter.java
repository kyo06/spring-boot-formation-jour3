package org.formation.projet3.writer;

import org.springframework.stereotype.Component;

@Component("xmlwriter")
public class XMLWriter implements IWriter {
    @Override
    public void write(Object object) {
        System.out.println("<object>" + object.toString() + "</object>");
    }
}
