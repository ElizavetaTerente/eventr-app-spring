package at.qe.skeleton.ui.beans;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Scope;

import at.qe.skeleton.model.OpeningHour;

@Component
@Scope("request")
public class TimeFilterBean implements Serializable {

    private LocalDateTime start;
    private LocalTime end;

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalTime getEnd() {
        return end;
    }

    public void setEnd(LocalTime end) {
        this.end = end;
    }

    public boolean filterOpenTime(Object value, Object filter, Locale locale) {

        if (start == null) {
            return true;
        }

        List<OpeningHour> openingHour_list = (List<OpeningHour>) value;

        switch (start.getDayOfWeek()) {
            case MONDAY:
                if (start.toLocalTime().compareTo(openingHour_list.get(0).getOpen()) <= 0
                        || start.toLocalTime().compareTo(openingHour_list.get(0).getClose()) >= 0) {
                    return false;
                }
            case TUESDAY:
                if (start.toLocalTime().compareTo(openingHour_list.get(1).getOpen()) <= 0
                        || start.toLocalTime().compareTo(openingHour_list.get(1).getClose()) >= 0) {
                    return false;
                }
            case WEDNESDAY:
                if (start.toLocalTime().compareTo(openingHour_list.get(2).getOpen()) <= 0
                        || start.toLocalTime().compareTo(openingHour_list.get(2).getClose()) >= 0) {
                    return false;
                }
            case THURSDAY:
                if (start.toLocalTime().compareTo(openingHour_list.get(3).getOpen()) <= 0
                        || start.toLocalTime().compareTo(openingHour_list.get(3).getClose()) >= 0) {
                    return false;
                }
            case FRIDAY:
                if (start.toLocalTime().compareTo(openingHour_list.get(4).getOpen()) <= 0
                        || start.toLocalTime().compareTo(openingHour_list.get(4).getClose()) >= 0) {
                    return false;
                }
            case SATURDAY:
                if (start.toLocalTime().compareTo(openingHour_list.get(5).getOpen()) <= 0
                        || start.toLocalTime().compareTo(openingHour_list.get(5).getClose()) >= 0) {
                    return false;
                }
            case SUNDAY:
                if (start.toLocalTime().compareTo(openingHour_list.get(6).getOpen()) <= 0
                        || start.toLocalTime().compareTo(openingHour_list.get(6).getClose()) >= 0) {
                    return false;
                }
        }

        return true;

    }

    public boolean filterCloseTime(Object value, Object filter, Locale locale) {

        if (start == null || end == null) {
            return true;
        }

        List<OpeningHour> openingHour_list = (List<OpeningHour>) value;

        switch (start.getDayOfWeek()) {
            case MONDAY:
                if (end.compareTo(openingHour_list.get(0).getClose()) >= 0) {
                    return false;
                }
            case TUESDAY:
                if (end.compareTo(openingHour_list.get(0).getClose()) >= 0) {
                    return false;
                }
            case WEDNESDAY:
                if (end.compareTo(openingHour_list.get(0).getClose()) >= 0) {
                    return false;
                }
            case THURSDAY:
                if (end.compareTo(openingHour_list.get(0).getClose()) >= 0) {
                    return false;
                }
            case FRIDAY:
                if (end.compareTo(openingHour_list.get(0).getClose()) >= 0) {
                    return false;
                }
            case SATURDAY:
                if (end.compareTo(openingHour_list.get(0).getClose()) >= 0) {
                    return false;
                }
            case SUNDAY:
                if (end.compareTo(openingHour_list.get(0).getClose()) >= 0) {
                    return false;
                }
        }

        return true;

    }

}
