package org.formation.projet3.writer_example_spring;

import org.springframework.stereotype.Component;

@Component
public interface IWriter {
    void write(Object object);
}
