package com.teonvioncollins.ImposterGameApplication.services;

import com.teonvioncollins.ImposterGameApplication.models.GameSession;
import com.teonvioncollins.ImposterGameApplication.models.Player;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class CreateSessionService {

    Map<Integer, GameSession> sessions = new ConcurrentHashMap<>();

    private int generateRandomCode() {

        int code;
        do {

            code = (int) (Math.random() * 9000) + 1000;

        } while (sessions.containsKey(code));

        return code;
    }


    public String chooseRandomCategory() {

        List<String> categories = List.of(
                "Food",
                "Animal",
                "Person",
                "Number",
                "Place"
        );

        int index = new Random().nextInt(categories.size());
        return categories.get(index);
    }

    public String chooseRandomQuestion(String category) {

        Map<String, List<String>> questions = Map.of(
                "Food", List.of(
                        "Which food feels nostalgic to you?",
                        "What warm food do people claim is better when it's cold?",
                        "What's a food you absolutely hate?",
                        "What food have you definitely dropped on the floor before eating anyway?"


                ),
                "Animal", List.of(
                        "What animal would you own if you had no choice?",
                        "What animal’s personality do you connect with most?",
                        "What animal would you lose to in a fight?"

                ),
                "Person", List.of(
                        "Who is the most dramatic person you know?",
                        "Who is the calmest person you know, even in chaos?",
                        "Who would get scammed in the funniest way?",
                        "Who would confidently answer a question with the wrong answer?",
                        "Who would take a joke too seriously?"


                ),
                "Number", List.of(
                        "How many hours of sleep do you aim for?",
                        "How many attempts does it take before you accept defeat?"

                ),
                "Place", List.of(
                        "What place would make you quit your job if you had to go daily?",
                        "What place do you always spend too much time in?",
                        "What place makes you act more polite than usual?",
                        "What place would you love to visit?"

                )
        );

        List<String> questionList = questions.get(category);
        int index = new Random().nextInt(questionList.size());
        return questionList.get(index);
    }

    public void assignSingleImposter(GameSession session) {
        int count = session.getPlayers().size();
        int index = new Random().nextInt(count);

        for (int i = 0; i < count; i++) {
            Player p = session.getPlayers().get(i);

            if (i == index)
                p.setRole("IMPOSTER 😬");
            else
                p.setRole("INNOCENT 😅");
        }
    }

    public void lockSession(int code, int maxPlayers) {
        GameSession session = sessions.get(code);
        if (session != null) {
            session.setMaxPlayers(maxPlayers);
            session.setLocked(true);
        }
    }

    public GameSession createSession() {

        int code = generateRandomCode();

        String category = chooseRandomCategory();

        String question = chooseRandomQuestion(category);

        GameSession session = new GameSession();
        session.setCode(code);
        session.setCategory(category);
        session.setQuestion(question);
        session.setPlayers(new ArrayList<>());

        sessions.put(code, session);

        return session;

    }

    public GameSession getSession(int code) {
        return sessions.get(code);
    }

    public Player joinSession(int code, String name) {

        GameSession session = sessions.get(code);
        if (session == null) return null;

        if (session.isLocked() && session.getPlayers().size() >= session.getMaxPlayers()) {
            return null;
        }

        int number = session.getPlayers().size() + 1;
        String playerId = UUID.randomUUID().toString();
        Player player = new Player(name, number, playerId);

        session.getPlayers().add(player);

        System.out.println("JOIN REQUEST CODE = " + code);
        System.out.println("LOCKED: " + session.isLocked());
        System.out.println("PLAYERS: " + session.getPlayers().size());
        System.out.println("MAX: " + session.getMaxPlayers());
        System.out.println("ASSIGNED: " + session.isAssignedImposter());


        if (session.isLocked() &&
                session.getPlayers().size() == session.getMaxPlayers() &&
                !session.isAssignedImposter())
        {
            assignSingleImposter(session);
            session.setAssignedImposter(true);
        }

        return player;
    }

    public String getPrompt(int code, String playerId) {

        GameSession session = sessions.get(code);

        if (session == null) return null;

        Player player = null;

        for (Player p : session.getPlayers()) {
            if (p.getPlayerId().equals(playerId)) {
                player = p;
                break;
            }
        }

        return session.getQuestion();
    }
}