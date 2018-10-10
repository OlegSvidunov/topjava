package ru.javawebinar.topjava.model;

import java.time.LocalDateTime;
import java.util.List;

public interface Model {

    List<Meal> getList();

    void add(LocalDateTime dateTime, String description, int calories);

    void delete(int index);

    void update(int id, LocalDateTime dateTime, String description, int calories);

}
