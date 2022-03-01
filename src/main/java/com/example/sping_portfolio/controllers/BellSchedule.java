package com.example.sping_portfolio.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.DateTime;
import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.PeriodList;
import net.fortuna.ical4j.model.Period;

import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BellSchedule {
    @GetMapping("/bellschedule")
    public String Schedule() {
        return "bellschedule";
    }

    @GetMapping("/get-schedule")
    @ResponseBody
    public String GetSchedule(String schedule) throws IOException, ParserException, ParseException {
        File file = ResourceUtils.getFile("classpath:static/schedules/ics/dnhs.ics");
        FileInputStream fin = new FileInputStream(file);

        CalendarBuilder builder = new CalendarBuilder();
        Calendar calendar = builder.build(fin);

        Period period = getPeriod();
        ArrayList<String> events = new ArrayList<>();

        for (Object o : calendar.getComponents("VEVENT")) {
            Component c = (Component) o;
            PeriodList list = c.calculateRecurrenceSet(period);

            for (int i = 0; i < list.size(); i++) {
                Pattern p = Pattern.compile("SUMMARY:[\\S ]+");
                Matcher m = p.matcher(o.toString());

                while (m.find()) {
                    events.add(m.group().split(":")[1].toLowerCase());
                }
            }
        }

        for (String evt : events) {
            if (evt.contains("no school"))
                return "none";

            if (evt.contains("finals")) {
                switch (new Date().getDay()) {
                    case 3:
                        return "/schedules/final_1.json";

                    case 4:
                        return "/schedules/final_2.json";

                    case 5:
                        return "/schedules/final_3.json";

                    default:
                        break;
                }
            }

            if (evt.contains("parade"))
                return "/schedules/parade.json";

            if (evt.contains("pep rally"))
                return "/schedules/pep_rally.json";

            if (evt.contains("conferences"))
                return "/schedules/conferences.json";

            if (evt.contains("first day"))
                return "/schedules/first day.json";

            if (evt.contains("minimum day"))
                return "/schedules/minimum.json";
        }

        int cur_day = new Date().getDay();
        if (cur_day == 1 || cur_day == 3 || cur_day == 4) {
            return "/schedules/regular.json";
        } else if (cur_day == 2) {
            return "/schedules/late_start.json";
        } else if (cur_day == 5) {
            return "/schedules/homeroom.json";
        }

        return "none";
    }

    Period getPeriod() throws ParseException {
        TimeZone tz = TimeZone.getTimeZone("PST");
        DateFormat df = new SimpleDateFormat("yyyyMMdd'T'");
        df.setTimeZone(tz);

        String now = df.format(new Date());

        DateTime from = new DateTime(now + "000000Z");
        DateTime to = new DateTime(now + "115959Z");
        Period period = new Period(from, to);

        return period;
    }
}