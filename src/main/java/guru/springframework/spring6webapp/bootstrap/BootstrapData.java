package guru.springframework.spring6webapp.bootstrap;

import guru.springframework.spring6webapp.domain.Author;
import guru.springframework.spring6webapp.domain.Book;
import guru.springframework.spring6webapp.domain.Publisher;
import guru.springframework.spring6webapp.repositories.AuthorRepository;
import guru.springframework.spring6webapp.repositories.BookRepository;
import guru.springframework.spring6webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {
    private final AuthorRepository authorRepository;

    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
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


        Publisher publisher = new Publisher();
        publisher.setAddress("India");
        publisher.setCity("Delhi");
        publisher.setZip("110092");
        publisher.setPublisherName("Eric Evans");
        publisher.setState("Delhi");
        Publisher savedPublisher = publisherRepository.save(publisher);
        System.out.println("Publisher count " + publisherRepository.count());
        System.out.println("Author count " + authorRepository.count());
        System.out.println("Book count " + bookRepository.count());
    }
}
