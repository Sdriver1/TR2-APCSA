public class console {

    public static void log(Object... inputs) {
    	 StringBuilder output = new StringBuilder();
         for (Object input : inputs) {
             output.append(input).append(" ");
         }
         System.out.println(output.toString().trim());
    }
}
