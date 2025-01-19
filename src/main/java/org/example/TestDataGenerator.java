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
  public static void handleTestDataGeneratorByUser(Archive list,int books,int magazine){
    for(int i = 0; i <books ; i++) {
      list.add(TestDataGenerator.generateRandomBook());
    }
    for(int i = 0; i <magazine ; i++) {
      list.add(TestDataGenerator.generateRandomMagazine());
    }
  }
  public static void handleBookGenerator(Archive list,int numBooks){
    for(int i = 0; i <numBooks ; i++) {
      list.add(TestDataGenerator.generateRandomBook());
    }
  }
  public static void handleMagazineGenerator(Archive list,int numMagazines){
    for(int i = 0; i <numMagazines ; i++) {
      list.add(TestDataGenerator.generateRandomMagazine());
    }
  }

  public static Books generateRandomBook() {
    long isbnCode = faker.number().randomNumber(6, true);
    String title = faker.book().title();
    String author = faker.book().author();
    String genre = faker.book().genre();
    int publishYear = random.nextInt(1900,2025);
    int numberOfPages = random.nextInt(500) + 50;

    return new Books(isbnCode, title, publishYear, numberOfPages, author, genre);
  }

  public static Magazine generateRandomMagazine() {
    long isbnCode = faker.number().randomNumber(6, true);
    String title = faker.book().title();
    PeriodType type = PeriodType.values()[random.nextInt(PeriodType.values().length)];
    int publishYear = random.nextInt(1900, 2025);
    int numberOfPages = random.nextInt(500) + 50;
    return new Magazine(isbnCode, title, publishYear, numberOfPages, type);
  }
}
