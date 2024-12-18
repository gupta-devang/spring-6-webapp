package guru.springframework.spring6webapp.bootstrap;

import guru.springframework.spring6webapp.domain.Author;
import guru.springframework.spring6webapp.domain.Book;
import guru.springframework.spring6webapp.repositories.AuthorRepository;
import guru.springframework.spring6webapp.repositories.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {
    private final AuthorRepository authorRepository;

    private final BookRepository bookRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author eric = new Author();
        eric.setFirstName("Eric");
        eric.setLastName("Evans");

        Book ddd = new Book();
        ddd.setTitle("domain driven design");
        ddd.setIsbn("123456");
        Book dddSave = bookRepository.save(ddd);
        eric.getBooks().add(dddSave);
        Author ericSaved = authorRepository.save(eric);

        Author rod = new Author();
        rod.setFirstName("Rod");
        rod.setLastName("Johnson");
        Book noEjb = new Book();
        noEjb.setTitle("J2EE development without EJB");
        noEjb.setIsbn("54757585");

        Book noEJBSaved = bookRepository.save(noEjb);
        rod.getBooks().add(noEJBSaved);
        Author rodSaved = authorRepository.save(rod);
        System.out.println("Author count " + authorRepository.count());
        System.out.println("Book count " + bookRepository.count());
    }
}
