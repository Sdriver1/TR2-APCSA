package outputs;

public class fmt {
	// Go | fmt.Println
		public static void Println(Object... inputs) {
			StringBuilder output = new StringBuilder();
			for (Object input : inputs) {
				output.append(input).append(" ");
			}
			System.out.println(output.toString().trim());
		}
}
