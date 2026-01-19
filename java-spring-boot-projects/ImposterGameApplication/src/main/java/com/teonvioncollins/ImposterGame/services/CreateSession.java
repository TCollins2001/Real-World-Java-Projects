package com.teonvioncollins.ImposterGame.services;

import com.teonvioncollins.ImposterGame.models.GameModel;
import com.teonvioncollins.ImposterGame.models.Player;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class CreateSession {

    private static final Map<Integer, GameModel> sessions = new ConcurrentHashMap<>();

    public int createSession() {
        int code = generateRandomCode();
        sessions.put(code, new GameModel(code));
        return code;
    }

    public GameModel getSession(int code) {
        return sessions.get(code) ;
    }

    public void lockSession(int code, int maxPlayers, int totalRounds) {

        GameModel session = sessions.get(code);

        if (session == null) {
            return;
        }

        session.lock(maxPlayers);
        session.setTotalRounds(totalRounds);
    }

    public Player joinSession(int code, String name) {

        GameModel session = sessions.get(code);

        if (session == null) {
            return null;
        }

        if (!session.canJoin()) {
            return null;
        }

        Player player = new Player(name);

        if (session.getPlayers().isEmpty()) {
            player.setHost(true);
        }

        session.addPlayer(player);

        if (session.isLocked()
                && session.getPlayers().size() == session.getMaxPlayers()
                && !session.isImposterAssigned()) {

            assignImposter(session);

            String category = chooseRandomCategory();
            String question = chooseRandomQuestion(category);

            session.setCategory(category);
            session.setQuestion(question);

            session.setReadyAt(System.currentTimeMillis());
            }
        return player;

    }

    public int generateRandomCode() {

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
                        "What food have you definitely dropped on the floor before eating anyway?",
                        "What food is impossible to eat neatly?"
                ),
                "Animal", List.of(
                        "What animal would you own if you had no choice?",
                        "What animal’s personality do you connect with most?",
                        "What animal would you lose to in a fight?",
                        "Which animal would be the worst at hide-and-seek?"

                ),
                "Person", List.of(
                        "Who is the most dramatic person you know?",
                        "Who is the calmest person you know, even in chaos?",
                        "Who would get scammed in the funniest way?",
                        "Who would confidently answer a question with the wrong answer?",
                        "Who would take a joke too seriously?",
                        "Who would survive a zombie apocalypse the longest?"
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

    public synchronized void assignImposter(GameModel session) {

        if (session.isImposterAssigned()) {
            return;
        }

        List<Player> players = session.getPlayers();
        int index = new Random().nextInt(players.size());

        Player imposter = players.get(index);
        imposter.setRole("IMPOSTER \uD83D\uDE2C");

        session.setImposterAssigned(true);
    }

}