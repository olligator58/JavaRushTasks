package com.javarush.task.task39.task3913;

import com.javarush.task.task39.task3913.query.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class LogParser implements IPQuery, UserQuery, DateQuery, EventQuery, QLQuery {
    private Path logDir;
    private List<LogLine> logLines;
    private SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

    public LogParser(Path logDir) {
        this.logDir = logDir;
        logLines = parseLogFiles();
    }

    private List<LogLine> parseLogFiles() {
        List<LogLine> result = new ArrayList<>();
        List<Path> logFiles = findLogFiles();
        for (Path logFile : logFiles) {
            try (BufferedReader reader = new BufferedReader(new FileReader(logFile.toFile()))) {
                while (reader.ready()) {
                    String line = reader.readLine();
                    result.add(parseLogLine(line));
                }
            } catch (IOException ignored) {
            }
        }
        return result;
    }

    private List<Path> findLogFiles() {
        List<Path> result = new ArrayList<>();
        try {
            DirectoryStream<Path> paths = Files.newDirectoryStream(logDir);
            for (Path path : paths) {
                if (Files.isRegularFile(path) && path.toString().endsWith(".log")) {
                    result.add(path);
                }
            }
        } catch (IOException ignored) {
        }
        return result;
    }

    private List<LogLine> filterLogLines(List<LogLine> logLines, Date after, Date before) {
        List<LogLine> result = new ArrayList<>();
        for (LogLine logLine : logLines) {
            if ((after == null || !after.after(logLine.date)) && (before == null || !before.before(logLine.date))) {
                result.add(logLine);
            }
        }
        return result;
    }

    private LogLine parseLogLine(String line) {
        String[] fields = line.split("\t");

        String ip = fields[0];
        String user = fields[1];
        Date date = null;
        try {
            date = formatter.parse(fields[2]);
        } catch (ParseException ignored) {
        }
        String[] eventFields = fields[3].split("\\s");
        Event event = Event.valueOf(eventFields[0]);
        int eventParameter = -1;
        if (eventFields.length > 1) {
            eventParameter = Integer.parseInt(eventFields[1]);
        }
        Status status = Status.valueOf(fields[4]);
        return new LogLine(ip, user, date, event, eventParameter, status);
    }

    @Override
    public int getNumberOfUniqueIPs(Date after, Date before) {
        return getUniqueIPs(after, before).size();
    }

    @Override
    public Set<String> getUniqueIPs(Date after, Date before) {
        Set<String> result = new HashSet<>();
        for (LogLine logLine : filterLogLines(logLines, after, before)) {
            result.add(logLine.ip);
        }
        return result;
    }

    @Override
    public Set<String> getIPsForUser(String user, Date after, Date before) {
        Set<String> result = new HashSet<>();
        for (LogLine logLine : filterLogLines(logLines, after, before)) {
            if (user.equals(logLine.user)) {
                result.add(logLine.ip);
            }
        }
        return result;
    }

    @Override
    public Set<String> getIPsForEvent(Event event, Date after, Date before) {
        Set<String> result = new HashSet<>();
        for (LogLine logLine : filterLogLines(logLines, after, before)) {
            if (event == logLine.event) {
                result.add(logLine.ip);
            }
        }
        return result;
    }

    @Override
    public Set<String> getIPsForStatus(Status status, Date after, Date before) {
        Set<String> result = new HashSet<>();
        for (LogLine logLine : filterLogLines(logLines, after, before)) {
            if (status == logLine.status) {
                result.add(logLine.ip);
            }
        }
        return result;
    }

    @Override
    public Set<String> getAllUsers() {
        return logLines.stream()
                .map(l -> l.user)
                .collect(Collectors.toSet());
    }

    @Override
    public int getNumberOfUsers(Date after, Date before) {
        return filterLogLines(logLines, after, before).stream()
                .map(l -> l.user)
                .collect(Collectors.toSet()).size();
    }

    @Override
    public int getNumberOfUserEvents(String user, Date after, Date before) {
        return filterLogLines(logLines, after, before).stream()
                .filter(line -> line.user.equals(user))
                .map(l -> l.event)
                .collect(Collectors.toSet()).size();
    }

    @Override
    public Set<String> getUsersForIP(String ip, Date after, Date before) {
        return filterLogLines(logLines, after, before).stream()
                .filter(l -> l.ip.equals(ip))
                .map(l -> l.user)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getLoggedUsers(Date after, Date before) {
        return filterLogLines(logLines, after, before).stream()
                .filter(l -> l.event == Event.LOGIN)
                .map(l -> l.user)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getDownloadedPluginUsers(Date after, Date before) {
        return filterLogLines(logLines, after, before).stream()
                .filter(l -> l.event == Event.DOWNLOAD_PLUGIN)
                .map(l -> l.user)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getWroteMessageUsers(Date after, Date before) {
        return filterLogLines(logLines, after, before).stream()
                .filter(l -> l.event == Event.WRITE_MESSAGE)
                .map(l -> l.user)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before) {
        return filterLogLines(logLines, after, before).stream()
                .filter(l -> l.event == Event.SOLVE_TASK)
                .map(l -> l.user)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before, int task) {
        return filterLogLines(logLines, after, before).stream()
                .filter(l -> l.event == Event.SOLVE_TASK && l.eventParameter == task)
                .map(l -> l.user)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before) {
        return filterLogLines(logLines, after, before).stream()
                .filter(l -> l.event == Event.DONE_TASK)
                .map(l -> l.user)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before, int task) {
        return filterLogLines(logLines, after, before).stream()
                .filter(l -> l.event == Event.DONE_TASK && l.eventParameter == task)
                .map(l -> l.user)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Date> getDatesForUserAndEvent(String user, Event event, Date after, Date before) {
        return filterLogLines(logLines, after, before).stream()
                .filter(l -> l.user.equals(user))
                .filter(l -> l.event == event)
                .map(l -> l.date)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Date> getDatesWhenSomethingFailed(Date after, Date before) {
        return filterLogLines(logLines, after, before).stream()
                .filter(l -> l.status == Status.FAILED)
                .map(l -> l.date)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Date> getDatesWhenErrorHappened(Date after, Date before) {
        return filterLogLines(logLines, after, before).stream()
                .filter(l -> l.status == Status.ERROR)
                .map(l -> l.date)
                .collect(Collectors.toSet());
    }

    @Override
    public Date getDateWhenUserLoggedFirstTime(String user, Date after, Date before) {
        return filterLogLines(logLines, after, before).stream()
                .filter(l -> l.user.equals(user))
                .filter(l -> l.event == Event.LOGIN)
                .map(l -> l.date)
                .min(Comparator.naturalOrder())
                .orElse(null);
    }

    @Override
    public Date getDateWhenUserSolvedTask(String user, int task, Date after, Date before) {
        return filterLogLines(logLines, after, before).stream()
                .filter(l -> l.user.equals(user))
                .filter(l -> l.event == Event.SOLVE_TASK)
                .filter(l -> l.eventParameter == task)
                .map(l -> l.date)
                .min(Comparator.naturalOrder())
                .orElse(null);
    }

    @Override
    public Date getDateWhenUserDoneTask(String user, int task, Date after, Date before) {
        return filterLogLines(logLines, after, before).stream()
                .filter(l -> l.user.equals(user))
                .filter(l -> l.event == Event.DONE_TASK)
                .filter(l -> l.eventParameter == task)
                .map(l -> l.date)
                .min(Comparator.naturalOrder())
                .orElse(null);
    }

    @Override
    public Set<Date> getDatesWhenUserWroteMessage(String user, Date after, Date before) {
        return filterLogLines(logLines, after, before).stream()
                .filter(l -> l.user.equals(user))
                .filter(l -> l.event == Event.WRITE_MESSAGE)
                .map(l -> l.date)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Date> getDatesWhenUserDownloadedPlugin(String user, Date after, Date before) {
        return filterLogLines(logLines, after, before).stream()
                .filter(l -> l.user.equals(user))
                .filter(l -> l.event == Event.DOWNLOAD_PLUGIN)
                .map(l -> l.date)
                .collect(Collectors.toSet());
    }

    @Override
    public int getNumberOfAllEvents(Date after, Date before) {
        return getAllEvents(after, before).size();
    }

    @Override
    public Set<Event> getAllEvents(Date after, Date before) {
        return filterLogLines(logLines, after, before).stream()
                .map(l -> l.event)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Event> getEventsForIP(String ip, Date after, Date before) {
        return filterLogLines(logLines, after, before).stream()
                .filter(l -> l.ip.equals(ip))
                .map(l -> l.event)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Event> getEventsForUser(String user, Date after, Date before) {
        return filterLogLines(logLines, after, before).stream()
                .filter(l -> l.user.equals(user))
                .map(l -> l.event)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Event> getFailedEvents(Date after, Date before) {
        return filterLogLines(logLines, after, before).stream()
                .filter(l -> l.status == Status.FAILED)
                .map(l -> l.event)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Event> getErrorEvents(Date after, Date before) {
        return filterLogLines(logLines, after, before).stream()
                .filter(l -> l.status == Status.ERROR)
                .map(l -> l.event)
                .collect(Collectors.toSet());
    }

    @Override
    public int getNumberOfAttemptToSolveTask(int task, Date after, Date before) {
        return (int) filterLogLines(logLines, after, before).stream()
                .filter(l -> l.event == Event.SOLVE_TASK)
                .filter(l -> l.eventParameter == task)
                .count();
    }

    @Override
    public int getNumberOfSuccessfulAttemptToSolveTask(int task, Date after, Date before) {
        return (int) filterLogLines(logLines, after, before).stream()
                .filter(l -> l.event == Event.DONE_TASK)
                .filter(l -> l.eventParameter == task)
                .count();
    }

    @Override
    public Map<Integer, Integer> getAllSolvedTasksAndTheirNumber(Date after, Date before) {
        Map<Integer, Integer> result = new HashMap<>();
        List<LogLine> solvedTasks = filterLogLines(logLines, after, before).stream()
                .filter(l -> l.event == Event.SOLVE_TASK)
                .collect(Collectors.toList());
        for (LogLine logLine : solvedTasks) {
            int count = result.getOrDefault(logLine.eventParameter, 0);
            result.put(logLine.eventParameter, ++count);
        }
        return result;
    }

    @Override
    public Map<Integer, Integer> getAllDoneTasksAndTheirNumber(Date after, Date before) {
        Map<Integer, Integer> result = new HashMap<>();
        List<LogLine> solvedTasks = filterLogLines(logLines, after, before).stream()
                .filter(l -> l.event == Event.DONE_TASK)
                .collect(Collectors.toList());
        for (LogLine logLine : solvedTasks) {
            int count = result.getOrDefault(logLine.eventParameter, 0);
            result.put(logLine.eventParameter, ++count);
        }
        return result;
    }

    private Object[] parseQLParams(String query) {
        query = query.replaceAll("\\s+", " ");
        String newQuery = query;
        List<String> values = new ArrayList<>();

        Pattern pattern = Pattern.compile("\".+?\"");
        Matcher matcher = pattern.matcher(query);
        while (matcher.find()) {
            String value = query.substring(matcher.start(), matcher.end());
            values.add(value.replace("\"", ""));
            newQuery = newQuery.replace(value, "");
        }

        String[] fields = newQuery.replaceAll("\\s+", " ").split("\\s");
        int n;
        if (fields.length < 5) {
            n = 1;
        } else if (fields.length < 9) {
            n = 3;
        } else {
            n = 5;
        }
        Object[] result = new Object[n];
        result[0] = fields[1].toLowerCase().trim();

        if (n > 1) {
            String fieldName = fields[3].toLowerCase().trim();
            result[1] = fieldName;
            String fieldValue = values.get(0);
            switch (fieldName) {
                case "ip":
                case "user":
                    result[2] = fieldValue;
                    break;
                case "date":
                    try {
                        result[2] = formatter.parse(fieldValue);
                    } catch (ParseException ignored) {
                    }
                    break;
                case "event":
                    result[2] = Event.valueOf(fieldValue);
                    break;
                case "status":
                    result[2] = Status.valueOf(fieldValue);
                    break;
            }
        }
        if (n > 3) {
            try {
                result[3] = formatter.parse(values.get(1));
                result[4] = formatter.parse(values.get(2));
            } catch (ParseException ignore) {
            }
        }
        return result;
    }

    private Object getField(LogLine logLine, String field) {
        Object result = null;
        switch (field) {
            case "ip":
                result = logLine.ip;
                break;
            case "user":
                result = logLine.user;
                break;
            case "date":
                result = logLine.date;
                break;
            case "event":
                result = logLine.event;
                break;
            case "status":
                result = logLine.status;
                break;
        }
        return result;
    }

    private boolean fieldHasSuchValue(LogLine logLine, Object field, Object value) {
        return getField(logLine, (String) field).equals(value);
    }

    @Override
    public Set<Object> execute(String query) {
        Set<Object> result = new HashSet<>();
        Object[] qlParams = parseQLParams(query);
        boolean needFieldFilter = qlParams.length > 1;
        boolean needDatesFilter = qlParams.length > 3;
        List<LogLine> lines = needDatesFilter ? filterLogLines(logLines, (Date) qlParams[3], (Date) qlParams[4]) : logLines;
        for (LogLine line : lines) {
            if (!needFieldFilter || fieldHasSuchValue(line, qlParams[1], qlParams[2])) {
                result.add(getField(line, (String) qlParams[0]));
            }
        }
        return result;
    }

    private static class LogLine {
        private String ip;
        private String user;
        private Date date;
        private Event event;
        private int eventParameter;
        private Status status;

        public LogLine(String ip, String user, Date date, Event event, int eventParameter, Status status) {
            this.ip = ip;
            this.user = user;
            this.date = date;
            this.event = event;
            this.eventParameter = eventParameter;
            this.status = status;
        }

        @Override
        public int hashCode() {
            int result = (ip != null) ? ip.hashCode() : 0;
            result = 31 * result + ((user != null) ? user.hashCode() : 0);
            result = 31 * result + ((date != null) ? date.hashCode() : 0);
            result = 31 * result + ((event != null) ? event.hashCode() : 0);
            result = 31 * result + eventParameter;
            result = 31 * result + ((status != null) ? status.hashCode() : 0);
            return result;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || o.getClass() != getClass()) {
                return false;
            }
            LogLine logLine = (LogLine) o;
            if ((ip != null) ? !ip.equals(logLine.ip) : logLine.ip != null) return false;
            if ((user != null) ? !user.equals(logLine.user) : logLine.user != null) return false;
            if ((date != null) ? !date.equals(logLine.date) : logLine.date != null) return false;
            if ((event != null) ? !event.equals(logLine.event) : logLine.event != null) return false;
            if (eventParameter != logLine.eventParameter) return false;
            return (status != null) ? status.equals(logLine.status) : logLine.status == null;
        }

        @Override
        public String toString() {
            return "LogLine{" +
                    "ip='" + ip + '\'' +
                    ", user='" + user + '\'' +
                    ", date=" + date +
                    ", event=" + event +
                    ", eventParameter='" + eventParameter + '\'' +
                    ", status=" + status +
                    '}';
        }
    }
}