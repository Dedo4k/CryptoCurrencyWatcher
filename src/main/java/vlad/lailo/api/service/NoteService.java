package vlad.lailo.api.service;

import vlad.lailo.api.entity.Note;

import java.util.List;

public interface NoteService {

    void addNote(String username, long id);

    List<Note> findAll();
}
