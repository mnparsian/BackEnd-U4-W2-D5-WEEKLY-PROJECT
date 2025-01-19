package org.example;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ObjectUtils;

import java.awt.print.Book;
import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Archive {
  private Set<Library> articles;

  public Archive() {
    this.articles = new HashSet<Library>();
  }

  public void add(Library article) {
    this.articles.add(article);
  }

  public void remove(long isbnCode) {
    articles.removeIf(article -> article.getisbnCode() == isbnCode);
  }

  public Library searchByIsbn(long isbnCode) {
    return articles.stream()
        .filter(article -> article.getisbnCode() == isbnCode)
        .findFirst()
        .orElse(null);
  }

  public Library searchedByTitle(String title) {
    return articles.stream()
        .filter(article -> article.getTitle().equalsIgnoreCase(title))
        .findFirst()
        .orElse(null);
  }

  public List<Library> searchedByYear(int year) {
    return articles.stream().filter(article -> article.getPublishYear() == year).toList();
  }

  public List<Books> searhcedByAuthor(String author) {
    return articles.stream()
        .filter(article -> article instanceof Books)
        .map(article -> (Books) article)
        .filter(book -> book.getAuthor().equalsIgnoreCase(author))
        .toList();
  }

  public long totalBooks() {
    return articles.stream().filter(article -> article instanceof Books).count();
  }

  public long totalMagazine() {
    return articles.stream().filter(article -> article instanceof Magazine).count();
  }

  public Library maxPage() {
    return articles.stream().max(Comparator.comparing(Library::getNumberOfPages)).orElse(null);
  }

  public double avgPage() {
    return articles.stream().mapToInt(Library::getNumberOfPages).average().orElse(0.0);
  }

  public void printAll() {
    articles.forEach(article -> System.out.println(article.toString()));
  }

  public void modifyItem(
      long isbnCode,
      String newTitle,
      int newPublishYear,
      int newNumberOfPages,
      String newAuthor,
      GenreType newGenre,
      PeriodType newType) {
    Library foundItem = searchByIsbn(isbnCode);
    if (foundItem != null) {
      foundItem.setTitle(newTitle);
      foundItem.setPublishYear(newPublishYear);
      foundItem.setNumberOfPages(newNumberOfPages);
      if (foundItem instanceof Books) {
        Books book = (Books) foundItem;
        book.setAuthor(newAuthor);
        try {
          book.setGenre(newGenre);
        } catch (IllegalArgumentException e) {
          System.out.println("Invalid genre! No changes were made to the genre.");
        }
      } else if (foundItem instanceof Magazine) {
        Magazine magazine = (Magazine) foundItem;
        magazine.setType(newType);
      }
    } else {
      System.out.println("Item not found.");
    }
  }

  public  void saveListToFile(String fileName) throws IOException {
    File file = new File("./saved_files/" + fileName + ".txt");
    FileUtils.writeLines(file,articles, true);
    System.out.println("File saved successfully at: " + file.getAbsolutePath());
  }

  public  void saveObjectToFile(String article, String fileName) throws IOException {
    File file = new File("./saved_files/" + fileName + ".txt");
    FileUtils.writeStringToFile(file, article, "UTF-8", true);
  }

  public String filterBooks() {
    return articles.stream().filter(article -> article instanceof Books).toString();
  }

  public void saveBooksToFile(String fileName) throws IOException {

    List<Books> books =
        articles.stream()
            .filter(article -> article instanceof Books)
            .map(article -> (Books) article)
            .toList();

    File file = new File("./saved_files/" + fileName + ".txt");
    File directory = new File("./saved_files");

    if (!directory.exists()) {
      directory.mkdirs();
    }

    FileUtils.writeLines(file, books);
    System.out.println("Books saved successfully in: " + file.getAbsolutePath());
  }


  public void saveMagazinesToFile(String fileName) throws IOException {

    List<Magazine> magazines = articles.stream()
            .filter(article -> article instanceof Magazine)
            .map(article -> (Magazine) article)
            .toList();


    File file = new File("./saved_files/" + fileName + ".txt");
    File directory = new File("./saved_files");


    if (!directory.exists()) {
      directory.mkdirs();
    }


    FileUtils.writeLines(file, magazines);
    System.out.println("Magazines saved successfully in: " + file.getAbsolutePath());
  }

}
