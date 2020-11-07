package de.meldanor.gronkskyrim.postprocess;

import de.meldanor.gronkskyrim.events.SeriesEventLog;

import java.util.concurrent.Callable;

public class CsvExport implements Callable<Void> {

    private final SeriesEventLog eventLog;

    public CsvExport(SeriesEventLog eventLog) {
        this.eventLog = eventLog;
    }

    @Override
    public Void call() throws Exception {
        System.out.println(eventLog.getEventLogs().size());
        System.out.println(eventLog.getEventLogs().get(1).getEvents());
        System.out.println(eventLog.getSeries().getTotalEpisodeLengthSeconds());
        return null;
    }


}
