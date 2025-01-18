package org.example;

public class Books extends Library {
    private String author;
    private String genre;

    public Books(long isbnCode,String title, String author, String genre) {
        super(isbnCode,title);
        this.author = author;
        this.genre = genre;
        this.articleType = ArticleType.BOOK;
    }

    public Books(long isbnCode, String title, int publishYear, int numberOfPages, String author, String genre) {
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

    @Override
    public String toString() {
        return "Books: " +
                super.toString() +
                "author='" + author + '\'' +
                ", genre='" + genre + '\'' ;
    }
}
