package outputs;

public class console {

	// JavaScript/TypeScript | console.log()
	public static void log(Object... inputs) {
		StringBuilder output = new StringBuilder();
		for (Object input : inputs) {
			output.append(input).append(" ");
		}
		System.out.println(output.toString().trim());
	}

	// Python/Swift/R/Lua/Dart | print()
	public static void print(Object... inputs) {
		StringBuilder output = new StringBuilder();
		for (Object input : inputs) {
			output.append(input).append(" ");
		}
		System.out.println(output.toString().trim());
	}

	// C# | console.WriteLine()
	public static void WriteLine(Object... inputs) {
		StringBuilder output = new StringBuilder();
		for (Object input : inputs) {
			output.append(input).append(" ");
		}
		System.out.println(output.toString().trim());
	}

	// C | printf
	public static void printf(Object... inputs) {
		StringBuilder output = new StringBuilder();
		for (Object input : inputs) {
			output.append(input).append(" ");
		}
		// In C, you'd normally need a format string, but we’re just simulating:
		System.out.println(output.toString().trim());
	}

	// C++ | std::cout
	// Just a simulation: “cout()” will print arguments with spaces
	public static void cout(Object... inputs) {
		StringBuilder output = new StringBuilder();
		for (Object input : inputs) {
			output.append(input).append(" ");
		}
		System.out.println(output.toString().trim());
	}

	// PHP | echo
	public static void echo(Object... inputs) {
		StringBuilder output = new StringBuilder();
		for (Object input : inputs) {
			output.append(input).append(" ");
		}
		System.out.println(output.toString().trim());
	}

	// Ruby | puts
	public static void puts(Object... inputs) {
		StringBuilder output = new StringBuilder();
		for (Object input : inputs) {
			output.append(input).append(" ");
		}
		// Ruby’s puts adds a newline by default
		System.out.println(output.toString().trim());
	}

	// R | cat
	public static void cat(Object... inputs) {
		StringBuilder output = new StringBuilder();
		for (Object input : inputs) {
			output.append(input).append(" ");
		}
		// R’s cat doesn’t automatically add a newline unless you include "\n"
		System.out.println(output.toString().trim());
	}

	// Rust/Kolton/Scala | println
	public static void println(Object... inputs) {
		StringBuilder output = new StringBuilder();
		for (Object input : inputs) {
			output.append(input).append(" ");
		}
		System.out.println(output.toString().trim());
	}

}
