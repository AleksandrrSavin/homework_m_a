import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class AviaSoulsTest {
    Ticket ticket1 = new Ticket("MSK", "SPB", 5_000, 8, 10);
    Ticket ticket2 = new Ticket("MSK", "SPB", 11_000, 8, 15);
    Ticket ticket3 = new Ticket("EKB", "SPB", 95_000, 8, 14);
    Ticket ticket4 = new Ticket("MSK", "EKB", 245_070, 8, 10);
    Ticket ticket5 = new Ticket("MSK", "SPB", 2_500, 8, 13);
    Ticket ticket6 = new Ticket("MSK", "EKB", 5_050, 8, 11);

    private AviaSouls fillTickets() {
        AviaSouls tickets = new AviaSouls();
        tickets.add(ticket1);
        tickets.add(ticket2);
        tickets.add(ticket3);
        tickets.add(ticket4);
        tickets.add(ticket5);
        tickets.add(ticket6);
        return tickets;
    }


    @Test
    public void shouldComparablePrice() {
        Ticket[] actual = {ticket1, ticket2, ticket3, ticket4, ticket5, ticket6};
        Ticket[] expected = {ticket5, ticket1, ticket6, ticket2, ticket3, ticket4};
        Arrays.sort(actual);
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSortAfterSearch() {
        AviaSouls ticketsManager = fillTickets();
        Ticket[] expected = {ticket5, ticket1, ticket2};
        Ticket[] actual = ticketsManager.search("MSK", "SPB");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearch1Ticket() {
        AviaSouls ticketsManager = fillTickets();
        Ticket[] expected = {ticket3};
        Ticket[] actual = ticketsManager.search("EKB", "SPB");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotSearchTicket() {
        AviaSouls ticketsManager = fillTickets();
        Ticket[] expected = {};
        Ticket[] actual = ticketsManager.search("EBB", "SPB");
        Assertions.assertArrayEquals(expected, actual);
    }
    @Test
    public void shouldComparableTime() {
        TicketTimeComparator timeComparator = new TicketTimeComparator();
        Ticket[] actual = {ticket1, ticket2, ticket3, ticket4, ticket5, ticket6};
        Ticket[] expected = {ticket1, ticket4, ticket6, ticket5, ticket3, ticket2};
        Arrays.sort(actual, timeComparator);
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSortTimeAfterSearch() {
        TicketTimeComparator timeComparator = new TicketTimeComparator();
        AviaSouls ticketsManager = fillTickets();
        Ticket[] expected = {ticket1, ticket5, ticket2};
        Ticket[] actual = ticketsManager.searchAndSortBy("MSK", "SPB", timeComparator);
        Assertions.assertArrayEquals(expected, actual);
    }
}