package ru.innopolis;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import ru.innopolis.services.TicketService;
import ru.innopolis.services.TicketServiceImpl;

@EnableScheduling
@Configuration
@ConditionalOnProperty(name = "spring.enable.scheduling")
class ChangeTicketStatusScheduller {
    TicketService ticketService;

    @Autowired
    public ChangeTicketStatusScheduller(TicketService ticketService) {
        this.ticketService = ticketService;
    }
    @Scheduled(fixedDelay = 300000)
    public void runScheduler() {
        ticketService.updateTicketsStatusFreeWhereStartTimeLess30();
    }
}
