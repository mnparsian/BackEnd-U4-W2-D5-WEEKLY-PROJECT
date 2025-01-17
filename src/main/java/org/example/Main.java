package org.example;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

import static org.example.TestDataGenerator.HandleTestDataGenerator;

/** Hello world! */
public class Main {

  public static final Logger logger = LoggerFactory.getLogger(Main.class);

  public static void main(String[] args) {

    Archive myArchive = HandleTestDataGenerator(5);
    Scanner scan = new Scanner(System.in);

    while (true) {

      System.out.println(
          """
                Select Option:
                1-Add to library
                2-Show items
                3-Search items
                4-Modify
                5-Delete
                6-Statistic
                0-Exit
                """);
      int option = scan.nextInt();
      try {
        if (option == 0) {
          logger.info("Exiting...");
          break;
        } else if (option == 1) {System.out.println( """ 
                                                    1-Book
                                                    2-Magazine
                                                    """);
          int typeOption = scan.nextInt();
          System.out.println("Please inset ISBN of you item: ");
          long isbnCode = scan.nextLong();
          System.out.println("insert thi title: ");
          String title = scan.nextLine();
          if(typeOption == 1){
            Books newBook = new Books(isbnCode,title);
            myArchive.add(newBook);
          }
          else if(typeOption == 2){
            Magazine newMagazin = new Magazine(isbnCode,title);
            myArchive.add(newMagazin);

          }
        }

      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    }
  }
}
