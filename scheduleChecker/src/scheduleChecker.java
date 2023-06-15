import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class scheduleChecker {
    private String fileName;
    private String error;

    public scheduleChecker(String fileName) {
        this.fileName = fileName;
        this.error = "No Error";
    }

    public boolean check() {
        List<Map<String, String>> schedule = loadScheduleFromFile();
        if (schedule.isEmpty()) {
            error = "Schedule file is empty.";
            return false;
        }

        int totalWeeks = schedule.size();
        int teamCount = schedule.get(0).size();
        int gamesPerWeek = teamCount / 2;

        // Check each week of the schedule
        for (int week = 0; week < totalWeeks; week++) {
            Map<String, Integer> homeTeamCount = new HashMap<>();
            Map<String, Integer> awayTeamCount = new HashMap<>();

            // Check number of games in the week
            if (schedule.get(week).size() != gamesPerWeek) {
                error = "Incorrect Number of Games in Week " + (week + 1);
                return false;
            }

            // Check for duplicate games
            for (Map.Entry<String, String> game : schedule.get(week).entrySet()) {
                String homeTeam = game.getKey();
                String awayTeam = game.getValue();

                // Check if the home team is scheduled twice in the same week
                if (homeTeamCount.containsKey(homeTeam)) {
                    error = homeTeam + " Scheduled Twice in Week " + (week + 1);
                    return false;
                }

                // Check if the away team is scheduled twice in the same week
                if (awayTeamCount.containsKey(awayTeam)) {
                    error = awayTeam + " Scheduled Twice in Week " + (week + 1);
                    return false;
                }

                homeTeamCount.put(homeTeam, week);
                awayTeamCount.put(awayTeam, week);

                // Check for duplicate games with reversed home and away teams
                if (homeTeamCount.containsKey(awayTeam) && homeTeamCount.get(awayTeam) != week) {
                    error = "Game Between " + homeTeam + " and " + awayTeam + " duplicate scheduled in Week " + (week + 1);
                    return false;
                }

                if (awayTeamCount.containsKey(homeTeam) && awayTeamCount.get(homeTeam) != week) {
                    error = "Game Between " + homeTeam + " and " + awayTeam + " duplicate scheduled in Week " + (week + 1);
                    return false;
                }
            }
        }

        return true;
    }

    private List<Map<String, String>> loadScheduleFromFile() {
        List<Map<String, String>> schedule = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Map<String, String> weekSchedule = new HashMap<>();
                String[] games = line.split(",");
                for (String game : games) {
                    String[] teams = game.split(":");
                    weekSchedule.put(teams[1].trim(), teams[2].trim());
                }
                schedule.add(weekSchedule);
            }
        } catch (IOException e) {
            error = "Error loading schedule from file.";
            return schedule;
        }

        return schedule;
    }

    public String getError() {
        return error;
    }
}
