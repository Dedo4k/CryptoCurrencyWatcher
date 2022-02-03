package vlad.lailo.api.service.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import vlad.lailo.api.dao.NoteRepository;
import vlad.lailo.api.entity.Coin;
import vlad.lailo.api.entity.Note;
import vlad.lailo.api.service.CoinService;
import vlad.lailo.api.service.NoteService;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;
    private final CoinService coinService;

    public NoteServiceImpl(@Qualifier("noteRepository") NoteRepository noteRepository,
                           @Qualifier("coinServiceImpl") CoinService coinService) {
        this.noteRepository = noteRepository;
        this.coinService = coinService;
    }

    @Override
    public void addNote(String username, long id) {
        Coin coin = coinService.getById(id);
        Note note = new Note(username, coin.getId(), coin.getPriceUsd());
        noteRepository.save(note);
    }

    @Override
    public List<Note> findAll() {
        return noteRepository.findAll();
    }
}
