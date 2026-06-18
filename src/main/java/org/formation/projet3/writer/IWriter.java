package org.formation.projet3.writer;

import org.springframework.stereotype.Component;

@Component
public interface IWriter {
    void write(Object object);
}
