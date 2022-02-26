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

        

        return "";
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