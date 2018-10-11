package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.FakeMealModel;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.model.Model;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.Util;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    private static final Model model = FakeMealModel.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<MealWithExceed> meals = MealsUtil.getFilteredWithExceeded(model.getList(), LocalTime.MIN, LocalTime.MAX, 2000);
        req.setAttribute("meals", meals);

        log.debug("Forwarding doGet request");
        req.getRequestDispatcher("/meals.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF8");

        if (req.getParameter("deleteId") != null) {
            int id = Integer.parseInt(req.getParameter("deleteId"));
            model.getList().forEach(meal -> {
                if (meal.getId() == id) model.delete(model.getList().indexOf(meal));
                log.info(String.format("Meal deleted ID: %d", id));
                return;
            });
            doGet(req, resp);
        }

        if (req.getParameter("updateId") != null) {
            int id = Integer.parseInt(req.getParameter("updateId"));
            model.getList().forEach(meal -> {
                if (meal.getId() == id) {
                    req.setAttribute("mealToUpdate", meal);
                }
            });
            doGet(req, resp);
        }

        if (!Util.isRequestValid(req)) {
            doGet(req, resp);
        }

        final String description = req.getParameter("description");
        final int calories = Integer.parseInt(req.getParameter("calories"));
        final LocalDateTime dateTime = Util.parseDateTime(req.getParameter("datetime"));

        if (req.getParameter("idToUpdate") != null) {
            int id = Integer.parseInt(req.getParameter("idToUpdate"));
            model.update(id, dateTime, description, calories);
            log.info("Meal updated ID: %s", id);
            doGet(req, resp);

        } else {
            model.add(dateTime, description, calories);
            log.info("New meal added");
            doGet(req, resp);
        }

        req.getRequestDispatcher("meals.jsp").forward(req, resp);
    }


}
