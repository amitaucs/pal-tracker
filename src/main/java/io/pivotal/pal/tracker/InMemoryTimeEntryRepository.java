package io.pivotal.pal.tracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryTimeEntryRepository implements TimeEntryRepository{

    private HashMap<Long, TimeEntry> timeEntries = new HashMap<>();
    private long currentId = 1L;


    @Override
    public TimeEntry create(TimeEntry timeEntry) {

        Long id = currentId++;

        TimeEntry newtimeEntry = new TimeEntry(id,timeEntry.getProjectId(),timeEntry.getUserId(),
                timeEntry.getDate(),timeEntry.getHours());

        timeEntries.put(id,newtimeEntry);

        return newtimeEntry;
    }

    @Override
    public TimeEntry find(long timeEntryId) {
        return timeEntries.get(timeEntryId);
    }

    @Override
    public List<TimeEntry> list() {
        return new ArrayList<>(timeEntries.values());
    }

    @Override
    public TimeEntry update(long id, TimeEntry timeEntry) {
        if(find(id) == null)
            return null;
        TimeEntry updatedTimeEntry = new TimeEntry(id,
                timeEntry.getProjectId(),timeEntry.getUserId(),timeEntry.getDate(),timeEntry.getHours());

        timeEntries.put(id,updatedTimeEntry);
        return updatedTimeEntry;
    }

    @Override
    public void delete(long timeEntryId) {

        timeEntries.remove(timeEntryId);

    }
}
