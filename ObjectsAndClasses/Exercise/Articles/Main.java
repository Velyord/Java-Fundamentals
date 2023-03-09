/*
Task:
    Create an article class with the following properties:
    • Title – a string
    • Content – a string
    • Author – a string
    The class should have a constructor and the following methods:
    • Edit (new content) – change the old content with the new one
    • ChangeAuthor (new author) – change the author
    • Rename (new title) – change the title of the article
    • override ToString – print the article in the following format:
    "{title} - {content}: {author}"
    Write a program that reads an article in the following format "{title}, {content}, {author}". On the next line, you will get the number n. On the next n lines, you will get one of the following commands:
    • "Edit: {new content}"
    • "ChangeAuthor: {new author}"
    • "Rename: {new title}".
    At the end, print the final article.
Examples:
    some title, some content, some author
    3
    Edit: better content
    ChangeAuthor:  better author
    Rename: better title
    ->
    better title - better content: better author

    Holy Ghost, content, John Sandford
    3
    ChangeAuthor:  Mitch Albom
    ChangeAuthor:  better author
    ChangeAuthor:  Kim Heacox
    ->
    Holy Ghost - content:  Kim Heacox
 */
package ObjectsAndClasses.Exercise.Articles;

import static ObjectsAndClasses.Exercise.Articles.NumberValidator.setValue;
import static ObjectsAndClasses.Exercise.Articles.StringValidator.setValue;
import static java.lang.System.out;

public class Main {
    public static void main(String[] args) {
        String[] input = setValue().split(", ");
        String title = input[0];
        String content = input[1];
        String author = input[2];
        Article article = new Article(title, content, author);
        int editionsCount = setValue(0, Integer.MAX_VALUE);

        editArticle(article, editionsCount);
        printArticle(article);
    }

    private static void printArticle(Article article) {
        out.printf(
                "%s-%s:%s",
                article.getTitle(), article.getContent(), article.getAuthor()
        );
    }

    private static void editArticle(Article article, int editionsCount) {
        for (int i = 1; i <= editionsCount; i++) {
            String[] editionInput = setValue().split("\\s+");
            String editionType = editionInput[0];

            switch (editionType) {
                case "Edit:":         article.Edit(edition);         break;
                case "ChangeAuthor:": article.ChangeAuthor(edition); break;
                case "Rename:":       article.Rename(edition);       break;
                default: /* do nothing */ break;
            }
        }
    }
}
