package org.example;

import org.apache.commons.lang3.ObjectUtils;

import java.awt.print.Book;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class Archive {
  private Set<Library> articles;

  public Archive() {
    this.articles = new HashSet<Library>();
  }

  public void add(Library article){
    this.articles.add(article);
  }

  public void remove(long isbnCode) {
    articles.removeIf(article -> article.getisbnCode() == isbnCode);
    }

  public Library searchByIsbn(long isbnCode){
    return articles.stream().filter(article-> article.getisbnCode() == isbnCode).findFirst().orElse(null);
  }
  public Library searchedByTitle(String title){
    return articles.stream().filter(article->article.getTitle().equalsIgnoreCase(title)).findFirst().orElse(null);
  }
  public List<Library> searchedByYear(int year){
    return articles.stream().filter(article->article.getPublishYear() == year).toList();
  }
  public List<Books> searhcedByAuthor(String author){
    return articles.stream().filter(article-> article instanceof Books).map(article-> (Books) article).filter(book->book.getAuthor().equalsIgnoreCase(author)).toList();

  }
  public long totalBooks(){
    return articles.stream().filter(article-> article instanceof Books).count();
  }
  public long totalMagazine(){
    return articles.stream().filter(article->article instanceof Magazine).count();
  }
  public Library maxPage(){
    return articles.stream().max(Comparator.comparing(Library::getNumberOfPages)).orElse(null);
  }
  public double avgPage(){
    return articles.stream().mapToInt(Library::getNumberOfPages).average().orElse(0.0);
  }
  public void printAll(){
    articles.forEach(article -> System.out.println(article.toString()));
  }
}
