package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/time-entries")
public class TimeEntryController {

    private TimeEntryRepository timeEntriesRepo;

    public TimeEntryController(TimeEntryRepository timeEntriesRepo) {
        this.timeEntriesRepo = timeEntriesRepo;
    }

   @PostMapping
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntryToCreate) {
       TimeEntry createdTimeEntry =  timeEntriesRepo.create(timeEntryToCreate);
       return new ResponseEntity<>(createdTimeEntry, HttpStatus.CREATED);
    }

    @GetMapping("{timeEntryId}")
   // @RequestMapping(value = "/time-entries/{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable long timeEntryId) {
        TimeEntry foundTimeEntry = timeEntriesRepo.find(timeEntryId);
        if(foundTimeEntry == null) {
            return new ResponseEntity<>(foundTimeEntry, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(foundTimeEntry, HttpStatus.OK);
    }

    @PutMapping("{timeEntryId}")
    public ResponseEntity<TimeEntry> update(@PathVariable long timeEntryId,@RequestBody TimeEntry expected) {
        TimeEntry updatedTimeEntry =  timeEntriesRepo.update(timeEntryId,expected);
        if(updatedTimeEntry == null) {
            return new ResponseEntity<>(updatedTimeEntry, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedTimeEntry, HttpStatus.OK);
    }

    @DeleteMapping("{timeEntryId}")
    public ResponseEntity< List<TimeEntry>> delete(@PathVariable long timeEntryId) {
        timeEntriesRepo.delete(timeEntryId);
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }
    @GetMapping
    public ResponseEntity<List<TimeEntry>> list() {
        List<TimeEntry>  listOfTimeEntry = timeEntriesRepo.list();
        return new ResponseEntity<>(listOfTimeEntry, HttpStatus.OK);
    }
}
