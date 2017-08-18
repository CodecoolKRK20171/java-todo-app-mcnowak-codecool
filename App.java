/**
 * Class representing the TodoApp application.
 * It is the main entry point for this program.
 */
import java.util.Scanner;
import java.lang.IndexOutOfBoundsException;
import java.lang.NumberFormatException;

public class App{
    private Scanner inputGetter;
    private TodoList todoList;

    public App() {
        this.inputGetter = new Scanner(System.in);
        this.todoList = new TodoList();
    }

    private void printMenu() {
        String menu = "\nMenu:\n 1. Add item\n 2. Mark item as done\n 3. Remove all done items\n 4. Exit";
        System.out.println(menu);
    }

    private int askUserIndex() {
        String option;
        int index;

        System.out.println("\nType the item number: ");
        option = inputGetter.nextLine();
        index = Integer.parseInt(option);

        return index;
    }

    private String askUserContent() {
        String option;
        System.out.println("\nType the content of the item to do: ");
        option = inputGetter.nextLine();
        return option;
    }

    private String askUserChoice() {
        String option;
        System.out.println("\nChoose an option: ");
        option = inputGetter.nextLine();
        return option;
    }

    private void addItem() {

        String content = this.askUserContent();
        this.todoList.addItem(new TodoItem(content));
    }

    private void markItem() {

        int index = this.askUserIndex();
        this.todoList.itemList.get(index).markAsDone();
    }

    private void archive() {
        for (int i = this.todoList.itemList.size() - 1; i > -1; i--) {
            if (this.todoList.itemList.get(i).getIsDone()) {
                this.todoList.itemList.remove(i);
            }
        }
    }

    public static void main(String[] args) {
        boolean exit = false;
        String option;
        App application = new App();

        while (!exit) {
            System.out.println(application.todoList.getStringTodoList());
            application.printMenu();

            option = application.askUserChoice();
            switch(option) {
                case "1":
                    application.addItem();
                    break;

                case "2":
                try {
                    application.markItem();
                } catch (IndexOutOfBoundsException | NumberFormatException e) {
                    System.out.println("There is no information with such number!");
                }

                    break;

                case "3":
                    application.archive();
                    break;

                case "4":
                    exit = true;
                    break;

                default:
                    System.out.println("There is no such option");
                    break;

            }
        }
    }
}
