package org.example;

public class Books extends Library {
    private String author;
    private GenreType genre;

    public Books(long isbnCode,String title, String author, GenreType genre) {
        super(isbnCode,title);
        this.author = author;
        this.genre = genre;
        this.articleType = ArticleType.BOOK;
    }

    public Books(long isbnCode, String title, int publishYear, int numberOfPages, String author, GenreType genre) {
        super(isbnCode, title, publishYear, numberOfPages);
        this.author = author;
        this.genre = genre;
        this.articleType = ArticleType.BOOK;
    }
    public Books(long isbnCode,String title) {
        super(isbnCode,title);
        this.articleType = ArticleType.BOOK;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public GenreType getGenre() {
        return genre;
    }

    public void setGenre(GenreType genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Books: " +
                super.toString() +
                "author='" + author + '\'' +
                ", genre='" + genre + '\'' ;
    }
}
