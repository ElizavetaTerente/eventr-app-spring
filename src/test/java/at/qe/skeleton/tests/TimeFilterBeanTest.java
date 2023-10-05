package at.qe.skeleton.tests;

import at.qe.skeleton.model.Location;
import at.qe.skeleton.model.OpeningHour;
import at.qe.skeleton.model.Weekday;
import at.qe.skeleton.services.LocationService;
import at.qe.skeleton.services.UserService;
import at.qe.skeleton.services.VotingService;
import at.qe.skeleton.ui.beans.TimeFilterBean;
import at.qe.skeleton.ui.controllers.LocationDetailController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Transactional
@SpringBootTest
@WebAppConfiguration

public class TimeFilterBeanTest {

    @Autowired
    TimeFilterBean timeFilterBean;

    @Autowired
    LocationService locationService;


    @Test
    @WithMockUser(username = "admin", authorities = {"ADMIN"})
    public void datainitializationTest() {
        TimeFilterBean timeFilterBean = new TimeFilterBean();
        LocalDateTime start = LocalDateTime.of(2022, 01, 31,07,00);
        LocalTime end = LocalTime.of(22,00);

        timeFilterBean.setEnd(end);
        Assertions.assertEquals(end,timeFilterBean.getEnd());

        Locale austria = new Locale("de", "AT");

        OpeningHour openingHour = new OpeningHour();
        openingHour.setOpened(true);
        openingHour.setWeekday(Weekday.MO);
        LocalTime open = LocalTime.of( 9, 00);
        LocalTime close = LocalTime.of( 21, 00);
        openingHour.setOpen(open);
        openingHour.setClose(close);

        List list1 = new ArrayList<>();
        list1.add(openingHour);
        list1.add(openingHour);
        list1.add(openingHour);
        list1.add(openingHour);
        list1.add(openingHour);
        list1.add(openingHour);
        list1.add(openingHour);

        timeFilterBean.setStart(null);

        Assertions.assertTrue(timeFilterBean.filterOpenTime(list1,null,austria));
        Assertions.assertTrue(timeFilterBean.filterCloseTime(list1,null,austria));

        timeFilterBean.setStart(start);
        Assertions.assertEquals(start,timeFilterBean.getStart());

        Assertions.assertFalse(timeFilterBean.filterOpenTime(list1,null,austria));
        Assertions.assertFalse(timeFilterBean.filterCloseTime(list1,null,austria));

        //

        start = LocalDateTime.of(2022, 01, 31,07,00);
        timeFilterBean.setStart(start);
        Assertions.assertFalse(timeFilterBean.filterOpenTime(list1,null,austria));
        Assertions.assertFalse(timeFilterBean.filterCloseTime(list1,null,austria));

        start = LocalDateTime.of(2022, 02, 01,07,00);
        timeFilterBean.setStart(start);
        Assertions.assertFalse(timeFilterBean.filterOpenTime(list1,null,austria));
        Assertions.assertFalse(timeFilterBean.filterCloseTime(list1,null,austria));

        start = LocalDateTime.of(2022, 02, 02,07,00);
        timeFilterBean.setStart(start);
        Assertions.assertFalse(timeFilterBean.filterOpenTime(list1,null,austria));
        Assertions.assertFalse(timeFilterBean.filterCloseTime(list1,null,austria));

        start = LocalDateTime.of(2022, 02, 03,07,00);
        timeFilterBean.setStart(start);
        Assertions.assertFalse(timeFilterBean.filterOpenTime(list1,null,austria));
        Assertions.assertFalse(timeFilterBean.filterCloseTime(list1,null,austria));

        start = LocalDateTime.of(2022, 02, 04,07,00);
        timeFilterBean.setStart(start);
        Assertions.assertFalse(timeFilterBean.filterOpenTime(list1,null,austria));
        Assertions.assertFalse(timeFilterBean.filterCloseTime(list1,null,austria));

        start = LocalDateTime.of(2022, 02, 05,07,00);
        timeFilterBean.setStart(start);
        Assertions.assertFalse(timeFilterBean.filterOpenTime(list1,null,austria));
        Assertions.assertFalse(timeFilterBean.filterCloseTime(list1,null,austria));

        start = LocalDateTime.of(2022, 02, 06,07,00);
        timeFilterBean.setStart(start);
        Assertions.assertFalse(timeFilterBean.filterOpenTime(list1,null,austria));
        Assertions.assertFalse(timeFilterBean.filterCloseTime(list1,null,austria));

        //

        start = LocalDateTime.of(2022, 01, 31,14,00);
        end = LocalTime.of(20,00);
        timeFilterBean.setStart(start);
        timeFilterBean.setEnd(end);

        Assertions.assertTrue(timeFilterBean.filterOpenTime(list1,null,austria));
        Assertions.assertTrue(timeFilterBean.filterCloseTime(list1,null,austria));














    }
}
