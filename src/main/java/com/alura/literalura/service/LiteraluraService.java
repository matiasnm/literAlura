package com.alura.literalura.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import com.alura.literalura.dto.BookDto;
import com.alura.literalura.dto.SearchDto;
import com.alura.literalura.enums.Languages;
import com.alura.literalura.model.Book;
import com.alura.literalura.model.Author;
import com.alura.literalura.repository.AuthorRepository;
import com.alura.literalura.repository.BookRepository;
import com.alura.literalura.service.PrintService.Color;

public class LiteraluraService {

    private static final String apiUrl = "https://gutendex.com/books/";

    private ApiConsumer apiConsumer;
    private PrintService printService;
    private JsonConversor jsonConversor;
	private ScannerService scannerService;
    private BookRepository bookRepository;
    private AuthorRepository authorRepository;

    public LiteraluraService(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        setLiteraluraService();
    }

    private void setLiteraluraService() {
        apiConsumer = new ApiConsumer();
        printService = new PrintService();
        jsonConversor = new JsonConversor();
        scannerService = new ScannerService();
    } 

    public void init() {
        cls();
        showBanner();
        String input = "";
        do {
            System.out.print("\n\n");
            showMenu();
            input = scannerService.getUserInput("Ingrese una opción: ");
            switch (input) {
            case "1" -> buscarLibrosPorTituloYPersistir();
            case "2" -> listarLibrosRegistrados();
            case "3" -> listarAutoresRegistrados();
            case "4" -> listarAutoresVivosPorAno();
            case "5" -> listarLibrosPorIdioma();
            case "6" -> mostrarLibrosPorAutor();
            case "0" -> exit();
            default -> printService.cPrint("ERROR: Opción inválida.", Color.RED);
            }
        } while (!input.equals("0"));
    }

    public void showMenu() {
        var menu = """
            1 - Buscar libros
            2 - Listar libros registrados
            3 - Listar autores registrados
            4 - Listar autores vivos por año
            5 - Listar libros por idioma
            6 - Buscar libros por autor

            0 - Salir
            """;
            printService.cPrintln(menu, Color.BLUE);
    }

    private void cls() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private void showBanner() {
        var banner = """
            ,--.   ,--.  ,--.                  ,---.  ,--.                       
            |  |   `--',-'  '-. ,---. ,--.--. /  O  \\ |  |,--.,--.,--.--.,--,--. 
            |  |   ,--.'-.  .-'| .-. :|  .--'|  .-.  ||  ||  ||  ||  .--' ,-.  | 
            |  '--.|  |  |  |  \\   --.|  |   |  | |  ||  |'  ''  '|  |  \\ '-'  | 
            `-----'`--'  `--'   `----'`--'   `--' `--'`--' `----' `--'   `--`--'                                                          
            """;
        printService.cPrint(banner, Color.PURPLE);
    }

    private void exit() {
        scannerService.close();
        printService.cPrintln("Hasta la próxima!", Color.RED);
        System.exit(0);
    }

    private void buscarLibrosPorTituloYPersistir(){
        String input = scannerService.getUserInput("Ingrese el título o autor: ");
        input = input.replace(" ", "%20");
        String json = apiConsumer.getApiRequest(apiUrl + "?search=" + input).replace(" ","+");

        if (json == null) {
            printService.cPrintln("Sin resultados.", Color.RED);
        } else {
            SearchDto searchDto = jsonConversor.getJson(json, SearchDto.class);

            Optional<BookDto> optionalBookDto = searchDto.results().stream()
                .findFirst();

            if (optionalBookDto.isPresent()) {
                
                BookDto bookDto = optionalBookDto.get();
                printService.cPrintln(bookDto.toString(), Color.PURPLE);

                Book newBook = new Book(bookDto);
                
                if (!bookDto.authors().isEmpty()) {
                    Author author = new Author(bookDto.authors().get(0));
                    authorRepository.save(author);
                    newBook.setAuthors(author);
                }
                bookRepository.save(newBook);
            }
        }
    }

    private void listarLibrosRegistrados() {
        List<Book> books = bookRepository.findAll();
        mostrarResultados(books);
    }

    private void listarAutoresRegistrados() {
        List<Author> authors = authorRepository.findAll();
        mostrarResultados(authors);
    }

    private void listarAutoresVivosPorAno() {
        String input = scannerService.getUserInput("Ingrese el año: ");
        Integer year = null;
        try {
            year = Integer.parseInt(input);
        } catch (NumberFormatException ex) {
            printService.cPrint("ERROR: Ingrese un número válido", Color.RED);
            return;
        }
        List<Author> authors = authorRepository.findByLivingInYear(year);
        mostrarResultados(authors);
    }

    private void listarLibrosPorIdioma() {
        List<Languages> languages = bookRepository.listLanguages();
        if (!languages.isEmpty()) {
            int cols = 0;
            for (Languages language : languages) {
                printService.cPrint("- " + language, Color.BLUE);
                cols ++;
                if (cols % 3 == 0) {
                    printService.cPrintln("", Color.BLUE);
                } else {
                    printService.cPrintln("\t", Color.BLUE);
                }
            }
        } else {
            printService.cPrintln("No hay idiomas registrados.", Color.RED);
            return;
        }

        String input = scannerService.getUserInput("Ingrese un idioma: ");
        Languages language;
        try {
            language = Languages.valueOf(input.toLowerCase());
        } catch (IllegalArgumentException e) {
            printService.cPrintln("El idioma ingresado no es válido.", Color.RED);
            return;
        }

        List<Book> books = bookRepository.findByLanguages(language);
        mostrarResultados(books);
    }

    private void mostrarLibrosPorAutor() {
        List<Author> optionalAuthors = authorRepository.findAll();
        if (!optionalAuthors.isEmpty()) {
            int cols = 0;
            for (Author author : optionalAuthors) {
                printService.cPrint("- " + author.getName(), Color.BLUE);
                cols ++;
                if (cols % 3 == 0) {
                    printService.cPrintln("", Color.BLUE);
                } else {
                    printService.cPrintln("\t", Color.BLUE);
                }
            }
        } else {
            printService.cPrintln("No hay autores registrados.", Color.RED);
            return;
        }

        String input = scannerService.getUserInput("Ingrese el autor: ");
        Optional<Author> optionalAuthor = authorRepository.findByNameContainingIgnoreCase(input);
        if (optionalAuthor.isPresent()) {
            Author author = optionalAuthor.get();
            List<Book> books = bookRepository.findByAuthors(author);
            mostrarResultados(books);
        } else {
            printService.cPrintln("Autor no registrado.", Color.RED);
            return;
        }
    }

    private <T> void mostrarResultados(Collection<T> t) {
        if (t.isEmpty()){
            printService.cPrintln("Sin resultados", Color.RED);
        } else {
            for (T entity : t) {
                printService.cPrintln(entity.toString(), Color.GREEN);
            }
        }
    }
}