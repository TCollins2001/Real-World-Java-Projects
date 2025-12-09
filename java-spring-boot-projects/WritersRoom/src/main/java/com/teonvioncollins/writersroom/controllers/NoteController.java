package com.teonvioncollins.writersroom.controllers;

import com.teonvioncollins.writersroom.models.Note;
import com.teonvioncollins.writersroom.models.User;
import com.teonvioncollins.writersroom.services.NoteService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class NoteController {

    @Autowired
    private NoteService noteService;

    @PostMapping("/add-note")
        public String addNote(HttpSession session) {

        User user = (User) session.getAttribute("user");
        if (user == null) return "redirect:/login";

        Note note = noteService.addNewNote(user);

        return "redirect:/notes?selected=" + note.getId();
        }
}
