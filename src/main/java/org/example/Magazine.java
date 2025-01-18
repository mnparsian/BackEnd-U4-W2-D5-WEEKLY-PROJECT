package org.example;

public class Magazine extends Library{
    private PeriodType type;

    public Magazine(long isbnCode,String title) {
        super(isbnCode,title);
        this.articleType = ArticleType.MAGAZINE;
    }
    public Magazine(long isbnCode,String title, PeriodType type) {
        super(isbnCode,title);
        this.type = type;
        this.articleType = ArticleType.MAGAZINE;
    }

    public Magazine(long isbnCode, String title, int publishYear, int numberOfPages, PeriodType type) {
        super(isbnCode, title, publishYear, numberOfPages);
        this.type = type;
        this.articleType = ArticleType.MAGAZINE;
    }

    public PeriodType getType() {
        return type;
    }

    public void setType(PeriodType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Magazine: " +
                super.toString() +
                "type=" + type;
    }
}
