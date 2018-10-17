package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;

import java.util.Collection;

public class MealServiceImpl implements MealService {

    private MealRepository repository;

    @Override
    public Meal create(Meal meal, int userId) {
        return repository.save(meal, userId);
    }

    @Override
    public boolean delete(int id, int userId) {
        return repository.delete(id, userId);
    }

    @Override
    public Meal get(int id, int userId) {
        return repository.get(id, userId);
    }

    @Override
    public void update(Meal meal, int userId) {
        repository.save(meal, userId);
    }

    @Override
    public Collection<Meal> getAllMealsForUser(int userId) {
        return repository.getAll(userId);
    }
}