package fileManip;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileManip {

	public static void main(String[] args) throws IOException {
		Path p1 = Paths.get("C:\\Users\\jer6093\\Downloads\\Backup\\Backup\\SPEED");
		Path p2 = Paths.get("C:\\Users\\jer6093\\Downloads\\Backup\\Backup\\SPEED\\SPEED_20171102_1of10.txt.txt");
		List<File> direct;
		ArrayList<Path> files = new ArrayList<Path>();
		
		System.out.printf("getFileName: %s%n", p2.getFileName());
		System.out.printf("getFileName: %s%n", p1.getFileName());
		System.out.printf("getFileName: %s%n", p1.getRoot());
		
		if(Files.isRegularFile(p2) & Files.isReadable(p2) & Files.isExecutable(p2)) {
			System.out.println("nice");
		}
		else {
			System.out.println("not nice");
		}
		
		PathMatcher match = FileSystems.getDefault().getPathMatcher("glob:*.txt");
		
		Path dotTxt = p2.getFileName();
		
		if(match.matches(dotTxt)) {
			System.out.println(dotTxt);
		}
		else {
			System.out.println("lol");
		}
		try ( Stream<Path> paths = Files.walk(Paths.get("C:\\\\Users\\\\jer6093\\\\Downloads\\\\Backup\\\\Backup\\\\SPEED")) ) {
			direct = paths
	                .filter(Files::isRegularFile)
	                .map(Path::toFile)
	                .collect(Collectors.toList());
		}
		
		System.out.println(direct.get(5).getName());
		
		for(File f: direct) {
			files.add(Paths.get("C:\\Users\\jer6093\\Downloads\\Backup\\Backup\\SPEED\\" + f.getName()));
		}
		
		for(Path p : files) {
			if(match.matches(p)) {
				System.out.println(p);
			}
			else {
				Files.delete(p);
			}
		}
		
	}

}
