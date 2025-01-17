package org.example;

public class Library {
    protected long isbnCode;
    protected String title;
    protected int publishYear;
    protected int numberOfPages;
    protected ArticleType articleType;

    public Library(long isbnCode, String title) {
        this.title = title;
        this.isbnCode = isbnCode;
    }

    public Library(long isbnCode, String title, int publishYear, int numberOfPages) {
        this.isbnCode = isbnCode;
        this.title = title;
        this.publishYear = publishYear;
        this.numberOfPages = numberOfPages;
    }

    public long getisbnCode() {
        return isbnCode;
    }

    public void setisbnCode(long isbnCode) {
        this.isbnCode = isbnCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(int publishYear) {
        this.publishYear = publishYear;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public ArticleType getArticleType() {
        return articleType;
    }

    @Override
    public String toString() {
        return " " +
                "codiceIsbn = " + isbnCode +
                ", title = '" + title + '\'' +
                ", publishYear = " + publishYear +
                ", numberOfPages = " + numberOfPages +
                ' ';
    }
}




