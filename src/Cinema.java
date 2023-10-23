import java.util.Arrays;

public class Cinema {
    private int[][][] seats;

    public Cinema(int numHalls, int numRows, int numSeats) {
        seats = new int[numHalls][numRows][numSeats];
        // Ініціалізація масиву нулями
        for (int hall = 0; hall < numHalls; hall++) {
            for (int row = 0; row < numRows; row++) {
                for (int seat = 0; seat < numSeats; seat++) {
                    seats[hall][row][seat] = 0;
                }
            }
        }
    }

    public boolean bookSeats(int hallNumber, int row, int[] seatsToBook) {
        for (int seat : seatsToBook) {
            if (seats[hallNumber][row][seat-1] == 1) {
                System.out.println("Місце " + row + "-" + seat + " в залі " + hallNumber + " вже заброньоване.");
                return false;
            }
        }
        for (int seat : seatsToBook) {
            seats[hallNumber][row][seat-1] = 1;
        }
        System.out.println("Місця " + row + "-" + Arrays.toString(seatsToBook) + " в залі " + hallNumber + " успішно заброньовані.");
        return true;
    }

    public boolean bookSingleSeat(int hallNumber, int row, int seat) {
        if (seats[hallNumber][row][seat-1] == 1) {
            System.out.println("Місце " + row + "-" + seat + " в залі " + hallNumber + " вже заброньоване.");
            return false;
        }

        seats[hallNumber][row][seat-1] = 1; // Позначити місце, яке було заброньоване
        System.out.println("Місце " + row + "-" + seat + " в залі " + hallNumber + " успішно заброньоване.");
        return true;
    }

    public void cancelBooking(int hallNumber, int row, int[] seatsToCancel) {
        for (int seat : seatsToCancel) {
            seats[hallNumber][row][seat] = 0;
        }
        System.out.println("Скасовано бронювання місць " + row + "-" + Arrays.toString(seatsToCancel) + " в залі " + hallNumber + ".");
    }

    public boolean checkAvailability(int hallNumber, int numSeats) {
        for (int row = 0; row < seats[hallNumber].length; row++) {
            int consecutiveEmptySeats = 0;
            for (int seat = 0; seat < seats[hallNumber][row].length; seat++) {
                if (seats[hallNumber][row][seat] == 0) {
                    consecutiveEmptySeats++;
                    if (consecutiveEmptySeats == numSeats) {
                        System.out.println("Доступні " + numSeats + " послідовних місць у ряду " + row + " залу " + hallNumber + ".");
                        return true;
                    }
                } else {
                    consecutiveEmptySeats = 0;
                }
            }
        }
        System.out.println("Недостатньо послідовних місць у залі " + hallNumber + ".");
        return false;
    }

    public void printSeatingArrangement(int hallNumber) {
        System.out.println("Схема розміщення місць у залі " + hallNumber + ":");

        System.out.print("  ");
        for (int seat = 0; seat < seats[hallNumber][0].length; seat++) {
            System.out.printf("%3d", seat+1);
        }
        System.out.println();

        for (int row = 0; row < seats[hallNumber].length; row++) {
            System.out.printf("%2d ", row+1);

            for (int seat = 0; seat < seats[hallNumber][row].length; seat++) {
                if (seats[hallNumber][row][seat] == 0) {
                    System.out.print(" O ");
                } else {
                    System.out.print(" 1 ");
                }
            }

            System.out.printf(" %2d", row+1);
            System.out.println();
        }

        System.out.print("  ");
        for (int seat = 0; seat < seats[hallNumber][0].length; seat++) {
            System.out.printf("%3d", seat+1);
        }
        System.out.println();
    }


    public static void main(String[] args) {
        Cinema cinema = new Cinema(5, 10, 20);

        int[] seatsToBook = {5, 6, 7};
        cinema.bookSeats(2, 3, seatsToBook);
        cinema.printSeatingArrangement(2);
        cinema.cancelBooking(2, 3, seatsToBook);
        cinema.checkAvailability(2, 3);
        cinema.bookSingleSeat(2,3,9);
        cinema.printSeatingArrangement(2);
    }
}