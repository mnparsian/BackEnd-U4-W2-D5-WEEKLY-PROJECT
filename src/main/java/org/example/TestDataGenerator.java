package org.example;

import com.github.javafaker.Faker;

import java.util.Random;

public class TestDataGenerator {
  private static Faker faker = new Faker();
  private static Random random = new Random();

  public static Archive HandleTestDataGenerator(int param) {
    Archive list = new Archive();
    for (int i = 0; i < param; i++) {
      list.add(TestDataGenerator.generateRandomBook());
      list.add(TestDataGenerator.generateRandomMagazine());
    }
    return list;
  }

  public static Books generateRandomBook() {
    long isbnCode = faker.number().randomNumber(6, true);
    String title = faker.book().title();
    String author = faker.book().author();
    String genre = faker.book().genre();
    int publishYear = random.nextInt(2025 - 1900) + 1900;
    int numberOfPages = random.nextInt(500) + 50;

    return new Books(isbnCode, title, publishYear, numberOfPages, author, genre);
  }

  public static Magazine generateRandomMagazine() {
    long isbnCode = faker.number().randomNumber(6, true);
    String title = faker.book().title();
    PeriodType type = PeriodType.values()[random.nextInt(PeriodType.values().length)];
    int publishYear = random.nextInt(1900, 2025) + 1900;
    int numberOfPages = random.nextInt(500) + 50;
    return new Magazine(isbnCode, title, publishYear, numberOfPages, type);
  }
}
