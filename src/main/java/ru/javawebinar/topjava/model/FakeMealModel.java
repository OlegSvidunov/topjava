package ru.javawebinar.topjava.model;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class FakeMealModel implements Model {
    private static List<Meal> meals = new CopyOnWriteArrayList<>();
    private static AtomicInteger counter = new AtomicInteger(0);

    private static class SingletonHolder {
        private static final FakeMealModel instance = new FakeMealModel();
    }

    public static FakeMealModel getInstance() {
        return SingletonHolder.instance;
    }

    public static int getId() {
        return counter.getAndIncrement();
    }

    private FakeMealModel() {
        //adding to list some test data
        meals.add(new Meal(getId(), LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500));
        meals.add(new Meal(getId(), LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000));
        meals.add(new Meal(getId(), LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500));
        meals.add(new Meal(getId(), LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000));
        meals.add(new Meal(getId(), LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500));
        meals.add(new Meal(getId(), LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510));
    }

    @Override
    public List<Meal> getList() {
        return meals;
    }

    @Override
    public void add(LocalDateTime dateTime, String description, int calories) {
        meals.add(new Meal(getId(), dateTime, description, calories));
    }

    @Override
    public void delete(int index) {
        meals.remove(index);
    }

    @Override
    public void update(int id, LocalDateTime dateTime, String description, int calories) {
        Meal mealToUpdate = null;
        for (Meal m : meals) {
            if (m.getId() == id) {
                mealToUpdate = m;
                break;
            }
        }
        meals.set(meals.indexOf(mealToUpdate), new Meal(id, dateTime, description, calories));
    }
}
