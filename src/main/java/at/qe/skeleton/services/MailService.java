package at.qe.skeleton.services;

import at.qe.skeleton.model.*;
import at.qe.skeleton.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

@Service
public class MailService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    @Async
    public void sendMailEnded(Event event) {
        String[] tos = event.getParticipants().stream().map(x -> x.getEmail()).collect(Collectors.toList()).toArray(new String[0]);

        String subject = "Voting for " + event.getName() + " has ended";
        String text = "Hello, the voting for " + event.getName() + " has ended. The Event will take place at " + event.getLocation() +
                      " on " + event.getStart().toLocalDate() + " between " + event.getStart().toLocalTime() + " and " + event.getEnd().toLocalTime() + ". Participants: " + event.getNumParticipants();

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom("swa.g4t2@gmail.com");
        msg.setTo(tos);
        msg.setSubject(subject);
        msg.setText(text);

        javaMailSender.send(msg);
    }

    @Async
    public void sendMailCancelled(String name, String[] tos) {
        String subject = "Voting for " + name + " was cancelled";
        String text = "Hello, the voting for " + name + " was cancelled.";

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom("swa.g4t2@gmail.com");
        msg.setTo(tos);
        msg.setSubject(subject);
        msg.setText(text);

        javaMailSender.send(msg);
    }

    @Async
    public void sendMailInvitation(Voting voting, List<User> users) {
        String[] tos = voting.getParticipants().stream().map(x -> x.getEmail()).collect(Collectors.toList()).toArray(new String[0]);

        String subject = "Intivation for voting " + voting.getName();
        String text = "Hello, you have been invited to the voting " + voting.getName();

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom("swa.g4t2@gmail.com");
        msg.setTo(tos);
        msg.setSubject(subject);
        msg.setText(text);

        javaMailSender.send(msg);
    }
}
