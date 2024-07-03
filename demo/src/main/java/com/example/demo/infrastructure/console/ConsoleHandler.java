package com.example.demo.infrastructure.console;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.demo.application.books.services.BooksService;
import com.example.demo.application.persons.services.PersonsService;

@Component
public class ConsoleHandler implements CommandLineRunner {
    @Autowired
    private BooksService booksService;
    @Autowired
    private PersonsService personsService;

    private static Map<String, String> languages = new HashMap<>();
    static {
        languages.put("spanish", "es");
        languages.put("english", "en");
        languages.put("french", "fr");
        languages.put("german", "de");
        languages.put("italian", "it");
        languages.put("portuguese", "pt");
        languages.put("russian", "ru");
    }


    private static Logger logger = LoggerFactory
            .getLogger(ConsoleHandler.class);
    Scanner scanner = new Scanner(System.in);

    private void menuHandler() {
        System.out.println("Opciones de menu");
        System.out.println("1. Libros");
        System.out.println("2. Personas");
        String command = scanner.nextLine();
        try {
            switch (command) {
                case "1":
                    booksHandler();
                    break;
                case "2":
                    personsHandler();
                    break;
                default:
                    System.out.println("Comando no reconocido");
                    break;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void booksHandler() {
        System.out.println("Opciones de libros");
        System.out.println("1. Listar libros");
        System.out.println("2. Buscar libro por titulo");
        System.out.println("3. Buscar libro por autor");
        System.out.println("4. Buscar libro por id");
        System.out.println("5. Buscar libros por idioma");

        String command = scanner.nextLine();
        switch (command) {
            case "1":
                System.out.println("Listando libros");
                System.out.println(new JSONArray(booksService.getAllBooks()).toString());
                break;
            case "2":
                System.out.println("Buscar libro por titulo");
                System.out.println("Introduce el titulo del libro");
                String title = scanner.nextLine();
                System.out.println(new JSONArray(booksService.getBookByTitle(title)).toString());
                break;
            case "3":
                System.out.println("Buscar libro por autor");
                System.out.println("Introduce el autor del libro");
                String author = scanner.nextLine();
                System.out.println(new JSONArray(booksService.getBooksByAuthor(author)).toString());
                break;
            case "4":
                System.out.println("Buscar libro por id");
                System.out.println("Introduce el id del libro");
                Long id = Long.parseLong(scanner.nextLine());
                System.out.println(new JSONObject(booksService.getBookById(id)).toString());
                break;
            case "5":
                System.out.println("Buscar libros por idioma");
                System.out.println("Introduce el idioma del libro");
                String language = scanner.nextLine();
                System.out.println(new JSONArray(booksService.getBooksByLanguage(languages.get(language))).toString());
                break;
            default:
                System.out.println("Comando no reconocido");
                break;
        }

    }

    private void personsHandler() {
        System.out.println("Opciones de personas");
        System.out.println("1. Listar personas");
        System.out.println("2. Buscar personas vivas en un año");

        String command = scanner.nextLine();

        switch (command) {
            case "1":
                System.out.println("Listando personas");
                System.out.println(new JSONArray(personsService.getAllPersons()).toString());
                break;
            case "2":
                System.out.println("Buscar personas vivas en un año");
                System.out.println("Introduce el año");
                Integer year = scanner.nextInt();
                System.out.println(new JSONArray(personsService.getPersonAliveByYear(year)).toString());
                break;
            default:
                break;
        }
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("ConsoleHandler.run()");
        System.out.println("Bienvenido a la consola de la biblioteca");

        while (true) {
            try {
                menuHandler();
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

}
