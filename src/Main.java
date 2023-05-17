import util.TextUtil;

public class Main {
    public static void main(String[] args) {
        String text = "Sed ut perspiciatis unde omnis iste natus error sit voluptatem\n" +
                "accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab\n" +
                "illo inventore veritatis et quasi architecto beatae vitae dicta sunt\n" +
                "explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut\n" +
                "odit aut fugit, sed quia consequuntur magni dolores eos qui ratione\n" +
                "voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum\n" +
                "quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam\n" +
                "eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat\n" +
                "voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam\n" +
                "corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur?\n" +
                "Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse\n" +
                "quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo\n" +
                "voluptas nulla pariatur?";

        // Prvi zadatak
        new Thread(() -> {
            System.out.println("Prvi zadatak: " + TextUtil.randomizeInnerChars(text));
        }).start();

        // Drugi zadatak
        new Thread(() -> {
            System.out.println("Drugi zadatak: " + TextUtil.randomizeInnerChars(text));
        }).start();

        // Treci zadatak
        new Thread(() -> {
            System.out.println("Treci zadatak: " + TextUtil.reverseWordsInSentence(text));
        }).start();

        // Cetvrti zadatak
        new Thread(() -> {
            System.out.println("Cetvrti zadatak: " + TextUtil.reverseSentencesInText(text));
        }).start();

        // Peti zadatak a)
        new Thread(() -> {
            System.out.println("Peti zadatak, a), broj samoglasnika u tekstu: " + TextUtil.countVowelsInText(text));
        }).start();

        // Peti zadatak b)
        new Thread(() -> {
            System.out.println("Peti zadatak, b), broj samoglasnika po rečenici: " + TextUtil.countVowelsInEachSentence(text));
        }).start();
    }
}