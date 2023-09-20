package fr.univ_lyon1.info.m1.elizagpt.controller;

import fr.univ_lyon1.info.m1.elizagpt.model.messages.MessageRepository;

public class MessageController {
    public void sendMessage(String message) {
        MessageRepository.getInstance().sendMessage(message);
    }
}
