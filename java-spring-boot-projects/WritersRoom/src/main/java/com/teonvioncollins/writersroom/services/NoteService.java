package com.teonvioncollins.writersroom.services;

import com.teonvioncollins.writersroom.models.Note;
import com.teonvioncollins.writersroom.models.User;
import com.teonvioncollins.writersroom.repositories.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    public Note addNewNote(User user) {
        Note note = new Note();
        note.setTitle("Untitled");
        note.setContent("");
        note.setUser(user);

        return noteRepository.save(note);
    }
}
