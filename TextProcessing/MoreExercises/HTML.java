/*
Task:
    You will receive 3 lines of input. On the first line, you will receive a title of an article.
    On the next line, you will receive the content of that article.
    On the next n lines, until you receive "end of comments",
    you will get the comments about the article. Print the whole information in HTML format.
    The title should be in "h1" tag (<h1></h1>); the content in article tag (<article></article>);
    each comment should be in div tag (<div></div>). For more clarification, see the example below
Examples:
    SoftUni Article
    Some content of the SoftUni article
    some comment
    more comment
    last comment
    end of comments
    ->
    <h1>
        SoftUni Article
    </h1>
    <article>
        Some content of the SoftUni article
    </article>
    <div>
        some comment
    </div>
    <div>
        more comment
    </div>
    <div>
        last comment
    </div>

    The Reckoning
    John Grishams The Reckoning is the master storytellers most powerful, surprising, and accomplished novel yet.
    some comment1
    more comment2
    last comment3
    end of comments
    ->
    <h1>
        The Reckoning
    </h1>
    <article>
        John Grishams The Reckoning is the master storytellers most powerful, surprising, and accomplished novel yet.
    </article>
    <div>
        some comment1
    </div>
    <div>
        more comment2
    </div>
    <div>
        last comment3
    </div>
 */
package TextProcessing.MoreExercises;

import java.util.Scanner;

public class HTML {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String title = scanner.nextLine();
        String content = scanner.nextLine();
        StringBuilder divs = new StringBuilder();
        String input = scanner.nextLine();

        while (!input.equals("end of comments")) {
            divs.append(String.format("<div>\n%s\n</div>\n", input));
            input = scanner.nextLine();
        }

        System.out.printf(
            "<h1>\n%s\n</h1>\n<article>\n%s\n</article>\n%s",
            title, content, divs
        );
    }
}