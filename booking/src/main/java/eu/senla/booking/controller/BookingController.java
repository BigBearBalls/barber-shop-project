package eu.senla.booking.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/booking/")
public class BookingController {

    // получает UUID мастера и возвращает список свободного времени для этого мастера
    public void getMasterFreeTime() {

    }

    // получает конкретный день возвращает список свободного времени на этот день
    public void getDateFreeTime() {

    }

    //Создаем бронь. получает id услуги (и мастера) и дату для записи. Возвращает id брони
    public void bookTime() {

    }

    // удаляет бронь. Получает id, возвращает статус.
    public void removeBook() {

    }

    // принимает id брони с новыми исправлениями. Возвращает статус (или измененную бронь)
    public void changeBookDate() {

    }

    //принимает UUID пользователя и возвращает список его бронирований
    public void showUsersBooks() {

    }

    //Получает id брони и возвращает всю информацию по ней
    public void showBookInfoById() {

    }

}
