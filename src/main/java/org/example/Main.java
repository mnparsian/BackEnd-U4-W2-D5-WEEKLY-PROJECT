package org.example;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import static org.example.TestDataGenerator.HandleTestDataGenerator;

public class Main {

  public static final Logger logger = LoggerFactory.getLogger(Main.class);
  public static Scanner scan = new Scanner(System.in);
  public static Archive myArchive = HandleTestDataGenerator(5);

  public static void main(String[] args) {

    while (true) {
      try {
        System.out.println(
            """
                -----------------------------------------------------------------
                Select Option:
                1-Add to library
                2-Show items
                3-Search items
                4-Modify
                5-Delete
                6-Statistic
                7-Save to file
                0-Exit
                -----------------------------------------------------------------
                """);
        System.out.print("Enter your choice: ");
        int option = scan.nextInt();
        scan.nextLine();

        if (option == 0) {
          logger.info("Exiting...");
          break;
        } else if (option == 1) {
          handleAdd();
        } else if (option == 2) {
          myArchive.printAll();
          continue;
        } else if (option == 3) {
          handleSearch();
        } else if (option == 4) {
          handleModify();
        } else if (option == 5) {
          handleDelete();
        } else if (option == 6) {
          handleStatistic();
        } else if(option == 7){
          handleSave();
        }
        else {
          logger.error("Invalid Number! Please select a number in range (1.7) or 0 to exit");
        }

      } catch (InputMismatchException e) {
        logger.error("Invalid input! Just select a number between 1, 7, or select 0 for exit!");
        scan.nextLine();
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    }
  }

  //////////////////////////// ---- ADD HANDLER ----//////////////////////////
  public static void handleAdd() {
    while (true) {
      System.out.println(
          """
            1-Book
            2-Magazine
            0-Back
            """);
      try {
        System.out.print("Enter your choice: ");
        int typeOption = scan.nextInt();
        scan.nextLine();

        if (typeOption == 0) {
          logger.info("Back to main menu");
          break;
        } else if (typeOption == 1 || typeOption == 2) {
          System.out.print("Please insert ISBN of your item (6 digit): ");
          if (!scan.hasNextLong()) {
            logger.error("Invalid ISBN! Please enter a valid number.");
            scan.next();
            continue;
          }
          long isbnCode = scan.nextLong();
          scan.nextLine();

          System.out.print("Insert the title: ");
          String title = scan.nextLine();
          System.out.print("Insert the publish year: ");
          int year = scan.nextInt();
          scan.nextLine();
          System.out.print("Insert the total number of pages: ");
          int pages = scan.nextInt();
          scan.nextLine();

          if (typeOption == 1) {
            System.out.print("Insert the name of author: ");
            String author = scan.nextLine();
            System.out.print("Insert type of genre: ");
            String genre = scan.nextLine();
            Books newBook = new Books(isbnCode, title,year,pages,author,genre);
            myArchive.add(newBook);
            logger.info("Book added successfully!");
          } else if (typeOption == 2) {
            System.out.print("Insert new Period Type (WEEKLY, MONTHLY, SEMESTRALE):");
            try{
              String periodType = scan.nextLine();
              PeriodType newType = PeriodType.valueOf(periodType.toUpperCase());
              Magazine newMagazine = new Magazine(isbnCode, title,year,pages,newType);
              myArchive.add(newMagazine);
              logger.info("Magazine added successfully!");
              }catch (RuntimeException e){
              logger.error("Invalid Input! select between(WEEKLY, MONTHLY, SEMESTRALE)");
            }
          }
        } else {
          logger.error("Invalid Number! Please enter 1, 2, or 0.");
        }
      } catch (InputMismatchException e) {
        logger.error("Invalid input! Please enter a valid number (1, 2, or 0).");
        scan.nextLine();
      }
    }
  }

  //////////////////////////// ---- SEARCH HANDLER ----//////////////////////////
  public static void handleSearch() {
    while (true) {
      System.out.println(
          """
                           --------------------------------------------------------
                           Search by:
                           1-ISBN code
                           2-Title
                           3-Publish year
                           4-Author
                           0-Back
                           """);
      try {
        System.out.print("Enter your choice: ");
        int searchOption = scan.nextInt();
        scan.nextLine();
        if (searchOption == 1) {
          System.out.print("Insert ISBN Code: ");
          long isbn = scan.nextLong();
          scan.nextLine();
          Library item = myArchive.searchByIsbn(isbn);
          if (item != null) System.out.println(item);
          else logger.info("There isn't any book or magazine with this ISBN");
        } else if (searchOption == 2) {
          System.out.print("Insert the title: ");
          String title = scan.nextLine();
          Library item = myArchive.searchedByTitle(title);
          if (item != null) System.out.println(item);
          else logger.info("There isn't any book or magazine with this Title");
        } else if (searchOption == 3) {
          System.out.print("Insert Publish year: ");
          int year = scan.nextInt();
          List<Library> items = myArchive.searchedByYear(year);
          scan.nextLine();
          if (items.isEmpty())
            logger.info("There isn't any book or magazine that piblished in this year");
          else items.forEach(item -> System.out.println(item.toString()));
        } else if (searchOption == 4) {
          System.out.print("Insert name of Author: ");
          String author = scan.nextLine();
          List<Books> items = myArchive.searhcedByAuthor(author);
          if (items.isEmpty()) logger.info("There isn't any book or magazine with this Author");
          else items.forEach(item -> System.out.println(item.toString()));
        } else if (searchOption == 0) {
          break;
        } else {
          logger.error(
              "Invalid Number! Please select a number in range (1. 4) or 0 to Back to main menu");
        }

      } catch (InputMismatchException e) {
        logger.error("Invalid input! Just select a number between 1, 4, or  0 Back to main menu");
        scan.nextLine();
      }
      catch (Exception e) {
        throw new RuntimeException(e);
      }
    }
  }

  //////////////////////////// ---- MODIFY HANDLER ----//////////////////////////
  public static void handleModify() {
    try {
      System.out.print(
          "Insert ISBN code of the item you want to modify: ");
      long isbnCode = scan.nextLong();
      scan.nextLine();
      Library item = myArchive.searchByIsbn(isbnCode);
      if (item != null) {
        System.out.print("Insert new title:");
        String newTitle = scan.nextLine();

        System.out.print("Insert new publish year:");
        int newPublishYear = scan.nextInt();

        System.out.print("Insert new number of pages:");
        int newNumberOfPages = scan.nextInt();
        scan.nextLine();

        //System.out.print("Is this a Book or a Magazine? (1 for Book, 2 for Magazine):");
        //int typeOption = scan.nextInt();
        //scan.nextLine();

        if (item.getArticleType() == ArticleType.BOOK) {
          System.out.print("Insert new author:");
          String newAuthor = scan.nextLine();

          System.out.print("Insert new genre:");
          String newGenre = scan.nextLine();


          myArchive.modifyItem(
              isbnCode, newTitle, newPublishYear, newNumberOfPages, newAuthor, newGenre, null);

          logger.info("Book Modified successfully!");

        } else if (item.getArticleType() == ArticleType.MAGAZINE) {
          System.out.print("Insert new Period Type (WEEKLY, MONTHLY, SEMESTRALE):");
          try{
            String periodType = scan.nextLine();
            PeriodType newType = PeriodType.valueOf(periodType.toUpperCase());

          myArchive.modifyItem(
              isbnCode, newTitle, newPublishYear, newNumberOfPages, null, null, newType);

          logger.info("Magazine Modified successfully!");
          }
          catch (RuntimeException e){
                  logger.error("Invalid Input! select between(WEEKLY, MONTHLY, SEMESTRALE)");
          }
        }
      } else logger.info("There isn't any book or magazine with this ISBN");
    } catch (InputMismatchException e) {
      logger.error("Invalid input!");
      scan.nextLine();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  //////////////////////////// ---- DELETE HANDLER ----//////////////////////////
  public static void handleDelete() {
    try{
      System.out.print("insert the ISBN number: ");
      long isbn = scan.nextLong();
      scan.nextLine();
      Library selected = myArchive.searchByIsbn(isbn);
      if(selected != null){
        System.out.println(selected.toString());
        System.out.println(
                """
                                --------------------------------
                                Are you sure you want to delete?
                                1-Yes
                                2-No
                                """);
        int deleteOption = scan.nextInt();
        scan.nextLine();
        if (deleteOption == 1) {
          myArchive.remove(isbn);
          logger.info("The item deleted!");
        } else {
          logger.info("back to main menu");
        }
      }
      else logger.error("Item with this ISBN code not found!");


    }
    catch (InputMismatchException e) {
      logger.error("Invalid input! Just insert the valid ISBN code");
      scan.nextLine();}
      catch (RuntimeException e) {
      logger.error("Invalid input!");
    }
  }

  //////////////////////////// ---- STATISTIC HANDLER ----//////////////////////////
  public static void handleStatistic() {
    while (true) {
      try{
        System.out.println(
                """
                             --------------------------------------------
                             1-Total Number of Books
                             2-Total Number of Magazine
                             3-Find the book with Max pages
                             4-Average of pages
                             0-Back
                             """);
        System.out.print("Enter your choice: ");
        int selectOption = scan.nextInt();
        scan.nextLine();
        if (selectOption == 0) {
          logger.info("Back to main menu");
          break;
        } else if (selectOption == 1) {
          if (myArchive != null) {
            System.out.println("Total number of books: " + myArchive.totalBooks());
          } else {
            logger.error("The archive is empty!");
          }
        } else if (selectOption == 2) {
          if (myArchive != null) {
            System.out.println("Total number of magazines: " + myArchive.totalMagazine());
          } else {
            logger.error("The archive is empty!");
          }
        } else if (selectOption == 3) {
          System.out.println(myArchive.maxPage());

        } else if (selectOption == 4) {
          System.out.println("Average of pages: " + myArchive.avgPage());
        }
        else logger.error("Please select number between 1,4 or 0 to back to main menu");
      }
      catch (InputMismatchException e) {
        logger.error("Invalid input! Just insert the valid Number");
        scan.nextLine();}

    }
  }
  //////////////////////////// ---- SAVE TO FILE HANDLER ----//////////////////////////
  public static void handleSave(){
    while (true){
      try{
        System.out.println(
            """
            1-Save all items
            2-Save Books
            3-Save Magazines
            0-Back
            """);
        System.out.print("Enter your choice: ");
        int selectOption = scan.nextInt();
        scan.nextLine();
        if (selectOption == 0) {
          logger.info("Back to main menu");
          break;
        } else if (selectOption == 1) {
          try {
            myArchive.saveListToFile("AllItems");
          } catch (IOException e) {
            System.err.println("Error while saving the file: " + e.getMessage());
          }
        }
        else if(selectOption == 2){
          try {
            myArchive.saveBooksToFile("filteredBooks");
          } catch (IOException e) {
            System.err.println("Error while saving books: " + e.getMessage());
          }
        }
        else if(selectOption == 3){
          try {
            myArchive.saveMagazinesToFile("filteredMagazines");
          } catch (IOException e) {
            System.err.println("Error while saving magazines: " + e.getMessage());
          }
        }
        else logger.error("Please select number between 1,4 or 0 to back to main menu");

      } catch (InputMismatchException e) {
        logger.error("Invalid input! Just insert the valid Number");
        scan.nextLine();}
    }
  }
}
