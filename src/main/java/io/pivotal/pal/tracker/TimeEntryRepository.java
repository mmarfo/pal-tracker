package io.pivotal.pal.tracker;

import java.util.List;

public interface TimeEntryRepository {
     TimeEntry create(TimeEntry any);

     TimeEntry find(long timeEntryId) ;

     List<TimeEntry> list();

     TimeEntry update(Long id, TimeEntry timeEntry);

     void delete(Long timeEntryId) ;
}
